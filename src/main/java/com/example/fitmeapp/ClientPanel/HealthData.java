package com.example.fitmeapp.ClientPanel;

import com.example.fitmeapp.UserAuthorization.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class HealthData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paramId;
    private ParameterType parameterType;
    private double parameterValue;
    private LocalDateTime createdAt;

    public HealthData(ParameterType parameterType, double parameterValue, User owner) {
        this.parameterType = parameterType;
        this.parameterValue = parameterValue;
        this.owner = owner;
        this.createdAt = LocalDateTime.now();
    }

    public HealthData() {
    }

    @ManyToOne
    @JoinColumn(name="ownerId", nullable=false)
    private User owner;

    public Long getParam_id() {
        return paramId;
    }

    public void setParam_id(Long param_id) {
        this.paramId = param_id;
    }

    public ParameterType getParameterType() {
        return parameterType;
    }

    public void setParameterType(ParameterType parameterType) {
        this.parameterType = parameterType;
    }

    public double getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(double parameterValue) {
        this.parameterValue = parameterValue;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User user) {
        this.owner = user;
    }

    public Long getParamId() {
        return paramId;
    }

    public void setParamId(Long paramId) {
        this.paramId = paramId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
