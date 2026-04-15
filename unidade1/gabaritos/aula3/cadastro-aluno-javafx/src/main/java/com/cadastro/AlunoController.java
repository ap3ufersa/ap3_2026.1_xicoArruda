package com.cadastro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AlunoController {

    private final ObservableList<Aluno> alunos = FXCollections.observableArrayList();

    public void adicionar(String matriculaStr, String nome, String n1, String n2, String n3, String n4) {
        int matricula = parseMatricula(matriculaStr);
        double[] notas = validarNotas(n1, n2, n3, n4);
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome é obrigatório.");
        alunos.add(new Aluno(matricula, nome.trim(), notas[0], notas[1], notas[2], notas[3]));
    }

    public void remover(Aluno aluno) {
        if (aluno == null) throw new IllegalArgumentException("Selecione um aluno para remover.");
        alunos.remove(aluno);
    }

    public void validarEAtualizar(Aluno aluno, String matriculaStr, String nome,
                                  String n1, String n2, String n3, String n4) {
        int matricula = parseMatricula(matriculaStr);
        double[] notas = validarNotas(n1, n2, n3, n4);
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome é obrigatório.");
        aluno.setMatricula(matricula);
        aluno.setNome(nome.trim());
        aluno.setNota1(notas[0]);
        aluno.setNota2(notas[1]);
        aluno.setNota3(notas[2]);
        aluno.setNota4(notas[3]);
    }

    public ObservableList<Aluno> getAlunos() {
        return alunos;
    }

    private int parseMatricula(String valor) {
        if (valor == null || valor.isBlank()) throw new IllegalArgumentException("Matrícula é obrigatória.");
        try {
            int m = Integer.parseInt(valor.trim());
            if (m <= 0) throw new IllegalArgumentException("Matrícula deve ser positiva.");
            return m;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Matrícula deve ser um número inteiro.");
        }
    }

    private double[] validarNotas(String n1, String n2, String n3, String n4) {
        return new double[]{
            parseNota(n1, "Nota 1"),
            parseNota(n2, "Nota 2"),
            parseNota(n3, "Nota 3"),
            parseNota(n4, "Nota 4")
        };
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
