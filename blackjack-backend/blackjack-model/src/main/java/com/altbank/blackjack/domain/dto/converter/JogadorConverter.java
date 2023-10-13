package com.altbank.blackjack.domain.dto.converter;

import org.springframework.stereotype.Component;

import com.altbank.blackjack.domain.Jogador;
import com.altbank.blackjack.domain.dto.JogadorAdicionarDTO;

@Component
public class JogadorConverter {

	public Jogador parseAdicionarJogadorDTO(JogadorAdicionarDTO origin) {
		if (origin == null)
			return null;

		Jogador jogador = new Jogador();
		jogador.setNome(origin.getNome());
		return jogador;
	}

}
