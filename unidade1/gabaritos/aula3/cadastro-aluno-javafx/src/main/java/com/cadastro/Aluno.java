package com.cadastro;

import javafx.beans.property.*;

public class Aluno {

    private final IntegerProperty matricula = new SimpleIntegerProperty();
    private final StringProperty  nome      = new SimpleStringProperty();
    private final DoubleProperty  nota1     = new SimpleDoubleProperty();
    private final DoubleProperty  nota2     = new SimpleDoubleProperty();
    private final DoubleProperty  nota3     = new SimpleDoubleProperty();
    private final DoubleProperty  nota4     = new SimpleDoubleProperty();

    public Aluno(int matricula, String nome, double nota1, double nota2, double nota3, double nota4) {
        this.matricula.set(matricula);
        this.nome.set(nome);
        this.nota1.set(nota1);
        this.nota2.set(nota2);
        this.nota3.set(nota3);
        this.nota4.set(nota4);
    }

    public IntegerProperty matriculaProperty() { return matricula; }
    public StringProperty  nomeProperty()      { return nome; }
    public DoubleProperty  nota1Property()     { return nota1; }
    public DoubleProperty  nota2Property()     { return nota2; }
    public DoubleProperty  nota3Property()     { return nota3; }
    public DoubleProperty  nota4Property()     { return nota4; }

    public int    getMatricula() { return matricula.get(); }
    public String getNome()      { return nome.get(); }
    public double getNota1()     { return nota1.get(); }
    public double getNota2()     { return nota2.get(); }
    public double getNota3()     { return nota3.get(); }
    public double getNota4()     { return nota4.get(); }

    public void setMatricula(int v)    { matricula.set(v); }
    public void setNome(String v)      { nome.set(v); }
    public void setNota1(double v)     { nota1.set(v); }
    public void setNota2(double v)     { nota2.set(v); }
    public void setNota3(double v)     { nota3.set(v); }
    public void setNota4(double v)     { nota4.set(v); }

    public String getNomeMaiusculo() { return nome.get().toUpperCase(); }
    public String getNomeMinusculo() { return nome.get().toLowerCase(); }

    /** Média das três maiores notas (descarta a menor). */
    public double getMedia() {
        double soma = nota1.get() + nota2.get() + nota3.get() + nota4.get();
        double menor = Math.min(nota1.get(), Math.min(nota2.get(), Math.min(nota3.get(), nota4.get())));
        return (soma - menor) / 3.0;
    }

    public String getSituacao() {
        double media = getMedia();
        if (media >= 7.0) return "Aprovado";
        if (media >= 5.0) return "Recuperação";
        return "Reprovado";
    }

    public boolean isAprovado() {
        return getMedia() >= 7.0;
    }
}
