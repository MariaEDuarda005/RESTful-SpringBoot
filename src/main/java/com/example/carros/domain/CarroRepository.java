package com.example.carros.domain;

// filho do crud repositorio
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

// classe que vai se comunicar com o banco de dados
public interface CarroRepository extends JpaRepository<Carro, Long> {

    //Iterable<Carro> findByTipo(String tipo);
    List<Carro> findByTipo(String tipo);
}
