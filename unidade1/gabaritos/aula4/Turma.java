import java.util.ArrayList;
import java.util.List;

public class Turma {

    List<Aluno> osAlunos = new ArrayList<>();

    void adicionarAluno(Aluno novoAluno){
        osAlunos.add(novoAluno);
    }

    void listarAlunos(){
        for (Aluno umAluno : osAlunos) {
            System.out.println(umAluno);
        }
    }

    double getMediaDaTurma(){
        double mediaGeral = -1;
        double soma = 0;
        for (Aluno umAluno : osAlunos)
            soma = soma + umAluno.getMedia();

        mediaGeral = soma / osAlunos.size();

        return mediaGeral;
    }


//da turma com nome em maiúsculo,
//  média do aluno, situação de cada aluno e 
// a média geral da turma.

    String gerarRelatorio(){
        String textoFinal = "";
        for (Aluno umAluno : osAlunos){
            textoFinal += umAluno.getNomeMaiusculo();
            textoFinal += " media=" + umAluno.getMedia();
            textoFinal += " situacao=" + umAluno.getSituacao();
            textoFinal += "\n";
        }
        textoFinal += " media da turma=" + getMediaDaTurma();
        return textoFinal;
    }





}
