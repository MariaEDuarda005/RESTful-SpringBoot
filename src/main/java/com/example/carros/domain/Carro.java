// esse pacote é as entidades que vão ficar dentro dela
package com.example.carros.domain;

public class Carro {
    // Na classe vai ter um id,nome
    private Long id;
    private String nome;

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
}
