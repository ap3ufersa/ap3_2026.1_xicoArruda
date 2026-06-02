package com.cadastro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Controller {

    private final ObservableList<Aluno>    alunos    = FXCollections.observableArrayList();
    private final ObservableList<Professor> professores = FXCollections.observableArrayList();

    // --- Aluno ---

    public void adicionarAluno(String nome, String rua, String bairro, String cidade, Estado estado,
                               String matriculaStr, String n1, String n2, String n3, String n4) {
        Endereco end = parseEndereco(rua, bairro, cidade, estado);
        Aluno novo = new Aluno(validarNome(nome), end, parseIntPositivo(matriculaStr, "Matrícula"),
            parseNota(n1, "Nota 1"), parseNota(n2, "Nota 2"),
            parseNota(n3, "Nota 3"), parseNota(n4, "Nota 4"));
        alunos.add(novo);
        System.out.println("[LOG] Aluno adicionado: " + novo);
        System.out.println("[LOG] Média: " + novo.getMedia() + ", Situação: " + novo.getSituacao());
    }

    public void removerAluno(Aluno a) {
        if (a == null) throw new IllegalArgumentException("Selecione um aluno.");
        System.out.println("[LOG] Removendo aluno: " + a);
        alunos.remove(a);
    }

    public void atualizarAluno(Aluno a, String nome, String rua, String bairro, String cidade, Estado estado,
                               String matriculaStr, String n1, String n2, String n3, String n4) {
        System.out.println("[LOG] Atualizando aluno: " + a);
        a.setNomeCompleto(validarNome(nome));
        a.setEndereco(parseEndereco(rua, bairro, cidade, estado));
        a.setMatricula(parseIntPositivo(matriculaStr, "Matrícula"));
        a.setNota1(parseNota(n1, "Nota 1")); a.setNota2(parseNota(n2, "Nota 2"));
        a.setNota3(parseNota(n3, "Nota 3")); a.setNota4(parseNota(n4, "Nota 4"));
        System.out.println("[LOG] Aluno atualizado: " + a);
        System.out.println("[LOG] Nova média: " + a.getMedia() + ", Nova situação: " + a.getSituacao());
    }

    public ObservableList<Aluno> getAlunos() { return alunos; }

    // --- Professor ---

    public void adicionarProfessor(String nome, String rua, String bairro, String cidade, Estado estado,
                                   String salarioStr) {
        Endereco end = parseEndereco(rua, bairro, cidade, estado);
        Professor novo = new Professor(validarNome(nome), end, parseDouble(salarioStr, "Salário"));
        professores.add(novo);
        System.out.println("[LOG] Professor adicionado: " + novo);
        System.out.println("[LOG] Salário bruto: " + novo.getSalarioBruto() + 
                          ", Salário líquido: " + novo.getSalarioLiquido());
    }

    public void removerProfessor(Professor p) {
        if (p == null) throw new IllegalArgumentException("Selecione um professor.");
        System.out.println("[LOG] Removendo professor: " + p);
        professores.remove(p);
    }

    public void atualizarProfessor(Professor p, String nome, String rua, String bairro,
                                   String cidade, Estado estado, String salarioStr) {
        System.out.println("[LOG] Atualizando professor: " + p);
        p.setNomeCompleto(validarNome(nome));
        p.setEndereco(parseEndereco(rua, bairro, cidade, estado));
        p.setSalarioBruto(parseDouble(salarioStr, "Salário"));
        System.out.println("[LOG] Professor atualizado: " + p);
        System.out.println("[LOG] Novo salário bruto: " + p.getSalarioBruto() + 
                          ", líquido: " + p.getSalarioLiquido());
    }

    public ObservableList<Professor> getProfessores() { return professores; }

    // --- helpers ---

    private String validarNome(String v) {
        if (v == null || v.isBlank()) throw new IllegalArgumentException("Nome é obrigatório.");
        return v.trim();
    }

    private Endereco parseEndereco(String rua, String bairro, String cidade, Estado estado) {
        if (cidade == null || cidade.isBlank())
            throw new IllegalArgumentException("Cidade é obrigatória.");
        if (estado == null)
            throw new IllegalArgumentException("Estado é obrigatório.");
        
        return new Endereco(
                    rua == null ? "" : rua.trim(),
                    bairro == null ? "" : bairro.trim(),
                    cidade.trim(), estado
                );
    }

    private int parseIntPositivo(String v, String campo) {
        if (v == null || v.isBlank()) throw new IllegalArgumentException(campo + " é obrigatório.");
        try {
            int n = Integer.parseInt(v.trim());
            if (n <= 0) throw new IllegalArgumentException(campo + " deve ser positivo.");
            return n;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(campo + " deve ser inteiro.");
        }
    }

    private double parseNota(String v, String campo) {
        double n = parseDouble(v, campo);
        if (n < 0 || n > 10) throw new IllegalArgumentException(campo + " deve estar entre 0 e 10.");
        return n;
    }

    private double parseDouble(String v, String campo) {
        if (v == null || v.isBlank()) throw new IllegalArgumentException(campo + " é obrigatório.");
        try {
            return Double.parseDouble(v.trim().replace(',', '.'));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(campo + " deve ser um número.");
        }
    }
}
