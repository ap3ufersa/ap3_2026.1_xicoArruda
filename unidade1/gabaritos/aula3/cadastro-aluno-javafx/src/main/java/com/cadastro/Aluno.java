package com.cadastro;

import javafx.beans.property.*;

public class Aluno {

    private final StringProperty  nomeCompleto = new SimpleStringProperty();
    private final DoubleProperty  nota1        = new SimpleDoubleProperty();
    private final DoubleProperty  nota2        = new SimpleDoubleProperty();
    private final DoubleProperty  nota3        = new SimpleDoubleProperty();

    public Aluno(String nomeCompleto, double nota1, double nota2, double nota3) {
        this.nomeCompleto.set(nomeCompleto);
        this.nota1.set(nota1);
        this.nota2.set(nota2);
        this.nota3.set(nota3);
    }

    public StringProperty nomeCompletoProperty() { return nomeCompleto; }
    public DoubleProperty nota1Property()         { return nota1; }
    public DoubleProperty nota2Property()         { return nota2; }
    public DoubleProperty nota3Property()         { return nota3; }

    public String getNomeCompleto() { return nomeCompleto.get(); }
    public double getNota1()        { return nota1.get(); }
    public double getNota2()        { return nota2.get(); }
    public double getNota3()        { return nota3.get(); }

    public void setNomeCompleto(String v) { nomeCompleto.set(v); }
    public void setNota1(double v)        { nota1.set(v); }
    public void setNota2(double v)        { nota2.set(v); }
    public void setNota3(double v)        { nota3.set(v); }

    public double getMedia() {
        return (nota1.get() + nota2.get() + nota3.get()) / 3.0;
    }

    public boolean isAprovado() {
        return getMedia() >= 7.0;
    }
}
