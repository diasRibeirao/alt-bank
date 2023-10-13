//package com.altbank.blackjack.service;
//
//import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.altbank.blackjack.component.Baralho;
//import com.altbank.blackjack.domain.Jogador;
//import com.altbank.blackjack.service.exceptions.BlackjackException;
//
//public class BlackJackServiceTest {
//
//	@InjectMocks
//	private BlackJackService blackJackService;
//
//	@Mock
//	private Baralho baralho;
//
//	@BeforeEach
//	public void setUp() {
//		MockitoAnnotations.openMocks(this);
//	}
//
//	@Test
//	public void testAdicionarJogador() {
//		Jogador jogador = new Jogador();
//		jogador.setNome("Jogador");
//
//		blackJackService.addJogador(jogador);
//		assertEquals(1, blackJackService.listarJogadores().size());
//	}
// 
//	@Test
//	public void testIniciarJogoComMenosDeDoisJogadores() {
//		Jogador jogador = new Jogador();
//		jogador.setNome("Jogador");
//
//		blackJackService.addJogador(jogador);
//		assertThrows(BlackjackException.class, () -> blackJackService.iniciarJogo());
//	}
//
//	@Test
//	public void testIniciarJogoComDoisOuMaisJogadores() {
//		Jogador jogador1 = new Jogador();
//		jogador1.setNome("Jogador1");
//
//		Jogador jogador2 = new Jogador();
//		jogador2.setNome("Jogador2");
//
//		blackJackService.addJogador(jogador1);
//		blackJackService.addJogador(jogador2);
//
//		assertDoesNotThrow(() -> blackJackService.iniciarJogo());
//
//		verify(baralho, times(1)).iniciarBaralho();
//		verify(baralho, times(1)).embaralhar();
//	}
//}
