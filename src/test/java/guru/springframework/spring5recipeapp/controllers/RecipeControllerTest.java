package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.domain.Difficulty;
import guru.springframework.spring5recipeapp.domain.Notes;
import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.services.RecipeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class RecipeControllerTest {

    MockMvc mvc;
    @Mock
    private RecipeService recipeService;
    private RecipeController recipeController;

    private Recipe recipeReturned;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        final Notes fishFryNotes = Notes.builder().id(1L).recipeNotes("Rich in Omega-3 and very healthy")
                .build();
        recipeReturned = Recipe.builder().description("Fish Fry").cookTime(15).difficulty(Difficulty.Medium)
                .id(2L).notes(fishFryNotes).direction("1. Add Tilapia to Oil and use cast iron to fry")
                .source("Bengali Dish").build();
        recipeController = new RecipeController(recipeService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRecipeById() throws Exception {
        Mockito.when(recipeService.getRecipeById(anyLong())).thenReturn(recipeReturned);
        mvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        mvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"));
    }
}