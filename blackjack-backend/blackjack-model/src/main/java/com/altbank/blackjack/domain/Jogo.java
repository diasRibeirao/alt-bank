package com.altbank.blackjack.domain;

import java.util.ArrayList;
import java.util.List;

import com.altbank.blackjack.enums.StatusJogo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Jogo {
	private Integer id;
	private StatusJogo status;
	private List<Jogador> jogadores;
	private Baralho baralho;

	public Jogo() {
		this.id = (int) System.currentTimeMillis() % 1000;
		this.status = StatusJogo.NOVO;
		this.jogadores = new ArrayList<>();
	}

	public void addJogador(List<Jogador> jogadores) {
		this.jogadores.addAll(jogadores);
	}
}
