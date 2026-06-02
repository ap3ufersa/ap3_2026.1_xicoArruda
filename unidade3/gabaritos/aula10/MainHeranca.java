public class MainHeranca {
    public static void main(String[] args) {
        Endereco endAluno = new Endereco("Rua do Aluno", "Centro", "Angicos", Estado.RN);
        Endereco endProf  = new Endereco("Rua do Professor", "Centro", "Angicos", Estado.RN);

        Aluno     aluno = new Aluno("Joãozinho da Silva", endAluno, 20231057);
        Professor prof  = new Professor("Josefa de Arruda", endProf, 1500.00);

        aluno.setNota1(8); aluno.setNota2(6); aluno.setNota3(9); aluno.setNota4(7);

        System.out.println(aluno);
        System.out.println(prof);
    }
}
