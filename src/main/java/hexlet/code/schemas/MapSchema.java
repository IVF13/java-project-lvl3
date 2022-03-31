package hexlet.code.schemas;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public final class MapSchema extends BaseSchema {
    private Map<String, BaseSchema> schemasStorage;
    private int sizeOf;

    public MapSchema required() {
        getCheckList().add(SchemasChecks.required);
        return this;
    }

    public MapSchema sizeof(int size) {
        this.required();
        this.sizeOf = size;
        getCheckList().add(SchemasChecks.sizeof);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema> schemas) {
        this.required();
        this.schemasStorage = schemas;
        getCheckList().add(SchemasChecks.shape);
        return this;
    }

    @Override
    public boolean isRequired(Object mapToValidate, boolean isValid) {
        if (this.getCheckList().contains(SchemasChecks.required) && !(mapToValidate instanceof Map<?, ?>)) {
            return false;
        }
        return isValid;
    }

    @Override
    public boolean isPassesOtherTests(Object mapToValidate, boolean isValid) {
        isValid = isSizeOf(mapToValidate, isValid);

        isValid = isMatchTheShape(mapToValidate, isValid);

        return isValid;
    }

    private boolean isSizeOf(Object mapToValidate, boolean isValid) {
        if (this.getCheckList().contains(SchemasChecks.sizeof)) {
            if (convertObjectToMap(mapToValidate).size() != this.sizeOf) {
                return false;
            }
        }
        return isValid;
    }

    private boolean isMatchTheShape(Object mapToValidate, boolean isValid) {
        Map<String, Object> map = convertObjectToMap(mapToValidate);

        if (this.getCheckList().contains(SchemasChecks.shape)) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {

                String key = entry.getKey();
                Object value = entry.getValue();

                if (schemasStorage.containsKey(key)) {
                    isValid = schemasStorage.get(key).isValid(value);
                }

            }
        }
        return isValid;
    }

    private Map<String, Object> convertObjectToMap(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(object, Map.class);
    }

}
