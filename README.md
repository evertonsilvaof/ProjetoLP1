Pet System

Sistema de Gestão Integrada para Clínicas Veterinárias e Pet Shops

Projeto para a disciplina de Linguagem de Programação I
Curso de Sistemas para Internet - UNIESP
sob orientação da Professora Fernanda Dias.

Sobre o Projeto:
O Pet System é uma aplicação em Java desenvolvida para resolver o problema da desorganização no gerenciamento de informações em ambientes veterinários. 

Tecnologias Utilizadas:

Java (JDK 21): Linguagem principal.
IntelliJ IDEA: Ambiente de desenvolvimento (IDE).
Git/GitHub: Versionamento de código.

Conceitos: Programação Orientada a Objetos (POO), Listas (ArrayList), Tratamento de Exceções.

Funcionalidades (Requisitos Funcionais)

O sistema atende aos seguintes requisitos propostos no Backlog:

[RF001] Cadastro de Tutores
[RF002] Cadastro de Animais (com vínculo ao Tutor)
[RF003] Cadastro de Veterinários
[RF004] Agendamento de Consultas
[RF005] Registro de Atendimentos Veterinários
[RF006] Módulo de Vacinação
[RF007] Marcar Animal para Adoção
[RF008] Conclusão de Adoção (Transferência de Tutor)
[RF009] Cadastro de Produtos
[RF010] Vendas com Baixa Automática de Estoque
[RF011] Relatórios de Estoque e Vendas
[RF012] Cadastro de Denúncias
[RF013] Atualização de Status de Denúncias
[RF014] Relatórios de Denúncias por Status
[RF015] Alerta de Reincidência de Denúncias (por Localização)
[RF016] Menu Interativo via Console

Estrutura do Projeto

A organização das pastas segue o padrão de camadas:
PetSystem/
├── src/
│   ├── controller/       # Camada de Controle (PetController)
│   ├── model/            # Camada de Modelo (Entidades: Tutor, Animal, etc.)
│   ├── Service/          # Camada de Serviço (Regras de Negócio: PetService)
│   └── Main.java         # Ponto de Entrada (Inicia o Sistema)


Como Executar

Faça o Dowload do Projeto 
Abra o projeto no seu IDE favorito (recomendado: IntelliJ IDEA).
Localize a classe Main.java na pasta src.
Execute a classe Main.
Interaja com o sistema através do Menu no Console.

Autor:
Everton Fernandes.
