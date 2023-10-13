import { HttpClient } from '@angular/common/http';
import { Inject, Injectable, Injector } from '@angular/core';

import { Jogador } from '../model/jogador';
import { Observable } from 'rxjs';
import { AdicionarJogador } from '../model/adicionarJogador';
import { ToastrService } from 'ngx-toastr';
import { Jogo } from '../model/jogo';

@Injectable({
  providedIn: 'root'
})
export class BlackjackService {

  //private readonly API = 'http://localhost:8080/api/v1/blackjack';
  private readonly API = 'http://192.168.15.23:8080/api/v1';

  constructor(
    private httpClient: HttpClient,
    @Inject(Injector) private injector: Injector
  ) { }

  iniciarJogo(jogoId: number) {
    return this.httpClient.post<Jogo>(`${this.API}/jogos/${jogoId}/iniciar`, null);
  }

  criarJogo(){
    return this.httpClient.post<Jogo>(`${this.API}/jogos/criar`, null);
  }

  sairJogo(jogoId: number): Observable<any> {
    return this.httpClient.delete<any>(`${this.API}/jogos/${jogoId}/sair`);
  }

  darCartas(jogoId: number) {
    return this.httpClient.post<Jogo>(`${this.API}/jogos/${jogoId}/dar-cartas`, null);
  }

  pegarCarta(jogoId: number, jogadorId: number) {
    return this.httpClient.post<Jogo>(`${this.API}/jogos/${jogoId}/pegar-carta/${jogadorId}`, null);
  }

  buscarJogo(jogoId: number) {
    return this.httpClient.get<Jogo>(`${this.API}/jogos/${jogoId}`);
  }

  adicionarJogador(jogoId: number, payload: AdicionarJogador): Observable<any> {
    return this.httpClient.post<Jogo>(`${this.API}/${jogoId}/jogadores`, payload);
  }

  removerJogador(jogoId: number, jogadorId: number): Observable<any> {
    return this.httpClient.delete<any>(`${this.API}/${jogoId}/jogadores/${jogadorId}`);
  }

  private get toastrService(): ToastrService {
    return this.injector.get(ToastrService);
  }

  exibirMensagem(msg: string, isError: boolean = false): void {
    isError
      ? this.toastrService.error(`${msg}`, 'Erro')
      : this.toastrService.success(`${msg}`, 'Sucesso.');
  }

}
