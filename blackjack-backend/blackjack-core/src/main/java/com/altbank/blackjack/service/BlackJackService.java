package com.altbank.blackjack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altbank.blackjack.domain.Jogador;
import com.altbank.blackjack.domain.Jogo;
import com.altbank.blackjack.enums.StatusJogo;
import com.altbank.blackjack.service.exceptions.BlackjackException;
import com.altbank.blackjack.service.repository.JogoRepository;

import jakarta.validation.Valid;

@Service
public class BlackJackService {

	private static final Logger logger = LoggerFactory.getLogger(BlackJackService.class);

	@Autowired
	private JogoRepository jogoRepository;

	public Jogo criarJogo() {
		Jogo jogo = jogoRepository.criar();

		logger.info("Adicionando Mesa como jogador da banca");
		adicionarJogadorAoJogo(jogo.getId(), gerarJogadorMesa());

		return jogo;
	}

	public Jogo buscarJogo(Integer jogoId) {
		return jogoRepository.buscarPeloId(jogoId);
	}

	public Jogo adicionarJogadorAoJogo(Integer jogoId, Jogador jogador) {
		return jogoRepository.adicionarJogador(jogoId, jogador);
	}

	public Jogo removerJogadorDoJogo(Integer jogoId, Integer id) {
		return jogoRepository.removerJogadorDoJogo(jogoId, id);
	}

	public Jogo iniciarJogo(Integer jogoId) {
		Jogo jogo = jogoRepository.buscarPeloId(jogoId);

		if (jogo.getJogadores().size() < 2) {
			throw new BlackjackException("Necessário no mínimo 2 jogadores para iniciar o jogo");
		}
		
		jogo.setStatus(StatusJogo.NOVO);

		jogo.getBaralho().iniciarBaralho();
		jogo.getBaralho().embaralhar();

		jogo.getJogadores().forEach(jogador -> {
			jogador.setMao(new ArrayList<>());
			jogador.setPontos(0);
		});

		return jogo;
	}

	public void sairJogo(Integer jogoId) {
		jogoRepository.sair(jogoId);
	}

	public Jogo darCartas(Integer jogoId) {
		Jogo jogo = jogoRepository.buscarPeloId(jogoId);
		jogo.setStatus(StatusJogo.INICIADO);
		for (Jogador j : jogo.getJogadores()) {
			j.addCartaAMao(jogo.getBaralho().remover());
		}
		return jogo;
	}

	public Jogo pegarCarta(Integer jogoId, Integer jogadorId) {
		Jogo jogo = jogoRepository.buscarPeloId(jogoId);
		Jogador jogador = buscarJogadorPeloId(jogo.getJogadores(), jogadorId);
		if (jogador == null) {
			throw new BlackjackException("Jogador não encontrado");
		}
		jogador.addCartaAMao(jogo.getBaralho().remover());
		return jogo;
	}

	private Jogador buscarJogadorPeloId(List<Jogador> jogadores, Integer jogadorId) {
		Optional<Jogador> jogadorEncontrado = jogadores.stream().filter(jogador -> jogador.getId().equals(jogadorId))
				.findFirst();
		return jogadorEncontrado.orElse(null);
	}

	private Jogador gerarJogadorMesa() {
		Jogador jogador = new Jogador();
		jogador.setNome("Mesa");
		return jogador;
	}

	public List<Jogador> buscarJogadoresDoJogo(@Valid Integer jogoId) {
		Jogo jogo = jogoRepository.buscarPeloId(jogoId);
		return jogo.getJogadores();
	}
}
