package guru.springframework.spring5recipeapp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    String description;

    @ManyToMany(mappedBy="categories")
    Set<Recipe> recipes;
}
