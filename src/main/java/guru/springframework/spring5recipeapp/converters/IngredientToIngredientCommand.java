package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    @Override
    public IngredientCommand convert(Ingredient src) {
        if(src == null){
            return null;
        }

        final RecipeToRecipeCommand recipeToRecipeCommand = new RecipeToRecipeCommand();
        return IngredientCommand.builder()
                .description(src.getDescription())
                .id(src.getId())
                .qty(src.getQty())
                .recipeId(src.getRecipe().getId())
                .uom(new UnitOfMeasureToUnitOfMeasureCommand().convert(src.getUom()))
                .build();
    }
}
