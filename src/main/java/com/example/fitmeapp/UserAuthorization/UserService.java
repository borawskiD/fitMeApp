package com.example.fitmeapp.UserAuthorization;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    public final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public Optional<UserCredentialsDTO> findCredentialsByEmail(String email){
        System.out.println("--email--");
        System.out.println(email);
        return userRepository.findByEmail(email).map(UserCredentialsDtoMapper::map);
    }
    public boolean registerNewUser(UserRegistrationDto userRegistrationDto, Gender gender){
        if (findCredentialsByEmail(userRegistrationDto.getEmail()).isPresent()) return false; //check if user exists
        if (!userRegistrationDto.getConfirmPassword().equals(userRegistrationDto.getPassword())) return false; //check if passwords match
        String passwordHash = passwordEncoder.encode(userRegistrationDto.getPassword());
        User user = new User(userRegistrationDto.getEmail(), passwordHash, userRegistrationDto.getFirstName(), userRegistrationDto.getLastName(), userRegistrationDto.getWeight(), userRegistrationDto.getHeight(), userRegistrationDto.getBirthDate(), userRegistrationDto.getActivityLevel(), gender, LocalDateTime.now());
        userRepository.save(user);
        return true;
    }

}
