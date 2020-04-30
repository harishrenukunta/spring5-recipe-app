package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.converters.RecipeCommandToRecipe;
import guru.springframework.spring5recipeapp.converters.RecipeToRecipeCommand;
import guru.springframework.spring5recipeapp.domain.Difficulty;
import guru.springframework.spring5recipeapp.domain.Notes;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class RecipeServiceTest {

    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    private RecipeToRecipeCommand recipeToRecipeCommand;

    private Recipe recipe1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    void getRecipesTest() {
        final List<Recipe> recipeData = new ArrayList<>();
        final Recipe recipe = new Recipe();
        recipe.setDescription("Chicken Tikka Masala");
        recipeData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeData);
        assertThat(recipeService.getRecipes().size()).isEqualTo(1);
        assertThat(recipeService.getRecipes().get(0).getDescription()).isEqualToIgnoringCase("Chicken Tikka Masala");
        verify(recipeRepository, times(2)).findAll();
    }

    @Test
    public void getRecipeByIdTest(){
        recipe1 = Recipe.builder().id(1L).cookTime(10).difficulty(Difficulty.Easy).description("Pav Bhaji preparation")
                    .notes(Notes.builder().recipeNotes("Its a tangy dish").id(2L).build())
                    .build();
        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe1));

        final Recipe recipe = recipeService.getRecipeById(1L);

        assertThat(recipe).isNotNull();
        assertThat(recipe.getDifficulty()).isEqualTo(Difficulty.Easy);
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,never()).findAll();
    }
}