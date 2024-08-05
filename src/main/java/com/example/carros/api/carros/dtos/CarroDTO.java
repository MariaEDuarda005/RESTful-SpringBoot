package com.example.carros.api.carros.dtos;

import com.example.carros.api.carros.Carro;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // para ignorar atributos nulos ao gerar o json
public class CarroDTO {
    private Long id;
    private String nome;
    private String tipo;

    public CarroDTO(Carro c){
        this.id = c.getId();
        this.nome = c.getNome();
        this.tipo = c.getTipo();
    }

    public CarroDTO() {
        // Construtor sem argumentos
    }


    public static CarroDTO create(Carro c) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(c, CarroDTO.class);
    }
}
