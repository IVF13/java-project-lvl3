package hexlet.code;

import hexlet.code.schemas.StringSchemaChecks;

import java.util.List;

public final class StringSchemaValidator extends Validator {

    public boolean isValid(List<StringSchemaChecks> checkList, List<String> stringsMustBeContained,
                           int minLength, String stringToValidate) {
        boolean isValid = true;

        isValid = isRequired(checkList, stringToValidate, isValid);

        isValid = isContain(checkList, stringsMustBeContained, stringToValidate, isValid);

        isValid = isLongEnough(checkList, minLength, stringToValidate, isValid);

        return isValid;
    }

    private boolean isRequired(List<StringSchemaChecks> checkList, String stringToValidate, boolean isValid) {
        if (checkList.contains(StringSchemaChecks.required)) {
            if (stringToValidate == null) {
                return false;
            } else if (stringToValidate.isEmpty()) {
                return false;
            }
        }
        return isValid;
    }

    private boolean isContain(List<StringSchemaChecks> checkList, List<String> stringsMustBeContained,
                              String stringToValidate, boolean isValid) {
        if (checkList.contains(StringSchemaChecks.contains)) {
            for (String content : stringsMustBeContained) {
                if (!stringToValidate.contains(content)) {
                    return false;
                }
            }
        }
        return isValid;
    }

    private boolean isLongEnough(List<StringSchemaChecks> checkList, int minLength,
                                 String stringToValidate, boolean isValid) {
        if (checkList.contains(StringSchemaChecks.minLength)) {
            if (stringToValidate.length() < minLength) {
                return false;
            }
        }
        return isValid;
    }

}
