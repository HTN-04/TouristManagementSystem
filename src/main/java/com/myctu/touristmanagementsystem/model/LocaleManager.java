package com.myctu.touristmanagementsystem.model;

import java.util.Locale;

public class LocaleManager {
    private static Locale currentLocale = new Locale("vi", "VN");  // Ngôn ngữ mặc định (Tiếng Việt)

    public static Locale getCurrentLocale() {
        return currentLocale;
    }

    public static void setCurrentLocale(Locale locale) {
        currentLocale = locale;
        Locale.setDefault(locale); // Đặt ngôn ngữ mặc định
    }
}