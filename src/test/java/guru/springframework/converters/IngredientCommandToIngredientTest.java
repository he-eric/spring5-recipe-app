package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    static final Long ID_VALUE = new Long(1L);
    static final String DESCRIPTION = "description";
    static final BigDecimal AMOUNT = new BigDecimal(5);
    IngredientCommandToIngredient ingredientConverter;
    UnitOfMeasureCommandToUnitOfMeasure uomConverter;
    UnitOfMeasureCommand uomCommand;

    @Before
    public void setUp() throws Exception {
        uomConverter = new UnitOfMeasureCommandToUnitOfMeasure();
        ingredientConverter = new IngredientCommandToIngredient(uomConverter);
        uomCommand = new UnitOfMeasureCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(ingredientConverter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(ingredientConverter.convert(new IngredientCommand()));
    }

    @Test
    public void convert() {
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ID_VALUE);
        ingredientCommand.setDescription(DESCRIPTION);
        ingredientCommand.setAmount(AMOUNT);

        uomCommand.setId(ID_VALUE);
        uomCommand.setDescription(DESCRIPTION);
        ingredientCommand.setUnitOfMeasure(uomCommand);

        Ingredient ingredient = ingredientConverter.convert(ingredientCommand);
        assertEquals(ingredient.getId(), ID_VALUE);
        assertEquals(ingredient.getDescription(), DESCRIPTION);
        assertEquals(ingredient.getAmount(), AMOUNT);
        assertEquals(ingredient.getUom().getId(), ID_VALUE);
        assertEquals(ingredient.getUom().getDescription(), DESCRIPTION);
    }

    @Test
    public void convertWithNullUom() {
        IngredientCommand command = new IngredientCommand();
        command.setId(ID_VALUE);
        command.setDescription(DESCRIPTION);
        command.setAmount(AMOUNT);

        Ingredient ingredient = ingredientConverter.convert(command);

        assertNotNull(ingredient);
        assertNull(ingredient.getUom());
        assertEquals(ingredient.getId(), ID_VALUE);
        assertEquals(ingredient.getDescription(), DESCRIPTION);
        assertEquals(ingredient.getAmount(), AMOUNT);
    }
}