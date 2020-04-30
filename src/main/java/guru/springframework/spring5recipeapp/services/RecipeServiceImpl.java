package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.converters.RecipeCommandToRecipe;
import guru.springframework.spring5recipeapp.converters.RecipeToRecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepo;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepo, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepo = recipeRepo;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public List<Recipe> getRecipes() {
        return (List<Recipe>) recipeRepo.findAll();
    }

    @Override
    public Recipe getRecipeById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepo.findById(id);
        return recipeOptional.orElse(null);
    }

    @Override
    public void deleteRecipeById(Long id) {
        recipeRepo.deleteById(id);
    }

    @Override
    public RecipeCommand findRecipeCommandById(Long id) {
        final Recipe recipe = recipeRepo.findById(id).orElse(null);
        return recipeToRecipeCommand.convert(recipe);

    }

    @Override
    public Recipe findById(Long id){
        return recipeRepo.findById(id).orElse(null);
    }

    @Override
    public IngredientCommand findIngredientByRecipeAndIngredientId(Long recipeId, Long ingredientId) {
        final RecipeCommand recipeCmd = findRecipeCommandById(recipeId);
        final Optional<IngredientCommand> recipeIngredientCmd =  recipeCmd.getIngredients().stream()
                .filter(ingredientCmd -> ingredientCmd.getId().compareTo(ingredientId) == 0)
                .findFirst();
        return recipeIngredientCmd.orElse(null);

    }

    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
        final Recipe recipe = recipeCommandToRecipe.convert(recipeCommand);
        final Recipe savedRecipe = saveRecipe(recipe);
        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    public Recipe saveRecipe(Recipe recipe) {
        final Recipe savedRecipe = recipeRepo.save(recipe);
        log.debug("Saved Recipe with id:" + savedRecipe.getId());
        return savedRecipe;
    }

}
