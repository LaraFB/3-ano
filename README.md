# Project Title

## Contents

- [Team](#team)
- [Vision and Scope](#vision-and-scope)
- [Requirements](#requirements)
    - [Use case diagram](#use-case-diagram)
    - [User stories and prototypes](#user-stories-and-prototypes)
- [Architecture and Design](#architecture-and-design)
    - [Domain Model](#domain-model)
- [Implementation](#implementation)
    - [Product Increment 1](#product-increment-1)
    - [Product Increment 2](#product-increment-2)
    - [Product Increment 3](#product-increment-3)
    - [Product Increment 4](#product-increment-4)

## Team

- Francisco Costa - 2021146317
- Artur Yurchuk - 2021130221
- Lara Bizarro - 2021130066
- Joana Ferrao - 2020131638
- Ana Sofia Silva - 2021154586

***

## Vision and Scope

#### Problem Statement

##### Project background


Gerir as finanças pessoais é uma parte essencial da vida quotidiana. Atualmente, de forma a controlar as despesas apenas
consegue através do extrato bancário, o que se torna um trabalho entediante e complexo. Numa tentativa de facilitar a
leitura dos gastos financeiros, procuramos desenvolver um Gestor de Despesas, uma solução intuitiva e fácil de usar, que
permite categorizar e visualizar um resumo das despesas do dia, do mês ou até do ano.

##### Stakeholders


###### Stakeholder Primário - Estudante Deslocado
Os estudantes são os principais beneficiários deste software, visto que afeta diretamente a sua capacidade de gerir as suas finanças enquanto vivem longe de casa.


###### Interessados Secundários: Pais / Responsáveis
Podem fornecer apoio financeiro aos estudantes e têm um interesse indireto na eficiência do software. É do seu interesse que as suas contribuições financeiras sejam usadas de maneira responsável e que o seu filho tenha estabilidade e educação financeira.


###### Stakeholders Terceários - Instituições de Ensino 
Embora possam não ter um interesse financeiro direto, têm interesse no bem-estar geral de seus estudantes - é um tema que pode afetar seu desempenho acadêmico e experiência geral na instituição.
Existem também algumas instituições que oferecem bolsas a certos estudantes, de acordo com o seu desempenho ou contribuição para com a mesma.


##### Users

O software “Gestor de Despesas” é projetado para estudantes deslocados - Pessoas que desejam
manter um registo detalhado de suas despesas para uma melhor gestão financeira. Estas despesas são distribuidas entre custos de vida (renda, compras de supermercado, serviços de água, eletrecidade, entre outros), despesas pessoas (jantares com amigos, idas a cafés, etc) e custos únicos adicionais (o estudante pode comprar manuais escolares durante o primeiro mês de aulas).


#### Vision & Scope of the Solution

##### Vision statement

Com Gestor de Despesas procuramos um mundo onde a gestão financeira é facilmente acessível. Aspiramos criar um software
que sirva como um companheiro financeiro confiável, simplificando as complexidades do acompanhamento financeiro.
Vemos o Gestor de Despesas como um catalisador de mudanças, ajudando os utilizadores a assumir o controlo do seu
dinheiro, reduzindo o stress financeiro e ajudando a criar e cumprir objetivos impostos pelos próprios utilizadores.
A nossa visão é capacitar as pessoas a abraçarem um futuro financeiro mais controlado, onde a gestão de despesas não
seja um fardo, mas uma oportunidade de crescimento, estabilidade e prosperidade.

##### List of features

Os nossos principais objetivos são os seguintes:

- Acompanhar as despesas
- Categorizar as despesas (Por exemplo: Refeições na cantina, propinas, "going out", despesas, etc.)
- Resumo de despesas
- Análise de despesas
- Estabelecer budgets/limites em certas áreas (reservar ou não permitir que o utilizador gaste mais)
- Notificar o utilizador quando os limites estiverem próximos de serem ultrapassados  

##### Features that will not be developed

Associar um cartão e uma conta bancária, uma vez que exige permissões que não temos (pretendemos resolver esta questão criando um budget que é introduzido pelo utilizador e adiciona a cada mês o mesmo valor. Irá exigir que o utilizador tome o cuidado de registar todas as operações financeiras efetuadas.)

##### Risk

#### Segurança e privacidade
Como pretendemos trabalhar com informação sensível temos um grande risco de violações de dados ou de privacidade, o que poderia pôr em causa a fidelidade do nosso software. Desta forma, a implementação de protocolos de segurança rigorosos, criptologia e a conformidade com leis de proteção de dados são essenciais.

#### Marketing
Outro risco será a adoção dos estudantes, por outras palavras, a falta de conhecimento sobre o software, uma vez que existem imensas aplicações financeiras. Para combater isto, pretendemos fazer parcerias com instituições, adotar medidas eficazes de marketing.

##### Assumptions

Supõe-se:
- que os estudantes utilizem o software ativamente para acompanhar despesas, definir orçamentos e trabalhar em direção aos seus objetivos.
- que os utilizadores fornecerão valores precisos, como renda e despesas.
-  a demanda de um produto financeiro dedicado às necessidades dos estudantes.
- que a infraestrutura do software será segura e resistente a violações de dados.
- que o software cumprirá todas as regulamentações e leis financeiras relevantes.
- O software pressupõe a necessidade de atualizações e melhorias contínuas para se adaptar às mudanças no cenário financeiro e às necessidades dos utilizadores.

***

## Requirements

#### Use Case Diagram

![Use case diagram](imgs/UML_use_case_example-800x707.png)

***

##### Use Case 1

- Actor: Actor x
- Description: Description of use case number 1
- Preconditions:
    - Precondition 1
    - Precondition 2
    - Without preconditions
- Postconditions:
    - Postcondition 1
    - Postcondition 2
    - Without postcondition
- Normal flow:
    - The user ...
    - The user ...
- Alternative flows:
    - The user ...
    - The user ...

***

##### Use Case 2

***

##### Use Case 3

***

#### User Stories and Prototypes

***

##### User Story 1

As a new user,
I want to be able to create a new account,
So that I can start using it and mange my finances effectively.

(Como um novo utilizador. desejo poder registar-me para puder usar o software).

###### Acceptance Criteria

```
Quando abrir o software, devo ver uma opção clara e intuitiva para fazer o login ou para registar uma nova conta. Durante o processo de registo, devo ser pedido o meu nome, endereço de e-mail e criar uma senha segura com verificação. Após fornecer as informações necessárias, devo receber um e-mail de verificação para confirmar minha conta. Após clicar no link de verificação no e-mail, devo receber uma mensagem de confirmação no software, indicando que minha conta está ativa. Não devo ser capaz de aceder aos recursos do software até que minha conta seja verificada. Se encontrar qualquer problema durante o login, como um e-mail ou senha inválidos, devo receber mensagens de erro claras me orientando sobre como resgatar a palavra-passe.
```

###### Prototype

A prototype of user story 1 should be here. You can see in (#use-case-diagram) how to import an image.

***

##### User Story 2

As a student, I want to be able to track my daily expenses and categorize them,so that I can gain insights into my spending habits.

(Como estudante, quero ser capaz de controlar as minhas despesas diárias e categorizá-las, para saber mais sobre os meus gastos diários).

###### Acceptance Criteria

```
Após fazer o login, devo ter um painel inicial que mostre o meu balanço semanal, de forma a mostrar o dinheiro que tenho reservado para aquela semana, assim como um balanço do que já gastei. Além disso, o software deve ter uma opção bem vísivelpara inserir as minhas despesas. Devo ser capaz de inserir o valor gasto, a data, uma breve descrição e escolher uma categoria existente (por exemplo, alimentação, refeições na cantina, propinas, transporte, ginásio) ou criar uma nova. Devo ser capaz de visualizar gráficos ou informação clara e curta em cada categoria sobre os balanços mensais e semanais. Devo receber lembretes se exceder os limites de gastos predefinidos.
```

###### Prototype

A prototype of user story 2 should be here. You can see in (#use-case-diagram) how to import an image.

***

##### User Story 3

As a student, I want to analyze my spending patterns, So that I can better understand where my money is going and make informed financial decisions.

(Como estudante, quero analisar os meus gastos, para entender para onde vai o meu dinheiro e tomar melhores decisões).

###### Acceptance Criteria

```
Após fazer o login, devo ter um menu que me permita efetuar diversas operações, como por exemplo ver o conjunto de todas as despesas, ver as despesas por categoria, ver uma breve análise. Nessa análise, posso escolher o intervalos de datas que pretendo (o último mês, o último semestre, a última semana, hoje, ou datas personalizadas). O software deve fazer um resumo das minhas despezas, exibir um pequeno texto que me informe das minhas despesas (por exemplo, este mês gastou mais 50€ em compras, para melhorar tente fazer uma lista do que realmete precisa e obrigue-se a segui-la). Poderá também exiber algund gráficos ou imagens representivas. 
```

###### Prototype

A prototype of user story 3 should be here. You can see in (#use-case-diagram) how to import an image.
***

## Architecture and Design

#### Domain Model

A domain model should be here. You can see in (#use-case-diagram) how to import an image.

***

## Risk Plan

##### Threshhold of Success
- By the 2nd release date, 30% of all the User Stories from the Product Backlog are closed. (for example)
- Goal2
- Goal3

##### Risk List

###### RSK1 Falta de Experiencia

A falta de experiência da equipa neste tipo de projetos pode levar a atrasos em metas por estimativas de tempo mal calculadas ou falhas de comunicação.

###### RSK2 Alterações constantes e conflitos

Alterações constantes ao projeto podem vir a causar complicações ou atrasos principalmente por conflitos que possam surgir a cada nova alteração elaborada em simultaneo pelos vários elementos da equipa.

###### RSK3 Bugs e uso excessivo de recursos
Bugs que ocorram durante a realização do projeto obrigar-nos-ão a dispender mais tempo do que o necessário levando a envetuais atrasos. Uso excessivo de recursos terá este mesmo efeito por alterações de última hora com o objetivo de diminuir o seu uso.

###### RSK4 Segurança e privacidade

Como pretendemos trabalhar com informação sensível temos um grande risco de violações de dados ou de privacidade, o que poderia pôr em causa a fidelidade do nosso software. Desta forma, a implementação de protocolos de segurança rigorosos, criptologia e a conformidade com leis de proteção de dados são essenciais. 

###### RSK5 Marketing

Outro risco será a adoção dos estudantes, por outras palavras, a falta de conhecimento sobre o software, uma vez que existem imensas aplicações financeiras. Para combater isto, pretendemos fazer parcerias com instituições, adotar medidas eficazes de marketing.



##### Mitigation Actions (threats>=20)
- RSK1 - AS; Efetuar check-ins regulares durante os sprints, e reuniões;
- RSK2 - AS; Panificar bem o projeto, para evitar qualquer alteração futura;
- RSK3 - AS; Comunicar com os membros do grupo, para que estes ajudem a manter o código coroente e sem bugs;
- RSK4 - CP; Efetuar reuniões todas as semanas para averiguar o progresso de todos os membros;
- RSK5 - MS; Gerir de forma eficiente o tempo entre todos os membros, de forma a implementar o maior numero de funcionalidades possiveis.

***

## Pre-Game

### Sprint 0 Plan

- Goal: description
- Dates: from 10-13/Oct to 24-27/Oct, 2 weeks
- Sprint 0 Backlog (don't edit this list):
  - Task1 – Write Team
  - Task2 – Write V&S
  - Task3 – Write Requirements
  - Task4 – Write DoD
  - Task5 – Write Architecture&Design
  - Task6 – Write Risk Plan
  - Task7 – Write Pre-Gane
  - Task8 – Write Release Plan
  - Task9 – Write Product Increments
  - Task10 – Create Product Board
  - Task11 – Create Sprint 0 Board
  - Task12 – Write US in PB, estimate (SML), prioritize (MoSCoW), sort
  - Task13 – Create repository with “GPS Git” Workflow

***

## Release Plan

### Release 1

- Goal: UI basica, modelo inicial a ser trabalhado, metodos mais basicos
- Dates: 
- Release: V1.0

***

### Release 2

- Goal: Grande parte dos metodos realizados
- Date: 
- Release: V2.0

***

### Release 3

- Goal: UI final, metodos completos e correção de  possiveis bugs
- Date: 
- Release: V3.0

***

***

## Implementation

#### Product Increment 1

##### Sprint Goal

The sprint goal was ...

##### Planned vs Implemented

For this iteration we planned to implement the:

- Feature 1
- Feature 2

For this iteration we implemented the:

- Feature 1
- Feature 2

##### Sprint Retrospective

- What we did well:
    - A
- What we did less well:
    - B
- How to improve to the next sprint:
    - C

***

#### Product Increment 2

***

#### Product Increment 3

***

#### Product Increment 4

***
