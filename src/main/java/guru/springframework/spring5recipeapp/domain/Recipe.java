package guru.springframework.spring5recipeapp.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
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
    Set<Ingredient> ingredients;

}
