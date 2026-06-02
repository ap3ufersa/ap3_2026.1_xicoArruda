import java.util.ArrayList;
import java.util.List;

public class MainPolimorfismo {
    public static void main(String[] args) {
        Endereco endAluno = new Endereco("Rua do Aluno", "Centro", "Angicos", Estado.RN);
        Endereco endProf = new Endereco("Rua do Professor", "Centro", "Angicos", Estado.RN);
        Endereco endPessoa = new Endereco("Rua da Pessoa", "Bairro Novo", "Natal", Estado.RN);

        Aluno aluno = new Aluno("Joãozinho da Silva", endAluno, 20231057);
        aluno.setNota1(8);
        aluno.setNota2(6);
        aluno.setNota3(9);
        aluno.setNota4(7);

        Professor prof = new Professor("Josefa de Arruda", endProf, 1500.00);

        Pessoa pessoa = new Pessoa("Maria da Silva", endPessoa);

        // Lista polimórfica - referência do tipo Pessoa pode conter Aluno, Professor ou Pessoa
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(aluno);
        pessoas.add(prof);
        pessoas.add(pessoa);

        // Exemplo 1: chamada polimórfica com loop
        System.out.println("=== Exibindo todas as pessoas (polimorfismo com toString()) ===");
        for (Pessoa p : pessoas) {
            System.out.println(p); // toString() é resolvido em tempo de execução
        }

        // Exemplo 2: chamada polimórfica com método específico usando instanceof
        System.out.println("\n=== Informações específicas por tipo ===");
        for (Pessoa p : pessoas) {
            if (p instanceof Aluno) {
                Aluno a = (Aluno) p;
                System.out.printf("Aluno: %s - Média: %.2f - Situação: %s%n",
                        a.getNomeCompleto(), a.getMedia(), a.getSituacao());
            } else if (p instanceof Professor) {
                Professor pr = (Professor) p;
                System.out.printf("Professor: %s - Salário Bruto: R$ %.2f - Líquido: R$ %.2f%n",
                        pr.getNomeCompleto(), pr.getSalarioBruto(), pr.getSalarioLiquido());
            } else {
                System.out.printf("Pessoa: %s - Endereço: %s%n",
                        p.getNomeCompleto(), p.getEndereco());
            }
        }

        // Exemplo 3: chamada polimórfica com método comum (se existisse)
        System.out.println("\n=== Nomes em maiúsculas (método de Aluno disponível apenas para instâncias de Aluno) ===");
        for (Pessoa p : pessoas) {
            if (p instanceof Aluno) {
                Aluno a = (Aluno) p;
                System.out.println("Nome maiúsculo do aluno: " + a.getNomeMaiusculo());
            }
        }

        // Exemplo 4: criação polimórfica direta
        System.out.println("\n=== Criação polimórfica direta ===");
        Pessoa p1 = new Aluno("Carlos Souza", endAluno, 20241001);
        Pessoa p2 = new Professor("Ana Lima", endProf, 2000.00);
        Pessoa p3 = new Pessoa("Roberto Santos", endPessoa);

        System.out.println("p1 (Aluno como Pessoa): " + p1);
        System.out.println("p2 (Professor como Pessoa): " + p2);
        System.out.println("p3 (Pessoa como Pessoa): " + p3);
    }
}
