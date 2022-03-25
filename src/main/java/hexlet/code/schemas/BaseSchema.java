package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public class BaseSchema {
    private final List<SchemaChecks> checkList = new ArrayList<>();

    public final void required() {
        getCheckList().add(SchemaChecks.required);
    }

    public final boolean isValid(Object objectToValidate) {
        if (this instanceof NumberSchema) {
            return NumberSchema.isValid(getCheckList(), ((NumberSchema) this).getThresholdValues(), objectToValidate);
        } else if (this instanceof StringSchema) {
            return StringSchema.isValid(getCheckList(), ((StringSchema) this).getStringsMustBeContained(),
                    ((StringSchema) this).getMinLength(), objectToValidate);
        }

        return true;
    }

    public final List<SchemaChecks> getCheckList() {
        return this.checkList;
    }

}
