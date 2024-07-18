package com.example.carros.domain;

import com.example.carros.domain.dto.CarroDTO;
import com.example.carros.api.exception.ObjectNotFoundException; // da classe que foi criada
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired // para o spring injetar esta dependencia
    private CarroRepository rep;

    // Iterable é uma interface que representa uma coleção de elementos sobre a qual você pode percorrer
    public List<CarroDTO> getCarros() {
        List<Carro> carros = rep.findAll();

        List<CarroDTO> list= carros.stream().map(c -> CarroDTO.create(c)).collect(Collectors.toList());

        // mesmo coisa de outro modo diferente
        //        List<CarroDTO> list = new ArrayList<>();
//
//        for (Carro c : carros){
//            list.add(new CarroDTO(c));
//        }
//
        return list;
    }

    public CarroDTO getCarroById(Long id) {
        // esse metodo findbyid já existe no crudrepository então não precisa criar nada no CarroRepository
        Optional<Carro> carro = rep.findById(id);
        // ou o carro existe evai ser convertido para um carro dto, orElse não retorna um optional
        return carro.map(CarroDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado..."));

//        Optional<Carro> carro = rep.findById(id);
//        if (carro.isPresent()){
//            return Optional.of(new CarroDTO(carro.get()));
//        } else {
//            return null;
//        }
    }

    public List<CarroDTO> getCarrosByTipo(String tipo) {
        // c -> new CarroDTO(c) é a mesma coisa que CarroDTO::new
        // c -> CarroDTO.create(c) é a mesma coisa que CarroDTO::create
        return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO insert(Carro carro){
        Assert.isNull(carro.getId(), "Não foi possivel inserir o registro");

        return CarroDTO.create(rep.save(carro));
    }

    // Declara o metodo uptade
    public CarroDTO update(Carro carro, Long id) {
        // verificando se o id é nulo, se o id não for nulo, significa que o registro já existe no banco e não deve ser inserido novamente
        Assert.notNull(id, "Não foi possivel inserir o registro");

        // Busca o carro pelo banco de dados
        Optional<Carro> optional = rep.findById(id);
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
            return CarroDTO.create(db);

        } else {
            return null;
            // o else retorna se o objeto Optional estiver vazio, ou seja, não encontrou no banco
            // throw new RuntimeException("Não foi possivel atualizar o registro");
        }
    }

    public void delete(Long id){
        rep.deleteById(id);
    }

}
