package com.example.carros;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.domain.dto.CarroDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;

// esse arquivo já é criado por padrão
@SpringBootTest
class CarrosApplicationTests {

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
		Optional<CarroDTO>op = service.getCarroById(id);
		// como eu sei que o carro existe faz um assertTrue
		assertTrue(op.isPresent());

		c = op.get();
		// compara para ver se o objeto que vem do banco de dados possui o mesmo nome que salvou
		assertEquals("Ferrari", c.getNome());
		assertEquals("esportivos", c.getTipo());
		// isso garante que está salvando corretamente e lendo corretamente

		// DELETAR LOGO DEPOIS DO TESTE, BOAS PRATICAS
		service.delete(id);

		// buscar novamente para verificar se deletou
		assertFalse(service.getCarroById(id).isPresent()); // tem que dar false
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

		Optional<CarroDTO> op = service.getCarroById(11L);

		assertTrue(op.isPresent());
		CarroDTO c = op.get();

		assertEquals("Ferrari FF", c.getNome());
	}



}
