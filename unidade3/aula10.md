### U3 - Aula 10 - 29/05/2026 (2,0) - Herança

### 1. Conceitos

- **Herança** (`extends`): mecanismo pelo qual uma subclasse absorve atributos e métodos de uma superclasse. `Aluno extends Pessoa` significa que `Aluno` **é uma** `Pessoa` — tem `nome` e `endereco` sem redeclará-los, e acrescenta `matricula`. A relação é unidirecional: a superclasse não conhece as subclasses.

  O construtor da subclasse **deve** chamar `super(...)` como primeira instrução para inicializar a parte herdada — caso contrário o compilador rejeita.

  ```java
  public Aluno(String nome, Endereco endereco, String matricula) {
      super(nome, endereco); // inicializa Pessoa
      this.matricula = matricula;
  }
  ```

  Herança deve modelar uma relação semântica real de identidade. O teste: "um `Aluno` **é uma** `Pessoa`?" — sim. "Um `Estoque` **é um** `Produto`?" — não; nesse caso usa-se composição.

- **`@Override`**: anotação que indica que o método sobrescreve um da superclasse, e o compilador valida.

- **Composição** (`o--`): `Pessoa` **tem um** `Endereco`. Modela posse, não identidade.

- **`enum`**: tipo com valores fixos. `Estado.RN` é mais seguro que `"RN"` (evita-se números mágicos).

### Pessoa com Herança:

```mermaid
classDiagram
direction BT

class Pessoa {
    - nome: String
    - endereco: Endereco
    + Pessoa(nome, endereco)
    // get, set, toString()
}

class Aluno {
    - matricula: String
    + Aluno()
    + Aluno(nome, endereco, matricula)
    // get, set, toString()
}

class Professor {
    - salario: double
    + Professor()
    + Professor(nome, endereco, salario)
    // get, set, toString()
}

Aluno --|> Pessoa : é_uma
Professor --|> Pessoa : é_uma
Pessoa "1" o-- "1" Endereco : tem_um
```

### Endereço:

```mermaid
classDiagram
direction LR

class Endereco {
    - rua: String
    - bairro: String
    - cidade: String
    - estado: Estado
    // get, set, toString()
}

class Estado {
    <<enumeration>>
    PB
    RN
    SP
    RJ
}

Endereco "1" --> "1" Estado : tem_um
```

### Main:

```java
package package_heranca;

public class MainHeranca {
    public static void main(String[] args) {
        Endereco enderecoAluno = new Endereco("Rua do Aluno", "Centro", "Angicos", Estado.RN);
        Endereco enderecoProf = new Endereco("Rua do Professor", "Centro", "Angicos", Estado.RN);

        Aluno umAluno = new Aluno("Joãozinho da Silva", enderecoAluno, "20231057");
        Professor umProfessor = new Professor("Josefa de Arruda", enderecoProf, 1500.00);

        System.out.println(umAluno);
        System.out.println(umProfessor);
    }
}
```

### Exercícios em Sala

Gabaritos para ajudar no exercícios [aqui](gabaritos).

Após concluir cada questão, faça _commit_ localmente e sincronize-o (_push_) com o seu repositório remoto no GitHub. Conforme [figura](https://drive.google.com/open?id=1dV5TwUdMxSmh80sx13epVcJFewIT_MVk).

Entregue a folha assinada!
