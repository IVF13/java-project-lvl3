package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public class Validator {

    final StringSchema string() {
        return new StringSchema();
    }

    final NumberSchema number() {
        return new NumberSchema();
    }

    final MapSchema map() {
        return new MapSchema();
    }

}
