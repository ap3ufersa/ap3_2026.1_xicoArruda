package com.cadastro;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

public class MainView {

    private final Controller ctrl = new Controller();

    public void iniciar(Stage stage) {
        Button btnAluno = new Button("Cadastrar Aluno");
        Button btnProf  = new Button("Cadastrar Professor");
        btnAluno.setPrefWidth(180);
        btnProf.setPrefWidth(180);

        btnAluno.setOnAction(e -> new AlunoDialog(ctrl, stage, null).showAndWait());
        btnProf.setOnAction(e  -> new ProfessorDialog(ctrl, stage, null).showAndWait());

        HBox botoes = new HBox(16, btnAluno, btnProf);
        botoes.setAlignment(Pos.CENTER_LEFT);

        VBox root = new VBox(12, botoes, buildTabelaAlunos(), buildTabelaProfessores());
        root.setPadding(new Insets(16));

        stage.setScene(new Scene(root, 1000, 600));
        stage.setTitle("Cadastro de Alunos e Professores com Herança");
        stage.show();
    }

    private TitledPane buildTabelaAlunos() {
        TableView<Aluno> tabela = new TableView<>(ctrl.getAlunos());
        tabela.setEditable(false);
        tabela.setPrefHeight(220);

        TableColumn<Aluno, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(c -> Bindings.createStringBinding(
            c.getValue()::getNomeMaiusculo, c.getValue().nomeCompletoProperty()));
        colNome.setPrefWidth(180);

        TableColumn<Aluno, String> colEnd = new TableColumn<>("Endereço");
        colEnd.setCellValueFactory(c -> Bindings.createStringBinding(
            () -> c.getValue().getEndereco() != null ? c.getValue().getEndereco().toString() : "",
            c.getValue().enderecoProperty()));
        colEnd.setPrefWidth(200);

        TableColumn<Aluno, Number> colMat = new TableColumn<>("Matrícula");
        colMat.setCellValueFactory(c -> c.getValue().matriculaProperty());
        colMat.setPrefWidth(90);

        TableColumn<Aluno, Double> colMedia = new TableColumn<>("Média");
        colMedia.setCellValueFactory(c -> {
            Aluno a = c.getValue();
            return Bindings.createDoubleBinding(a::getMedia,
                a.nota1Property(), a.nota2Property(), a.nota3Property(), a.nota4Property()).asObject();
        });
        colMedia.setPrefWidth(65);

        TableColumn<Aluno, String> colSit = new TableColumn<>("Situação");
        colSit.setCellValueFactory(c -> {
            Aluno a = c.getValue();
            return Bindings.createStringBinding(a::getSituacao,
                a.nota1Property(), a.nota2Property(), a.nota3Property(), a.nota4Property());
        });
        colSit.setPrefWidth(90);

        tabela.getColumns().addAll(colNome, colEnd, colMat, colMedia, colSit, buildAcoesAluno());
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return new TitledPane("Alunos", tabela);
    }

    private TitledPane buildTabelaProfessores() {
        TableView<Professor> tabela = new TableView<>(ctrl.getProfessores());
        tabela.setEditable(false);
        tabela.setPrefHeight(180);

        TableColumn<Professor, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(c -> c.getValue().nomeCompletoProperty());
        colNome.setPrefWidth(200);

        TableColumn<Professor, String> colEnd = new TableColumn<>("Endereço");
        colEnd.setCellValueFactory(c -> Bindings.createStringBinding(
            () -> c.getValue().getEndereco() != null ? c.getValue().getEndereco().toString() : "",
            c.getValue().enderecoProperty()));
        colEnd.setPrefWidth(240);

        TableColumn<Professor, Double> colBruto = new TableColumn<>("Salário Bruto");
        colBruto.setCellValueFactory(c -> c.getValue().salarioBrutoProperty().asObject());
        colBruto.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        colBruto.setPrefWidth(110);

        TableColumn<Professor, Double> colLiq = new TableColumn<>("Salário Líquido (-27,5%)");
        colLiq.setCellValueFactory(c -> Bindings.createDoubleBinding(
            c.getValue()::getSalarioLiquido, c.getValue().salarioBrutoProperty()).asObject());
        colLiq.setPrefWidth(110);

        tabela.getColumns().addAll(colNome, colEnd, colBruto, colLiq, buildAcoesProf());
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return new TitledPane("Professores", tabela);
    }

    private TableColumn<Aluno, Void> buildAcoesAluno() {
        TableColumn<Aluno, Void> col = new TableColumn<>("Ações");
        col.setPrefWidth(90); col.setSortable(false);
        col.setCellFactory(tc -> new TableCell<>() {
            private final Button btnE = new Button("✎");
            private final Button btnR = new Button("✕");
            {
                btnE.setStyle("-fx-cursor:hand;");
                btnR.setStyle("-fx-cursor:hand;-fx-text-fill:red;");
                btnE.setOnAction(e -> new AlunoDialog(ctrl,
                    getTableView().getScene().getWindow(),
                    getTableView().getItems().get(getIndex())).showAndWait());
                btnR.setOnAction(e -> ctrl.removerAluno(getTableView().getItems().get(getIndex())));
            }
            @Override protected void updateItem(Void v, boolean empty) {
                super.updateItem(v, empty);
                setGraphic(empty ? null : new HBox(6, btnE, btnR));
            }
        });
        return col;
    }

    private TableColumn<Professor, Void> buildAcoesProf() {
        TableColumn<Professor, Void> col = new TableColumn<>("Ações");
        col.setPrefWidth(90); col.setSortable(false);
        col.setCellFactory(tc -> new TableCell<>() {
            private final Button btnE = new Button("✎");
            private final Button btnR = new Button("✕");
            {
                btnE.setStyle("-fx-cursor:hand;");
                btnR.setStyle("-fx-cursor:hand;-fx-text-fill:red;");
                btnE.setOnAction(e -> new ProfessorDialog(ctrl,
                    getTableView().getScene().getWindow(),
                    getTableView().getItems().get(getIndex())).showAndWait());
                btnR.setOnAction(e -> ctrl.removerProfessor(getTableView().getItems().get(getIndex())));
            }
            @Override protected void updateItem(Void v, boolean empty) {
                super.updateItem(v, empty);
                setGraphic(empty ? null : new HBox(6, btnE, btnR));
            }
        });
        return col;
    }
}
