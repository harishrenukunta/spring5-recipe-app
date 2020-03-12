package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepo;

    public RecipeServiceImpl(RecipeRepository recipeRepo) {
        this.recipeRepo = recipeRepo;
    }

    @Override
    public List<Recipe> getRecipes() {
        return (List<Recipe>) recipeRepo.findAll();
    }
}
