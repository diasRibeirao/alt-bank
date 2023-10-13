package com.altbank.blackjack.domain;

import java.util.ArrayList;
import java.util.List;

import com.altbank.blackjack.enums.StatusJogador;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Jogador {
	private Integer id;
	private String nome;
	private List<Carta> mao;
	private Integer pontos;
	@SuppressWarnings("unused")
	private StatusJogador status;

	public Jogador() {
		this.id = (int) System.currentTimeMillis() % 1000;
		this.pontos = 0;
		this.mao = new ArrayList<>();
		this.status = StatusJogador.ATIVO;
	}

	public void addCartaAMao(Carta carta) {
		this.pontos += carta.getValor();
		this.mao.add(carta);
	}

	public StatusJogador getStatus() {
		if (this.pontos > 21) {
			return StatusJogador.PERDEU;
		} else if (this.pontos == 21) {
			return StatusJogador.VENCEU;
		}
		return StatusJogador.ATIVO;
	}
}
