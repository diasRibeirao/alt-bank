package com.altbank.blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Baralho {

	private List<Carta> cartas;
	
	public Baralho() {
		this.cartas = new ArrayList<>();
	}

	public void iniciarBaralho() {
		this.cartas.clear();
		criarBaralho();
	}

	public void embaralhar() {
		Collections.shuffle(this.cartas);
	}

	public Carta remover() {
		return this.cartas.remove(this.cartas.size() - 1);
	}

	public int tamanho() {
		return this.cartas.size();
	}

	private void criarBaralho() {
		String[] naipes = { "Copas", "Paus", "Ouro", "Espadas" };
		String[] valores = { "as", "dois", "tres", "quatro", "cinco", "seis", "sete", "oito", "nove", "dez", "valete",
				"dama", "rei" };
		int[] pontos = { 11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10 };

		for (String naipe : naipes) {
			for (int i = 0; i < valores.length; i++) {
				Carta carta = new Carta(valores[i], naipe, pontos[i]);
				this.cartas.add(carta);
			}
		}
	}
 
}
