package hexlet.code.schemas;

import hexlet.code.StringSchemaValidator;

import java.util.ArrayList;
import java.util.List;

public final class StringSchema {
    private final List<StringSchemaChecks> checkList = new ArrayList<>();
    private final List<String> stringsMustBeContained = new ArrayList<>();
    private int minLength = 0;
    private final StringSchemaValidator validator;

    public StringSchema() {
        this.validator = new StringSchemaValidator();
    }

    public boolean isValid(String stringToValidate) {
        return validator.isValid(checkList, stringsMustBeContained, minLength, stringToValidate);
    }

    public void required() {
        checkList.add(StringSchemaChecks.required);
    }

    public StringSchema minLength(int length) {
        checkList.add(StringSchemaChecks.required);
        checkList.add(StringSchemaChecks.minLength);
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String content) {
        checkList.add(StringSchemaChecks.required);
        checkList.add(StringSchemaChecks.contains);
        stringsMustBeContained.add(content);
        return this;
    }

}
