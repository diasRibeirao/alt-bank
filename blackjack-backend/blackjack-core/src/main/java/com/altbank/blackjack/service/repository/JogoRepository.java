package com.altbank.blackjack.service.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.altbank.blackjack.domain.Baralho;
import com.altbank.blackjack.domain.Jogador;
import com.altbank.blackjack.domain.Jogo;
import com.altbank.blackjack.service.exceptions.BlackjackException;
import com.altbank.blackjack.service.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Repository
public class JogoRepository {

	private List<Jogo> jogos;

	public JogoRepository() {
		this.jogos = new ArrayList<>();
	}

	public Jogo buscarPeloId(Integer id) {
		Optional<Jogo> jogoEncontrado = this.jogos
				.stream()
				.filter(jogo -> jogo.getId().equals(id))
				.findFirst();
		return jogoEncontrado.orElseThrow(() -> new ObjectNotFoundException("Jogo não encontrado! Id: " + id));
	}

	public Jogo criar() {
		Jogo jogo = new Jogo();
		this.jogos.add(jogo);
		jogo.setBaralho(new Baralho());
		return jogo;
	}

	public Jogo adicionarJogador(Integer jogoId, Jogador jogador) {
		Jogo jogo = buscarPeloId(jogoId);
		if (verificarJogadorComNome(jogo.getJogadores(), jogador.getNome())) {
			throw new BlackjackException("Já existe um jogador com o mesmo nome: " + jogador.getNome());
		}		
		jogo.getJogadores().add(jogador);
		return jogo;
	}
	
	public Jogo removerJogadorDoJogo(@Valid Integer jogoId, @Valid Integer id) {
		Jogo jogo = buscarPeloId(jogoId);
		jogo.getJogadores().removeIf(jogador -> jogador.getId().equals(id));		
		return jogo;
	}
		
	public boolean verificarJogadorComNome(List<Jogador> jogadores, String nome) {
		return Optional.ofNullable(nome)
				.map(String::trim)
				.filter(trimmed -> !trimmed.isEmpty())
				.map(trimmed -> jogadores.stream().anyMatch(jogador -> nome.equalsIgnoreCase(jogador.getNome())))
				.orElse(false);
	}

	public void sair(Integer jogoId) {
		this.jogos.removeIf(jogador -> jogador.getId().equals(jogoId));
	}

	

}
