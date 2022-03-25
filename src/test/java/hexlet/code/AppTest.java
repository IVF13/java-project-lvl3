package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class AppTest {
    private StringSchema schema;

    @BeforeEach
    void toPrepareForTests() {
        Validator validator = new Validator();
        schema = validator.string();
    }

    @Test
    void stringSchemaWthOutOptionsTest() {
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("hexlet"));
        assertTrue(schema.isValid("All values is valid"));
    }

    @Test
    void stringSchemaRequiredTest() {
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();

        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
    }

    @Test
    void stringSchemaContainsTest() {
        assertTrue(schema.contains("what").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));

        assertFalse(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("what whatthe"));
        assertFalse(schema.isValid(null));
    }

    @Test
    void stringSchemaMinLengthTest() {
        final int minLength = 3;

        assertTrue(schema.minLength(minLength).isValid("whatthe"));
        assertTrue(schema.isValid("lol"));
        assertFalse(schema.isValid(null));
    }

    @Test
    void stringSchemaAllOptionsTest() {
        final int minLength = 3;

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();

        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));

        schema.contains("what");

        assertTrue(schema.isValid("what does the fox say"));

        schema.contains("whatthe");

        assertFalse(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("what whatthe"));
        assertFalse(schema.isValid(null));

        schema.minLength(minLength);

        assertTrue(schema.isValid("whatthe"));
        assertFalse(schema.isValid("lol"));
        assertFalse(schema.isValid("l"));
        assertFalse(schema.isValid(null));
    }


}
