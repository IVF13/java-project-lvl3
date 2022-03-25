package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public final class StringSchema extends BaseSchema {
    private final List<String> stringsMustBeContained = new ArrayList<>();
    private int minLength = 0;

    public StringSchema minLength(int length) {
        this.required();
        getCheckList().add(SchemaChecks.minLength);
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String content) {
        this.required();
        getCheckList().add(SchemaChecks.contains);
        this.stringsMustBeContained.add(content);
        return this;
    }

    @Override
    public boolean toRunChecks(Object stringToValidate) {
        boolean isValid = true;

        isValid = isRequired(stringToValidate, isValid);

        isValid = isContain(stringToValidate, isValid);

        isValid = isLongEnough(stringToValidate, isValid);

        return isValid;
    }

    private boolean isRequired(Object stringToValidate, boolean isValid) {
        if (this.getCheckList().contains(SchemaChecks.required)) {
            if (stringToValidate == null) {
                return false;
            } else if (stringToValidate.toString().isEmpty()) {
                return false;
            }
        }
        return isValid;
    }

    private boolean isContain(Object stringToValidate, boolean isValid) {
        if (this.getCheckList().contains(SchemaChecks.contains)) {
            for (String content : getStringsMustBeContained()) {
                if (stringToValidate == null || !stringToValidate.toString().contains(content)) {
                    return false;
                }
            }
        }
        return isValid;
    }

    private boolean isLongEnough(Object stringToValidate, boolean isValid) {
        if (this.getCheckList().contains(SchemaChecks.minLength)) {
            if (stringToValidate == null || stringToValidate.toString().length() < getMinLength()) {
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
