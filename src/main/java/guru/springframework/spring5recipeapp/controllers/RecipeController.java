package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping(path="/recipe/{id}/show")
    public String getRecipeById(@PathVariable("id") String id, Model model){
        final Recipe recipe = recipeService.getRecipeById(new Long(id));
        model.addAttribute("recipe", recipe);
        return "recipe/show";
    }

    @RequestMapping(path="/recipe/{id}/update")
    public String updaeRecipe(@PathVariable("id") String id, final Model model){
        final RecipeCommand recipeCmd = recipeService.findRecipeCommandById(new Long(id));
        model.addAttribute("recipe", recipeCmd);
        return "recipe/recipeform";
    }

    @RequestMapping(path="/recipe/{id}/delete")
    public String deleteRecipe(@PathVariable("id") String id){
        recipeService.deleteRecipeById(new Long(id));
        return "redirect:/index";
    }

    @RequestMapping("recipe/new")
    public String newRecipeForm(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand){
         recipeCommand = recipeService.saveRecipeCommand(recipeCommand);
        log.info("Saved recipe with id:" + recipeCommand.getId());
        return "redirect:/recipe/" + recipeCommand.getId() + "/show";
    }
}
