package com.cadastro;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class ProfessorDialog extends Dialog<Void> {

    private final TextField        txtNome    = new TextField();
    private final TextField        txtRua     = new TextField();
    private final TextField        txtBairro  = new TextField();
    private final TextField        txtCidade  = new TextField();
    private final ComboBox<Estado> cmbEstado  = new ComboBox<>();
    private final TextField        txtSalario = new TextField();
    private final Label            lblStatus  = new Label();

    private final Controller ctrl;
    private final Professor  emEdicao;

    public ProfessorDialog(Controller ctrl, Window owner, Professor emEdicao) {
        this.ctrl     = ctrl;
        this.emEdicao = emEdicao;

        initOwner(owner);
        initModality(Modality.WINDOW_MODAL);
        setTitle(emEdicao == null ? "Novo Professor" : "Editar Professor");
        setResizable(true);

        cmbEstado.getItems().addAll(Estado.values());
        cmbEstado.getSelectionModel().selectFirst();
        txtSalario.setPromptText("ex: 3500.00");

        if (emEdicao != null) preencher(emEdicao);

        GridPane form = new GridPane();
        form.setHgap(8); form.setVgap(8);
        form.setPadding(new Insets(12));

        form.addRow(0, new Label("Nome:"),         txtNome,   new Label("Salário bruto:"), txtSalario);
        form.addRow(1, new Label("Rua:"),          txtRua,    new Label("Bairro:"),        txtBairro);
        form.addRow(2, new Label("Cidade:"),       txtCidade, new Label("Estado:"),        cmbEstado);
        form.addRow(3, lblStatus);

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
                e.consume();
            }
        });

        setResultConverter(bt -> null);
    }

    private void preencher(Professor p) {
        txtNome.setText(p.getNomeCompleto());
        txtSalario.setText(String.valueOf(p.getSalarioBruto()));
        if (p.getEndereco() != null) {
            Endereco end = p.getEndereco();
            txtRua.setText(end.getRua());
            txtBairro.setText(end.getBairro());
            txtCidade.setText(end.getCidade());
            if (end.getEstado() != null) cmbEstado.setValue(end.getEstado());
        }
    }

    private void salvar() {
        if (emEdicao == null) {
            ctrl.adicionarProfessor(txtNome.getText(), txtRua.getText(), txtBairro.getText(),
                txtCidade.getText(), cmbEstado.getValue(), txtSalario.getText());
        } else {
            ctrl.atualizarProfessor(emEdicao, txtNome.getText(), txtRua.getText(), txtBairro.getText(),
                txtCidade.getText(), cmbEstado.getValue(), txtSalario.getText());
        }
    }
}
