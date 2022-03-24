package hexlet.code;

import hexlet.code.schemas.StringSchemaChecks;

import java.util.Map;

public final class StringSchemaValidator extends Validator {

    public boolean isValid(Map<StringSchemaChecks, String> checkList, String stringToValidate) {
        for (Map.Entry<StringSchemaChecks, String> entry : checkList.entrySet()) {

            if (entry.getKey().equals(StringSchemaChecks.required)) {

                if (stringToValidate == null) {
                    return false;
                } else if (stringToValidate.length() == 0) {
                    return false;
                }

                return true;
            }

            if (entry.getKey().equals(StringSchemaChecks.contains)) {

                if (stringToValidate.contains(entry.getValue())) {
                    return true;
                }

                return false;

            }

        }

        return true;
    }

}
