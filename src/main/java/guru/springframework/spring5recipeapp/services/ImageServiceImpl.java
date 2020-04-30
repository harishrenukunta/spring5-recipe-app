package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.domain.Recipe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{

    private final RecipeService recipeService;

    @Override
    public void saveImage(String recipeId, MultipartFile file) {
        final Recipe recipe = recipeService.findById(new Long(recipeId));
        Optional.ofNullable(recipe).orElseThrow(()-> new RuntimeException("No recipe found with id:" + recipeId));

        try {
            final Byte[] imageBytes = new Byte[file.getBytes().length];
            int i = 0;
            for(byte b : file.getBytes()){
                imageBytes[i++] = b ;
            }
            recipe.setImage(imageBytes);
            recipeService.saveRecipe(recipe);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
