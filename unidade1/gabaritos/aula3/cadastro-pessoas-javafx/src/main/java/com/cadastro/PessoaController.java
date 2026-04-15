package com.cadastro;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PessoaController {

    private final ObservableList<Pessoa> pessoas = FXCollections.observableArrayList();

    public void adicionar(String nome, String cpf, String email, String idadeStr) {
        validar(nome, cpf, email, idadeStr);
        pessoas.add(new Pessoa(nome.trim(), cpf.trim(), email.trim(), Integer.parseInt(idadeStr.trim())));
    }

    public void remover(Pessoa pessoa) {
        if (pessoa == null) throw new IllegalArgumentException("Selecione uma pessoa para remover.");
        pessoas.remove(pessoa);
    }

    public void validarEAtualizar(Pessoa pessoa, String nome, String cpf, String email, String idadeStr) {
        validar(nome, cpf, email, idadeStr);
        pessoa.setNome(nome.trim());
        pessoa.setCpf(cpf.trim());
        pessoa.setEmail(email.trim());
        pessoa.setIdade(Integer.parseInt(idadeStr.trim()));
    }

    public ObservableList<Pessoa> getPessoas() {
        return pessoas;
    }

    private void validar(String nome, String cpf, String email, String idadeStr) {
        if (nome == null || nome.isBlank())   throw new IllegalArgumentException("Nome é obrigatório.");
        if (cpf == null || cpf.isBlank())     throw new IllegalArgumentException("CPF é obrigatório.");
        if (email == null || email.isBlank()) throw new IllegalArgumentException("E-mail é obrigatório.");
        if (idadeStr == null || idadeStr.isBlank()) throw new IllegalArgumentException("Idade é obrigatória.");
        try {
            int idade = Integer.parseInt(idadeStr.trim());
            if (idade < 0 || idade > 150) throw new IllegalArgumentException("Idade inválida.");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Idade deve ser um número inteiro.");
        }
    }
}
