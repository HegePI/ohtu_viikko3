package ohtu.authentication;

import ohtu.data_access.UserDao;
import ohtu.domain.User;
import ohtu.util.CreationStatus;

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

    public CreationStatus createUser(String username, String password, String passwordConfirmation) {
        CreationStatus status = new CreationStatus();

        checkIfValidUsername(username, status);
        checkIfValidPassword(password, passwordConfirmation, status);

        if (status.isOk()) {
            userDao.add(new User(username, password));
        }

        return status;
    }

    private void checkIfValidPassword(String password, String passwordConfirmation, CreationStatus status) {
        if (!password.equals(passwordConfirmation)) {
            status.addError("password and password confirmation do not match");
        }
        if (password.length() < 8) {
            status.addError("password should have at least 8 characters");
        }
        boolean containsDigits = false;
        char[] passwordCharArray = password.toCharArray();
        for (char c : passwordCharArray) {
            if (Character.isDigit(c)) {
                containsDigits = true;
            }
        }
        if (!containsDigits) {
            status.addError("password doesn't contain digits");
        }

    }

    private void checkIfValidUsername(String username, CreationStatus status) {
        if (userDao.findByName(username) != null) {
            status.addError("username is already taken");
        }
        if (username.length() < 3) {
            status.addError("username should have at least 3 characters");
        }

        char[] usernameCharArray = username.toCharArray();
        for (char c : usernameCharArray) {
            if (!(c >= 'a' && c <= 'z')) {
                status.addError("username doesn't contain only letters");
            }
        }

    }
}
