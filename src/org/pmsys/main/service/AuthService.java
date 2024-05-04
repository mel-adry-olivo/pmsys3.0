package org.pmsys.main.service;


import com.password4j.Hash;
import com.password4j.Password;
import org.pmsys.main.entity.Result;
import org.pmsys.main.entity.User;

public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public Result signIn(String username, String password) {
        User storedUser = userService.findAccountByUsername(username);

        if (storedUser == null) {
            return Result.USER_NOT_FOUND();
        }

        boolean isValidPassword = Password.check(password, storedUser.getHashedPassword()).withArgon2();
        return isValidPassword ? Result.SUCCESS(storedUser) : Result.WRONG_PASSWORD();
    }

    public Result signUp(String username, String password) {
        // TODO: validate username

        Hash hash = Password.hash(password)
                .addRandomSalt(12)
                .withArgon2();

        User user = new User(username, hash.getResult());
        userService.storeUserData(user);

        return Result.SUCCESS();
    }

    public void signOut() {
        // TODO
    }

    public boolean isLoggedIn() {
        // TODO
        return false;
    }

    public User getCurrentUser() {
        // TODO
        return null;
    }
}
