package tech.harry.online_book_strore.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tech.harry.online_book_strore.enums.Role;
import tech.harry.online_book_strore.dtos.UserRegistrationRequest;
import tech.harry.online_book_strore.dtos.UserRegistrationResponse;
import tech.harry.online_book_strore.entities.User;
import tech.harry.online_book_strore.exceptions.UserRegistrationException;
import tech.harry.online_book_strore.repositories.UserRepository;

import java.util.Optional;

@Service @RequiredArgsConstructor
public class UserService {

//    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    //    @Transactional
    public UserRegistrationResponse registerUser(UserRegistrationRequest userRegistrationRequest) throws UserRegistrationException {
//        logger.info("Attempting to register user with email: {}", userRegistrationRequest.getEmail());

        // Check if the email is already in use
        if (userRepository.existsByEmail(userRegistrationRequest.getEmail())) {
//            logger.error("Email already in use: {}", userRegistrationRequest.getEmail());
            throw new UserRegistrationException("Email is already in use.");
        }
        // Convert DTO to entity and set additional properties
        User user = modelMapper.map(userRegistrationRequest, User.class);
        user.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
        user.setRole(Role.USER_ROLE);

        try {
            User savedUser = userRepository.save(user);
//            logger.info("User registered successfully with email: {}", savedUser.getEmail());
            return modelMapper.map(savedUser,UserRegistrationResponse.class);
        } catch (Exception e) {
//            logger.error("Failed to register user: {}", e.getMessage(), e);
            throw new UserRegistrationException("Failed to register user. Please try again.");
        }
    }
    public  UserRegistrationResponse findById(Integer userId){
        User user=userRepository.findById(userId).orElse(null);
        if (user!=null){
            return modelMapper.map(user,UserRegistrationResponse.class);
        }
        return null;

    }

    public boolean deleteUserById(Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return true;
        }
        return false;
    }
     public UserRegistrationResponse registerAdmin(UserRegistrationRequest userRegistrationRequest) throws UserRegistrationException {
//        logger.info("Attempting to register user with email: {}", userRegistrationRequest.getEmail());

        // Check if the email is already in use
        if (userRepository.existsByEmail(userRegistrationRequest.getEmail())) {
//            logger.error("Email already in use: {}", userRegistrationRequest.getEmail());
            throw new UserRegistrationException("Email is already in use.");
        }
        // Convert DTO to entity and set additional properties
        User user = modelMapper.map(userRegistrationRequest, User.class);
        user.setPassword(passwordEncoder.encode(userRegistrationRequest.getPassword()));
        user.setRole(Role.ADMIN_ROLE);

        try {
            User savedUser = userRepository.save(user);
//            logger.info("User registered successfully with email: {}", savedUser.getEmail());
            return modelMapper.map(savedUser,UserRegistrationResponse.class);
        } catch (Exception e) {
//            logger.error("Failed to register user: {}", e.getMessage(), e);
            throw new UserRegistrationException("Failed to register user. Please try again.");
        }
    }
}
