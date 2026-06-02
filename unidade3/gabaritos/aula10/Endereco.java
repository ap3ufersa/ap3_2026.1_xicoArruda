
public class Endereco {
    private String rua;
    private String bairro;
    private String cidade;
    private Estado estado;

    public Endereco() {}

    public Endereco(String rua, String bairro, String cidade, Estado estado) {
        this.rua    = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }

    public String getRua()             { return rua; }
    public void   setRua(String v)     { this.rua = v; }
    public String getBairro()          { return bairro; }
    public void   setBairro(String v)  { this.bairro = v; }
    public String getCidade()          { return cidade; }
    public void   setCidade(String v)  { this.cidade = v; }
    public Estado getEstado()          { return estado; }
    public void   setEstado(Estado v)  { this.estado = v; }

    @Override
    public String toString() {
        return rua + ", " + bairro + ", " + cidade + " - " + estado;
    }
}
