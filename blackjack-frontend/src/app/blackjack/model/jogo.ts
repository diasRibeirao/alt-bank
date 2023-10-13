import { Baralho } from "./baralho";
import { Jogador } from "./jogador";

export interface Jogo {
  id: number;
  status: string;
  jogadores: Jogador[];
  Baralho: Baralho | null;
}
