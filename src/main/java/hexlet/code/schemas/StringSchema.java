package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public final class StringSchema extends BaseSchema {
    private final List<String> stringsMustBeContained = new ArrayList<>();
    private int minLength = 0;

    public StringSchema minLength(int length) {
        this.required();
        getCheckList().add(SchemasChecks.minLength);
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String content) {
        this.required();
        getCheckList().add(SchemasChecks.contains);
        this.stringsMustBeContained.add(content);
        return this;
    }

    @Override
    public boolean toRunOtherChecks(Object stringToValidate, boolean isValid) {

        isValid = isContain(stringToValidate, isValid);

        isValid = isLongEnough(stringToValidate, isValid);

        return isValid;
    }

    @Override
    public boolean isRequired(Object stringToValidate, boolean isValid) {
        if (this.getCheckList().contains(SchemasChecks.required)) {
            if (!(stringToValidate instanceof String)) {
                return false;
            } else if (stringToValidate.toString().isEmpty()) {
                return false;
            }
        }
        return isValid;
    }

    private boolean isContain(Object stringToValidate, boolean isValid) {
        if (this.getCheckList().contains(SchemasChecks.contains)) {
            for (String content : getStringsMustBeContained()) {
                if (!stringToValidate.toString().contains(content)) {
                    return false;
                }
            }
        }
        return isValid;
    }

    private boolean isLongEnough(Object stringToValidate, boolean isValid) {
        if (this.getCheckList().contains(SchemasChecks.minLength)) {
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
