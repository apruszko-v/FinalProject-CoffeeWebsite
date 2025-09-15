package pl.coderslab.finalprojectcoffeewebsite.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.finalprojectcoffeewebsite.brewrecipe.BrewRecipeRepository;
import pl.coderslab.finalprojectcoffeewebsite.review.ReviewRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReviewRepository reviewRepository;
    private final BrewRecipeRepository brewRecipeRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ReviewRepository reviewRepository, BrewRecipeRepository brewRecipeRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.reviewRepository = reviewRepository;
        this.brewRecipeRepository = brewRecipeRepository;
    }

    private UserDTO convertToUserDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }

//    create
    public UserDTO registerUser(UserRegisterDTO userRegisterDTO) {
        if(userRepository.existsByUsername(userRegisterDTO.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if(userRepository.existsByEmail(userRegisterDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setEmail(userRegisterDTO.getEmail());

        User saved = userRepository.save(user);
        return convertToUserDTO(saved);
    }

//    read
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return convertToUserDTO(user);
    }

//
    public UserDTO getCurrentUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return convertToUserDTO(user);
    }

//    update
    public UserDTO updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(userUpdateDTO.getUsername() != null) {
            user.setUsername(userUpdateDTO.getUsername());
        }
        if(userUpdateDTO.getEmail() != null) {
            user.setEmail(userUpdateDTO.getEmail());
        }
        if(userUpdateDTO.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
        }

        User updated = userRepository.save(user);
        return convertToUserDTO(updated);
    }

//    delete
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        reviewRepository.deleteByUserId(id);
        brewRecipeRepository.deleteByUserId(id);
        user.getFavoriteCoffees().clear();
        userRepository.save(user);

        userRepository.delete(user);
    }
}
