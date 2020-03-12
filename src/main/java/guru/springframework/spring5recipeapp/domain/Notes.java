package guru.springframework.spring5recipeapp.domain;

import javax.persistence.*;

@Entity
public class Notes {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    @OneToOne
    Recipe recipe;
    String recipeNotes;

    public Notes(){}

    public Notes(final String recipeNotes, final Recipe recipe){
        this.recipeNotes = recipeNotes;
        this.recipe = recipe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getRecipeNotes() {
        return recipeNotes;
    }

    public void setRecipeNotes(String recipeNotes) {
        this.recipeNotes = recipeNotes;
    }

    public UnitOfMeasure getUom() {
        return uom;
    }

    public void setUom(UnitOfMeasure uom) {
        this.uom = uom;
    }

    @OneToOne
    UnitOfMeasure uom;
}
