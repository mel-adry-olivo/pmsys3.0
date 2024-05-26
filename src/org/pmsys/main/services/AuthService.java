package org.pmsys.main.services;


import com.password4j.Hash;
import com.password4j.Password;
import org.pmsys.main.managers.ServiceManager;
import org.pmsys.main.entities.request.AuthRequest;
import org.pmsys.main.entities.result.AuthResult;
import org.pmsys.main.entities.User;
import org.pmsys.main.ui.utils.MessageUtils;

public class AuthService {

    private final UserService userService;

    public AuthService() {
        this.userService = (UserService) ServiceManager.getService(Services.USER);
    }

    public AuthResult signIn(AuthRequest request) {

        User storedUser = userService.getUserByUsername(request.username());

        if (storedUser == null) {
            return AuthResult.USER_NOT_FOUND();
        }

        boolean isValidPassword = Password.check(request.password(), storedUser.getHashedPassword()).withArgon2();

        return isValidPassword ? AuthResult.SUCCESS(storedUser) : AuthResult.WRONG_PASSWORD();
    }

    public AuthResult signUp(AuthRequest request) {

        if(!userService.validateUsername(request.username())) {
            return AuthResult.USER_EXISTS();
        }

        Hash hash = Password.hash(request.password())
                .addRandomSalt(12)
                .withArgon2();

        User user = new User(request.username(), hash.getResult());

        userService.saveInFile(user);
        return AuthResult.SUCCESS();
    }
}
