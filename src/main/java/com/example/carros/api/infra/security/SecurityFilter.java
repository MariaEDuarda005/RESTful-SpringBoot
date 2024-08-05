package com.example.carros.api.infra.security;

import com.example.carros.api.users.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        if (token != null) {
            var login = tokenService.validationToken(token);
            if (login != null) {
                System.out.println("Login recuperado do token: " + login);
                UserDetails user = userRepository.findByLogin(login);
                if (user != null) {
                    System.out.println("Usuário encontrado: " + user.getUsername());

                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(authentication);

                } else {
                    System.out.println("Usuário não encontrado para o login: " + login);
                }
            } else {
                System.out.println("Token inválido ou expirado.");
            }
        } else {
            System.out.println("Token não encontrado na requisição.");
        }

        filterChain.doFilter(request, response); // chamando o proximo filtro
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", ""); // sempre que faz essa autenticação tem que usar o Bearer, então vamos substituir pelo espaço vazio

    }
}
