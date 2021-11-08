public class Tabuleiro {
    private String[][] tabuleiro = new String[10][10]; // Array que define o Tabuleiro
    private int[] saida = new int[100]; //Array que recebe as posicoes das saidas
    private int[] posicao = new int[2]; //Array que recebe a posiçao atual


    public void Criar() {  //Esta funcao cria o Tabuleiro
        for (int linha = 0; linha < tabuleiro.length; linha++) {  // Anda com as linhas de 0 ate 9
            for (int coluna = 0; coluna < tabuleiro.length; coluna++) {  //Andas com as colunas de 0 ate 9
                this.tabuleiro[linha][coluna] = (" * "); // Definir todas as casas do labirinto como " * ".
                if (linha <= 3) {                          // Definir todos os espaços vazios do tabuleiro.
                    this.tabuleiro[linha][3] = ("   ");
                }
                if (linha >= 3 && linha <= 8) {
                    this.tabuleiro[linha][5] = ("   ");
                }
                if (linha == 8 || linha == 9) {
                    this.tabuleiro[linha][4] = ("   ");
                    this.tabuleiro[linha][7] = ("   ");
                }
                if (linha >= 3 && linha <= 5) {
                    this.tabuleiro[linha][8] = ("   ");
                }
                if (coluna >= 4 && coluna <= 6) {
                    this.tabuleiro[1][coluna] = ("   ");
                }
                if (coluna == 6 || coluna == 7) {
                    this.tabuleiro[2][coluna] = ("   ");
                    this.tabuleiro[7][coluna] = ("   ");
                }
                this.tabuleiro[3][4] = ("   ");
                this.tabuleiro[3][7] = ("   ");
                this.tabuleiro[4][4] = ("   ");

                this.tabuleiro[4][4] = (" P ");    //Definir o ponto de partida
            }
        }
    }

    public void mostrar() {  // Metodo para imprimir o tabuleiro
        for (int linha = 0; linha < tabuleiro.length; linha++) {
            for (int coluna = 0; coluna < tabuleiro.length; coluna++) {
                System.out.print(this.tabuleiro[linha][coluna]);  //print de cada uma das casas do tabuleiro
            }
            System.out.println(""); // Mudar de linha quando chega ao fim das colunas do tabuleiro
        }
    }

    public int procurar() {  // Funcao que procora o numero de saidas e grava as posicoes no array saidas
        int saida = 0;
        int cont = 0;
        for (int linha = 0; linha < tabuleiro.length; linha++) {
            for (int coluna = 0; coluna < tabuleiro.length; coluna++) {
                if (linha == 0 || linha == 9) {                              //verificar quantas saidas existem nas linhas 0 e 9
                    if (this.tabuleiro[linha][coluna] == ("   ")) {
                        this.saida[cont] = linha;
                        this.saida[cont + 1] = coluna;
                        saida++;
                        cont = cont + 2;
                    }
                }
                if (coluna == 0 || coluna == 9 && (linha >= 1 && linha <= 8)) {   //verificar quantas saidas existem nas colunas 0 e 9
                    if (this.tabuleiro[linha][coluna] == ("   ")) {
                        this.saida[cont] = linha;
                        this.saida[cont + 1] = coluna;
                        saida++;
                        cont = cont + 2;

                    }
                }
            }
        }
        return saida;

    }

    public int resolver(int a, int mov) {
        if (a != 0) {
            a = (a * 2);
        }

        this.posicao[0] = 4; //posicao inicial
        this.posicao[1] = 4;  //
        int px = this.posicao[0]; // posicao atual de x
        int py = this.posicao[1];
        int sx = this.saida[a]; //posicao de x(linha) da saida atual de
        int sy = this.saida[a + 1];
        int c, b, e, d;
        c = 0;
        b = 0;
        e = 0;
        d = 0;
        if ((px + sx) > (py + sy)) {  //
                                        // linhas tem prioridade
            if (px - sx > 0) {
                c = 4;
                b = 2;
            } else {
                b = 4;
                c = 2;
            }
            if (py - sy > 0) {
                e = 3;
                d = 1;
            } else {
                d = 3;
                e = 1;
            }
            if (px - sx == 0) {
                if (py - sy > 0) {
                    e = 4;
                    c = 3;
                    b = 2;
                    d = 1;
                } else {
                    d = 4;
                    c = 3;
                    b = 2;
                    e = 1;
                }
            }
            if (py - sy == 0) {
                if (px - sx > 0) {
                    c = 4;
                    d = 3;
                    e = 2;
                    b = 1;
                } else {
                    b = 4;
                    d = 3;
                    e = 2;
                    c = 1;
                }
            }


        } else {
                                           //colunas tem proiridade
            if (py - sy > 0) { //
                e = 4;
                d = 2;
            } else {
                d = 4;
                e = 2;
            }
            if (px - sx > 0) {
                c = 3;
                b = 1;
            } else {
                b = 3;
                c = 1;
            }

        }
        while (this.tabuleiro[saida[a]][saida[a + 1]] != (" S ")) {

            int x = this.posicao[0];
            int y = this.posicao[1];


            for (int z = 4; z > 0; z--) {
                if (c == z && this.tabuleiro[x - 1][y] != (" * ") && this.tabuleiro[x - 1][y] != (" S ")) {  //mov cima
                    this.tabuleiro[x - 1][y] = (" S ");
                    this.posicao[0] = x - 1;
                    this.posicao[1] = y;
                    z = 0;
                    mov++;
                }
                if (b == z && this.tabuleiro[x + 1][y] != (" * ") && this.tabuleiro[x + 1][y] != (" S ")) {  //mov baixo
                    this.tabuleiro[x + 1][y] = (" S ");
                    this.posicao[0] = x + 1;
                    this.posicao[1] = y;
                    z = 0;
                    mov++;
                }
                if (e == z && this.tabuleiro[x][y - 1] != (" * ") && this.tabuleiro[x][y - 1] != (" S ")) {  //mov esquerda
                    this.tabuleiro[x][y - 1] = (" S ");
                    this.posicao[0] = x;
                    this.posicao[1] = y - 1;
                    z = 0;
                    mov++;
                }
                if (d == z && this.tabuleiro[x][y + 1] != (" * ") && this.tabuleiro[x][y + 1] != (" S ")) {  //mov direita
                    this.tabuleiro[x][y + 1] = (" S ");
                    this.posicao[0] = x;
                    this.posicao[1] = y + 1;
                    z = 0;
                    mov++;
                }
                if (z == 1) {                // quando nao é posivel fazer uma jogada para uma casa nova
                    for (int zz = 4; zz > 0; zz--) {
                        if (c == zz && this.tabuleiro[x - 1][y] != (" * ")) {  //mov cima
                            System.out.println("Andei para cima ");
                            this.tabuleiro[x - 1][y] = (" S ");
                            this.posicao[0] = x - 1;
                            this.posicao[1] = y;
                            z = 0;
                            zz = 0;
                            mov++;
                        }
                        if (b == zz && this.tabuleiro[x + 1][y] != (" * ")) {  //mov baixo
                            this.tabuleiro[x + 1][y] = (" S ");
                            this.posicao[0] = x + 1;
                            this.posicao[1] = y;
                            z = 0;
                            zz = 0;
                            mov++;
                        }
                        if (e == zz && this.tabuleiro[x][y - 1] != (" * ")) {  //mov esquerda
                            this.tabuleiro[x][y - 1] = (" S ");
                            this.posicao[0] = x;
                            this.posicao[1] = y - 1;
                            z = 0;
                            zz = 0;
                            mov++;
                        }
                        if (d == zz && this.tabuleiro[x][y + 1] != (" * ")) {  //mov direita
                            this.tabuleiro[x][y + 1] = (" S ");
                            this.posicao[0] = x;
                            this.posicao[1] = y + 1;
                            z = 0;
                            zz = 0;
                            mov++;
                        }
                    }
                }
            }
        }
        return mov;
    }
}

















