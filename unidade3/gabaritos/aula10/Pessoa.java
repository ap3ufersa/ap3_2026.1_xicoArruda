/**
 * Superclasse, base que representa uma pessoa genérica.
 * Contém atributos comuns como nome e endereço.
 *
 * @author Francisco Arruda
 * @version 1.0
 * @since 2025-06-27
 */

public class Pessoa {
    private String nomeCompleto;
    private Endereco endereco;

    public Pessoa() {
    }

    public Pessoa(String nome, Endereco endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Endereço: " + endereco;
    }
}
