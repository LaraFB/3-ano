# Gestor de despesas

## Contents

- [Team](#team)
- [Vision and Scope](#vision-and-scope)
- [Requirements](#requirements)
    - [Use case diagram](#use-case-diagram)
    - [Mockups](#mockups)
    - [User stories](#user-stories)
- [Definition of Done](#definition-of-done)
- [Architecture and Design](#architecture-and-design)
    - [Domain Model](#domain-model)
- [Risk Plan](#risk-plan)
- [Pre-Game](#pre-game)
- [Release Plan](#release-plan)
  - [Release 1](#release-1)
  - [Release 2](#release-2)
- [Increments](#increments)
  - [Sprint 1](#sprint-1)
  - [Sprint 2](#sprint-2)
  - [Sprint 3](#sprint-3)

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

Currently, the only way to control expenses is through bank statements, which becomes a tedious and complex job.
In an attempt to make it easier to read financial expenses, we sought to develop an Expense Manager - an intuitive and easy-to-use solution - that
allows you to categorize and view a summary of expenses for the day, month or even the year.


##### Stakeholders

###### Students
Students are the main beneficiaries of this software, as it directly affects their ability to manage their finances.

###### Parents
They may provide financial support to students and have an indirect interest in the efficiency of the software. It is in their interest that their financial contributions are used responsibly and that their child has financial stability and education.

###### Educational Institutions
Although they may not have a direct financial interest, they do have an interest in the general well-being of their students - it is an issue that can affect their academic performance and overall experience at the institution.
There are also some institutions that offer scholarships to certain students, according to their performance or contribution to it.

#### Users
Students (People who want to keep a detailed record of their expenses for better financial management. These expenses are distributed between living costs, personal expenses and additional one-off costs).

***


#### Vision & Scope of the Solution

##### Vision statement

We see Expense Manager as a catalyst for change, helping users take control of their money, reducing financial stress and helping to create and meet user-imposed goals.

##### List of features

Our main objectives are the following:

- Track expenses
- Categorize expenses (For example: Canteen meals, tuition fees, going out, expenses, etc.)
- Expense summary
- Expense analysis
- Establish budgets/limits in certain areas (reserve or not allow the user to spend more)
- Notify the user when limits are close to being exceeded

##### Features that will not be developed
Link a card and bank account- Since it requires permissions that we do not have (we intend to resolve this issue by creating a budget that is entered by the user and adds the same amount each month. It will require the user to take care to record all financial operations carried out.)


##### Assumptions

It is assumed:
- Students actively use the software to track expenses, set budgets, and work toward their goals.
- Users will provide accurate values such as income and expenses.
- The demand for a financial product dedicated to the needs of students.
- The software infrastructure will be secure and resistant to data breaches.
- The software will comply with all relevant financial regulations and laws.
- The software assumes the need for continuous updates and improvements to adapt to changes in the financial scenario and user needs.

***

## Requirements

### Use Case Diagram

![Use case diagram](imgs/UseCaseDiagram.png)

***

### Mockups

![Sign up](imgs/login.png)

 Figura 1: Login (US1)

![Painel Inicial](imgs/pagina_inicial.png)

 Figura 2: Homepage

![Adicionar despesas](imgs/adicionar_despesa.png)

 Figura 3: Add expense (US2)

![Resultado adicionar despesas](imgs/visualizacao_pos_adicao_despesa.png)

 Figura 4: Viewing the added expense (US2)

![Historico depois de adicionar despesa](imgs/despesas_historico_dps_adicionar.png)

 Figura 5: Expense history (US11)

![Despesas por categotia](imgs/despesas_compras.png)

 Figura 6: Expenses by category (US13)

![Despesas](imgs/despesas.png)

 Figura 7: Expenses Homepage (US4)

![Gerir categorias](imgs/gerir_categorias.png)

 Figura 8: Manage categories (add and delete) (US3)

![Budget](imgs/guardar_montante.png)

 Figura 9: Budget homepage (US8, US14, US12, US10)

![Objetivos](imgs/objetivos.png)

 Figura 10: Goals hompage (US7)

![Relatorio](imgs/relatorio.png)

 Figura 11: Result of generating report (US5,US9)

![Reservar Montante](imgs/reservar_montantes.png)

 Figura 12: Reserve Amounts (US6)

***

### User Stories

- User story 1 (tp-gps-11/gps_g11#1)
- User story 2 (tp-gps-11/gps_g11#2)
- User story 3 (tp-gps-11/gps_g11#3)
- User story 4 (tp-gps-11/gps_g11#7)
- User story 5 (tp-gps-11/gps_g11#8)
- User story 6 (tp-gps-11/gps_g11#9)
- User story 7 (tp-gps-11/gps_g11#10)
- User story 8 (tp-gps-11/gps_g11#11)
- User story 9 (tp-gps-11/gps_g11#12)
- User story 10 (tp-gps-11/gps_g11#13)
- User story 11 (tp-gps-11/gps_g11#14)
- User story 12 (tp-gps-11/gps_g11#15)
- User story 13 (tp-gps-11/gps_g11#16)
- User story 14 (tp-gps-11/gps_g11#17)

***
##### User Story 1
As a new user, I want to be able to create a new account, So that I can start using it and mange my finances effectively.
***
##### User Story 2
As a student, I want to be able to track my daily expenses,so that I can maintain an accurate record of my personal finances.
***
##### User Story 3
As a user, I want to manage categories so that I can organize my expenses according to different types.
***
##### User Story 4
As a student, I want to access expenses so that I can view all my recorded expenses.
***
##### User Story 5
As a student, I want to generate reports so that I can analyze send them or analize them later.
***
##### User Story 6
As a user, I want to reserve amounts for future expenses so that I can plan my budget.
***
##### User Story 7
As a user, I want to set goals so that I can work for a specific objective.
***
##### User Story 8
As a user, I want to see the monthly budget so that I can make adjustments as needed.
***
##### User Story 9
As a user, I want to extract data from reports in PDF and Excel, so that I can share and analyze my expenses.
***
##### User Story 10
As a student, I want to reset the monthly budget, which is the amount of money I have available per month, so that I can start a new period of financial control.
***
##### User Story 11
As a student, I want to view the transaction history so that I can review my past financial activities.
***
##### User Story 12
As a student, I want to view the use of my academic scholarship so that I can keep track of the scholarship usage and decide on the best way to manage it.
***
##### User Story 13
As a user, I want to view expenses by category, so that I can understand in which categories I spend the most money.
***
##### User Story 14
As a working student, I want to add money to my budget, so that I can keep the budget updated.
***
## Definition of done

(This section is already written, do not edit)
It is a collection of criteria that must be completed for a User Story to be considered “done.”

1. All tasks done:
  - CI – built, tested (Junit), reviewed (SonarCloud)
  - Merge request to qa (code review)
2. Acceptance tests passed
3. Accepted by the client
4. Code merged to main

***

## Architecture and Design

#### Domain Model

![Domain Model](imgs/domain_model.png)

***

## Risk Plan

##### Threshhold of Success
For the project to be considered "successful" we must have:
  - Expense Tracking
  - categorization, summary and analysis of expenses
  - Allows you to establish budgets/limits in certain areas


##### Risk List


- RSK1 – PxI: 4x5=20; The team doesn't have any experience in this type of project, this can lead to delays in goals due to poorly calculated time estimates or communication failures.
- RSK2 – PxI: 3x4=12; Constant changes to the project can cause complications or delays, mainly due to conflicts that may arise with each new change prepared simultaneously by the various team members.
- RSK3 – PxI: 4x=8; Taking into account that the group members are not professionals in this area, they may write code that uses excessive resources and encounter more bugs. This will force us to spend more time than necessary, leading to possible delays.
- RSK4 - PxI: 4x5=20; The group's members are inexperienced in the area of security and privacy. As we intend to work with sensitive information, we have a high risk of data or privacy breaches, which could jeopardize the fidelity of our software. 
- RSK5 - PxI: 5x5=25; The software will be useless if it is not used. 
- RSK 6 - PxI: 4*5=20; We have a limited amount of time, so we might not be able to finish all the features that we promised.

##### Mitigation Actions (threats>=20)
- RSK1 - AS; Perform regular check-ins during sprints and meetings;
- RSK2 - AS; Pan the project well to avoid any future changes;
- RSK3 - AS; Communicate with group members, so that they help keep the code consistent and bug-free;
- RSK4 - CP; Hold meetings every week to check the progress of all members;
- RSK5 - MS; Efficiently manage time among all members, in order to implement as many features as possible.
- RSK6 - MS; COnstantly check the progress of the project, make regular meetings and keep the client updated in possible changes (features that won't be possible to make).

***

## Pre-Game
### Sprint 0 Plan

- Goal: Plan the project, create an action plan and present the project to the client

- Dates: from 10-13/Oct to 24-27/Oct, 2 weeks

- Roles:
  - Product Owner: Francisco Costa
  - Scrum Master: Artur Yurchuck
  - Developer: Lara Bizarro
  	           Joana Ferrao
  	           Ana Sofia Silva
  
- Story Points: 2S+3M+3X
- Prioritize: MSC

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

- Goal: MVP -> 
    - Acompanhamento,categorização, resumo e análise de despesas 
    - permitir estabelecer budgets/limites em certas áreas
- Dates: [teams 0] 21-24/Nov | [teams1] 28-30/Nov
- Release: V1.0

***

### Release 2

- Goal: Final release – description​
- Date: [teams 0+1] 12-15/Dec
- Release: V2.0

***

## Increments

### Sprint 1
##### Sprint Plan

- Goal: User Interface que permita ter um ideia inicial do projeto, com os metodos mais simples
- Roles:
  - Product Owner: name
  - Scrum Master: name
- Dates: from 24-27/Oct to 7-10/Nov | 14-17/Nov, 2 | 3 weeks

- Roles:
  - Product Owner: name
  - Scrum Master: name

- To do:
  - (list of US or Tasks from the PB)
  - US1: As … I want … so that …
  - Task1: Some task
  
- Story Points: 2S+3M+3X+2H

- Analysis: short analysis of the planning meeting

##### Sprint Review

- Analysis: what was not done or what was added (Link to US or Task from the PB)

- Story Points: 2S+1M+2X+2H

- Version: 0.1 

- Client analysis: client feedback

- Conclusions: what to add/review

##### Sprint Retrospective

- What we did well:
    - A
- What we did less well:
    - B
- How to improve to the next sprint:
    - C

***

#### Sprint 2

##### Sprint Plan


- Goal: Metodos mais complexos, melhorando e adicionando alguns aspetos de user interface 

- Roles:
  - Product Owner: name
  - Scrum Master: name
- Dates: from 24-27/Oct to 7-10/Nov | 14-17/Nov, 2 | 3 weeks

- Roles:
  - Product Owner: name
  - Scrum Master: name

- To do:
  - (list of US or Tasks from the PB)
  - US1: As … I want … so that …
  - Task1: Some task
  
- Story Points: 2S+3M+3X+2H

- Analysis: short analysis of the planning meeting

##### Sprint Review

- Analysis: what was not done or what was added (Link to US or Task from the PB)

- Story Points: 2S+1M+2X+2H

- Version: 0.1 

- Client analysis: client feedback

- Conclusions: what to add/review

##### Sprint Retrospective

- What we did well:
    - A
- What we did less well:
    - B
- How to improve to the next sprint:
    - C


***

#### Sprint 3

##### Sprint Plan

- Goal: User Interfeca aperfeiçoada, terminar todos os metodos em falta, e corrigir bugs que possam existir 

- Roles:
  - Product Owner: name
  - Scrum Master: name
- Dates: from 24-27/Oct to 7-10/Nov | 14-17/Nov, 2 | 3 weeks

- Roles:
  - Product Owner: name
  - Scrum Master: name

- To do:
  - (list of US or Tasks from the PB)
  - US1: As … I want … so that …
  - Task1: Some task
  
- Story Points: 2S+3M+3X+2H

- Analysis: short analysis of the planning meeting

##### Sprint Review

- Analysis: what was not done or what was added (Link to US or Task from the PB)

- Story Points: 2S+1M+2X+2H

- Version: 0.1 

- Client analysis: client feedback

- Conclusions: what to add/review

##### Sprint Retrospective

- What we did well:
    - A
- What we did less well:
    - B
- How to improve to the next sprint:
    - C


***
