package com.cadastro;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class AlunoView {

    private final AlunoController controller = new AlunoController();

    private final TextField txtMatricula = new TextField();
    private final TextField txtNome      = new TextField();
    private final TextField txtNota1     = new TextField();
    private final TextField txtNota2     = new TextField();
    private final TextField txtNota3     = new TextField();
    private final TextField txtNota4     = new TextField();
    private final Label     lblStatus    = new Label();
    private final Button    btnAcao      = new Button("Adicionar");
    private final Button    btnCancelar  = new Button("Cancelar");

    private Aluno emEdicao = null;

    public void iniciar(Stage stage) {
        btnAcao.setOnAction(e -> onAcao());
        btnCancelar.setOnAction(e -> cancelarEdicao());
        btnCancelar.setVisible(false);

        txtMatricula.setPromptText("ex: 2026001");
        txtNota1.setPromptText("0 – 10");
        txtNota2.setPromptText("0 – 10");
        txtNota3.setPromptText("0 – 10");
        txtNota4.setPromptText("0 – 10");

        GridPane form = new GridPane();
        form.setHgap(8);
        form.setVgap(8);
        form.addRow(0, new Label("Matrícula:"), txtMatricula, new Label("Nome:"),   txtNome);
        form.addRow(1, new Label("Nota 1:"),    txtNota1,     new Label("Nota 2:"), txtNota2);
        form.addRow(2, new Label("Nota 3:"),    txtNota3,     new Label("Nota 4:"), txtNota4);

        VBox root = new VBox(10, form, new HBox(8, btnAcao, btnCancelar), lblStatus, buildTabela());
        root.setPadding(new Insets(16));

        stage.setScene(new Scene(root, 980, 500));
        stage.setTitle("Cadastro de Alunos");
        stage.show();
    }

    private TableView<Aluno> buildTabela() {
        TableView<Aluno> tabela = new TableView<>(controller.getAlunos());
        tabela.setEditable(true);

        TableColumn<Aluno, Integer> colMatricula = new TableColumn<>("Matrícula");
        colMatricula.setCellValueFactory(c -> c.getValue().matriculaProperty().asObject());
        colMatricula.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colMatricula.setOnEditCommit(e -> { if (e.getNewValue() != null) e.getRowValue().setMatricula(e.getNewValue()); });
        colMatricula.setPrefWidth(90);

        TableColumn<Aluno, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(c -> Bindings.createStringBinding(
            c.getValue()::getNomeMaiusculo, c.getValue().nomeProperty()));
        colNome.setCellFactory(TextFieldTableCell.forTableColumn());
        colNome.setOnEditCommit(e -> e.getRowValue().setNome(e.getNewValue()));
        colNome.setPrefWidth(200);

        TableColumn<Aluno, String> colNomeMin = new TableColumn<>("Nome (minúsculo)");
        colNomeMin.setCellValueFactory(c -> Bindings.createStringBinding(
            c.getValue()::getNomeMinusculo, c.getValue().nomeProperty()));
        colNomeMin.setPrefWidth(200);

        TableColumn<Aluno, Double> colMedia = new TableColumn<>("Média");
        colMedia.setCellValueFactory(c -> {
            Aluno a = c.getValue();
            return Bindings.createDoubleBinding(
                a::getMedia, a.nota1Property(), a.nota2Property(), a.nota3Property(), a.nota4Property()
            ).asObject();
        });
        colMedia.setPrefWidth(65);
        colMedia.setStyle("-fx-alignment: CENTER;");

        TableColumn<Aluno, String> colSituacao = new TableColumn<>("Situação");
        colSituacao.setCellValueFactory(c -> {
            Aluno a = c.getValue();
            return Bindings.createStringBinding(
                a::getSituacao, a.nota1Property(), a.nota2Property(), a.nota3Property(), a.nota4Property());
        });
        colSituacao.setPrefWidth(90);
        colSituacao.setStyle("-fx-alignment: CENTER;");

        tabela.getColumns().addAll(
            colMatricula, colNome, colNomeMin,
            colNota("N1", 1), colNota("N2", 2), colNota("N3", 3), colNota("N4", 4),
            colMedia, colSituacao, buildColunaAcoes()
        );
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        return tabela;
    }

    private TableColumn<Aluno, Double> colNota(String titulo, int n) {
        TableColumn<Aluno, Double> col = new TableColumn<>(titulo);
        col.setPrefWidth(55);
        col.setStyle("-fx-alignment: CENTER;");
        col.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        col.setCellValueFactory(c -> switch (n) {
            case 1 -> c.getValue().nota1Property().asObject();
            case 2 -> c.getValue().nota2Property().asObject();
            case 3 -> c.getValue().nota3Property().asObject();
            default -> c.getValue().nota4Property().asObject();
        });
        col.setOnEditCommit(e -> {
            if (e.getNewValue() == null) return;
            switch (n) {
                case 1 -> e.getRowValue().setNota1(e.getNewValue());
                case 2 -> e.getRowValue().setNota2(e.getNewValue());
                case 3 -> e.getRowValue().setNota3(e.getNewValue());
                default -> e.getRowValue().setNota4(e.getNewValue());
            }
        });
        return col;
    }

    private TableColumn<Aluno, Void> buildColunaAcoes() {
        TableColumn<Aluno, Void> col = new TableColumn<>("Ações");
        col.setPrefWidth(90);
        col.setSortable(false);
        col.setCellFactory(tc -> new TableCell<>() {
            private final Button btnEditar  = new Button("✎");
            private final Button btnRemover = new Button("✕");

            {
                btnEditar.setStyle("-fx-cursor: hand;");
                btnRemover.setStyle("-fx-cursor: hand; -fx-text-fill: red;");
                btnEditar.setOnAction(e  -> iniciarEdicao(getTableView().getItems().get(getIndex())));
                btnRemover.setOnAction(e -> {
                    controller.remover(getTableView().getItems().get(getIndex()));
                    status("Aluno removido.", false);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : new HBox(6, btnEditar, btnRemover));
            }
        });
        return col;
    }

    private void iniciarEdicao(Aluno a) {
        emEdicao = a;
        txtMatricula.setText(String.valueOf(a.getMatricula()));
        txtNome.setText(a.getNome());
        txtNota1.setText(String.valueOf(a.getNota1()));
        txtNota2.setText(String.valueOf(a.getNota2()));
        txtNota3.setText(String.valueOf(a.getNota3()));
        txtNota4.setText(String.valueOf(a.getNota4()));
        btnAcao.setText("Salvar");
        btnCancelar.setVisible(true);
        txtNome.requestFocus();
    }

    private void cancelarEdicao() {
        emEdicao = null;
        limparCampos();
        btnAcao.setText("Adicionar");
        btnCancelar.setVisible(false);
        lblStatus.setText("");
    }

    private void onAcao() {
        try {
            if (emEdicao != null) {
                controller.validarEAtualizar(emEdicao,
                    txtMatricula.getText(), txtNome.getText(),
                    txtNota1.getText(), txtNota2.getText(), txtNota3.getText(), txtNota4.getText());
                cancelarEdicao();
                status("Aluno atualizado.", false);
            } else {
                controller.adicionar(
                    txtMatricula.getText(), txtNome.getText(),
                    txtNota1.getText(), txtNota2.getText(), txtNota3.getText(), txtNota4.getText());
                limparCampos();
                status("Aluno adicionado.", false);
            }
        } catch (IllegalArgumentException ex) {
            status(ex.getMessage(), true);
        }
    }

    private void limparCampos() {
        txtMatricula.clear(); txtNome.clear();
        txtNota1.clear(); txtNota2.clear(); txtNota3.clear(); txtNota4.clear();
    }

    private void status(String msg, boolean erro) {
        lblStatus.setText(msg);
        lblStatus.setStyle(erro ? "-fx-text-fill: red;" : "-fx-text-fill: green;");
    }
}
