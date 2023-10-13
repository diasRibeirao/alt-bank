import { Carta } from "./carta";

export interface Jogador {
  id: number;
  nome: string;
  mao: Carta[];
  pontos: number;
  status: string;
}
