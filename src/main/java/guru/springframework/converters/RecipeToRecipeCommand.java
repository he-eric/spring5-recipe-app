package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private IngredientToIngredientCommand ingredientConverter;
    private CategoryToCategoryCommand categoryConverter;
    private NotesToNotesCommand notesConverter;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientConverter, CategoryToCategoryCommand categoryConverter,
                                 NotesToNotesCommand notesConverter) {
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if (source == null)
            return null;
        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setServings(source.getServings());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setNotes(notesConverter.convert(source.getNotes()));

        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients()
                    .forEach(ingredient -> recipeCommand
                            .getIngredients()
                            .add(ingredientConverter.convert(ingredient)));
        }

        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories()
                    .forEach(category -> recipeCommand
                            .getCategories()
                            .add(categoryConverter.convert(category)));
        }

        return recipeCommand;
    }
}
