import java.util.Scanner;

public class TestaTurma {
    public static void main(String[] args) {
        Turma queridosDeAp3 = new Turma();
        Aluno xico = new Aluno();
        Scanner entrada = new Scanner(System.in);
        
        System.out.println("Nomezinho?");
        xico.setNomeCompleto(entrada.nextLine());
        System.out.println("Nota1");
        xico.setNota1(entrada.nextDouble());
        System.out.println("Nota2");
        xico.setNota2(entrada.nextDouble());
        System.out.println("Nota3");
        xico.setNota3(entrada.nextDouble());
        System.out.println("Nota4");
        xico.setNota4(entrada.nextDouble());

        queridosDeAp3.adicionarAluno(xico);

        System.out.println(queridosDeAp3.gerarRelatorio());

        entrada.close();
    }
}
