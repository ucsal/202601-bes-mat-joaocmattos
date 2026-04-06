# Olimpíada de Questões - Refatoração SOLID

Este projeto consiste na refatoração de um sistema legado de gestão de olimpíadas acadêmicas e exercícios de xadrez, aplicando os princípios de design de software SOLID.
Sobre o Projeto

O sistema original apresentava uma arquitetura centralizada na classe App.java, que acumulava responsabilidades de interface de usuário (I/O), lógica de negócio e persistência de dados. A refatoração buscou a descentralização dessas responsabilidades, promovendo um código modular, testável e de fácil manutenção.
Princípios Aplicados

A reestruturação foi guiada pelos cinco pilares do SOLID:

    S – Single Responsibility Principle (SRP):
    Cada classe possui uma única razão para mudar. A lógica de entrada e saída foi movida para o pacote ui, a persistência para o pacote repository e a lógica de exibição do tabuleiro para a classe ConsoleChessRenderer.

    O – Open/Closed Principle (OCP):
    O sistema foi projetado para ser estensível sem a necessidade de modificação do código fonte original. Através de interfaces como CalculadoraNota e TabuleiroRenderer, novas regras de negócio ou formas de visualização podem ser adicionadas apenas criando novas implementações.

    L – Liskov Substitution Principle (LSP):
    As abstrações de repositório permitem que qualquer implementação (como InMemoryParticipanteRepository) substitua a interface Repository sem alterar o comportamento esperado pelo OlimpiadaService.

    I – Interface Segregation Principle (ISP):
    Interfaces granulares e específicas (ex: EntradaDados, TabuleiroRenderer) garantem que as classes clientes dependam apenas dos métodos que efetivamente utilizam.

    D – Dependency Inversion Principle (DIP):
    O OlimpiadaService não depende mais de implementações concretas ou estados globais. As dependências são injetadas via construtor, dependendo exclusivamente de interfaces (abstrações).

Estrutura de Pacotes
Plaintext

src/main/java/br/com/ucsal.olimpiadas/
├── domain/       # Entidades de domínio (Participante, Prova, Questão...)
├── repository/   # Interfaces de persistência e implementações em memória
├── service/      # Regras de negócio, cálculos e orquestração
├── ui/           # Componentes de interface de usuário e renderizadores
└── App.java      # Classe principal responsável pelo bootstrap do sistema

Como executar

    Requisito: JDK 17 ou superior.

    Clone o repositório.

    Utilize o Maven para compilar e executar:
    Bash

    mvn compile
    mvn exec:java -Dexec.mainClass="br.com.ucsal.olimpiadas.App"

Objetivo Acadêmico: Demonstrar a transição de um paradigma de programação procedural para uma arquitetura orientada a objetos robusta, utilizando padrões de projeto que facilitam a escalabilidade do software.