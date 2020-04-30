package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.converters.IngredientCommandToIngredient;
import guru.springframework.spring5recipeapp.converters.RecipeCommandToRecipe;
import guru.springframework.spring5recipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final RecipeService recipeService;
    private final UnitOfMeasureCommandToUnitOfMeasure uomCmdToUom;
    private final IngredientCommandToIngredient ingCmgToIng;

    @Override
    public IngredientCommand saveIngredientCommand(final IngredientCommand ingredientCmd) {
        final Recipe recipe= recipeService.getRecipeById(ingredientCmd.getRecipeId());
        final Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
                .filter(ingredient -> ingredientCmd.getId()!=null && ingredient.getId().compareTo(ingredientCmd.getId())==0)
                .findFirst();
        if(ingredientOptional.isPresent()){
            final Ingredient ing = ingredientOptional.get();
            ing.setQty(ingredientCmd.getQty());
            ing.setUom(uomCmdToUom.convert(ingredientCmd.getUom()));
        }else{
            final Ingredient newIngredient = ingCmgToIng.convert(ingredientCmd);
            newIngredient.setRecipe(recipe);
            recipe.getIngredients().add(newIngredient);
        }
        final Recipe savedRecipe = recipeService.saveRecipe(recipe);
        return ingredientCmd;
    }

    @Override
    public void deleteById(Long recipeId, Long ingredientId) {
        final Recipe recipe = recipeService.findById(recipeId);
        Optional.ofNullable(recipe)
                .orElseThrow(()->new RuntimeException("No Recipe found with id" + recipeId));

        final Ingredient ingredientToDelete = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().compareTo(ingredientId) == 0)
                .findFirst()
                .orElse(null);

        Optional.ofNullable(ingredientToDelete).orElseThrow(()-> new RuntimeException("There is no ingredient with id:" + ingredientId));
        ingredientToDelete.setRecipe(null);
        recipe.getIngredients().remove(ingredientToDelete);
        recipeService.saveRecipe(recipe);

    }

}
