package ru.iteco.fmhandroid.ui.data;

import ru.iteco.fmhandroid.ui.page.BaseClass;

public class TestDataInfo extends BaseClass {
    private static String validLogin = "login2";
    private static String invalidLogin = "login";
    private static String validPassword = "password2";

    private static int maxQuotes = 8;
    private static String category = "Объявление";
    private static String title = "Всем привет";
    private static String date = "25.05.2025";
    private static String time = "16:30";
    private static String description = "хочу обратить ваше внимание";
    private static String link = "https://google.com";
    private static String newDescription = "всем смотреть";

    public static String getValidLogin() {
        return validLogin;
    }

    public static String getValidPassword() {
        return validPassword;
    }

    public static String getInvalidLogin() {
        return invalidLogin;
    }

    public static int getMaxQuotes() {
        return maxQuotes;
    }

    public static String getCategory() {
        return category;
    }

    public static String getTitle() {
        return title;
    }

    public static String getDate() {
        return date;
    }

    public static String getTime() {
        return time;
    }

    public static String getDescription() {
        return description;
    }

    public static String getLink() {
        return link;
    }

    public static String getNewDescription() {
        return newDescription;
    }
}
