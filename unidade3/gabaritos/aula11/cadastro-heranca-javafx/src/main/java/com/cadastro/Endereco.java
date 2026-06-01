package com.cadastro;

import javafx.beans.property.*;

public class Endereco {
    private final StringProperty rua    = new SimpleStringProperty();
    private final StringProperty bairro = new SimpleStringProperty();
    private final StringProperty cidade = new SimpleStringProperty();
    private final ObjectProperty<Estado> estado = new SimpleObjectProperty<>();

    public Endereco() {}

    public Endereco(String rua, String bairro, String cidade, Estado estado) {
        this.rua.set(rua);
        this.bairro.set(bairro);
        this.cidade.set(cidade);
        this.estado.set(estado);
    }

    public StringProperty ruaProperty()    { return rua; }
    public StringProperty bairroProperty() { return bairro; }
    public StringProperty cidadeProperty() { return cidade; }
    public ObjectProperty<Estado> estadoProperty() { return estado; }

    public String getRua()    { return rua.get(); }
    public String getBairro() { return bairro.get(); }
    public String getCidade() { return cidade.get(); }
    public Estado getEstado() { return estado.get(); }

    public void setRua(String v)    { rua.set(v); }
    public void setBairro(String v) { bairro.set(v); }
    public void setCidade(String v) { cidade.set(v); }
    public void setEstado(Estado v) { estado.set(v); }

    @Override
    public String toString() {
        return getRua() + ", " + getBairro() + ", " + getCidade() + " - " + getEstado();
    }
}
