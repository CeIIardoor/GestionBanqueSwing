package Validator;

public class FormValidator {

    public static boolean validateEmail(String email) {
        return email.matches("[A-Za-z0-9_-]{3,}@[A-Za-z]{2,}\\.[A-Za-z]{2,4}");
    }

    public static boolean validatePassword(String password) {
        return password.matches("[A-Za-z0-9_-]{6,}");
    }

}