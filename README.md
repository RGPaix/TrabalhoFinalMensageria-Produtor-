# TrabalhoFinalMensageria-Produtor-
Produtor do trabalho final da disciplina de Mensageria e Streams em Aplicações

A presente aplicação de envio de mensagens meteorológicas permite, do lado do produtor, a criação de instâncias de um banco de dados (configurado no PostgreSQL) e a realização do CRUD das mesmas. Se comunica, através do RabbitMQ ao consumidor e permite o envio de mensagens (combinação das instâncias presentes no banco de dados). Infelizmente o código do produtor não está integrado ao JavaFX (embora os códigos das telas estejam presentes no projeto), então o CRUD e a comunicação com o consumidor se dá através de um menu do terminal. O código do consumidor, por sua vez, possui interface gráfica JavaFX feita no SceneBuilder.
