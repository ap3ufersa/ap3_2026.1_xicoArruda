public class Aluno {

    int matricula = -1;
    String nomeCompleto = "SemNome";
    double nota1 = -1;
    double nota2 = -1;
    double nota3 = -1;
    double nota4 = -1;

    public Aluno(){
        nomeCompleto = 
    }

    public Aluno(String nomeDoAluno){
        nomeCompleto = nomeDoAluno;
    }
    public double getMedia() {

        double menorNota1Nota2 = Math.min(nota1, nota2);
        double menorNota3Nota4 = Math.min(nota3, nota4);
        double menorNota = Math.min(menorNota1Nota2,menorNota3Nota4);

        double media = (nota1 + nota2 + nota3 + nota4 - menorNota) / 3.0;

        return media;
    }
  

    String getSituacao(){
        if (getMedia() >= 7.0)
            return "Aprovado";
        
        if (getMedia() >= 5.0)
            return "Recuperação";

        return "Reprovado";
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


    public int getMatricula() {
        return matricula;
    }


    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }


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


    public double getNota4() {
        return nota4;
    }


    public void setNota4(double nota4) {
        this.nota4 = nota4;
    }


    @Override
    public String toString() {
        return "Aluno [matricula=" + matricula + ", nomeCompleto=" + nomeCompleto + ", nota1=" + nota1 + ", nota2="
                + nota2 + ", nota3=" + nota3 + ", nota4=" + nota4 + ", getMedia()=" + getMedia() + ", getSituacao()="
                + getSituacao() + "]";
    }


}