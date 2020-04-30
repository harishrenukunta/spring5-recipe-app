package guru.springframework.spring5recipeapp.commands;

import guru.springframework.spring5recipeapp.domain.Category;
import guru.springframework.spring5recipeapp.domain.Difficulty;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.Notes;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeCommand {
    Long id;
    String description;
    Integer prepTime;
    Integer cookTime;
    Integer servings;
    String source;
    String url;
    String direction;
    NotesCommand notes;
    Byte[] image;
    Set<IngredientCommand> ingredients = new HashSet<>();
    Difficulty difficulty;
    Set<CategoryCommand> categories = new HashSet<>();
}
