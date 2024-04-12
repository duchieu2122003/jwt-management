package com.example.server.infrastructure.constant;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * @author duchieu212
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class EntityProperties {

    private static final byte LENGTH_ID = 36;
    public static final short LENGTH_NAME = 255;
    public static final byte LENGTH_CODE = 100;
    public static final byte LENGTH_PASSWORD = 100;
    public static final short LENGTH_DESCRIPTIONS = 1000;
    public static final byte LENGTH_EMAIL = 50;
    public static final short LENGTH_DESCRIPTIONS_SHORT = 500;

}
