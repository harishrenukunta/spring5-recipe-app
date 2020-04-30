package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;
import guru.springframework.spring5recipeapp.commands.RecipeCommand;
import guru.springframework.spring5recipeapp.services.RecipeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {
    MockMvc mock;
    @Mock
    RecipeService recipeService;

    @InjectMocks
    IngredientController controllerToTest;

    @BeforeEach
    void setUp() {
        mock = MockMvcBuilders.standaloneSetup(controllerToTest).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRecipeIngredients() throws Exception {
        RecipeCommand recipeCmd = RecipeCommand.builder().id(1L).description("Chicken Tikka").build();
        when(recipeService.findRecipeCommandById(anyLong())).thenReturn(recipeCmd);
        mock.perform(get("/recipe/1/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe") );
    }

    @Test
    public void getIngredientByRecipeAndIngredientIdTest() throws Exception {
        final Long ingredientId = 2L;
        final IngredientCommand returnIngredientCmd = IngredientCommand.builder().id(ingredientId).build();
        when(recipeService.findIngredientByRecipeAndIngredientId(anyLong(), anyLong())).thenReturn(returnIngredientCmd);
        mock.perform(get(String.format("/recipe/1/ingredient/%s/show", ingredientId.toString())))
                .andExpect(status().isOk())
                .andExpect(view().name("/recipe/ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));
    }
}