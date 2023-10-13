package com.altbank.blackjack.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Carta {

	private String nome;
	private String naipe;
	private int valor;

}
