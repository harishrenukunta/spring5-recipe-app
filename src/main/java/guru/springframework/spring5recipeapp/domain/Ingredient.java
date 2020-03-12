package guru.springframework.spring5recipeapp.domain;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String description;
    BigDecimal qty;

    @OneToOne
    UnitOfMeasure uom;

    public Ingredient(final String description, final BigDecimal qty, final UnitOfMeasure uom,final Recipe recipe){
        this.description = description;
        this.qty = qty;
        this.uom = uom;
        this.recipe = recipe;
    }

    @ManyToOne
    Recipe recipe;

    public Ingredient(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public UnitOfMeasure getUom() {
        return uom;
    }

    public void setUom(UnitOfMeasure uom) {
        this.uom = uom;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
