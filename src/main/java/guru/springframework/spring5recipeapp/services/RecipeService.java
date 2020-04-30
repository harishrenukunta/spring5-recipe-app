package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;

import java.util.List;

public interface RecipeService {
    public List<Recipe> getRecipes();
    public Recipe getRecipeById(final Long id);
    public void deleteRecipeById(final Long id);
    public Recipe findById(Long Id);
    public RecipeCommand findRecipeCommandById(Long id);
    public IngredientCommand findIngredientByRecipeAndIngredientId(final Long recipeId, final Long ingredientId);
    public RecipeCommand saveRecipeCommand(final RecipeCommand recipeCommand);
    public Recipe saveRecipe(final Recipe recipe);
}
