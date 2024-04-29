package com.example.carros.api;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<CarroDTO>> get() {
        //return service.getCarros();
        return new ResponseEntity<>(service.getCarros(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id ) {
        //return service.getCarroById(id);
        Optional<CarroDTO> carro = service.getCarroById(id);

        return carro
                .map(c -> ResponseEntity.ok(c))
                .orElse(ResponseEntity.notFound().build());

        // outros modos de fazer

        // utilizando o if ternario
//        return carro.isPresent() ?
//                ResponseEntity.ok(carro.get()) :
//                ResponseEntity.notFound().build();

//        if (carro.isPresent()){
//            Carro c = carro.get();
//            return ResponseEntity.ok(c);
//        } else {
//            //.build() é um método utilizado principalmente para construir e configurar uma aplicação
//            return ResponseEntity.notFound().build();
//        }
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<CarroDTO>> getCarrosByTipo(@PathVariable("tipo") String tipo ) {
        List<CarroDTO> carros = service.getCarrosByTipo(tipo);

        // se a lista estiver vazia retorna um noContent
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @PostMapping
    public String post(@RequestBody Carro carro){
        Carro c = service.insert(carro);
        return "Carro salvo com sucesso: " + c.getId();
    }

    @PutMapping("/{id}")
    public String put(@PathVariable("id") Long id, @RequestBody Carro carro){
        CarroDTO c = service.update(carro, id);
        return "Carro atualizado com sucesso: " + c.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){

        service.delete(id);

        return "Carro deletado com sucesso";
    }
}
