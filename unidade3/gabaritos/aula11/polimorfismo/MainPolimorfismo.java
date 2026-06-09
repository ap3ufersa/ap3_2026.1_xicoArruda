import java.util.ArrayList;
import java.util.List;

public class MainPolimorfismo {
    public static void main(String[] args) {
        Endereco endAluno = new Endereco("Rua do Aluno", "Centro", "Angicos", Estado.RN);
        Endereco endProf = new Endereco("Rua do Professor", "Centro", "Angicos", Estado.RN);
        Endereco endPessoa = new Endereco("Rua da Pessoa", "Bairro Novo", "Natal", Estado.RN);

        Aluno aluno = new Aluno("Joãozinho da Silva", endAluno, 20231057);
        aluno.setNota1(8); aluno.setNota2(6); aluno.setNota3(9); aluno.setNota4(7);

        Professor prof = new Professor("Josefa de Arruda", endProf, 1500.00);

        Pessoa pessoa = new Pessoa("Maria da Silva", endPessoa);

        // Lista polimórfica
        List<Pessoa> asPessoas = new ArrayList<>();
        asPessoas.add(aluno);
        asPessoas.add(prof);
        asPessoas.add(pessoa);

        // Polimorfismo com toString()
        System.out.println("=== Polimorfismo com toString() ===");
        for (Pessoa p : asPessoas) {
            System.out.println(p); // Dinamicamente decide qual toString() chamar
        }

        // Métodos específicos por tipo
        System.out.println("\n=== Método exclusivo de Aluno ===");
        for (Pessoa p : asPessoas) {
            if (p instanceof Aluno) {
                Aluno a = (Aluno) p;
                System.out.println("Nome maiúsculo do aluno: " + a.getNomeMaiusculo());
            }
        }

        // Superclasse com instância de subclasse
        Pessoa p1 = new Aluno("Carlos Souza", endAluno, 20241001);
        Pessoa p2 = new Professor("Ana Lima", endProf, 2000.00);
        Pessoa p3 = new Pessoa("Roberto Santos", endPessoa);

        System.out.println("Aluno tratado como Pessoa: " + p1);
        System.out.println("Professor tratado como Pessoa: " + p2);
        System.out.println("Pessoa tratada como Pessoa: " + p3);
    }
}
