import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router, } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdicionarJogador } from '../model/adicionarJogador';
import { BlackjackService } from '../services/blackjack.service';

@Component({
  selector: 'app-jogador',
  templateUrl: './jogador.component.html',
  styleUrls: ['./jogador.component.scss']
})
export class JogadorComponent implements OnInit {
  jogoId: number;
  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private readonly blackjackService: BlackjackService
    ) {
      this.jogoId = Number(sessionStorage.getItem('jogoId'));
      this.form = this.formBuilder.group({
        nome: [
          '',
          [
            Validators.required,
            Validators.minLength(5),
            Validators.maxLength(30),
          ],
        ]
      });
    }

  ngOnInit(): void {

  }

  onSubmit() {
    if (this.form.valid) {
      try {
        const adicionarJogador: AdicionarJogador = {
          nome: this.form.get('nome')!.value
        };

        this.blackjackService.adicionarJogador(this.jogoId, adicionarJogador).subscribe({
          next: () => {
            this.blackjackService.exibirMensagem(`Jogador adicionado com sucesso!`, false);
            this.router.navigate(['/blackjack']);
          },
          error: (data) => {
            if (data.error.status === 400 && data.error.msg != '') {
              this.blackjackService.exibirMensagem(data.error.msg, true);
            } else {
              this.blackjackService.exibirMensagem(`Erro ao adicionar jogador. Por favor, tente novamente mais tarde.`, true);
            }
          },
        });

      } catch (err: unknown) {
        this.blackjackService.exibirMensagem(`Error ${err}`, true);
        this.router.navigate(['/blackjack']);
      }
    }
  }

  onCancel() {
    this.router.navigate(['blackjack']);
  }

  public hasError = (controlName: string, errorName: string) => {
    return this.form.controls[controlName].hasError(errorName);
  };

}
