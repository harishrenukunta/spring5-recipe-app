package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IndexControllerTest {

    private IndexController recipeController;

    @Mock
    private RecipeService recipeService;

    @Mock
    private Model model;

    @BeforeEach
    public void SetUp(){
        MockitoAnnotations.initMocks(this);
        recipeController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPageTest(){
        final List<Recipe> recipesData = new ArrayList<>();
        Recipe chickenCurryRecipe = new Recipe();
        chickenCurryRecipe.setId(1L);
        chickenCurryRecipe.setDescription("Chicken Curry");

        Recipe fishMasala = new Recipe();
        fishMasala.setId(2L);
        fishMasala.setDescription("Fish delicacy");

        recipesData.add(chickenCurryRecipe);
        recipesData.add(fishMasala);

        when(recipeService.getRecipes()).thenReturn(recipesData);

        ArgumentCaptor<List<Recipe>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        assertThat(recipeController.getRecipeIndexPage(model)).isEqualTo("index");
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"),argumentCaptor.capture());

        final List<Recipe> capturedRecipes = argumentCaptor.getValue();
        assertThat(capturedRecipes.size()).isEqualTo(2);
    }

    @Test
    public void indexControllerMockMvcTest() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

}