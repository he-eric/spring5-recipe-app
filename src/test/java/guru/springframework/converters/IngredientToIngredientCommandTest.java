package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    static final Recipe RECIPE = new Recipe();
    static final Long ID_VALUE = new Long(1L);
    static final String DESCRIPTION = "description";
    static final BigDecimal AMOUNT = new BigDecimal(5);
    IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullConvert() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void convertNullUOM() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setRecipe(RECIPE);
        ingredient.setUom(null);

        IngredientCommand command = converter.convert(ingredient);
        assertNull(command.getUnitOfMeasure());
        assertEquals(ID_VALUE, command.getId());
        assertEquals(AMOUNT, command.getAmount());
        assertEquals(DESCRIPTION, command.getDescription());
    }

    @Test
    public void convertWithUOM() {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ID_VALUE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setRecipe(RECIPE);

        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID_VALUE);
        uom.setDescription(DESCRIPTION);
        ingredient.setUom(uom);

        IngredientCommand command = converter.convert(ingredient);
        assertEquals(ID_VALUE, command.getId());
        assertNotNull(command.getUnitOfMeasure());
        assertEquals(ID_VALUE, command.getUnitOfMeasure().getId());
        assertEquals(AMOUNT, command.getAmount());
        assertEquals(DESCRIPTION, command.getDescription());
    }
}