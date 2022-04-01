package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSchema {
    private final List<SchemasChecks> checkList = new ArrayList<>();

    public final boolean isValid(Object objectToValidate) {
        return this.isPassesTests(objectToValidate, true);
    }

    public abstract boolean isRequired(Object objectToValidate, boolean isValid);

    public abstract boolean isPassesTests(Object stringToValidate, boolean isValid);

    public final List<SchemasChecks> getCheckList() {
        return this.checkList;
    }

}
