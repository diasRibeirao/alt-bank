# BlackJack
Test Code - Alt.Bank

## Projetos
Neste repositório estão os projetos de back e frontend.
### Backend - Desenvolvido com Java (JDK 17) - Spring Boot (version: 3.1.4)
blackjack-backend
 - blackjack-core
 - blackjack-model
 - blackjack-rest
### Frontend - Desenvolvido com Angular (Angular: 16.2.6 e Node: 18.14.1)
blackjack-frontend
     

## Para executar os projetos
-  Clone o repositório ou faça download;
### Backend
 - Necessária a JDK 17 instalada na máquina;
 - Utilizando uma IDE, como por exemplo Eclipse, SpringToolSuite4, importe o projeto blackjack-backend como Maven;
 - Para iniciar a aplicação clique no modulo 'blackjack-rest' com o botão direito do mouse, vá até a opção *Run As* e selecione Spring Boot App.
#### EndPoints
Para ver a lista de chamadas REST disponíveis, seus parametros, códigos de resposta HTTP, e tipo de retorno, inicie a aplicação e acesse: 
     http://localhost:8080/swagger-ui.html#/
#### Testes
Para executar os testes abra a classe ApplicationTests.java do modulo 'blackjack-core', clique em Run -> Run As -> JUnit Test. Isso fará com que todos os testes sejam executados.
#### Lombok
Por favor, verifique se o Lombok está configurado na IDE.
 - Baixar o jar do lombok;
 - Executar o jar do lombok (java -jar lombok.jar);
 - Selecionar o local onde está o eclipse;
 - Instalar o lombok no eclipse.

### Frontend
 - Necessário o Node 18.14.1 instalado na máquina;
 - Necessário o Angular CLI 16.2.6 instalado na máquina; 
 - Utilizando uma IDE, como por exemplo Visual Studio Code, abre o projeto blackjack-frontend;
 - Para iniciar o projeto, abra um terminal e executa:
      -  npm install
      -  ng serve 
#### Front
Para jogar o BlackJack / Jogo 21 acesse: http://localhost:4200/   
 - Clicar no Botão jogar:  
   ![image](https://github.com/diasRibeirao/alt-bank/assets/29930488/717cebf0-dcdb-4063-b968-b8d22e366299)
 - Adicionar um ou mais jogadores:   
   ![image](https://github.com/diasRibeirao/alt-bank/assets/29930488/c85d87a7-e9ea-4c33-ba07-7b07d7eb65f5)
 - Adicionar jogador:
   ![image](https://github.com/diasRibeirao/alt-bank/assets/29930488/5420ccd2-240e-4a56-bae9-ab62b383de0b)
 - Para jogar, clicar no botão jogar:
   ![image](https://github.com/diasRibeirao/alt-bank/assets/29930488/89cb4051-84c3-4775-8efe-507771bc3a88)
- Ao abrir a tela do jogo, para começar, clique no botão dar cartas:
   ![image](https://github.com/diasRibeirao/alt-bank/assets/29930488/0f7b626d-7307-4f05-bd59-eda0e25fb855)   
- A primeira rodada, as cartas são dadas automaticamente, a partir da segunda rodada de cartas, a MESA pega a carta automaticamente e o outro jogador precisa clicar em pegar cartas:
   ![image](https://github.com/diasRibeirao/alt-bank/assets/29930488/bc48050e-c4c4-42bb-a38d-fc104eea1aa7)
 - E as rodas seguem, Mesa pegando carta automaticamente e o outro jogador clicando em pegar cartas até ter um vencedor :
   ![image](https://github.com/diasRibeirao/alt-bank/assets/29930488/d1aeaae5-9a09-4954-b9ff-22cffe958d13)
 - A rodada termina quando:
      - Jogador faz exatamente 21 pontos;
      - Jogadores ultrapassem 21 pontos, ;
  ![image](https://github.com/diasRibeirao/alt-bank/assets/29930488/0c9911e6-f2c1-4790-9766-b6562226d380)

#### Ao terminar a rodada, pode iniciar um novo jogo, retornar ao menu principal para adicionar ou remover jogadores, sair.





