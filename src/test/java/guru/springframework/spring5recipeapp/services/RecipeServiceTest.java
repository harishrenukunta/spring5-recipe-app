package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RecipeServiceTest {

    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
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
}