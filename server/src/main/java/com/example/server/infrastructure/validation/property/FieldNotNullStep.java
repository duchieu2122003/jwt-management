package com.example.server.infrastructure.validation.property;

import com.example.server.infrastructure.constant.Message;
import com.example.server.infrastructure.exception.RestApiException;
import com.example.server.infrastructure.validation.ValidationSteps;

import java.lang.reflect.Field;

/**
 * @author duchieu212
 */
public class FieldNotNullStep implements ValidationSteps {

    @Override
    public void validated(Object record) {
        Class<?> classRecord = record.getClass();
        Field[] fields = classRecord.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(record);
                if ("descriptions".equals(field.getName()) ||
                    "address".equals(field.getName()) ||
                    "idDepartments".equals(field.getName())) {
                    continue;
                }
                if (value == null || (value instanceof String && ((String) value).isEmpty())) {
                    throw new RestApiException(field.getName() + " " + Message.NOT_EMPTY.getMessage());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }
}
