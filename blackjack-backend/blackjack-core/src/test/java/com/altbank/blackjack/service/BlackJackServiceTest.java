package com.altbank.blackjack.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.altbank.blackjack.domain.Baralho;
import com.altbank.blackjack.domain.Jogador;
import com.altbank.blackjack.domain.Jogo;
import com.altbank.blackjack.enums.StatusJogo;
import com.altbank.blackjack.repository.JogoRepository;
import com.altbank.blackjack.service.exceptions.BlackjackException;

public class BlackJackServiceTest {

	@Mock
	private JogoRepository jogoRepository;

	@InjectMocks
	private BlackJackService blackJackService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCriarJogo() {
		Jogo jogoMock = new Jogo();
		Mockito.when(jogoRepository.criar()).thenReturn(jogoMock);

		Jogo jogo = blackJackService.criarJogo();

		assertNotNull(jogo);
		assertEquals(jogoMock, jogo);
	}

	@Test
	void testBuscarJogo() {
		Integer jogoId = 1;

		Jogo jogoMock = new Jogo();
		jogoMock.setId(jogoId);
		Mockito.when(jogoRepository.buscarPeloId(jogoId)).thenReturn(jogoMock);

		Jogo jogo = blackJackService.buscarJogo(jogoId);

		assertEquals(jogoMock, jogo);

		Mockito.verify(jogoRepository, Mockito.times(1)).buscarPeloId(jogoId);
	}

	@Test
	void testIniciarJogoComMenosDeDoisJogadores() {
		Jogo jogoMock = new Jogo();
		Mockito.when(jogoRepository.buscarPeloId(1)).thenReturn(jogoMock);

		BlackjackException exception = assertThrows(BlackjackException.class, () -> {
			blackJackService.iniciarJogo(1);
		});

		assertEquals("Necessário no mínimo 2 jogadores para iniciar o jogo", exception.getMessage());
	}

	@Test
	void testIniciarJogoComDoisOuMaisJogadores() {
		Jogo jogoMock = new Jogo();
		jogoMock.setBaralho(new Baralho());

		Jogador jogador1 = new Jogador();
		jogador1.setNome("Jogador1");

		Jogador jogador2 = new Jogador();
		jogador1.setNome("jogador2");

		jogoMock.setJogadores(Arrays.asList(jogador1, jogador2));

		Mockito.when(jogoRepository.buscarPeloId(1)).thenReturn(jogoMock);

		Jogo jogo = blackJackService.iniciarJogo(1);

		assertEquals(StatusJogo.NOVO, jogo.getStatus());
		assertEquals(0, jogador1.getMao().size());
		assertEquals(0, jogador2.getMao().size());

		verify(jogoRepository, times(1)).buscarPeloId(1);
	}

	@Test
	void testAdicionarJogadorAoJogo() {
		Integer jogoId = 1;
		Jogador jogador = new Jogador();
		jogador.setId(2);

		Jogo jogoMock = new Jogo();
		jogoMock.setId(jogoId);
		Mockito.when(jogoRepository.adicionarJogador(jogoId, jogador)).thenReturn(jogoMock);

		Jogo jogo = blackJackService.adicionarJogadorAoJogo(jogoId, jogador);

		assertEquals(jogoMock, jogo);
		Mockito.verify(jogoRepository, Mockito.times(1)).adicionarJogador(jogoId, jogador);
	}

	@Test
	void testRemoverJogadorDoJogo() {
		Integer jogoId = 1;
		Integer jogadorId = 2;

		Jogo jogoMock = new Jogo();
		jogoMock.setId(jogoId);
		Mockito.when(jogoRepository.removerJogador(jogoId, jogadorId)).thenReturn(jogoMock);

		Jogo jogo = blackJackService.removerJogadorDoJogo(jogoId, jogadorId);

		assertEquals(jogoMock, jogo);
		Mockito.verify(jogoRepository, Mockito.times(1)).removerJogador(jogoId, jogadorId);
	}

	@Test
	void testDarCartas() {
		Integer jogoId = 1;
		Jogo jogoMock = new Jogo();
		jogoMock.setId(jogoId);
		jogoMock.setStatus(StatusJogo.INICIADO);

		Baralho baralhoMock = new Baralho();
		baralhoMock.iniciarBaralho();
		baralhoMock.embaralhar();

		Jogador jogador1 = new Jogador();
		Jogador jogador2 = new Jogador();
		jogoMock.setJogadores(Arrays.asList(jogador1, jogador2));
		jogoMock.setBaralho(baralhoMock);

		Mockito.when(jogoRepository.buscarPeloId(jogoId)).thenReturn(jogoMock);

		Jogo jogo = blackJackService.darCartas(jogoId);

		assertEquals(StatusJogo.INICIADO, jogo.getStatus());
		assertEquals(1, jogador1.getMao().size());
		assertEquals(1, jogador2.getMao().size());
	}

	@Test
	void testPegarCarta() {
		Integer jogoId = 1;
		Integer jogadorId = 1;

		Jogo jogoMock = new Jogo();
		jogoMock.setId(jogoId);
		jogoMock.setStatus(StatusJogo.INICIADO);

		Baralho baralhoMock = new Baralho();
		baralhoMock.iniciarBaralho();
		baralhoMock.embaralhar();

		Jogador jogadorMock = new Jogador();
		jogadorMock.setId(jogadorId);

		jogoMock.setJogadores(Arrays.asList(jogadorMock));
		jogoMock.setBaralho(baralhoMock);

		Mockito.when(jogoRepository.buscarPeloId(jogoId)).thenReturn(jogoMock);

		Jogo jogo = blackJackService.pegarCarta(jogoId, jogadorId);

		assertEquals(StatusJogo.INICIADO, jogo.getStatus());
		assertEquals(1, jogadorMock.getMao().size());
	}

	@Test
	void testPegarCartaJogadorNaoEncontrado() {
		Integer jogoId = 1;
		Integer jogadorId = 2;

		Jogo jogoMock = new Jogo();
		jogoMock.setId(jogoId);
		jogoMock.setStatus(StatusJogo.INICIADO);

		Baralho baralhoMock = new Baralho();
		baralhoMock.iniciarBaralho();
		baralhoMock.embaralhar();

		Jogador jogadorMock = new Jogador();
		jogadorMock.setId(1);

		jogoMock.setJogadores(Arrays.asList(jogadorMock));
		jogoMock.setBaralho(baralhoMock);

		Mockito.when(jogoRepository.buscarPeloId(jogoId)).thenReturn(jogoMock);

		assertThrows(BlackjackException.class, () -> blackJackService.pegarCarta(jogoId, jogadorId));
	}

	@Test
	void testBuscarJogadoresDoJogo() {
		Jogo jogoMock = new Jogo();
		Jogador jogador1 = new Jogador();
		Jogador jogador2 = new Jogador();
		jogoMock.setJogadores(Arrays.asList(jogador1, jogador2));

		Mockito.when(jogoRepository.buscarPeloId(1)).thenReturn(jogoMock);
		List<Jogador> jogadores = blackJackService.buscarJogadoresDoJogo(1);

		assertEquals(2, jogadores.size());
		assertTrue(jogadores.contains(jogador1));
		assertTrue(jogadores.contains(jogador2));
	}
	
	@Test
    void testSairJogo() {
        blackJackService.sairJogo(1);
        Mockito.verify(jogoRepository, Mockito.times(1)).sair(1);
    }
}
