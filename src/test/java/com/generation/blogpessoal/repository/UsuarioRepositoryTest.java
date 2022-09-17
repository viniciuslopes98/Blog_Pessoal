package com.generation.blogpessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogpessoal.model.Usuario;

// indica que a classe UsuarioRepositoryTest é uma classe de test. webEnvironment = rodará em uma porta aleatória desde que não esteja sendo usada.
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

//indica que o teste a ser feito vai ser um teste unitário(por classe).
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	//inserindo usuarios no meu banco de dados h2 para que sejam feitos os testes de funções.
	@BeforeAll
	void start() {
		
		usuarioRepository.deleteAll();
		
		usuarioRepository.save(new Usuario(0L, "João da Silva","joao@email.com.br","134265278", "https://i.imgur.com/FETvc20.jpg"));
		
		usuarioRepository.save(new Usuario(0L, "Manuela da Silva","manuela@email.com.br","134265278", "https://i.imgur.com/NtyGneo.jpg"));
		
		usuarioRepository.save(new Usuario(0L, "Adriana da Silva","adriana@email.com.br","134265278", "https://i.imgur.com/mB3VM2N.jpg"));
		
		usuarioRepository.save(new Usuario(0L, "Paulo Antunes","paulo@email.com.br","134265278", "https://i.imgur.com/JR7kUFU.jpg"));
		
	}

	@Test
	@DisplayName("Retornar 1 usuario")
	public void deveRetornarUmUsuario() {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("joao@email.com.br");
		assertTrue(usuario.get().getUsuario().equals("joao@email.com.br"));
		
	}
	
	@Test
	@DisplayName("Retornar 3 usuarios")
	public void deveRetornarTresUsuarios() {
		
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("João da Silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Manuela da Silva"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Adriana da Silva"));
	}

	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
		
	}


}
