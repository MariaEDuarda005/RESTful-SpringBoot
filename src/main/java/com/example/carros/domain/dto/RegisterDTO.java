package com.example.carros.domain.dto;

import com.example.carros.domain.UserRole;

public record RegisterDTO(String nome, String login, String senha, UserRole role) {
}
