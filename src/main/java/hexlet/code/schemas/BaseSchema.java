package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSchema {
    private final List<SchemaChecks> checkList = new ArrayList<>();

    public final void required() {
        getCheckList().add(SchemaChecks.required);
    }

    public final boolean isValid(Object objectToValidate) {
        boolean isValid = true;

        isValid = this.isRequired(objectToValidate, isValid);

        if (!isValid) {
            return isValid;
        }

        isValid = this.toRunOtherChecks(objectToValidate, isValid);

        return isValid;
    }

    public abstract boolean isRequired(Object objectToValidate, boolean isValid);

    public abstract boolean toRunOtherChecks(Object stringToValidate, boolean isValid);

    public final List<SchemaChecks> getCheckList() {
        return this.checkList;
    }

}
