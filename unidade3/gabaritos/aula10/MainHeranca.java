//Gere a documentação com: javadoc -d documentacao -sourcepath . *.java

public class MainHeranca {
    public static void main(String[] args) {
        Endereco enderecoAluno = new Endereco("Rua do Aluno", "Centro", "Angicos");
        Endereco enderecoProf = new Endereco("Rua do Professor", "Centro", "Angicos");

        Aluno umAluno = new Aluno("Joãozinho da Silva", enderecoAluno, "20231057");
        Professor umProfessor = new Professor("Josefa de Arruda", enderecoProf, 1500.00);

        System.out.println(umAluno);
        System.out.println(umProfessor);
    }
}