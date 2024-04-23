package com.example.carros.api;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/api/v1/carros")
public class CarrosController {
//    private CarroService service = new CarroService();

    //marca um ponto de injeção de dependência. @Autowired, faz a ligação de um bean que é apropriado para esse ponto de injeção.
    @Autowired
    private CarroService service;

    @GetMapping()
    public Iterable<Carro> get() {
        return service.getCarros();
    }

    @GetMapping("/{id}")
    public Optional<Carro> get(@PathVariable("id") Long id ) {
        return service.getCarroById(id);
    }

    @GetMapping("/tipo/{tipo}")
    public Iterable<Carro> getCarrosByTipo(@PathVariable("tipo") String tipo ) {
        return service.getCarrosByTipo(tipo);
    }

    @PostMapping
    public String post(@RequestBody Carro carro){
        Carro c = service.save(carro);
        return "Carro salvo com sucesso: " + c.getId();
    }

    // começar agora
//    @PutMapping
//    public String put(@PathVariable("id") Long id, @RequestBody Carro carro){
//        Carro c = service.update(carro, id);
//        return "Carro atualizado com sucesso: " + c.getId();
//    }
}
