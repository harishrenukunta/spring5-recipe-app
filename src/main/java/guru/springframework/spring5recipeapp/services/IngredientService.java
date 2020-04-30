package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.IngredientCommand;

public interface IngredientService {

    public IngredientCommand saveIngredientCommand(final IngredientCommand ingredientCmd);
    public void deleteById(final Long recipeId, final Long ingredientId);
}
