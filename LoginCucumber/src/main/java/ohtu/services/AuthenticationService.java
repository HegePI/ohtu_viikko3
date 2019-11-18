package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password
        if (invalidUsername(username) || invalidPassword(password)) {
            return true;
        }

        return false;
    }

    private boolean invalidPassword(String password) {
        if (password.length() < 8) {
            return true;
        }

        char[] pswdChar = password.toCharArray();
        boolean containsDigits = false;
        for (char c : pswdChar) {
            if (Character.isDigit(c)) {
                containsDigits = true;
            }
        }
        if (!containsDigits) {
            return true;
        }
        return false;
    }

    private boolean invalidUsername(String username) {
        if (username.length() < 3) {
            return true;
        }
        char[] nameChar = username.toCharArray();
        for (char c : nameChar) {
            if (!(c >= 'a' && c <= 'z')) {
                return true;
            }
        }
        return false;
    }
}
