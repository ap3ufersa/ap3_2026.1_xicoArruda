### U3 - Aula 11 - 12/06/2025 - Herança, composição, polimorfismo (2,0)

### 1. Conceitos

- **Herança** (`extends`): `Aluno extends Pessoa` e `Professor extends Pessoa`. Subclasses herdam `nomeCompleto` e `endereco` sem redeclará-los. O construtor da subclasse chama `super(nomeCompleto, endereco)` como primeira instrução.

- **Composição** (`o--`): `Pessoa` **tem um** `Endereco`. Modela posse, não identidade.

- **Polimorfismo**: referência do tipo `Pessoa` pode apontar para `Aluno` ou `Professor`. O método chamado é resolvido em tempo de execução pelo tipo real do objeto.

```java
Pessoa p = new Aluno("João", endereco, 20231057);
System.out.println(p); // chama toString() de Aluno
```

### Diagrama de classes:

```mermaid
classDiagram
direction BT

class Pessoa {
    - nomeCompleto: String
    - endereco: Endereco
    + Pessoa(nomeCompleto: String, endereco: Endereco)
    // get, set, toString()
}

class Aluno {
    - matricula: int
    - nota1: double
    - nota2: double
    - nota3: double
    - nota4: double
    + Aluno(nomeCompleto: String, endereco: Endereco, matricula: int)
    + getMedia(): double
    + getSituacao(): String
    + isAprovado(): boolean
    + getNomeMaiusculo(): String
    + getNomeMinusculo(): String
    // get, set, toString()
}

class Professor {
    - salarioBruto: double
    + Professor(nomeCompleto: String, endereco: Endereco, salarioBruto: double)
    + getSalarioBruto(): double
    + getSalarioLiquido(): double
    // set, toString()
}

class Endereco {
    - rua: String
    - bairro: String
    - cidade: String
    - estado: Estado
    + Endereco(rua, bairro, cidade, estado)
    // get, set, toString()
}

class Estado {
    <<enumeration>>
    PB
    RN
    SP
    RJ
}

Aluno     --|> Pessoa  : é_um
Professor --|> Pessoa  : é_um
Pessoa    "1" o-- "1" Endereco : tem_um
Endereco  "1" --> "1" Estado   : tem_um
```

### Main:

```java
public class MainHeranca {
    public static void main(String[] args) {
        Endereco endAluno = new Endereco("Rua do Aluno", "Centro", "Angicos", Estado.RN);
        Endereco endProf  = new Endereco("Rua do Professor", "Centro", "Angicos", Estado.RN);

        Aluno aluno = new Aluno("Joãozinho da Silva", endAluno, 20231057);
        aluno.setNota1(8); aluno.setNota2(6); aluno.setNota3(9); aluno.setNota4(7);

        Professor prof = new Professor("Josefa de Arruda", endProf, 1500.00);

        System.out.println(aluno);
        System.out.println(prof);
    }
}
```

### Exercícios em Sala

Gabaritos para ajudar no exercícios [aqui](gabaritos).

Interface gráfica aqui [cadastro-heranca-javafx](gabaritos/aula11/cadastro-heranca-javafx).

Após concluir cada questão, faça _commit_ localmente e sincronize-o (_push_) com o seu repositório remoto no GitHub. Conforme [figura](https://drive.google.com/open?id=1dV5TwUdMxSmh80sx13epVcJFewIT_MVk).

Entregue a folha assinada!