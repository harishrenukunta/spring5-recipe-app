package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {
    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {
        if(ingredientCommand == null){
            return null;
        }

        return Ingredient.builder()
                .description(ingredientCommand.getDescription())
                .id(ingredientCommand.getId())
                .qty(ingredientCommand.getQty())
                //.recipe(new RecipeCommandToRecipe().convert(ingredientCommand.getRecipeCmd()))
                .uom(new UnitOfMeasureCommandToUnitOfMeasure().convert(ingredientCommand.getUom()))
                .build();
    }
}
