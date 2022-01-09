package br.com.keina;

import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        var tabuleiro = new char[10][10];
        var tabuleiroCpu = new char[10][10];

        for (int i  = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tabuleiro[i][j] = ' ';
            }
        }

        iniciarTabuleiroCpu(tabuleiroCpu);
        printTabuleiro(tabuleiro);
        posicionarNavios(tabuleiro);

        while (true) {
            atirar(tabuleiro, tabuleiroCpu);
            atirarCpu(tabuleiro, tabuleiroCpu);
        }
    }

    public static void posicionarNavios(char[][] tabuleiro) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            System.out.print("Informe a posição x do navio " + i + ": ");
            int x = sc.nextInt();

            System.out.print("Informe a posição y do navio " + i + ": ");
            int y = sc.nextInt();
            tabuleiro[x][y] = 'N';
            printTabuleiro(tabuleiro);
        }
    }

    public static void printTabuleiro(char[][] tabuleiro) {
        System.out.print(
            "---------------------------------------------\n" +
            "                     JOGADOR                 \n" +
            "---------------------------------------------\n" +
            "|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |\n"
        );

        for (int i = 0; i < 10; i++) {
            System.out.print("| " + Character.toString(65 + i) + " | ");

            for (int j = 0; j < 10; j++)
                System.out.print(tabuleiro[i][j] + " | ");

            System.out.println();
        }
    }

    public static void atirar(char[][] tabuleiro, char[][] tabuleiroCpu) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            System.out.print("Informe a posição x do tiro: ");
            int x = sc.nextInt();

            System.out.print("Informe a posição y do tiro: ");
            int y = sc.nextInt();

            if (tabuleiroCpu[x][y] == 'N' & tabuleiro[x][y] == 'N')
                tabuleiro[x][y] = 'X';
            else if (tabuleiroCpu[x][y] == 'N')
                tabuleiro[x][y] = '*';
            else if (tabuleiroCpu[x][y] != 'N' & tabuleiro[x][y] == 'N')
                tabuleiro[x][y] = 'n';
            else
                tabuleiro[x][y] = '-';

            printTabuleiro(tabuleiro);
        }
    }

    public static void atirarCpu(char[][] tabuleiro, char[][] tabuleiroCpu) {
        Random sorteio = new Random();
        int x, y;

        do {
            x = sorteio.nextInt(10);
            y = sorteio.nextInt(10);
        }while (tabuleiro[x][y] != 'N' || tabuleiro[x][y] != ' ');

        if (tabuleiro[x][y] == 'N' & tabuleiroCpu[x][y] == 'N')
            tabuleiroCpu[x][y] = 'X';
        else if (tabuleiro[x][y] == 'N')
            tabuleiroCpu[x][y] = '*';
        else if (tabuleiro[x][y] != 'N' & tabuleiroCpu[x][y] == 'N')
            tabuleiroCpu[x][y] = 'n';
        else
            tabuleiroCpu[x][y] = '-';
    }

    public static void iniciarTabuleiroCpu(char[][] tabuleiroCpu) {
        Random sorteio = new Random();

        for(int linha=0 ; linha < 10 ; linha++ )
            for(int coluna=0 ; coluna < 10 ; coluna++ )
                tabuleiroCpu[linha][coluna] = ' ';

        for (int qtdNavio = 0; qtdNavio < 10; qtdNavio++) {
            int x, y;
            do {
                x = sorteio.nextInt(10);
                y = sorteio.nextInt(10);
            } while (tabuleiroCpu[x][y] != ' ');

            tabuleiroCpu[x][y] = 'N';
        }
    }
}
