public class Professor extends Pessoa {
    private double salarioBruto;

    public Professor() {}

    public Professor(String nomeCompleto, Endereco endereco, double salarioBruto) {
        super(nomeCompleto, endereco);
        this.salarioBruto = salarioBruto;
    }

    public double getSalarioBruto()          { return salarioBruto; }
    public void   setSalarioBruto(double v)  { this.salarioBruto = v; }

    /** Imposto: 27,5% */
    public double getSalarioLiquido()        { return salarioBruto * 0.725; }

    @Override
    public String toString() {
        return super.toString()
            + ", Salário bruto: " + String.format("%.2f", salarioBruto)
            + ", Salário líquido: " + String.format("%.2f", getSalarioLiquido());
    }
}
