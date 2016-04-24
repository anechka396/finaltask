package by.epam.likeit.service;

/**
 * Created by Пользователь on 24.04.2016.
 */
public class ValidateService {
    public static boolean validateLogin(String login){
        if(login.isEmpty() || login.length() <= 3 || login.length() >= 30){
            return false;
        }

        return true;
    }


    public static boolean validatePassword(String password){
        if(password.isEmpty() || password.length() <= 3 || password.length() >= 30){
            return false;
        }

        return true;
    }
}
