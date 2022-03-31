package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class AppTest {
    private StringSchema stringSchema;
    private NumberSchema numberSchema;
    private MapSchema mapSchema;
    private final Validator validator = new Validator();

    @BeforeEach
    void toPrepareForValidatorTests() {
        stringSchema = validator.string();
        numberSchema = validator.number();
        mapSchema = validator.map();
    }

    @Test
    void stringSchemaWthOutOptionsTest() {
        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid(null));
        assertTrue(stringSchema.isValid("hexlet"));
        assertTrue(stringSchema.isValid("All values is valid"));
    }

    @Test
    void stringSchemaRequiredTest() {
        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid(null));

        stringSchema.required();

        assertTrue(stringSchema.isValid("what does the fox say"));
        assertTrue(stringSchema.isValid("hexlet"));
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(""));
        assertFalse(stringSchema.isValid(new ArrayList<>()));
    }

    @Test
    void stringSchemaContainsTest() {
        assertTrue(stringSchema.contains("what").isValid("what does the fox say"));
        assertFalse(stringSchema.contains("whatthe").isValid("what does the fox say"));

        assertFalse(stringSchema.isValid("what does the fox say"));
        assertTrue(stringSchema.isValid("what whatthe"));
        assertFalse(stringSchema.isValid(null));
    }

    @Test
    void stringSchemaMinLengthTest() {
        final int minLength = 3;

        assertTrue(stringSchema.minLength(minLength).isValid("whatthe"));
        assertTrue(stringSchema.isValid("lol"));
        assertFalse(stringSchema.isValid(null));
    }

    @Test
    void stringSchemaAllOptionsTest() {
        final int minLength = 3;

        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid(null));

        stringSchema.required();

        assertTrue(stringSchema.isValid("what does the fox say"));
        assertTrue(stringSchema.isValid("hexlet"));
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(""));

        stringSchema.contains("what");

        assertTrue(stringSchema.isValid("what does the fox say"));

        stringSchema.contains("whatthe");

        assertFalse(stringSchema.isValid("what does the fox say"));
        assertTrue(stringSchema.isValid("what whatthe"));
        assertFalse(stringSchema.isValid(null));

        stringSchema.minLength(minLength);

        assertTrue(stringSchema.isValid("whatthe"));
        assertFalse(stringSchema.isValid("lol"));
        assertFalse(stringSchema.isValid("l"));
        assertFalse(stringSchema.isValid(null));
    }

    @Test
    void numberSchemaRequiredTest() {
        assertTrue(numberSchema.isValid(null));

        numberSchema.required();

        assertFalse(numberSchema.isValid(null));
        assertTrue(numberSchema.isValid(10));
        assertFalse(numberSchema.isValid("5"));
        assertFalse(numberSchema.isValid(new ArrayList<>()));
    }

    @Test
    void numberSchemaPositiveTest() {
        assertTrue(numberSchema.positive().isValid(10));
        assertFalse(numberSchema.isValid(-10));
        assertFalse(numberSchema.isValid("f"));
        assertFalse(numberSchema.isValid(null));
    }

    @Test
    void numberSchemaRangeTest() {
        assertTrue(numberSchema.range(5, 10).isValid(6));

        assertTrue(numberSchema.isValid(5));
        assertTrue(numberSchema.isValid(8));
        assertTrue(numberSchema.isValid(10));
        assertFalse(numberSchema.isValid(4));
        assertFalse(numberSchema.isValid(11));
        assertFalse(numberSchema.isValid(null));
        assertFalse(numberSchema.isValid("t"));
    }

    @Test
    void numberSchemaAllOptionsTest() {
        assertTrue(numberSchema.isValid(-10));
        assertTrue(numberSchema.isValid(10));
        assertTrue(numberSchema.isValid("5"));
        assertTrue(numberSchema.isValid(null));

        numberSchema.required();

        assertTrue(numberSchema.isValid(-10));
        assertTrue(numberSchema.isValid(10));
        assertFalse(numberSchema.isValid("5"));
        assertFalse(numberSchema.isValid(null));

        numberSchema.positive();

        assertTrue(numberSchema.isValid(10));
        assertFalse(numberSchema.isValid(-10));
        assertFalse(numberSchema.isValid("f"));
        assertFalse(numberSchema.isValid(null));

        numberSchema.range(5, 10);

        assertTrue(numberSchema.isValid(5));
        assertTrue(numberSchema.isValid(8));
        assertTrue(numberSchema.isValid(10));
        assertFalse(numberSchema.isValid(-4));
        assertFalse(numberSchema.isValid(11));
        assertFalse(numberSchema.isValid(null));
        assertFalse(numberSchema.isValid("t"));
    }

    @Test
    void mapSchemaWthOutOptionsTest() {
        assertTrue(mapSchema.isValid(null));
    }

    @Test
    void mapSchemaRequiredTest() {
        assertTrue(mapSchema.isValid(""));
        assertTrue(mapSchema.isValid(null));

        mapSchema.required();

        assertFalse(mapSchema.isValid("hexlet"));
        assertFalse(mapSchema.isValid(null));
        assertFalse(mapSchema.isValid(3));
        assertFalse(mapSchema.isValid(new ArrayList<>()));
        assertTrue(mapSchema.isValid(new HashMap<>()));
    }

    @Test
    void mapSchemaSizeOfTest() {
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertTrue(mapSchema.isValid(data));

        mapSchema.sizeof(2);

        assertFalse(mapSchema.isValid(data));

        data.put("key2", "value2");

        assertTrue(mapSchema.isValid(data));

        assertFalse(mapSchema.isValid(null));
        assertFalse(mapSchema.isValid(""));

        assertFalse(mapSchema.isValid(new ArrayList<>()));
    }

}
