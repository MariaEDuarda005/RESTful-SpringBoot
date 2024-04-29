// esse pacote é as entidades que vão ficar dentro dela
package com.example.carros.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

//Quanto mais proximo a classe for da tabela mais facil vai ser a configuração
//@Entity(name = "carros")
@Entity
@Data
// lombok já gera os getter e setter automaticamente
public class Carro {
    // Na classe vai ter um id,nome
    // diferença de auto e identity
    // O AUTO muitas vezes cria uma sequence que não queremos, e o IDENTITY cria a chave primária com a própria notação de "auto_increment" do MySQL.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // sempre incrementando o id
    private Long id;
    private String nome;
    private String tipo;

}
