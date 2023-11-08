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

- Francisco Costa - 2021146317 - a2021146317@isec.pt
- Artur Yurchuk - 2021130221 - a2021130221@isec.pt
- Lara Bizarro - 2021130066 - a2021130066@isec.pt
- Joana Ferrao - 2020131638 - a2020131638@isec.pt
- Ana Sofia Silva - 2021154586 - a2021154586@isec.pt

***

## Vision and Scope

#### Problem Statement

##### Project background

Currently, the only way to control expenses is through bank statements, which becomes a tedious and complex job.
For a student that started recently having their own expenses (rent ,university fees) and revenues (monthly allowances or salaries) can be hard to manage them in order to reach to the end of the month with some money left.

##### Stakeholders

###### Students
People who want to keep a detailed record of their expenses for better financial management. These expenses are distributed between living costs, personal expenses and additional one-off costs.
###### Parents
They may provide financial support to students and have an indirect interest in the efficiency of the software. It is in their interest that their financial contributions are used responsibly and that their child has financial stability and education.

###### Educational Institutions
Although they may not have a direct financial interest, they do have an interest in the general well-being of their students - it is an issue that can affect their academic performance and overall experience at the institution.
There are also some institutions that offer scholarships to certain students, according to their performance or contribution to it.

#### Users
Students.

***


#### Vision & Scope of the Solution

##### Vision statement

We see Expense Manager as a catalyst for change, helping users take control of their money by having expenses summaries, analyses and tracking, alowing to establish monthly budgets/limits.

##### List of features

Our main objectives are the following:

- Track expenses both card and physical money
- Categorize expenses (For example: Canteen meals, tuition fees, going out, expenses, etc.)
- Expense summary
- Expense analysis
- Establish budgets/limits mothly (reserve or not allow the user to spend more)
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

![Painel Inicial](imgs/pagina_inicial.png)

 Image 1: Homepage

![Adicionar despesas](imgs/adicionar_despesa.png)

 Image 2: Add expense (US1)

![Resultado adicionar despesas](imgs/visualizacao_pos_adicao_despesa.png)

 Image 3: Viewing the added expense (US1)

![Historico depois de adicionar despesa](imgs/despesas_historico_dps_adicionar.png)

 Image 4: Expense history (US10)

![Despesas por categotia](imgs/despesas_compras.png)

 Image 5: Expenses by category (US12)

![Despesas](imgs/despesas.png)

 Image 6: Expenses Homepage (US3)

![Gerir categorias](imgs/gerir_categorias.png)

 Image 7: Manage categories (add and delete) (US2)

![Budget](imgs/guardar_montante.png)

 Image 8: Budget homepage (US7, US13, US11, US9)

![Objetivos](imgs/objetivos.png)

 Image 9: Goals hompage (US6)

![Relatorio](imgs/relatorio.png)

 Image 10: Result of generating report (US4,US8)

![Reservar Montante](imgs/reservar_montantes.png)

 Image 11: Reserve Amounts (US5)

***

### User Stories

- User story 1 (tp-gps-11/gps_g11#2)
- User story 2 (tp-gps-11/gps_g11#3)
- User story 3 (tp-gps-11/gps_g11#7)
- User story 4 (tp-gps-11/gps_g11#8)
- User story 5 (tp-gps-11/gps_g11#9)
- User story 6 (tp-gps-11/gps_g11#10)
- User story 7 (tp-gps-11/gps_g11#11)
- User story 8 (tp-gps-11/gps_g11#12)
- User story 9 (tp-gps-11/gps_g11#13)
- User story 10 (tp-gps-11/gps_g11#14)
- User story 11 (tp-gps-11/gps_g11#15)
- User story 12 (tp-gps-11/gps_g11#16)
- User story 13 (tp-gps-11/gps_g11#17)

***
##### User Story 1
As a student, I want to be able to track my daily expenses,so that I can maintain an accurate record of my personal finances.
***
##### User Story 2
As a user, I want to manage categories so that I can organize my expenses according to different types.
***
##### User Story 3
As a student, I want to access expenses so that I can view all my recorded expenses.
***
##### User Story 4
As a student, I want to generate reports so that I can analyze send them or analize them later.
***
##### User Story 5
As a user, I want to reserve amounts for future expenses so that I can plan my budget.
***
##### User Story 6
As a user, I want to set goals so that I can work for a specific objective.
***
##### User Story 7
As a user, I want to see the monthly budget so that I can make adjustments as needed.
***
##### User Story 8
As a user, I want to extract data from reports in PDF and Excel, so that I can share and analyze my expenses.
***
##### User Story 9
As a student, I want to reset the monthly budget, which is the amount of money I have available per month, so that I can start a new period of financial control.
***
##### User Story 10
As a student, I want to view the transaction history so that I can review my past financial activities.
***
##### User Story 11
As a student, I want to view the use of my academic scholarship so that I can keep track of the scholarship usage and decide on the best way to manage it.
***
##### User Story 12
As a user, I want to view expenses by category, so that I can understand in which categories I spend the most money.
***
##### User Story 13
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

##### Threshold of Success
By the end of the second realease we must have all the user stories with "must" priorities.


##### Risk List


- RSK1 – PxI: 4x5=20; The team doesn't have any experience in this type of project, this can lead to delays in goals due to poorly calculated time estimates or communication failures.
- RSK2 – PxI: 3x4=12; There are constant simultaneous changes to the project, this can cause conflicts,complications or delays on the project. 
- RSK3 – PxI: 4x2=8; Taking into account that the group members are not professionals in this area, they may write code that uses excessive resources and encounter more bugs. This will force us to spend more time than necessary, leading to possible delays.

##### Mitigation Actions (threats>=20)
- RSK1 - AS; Perform regular check-ins twice a week during sprints and meetings;

***

## Pre-Game
### Sprint 0 Plan

- Goal: Plan the project, create an action plan and present the project to the client

- Dates: from 13/Oct to 27/Oct, 2 weeks

- Roles:
  - Product Owner: Francisco Costa
  - Scrum Master: Artur Yurchuck
  - Developer: Lara Bizarro, 
  	           Joana Ferrao, 
  	           Ana Sofia Silva.

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
  - Task14 - Fix week 4 feedback problems
  - Task15 - Create wiki page with weekly feedback
  - Task16 - Create wiki page with meeting agenda
  - Task17 - Plan sprint 1

  ***

## Release Plan

### Release 1

- Goal: MVP -> 
    - Track, Categorize and Analise expenses; 
    - Expense Summary;
    - Allow the criation of limits/budgets, in certain areas;
- Dates:[teams1] 30/Nov
- Release: V1.0

***

### Release 2

- Goal: Final release - Have a functional app with the missing features (Notifications).
- Date: [teams 0+1] 15/Dec
- Release: V2.0

***

## Increments

### Sprint 1
##### Sprint Plan

- Goal: User Interface that allows you to get an ideia of the inicial project, only with the simplest of methods as creation of new acounts, tracking and managing expenses, monthly budget and adding and categorize expenses.

- Roles:
  - Product Owner: Lara Bizarro
  - Scrum Master: Joana Ferrão

- Dates: from 27/Oct to 10/Nov | 17/Nov, 3 weeks

- To do (order by importance):

  - US 7: As a user, I want to create the monthly budget so that I can make adjustments as needed. [Story Points: L]
    - Task 1: Design user interface to allow budget entry
    - Task 2: Add budget methods
    - Task 3: Create budget validation methods
    - Task 4: Create Unit tests and fix remaining bugs

  - US 13: As a working student, I want to add money to my budget, so that I can keep the budget updated [Story Points: L]
    - Task 1: Design user interface 
    - Task 2: Add budget adding methods
    - Task 3: Create budget validation methods
    - Task 4: Create Unit tests and fix remaining bugs
    
  - US 1: As a student, I want to be able to track my daily expenses,so that I can maintain an accurate record of my personal finances.[Story Points: L]
    - Task 1: Design user interface to allow expense entry
    - Task 2: Add expenses managing methods
    - Task 3: Create expenses validation methods
    - Task 4: Create a display of the expense
    - Task 5: Create Unit tests and fix remaining bugs

  - US 2: As a user, I want to manage categories so that I can organize my expenses according to different types.[Story Points: L]
    - Task 1: Design user interface to allow category management
    - Task 2: Add category managing methods
    - Task 3: Create category validation methods
    - Task 4: Create Unit tests and fix remaining bugs 


- Story Points: 4L

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
