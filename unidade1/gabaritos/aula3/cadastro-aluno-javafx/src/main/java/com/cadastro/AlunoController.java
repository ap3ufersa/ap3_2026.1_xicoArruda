package com.cadastro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AlunoController {

    private final ObservableList<Aluno> alunos = FXCollections.observableArrayList();

    public void adicionar(String nome, String n1, String n2, String n3) {
        double[] notas = validar(nome, n1, n2, n3);
        alunos.add(new Aluno(nome.trim(), notas[0], notas[1], notas[2]));
    }

    public void remover(Aluno aluno) {
        if (aluno == null) throw new IllegalArgumentException("Selecione um aluno para remover.");
        alunos.remove(aluno);
    }

    public void validarEAtualizar(Aluno aluno, String nome, String n1, String n2, String n3) {
        double[] notas = validar(nome, n1, n2, n3);
        aluno.setNomeCompleto(nome.trim());
        aluno.setNota1(notas[0]);
        aluno.setNota2(notas[1]);
        aluno.setNota3(notas[2]);
    }

    public ObservableList<Aluno> getAlunos() {
        return alunos;
    }

    private double[] validar(String nome, String n1, String n2, String n3) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome é obrigatório.");
        return new double[]{parseNota(n1, "Nota 1"), parseNota(n2, "Nota 2"), parseNota(n3, "Nota 3")};
    }

    private double parseNota(String valor, String campo) {
        if (valor == null || valor.isBlank()) throw new IllegalArgumentException(campo + " é obrigatória.");
        try {
            double nota = Double.parseDouble(valor.trim().replace(',', '.'));
            if (nota < 0 || nota > 10) throw new IllegalArgumentException(campo + " deve estar entre 0 e 10.");
            return nota;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(campo + " deve ser um número.");
        }
    }
}
