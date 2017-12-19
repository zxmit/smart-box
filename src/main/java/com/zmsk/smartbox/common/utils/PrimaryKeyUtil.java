package com.zmsk.smartbox.common.utils;

import java.util.UUID;

public class PrimaryKeyUtil {

    public static String generateUniqueKey() {
        return UUID.randomUUID().toString();
    }
}
