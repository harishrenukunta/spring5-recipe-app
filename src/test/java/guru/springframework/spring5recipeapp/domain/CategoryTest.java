package guru.springframework.spring5recipeapp.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
    }

    @Test
    void getId() {
        Long aId = 4L;
        category.setId(aId);
        assertThat(category.getId()).as("Checking category id...").isEqualTo(aId);
    }

    @Test
    void getDescription() {
        final String catDescription = "Very delicious Chicken dish";
        category.setDescription(catDescription);
        assertThat(category.getDescription()).as("Checking category description value....").isEqualTo(catDescription);
    }

    @Test
    void getRecipes() {
    }
}