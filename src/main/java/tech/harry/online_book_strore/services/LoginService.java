package tech.harry.online_book_strore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.harry.online_book_strore.dtos.UserLoginRequest;
import tech.harry.online_book_strore.entities.User;
import tech.harry.online_book_strore.repositories.UserRepository;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean authenticate(UserLoginRequest userLoginRequest) {
        User user = userRepository.findByEmail(userLoginRequest.getEmail());
        if (user != null && passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
            return true;
        }
        return false;
    }
}
