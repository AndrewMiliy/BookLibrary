package repositories;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    public static boolean isPasswordValid(String password) {
        // Минимальная длина пароля
        int minLength = 8;

        // Регулярное выражение для валидации пароля
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{" + minLength + ",}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();

    }
    public static boolean validateEmail(String email) {
        // Регулярное выражение для валидации email-адреса
        String regex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean validateName (String name) {
        if (name.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean validateDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1; // Month is 0-based in Calendar, so add 1.

        if (day == 0 || month == 0) {
            return false;
        }

        // You can add additional validation for the year here if needed.
        return true;
    }

}
