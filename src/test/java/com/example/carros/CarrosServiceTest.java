package com.example.carros;

import com.example.carros.api.exception.ObjectNotFoundException;
import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.domain.dto.CarroDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;

// esse arquivo já é criado por padrão
@SpringBootTest
class CarrosServiceTest {
	// aqui nos estamos testando os metodos da classe carro service

	// Como eu quero testar a classe CarroService eu vou declarar
	@Autowired // used for automatic dependency injection. Injetar esse objeto
	private CarroService service;
	@Test
	public void test1() {
		Carro carro = new Carro(); // declarar o objeto
		carro.setNome("Ferrari");
		carro.setTipo("esportivos");

		CarroDTO c = service.insert(carro);

		assertNotNull(c);

		Long id = c.getId();
		assertNotNull(id);

		// Buscar o objeto
		c = service.getCarroById(id);
		assertNotNull(c);

		// compara para ver se o objeto que vem do banco de dados possui o mesmo nome que salvou
		assertEquals("Ferrari", c.getNome());
		assertEquals("esportivos", c.getTipo());
		// isso garante que está salvando corretamente e lendo corretamente

		// DELETAR LOGO DEPOIS DO TESTE, BOAS PRATICAS
		service.delete(id);

		// buscar novamente para verificar se deletou
		try {
			assertNull(service.getCarroById(id));
			fail("O carro não foi excluido");
		} catch (ObjectNotFoundException e){
			//ok. Se cair no catch está certo
		}
	}

	@Test
	public void testLista() {

		List<CarroDTO> carros = service.getCarros();

		assertEquals(30, carros.size());
	}

	@Test
	public void testListaPorTipo() {
		assertEquals(10, service.getCarrosByTipo("classicos").size());
		assertEquals(10, service.getCarrosByTipo("esportivos").size());
		assertEquals(10, service.getCarrosByTipo("luxo").size());

		assertEquals(0, service.getCarrosByTipo("x").size());
	}

	@Test
	public void testGet() {

		CarroDTO c = service.getCarroById(11L);

		// não pode ser nulo
		assertNotNull(c);

		assertEquals("Ferrari FF", c.getNome());
	}



}
