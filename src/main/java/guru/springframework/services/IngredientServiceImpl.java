package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeRepository recipeRepository;

    public IngredientServiceImpl(IngredientToIngredientCommand ingredientToIngredientCommand, RecipeRepository recipeRepository) {
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public IngredientCommand findByRecipeIdAndId(Long recipeId, Long id) {
        Optional<Recipe> optional = recipeRepository.findById(recipeId);
        if (!optional.isPresent()) {
            log.error("recipe id not found!");
        }
        Set<Ingredient> ingredients = optional.get().getIngredients();
        Optional<IngredientCommand> foundIngredient = ingredients.stream()
                .filter(ingredient -> ingredient.getId().equals(id))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient))
                .findFirst();
        if (!foundIngredient.isPresent()) {
            log.error("ingredient id not found!");
        }
        return foundIngredient.get();
    }
}
