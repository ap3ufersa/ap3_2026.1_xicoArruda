package com.cadastro;

import javafx.beans.property.*;

public class Professor extends Pessoa {
    private final DoubleProperty salarioBruto = new SimpleDoubleProperty();

    public Professor() {}

    public Professor(String nomeCompleto, Endereco endereco, double salarioBruto) {
        super(nomeCompleto, endereco);
        this.salarioBruto.set(salarioBruto);
    }

    public DoubleProperty salarioBrutoProperty() { return salarioBruto; }

    public double getSalarioBruto()         { return salarioBruto.get(); }
    public void   setSalarioBruto(double v) { salarioBruto.set(v); }

    /** INSS simplificado: 11% */
    public double getSalarioLiquido()       { return salarioBruto.get() * 0.89; }
}
