package guru.springframework.spring5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(exclude={"recipe"})
public class Notes {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    @OneToOne
    Recipe recipe;
    String recipeNotes;

    public Notes(){}

    public Notes(final String recipeNotes, final Recipe recipe) {
        this.recipeNotes = recipeNotes;
        this.recipe = recipe;
    }

    @OneToOne
    UnitOfMeasure uom;
}
