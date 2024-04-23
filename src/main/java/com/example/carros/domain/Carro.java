// esse pacote é as entidades que vão ficar dentro dela
package com.example.carros.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//Quanto mais proximo a classe for da tabela mais facil vai ser a configuração
//@Entity(name = "carros")
@Entity
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

    public Carro(){

    }

    // construtor
    public Carro(Long id, String nome){
        this.id = id;
        this.nome = nome;
    }

    // getter and setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
