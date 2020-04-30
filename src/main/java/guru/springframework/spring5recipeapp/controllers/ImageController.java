package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.services.ImageService;
import guru.springframework.spring5recipeapp.services.RecipeService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Controller
public class ImageController {

    private final RecipeService recipeService;
    private final ImageService imageService;

    public ImageController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }

    @GetMapping("/recipe/{recipeId}/imageform")
    public String getImageLoadForm(@PathVariable String recipeId, final Model model ){
        final RecipeCommand recipeCmd = recipeService.findRecipeCommandById(new Long(recipeId));
        Optional.ofNullable(recipeCmd).orElseThrow(() -> new RuntimeException("Recipe with id:" + recipeId + " not found"));
        model.addAttribute("recipe", recipeCmd);
        return "recipe/imageform";
    }

    @PostMapping("/recipe/{recipeId}/image")
    public String uploadImageFile(@PathVariable final String recipeId, @RequestParam("imagefile") MultipartFile  imagefile){
        imageService.saveImage(recipeId, imagefile);
        return "redirect:/recipe/" + recipeId + "/show";
    }

    @GetMapping("/recipe/{recipeId}/image")
    public void renderImage(@PathVariable String recipeId, HttpServletResponse response) throws IOException {
        final RecipeCommand recipeCmd = recipeService.findRecipeCommandById(Long.valueOf(recipeId));
        final Byte[] wrappedByteArray = recipeCmd.getImage();
        if(recipeCmd.getImage() == null){
            return;
        }
        byte[] byteArray = new byte[wrappedByteArray.length];
        int i = 0;
        for(Byte wrappedByte: wrappedByteArray){
            byteArray[i++] = wrappedByte;
        }
        final InputStream is = new ByteArrayInputStream(byteArray);
        response.setContentType("image/jpeg");
        IOUtils.copy(is, response.getOutputStream());
    }
}
