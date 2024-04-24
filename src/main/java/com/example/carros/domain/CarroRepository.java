package com.example.carros.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

// classe que vai se comunicar com o banco de dados
public interface CarroRepository extends CrudRepository<Carro, Long> {

    //Iterable<Carro> findByTipo(String tipo);
    List<Carro> findByTipo(String tipo);
}
