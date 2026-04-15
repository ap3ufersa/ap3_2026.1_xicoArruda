package com.cadastro;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.SimpleStringProperty;
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

    private Aluno emEdicao = null;

    public void iniciar(Stage stage) {
        TableView<Aluno> tabela = buildTabela();

        btnAcao.setOnAction(e -> onAcao());

        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setOnAction(e -> cancelarEdicao());
        btnCancelar.setVisible(false);
        btnAcao.setUserData(btnCancelar);

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

        HBox botoes = new HBox(8, btnAcao, btnCancelar);
        VBox root   = new VBox(10, form, botoes, lblStatus, tabela);
        root.setPadding(new Insets(16));

        stage.setScene(new Scene(root, 980, 500));
        stage.setTitle("Cadastro de Alunos");
        stage.show();
    }

    private TableView<Aluno> buildTabela() {
        TableView<Aluno> tabela = new TableView<>(controller.getAlunos());
        tabela.setEditable(true);

        // Matrícula
        TableColumn<Aluno, Integer> colMatricula = new TableColumn<>("Matrícula");
        colMatricula.setCellValueFactory(c -> c.getValue().matriculaProperty().asObject());
        colMatricula.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        colMatricula.setOnEditCommit(e -> { if (e.getNewValue() != null) e.getRowValue().setMatricula(e.getNewValue()); });
        colMatricula.setPrefWidth(90);

        // Nome (exibe em MAIÚSCULO — usa getNomeMaiusculo(), reage a mudanças na property)
        TableColumn<Aluno, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(c -> {
            Aluno a = c.getValue();
            return Bindings.createStringBinding(a::getNomeMaiusculo, a.nomeProperty());
        });
        colNome.setCellFactory(TextFieldTableCell.forTableColumn());
        colNome.setOnEditCommit(e -> e.getRowValue().setNome(e.getNewValue()));
        colNome.setPrefWidth(180);

        // Nome minúsculo (coluna extra para demonstrar getNomeMinusculo())
        TableColumn<Aluno, String> colNomeMin = new TableColumn<>("Nome (minúsculo)");
        colNomeMin.setCellValueFactory(c -> {
            Aluno a = c.getValue();
            return Bindings.createStringBinding(a::getNomeMinusculo, a.nomeProperty());
        });
        colNomeMin.setPrefWidth(180);

        // Notas
        TableColumn<Aluno, Double> colN1 = colNota("N1", 1);
        TableColumn<Aluno, Double> colN2 = colNota("N2", 2);
        TableColumn<Aluno, Double> colN3 = colNota("N3", 3);
        TableColumn<Aluno, Double> colN4 = colNota("N4", 4);

        // Média (reativa — recalcula ao editar qualquer nota)
        TableColumn<Aluno, Double> colMedia = new TableColumn<>("Média");
        colMedia.setCellValueFactory(c -> {
            Aluno a = c.getValue();
            DoubleBinding binding = new DoubleBinding() {
                { super.bind(a.nota1Property(), a.nota2Property(), a.nota3Property(), a.nota4Property()); }
                @Override protected double computeValue() { return a.getMedia(); }
            };
            return binding.asObject();
        });
        colMedia.setPrefWidth(65);
        colMedia.setStyle("-fx-alignment: CENTER;");

        // Situação (reativa — usa getSituacao() com três estados)
        TableColumn<Aluno, String> colSituacao = new TableColumn<>("Situação");
        colSituacao.setCellValueFactory(c -> {
            Aluno a = c.getValue();
            return Bindings.createStringBinding(
                a::getSituacao,
                a.nota1Property(), a.nota2Property(), a.nota3Property(), a.nota4Property()
            );
        });
        colSituacao.setPrefWidth(90);
        colSituacao.setStyle("-fx-alignment: CENTER;");

        tabela.getColumns().addAll(
            colMatricula, colNome, colNomeMin, colN1, colN2, colN3, colN4,
            colMedia, colSituacao, buildColunaAcoes()
        );
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        return tabela;
    }

    private TableColumn<Aluno, Double> colNota(String titulo, int numero) {
        TableColumn<Aluno, Double> col = new TableColumn<>(titulo);
        col.setPrefWidth(55);
        col.setStyle("-fx-alignment: CENTER;");
        col.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        switch (numero) {
            case 1 -> {
                col.setCellValueFactory(c -> c.getValue().nota1Property().asObject());
                col.setOnEditCommit(e -> { if (e.getNewValue() != null) e.getRowValue().setNota1(e.getNewValue()); });
            }
            case 2 -> {
                col.setCellValueFactory(c -> c.getValue().nota2Property().asObject());
                col.setOnEditCommit(e -> { if (e.getNewValue() != null) e.getRowValue().setNota2(e.getNewValue()); });
            }
            case 3 -> {
                col.setCellValueFactory(c -> c.getValue().nota3Property().asObject());
                col.setOnEditCommit(e -> { if (e.getNewValue() != null) e.getRowValue().setNota3(e.getNewValue()); });
            }
            default -> {
                col.setCellValueFactory(c -> c.getValue().nota4Property().asObject());
                col.setOnEditCommit(e -> { if (e.getNewValue() != null) e.getRowValue().setNota4(e.getNewValue()); });
            }
        }
        return col;
    }

    private TableColumn<Aluno, Void> buildColunaAcoes() {
        TableColumn<Aluno, Void> col = new TableColumn<>("Ações");
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
                    status("Aluno removido.", false);
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

    private void iniciarEdicao(Aluno aluno) {
        emEdicao = aluno;
        txtMatricula.setText(String.valueOf(aluno.getMatricula()));
        txtNome.setText(aluno.getNome());
        txtNota1.setText(String.valueOf(aluno.getNota1()));
        txtNota2.setText(String.valueOf(aluno.getNota2()));
        txtNota3.setText(String.valueOf(aluno.getNota3()));
        txtNota4.setText(String.valueOf(aluno.getNota4()));
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
                controller.validarEAtualizar(emEdicao,
                    txtMatricula.getText(), txtNome.getText(),
                    txtNota1.getText(), txtNota2.getText(), txtNota3.getText(), txtNota4.getText());
                cancelarEdicao();
                status("Aluno atualizado.", false);
            } catch (IllegalArgumentException ex) {
                status(ex.getMessage(), true);
            }
        } else {
            try {
                controller.adicionar(
                    txtMatricula.getText(), txtNome.getText(),
                    txtNota1.getText(), txtNota2.getText(), txtNota3.getText(), txtNota4.getText());
                limparCampos();
                status("Aluno adicionado.", false);
            } catch (IllegalArgumentException ex) {
                status(ex.getMessage(), true);
            }
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

    private Button cancelarBtn() {
        return (Button) btnAcao.getUserData();
    }
}
