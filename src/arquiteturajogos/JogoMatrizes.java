package arquiteturajogos;

import conexaobd.Conexao;
import controles.FXMLMatrizesController;
import controles.GerenciadorDeTelas;
import java.io.IOException;
import static java.nio.file.Files.delete;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jogomatematicafx.*;

/**
 *
 * @author arcn
 */
public class JogoMatrizes {

    private int nivelInicio;
    public String tipoJogo;
    public Conexao conexao;
    public int contador;
    
    // Pontos e nivel:
    public int pontos; //0 - 10
    public int nivel;  //0 - 10
    public float desempenho;

    // Pontos por tipo:
    public int pontosSoma;
    public int pontosMult;
    public int pontosTransp;
    public int pontosDet;
    public String nivelJogador;
    
    public JogoMatrizes(String tipo) throws IOException {
        conexao = new Conexao();       
        nivelInicio = JogoMatematicaFX.jogoPrincipal.pontuacao; 
        if (nivelInicio <= 25) { 
            this.nivel = 0;
        } else if (nivelInicio <= 70) { 
            this.nivel = 5; 
        } else { 
            this.nivel = 10;
        }
        this.contador = -1;   
        this.tipoJogo = tipo;
        this.pontos = 0;
        this.desempenho = 0;
        this.pontosSoma = this.pontosMult = this.pontosDet = this.pontosTransp = 0;
    }

    public boolean jogoEncerrado = false;

    // Determinante
    public int det;
    public int detE1;
    public int detE2;

    // Método para definição do tamanho da matriz
    public int linA, colA;
    public int linB, colB;
    public int linR, colR;

    // Matrizes para serem calculadas
    public int MatA[][]; // A
    public int MatB[][]; // B
    public int MatR[][]; // Matriz Resultado 
    public int MatRE1[][];
    public int MatRE2[][];

    // Lihas para definição de labels: 
    public String operacao = "";

    // Operadores: 
    public int operador = 1; // Valores : (1, 2, 3 , 4)
    public int coefMultA = 1;
    public int coefMultB = 1;

    // Guardar o numero do painel da resposta certa; 
    public int painelCerto;
    public int escolhaPainel;

    public void defineJogo() throws IOException {
        //Sobre o nível
        nivel++;
        //Sobe o contador
        contador++;
        
        //Define linha e coluna
        // Matrizes Quadradas 
        linA = 3;
        linB = 3;
        colA = linA;
        colB = linB;
        linR = linA;
        colR = colA;

        // Instanciação das Matrizes:
        MatA = new int[linA][colA];
        MatB = new int[linB][colB];
        MatR = new int[linR][colR];
        MatRE1 = new int[linR][colR];
        MatRE2 = new int[linR][colR];

        //Define valores para A
        defineA();

        if (this.tipoJogo.equalsIgnoreCase("Soma") || this.tipoJogo.equalsIgnoreCase("Multiplicacao")) {
            //Define valores para B
            defineB();
        }

        if (tipoJogo.equalsIgnoreCase("Soma")) {
            operador = 1;
        } else if (tipoJogo.equalsIgnoreCase("Multiplicacao")) {
            operador = 2;
        } else if (tipoJogo.equalsIgnoreCase("Transposicao")) {
            operador = 3;
        } else {
            operador = 4;
        }

        // Define coeficientes e a operação
        if (this.tipoJogo.equalsIgnoreCase("Multiplicacao")) {
            if (nivel <= 10) {
                coefMultA = 1;
                coefMultB = 1;
            } else {
                coefMultA = (int) ((Math.random() * 3) + 1);
                coefMultB = (int) ((Math.random() * 3) + 1);
            }
        } else {
            if (nivel <= 5) {
                coefMultA = (int) ((Math.random() * 2) + 1);
                coefMultB = (int) ((Math.random() * 2) + 1);
            } else if (nivel <= 10) {
                coefMultA = (int) ((Math.random() * 3) + 1);
                coefMultB = (int) ((Math.random() * 3) + 1);
            } else if (nivel <= 15) {
                coefMultA = (int) ((Math.random() * 4) + 1);
                coefMultB = (int) ((Math.random() * 4) + 1);
            } else {
                coefMultA = (int) ((Math.random() * 5) + 1);
                coefMultB = (int) ((Math.random() * 5) + 1);
            }
        }

        // Operadores: 
        switch (operador) {
            case 1:
                operacao = coefMultA + "A + " + coefMultB + "B";
                break;
            case 2:
                if (this.nivel <= 10) {
                    operacao = "A x B";
                } else {
                    operacao = coefMultA + "A x " + coefMultB + "B";
                }
                break;
            case 3:
                operacao = " x transposta(A)";
                break;
            case 4:
                operacao = "det(A)";
                break;
        }

        // Define a Operação e a resposta - MatR: 
        switch (operador) {
            case 1: // Soma de A com B :   
                calculaSoma();
                break;
            case 2: // Mult. de A com B:
                calculaMultiplicacao();
                break;
            case 3: // TRansposta
                calculaTransposta();
                break;
            case 4: // Determinante  
                calculaDeterminante();
                break;

        }
        //Define painel correto
        painelCerto = (int) ((Math.random() * 3) + 1);

    }

    public void defineA() {
        for (int i = 0; i < linA; i++) {
            for (int j = 0; j < colA; j++) {
                if (nivel <= 5) {
                    MatA[i][j] = ((int) (Math.random() * 2)) + 1;
                } else if (nivel <= 10) {
                    MatA[i][j] = ((int) (Math.random() * 3) + 1);
                } else if (nivel <= 15) {
                    MatA[i][j] = ((int) (Math.random() * 4) + 1);
                } else {
                    MatA[i][j] = ((int) (Math.random() * 5) + 1);
                }
            }
        }
    }

    public void defineB() {
        for (int i = 0; i < linB; i++) {
            for (int j = 0; j < colB; j++) {
                if (nivel <= 5) {
                    MatB[i][j] = ((int) (Math.random() * 2)) + 1;
                } else if (nivel <= 10) {
                    MatB[i][j] = ((int) (Math.random() * 3) + 1);
                } else if (nivel <= 15) {
                    MatB[i][j] = ((int) (Math.random() * 4) + 1);
                } else {
                    MatB[i][j] = ((int) (Math.random() * 5) + 1);
                }
            }
        }
    }

    public void calculaSoma() {
        int auxLin = ((int) (Math.random() * 3));
        int auxCol = ((int) (Math.random() * 3));
        for (int i = 0; i < linR; i++) {
            for (int j = 0; j < colR; j++) {
                MatR[i][j] = (coefMultA * MatA[i][j]) + (coefMultB * MatB[i][j]);
                if (i == auxLin) {
                    MatRE1[i][j] = (coefMultA+1) * (MatA[i][j] + MatB[i][j]);
                } else {
                    MatRE1[i][j] = (coefMultA * MatA[i][j]) + (coefMultB * MatB[i][j]);
                }
                if (j == auxCol) {
                    MatRE2[i][j] = (coefMultA * MatA[i][j]) - (coefMultB * MatB[i][j]);
                } else {
                    MatRE2[i][j] = (coefMultA * MatA[i][j]) + (coefMultB * MatB[i][j]);
                }
            }
        }
    }

    public void calculaMultiplicacao() {
        int auxLin = ((int) (Math.random() * 3));
        int auxCol = ((int) (Math.random() * 3));
        for (int i = 0; i < linR; i++) {
            for (int j = 0; j < colR; j++) {
                MatR[i][j] = 0;
                MatRE1[i][j] = 0;
                MatRE2[i][j] = 0;
                for (int k = 0; k < linB; k++) {
                    MatR[i][j] += (coefMultA * MatA[i][k]) * (MatB[k][j] * coefMultB);
                    if (i == auxLin) {
                        MatRE1[i][j] += (coefMultA * MatA[i][k]) * ((MatB[k][j] + 1) * coefMultB);
                    } else {
                        MatRE1[i][j] += (coefMultA * MatA[i][k]) * (MatB[k][j] * coefMultB);
                    }
                    if (j == auxCol) {
                        MatRE2[i][j] += (coefMultA * MatA[i][k]) + (MatB[k][j] * coefMultB);
                    } else {
                        MatRE2[i][j] += (coefMultA * MatA[i][k]) * (MatB[k][j] * coefMultB);
                    }

                }
            }
        }
    }

    public void calculaTransposta() {
        int auxLin = ((int) (Math.random() * 3));
        int auxCol = ((int) (Math.random() * 3));
        for (int i = 0; i < linR; i++) {
            for (int j = 0; j < colR; j++) {
                MatR[i][j] = MatA[j][i] * coefMultA;
                if (i == auxLin) {
                    MatRE1[i][j] = (MatA[i][j] * coefMultA) - 1;
                } else {
                    MatRE1[i][j] = MatA[j][i] * coefMultA;
                }
                if (j == auxCol) {
                    MatRE2[i][j] = MatA[j][i] * (coefMultA + 1);
                } else {
                    MatRE2[i][j] = MatA[j][i] * coefMultA;
                }
            }
        }
    }

    public void calculaDeterminante() {
        det = ((MatA[0][0] * MatA[1][1] * MatA[2][2]) + (MatA[0][1] * MatA[1][2] * MatA[2][0])
                + (MatA[0][2] * MatA[1][0] * MatA[2][1]))
                - ((MatA[0][0] * MatA[1][2] * MatA[2][1]) + (MatA[0][1] * MatA[1][0] * MatA[2][2])
                + (MatA[2][0] * MatA[1][1] * MatA[0][2]));
        
        detE2 = ((MatA[0][0] * MatA[1][1] * MatA[2][2]) - (MatA[1][0] * MatA[2][1] * MatA[0][2])
                - (MatA[0][1] * MatA[1][2] * MatA[2][0]))
                + ((MatA[0][2] * MatA[1][1] * MatA[2][0]) + (MatA[1][2] * MatA[2][1] * MatA[0][0])
                + (MatA[0][1] * MatA[2][2] * MatA[1][1]));
        
        detE1 = det + ((int) (Math.random() * 3)) + 1;
        
        if (det == detE2) { 
            detE2 += 1;
        }
        
        if (det == detE1) {
            detE1 += 1;
        }
        
        
    }

    public void vefificaAcerto(int valor) {
        escolhaPainel = valor;
        if (escolhaPainel == painelCerto) {
            pontos++;
            if (tipoJogo.equalsIgnoreCase("Soma")) {
                pontosSoma++;
            } else if (tipoJogo.equalsIgnoreCase("Multiplicacao")) {
                pontosMult++;
            } else if (tipoJogo.equalsIgnoreCase("Transposicao")) {
                pontosTransp++;
            } else {
                pontosDet++;
            }
        }
    }

}
