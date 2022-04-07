package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public final class StringSchema extends BaseSchema {
    private final List<String> stringsMustBeContained = new ArrayList<>();
    private int minLength = 0;

    public StringSchema required() {
        checkList.add(SchemasChecks.REQUIRED);
        return this;
    }

    public StringSchema minLength(int length) {
        checkList.add(SchemasChecks.MIN_LENGTH);
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String content) {
        checkList.add(SchemasChecks.CONTAINS);
        this.stringsMustBeContained.add(content);
        return this;
    }

    @Override
    public boolean isPassesOtherTests(Object stringToValidate, boolean isValid) {

        isValid = isContain(stringToValidate, isValid);

        isValid = isLongEnough(stringToValidate, isValid);

        return isValid;
    }

    @Override
    public boolean isRequired(Object stringToValidate, boolean isValid) {
        if (checkList.contains(SchemasChecks.REQUIRED)
                && (!(stringToValidate instanceof String) || stringToValidate.toString().isEmpty())) {
            return false;
        }
        return isValid;
    }

    private boolean isContain(Object stringToValidate, boolean isValid) {
        if (checkList.contains(SchemasChecks.CONTAINS) && stringToValidate instanceof String) {
            for (String content : getStringsMustBeContained()) {
                if (!stringToValidate.toString().contains(content)) {
                    return false;
                }
            }
        }
        return isValid;
    }

    private boolean isLongEnough(Object stringToValidate, boolean isValid) {
        if (checkList.contains(SchemasChecks.MIN_LENGTH) && stringToValidate instanceof String) {
            if (stringToValidate.toString().length() < getMinLength()) {
                return false;
            }
        }
        return isValid;
    }

    public List<String> getStringsMustBeContained() {
        return stringsMustBeContained;
    }

    public int getMinLength() {
        return minLength;
    }

}
