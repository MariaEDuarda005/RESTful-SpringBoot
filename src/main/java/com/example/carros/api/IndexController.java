package com.example.carros.api;

import org.springframework.web.bind.annotation.*;

// essa anotação transforma a classe em um web server
@RestController

// mapeado na raiz da aplicação
@RequestMapping("/")
public class IndexController {

    // não tem endpoints pois herda deste @RequestMapping("/")
    @GetMapping()
    public String get(){
        return "Api dos carros Spring Boot";
    }

}
