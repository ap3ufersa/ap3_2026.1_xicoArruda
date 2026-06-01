package heranca;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private final List<Aluno> osAlunos = new ArrayList<>();

    public void adicionarAluno(Aluno aluno) { osAlunos.add(aluno); }

    public void listarAlunos() { osAlunos.forEach(System.out::println); }

    public double getMediaDaTurma() {
        return osAlunos.stream().mapToDouble(Aluno::getMedia).average().orElse(0);
    }

    public String gerarRelatorio() {
        StringBuilder sb = new StringBuilder();
        for (Aluno a : osAlunos) {
            sb.append(a.getNomeMaiusculo())
              .append(" | média: ").append(String.format("%.2f", a.getMedia()))
              .append(" | ").append(a.getSituacao()).append("\n");
        }
        sb.append("Média da turma: ").append(String.format("%.2f", getMediaDaTurma()));
        return sb.toString();
    }
}
