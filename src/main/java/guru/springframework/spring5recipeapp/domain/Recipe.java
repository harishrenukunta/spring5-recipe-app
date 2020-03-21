package guru.springframework.spring5recipeapp.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    String description;
    Integer prepTime;
    Integer cookTime;
    Integer servings;
    String source;
    String url;
    String direction;

    @OneToOne(cascade = CascadeType.ALL)
    Notes notes;

    @Lob
    Byte[] image;

    @OneToMany(cascade=CascadeType.ALL,mappedBy = "recipe")
    Set<Ingredient> ingredients = new HashSet<>();

    @Enumerated(value=EnumType.STRING)
    Difficulty difficulty;

    @ManyToMany
    @JoinTable(name="recipe_category", joinColumns = {@JoinColumn(name="recipe_id")}, inverseJoinColumns = {@JoinColumn(name="category_id")})
    Set<Category> categories = new HashSet<>();

    public void setNotes(Notes notes){
        this.notes = notes;
        notes.setRecipe(this);
    }

    public Recipe addIngredient(final Ingredient ingredient){
        this.getIngredients().add(ingredient);
        ingredient.setRecipe(this);
        return this;
    }
}
