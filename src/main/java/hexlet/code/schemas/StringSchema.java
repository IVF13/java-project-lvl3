package hexlet.code.schemas;

import hexlet.code.StringSchemaValidator;

import java.util.HashMap;
import java.util.Map;

public final class StringSchema {
    private final Map<StringSchemaChecks, String> checkList = new HashMap<>();
    private final StringSchemaValidator validator;

    public StringSchema() {
        this.validator = new StringSchemaValidator();
    }

    public boolean isValid(String stringToValidate) {
        return validator.isValid(checkList, stringToValidate);
    }

    public void required() {
        checkList.put(StringSchemaChecks.required, "");
    }

    public StringSchema minLength(int length) {
        checkList.put(StringSchemaChecks.minLength, String.valueOf(length));
        return this;
    }

    public StringSchema contains(String content) {
        checkList.put(StringSchemaChecks.contains, content);
        return this;
    }

}
