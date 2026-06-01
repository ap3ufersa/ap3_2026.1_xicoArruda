# U3 - Aula 11 - 12/06/2025 - Herança, composição, polimorfismo (2,0)

### Leitura dos Relacionamentos

---

```mermaid
classDiagram
    direction BT

    class Cozinha
    class PortaCozinha
    Cozinha o-- PortaCozinha : agregação (fraca)

    class Quarto
    class PortaQuarto
    Quarto *-- PortaQuarto : composição (forte)
```

```text
O losango fica do lado de quem tem.
```

---

```mermaid
classDiagram
    direction BT

    Cozinha "1..1" o-- "1..1" PortaCozinha : tem
    Quarto  "1..1" *-- "1..1" PortaQuarto : tem
    Sala    "1..1" *-- "1..1" PortaSala : tem

    PortaCozinha --|> Porta
    PortaQuarto --|> Porta
    PortaSala --|> Porta

    note for PortaCozinha "PortaCozinha é uma Porta."
    note for Cozinha "Cozinha tem uma porta, ou não..."
```

---

## Modelagem Errada

```mermaid
classDiagram

direction BT

class Cozinha {
    - porta : PortaCozinha
    - americana : boolean
    - metragemQuadrada : float
    + Cozinha(porta, americana, metragemQuadrada)
    //getters, setters, toString()
}

class Quarto {
    - porta : PortaQuarto
    - banheiro : boolean
    - metragemQuadrada : float
    + Quarto(porta, banheiro, metragemQuadrada)
    //getters, setters, toString()
}

class Sala {
    - porta : PortaSala
    - metragemQuadrada : float
    + Sala(porta, metragemQuadrada)
    //getters, setters, toString()
}

class Porta {
    - cor : String
    - largura : float
    - altura : float
    - peso : double
    + Porta(cor, largura, altura, peso)
    + abrir()
    + fechar()
    //getters, setters, toString()
}

class PortaCozinha {
    - tipoAbertura : String
    //correr, sanfonada, camarão...
    + abrir()
    + fechar()
    //getters, setters, toString()
}

class PortaQuarto {
    - possuiTrinco : boolean
    + abrir()
    + fechar()
    //getters, setters, toString()
}

class PortaSala {
    - possuiOlhoMagico : boolean
    + abrir()
    + fechar()
    //getters, setters, toString()
}

note for Cozinha "ESTÁ ERRADO.

Foco nos losangos.

PortaCozinha tem uma Cozinha?
PortaCozinha é uma Porta?"

PortaCozinha "1..1" o-- "1..1" Cozinha : tem
PortaQuarto  "1..1" *-- "1..1" Quarto : tem
PortaSala    "1..1" *-- "1..1" Sala : tem

PortaCozinha --|> Porta
PortaQuarto --|> Porta
PortaSala --|> Porta
```

---

## Modelagem Correta

```mermaid
classDiagram

direction BT

class Cozinha {
    - porta : PortaCozinha
    - americana : boolean
    - metragemQuadrada : float
    + Cozinha(porta, americana, metragemQuadrada)
    //getters, setters, toString()
}

class Quarto {
    - porta : PortaQuarto
    - banheiro : boolean
    - metragemQuadrada : float
    + Quarto(porta, banheiro, metragemQuadrada)
    //getters, setters, toString()
}

class Sala {
    - porta : PortaSala
    - metragemQuadrada : float
    + Sala(porta, metragemQuadrada)
    //getters, setters, toString()
}

class Porta {
    - cor : String
    - largura : float
    - altura : float
    - peso : double
    + Porta(cor, largura, altura, peso)
    + abrir()
    + fechar()
    //getters, setters, toString()
}

class PortaCozinha {
    - tipoAbertura : String
    //correr, sanfonada, camarão...
    + abrir()
    + fechar()
    //getters, setters, toString()
}

class PortaQuarto {
    - possuiTrinco : boolean
    + abrir()
    + fechar()
    //getters, setters, toString()
}

class PortaSala {
    - possuiOlhoMagico : boolean
    + abrir()
    + fechar()
    //getters, setters, toString()
}

note for Cozinha "ESTÁ CORRETA.

Foco nos losangos.

Cozinha tem uma PortaCozinha?
PortaCozinha é uma Porta?"

Cozinha "1..1" o-- "1..1" PortaCozinha : tem
Quarto  "1..1" *-- "1..1" PortaQuarto : tem
Sala    "1..1" *-- "1..1" PortaSala : tem

PortaCozinha --|> Porta
PortaQuarto --|> Porta
PortaSala --|> Porta
```

```text
O losango fica do lado de quem tem.
```
