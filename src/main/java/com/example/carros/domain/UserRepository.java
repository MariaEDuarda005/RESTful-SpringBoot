package com.example.carros.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {

    // no userDetailsServiceImpl, buscamos pelo login, então tem que criar uma função que busca pelo login
    UserDetails findByLogin(String login);
}
