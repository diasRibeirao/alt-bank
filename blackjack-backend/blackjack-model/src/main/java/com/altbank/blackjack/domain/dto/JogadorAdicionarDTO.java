package com.altbank.blackjack.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class JogadorAdicionarDTO {
	
	@NotNull(message = "O nome é obrigatório!")
	@Size(min = 5, max = 30, message = "O nome deve possuir entre {min} e {max} caracteres!")
	private String nome;

}
