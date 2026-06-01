package heranca;

import java.util.Scanner;

public class TestaTurma {
    public static void main(String[] args) {
        Turma turma = new Turma();
        Scanner sc   = new Scanner(System.in);

        System.out.print("Nome do aluno: ");
        String nome = sc.nextLine();

        Endereco end = new Endereco();
        System.out.print("Cidade: ");
        end.setCidade(sc.nextLine());
        end.setEstado(Estado.RN);

        Aluno aluno = new Aluno(nome, end, 0);

        System.out.print("Nota 1: "); aluno.setNota1(Double.parseDouble(sc.nextLine().replace(',', '.')));
        System.out.print("Nota 2: "); aluno.setNota2(Double.parseDouble(sc.nextLine().replace(',', '.')));
        System.out.print("Nota 3: "); aluno.setNota3(Double.parseDouble(sc.nextLine().replace(',', '.')));
        System.out.print("Nota 4: "); aluno.setNota4(Double.parseDouble(sc.nextLine().replace(',', '.')));

        turma.adicionarAluno(aluno);
        System.out.println(turma.gerarRelatorio());
        sc.close();
    }
}
