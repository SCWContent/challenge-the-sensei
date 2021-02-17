package com.securecodewarrior.convertolong;

public class ConvertToLong {

    public static void main(String[] args) {
        Integer integerValue = 1;
        Long longValue = convertToLong(integerValue);
    }

    public static Long convertToLong(Object value) {
        if (value instanceof Integer) {
            return (Long) value;
        }
        return null;
    }
}
