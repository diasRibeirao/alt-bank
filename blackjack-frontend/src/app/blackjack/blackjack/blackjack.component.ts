import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';

import { ActivatedRoute, Router } from '@angular/router';
import { BlackjackService } from '../services/blackjack.service';
import { Jogador } from '../model/jogador';
import { ConfirmDialogComponent } from 'src/app/shared/components/confirm-dialog/confirm-dialog.component';
import { Jogo } from '../model/jogo';

@Component({
  selector: 'app-blackjack',
  templateUrl: './blackjack.component.html',
  styleUrls: ['./blackjack.component.scss']
})
export class BlackjackComponent implements OnInit {
  jogo: Jogo;
  jogoId: number;
  displayedColumns = ['nome', 'actions'];

  constructor(
    private blackjackService: BlackjackService,
    public dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.jogo = {
      id: 0,
      status: "NOVO",
      jogadores: [],
      Baralho: null
    };

    this.jogoId = Number(sessionStorage.getItem('jogoId'));
  }

  ngOnInit(): void {
    if (this.jogoId === 0) {
      this.criarJogo()
    } else {
      this.buscarJogo()
    }
  }

  onAddJogador() {
    this.router.navigate(['jogador'], {relativeTo: this.route});
  }

  onIniciarJogo() {
    this.router.navigate(['jogo'], {relativeTo: this.route});
  }

  onSair() {
    try {
      this.blackjackService.sairJogo(this.jogoId).subscribe({
        next: () => {
          this.blackjackService.exibirMensagem(`Saiu do jogo com sucesso!!!`, false);
        },
        error: () => {
          this.blackjackService.exibirMensagem(`Erro ao sair do jogo. Por favor, tente novamente mais tarde.`, true);
        },
      });
    } catch (err: unknown) {
      this.blackjackService.exibirMensagem(`Error ${err}`, true);
    }
    sessionStorage.removeItem('jogoId');
    this.router.navigate(['home']);
  }

  onRemoverJogador(jogador : Jogador) {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data:{
        message: 'Deseja realmente excluir o jogador ' + jogador.nome +'?'      }
      });

      dialogRef.afterClosed().subscribe((confirmed: boolean) => {
        if (confirmed) {
          try {
            this.blackjackService.removerJogador(this.jogo.id, jogador.id).subscribe({
              next: () => {
                this.blackjackService.exibirMensagem(`Jogador excluído com sucesso!!!`, false);
                this.buscarJogo();
              },
              error: () => {
                this.blackjackService.exibirMensagem(`Erro ao excluir jogador. Por favor, tente novamente mais tarde.`, true);
              },
            });
          } catch (err: unknown) {
            this.blackjackService.exibirMensagem(`Error ${err}`, true);
          }
        }
      });
  }

  criarJogo() {
    try {
      this.blackjackService.criarJogo().subscribe({
        next: (retorno: Jogo) => {
          this.jogo = retorno;
          this.jogo.jogadores = this.jogo.jogadores.filter((jogador: Jogador) => jogador.nome.toLowerCase() !== "mesa")
          sessionStorage.setItem('jogoId', this.jogo.id.toString());
        },
        error: (data) => {
          if ((data.error.status === 400 || data.error.status === 404)  && data.error.msg != '') {
            this.blackjackService.exibirMensagem(data.error.msg, true);
          } else {
            this.blackjackService.exibirMensagem(`Erro ao criar jogo. Por favor, tente novamente mais tarde.`, true);
          }
          this.router.navigate(['home']);
        },
      });

    } catch (err) {
      this.blackjackService.exibirMensagem('Erro ao iniciar o jogo.', true);
      this.router.navigate(['home']);
    }
  }

  buscarJogo() {
    try {
      this.blackjackService.buscarJogo(this.jogoId).subscribe({
        next: (retorno: Jogo) => {
          this.jogo = retorno;
          this.jogo.jogadores = this.jogo.jogadores.filter((jogador: Jogador) => jogador.nome.toLowerCase() !== "mesa")
          console.log(this.jogo);
        },
        error: (data) => {
          console.log(data.error);
          if ((data.error.status === 400 || data.error.status === 404) && data.error.msg != '') {
            this.blackjackService.exibirMensagem(data.error.msg, true);
          } else {
            this.blackjackService.exibirMensagem(`Erro ao buscar o jogo. Por favor, tente novamente mais tarde.`, true);
          }
          this.router.navigate(['home']);
        },
      });
    } catch (err) {
      this.blackjackService.exibirMensagem('Erro ao buscar jogo.', true);
      this.router.navigate(['home']);
    }
  }

}
