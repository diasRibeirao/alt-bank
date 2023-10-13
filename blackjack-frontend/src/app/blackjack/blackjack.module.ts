import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { AppMaterialModule } from '../shared/app-material/app-material.module';
import { SharedModule } from '../shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';
import { BlackjackComponent } from './blackjack/blackjack.component';
import { BlackjackRoutingModule } from './blackjack-routing.module';
import { JogadorComponent } from './jogador/jogador.component';
import { JogoComponent } from './jogo/jogo.component';

@NgModule({
  declarations: [
    BlackjackComponent,
    JogadorComponent,
    JogoComponent,
  ],
  imports: [
    CommonModule,
    BlackjackRoutingModule,
    AppMaterialModule,
    SharedModule,
    ReactiveFormsModule,
  ]
})
export class BlackjackModule { }
