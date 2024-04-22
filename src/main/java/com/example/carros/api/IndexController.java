package com.example.carros.api;

import org.springframework.web.bind.annotation.*;

// essa anotação transforma a classe em um web server
@RestController

// fala que esse web server vai estar mapeado na raiz da aplicação
@RequestMapping("/")
public class IndexController {

    // não tem endpoints pois herda deste @RequestMapping("/")
    // quando chamar a pagina aparece esta mensagem
    @GetMapping()
    // isso é a mesma coisa que o comando de cima - @RequestMapping(method = {RequestMethod.GET})
    public String get(){
        return "Api dos carros Spring Boot";
    }

    // não é recomendado passar a senha no get, recomendado o post
    // @RequestParam é usada para vincular os parâmetros de uma solicitação HTTP aos parâmetros de uma manipulação de solicitação
    //@PostMapping("/login")
    //public String login(@RequestParam("login") String login, @RequestParam("senha") String senha){
    //    return "login: " + login + " Senha: " + senha;
    //}


}
