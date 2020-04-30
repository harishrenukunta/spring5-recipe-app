package guru.springframework.spring5recipeapp.commands;

import guru.springframework.spring5recipeapp.domain.Recipe;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryCommand {
    Long id;
    String description;
}
