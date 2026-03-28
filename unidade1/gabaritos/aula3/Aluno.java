public class Aluno {

    String nomeCompleto = "SemNome";

    double nota1 = -1;

    double nota2 = -1;

    double nota3 = -1;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public double getNota1() {
        return nota1;
    }

    public void setNota1(double nota1) {
        this.nota1 = nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota2(double nota2) {
        this.nota2 = nota2;
    }

    public double getNota3() {
        return nota3;
    }

    public void setNota3(double nota3) {
        this.nota3 = nota3;
    }

    public double getMedia() {
        return (nota1 + nota2 + nota3) / 3;
    }
  

    boolean isAprovadoMedia(){
        if (getMedia() >= 7.0)
            return true;

        return false;
    }

    String getNomeMaiusculo(){
        return nomeCompleto.toUpperCase();
    }

    String getNomeMinusculo()
    {
        return nomeCompleto.toLowerCase();
    }

    @Override
    public String toString() {
        return "Classe Aluno. nomeCompleto=" + nomeCompleto + 
                ", nota1=" + nota1 + ", nota2=" + nota2 + ", nota3=" + nota3
                + ", Média=" + getMedia() + ", Aprovado?" + isAprovadoMedia() + ", Nome em MAIÚSCULO="
                + getNomeMaiusculo() + ", Nome em minúsculo=" + getNomeMinusculo() + "]";
    }

}