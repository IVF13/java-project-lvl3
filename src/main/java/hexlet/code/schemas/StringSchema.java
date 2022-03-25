package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public final class StringSchema extends BaseSchema {
    private final List<String> stringsMustBeContained = new ArrayList<>();
    private int minLength = 0;

    public StringSchema minLength(int length) {
        getCheckList().add(SchemaChecks.required);
        getCheckList().add(SchemaChecks.minLength);
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String content) {
        getCheckList().add(SchemaChecks.required);
        getCheckList().add(SchemaChecks.contains);
        stringsMustBeContained.add(content);
        return this;
    }

    static boolean isValid(List<SchemaChecks> checkList, List<String> stringsMustBeContained,
                           int minLength, Object stringToValidate) {
        boolean isValid = true;

        isValid = isRequired(checkList, stringToValidate, isValid);

        isValid = isContain(checkList, stringsMustBeContained, stringToValidate, isValid);

        isValid = isLongEnough(checkList, minLength, stringToValidate, isValid);

        return isValid;
    }

    private static boolean isRequired(List<SchemaChecks> checkList, Object stringToValidate, boolean isValid) {
        if (checkList.contains(SchemaChecks.required)) {
            if (stringToValidate == null) {
                return false;
            } else if (stringToValidate.toString().isEmpty()) {
                return false;
            }
        }
        return isValid;
    }

    private static boolean isContain(List<SchemaChecks> checkList, List<String> stringsMustBeContained,
                                     Object stringToValidate, boolean isValid) {
        if (checkList.contains(SchemaChecks.contains)) {
            for (String content : stringsMustBeContained) {
                if (stringToValidate == null || !stringToValidate.toString().contains(content)) {
                    return false;
                }
            }
        }
        return isValid;
    }

    private static boolean isLongEnough(List<SchemaChecks> checkList, int minLength,
                                        Object stringToValidate, boolean isValid) {
        if (checkList.contains(SchemaChecks.minLength)) {
            if (stringToValidate == null || stringToValidate.toString().length() < minLength) {
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
