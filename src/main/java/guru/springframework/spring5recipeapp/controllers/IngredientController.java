package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.services.IngredientService;
import guru.springframework.spring5recipeapp.services.RecipeService;
import guru.springframework.spring5recipeapp.services.UomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Set;

@Controller
@Slf4j
@RequiredArgsConstructor
public class IngredientController {

    public final RecipeService recipeService;
    private final UomService uomService;
    private final IngredientService ingredientService;

    @RequestMapping("/recipe/{id}/ingredients")
    public String getRecipeIngredients(@PathVariable String id, Model model){
        final RecipeCommand recipeCmd = recipeService.findRecipeCommandById(new Long(id));
        model.addAttribute("recipe", recipeCmd);
        return "/recipe/ingredient/list";
    }

    @RequestMapping("/recipe/{recipeId}/ingredient/new")
    public String addNewIngredientToRecipe(@PathVariable final String recipeId, final Model model){
        final RecipeCommand recipeCmd = recipeService.findRecipeCommandById(new Long(recipeId));
        Optional.ofNullable(recipeCmd).orElseThrow(()-> new RuntimeException("No recipe found with id:" + recipeId));

        final IngredientCommand ingredientCmd = new IngredientCommand();
        ingredientCmd.setRecipeId(new Long(recipeId));
        ingredientCmd.setUom(new UnitOfMeasureCommand());
        model.addAttribute("ingredient", ingredientCmd);

        final Set<UnitOfMeasureCommand> uomList = uomService.findAllUomCommands();
        model.addAttribute("uomList", uomList);
        return "/recipe/ingredient/ingredientform";
    }

    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String getIngredientCmdByRecipientAndIngredientId(@PathVariable final String recipeId, @PathVariable final String ingredientId, Model model){
        final IngredientCommand recipeIngredientCmd = recipeService.findIngredientByRecipeAndIngredientId(new Long(recipeId), new Long(ingredientId));
        model.addAttribute("ingredient", recipeIngredientCmd);
        return "/recipe/ingredient/show";
    }

    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredientFromRecipe(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
        ingredientService.deleteById(new Long(recipeId), new Long(ingredientId));
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }

    @RequestMapping({"/recipe/{recipeId}/ingredient/{ingredientId}/update"})
    public String showIngredientUpdateView(@PathVariable final String recipeId, @PathVariable final String ingredientId, final Model model){
        final IngredientCommand recipeIngredientCmd = recipeService.findIngredientByRecipeAndIngredientId(new Long(recipeId), new Long(ingredientId));
        final Set<UnitOfMeasureCommand> allUomsCmd = uomService.findAllUomCommands();
        model.addAttribute("ingredient", recipeIngredientCmd);
        model.addAttribute("uomList", allUomsCmd);
        return "/recipe/ingredient/ingredientform";
    }

    @PostMapping
    @RequestMapping({"/recipe/{recipeId}/ingredient"})
    public String updateRecipeIngredient(@PathVariable String recipeId, @ModelAttribute IngredientCommand recipeIngredientCmd){
        IngredientCommand savedRecipeIngredientCmd = ingredientService.saveIngredientCommand(recipeIngredientCmd);
        log.debug("Updated ingredient with id" + savedRecipeIngredientCmd.getId());
        return "redirect:/recipe/" + savedRecipeIngredientCmd.getRecipeId() + "/ingredients";

    }
}
