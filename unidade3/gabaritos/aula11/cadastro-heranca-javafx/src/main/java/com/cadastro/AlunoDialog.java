package com.cadastro;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class AlunoDialog extends Dialog<Void> {

    private final TextField    txtNome      = new TextField();
    private final TextField    txtRua       = new TextField();
    private final TextField    txtBairro    = new TextField();
    private final TextField    txtCidade    = new TextField();
    private final ComboBox<Estado> cmbEstado = new ComboBox<>();
    private final TextField    txtMatricula = new TextField();
    private final TextField    txtN1        = new TextField();
    private final TextField    txtN2        = new TextField();
    private final TextField    txtN3        = new TextField();
    private final TextField    txtN4        = new TextField();
    private final Label        lblStatus    = new Label();

    private final Controller ctrl;
    private final Aluno      emEdicao;

    public AlunoDialog(Controller ctrl, Window owner, Aluno emEdicao) {
        this.ctrl      = ctrl;
        this.emEdicao  = emEdicao;

        initOwner(owner);
        initModality(Modality.WINDOW_MODAL);
        setTitle(emEdicao == null ? "Novo Aluno" : "Editar Aluno");
        setResizable(true);

        cmbEstado.getItems().addAll(Estado.values());
        cmbEstado.getSelectionModel().selectFirst();

        txtMatricula.setPromptText("ex: 2026001");
        txtN1.setPromptText("0–10"); txtN2.setPromptText("0–10");
        txtN3.setPromptText("0–10"); txtN4.setPromptText("0–10");

        if (emEdicao != null) preencher(emEdicao);

        GridPane form = new GridPane();
        form.setHgap(8); form.setVgap(8);
        form.setPadding(new Insets(12));

        form.addRow(0, new Label("Nome:"),      txtNome,      new Label("Matrícula:"), txtMatricula);
        form.addRow(1, new Label("Rua:"),       txtRua,       new Label("Bairro:"),    txtBairro);
        form.addRow(2, new Label("Cidade:"),    txtCidade,    new Label("Estado:"),    cmbEstado);
        form.addRow(3, new Label("Nota 1:"),    txtN1,        new Label("Nota 2:"),    txtN2);
        form.addRow(4, new Label("Nota 3:"),    txtN3,        new Label("Nota 4:"),    txtN4);
        form.addRow(5, lblStatus);

        getDialogPane().setContent(form);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Button btnOk = (Button) getDialogPane().lookupButton(ButtonType.OK);
        btnOk.setText(emEdicao == null ? "Adicionar" : "Salvar");
        btnOk.addEventFilter(javafx.event.ActionEvent.ACTION, e -> {
            try {
                salvar();
            } catch (IllegalArgumentException ex) {
                lblStatus.setStyle("-fx-text-fill:red;");
                lblStatus.setText(ex.getMessage());
                e.consume(); // impede o dialog de fechar
            }
        });

        setResultConverter(bt -> null);
    }

    private void preencher(Aluno a) {
        txtNome.setText(a.getNomeCompleto());
        txtMatricula.setText(String.valueOf(a.getMatricula()));
        txtN1.setText(String.valueOf(a.getNota1()));
        txtN2.setText(String.valueOf(a.getNota2()));
        txtN3.setText(String.valueOf(a.getNota3()));
        txtN4.setText(String.valueOf(a.getNota4()));
        if (a.getEndereco() != null) {
            Endereco end = a.getEndereco();
            txtRua.setText(end.getRua());
            txtBairro.setText(end.getBairro());
            txtCidade.setText(end.getCidade());
            if (end.getEstado() != null) cmbEstado.setValue(end.getEstado());
        }
    }

    private void salvar() {
        if (emEdicao == null) {
            ctrl.adicionarAluno(txtNome.getText(), txtRua.getText(), txtBairro.getText(),
                txtCidade.getText(), cmbEstado.getValue(),
                txtMatricula.getText(), txtN1.getText(), txtN2.getText(), txtN3.getText(), txtN4.getText());
        } else {
            ctrl.atualizarAluno(emEdicao, txtNome.getText(), txtRua.getText(), txtBairro.getText(),
                txtCidade.getText(), cmbEstado.getValue(),
                txtMatricula.getText(), txtN1.getText(), txtN2.getText(), txtN3.getText(), txtN4.getText());
        }
    }
}
