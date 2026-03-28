import java.util.Random;

public class TestaAluno {

    public static void main(String[] args) {
        Random geradorAleatorio = new Random();
        int limiteSuperior = 10;

        for (int i = 0; i < 10; i++) {
            Aluno umAluno = new Aluno();
            umAluno.setNomeCompleto("Aluno" + i + 1);           
            umAluno.setNota1(geradorAleatorio.nextInt(limiteSuperior));
            umAluno.setNota2(geradorAleatorio.nextInt(limiteSuperior));
            umAluno.setNota3(geradorAleatorio.nextInt(limiteSuperior));
            
            System.out.println("aluno =" + umAluno);
    
        }
        


    }
}
