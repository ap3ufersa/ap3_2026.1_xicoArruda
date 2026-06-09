import java.util.ArrayList;
import java.util.List;

public class MainPolimorfismo {
    public static void main(String[] args) {
        Endereco endAluno = new Endereco("Rua do Aluno", "Centro", "Angicos", Estado.RN);
        Endereco endProf = new Endereco("Rua do Professor", "Centro", "Angicos", Estado.RN);
        Endereco endPessoa = new Endereco("Rua da Pessoa", "Bairro Mangabeira", "Jampa", Estado.PB);

        Aluno umAluno = new Aluno("Joãozinho da Silva", endAluno, 20231057);
        //aluno.setNota1(8); aluno.setNota2(6); aluno.setNota3(9); aluno.setNota4(7);

        Professor umProfessor = new Professor("Josefa de Arruda", endProf, 1500.00);
        
		Pessoa umaPessoa = new Pessoa("Maria da Silva", endPessoa);

        // Lista polimórfica
        List<Pessoa> asPessoas = new ArrayList<>();
        asPessoas.add(umAluno);
        asPessoas.add(umProfessor);
        asPessoas.add(umaPessoa);

		System.out.println("List<Pessoa> asPessoas = " + asPessoas);
		
        // Polimorfismo com toString()
        System.out.println("Explicação do @Override");
        for (Pessoa p : asPessoas) {
            System.out.println(p); // Dinamicamente decide qual toString() chamar
        }

        // Métodos específicos por tipo
        for (Pessoa p : asPessoas) {
            if (p instanceof Aluno) { //Não curto, mas tem.
                Aluno a = (Aluno) p;
                System.out.println("Nome maiúsculo do aluno: " + a.getNomeMaiusculo());
            }
        }

        Pessoa alunoPessoa = new Aluno("Carlos Souza", endAluno, 20241001);
        Pessoa professorPessoa = new Professor("Ana Lima", endProf, 2000.00);
        Pessoa pessoaPessoa = new Pessoa("Roberto Santos", endPessoa);

        System.out.println("Aluno tratado como Pessoa: " + alunoPessoa);
        System.out.println("Professor tratado como Pessoa: " + professorPessoa);
        System.out.println("Pessoa tratada como Pessoa: " + pessoaPessoa);
    }
}
