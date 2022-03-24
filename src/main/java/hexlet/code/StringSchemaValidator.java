package hexlet.code;

import hexlet.code.schemas.StringSchemaChecks;

import java.util.Map;

public final class StringSchemaValidator extends Validator {

    public boolean isValid(Map<StringSchemaChecks, Object> checkList, String stringToValidate) {
        for (Map.Entry<StringSchemaChecks, Object> entry : checkList.entrySet()) {

            if (entry.getKey().equals(StringSchemaChecks.required)) {

                if (stringToValidate == null) {
                    return false;
                } else if (stringToValidate.length() == 0) {
                    return false;
                }

            }

            if (entry.getKey().equals(StringSchemaChecks.contains)) {

                if (!stringToValidate.contains(entry.getValue().toString())) {
                    return false;
                }

            }

            if(entry.getKey().equals(StringSchemaChecks.minLength)){

                if((int)entry.getValue() > stringToValidate.length()){
                    return false;
                }
                
            }

        }

        return true;
    }

}
