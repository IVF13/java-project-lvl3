package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;


class AppTest {

    @Test
    void stringSchemaTest() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertEquals(true, schema.isValid(""));
        assertEquals(true, schema.isValid(null));

        schema.required();

        assertEquals(true, schema.isValid("what does the fox say"));
        assertEquals(true, schema.isValid("hexlet"));
        assertEquals(false, schema.isValid(null));
        assertEquals(false, schema.isValid(""));

        assertEquals(true, schema.contains("what").isValid("what does the fox say"));
        assertEquals(false, schema.contains("whatthe").isValid("what does the fox say"));

        assertEquals(false, schema.isValid("what does the fox say"));

    }

}
