package br.com.keina;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static Integer acertosJogador=0;
    public static Integer acertosCpu=0;
    public static List<int[]> posicaoNavioJogador = new ArrayList<>();
    public static List<int[]> tentativasJogador = new ArrayList<>();
    public static List<int[]> tentativasCpu = new ArrayList<>();

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
        printTabuleiro(tabuleiro, 1);
        posicionarNavios(tabuleiro);

        while (acertosJogador < 10 || acertosCpu < 10) {
            atirar(tabuleiro, tabuleiroCpu);
            atirarCpu(tabuleiro, tabuleiroCpu);
            printAcertos();
        }

        printTabuleiro(tabuleiro, 1);
        printTabuleiro(tabuleiroCpu, 2);
        printAcertos();
        printVencedor();
    }

    public static void posicionarNavios(char[][] tabuleiro) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            int verificaOcorrencia = 0;

            while(verificaOcorrencia == 0){
                System.out.print("Informe a posição x do navio " + i + ": ");
                int x = sc.nextInt();
                
                System.out.print("Informe a posição y do navio " + i + ": ");
                int y = sc.nextInt();

                if(posicaoNavioJogador.isEmpty()){
                    posicaoNavioJogador.add(new int[]{x,y});
                    tabuleiro[x][y] = 'N';
                    printTabuleiro(tabuleiro, 1);
                    verificaOcorrencia=1;
                }else{
                    for (int[] eachRow : posicaoNavioJogador) {
                        if(eachRow[0] == x && eachRow[1]==y){
                            verificaOcorrencia=1;
                        }
                    }
                    if(verificaOcorrencia == 1){
                        System.out.println("Essas coordenadas já foram registradas!");
                        verificaOcorrencia=0;
                    }else{
                        posicaoNavioJogador.add(new int[]{x,y});
                        tabuleiro[x][y] = 'N';
                        printTabuleiro(tabuleiro, 1);
                        verificaOcorrencia=1;
                    }
                }
            }
        }
    }

    public static void printTabuleiro(char[][] tabuleiro, Integer jogador) {
        if(jogador == 1){
            System.out.print(
                "---------------------------------------------\n" +
                "                   JOGADOR                   \n" +
                "---------------------------------------------\n" +
                "|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |\n"
            );
        }else{
            System.out.print(
                "---------------------------------------------\n" +
                "                 COMPUTADOR                  \n" +
                "---------------------------------------------\n" +
                "|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |\n"
            );
        }

        for (int i = 0; i < 10; i++) {
            System.out.print("| " + Character.toString(65 + i) + " | ");

            for (int j = 0; j < 10; j++)
                System.out.print(tabuleiro[i][j] + " | ");

            System.out.println();
        }
    }

    public static void atirar(char[][] tabuleiro, char[][] tabuleiroCpu) {
        Scanner sc = new Scanner(System.in);
        int x,y;
        //for (int i = 0; i < 10; i++) {
        do {
            System.out.print("Informe a posição x do tiro (0 a 9): ");
            x = sc.nextInt();

            System.out.print("Informe a posição y do tiro (0 a 9): ");
            y = sc.nextInt();
        }while(verificaDuplicidade(0,x,y));

        if (tabuleiroCpu[x][y] == 'N' & tabuleiro[x][y] == 'N') {
            tabuleiro[x][y] = 'X';
            acertosJogador++;
        }
        else if (tabuleiroCpu[x][y] == 'N') {
            tabuleiro[x][y] = '*';
            acertosJogador++;
        }
        else if (tabuleiroCpu[x][y] != 'N' & tabuleiro[x][y] == 'N')
            tabuleiro[x][y] = 'n';
        else
            tabuleiro[x][y] = '-';

        printTabuleiro(tabuleiro, 1);
        //}
    }

    public static boolean verificaDuplicidade(int opcao, int x, int y) {
        if(opcao == 0){
            if(tentativasJogador.isEmpty()) {
                tentativasJogador.add(new int[]{x,y});
                return false;
            }else{
                int verificaOcorrencia = 0;
                for (int[] eachRow : tentativasJogador) {
                    if(eachRow[0] == x && eachRow[1]==y){
                        verificaOcorrencia=1;
                    }
                }
                if(verificaOcorrencia == 1){
                    System.out.println("Essas coordenadas já foram registradas!");
                    return true;
                }else{
                    tentativasJogador.add(new int[]{x,y});
                    return false;
                }
            }
        }else{
            if(tentativasCpu.isEmpty()) {
                tentativasCpu.add(new int[]{x,y});
                return false;
            }else{
                int verificaOcorrencia = 0;
                for (int[] eachRow : tentativasCpu) {
                    if(eachRow[0] == x && eachRow[1]==y){
                        verificaOcorrencia=1;
                    }
                }
                if(verificaOcorrencia == 1){
                    return true;
                }else{
                    tentativasCpu.add(new int[]{x,y});
                    return false;
                }
            }
        }
    }

    public static void atirarCpu(char[][] tabuleiro, char[][] tabuleiroCpu) {
        Random sorteio = new Random();
        int x, y;

        //do {
        do {
            x = sorteio.nextInt(10);
            y = sorteio.nextInt(10);
        }while(verificaDuplicidade(1,x,y));
        //}while (tabuleiro[x][y] != 'N' || tabuleiro[x][y] != ' ');

        if (tabuleiro[x][y] == 'N' && tabuleiroCpu[x][y] == 'N') {
            tabuleiroCpu[x][y] = 'X';
            acertosCpu++;
        }
        else if (tabuleiro[x][y] == 'N') {
            tabuleiroCpu[x][y] = '*';
            acertosCpu++;
        }
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

    public static void printAcertos() {
        System.out.print("\nAcertos Jogador: " + acertosJogador);
        System.out.print("\nAcertos Cpu: " + acertosCpu + '\n');
    }

    public static void printVencedor() {
        if(acertosJogador == 10){
            System.out.print("\nJOGADOR VENCEU!!!" + '\n');
        }else{
            System.out.print("\nCOMPUTADOR VENCEU!!!" + '\n');
        }
    }
}
