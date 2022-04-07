package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSchema {
    protected final List<SchemasChecks> checkList = new ArrayList<>();

    public final boolean isValid(Object objectToValidate) {
        boolean isValid = true;

        isValid = this.isRequired(objectToValidate, isValid);

        isValid = this.isPassesOtherTests(objectToValidate, isValid);

        return isValid;
    }

    public abstract boolean isRequired(Object objectToValidate, boolean isValid);

    public abstract boolean isPassesOtherTests(Object stringToValidate, boolean isValid);

}
