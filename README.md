# PetCare Manager

Projeto inicial em Java Orientado a Objetos para gerenciamento simples de uma clínica veterinária.

O objetivo é que os alunos partam de uma base pronta e implementem novas funcionalidades de forma independente, usando branches, commits e Pull Requests no GitHub.

## Objetivos de aprendizagem

- Criar classes e objetos em Java
- Aplicar encapsulamento
- Trabalhar com herança
- Trabalhar com polimorfismo
- Organizar o projeto em pacotes
- Criar serviços e repositórios simples
- Praticar Git e GitHub com branches e Pull Requests

## Estrutura do projeto

```text
petcare-manager/
├── src/
│   └── br/
│       └── com/
│           └── petcare/
│               ├── Main.java
│               ├── model/
│               │   ├── Animal.java
│               │   ├── Cachorro.java
│               │   ├── Consulta.java
│               │   ├── Gato.java
│               │   └── Tutor.java
│               ├── repository/
│               │   └── BancoMemoria.java
│               └── service/
│                   └── ClinicaService.java
└── README.md
```

## Como executar

Na pasta raiz do projeto, execute:

```bash
javac -d out $(find src -name "*.java")
java -cp out br.com.petcare.Main
```

No Windows PowerShell, pode ser necessário compilar assim:

```powershell
javac -d out (Get-ChildItem -Recurse -Filter *.java src).FullName
java -cp out br.com.petcare.Main
```

## Funcionalidades iniciais

O projeto já possui:

- Cadastro de tutores
- Cadastro de cachorros
- Cadastro de gatos
- Agendamento de consultas
- Listagem de tutores
- Listagem de animais
- Listagem de consultas
- Exemplo de herança com `Animal`, `Cachorro` e `Gato`
- Exemplo de polimorfismo com o método `emitirSom()`

## Fluxo recomendado com Git

Cada aluno ou grupo deve criar uma branch para sua feature:

```bash
git checkout -b feature/nome-da-feature
```

Depois de implementar, deve realizar commits:

```bash
git add .
git commit -m "feat: implementa controle de vacinação"
```

Por fim, deve abrir um Pull Request no GitHub.

---

# Features independentes para implementação

As features abaixo foram pensadas para serem desenvolvidas de forma independente. Cada grupo pode implementar uma delas sem depender diretamente da implementação dos demais.

## Feature 01 — Menu interativo no terminal

Criar um menu usando `Scanner`, permitindo que o usuário escolha opções como:

- Cadastrar tutor
- Cadastrar cachorro
- Cadastrar gato
- Listar animais
- Listar consultas
- Agendar consulta
- Sair

Sugestão de branch:

```bash
git checkout -b feature/menu-interativo
```

## Feature 02 — Validações de cadastro

Adicionar validações nos cadastros.

Regras sugeridas:

- Nome do tutor não pode ser vazio
- Telefone não pode ser vazio
- Nome do animal não pode ser vazio
- Idade do animal não pode ser negativa
- Consulta não pode ser agendada sem animal
- Motivo da consulta não pode ser vazio

Sugestão de branch:

```bash
git checkout -b feature/validacoes-cadastro
```

## Feature 03 — Busca de animais

Criar métodos para buscar animais por:

- Nome
- Espécie
- Nome do tutor

Exemplo esperado:

```java
List<Animal> buscarAnimaisPorNome(String nome);
List<Animal> buscarAnimaisPorEspecie(String especie);
List<Animal> buscarAnimaisPorNomeTutor(String nomeTutor);
```

Sugestão de branch:

```bash
git checkout -b feature/busca-animais
```

## Feature 04 — Controle de vacinação

Criar uma classe `Vacina` e associar vacinas aos animais.

A classe pode conter:

- ID
- Nome da vacina
- Data de aplicação
- Data da próxima dose
- Animal

Sugestão de branch:

```bash
git checkout -b feature/controle-vacinacao
```

## Feature 05 — Prontuário do animal

Criar uma classe `Prontuario` para registrar histórico clínico dos animais.

A classe pode conter:

- ID
- Animal
- Data do registro
- Descrição
- Nome do veterinário

Sugestão de branch:

```bash
git checkout -b feature/prontuario-animal
```

## Feature 06 — Cadastro de serviços

Criar uma classe `ServicoClinica` para representar serviços oferecidos pela clínica.

Exemplos:

- Consulta
- Banho
- Tosa
- Vacinação
- Cirurgia

A classe pode conter:

- ID
- Nome
- Descrição
- Valor
- Duração em minutos

Sugestão de branch:

```bash
git checkout -b feature/cadastro-servicos
```

## Feature 07 — Cálculo de pagamento

Criar uma forma de calcular o valor total de serviços realizados.

Pode ser criada uma classe `Atendimento` contendo:

- ID
- Animal
- Lista de serviços
- Data
- Valor total

Sugestão de branch:

```bash
git checkout -b feature/calculo-pagamento
```

## Feature 08 — Status da consulta

Adicionar status para as consultas.

Sugestão: criar um `enum StatusConsulta` com valores:

- AGENDADA
- REALIZADA
- CANCELADA

A consulta deve iniciar como `AGENDADA`.

Sugestão de branch:

```bash
git checkout -b feature/status-consulta
```

## Feature 09 — Cancelamento de consulta

Criar funcionalidade para cancelar uma consulta pelo ID.

Regras sugeridas:

- Não remover a consulta da lista
- Apenas alterar o status para `CANCELADA`
- Não permitir cancelar consulta já realizada

Esta feature pode ser feita de forma simples, mesmo se a Feature 08 ainda não estiver pronta, criando um atributo `cancelada` temporário na classe `Consulta`.

Sugestão de branch:

```bash
git checkout -b feature/cancelamento-consulta
```

## Feature 10 — Relatório geral da clínica

Criar uma classe ou método para exibir um relatório com:

- Total de tutores cadastrados
- Total de animais cadastrados
- Total de consultas cadastradas
- Total de cachorros
- Total de gatos

Sugestão de branch:

```bash
git checkout -b feature/relatorio-geral
```

## Feature 11 — Cadastro de veterinários

Criar uma classe `Veterinario`.

A classe pode conter:

- ID
- Nome
- CRMV
- Especialidade

Depois, permitir associar um veterinário a uma consulta.

Sugestão de branch:

```bash
git checkout -b feature/cadastro-veterinarios
```

## Feature 12 — Persistência simples em arquivo

Salvar e carregar dados em arquivo `.txt` ou `.csv`.

Sugestão inicial:

- Salvar tutores em `tutores.csv`
- Salvar animais em `animais.csv`
- Salvar consultas em `consultas.csv`

Esta feature pode começar salvando apenas uma entidade, como tutores, e depois evoluir.

Sugestão de branch:

```bash
git checkout -b feature/persistencia-arquivo
```

---

## Critérios de avaliação sugeridos

- Implementação funcional da feature
- Uso adequado de classes e objetos
- Encapsulamento dos atributos
- Organização em pacotes
- Clareza dos nomes de classes, métodos e variáveis
- Commits organizados
- Pull Request com descrição da alteração
- Código executando sem erros
