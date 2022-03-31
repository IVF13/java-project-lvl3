package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSchema {
    private final List<SchemasChecks> checkList = new ArrayList<>();

    public final BaseSchema required() {
        getCheckList().add(SchemasChecks.required);
        return this;
    }

    public final boolean isValid(Object objectToValidate) {
        boolean isValid = true;

        if (!checkList.isEmpty()) {
            isValid = this.isRequired(objectToValidate, isValid);

            if (!isValid) {
                return isValid;
            }

            isValid = this.toRunOtherChecks(objectToValidate, isValid);
        }

        return isValid;
    }

    public abstract boolean isRequired(Object objectToValidate, boolean isValid);

    public abstract boolean toRunOtherChecks(Object stringToValidate, boolean isValid);

    public final List<SchemasChecks> getCheckList() {
        return this.checkList;
    }

}
