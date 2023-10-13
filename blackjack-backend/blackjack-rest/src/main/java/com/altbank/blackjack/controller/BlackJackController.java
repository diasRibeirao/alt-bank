package com.altbank.blackjack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altbank.blackjack.domain.Jogador;
import com.altbank.blackjack.domain.Jogo;
import com.altbank.blackjack.domain.dto.JogadorAdicionarDTO;
import com.altbank.blackjack.domain.dto.converter.JogadorConverter;
import com.altbank.blackjack.service.BlackJackService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Blackjack")
@RequestMapping("/api/v1")
public class BlackJackController {

	@Autowired
	private BlackJackService blackJackService;

	@Autowired
	private JogadorConverter jogadorConverter;

	@PostMapping("/jogos/criar")
	@Operation(summary = "Criar Jogo")
	public ResponseEntity<Jogo> criarJogo() {
		Jogo jogo = blackJackService.criarJogo();
		return ResponseEntity.ok().body(jogo);
	}

	@PostMapping("/{jogoId}/jogadores")
	@Operation(summary = "Adicionar Jogador")
	public ResponseEntity<Jogo> adicionarJogadorAoJogo(@Valid @PathVariable Integer jogoId,
			@Valid @RequestBody JogadorAdicionarDTO jogadorAdicionarDTO) {
		Jogador jogador = jogadorConverter.parseAdicionarJogadorDTO(jogadorAdicionarDTO);
		Jogo jogo = blackJackService.adicionarJogadorAoJogo(jogoId, jogador);
		return ResponseEntity.ok().body(jogo);
	}
	
	@GetMapping("/jogos/{jogoId}")
	@Operation(summary = "Buscar Jogo")
	public ResponseEntity<Jogo> buscarJogo(@Valid @PathVariable Integer jogoId) {
		Jogo jogo = blackJackService.buscarJogo(jogoId);
		return ResponseEntity.ok().body(jogo);
	}

	@DeleteMapping("/{jogoId}/jogadores/{jogadorId}")
	@Operation(summary = "Remover Jogador")
	public ResponseEntity<Jogo> removerJogadorDoJogo(@Valid @PathVariable Integer jogoId,
			@Valid @PathVariable Integer jogadorId) {
		Jogo jogo = blackJackService.removerJogadorDoJogo(jogoId, jogadorId);
		return ResponseEntity.ok().body(jogo);
	}

	@PostMapping("/jogos/{jogoId}/pegar-carta/{jogadorId}")
	@Operation(summary = "Jogador Pega Carta")
	public ResponseEntity<Jogo> pegarCarta(@Valid @PathVariable Integer jogoId,
			@Valid @PathVariable Integer jogadorId) {
		Jogo jogo = blackJackService.pegarCarta(jogoId, jogadorId);
		return ResponseEntity.ok().body(jogo);
	}

	@PostMapping("/jogos/{jogoId}/iniciar")
	@Operation(summary = "Iniciar o Jogo")
	public ResponseEntity<Jogo> iniciarJogo(@Valid @PathVariable Integer jogoId) {
		Jogo jogo = blackJackService.iniciarJogo(jogoId);
		return ResponseEntity.ok().body(jogo);
	} 
 
	@PostMapping("/jogos/{jogoId}/dar-cartas")
	@Operation(summary = "Dar Cartas")
	public ResponseEntity<Jogo> darCartas(@Valid @PathVariable Integer jogoId) {
		Jogo jogo = blackJackService.darCartas(jogoId);
		return ResponseEntity.ok().body(jogo);
	}

	@DeleteMapping("/jogos/{jogoId}/sair")
	@Operation(summary = "Sair do Jogo")
	public void sairJogo(@Valid @PathVariable Integer jogoId) {
		blackJackService.sairJogo(jogoId);
	}

}
