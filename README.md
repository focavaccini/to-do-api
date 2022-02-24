Para realizar o deploy da aplicação, utilizaremos o Heroku, pois ele ja faz o build da aplicação e implementação


1 passo: Será necessário cadastrar-se no Heroku. 
2 passo: Acesse heroku.com e faça o procedimento o Sign up.
3 passo: Preencha os dados e envie o formulário.
4 passo: Abra seu email e clique no link de confirmação.
5 passo: Defina uma senha 

Criando um novo app
Já realizado os passos anteriores e estando dentro do Heroku
6 passo:  Clique em Create New App.
7 passo:  Digite um nome para o e escolha a localidade.

Instalação do Postgres no Heroku
8 passo: clique na Aba Resources.
9 passo: Na caixa Add-nos, procure por postgres(será necessário ter o postgres instalado) e irá encontrar Heroku Postgres.
10 passo: Escolha o plano, podendo ser freee clique em Provision ou Submit for order 

Criando database no pgAdmin4
11 passo: Inicie o pgAdmin, dentro do pgAdmin4 clique com o botão direito em Databases, depois Create -> Database e defina um nome. Em owner deixe postgres, e assim quando for rodar o projeto no profile dev, as credenciais ser�o as mesmas, e nas configura��es opte por deixar Encoding: UTF8.

Gerando o script do banco de dados
12 passo: No arquivo application.properties altere para dev, caso esteja rodando no profile test 
	spring.profiles.active=dev 

application-dev.properties deverá ser informado o usuário e senha definidos na instalação do postgres nos campos:
	spring.datasource.username=usuario
	spring.datasource.password=senha


13 passo: Dentro do pgAdmin4:
==> Clique com o botão direito em cima da database -> Backup
==> Após abrir a janela faça os seguintes passos conforme a descrição
==> Escolha o local que irá ser salvo o documento. Ex C:\temp\script.sql
==> Format: Plain 
==> Encoding: UTF8 
==> Na aba Dump options: siga abaixo como deverá estar configurado
==> Type of objects: Only schema: YES, Blobs: NO 
==> Em Do not save: marque todos para YES 
==> Em Miscellaneous: Verbose messages: NO
==> Em seguida clique em backup

14 passo: Com o arquivo já baixado abra em um editor, e delete todas as linhas acima de -- Name: task; Type: TABLE; Schema: public; Owner: -

15 passo: Agora dentro do Heroku, clique no app que criou, e em seguida clique em settings. Agora clique em Reveal Config Vars para que assim possa ter acesso as váriaveis de ambiente da aplicação.
16 passo: Copie o valor de DATABASE_URL postgres://immikxefnvglfo:5a79bf54ccc3ad9574b2908cafdfbd7a503d86b4f240f2a727e0a08486737dff@ec2-3-228-222-169.compute-1.amazonaws.com:5432/de62dgmg9f8nut
17 passo: Agora em um editor de texto para poder retirar algumas informações da aplicação;
user: immikxefnvglfo --- o valor de user começa logo após postgres:// e vai até ':' ex:  postgres://immikxefnvglfo:
password: 5a79bf54ccc3ad9574b2908cafdfbd7a503d86b4f240f2a727e0a08486737dff --- o valor do password começa logo ':' e vai até '@' ex :5a79bf54ccc3ad9574b2908cafdfbd7a503d86b4f240f2a727e0a08486737dff@
server: ec2-3-228-222-169.compute-1.amazonaws.com --- o valor de server começa logo após '@' e vai até ':' ex: @ec2-3-228-222-169.compute-1.amazonaws.com:
port: 5432 --- o valor da database começa a partir de ':' e vai até '/' ext :5432/
database: de62dgmg9f8nut --- o valor da database começa a partir de '/' ex: /de62dgmg9f8nut

18 passo: Volte ao pgAdmin4 e crie um novo servidor remoto, que estará apontando lá para o Heroku
19 passo: Dentro do pgAdmin4 clique com o botão direito em cima de Server -> Create -> Server
20 passo: Dentro da janela que irá abrir, escolhe um nome para o seu server
21 passo: Na aba Connection da janela siga as instruções abaixo 
	- Campo Host name/address deverá ser informado o valor de server, retirado da linha no passo 17- ec2-3-228-222-169.compute-1.amazonaws.com
	- Campo Port deverá ser informado o valor de port, também retirado no passo 17 - 5432
	- Campo Maintenance database deverá ser informado o valor da database retirado no passo 17 - de62dgmg9f8nut
	- Campo Username deverá ser informado o valor do user retirado no passo 17 - immikxefnvglfo
	- Campo Password deverá ser informado o valor do password retirado no passo 17 - 5a79bf54ccc3ad9574b2908cafdfbd7a503d86b4f240f2a727e0a08486737dff
22 passo: Na aba Advanced
	- Campo DB restriction informe novamente o nome da sua database - de62dgmg9f8nut (Passando essa informação será listado somente a nossa database, caso contrário, será listado várias databases e você deverá buscar pela sua)
23 passo: Clique em Salvar e agora você está conectado ou seu banco de dados la do Heroku

Rodando o script para criação das tabelas
24 passo: Agora vá na sua database de62dgmg9f8nut criada, e clique com o botão direito e selecione Query Tool.
25 passo: Copie o conteúdo do seu arquivo sql baixo no passo 14, e cole dentro do campo Query Editor. Aperte Ctrl + A para selecionar todo o texto que você colou, e de um F5, e assim será criado todas as tabelas

Baixando Heroku CLI
26 passo: Dentro da sua conta Heroku no seu app, clique na aba Deploy, e na sessão Deploy using Heroku Git clique no link para instalar.
27 passo: feito a instalação abra um terminal do gitbash mesmo em alguma pasta e digite heroku -v, deverá rodar e retornar a versão do Heroku heroku/7.53.0 win32-x64 node-v12.21.0.

Associando o repositório local o Heroku - baseando em um deploy baseado no Git. Então vamos fazer um push para o Heroku, que após receber o push, fará um build da aplicação e a implementação
28 passo: Dentro da sua conta Heroku no seu app, clique na aba Deploy, e desça até Create a new repository Git e copie o trecho - $ heroku git:remote -a to-do-api-viceri.
29 passo: Abra um git bash na pasta do projeto, cole e execute. Para saber se teve sucesso digite git remote -v. Caso teve sucesso aparecerá que você o remote origin e o heroku

Definindo as váriaveis de configuração da aplicação do Heroku
30 passo: Dentro da sua conta Heroku no seu app, vá na aba Settings e clique em Reveal Config Vars
31 passo: Abaixo de DATABASE_URL  adicione 
	JWT_EXPIRATION   36000000       					  // tempo de expiração do token
	JWT_SECRET       52a77f39-8c69-4259-8336-34792694e71f			// chave secreta
32 passo: No arquivo application.properties altere para prod, caso esteja rodando no profile dev ou test
33 passo: Na pasta raiz do projeto crie um arquivo chamado system.properties, e adicione o valor dentro do arquivo: java.runtime.version=11

Enviando para o Heroku
34 passo: no git bash digite:
	git add . 
	git commit -m "Deploy" 
	git push heroku develop:main
35 passo: Vá na sua conta Heroku no app e clique em open app no canto direito superior, e te mandará para um enderço quer será a partir dele que faremos as requisições via postman
