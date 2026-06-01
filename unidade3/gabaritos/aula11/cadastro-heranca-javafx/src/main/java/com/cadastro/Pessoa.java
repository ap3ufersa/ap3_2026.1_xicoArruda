package com.cadastro;

import javafx.beans.property.*;

public class Pessoa {
    private final StringProperty           nomeCompleto = new SimpleStringProperty();
    private final ObjectProperty<Endereco> endereco     = new SimpleObjectProperty<>();

    public Pessoa() {}

    public Pessoa(String nomeCompleto, Endereco endereco) {
        this.nomeCompleto.set(nomeCompleto);
        this.endereco.set(endereco);
    }

    public StringProperty           nomeCompletoProperty() { return nomeCompleto; }
    public ObjectProperty<Endereco> enderecoProperty()     { return endereco; }

    public String   getNomeCompleto()         { return nomeCompleto.get(); }
    public Endereco getEndereco()             { return endereco.get(); }
    public void     setNomeCompleto(String v) { nomeCompleto.set(v); }
    public void     setEndereco(Endereco v)   { endereco.set(v); }

    @Override
    public String toString() {
        return getNomeCompleto() + " | " + getEndereco();
    }
}
