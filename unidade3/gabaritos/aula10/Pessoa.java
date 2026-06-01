package heranca;

public class Pessoa {
    private String nomeCompleto;
    private Endereco endereco;

    public Pessoa() {}

    public Pessoa(String nomeCompleto, Endereco endereco) {
        this.nomeCompleto = nomeCompleto;
        this.endereco = endereco;
    }

    public String getNomeCompleto()            { return nomeCompleto; }
    public void   setNomeCompleto(String v)    { this.nomeCompleto = v; }
    public Endereco getEndereco()              { return endereco; }
    public void   setEndereco(Endereco v)      { this.endereco = v; }

    @Override
    public String toString() {
        return "Nome: " + nomeCompleto + ", Endereço: " + endereco;
    }
}
