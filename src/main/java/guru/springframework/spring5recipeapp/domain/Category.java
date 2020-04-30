package guru.springframework.spring5recipeapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude={"recipes"})
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    String description;

    @ManyToMany(mappedBy="categories")
    Set<Recipe> recipes = new HashSet<>();
}
