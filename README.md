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
 - Utilizando uma IDE, como por exemplo Eclipse, STS, importe o projeto blackjack-backend como Maven;
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


