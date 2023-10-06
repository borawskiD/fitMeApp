package com.example.fitmeapp.UserAuthorization;

import com.example.fitmeapp.ClientPanel.HealthData;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "application_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String email;
    private String password;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Gender gender;
    private LocalDateTime createdAt;
    @OneToMany(mappedBy="owner", fetch=FetchType.EAGER)
    private List<HealthData> parameters;

    public User() {
    }

    public User(String email, String password, String firstName, String lastName, LocalDate birthDate, Gender gender, LocalDateTime createdAt) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.createdAt = createdAt;
    }

    public void setUserId(Long id) {
        this.userId = id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<HealthData> getParameters() {
        return parameters;
    }

    public void setParameters(List<HealthData> parameters) {
        this.parameters = parameters;
    }
}
