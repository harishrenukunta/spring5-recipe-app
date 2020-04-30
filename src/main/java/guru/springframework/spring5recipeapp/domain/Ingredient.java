package guru.springframework.spring5recipeapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(exclude={"recipe"})
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String description;
    BigDecimal qty;

    @OneToOne
    UnitOfMeasure uom;

    public Ingredient(final String description, final BigDecimal qty, final UnitOfMeasure uom){
        this.description = description;
        this.qty = qty;
        this.uom = uom;
    }

    @ManyToOne
    Recipe recipe;

    public Ingredient(){}
}
