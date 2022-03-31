package hexlet.code.schemas;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public final class MapSchema extends BaseSchema {
    private int sizeOf;

    public MapSchema sizeof(int size) {
        this.required();
        this.sizeOf = size;
        getCheckList().add(SchemaChecks.sizeof);
        return this;
    }

    @Override
    public boolean isRequired(Object mapToValidate, boolean isValid) {
        if (this.getCheckList().contains(SchemaChecks.required) && !(mapToValidate instanceof Map<?, ?>)) {
            return false;
        }
        return isValid;
    }

    @Override
    public boolean toRunOtherChecks(Object mapToValidate, boolean isValid) {
        isValid = isSizeOf(mapToValidate, isValid);

        return isValid;
    }

    private boolean isSizeOf(Object mapToValidate, boolean isValid) {
        ObjectMapper mapper = new ObjectMapper();
        if (this.getCheckList().contains(SchemaChecks.sizeof)) {
            if (mapper.convertValue(mapToValidate, Map.class).size() != this.sizeOf) {
                return false;
            }
        }
        return isValid;
    }

}
