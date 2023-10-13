import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BlackjackComponent } from './blackjack/blackjack.component';
import { JogadorComponent } from './jogador/jogador.component';
import { JogoComponent } from './jogo/jogo.component';


const routes: Routes = [
  { path: '', component: BlackjackComponent },
  { path: 'jogador', component: JogadorComponent },
  { path: 'jogo', component: JogoComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BlackjackRoutingModule { }
