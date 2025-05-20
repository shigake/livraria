package com.erick.livraria.util;

public class GoogleBookUtil {
    private GoogleBookUtil() {}

    public static Integer parseYear(String date) {
        if (date == null) return 2000;
        try {
            return Integer.parseInt(date.substring(0, 4));
        } catch (Exception e) {
            return 2000;
        }
    }
}
