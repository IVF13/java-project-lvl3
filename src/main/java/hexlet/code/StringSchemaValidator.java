package hexlet.code;

import hexlet.code.schemas.StringSchemaChecks;

import java.util.Map;

import static java.lang.Integer.parseInt;

public final class StringSchemaValidator extends Validator {

    public boolean isValid(Map<StringSchemaChecks, String> checkList, String stringToValidate) {
        for (Map.Entry<StringSchemaChecks, String> entry : checkList.entrySet()) {

            switch (entry.getKey()) {
                case required:
                    if (stringToValidate == null) {
                        return false;
                    } else if (stringToValidate.length() == 0) {
                        return false;
                    }
                case contains:
                    if (!stringToValidate.contains(entry.getValue().toString())) {
                        return false;
                    }
                case minLength:
                    if (entry.getValue().matches("[-+]?\\d+")) {
                        if (parseInt(entry.getValue()) > stringToValidate.length()) {
                            return false;
                        }
                    }
                default:
            }

        }

        return true;
    }

}
