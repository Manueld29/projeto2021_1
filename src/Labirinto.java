public class Labirinto {

    public static void main(String[] args) {

        int movimentos=0;

        Tabuleiro Tabuleiro=new Tabuleiro();
        Tabuleiro.Criar();                      //cria um novo tabuleiro

        System.out.println("Inicio do labirinto \n Labrinto:");
        Tabuleiro.mostrar();                    //mostra o tabuleiro

        System.out.println("Numero de movimentos = "+movimentos);
        System.out.println("\n");  //dar 2 linha de espaço

        int saida=Tabuleiro.procurar();                 //procura quantas saidas tem o labirinto

        for(int cont=0;cont<saida;cont++) {             //loop para procurar o numero de saidas que retornar de procurar()
            movimentos=Tabuleiro.resolver(cont,movimentos);
            Tabuleiro.mostrar();
            System.out.println("Numero de movimentos "+movimentos);
            System.out.println("\n");
        }
        System.out.println("Encontrei as " + saida + " saidas do Labirinto em " + movimentos + " jogadas.");
    }
}
