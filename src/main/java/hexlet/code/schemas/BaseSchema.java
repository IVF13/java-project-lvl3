package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSchema {
    private final List<SchemaChecks> checkList = new ArrayList<>();

    public final void required() {
        getCheckList().add(SchemaChecks.required);
    }

    public final boolean isValid(Object objectToValidate) {
        return this.toRunChecks(objectToValidate);
    }

    public abstract boolean toRunChecks(Object stringToValidate);

    public final List<SchemaChecks> getCheckList() {
        return this.checkList;
    }

}
