package com.example.fitmeapp.UserAuthorization;

import com.example.fitmeapp.ClientPanel.HealthData;
import com.example.fitmeapp.ClientPanel.HealthDataRepository;
import com.example.fitmeapp.ClientPanel.ParameterType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    public final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final HealthDataRepository healthDataRepository;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, HealthDataRepository healthDataRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.healthDataRepository = healthDataRepository;
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
        User user = new User(userRegistrationDto.getEmail(), passwordHash, userRegistrationDto.getFirstName(), userRegistrationDto.getLastName(), userRegistrationDto.getBirthDate(), gender, LocalDateTime.now());
        userRepository.save(user);
        List<HealthData> userData = new LinkedList<>();
        userData.add(new HealthData(ParameterType.WEIGHT, userRegistrationDto.getWeight(), user));
        userData.add(new HealthData(ParameterType.HEIGHT, userRegistrationDto.getHeight(), user));
        userData.add(new HealthData(ParameterType.ACTIVITY_LEVEL, userRegistrationDto.getActivityLevel(), user));
        healthDataRepository.saveAll(userData);
        return true;
    }

}
