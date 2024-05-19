package org.pmsys.main.service;


import com.password4j.Hash;
import com.password4j.Password;
import org.pmsys.main.model.request.AuthRequest;
import org.pmsys.main.model.result.AuthResult;
import org.pmsys.main.model.User;

public class AuthService {

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public AuthResult signIn(AuthRequest request) {
        User storedUser = userService.findAccountByUsername(request.getUsername());

        if (storedUser == null) {
            return AuthResult.USER_NOT_FOUND();
        }

        boolean isValidPassword = Password.check(request.getPassword(), storedUser.getHashedPassword()).withArgon2();
        return isValidPassword ? AuthResult.SUCCESS(storedUser) : AuthResult.WRONG_PASSWORD();
    }

    public AuthResult signUp(AuthRequest request) {
        // TODO: validate username

        Hash hash = Password.hash(request.getPassword())
                .addRandomSalt(12)
                .withArgon2();

        User user = new User(request.getUsername(), hash.getResult());
        userService.storeUserData(user);

        return AuthResult.SUCCESS();
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
