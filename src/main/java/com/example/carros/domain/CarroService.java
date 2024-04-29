package com.example.carros.domain;

import com.example.carros.domain.dto.CarroDTO;
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

    public Optional<CarroDTO> getCarroById(Long id) {
        // esse metodo findbyid já existe no crudrepository então não precisa criar nada no CarroRepository
        return rep.findById(id).map(CarroDTO::create);

//        Optional<Carro> carro = rep.findById(id);
//        if (carro.isPresent()){
//            return Optional.of(new CarroDTO(carro.get()));
//        } else {
//            return null;
//        }
    }

    public List<CarroDTO> getCarrosByTipo(String tipo) {
        // como ele não existe temos que criar este metodo
        // c -> new CarroDTO(c) é a mesma coisa que CarroDTO::new
        // c -> CarroDTO.create(c) é a mesma coisa que CarroDTO::create
        return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
    }


//    public Carro save(Carro carro) {
//        return rep.save(carro);
//    }

    public Carro insert(Carro carro){
        Assert.isNull(carro.getId(), "Não foi possivel inserir o registro");

        return rep.save(carro);
    }

    // Declara o metodo uptade
    public CarroDTO update(Carro carro, Long id) {
        // verificando se o id é nulo, se o id não for nulo, significa que o registro já existe no banco e não deve ser inserido novamente
        Assert.isNull(carro.getId(), "Não foi possivel inserir o registro");

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

    public void delete(Long id) {
        if (getCarroById(id).isPresent()) {
            // este metodo deleteById existe na classe crud repositorio que já esta pronto
            rep.deleteById(id);
        }
    }

}
