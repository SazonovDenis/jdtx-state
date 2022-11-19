package com.jdtx.ut;

import java.text.*;
import java.util.*;

/**
 * Десериализация/типизация примивных типов.
 */
public class UtData {

    public static long longValueOf(Object value) {
        return longValueOf(value, 0L);
    }

    public static int intValueOf(Object value) {
        return intValueOf(value, 0);
    }

    public static double doubleValueOf(Object value) {
        return doubleValueOf(value, 0.0);
    }

    public static boolean booleanValueOf(Object value) {
        return booleanValueOf(value, false);
    }

    public static String stringValueOf(Object value) {
        return stringValueOf(value, null);
    }

    public static String stringValueOf(Object value, String valueIfNull) {
        String valueString;
        if (value == null) {
            valueString = valueIfNull;
        } else {
            valueString = value.toString();
            if (valueString.length() == 0) {
                valueString = valueIfNull;
            } else if (valueString.compareToIgnoreCase("null") == 0) {
                valueString = valueIfNull;
            }
        }
        return valueString;
    }

    public static Long longValueOf(Object value, Long valueIfNull) {
        Long valueLong;
        if (value == null) {
            valueLong = valueIfNull;
        } else if (value instanceof Long) {
            valueLong = (Long) value;
        } else if (value instanceof Integer) {
            valueLong = Long.valueOf((Integer) value);
        } else {
            String valueString = value.toString();
            if (valueString.length() == 0) {
                valueLong = valueIfNull;
            } else if (valueString.compareToIgnoreCase("null") == 0) {
                valueLong = valueIfNull;
            } else {
                valueLong = Long.valueOf(valueString);
            }
        }
        return valueLong;
    }

    public static Integer intValueOf(Object value, Integer valueIfNull) {
        Integer valueInteger;
        if (value == null) {
            valueInteger = valueIfNull;
        } else if (value instanceof Integer) {
            valueInteger = (Integer) value;
        } else if (value instanceof Long) {
            // В value может оказаться long, но на самом деле - не более чем int, например в ReplicaInfo.fromJSONObject, в infoJson.get("replicaType") оказывается Long
            valueInteger = Integer.valueOf(value.toString());
        } else {
            String valueString = value.toString();
            if (valueString.length() == 0) {
                valueInteger = valueIfNull;
            } else if (valueString.compareToIgnoreCase("null") == 0) {
                valueInteger = valueIfNull;
            } else {
                valueInteger = Integer.valueOf(valueString);
            }
        }
        return valueInteger;
    }

    public static Double doubleValueOf(Object value, Double valueIfNull) {
        Double valueDouble;
        if (value == null) {
            valueDouble = valueIfNull;
        } else if (value instanceof Double) {
            valueDouble = (Double) value;
        } else if (value instanceof Integer) {
            valueDouble = Double.valueOf(value.toString());
        } else if (value instanceof Long) {
            valueDouble = Double.valueOf(value.toString());
        } else {
            String valueString = value.toString();
            if (valueString.length() == 0) {
                valueDouble = valueIfNull;
            } else if (valueString.compareToIgnoreCase("null") == 0) {
                valueDouble = valueIfNull;
            } else {
                valueDouble = Double.valueOf(valueString);
            }
        }
        return valueDouble;
    }

    public static Boolean booleanValueOf(Object value, boolean valueIfNull) {
        Boolean valueBoolean;
        if (value == null) {
            valueBoolean = valueIfNull;
        } else if (value instanceof Boolean) {
            valueBoolean = (Boolean) value;
        } else {
            String valueString = value.toString();
            if (valueString.length() == 0) {
                valueBoolean = valueIfNull;
            } else if (valueString.compareToIgnoreCase("null") == 0) {
                valueBoolean = valueIfNull;
            } else {
                valueBoolean = Boolean.valueOf(valueString);
            }
        }
        return valueBoolean;
    }

    public static Date dateTimeValueOf(Object value) {
        Date valueDateTime;

        if (value == null) {
            valueDateTime = null;
        } else if (value instanceof Date) {
            valueDateTime = (Date) value;
        } else {
            String valueStr = value.toString();
            if (valueStr.compareToIgnoreCase("null") == 0) {
                valueDateTime = null;
            } else if (valueStr.length() == 0) {
                valueDateTime = null;
            } else if (valueStr.length() == 10) {
                // 2015-10-09
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    valueDateTime = format.parse(valueStr);
                } catch (Exception e) {
                    e.printStackTrace();
                    valueDateTime = null;
                }
            } else {
                // 2015-04-01T21:54:05.000+07:00
                DateFormat format = new SimpleDateFormat();
                try {
                    valueDateTime = format.parse(valueStr);
                } catch (Exception e) {
                    e.printStackTrace();
                    valueDateTime = null;
                }
            }
        }

        return valueDateTime;
    }

    public static boolean equals(String s1, String s2) {
        if (s1 == null && s2 != null) {
            return false;
        }
        if (s1 != null && s2 == null) {
            return false;
        }
        if (s1 == null && s2 == null) {
            return true;
        }

        return s1.equalsIgnoreCase(s2);
    }
}
