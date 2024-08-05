package com.example.carros.api.users.dtos;

import com.example.carros.api.users.UserRole;

public record RegisterDTO(String nome, String login, String senha, UserRole role) {
}
