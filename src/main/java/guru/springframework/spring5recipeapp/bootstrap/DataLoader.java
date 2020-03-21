package guru.springframework.spring5recipeapp.bootstrap;

import guru.springframework.spring5recipeapp.domain.*;
import guru.springframework.spring5recipeapp.repositories.CategoryRepository;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import guru.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final UnitOfMeasureRepository uomRepo;
    private final CategoryRepository catRepo;
    private final RecipeRepository recipeRepo;

    public DataLoader(UnitOfMeasureRepository uomRepo, CategoryRepository catRepo, RecipeRepository recipeRepo) {
        this.uomRepo = uomRepo;
        this.catRepo = catRepo;
        this.recipeRepo = recipeRepo;
    }

    public List<Recipe> getRecipes() {
        final List<Recipe> recipes = new ArrayList<>();
        Optional<UnitOfMeasure> eachUomOptional = uomRepo.findByUom("Each");
        if(!eachUomOptional.isPresent()){
            new RuntimeException("Excepted UOM not found");
        }

        Optional<UnitOfMeasure> tspUomOptional = uomRepo.findByUom("Teaspoon");
        if(!tspUomOptional.isPresent()){
            new RuntimeException("Excepted UOM not found");
        }

        Optional<UnitOfMeasure> tblUomOptional = uomRepo.findByUom("Tablespoon");
        if(!tblUomOptional.isPresent()){
            new RuntimeException("Excepted UOM not found");
        }

        Optional<UnitOfMeasure> pinchUomOptional = uomRepo.findByUom("Pinch");
        if(!pinchUomOptional.isPresent()){
            new RuntimeException("Excepted UOM not found");
        }

        Optional<UnitOfMeasure> ounceUomOptional = uomRepo.findByUom("Ounce");
        if(ounceUomOptional.isPresent()){
            new RuntimeException("Excepted UOM not found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = uomRepo.findByUom("Cup");
        if(cupsUomOptional.isPresent()){
            new RuntimeException("Excepted UOM not found");
        }

        Optional<UnitOfMeasure> dashUomOptional = uomRepo.findByUom("Dash");
        if(dashUomOptional.isPresent()){
            new RuntimeException("Excepted UOM not found");
        }

        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tspUom = tspUomOptional.get();
        UnitOfMeasure tblUom = tblUomOptional.get();
        UnitOfMeasure ounceUom = ounceUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();

        Optional<Category> americanCategoryOptional = catRepo.findByDescription("American");
        if(!americanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category not found");
        }

        Optional<Category> mexicanCategoryOptional = catRepo.findByDescription("Mexican");
        if(!mexicanCategoryOptional.isPresent()){
            throw new RuntimeException("Expected Category not found");
        }

        Recipe guacaRecipe = new Recipe();
        guacaRecipe.setCookTime(10);
        guacaRecipe.setDifficulty(Difficulty.EASY);
        guacaRecipe.setDescription("Its a wonderful and delicious greeek recipe");
        guacaRecipe.setDirection("Direction 1");
        guacaRecipe.setServings(4);
        guacaRecipe.setPrepTime(3);

        guacaRecipe.getCategories().add(americanCategoryOptional.get());
        guacaRecipe.getCategories().add(mexicanCategoryOptional.get());

        Notes guacaNotes = new Notes();
        guacaNotes.setRecipeNotes("Guaca notes");
        guacaRecipe.setNotes(guacaNotes);

        guacaRecipe.addIngredient(new Ingredient("kosher Salt", new BigDecimal(2), tspUom))
                .addIngredient(new Ingredient("Ripen Avocado", new BigDecimal(2), eachUom))
                .addIngredient(new Ingredient("Lemon", new BigDecimal(1), eachUom))
                .addIngredient(new Ingredient("Red onion or thinly sliced green onion", new BigDecimal(2), tblUom))
                .addIngredient(new Ingredient("Freshly sliced black pepper", new BigDecimal(1), dashUom))
                .addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tblUom))
                .addIngredient(new Ingredient("ripe tomato", new BigDecimal(.5), eachUom));
        recipes.add(guacaRecipe);

        Recipe tacoRecipe = new Recipe();
        tacoRecipe.setDifficulty(Difficulty.MEDIUM);
        tacoRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacoRecipe.setServings(4);
        tacoRecipe.setCookTime(15);
        tacoRecipe.setPrepTime(5);
        tacoRecipe.setDirection("Taco Recipe directions for preparation and cooking");
        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("We have a motto and that is that everything goes well tortillas");
        tacoRecipe.setNotes(tacoNotes);

        tacoRecipe.addIngredient(new Ingredient("Ancho Chilli powder", new BigDecimal(2), tblUom))
                .addIngredient(new Ingredient("Dried Oregano", new BigDecimal(1), tspUom))
                .addIngredient(new Ingredient("Dried Cumin", new BigDecimal(1), tspUom))
                .addIngredient(new Ingredient("Sugar", new BigDecimal(1), tspUom))
                .addIngredient(new Ingredient("Clove or Garlic, Chopped", new BigDecimal(1), eachUom))
                .addIngredient(new Ingredient("Finely grated Orange Zestr", new BigDecimal(1), tblUom))
                .addIngredient(new Ingredient("Freshly Squeezed Orange Juice", new BigDecimal(3), tblUom))
                .addIngredient(new Ingredient("Olive Oil", new BigDecimal(1), tblUom))
                .addIngredient(new Ingredient("Boneless Chicken thigh", new BigDecimal(4), tblUom))
                .addIngredient(new Ingredient("Small Can tortillas", new BigDecimal(8), eachUom))
                .addIngredient(new Ingredient("Packed baby arugula", new BigDecimal(3), cupsUom))
                .addIngredient(new Ingredient("Medium ripe avocado, slic", new BigDecimal(2), eachUom))
                .addIngredient(new Ingredient("Radishes, thinly sliced", new BigDecimal(4), eachUom));
        tacoRecipe.getCategories().add(americanCategoryOptional.get());
        tacoRecipe.getCategories().add(mexicanCategoryOptional.get());

        recipes.add(tacoRecipe);
        return recipes;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepo.saveAll(getRecipes());
    }

}
