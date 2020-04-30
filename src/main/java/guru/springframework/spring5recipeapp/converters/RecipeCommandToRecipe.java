package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.domain.Category;
import guru.springframework.spring5recipeapp.domain.Recipe;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {
    @Override
    public Recipe convert(RecipeCommand  recipeCommand) {
        if(recipeCommand == null){
            return null;
        }

        final CategoryCommandToCategory catCmdToCat = new CategoryCommandToCategory();
        Set<Category> categories = new HashSet<>();
        recipeCommand.getCategories().iterator().forEachRemaining(categoryCommand -> {
            categories.add(catCmdToCat.convert(categoryCommand));
        });

        return Recipe.builder()
                .id(recipeCommand.getId())
                .cookTime(recipeCommand.getCookTime())
                .prepTime(recipeCommand.getPrepTime())
                .servings(recipeCommand.getServings())
                .url(recipeCommand.getUrl())
                .description(recipeCommand.getDescription())
                .notes(new NotesCommandToNotes().convert(recipeCommand.getNotes()))
                .difficulty(recipeCommand.getDifficulty())
                .direction(recipeCommand.getDirection())
                .source(recipeCommand.getSource())
                .categories(categories)
                .build();
    }
}
