package com.qualityunit.util;

import java.util.regex.Pattern;

public class DataValidator {
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("^\\d+(\\.\\d+)*$");
    private static final Pattern DATE_PATTERN = Pattern.compile("^(0?[1-9]|[1|2]\\d"
            + "|3[0|1])[./-](0?[1-9]|1[0-2])[./-](\\d{4}|\\d{2})$");

    private DataValidator() {
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return NUMERIC_PATTERN.matcher(strNum).matches();
    }

    public static boolean isDate(String strDate) {
        if (strDate == null) {
            return false;
        }
        return DATE_PATTERN.matcher(strDate).matches();
    }
}
