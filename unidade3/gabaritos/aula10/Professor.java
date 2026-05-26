/**
 * Representa um professor, que é uma especialização de Pessoa.
 * Adiciona o atributo salário.
 *
 * @author SeuNome
 * @version 1.0
 * @since 2025-06-27
 */

public class Professor extends Pessoa {
    private double salario;

    public Professor(String nome, Endereco endereco, double salario) {
        super(nome, endereco);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return super.toString() + ", Salário: " + salario;
    }
}
