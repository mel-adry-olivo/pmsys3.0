package org.pmsys.main.service;


import com.password4j.Hash;
import com.password4j.Password;
import org.pmsys.main.managers.ServiceManager;
import org.pmsys.main.entities.request.AuthRequest;
import org.pmsys.main.entities.result.AuthResult;
import org.pmsys.main.entities.User;

public class AuthService {

    private final UserService userService;

    public AuthService() {
        this.userService = (UserService) ServiceManager.getService(Services.USER);
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
