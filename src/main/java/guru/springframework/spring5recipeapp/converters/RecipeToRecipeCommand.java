package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.CategoryCommand;
import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final IngredientToIngredientCommand ingredientToIngredientCommand = new IngredientToIngredientCommand();

    @Override
    public RecipeCommand convert(final Recipe  recipe) {
        if(recipe == null){
            return null;
        }

        final CategoryToCategoryCommand catToCatCmd = new CategoryToCategoryCommand();
        //final IngredientToIngredientCommand ingredientToIngredientCommand = new IngredientToIngredientCommand();
        final Set<CategoryCommand> categoriesCommand = new HashSet<>();
        recipe.getCategories().iterator().forEachRemaining(category -> {
            if(category != null) {
                categoriesCommand.add(catToCatCmd.convert(category));
            }});

        final Set<IngredientCommand> ingredientCommands = new HashSet<>();

        if(recipe.getIngredients() != null) {
            recipe.getIngredients().iterator().forEachRemaining(ingredient -> {
                if (ingredient != null) {
                    ingredientCommands.add(ingredientToIngredientCommand.convert(ingredient));
                }
            });
        }

        return RecipeCommand.builder()
                .id(recipe.getId())
                .cookTime(recipe.getCookTime())
                .description(recipe.getDescription())
                .notes(new NotesToNotesCommand().convert(recipe.getNotes()))
                .direction(recipe.getDirection())
                .source(recipe.getSource())
                .categories(categoriesCommand)
                .ingredients(ingredientCommands)
                .servings(recipe.getServings())
                .image(recipe.getImage())
                .prepTime(recipe.getPrepTime())
                .url(recipe.getUrl())
                .difficulty(recipe.getDifficulty())
                .build();
    }
}
