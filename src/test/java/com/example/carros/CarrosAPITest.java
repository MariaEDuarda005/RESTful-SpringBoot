package com.example.carros;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.domain.dto.CarroDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarrosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// na SpringBootTest,a classe é carrosApplication, e ativei o atributo webEnvironment, que sobe a parte web do springmvc
// permitindo que faz as requisições http
public class CarrosAPITest {
    // nesse teste vamos testar diretamente a api

    // get carro foi chamado em varios momentos para facilitar os testes
    @Autowired
    protected TestRestTemplate rest; // ele faz as requisições

    @Autowired
    private CarroService service;

    // "/api/v1/carros/1"
    private ResponseEntity<CarroDTO> getCarro(String url) {
        // faz o rest e passa a url e o objeto, tem um response entendy tipo carro DTO
        return rest.getForEntity(url, CarroDTO.class);
    }

    // retorna um ResponseEntity
    private ResponseEntity<List<CarroDTO>> getCarros(String url) {
        // lista de carro, ou carros por tipo
        return rest.withBasicAuth("user","123").exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CarroDTO>>() {
                });
    }


    @Test
    public void testSave() {
        // criou um objeto carro
        Carro carro = new Carro();
        carro.setNome("Porshe");
        carro.setTipo("esportivos");

        // Insert
        //ResponseEntity response = rest.withBasicAuth("admin","123").postForEntity("/api/v1/carros", carro, null);
        // chama o metedo, passa o endereço que precisa fazer o post, o objeto, e a propria api converte o carro para json
        ResponseEntity response = rest.postForEntity("/api/v1/carros", carro, null);
        System.out.println(response);

        // Verifica se criou
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Buscar o objeto
        String location = response.getHeaders().get("location").get(0);
        CarroDTO c = getCarro(location).getBody(); // chama o get carro passando a url

        assertNotNull(c);
        assertEquals("Porshe", c.getNome());
        assertEquals("esportivos", c.getTipo());

        // Deletar o objeto
        rest.withBasicAuth("user","123").delete(location);

        // Verificar se deletou
        assertEquals(HttpStatus.NOT_FOUND, getCarro(location).getStatusCode());
    }

    @Test
    public void testLista() {
        // faz a requisição e pega um get, e compara para ver se tem 30
        List<CarroDTO> carros = getCarros("/api/v1/carros").getBody();
        assertNotNull(carros);
        assertEquals(30, carros.size());
    }

    @Test
    public void testListaPorTipo() {

        assertEquals(10, getCarros("/api/v1/carros/tipo/classicos").getBody().size());
        assertEquals(10, getCarros("/api/v1/carros/tipo/esportivos").getBody().size());
        assertEquals(10, getCarros("/api/v1/carros/tipo/luxo").getBody().size());

        assertEquals(HttpStatus.NO_CONTENT, getCarros("/api/v1/carros/tipo/xxx").getStatusCode()); // esse tipo não existe
    }

    @Test
    public void testGetOk() {
        // buscando o carro 11
        ResponseEntity<CarroDTO> response = getCarro("/api/v1/carros/11");
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        // compara o nome
        CarroDTO c = response.getBody();
        assertEquals("Ferrari FF", c.getNome());
    }

    @Test
    public void testGetNotFound() {
        // passar um id que não existe para retornar um not found
        ResponseEntity response = getCarro("/api/v1/carros/1100");
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
