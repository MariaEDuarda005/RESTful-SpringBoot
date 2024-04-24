package com.example.carros.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

    public List<Carro> getCarrosByTipo(String tipo) {
        // como ele não existe temos que criar este metodo
        return rep.findByTipo(tipo);
    }


//    public Carro save(Carro carro) {
//        return rep.save(carro);
//    }

    public Carro insert(Carro carro){
        Assert.isNull(carro.getId(), "Não foi possivel inserir o registro");

        return rep.save(carro);
    }

    // Declara o metodo uptade
    public Carro update(Carro carro, Long id) {
        // verificando se o id é nulo, se o id não for nulo, significa que o registro já existe no banco e não deve ser inserido novamente
        Assert.isNull(carro.getId(), "Não foi possivel inserir o registro");

        // Busca o carro pelo banco de dados
        Optional<Carro> optional = getCarroById(id);
        // verifica se o objeto Optional contém um objeto Carro
        if (optional.isPresent()){
            // Chama o metodo get e o armazena em uma variavel
            Carro db = optional.get();
            //Copiar as propriedades
            // Aqui atualiza o nome e o tipo
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro atualizado com o id " + db.getId());

            // Atualiza o carro, e salva as alterações feitas no objeto db
            rep.save(db);

            // retorna o objeto db se a atualização for bem sucedida
            return db;
        } else {
            // o else retorna se o objeto Optional estiver vazio, ou seja, não encontrou no banco
            throw new RuntimeException("Não foi possivel atualizar o registro");
        }
    }

    public void delete(Long id) {
        // Optional indica que o carro pode existir ou não
        Optional<Carro> carro = getCarroById(id);
        if (carro.isPresent()) {
            // este metodo deleteById existe na classe crud repositorio que já esta pronto
            rep.deleteById(id);
        }
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
