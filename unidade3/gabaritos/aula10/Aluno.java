public class Aluno extends Pessoa {
    private int    matricula;
    private double nota1;
    private double nota2;
    private double nota3;
    private double nota4;

    public Aluno() {}

    public Aluno(String nomeCompleto, Endereco endereco, int matricula) {
        super(nomeCompleto, endereco);
        this.matricula = matricula;
    }

    public int    getMatricula()        { return matricula; }
    public void   setMatricula(int v)   { this.matricula = v; }
    public double getNota1()            { return nota1; }
    public void   setNota1(double v)    { this.nota1 = v; }
    public double getNota2()            { return nota2; }
    public void   setNota2(double v)    { this.nota2 = v; }
    public double getNota3()            { return nota3; }
    public void   setNota3(double v)    { this.nota3 = v; }
    public double getNota4()            { return nota4; }
    public void   setNota4(double v)    { this.nota4 = v; }

    public double getMedia() {
        double soma  = nota1 + nota2 + nota3 + nota4;
        double menor = Math.min(nota1, Math.min(nota2, Math.min(nota3, nota4)));
        return (soma - menor) / 3.0;
    }

    public String getSituacao() {
        double m = getMedia();
        if (m >= 7.0) return "Aprovado";
        if (m >= 5.0) return "Recuperação";
        return "Reprovado";
    }

    public boolean isAprovado()         { return getMedia() >= 7.0; }
    public String  getNomeMaiusculo()   { return getNomeCompleto().toUpperCase(); }
    public String  getNomeMinusculo()   { return getNomeCompleto().toLowerCase(); }

    @Override
    public String toString() {
        return super.toString() + ", Matrícula: " + matricula
            + ", Média: " + String.format("%.2f", getMedia())
            + ", Situação: " + getSituacao();
    }
}
