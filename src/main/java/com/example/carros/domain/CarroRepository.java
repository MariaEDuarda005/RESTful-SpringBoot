package com.example.carros.domain;

import org.springframework.data.repository.CrudRepository;

// classe que vai se comunicar com o banco de dados
public interface CarroRepository extends CrudRepository<Carro, Long> {

    Iterable<Carro> findByTipo(String tipo);
}
