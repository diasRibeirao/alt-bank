package com.altbank.blackjack.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.altbank.blackjack.domain.Jogador;
import com.altbank.blackjack.domain.Jogo;
import com.altbank.blackjack.service.exceptions.BlackjackException;
import com.altbank.blackjack.service.exceptions.ObjectNotFoundException;

public class JogoRepositoryTest {

	@InjectMocks
	private JogoRepository jogoRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testBuscarPeloId() {
		Jogo jogo = jogoRepository.criar();
		assertEquals(jogo, jogoRepository.buscarPeloId(jogo.getId()));
	}

	@Test
	public void testBuscarPeloIdJogoNaoEncontrado() {
		assertThrows(ObjectNotFoundException.class, () -> {
			jogoRepository.buscarPeloId(2);
		});
	}

	@Test
	public void testCriar() {
		Jogo jogo = jogoRepository.criar();
		assertTrue(jogo.getId().intValue() > 0);
	}

	@Test
	public void testAdicionarJogador() {
		Jogo jogo = jogoRepository.criar();

		Jogador jogador = new Jogador();
		jogador.setNome("Teste");
		jogoRepository.adicionarJogador(jogo.getId(), jogador);
		assertEquals(1, jogo.getJogadores().size());
	}

	@Test
	public void testAdicionarJogadorComMesmoNome() {
		Jogo jogo = jogoRepository.criar();
		Jogador jogador1 = new Jogador();
		jogador1.setNome("Teste");
		jogo.getJogadores().add(jogador1);

		Jogador jogador2 = new Jogador();
		jogador2.setNome("Teste");

		BlackjackException exception = assertThrows(BlackjackException.class, () -> {
			jogoRepository.adicionarJogador(jogo.getId(), jogador2);
		});

		assertEquals("Já existe um jogador com o mesmo nome: Teste", exception.getMessage());
	}

	@Test
	public void testRemoverJogadorDoJogo() {
		Jogo jogo = jogoRepository.criar();
		Jogador jogador = new Jogador();
		jogador.setId(1);
		jogo.getJogadores().add(jogador);
		jogoRepository.removerJogador(jogo.getId(), 1);
		assertEquals(0, jogo.getJogadores().size());
	}

	@Test
	public void testVerificarJogadorComNome() {
		Jogador jogador = new Jogador();
		jogador.setNome("Teste");
		List<Jogador> jogadores = Arrays.asList(jogador);
		boolean resultado = jogoRepository.verificarJogadorComNome(jogadores, "Teste");
		assertEquals(true, resultado);
	}

	@Test
	public void testSair() {
		Jogo jogo = new Jogo();
		jogo.setId(1);
		jogoRepository.sair(1);
		assertEquals(0, jogoRepository.buscarJogos().size());
	}
}