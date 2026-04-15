package com.cadastro;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class PessoaView {

    private final PessoaController controller = new PessoaController();

    private final TextField txtNome   = new TextField();
    private final TextField txtCpf    = new TextField();
    private final TextField txtEmail  = new TextField();
    private final TextField txtIdade  = new TextField();
    private final Label     lblStatus = new Label();
    private final Button    btnAcao   = new Button("Adicionar");

    private Pessoa emEdicao = null;

    public void iniciar(Stage stage) {
        TableView<Pessoa> tabela = buildTabela();

        btnAcao.setOnAction(e -> onAcao());

        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setOnAction(e -> cancelarEdicao());
        btnCancelar.setVisible(false);

        // expor cancelar junto ao botão de ação
        btnAcao.setUserData(btnCancelar);

        GridPane form = new GridPane();
        form.setHgap(8);
        form.setVgap(8);
        form.addRow(0, new Label("Nome:"),   txtNome,  new Label("CPF:"),   txtCpf);
        form.addRow(1, new Label("E-mail:"), txtEmail, new Label("Idade:"), txtIdade);

        HBox botoes = new HBox(8, btnAcao, btnCancelar);
        VBox root   = new VBox(10, form, botoes, lblStatus, tabela);
        root.setPadding(new Insets(16));

        stage.setScene(new Scene(root, 760, 460));
        stage.setTitle("Cadastro de Pessoas");
        stage.show();
    }

    private TableView<Pessoa> buildTabela() {
        TableView<Pessoa> tabela = new TableView<>(controller.getPessoas());
        tabela.setEditable(true);

        TableColumn<Pessoa, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(c -> c.getValue().nomeProperty());
        colNome.setCellFactory(TextFieldTableCell.forTableColumn());
        colNome.setOnEditCommit(e -> e.getRowValue().setNome(e.getNewValue()));
        colNome.setPrefWidth(170);

        TableColumn<Pessoa, String> colCpf = new TableColumn<>("CPF");
        colCpf.setCellValueFactory(c -> c.getValue().cpfProperty());
        colCpf.setCellFactory(TextFieldTableCell.forTableColumn());
        colCpf.setOnEditCommit(e -> e.getRowValue().setCpf(e.getNewValue()));
        colCpf.setPrefWidth(120);

        TableColumn<Pessoa, String> colEmail = new TableColumn<>("E-mail");
        colEmail.setCellValueFactory(c -> c.getValue().emailProperty());
        colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
        colEmail.setOnEditCommit(e -> e.getRowValue().setEmail(e.getNewValue()));
        colEmail.setPrefWidth(190);

        TableColumn<Pessoa, Integer> colIdade = new TableColumn<>("Idade");
        colIdade.setCellValueFactory(c -> c.getValue().idadeProperty().asObject());
        colIdade.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colIdade.setOnEditCommit(e -> { if (e.getNewValue() != null) e.getRowValue().setIdade(e.getNewValue()); });
        colIdade.setPrefWidth(55);

        tabela.getColumns().addAll(colNome, colCpf, colEmail, colIdade, buildColunaAcoes());
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        return tabela;
    }

    private TableColumn<Pessoa, Void> buildColunaAcoes() {
        TableColumn<Pessoa, Void> col = new TableColumn<>("Ações");
        col.setPrefWidth(90);
        col.setSortable(false);
        col.setCellFactory(tc -> new TableCell<>() {
            private final Button btnEditar  = new Button("✎");
            private final Button btnRemover = new Button("✕");
            private final HBox   box        = new HBox(6, btnEditar, btnRemover);

            {
                btnEditar.setStyle("-fx-cursor: hand;");
                btnRemover.setStyle("-fx-cursor: hand; -fx-text-fill: red;");
                btnEditar.setOnAction(e  -> iniciarEdicao(getTableView().getItems().get(getIndex())));
                btnRemover.setOnAction(e -> {
                    controller.remover(getTableView().getItems().get(getIndex()));
                    status("Pessoa removida.", false);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : box);
            }
        });
        return col;
    }

    private void iniciarEdicao(Pessoa pessoa) {
        emEdicao = pessoa;
        txtNome.setText(pessoa.getNome());
        txtCpf.setText(pessoa.getCpf());
        txtEmail.setText(pessoa.getEmail());
        txtIdade.setText(String.valueOf(pessoa.getIdade()));
        btnAcao.setText("Salvar");
        cancelarBtn().setVisible(true);
        txtNome.requestFocus();
    }

    private void cancelarEdicao() {
        emEdicao = null;
        limparCampos();
        btnAcao.setText("Adicionar");
        cancelarBtn().setVisible(false);
        lblStatus.setText("");
    }

    private void onAcao() {
        if (emEdicao != null) {
            try {
                controller.validarEAtualizar(emEdicao, txtNome.getText(), txtCpf.getText(), txtEmail.getText(), txtIdade.getText());
                cancelarEdicao();
                status("Pessoa atualizada.", false);
            } catch (IllegalArgumentException ex) {
                status(ex.getMessage(), true);
            }
        } else {
            try {
                controller.adicionar(txtNome.getText(), txtCpf.getText(), txtEmail.getText(), txtIdade.getText());
                limparCampos();
                status("Pessoa adicionada.", false);
            } catch (IllegalArgumentException ex) {
                status(ex.getMessage(), true);
            }
        }
    }

    private void limparCampos() {
        txtNome.clear(); txtCpf.clear(); txtEmail.clear(); txtIdade.clear();
    }

    private void status(String msg, boolean erro) {
        lblStatus.setText(msg);
        lblStatus.setStyle(erro ? "-fx-text-fill: red;" : "-fx-text-fill: green;");
    }

    private Button cancelarBtn() {
        return (Button) btnAcao.getUserData();
    }
}
