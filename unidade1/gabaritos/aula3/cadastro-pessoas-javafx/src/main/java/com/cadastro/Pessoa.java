package com.cadastro;

import javafx.beans.property.*;

public class Pessoa {
    private final StringProperty  nome  = new SimpleStringProperty();
    private final StringProperty  cpf   = new SimpleStringProperty();
    private final StringProperty  email = new SimpleStringProperty();
    private final IntegerProperty idade = new SimpleIntegerProperty();

    public Pessoa(String nome, String cpf, String email, int idade) {
        this.nome.set(nome);
        this.cpf.set(cpf);
        this.email.set(email);
        this.idade.set(idade);
    }

    public StringProperty  nomeProperty()  { return nome; }
    public StringProperty  cpfProperty()   { return cpf; }
    public StringProperty  emailProperty() { return email; }
    public IntegerProperty idadeProperty() { return idade; }

    public String getNome()  { return nome.get(); }
    public String getCpf()   { return cpf.get(); }
    public String getEmail() { return email.get(); }
    public int    getIdade() { return idade.get(); }

    public void setNome(String v)  { nome.set(v); }
    public void setCpf(String v)   { cpf.set(v); }
    public void setEmail(String v) { email.set(v); }
    public void setIdade(int v)    { idade.set(v); }
}
