package repositories;

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
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

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

}
