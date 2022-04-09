package hexlet.code;

import hexlet.code.schemas.BaseSchema;
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
    void stringSchemaRequiredTest() {
        assertTrue(stringSchema.isValid(""));
        assertTrue(stringSchema.isValid(null));
        assertTrue(stringSchema.isValid(new ArrayList<>()));

        stringSchema.required();

        assertTrue(stringSchema.isValid("what does the fox say"));
        assertTrue(stringSchema.isValid("hexlet"));
        assertFalse(stringSchema.isValid(5));
        assertFalse(stringSchema.isValid(""));
        assertFalse(stringSchema.isValid(new ArrayList<>()));
    }

    @Test
    void stringSchemaContainsTest() {
        assertTrue(stringSchema.contains("what").isValid("what does the fox say"));
        assertFalse(stringSchema.contains("whatthe").isValid("what does the fox say"));

        assertFalse(stringSchema.isValid("what does the fox say"));
        assertTrue(stringSchema.isValid("what whatthe"));
        assertTrue(stringSchema.isValid(null));
        assertTrue(stringSchema.isValid(3));

        stringSchema.required();

        assertTrue(stringSchema.isValid("what whatthe"));
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid(3));
    }

    @Test
    void stringSchemaMinLengthTest() {
        final int minLength = 3;

        assertTrue(stringSchema.minLength(minLength).isValid("whatthe"));
        assertTrue(stringSchema.isValid("lol"));
        assertTrue(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid("mm"));

        stringSchema.required();

        assertTrue(stringSchema.isValid("lol"));
        assertFalse(stringSchema.isValid(null));
        assertFalse(stringSchema.isValid("mm"));
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
        assertTrue(numberSchema.isValid("f"));
        assertTrue(numberSchema.isValid(null));

        numberSchema.required();

        assertTrue(numberSchema.isValid(10));
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
        assertTrue(numberSchema.isValid(null));
        assertTrue(numberSchema.isValid("t"));

        numberSchema.required();

        assertTrue(numberSchema.isValid(10));
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
        assertTrue(mapSchema.isValid(new ArrayList<>()));
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

        mapSchema.sizeof(2);
        assertFalse(mapSchema.isValid(data));

        data.put("key2", "value2");
        assertTrue(mapSchema.isValid(data));

        assertTrue(mapSchema.isValid(null));
        assertTrue(mapSchema.isValid(""));
        assertTrue(mapSchema.isValid(new ArrayList<>()));

        mapSchema.required();

        assertFalse(mapSchema.isValid(null));
        assertFalse(mapSchema.isValid(""));
        assertFalse(mapSchema.isValid(new ArrayList<>()));
    }

    @Test
    void mapSchemaShapeTest() {
        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());

        mapSchema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertTrue(mapSchema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(mapSchema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(mapSchema.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertFalse(mapSchema.isValid(human4));

        assertTrue(mapSchema.isValid(""));
        assertTrue(mapSchema.isValid(null));
        assertTrue(mapSchema.isValid(new ArrayList<>()));

        mapSchema.required();

        assertFalse(mapSchema.isValid(""));
        assertFalse(mapSchema.isValid(null));
        assertFalse(mapSchema.isValid(new ArrayList<>()));
        assertTrue(mapSchema.isValid(human1));
        assertTrue(mapSchema.isValid(human2));
        assertFalse(mapSchema.isValid(human3));
        assertFalse(mapSchema.isValid(human4));
    }

}
