package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AppTest {
    private StringSchema schema;

    @BeforeEach
    void toPrepareForTests() {
        Validator validator = new Validator();
        schema = validator.string();
    }

    @Test
    void stringSchemaWthOutOptionsTest() {
        assertEquals(true, schema.isValid(""));
        assertEquals(true, schema.isValid(null));
        assertEquals(true, schema.isValid("hexlet"));
        assertEquals(true, schema.isValid("All values is valid"));
    }

    @Test
    void stringSchemaRequiredTest() {
        assertEquals(true, schema.isValid(""));
        assertEquals(true, schema.isValid(null));

        schema.required();

        assertEquals(true, schema.isValid("what does the fox say"));
        assertEquals(true, schema.isValid("hexlet"));
        assertEquals(false, schema.isValid(null));
        assertEquals(false, schema.isValid(""));
    }

    @Test
    void stringSchemaContainsTest() {
        assertEquals(true, schema.contains("what").isValid("what does the fox say"));
        assertEquals(false, schema.contains("whatthe").isValid("what does the fox say"));

        assertEquals(false, schema.isValid("what does the fox say"));
        assertEquals(true, schema.isValid("what whatthe"));
        assertEquals(false, schema.isValid(null));
    }

    @Test
    void stringSchemaMinLengthTest() {
        final int minLength = 3;

        assertEquals(true, schema.minLength(minLength).isValid("whatthe"));
        assertEquals(true, schema.isValid("lol"));
        assertEquals(false, schema.isValid(null));
    }

    @Test
    void stringSchemaAllOptionsTest() {
        final int minLength = 3;

        assertEquals(true, schema.isValid(""));
        assertEquals(true, schema.isValid(null));

        schema.required();

        assertEquals(true, schema.isValid("what does the fox say"));
        assertEquals(true, schema.isValid("hexlet"));
        assertEquals(false, schema.isValid(null));
        assertEquals(false, schema.isValid(""));

        schema.contains("what");

        assertEquals(true, schema.isValid("what does the fox say"));

        schema.contains("whatthe");

        assertEquals(false, schema.isValid("what does the fox say"));
        assertEquals(true, schema.isValid("what whatthe"));
        assertEquals(false, schema.isValid(null));

        schema.minLength(minLength);

        assertEquals(true, schema.isValid("whatthe"));
        assertEquals(false, schema.isValid("lol"));
        assertEquals(false, schema.isValid("l"));
        assertEquals(false, schema.isValid(null));
    }


}
