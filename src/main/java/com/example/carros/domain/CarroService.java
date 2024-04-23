package com.example.carros.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired // para o spring injetar esta dependencia
    private CarroRepository rep;

    // Iterable é uma interface que representa uma coleção de elementos sobre a qual você pode percorrer
    public Iterable<Carro> getCarros() {
        return rep.findAll();
    }

    public Optional<Carro> getCarroById(Long id) {
        // esse metodo findbyid já existe no crudrepository então não precisa criar nada no CarroRepository
        return rep.findById(id);
    }

    public Iterable<Carro> getCarrosByTipo(String tipo) {
        // como ele não existe temos que criar este metodo
        return rep.findByTipo(tipo);
    }


    public Carro save(Carro carro) {
        return rep.save(carro);
    }


    public List<Carro> getCarrosFake() {
        List<Carro> carros = new ArrayList<>();

        // l é de long no java
        carros.add(new Carro(1L,"Fusca"));
        carros.add(new Carro(2L,"Brasilia"));
        carros.add(new Carro(3L,"Chevette"));

        return carros;
    }


}
