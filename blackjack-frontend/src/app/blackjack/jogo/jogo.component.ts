import { Jogador } from './../model/jogador';
import { Component, OnInit } from '@angular/core';
import { BlackjackService } from '../services/blackjack.service';
import { Router } from '@angular/router';
import { Jogo } from '../model/jogo';

@Component({
  selector: 'app-jogo',
  templateUrl: './jogo.component.html',
  styleUrls: ['./jogo.component.scss']
})
export class JogoComponent implements OnInit {
  jogo: Jogo;
  jogoId: number;
  jogadorDaRodada: Jogador;

  constructor(
    private router: Router,
    private readonly blackjackService: BlackjackService
  ) {
    this.jogoId = Number(sessionStorage.getItem('jogoId'));
    this.jogo = {
      id: 1,
      status: "NOVO",
      jogadores: [],
      Baralho: null
    };

    this.jogadorDaRodada = {
      id: 0,
      nome: '',
      mao: [],
      pontos: 0,
      status: ''
    };
  }

  ngOnInit(): void {
    this.iniciarJogo()
  }

  onVoltar() {
    this.router.navigate(['/blackjack']);
  }

  iniciarJogo() {
    try {
      this.blackjackService.iniciarJogo(this.jogoId).subscribe({
        next: (retorno: Jogo) => {
          this.jogo = retorno;
          console.log(retorno);
        },
        error: (data) => {
          if ((data.error.status === 400 || data.error.status === 404)  && data.error.msg != '') {
            this.blackjackService.exibirMensagem(data.error.msg, true);
          } else {
            this.blackjackService.exibirMensagem(`Erro ao criar jogo. Por favor, tente novamente mais tarde.`, true);
            this.router.navigate(['home']);
          }
        },
      });

    } catch (err) {
      this.blackjackService.exibirMensagem('Erro ao iniciar o jogo.', true);
      this.router.navigate(['home']);
    }
  }

  darCartas() {
    try {
      this.blackjackService.darCartas(this.jogoId).subscribe({
        next: (retorno: Jogo) => {
          this.jogo = retorno;
          if (this.jogo.jogadores && this.jogo.jogadores.length > 0) {
            this.jogadorDaRodada = this.jogo.jogadores[0];
            this.pegarCartaAutomatico();
          }
          console.log(retorno);
        },
        error: (data) => {
          if (data.error.status === 400 && data.error.msg != '') {
            this.blackjackService.exibirMensagem(data.error.msg, true);
          } else {
            this.blackjackService.exibirMensagem(`Erro ao adicionar jogador. Por favor, tente novamente mais tarde.`, true);
          }
        },
      });

    } catch (err) {
      this.blackjackService.exibirMensagem('Erro ao dar cartas.', true);
    }
  }

  pegarCarta(jogadorId: number) {
    try {
      this.blackjackService.pegarCarta(this.jogoId, jogadorId).subscribe({
        next: (retorno: Jogo) => {
          this.jogo = retorno;
          this.verificarStatus(jogadorId);
        },
        error: (data) => {
          if (data.error.status === 400 && data.error.msg != '') {
            this.blackjackService.exibirMensagem(data.error.msg, true);
          } else {
            this.blackjackService.exibirMensagem(`Erro ao adicionar jogador. Por favor, tente novamente mais tarde.`, true);
          }
        },
      });
    } catch (err) {
      this.blackjackService.exibirMensagem('Erro ao pegar carta.', true);
    }
  }

  encontrarProximoAtivo(jogadorAtualId: number, jogadores: Jogador[]): Jogador {
    let index = jogadores.findIndex(jogador => jogador.id === jogadorAtualId);
    let proximoIndex = (index + 1) % jogadores.length;

    while (proximoIndex !== index) {
      if (jogadores[proximoIndex].status === 'ATIVO') {
        return jogadores[proximoIndex];
      }
      proximoIndex = (proximoIndex + 1) % jogadores.length;
    }

    throw new Error('Nenhum jogador ativo encontrado na lista.');
  }

  existeVencedor(jogadores: Jogador[]): boolean {
    const vencedores = jogadores.filter(jogador => jogador.status === 'VENCEU');

    if (vencedores.length > 0) {
      jogadores.forEach(jogador => {
        if (!vencedores.includes(jogador)) {
          jogador.status = 'PERDEU';
        }
      });
      return true;
    }

    const jogadoresAtivos = jogadores.filter(jogador => jogador.status === 'ATIVO');
    if (jogadoresAtivos.length === 1) {
      jogadoresAtivos[0].status = 'VENCEU';
      return true;
    }

    return false;
  }

  verificarStatus(jogadorId: number) {
    console.log(this.jogo);

    // Existe vencedor
    const jogadorVendeu = this.existeVencedor(this.jogo.jogadores);
    if (jogadorVendeu) {
      this.jogo.status = 'FINALIZADO';
    } else {
      try {
        this.jogadorDaRodada = this.encontrarProximoAtivo(jogadorId, this.jogo.jogadores);
        this.pegarCartaAutomatico();
      } catch (err) {
        this.blackjackService.exibirMensagem('Erro ao encontrar próximo jogador a pegar carta.', true);
      }
    }
  }

  pegarCartaAutomatico() {
    if (this.jogadorDaRodada.nome === 'Mesa') {
      this.pegarCarta(this.jogadorDaRodada.id);
    }
  }

}
