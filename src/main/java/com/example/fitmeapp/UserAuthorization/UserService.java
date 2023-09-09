package com.example.fitmeapp.UserAuthorization;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Optional<UserCredentialsDTO> findCredentialsByEmail(String email){
        System.out.println("--email--");
        System.out.println(email);
        return userRepository.findByEmail(email).map(UserCredentialsDtoMapper::map);
    }

}
