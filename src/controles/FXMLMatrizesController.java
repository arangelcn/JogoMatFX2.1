/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import arquiteturajogos.Jogo;
import arquiteturajogos.JogoMatrizes;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import com.sun.javafx.fxml.BeanAdapter;
import conexaobd.Conexao;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import jogomatematicafx.JogoMatematicaFX;

/**
 * FXML Controller class
 *
 * @author arcn
 */
public class FXMLMatrizesController implements Initializable {

    //
    public boolean primeiroAcesso;

    //constantes para o tempo de jogo
    public int controladorTempo;

    Thread tempo;

    public JogoMatrizes jogo;
    public Conexao conexao;
    public GerenciadorDeTelas tela_Info;
    public int desempenhoGeral;
    public String nivelJogador;

    public boolean jogoEncerrado = false;

    public int desempenhoSoma;
    public int desempenhoMult;
    public int desempenhoTransp;
    public int desempenhoDet;

    @SuppressWarnings("empty-statement")
    public void verificaFimDeJogo() throws IOException, SQLException {
        if (this.jogo.nivel == 20) {

            jogoEncerrado = true;

            SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String data = out.format(new Date());

            desempenhoGeral = (int) (((float) this.jogo.pontos / 20) * 100);
            desempenhoSoma = (int) (((float) this.jogo.pontosSoma / 5) * 100);
            desempenhoMult = (int) (((float) this.jogo.pontosMult / 5) * 100);
            desempenhoTransp = (int) (((float) this.jogo.pontosTransp / 5) * 100);
            desempenhoDet = (int) (((float) this.jogo.pontosDet / 5) * 100);

            ObservableList<PieChart.Data> pieChartData
                    = FXCollections.observableArrayList(
                            new PieChart.Data("Desempenho Soma", desempenhoSoma),
                            new PieChart.Data("Desempenho Mult", desempenhoMult),
                            new PieChart.Data("Desempenho Trasp", desempenhoTransp),
                            new PieChart.Data("Determinantes", desempenhoDet));

            this.grafico.setTitle("Desempenho por tópicos");
            grafico.getData().addAll(pieChartData);
            this.grafico.setVisible(true);

            lblDesempenho.setText(Integer.toString(desempenhoGeral) + "%");

        }
    }

    public void defineLabelsSomaEMult(JogoMatrizes j) {
        lblNivel.setText(this.nivelJogador);
        lblPontos.setText(Integer.toString(j.pontos));
        setMatriz(j.MatA, txtMatA);
        setMatriz(j.MatB, txtMatB);
        lblOperacao.setText(jogo.operacao);
    }

    public void defineLabelsTansposicao(JogoMatrizes j) {
        lblNivel.setText(this.nivelJogador);
        lblPontos.setText(Integer.toString(j.pontos));
        setMatriz(j.MatA, txtMatA);
        lblOperacao.setText(Integer.toString(j.coefMultA) + j.operacao);
    }

    public void defineLabelsDeterminantes(JogoMatrizes j) {
        //
        lblNivel.setText(this.nivelJogador);
        lblPontos.setText(Integer.toString(j.pontos));
        setMatriz(j.MatA, txtMatA);
        lblOperacao.setText(j.operacao);
    }

    public void defineLabelsEMatrizes(JogoMatrizes j) {
        switch (j.tipoJogo) {
            case "Soma":
                defineLabelsSomaEMult(j);
                break;
            case "Multiplicacao":
                defineLabelsSomaEMult(j);
                break;
            case "Transposicao":
                defineLabelsTansposicao(j);
                break;
            case "Determinantes":
                defineLabelsDeterminantes(j);
                break;
        }

    }

    public void setMatriz(int Mat[][], TextArea textA) {
        textA.setText("");
        String vetor[] = new String[3];
        textA.appendText("\n");
        for (int i = 0; i < 3; i++) {
            vetor[i] = String.format("%5d\t%5d\t%5d", Mat[i][0], Mat[i][1], Mat[i][2]);
            textA.appendText(vetor[i] + "\n");
        }
    }

    public void setMatrizResposta(JogoMatrizes j) {

        txtMatR1.setText("");
        txtMatR2.setText("");
        txtMatR3.setText("");
        String vetor[] = new String[3];

        if (j.tipoJogo.equalsIgnoreCase("Soma") || j.tipoJogo.equalsIgnoreCase("Multiplicacao") || j.tipoJogo.equalsIgnoreCase("Transposicao")) {
            switch (j.painelCerto) {
                case 1:
                    txtMatR1.appendText("\n");
                    for (int i = 0; i < 3; i++) {
                        vetor[i] = String.format("%5d\t%5d\t%5d", j.MatR[i][0], j.MatR[i][1], j.MatR[i][2]);
                        txtMatR1.appendText(vetor[i] + "\n");
                    }

                    txtMatR2.appendText("\n");
                    for (int i = 0; i < 3; i++) {
                        vetor[i] = String.format("%5d\t%5d\t%5d", j.MatRE1[i][0], j.MatRE1[i][1], j.MatRE1[i][2]);
                        txtMatR2.appendText(vetor[i] + "\n");
                    }

                    txtMatR3.appendText("\n");
                    for (int i = 0; i < 3; i++) {
                        vetor[i] = String.format("%5d\t%5d\t%5d", j.MatRE2[i][0], j.MatRE2[i][1], j.MatRE2[i][2]);
                        txtMatR3.appendText(vetor[i] + "\n");
                    }

                    break;
                case 2:
                    txtMatR1.appendText("\n");
                    for (int i = 0; i < 3; i++) {
                        vetor[i] = String.format("%5d\t%5d\t%5d", j.MatRE1[i][0], j.MatRE1[i][1], j.MatRE1[i][2]);
                        txtMatR1.appendText(vetor[i] + "\n");
                    }

                    txtMatR2.appendText("\n");
                    for (int i = 0; i < 3; i++) {
                        vetor[i] = String.format("%5d\t%5d\t%5d", j.MatR[i][0], j.MatR[i][1], j.MatR[i][2]);
                        txtMatR2.appendText(vetor[i] + "\n");
                    }

                    txtMatR3.appendText("\n");
                    for (int i = 0; i < 3; i++) {
                        vetor[i] = String.format("%5d\t%5d\t%5d", j.MatRE2[i][0], j.MatRE2[i][1], j.MatRE2[i][2]);
                        txtMatR3.appendText(vetor[i] + "\n");
                    }

                    break;
                case 3:
                    txtMatR1.appendText("\n");
                    for (int i = 0; i < 3; i++) {
                        vetor[i] = String.format("%5d\t%5d\t%5d", j.MatRE2[i][0], j.MatRE2[i][1], j.MatRE2[i][2]);
                        txtMatR1.appendText(vetor[i] + "\n");
                    }

                    txtMatR2.appendText("\n");
                    for (int i = 0; i < 3; i++) {
                        vetor[i] = String.format("%5d\t%5d\t%5d", j.MatRE1[i][0], j.MatRE1[i][1], j.MatRE1[i][2]);
                        txtMatR2.appendText(vetor[i] + "\n");
                    }

                    txtMatR3.appendText("\n");
                    for (int i = 0; i < 3; i++) {
                        vetor[i] = String.format("%5d\t%5d\t%5d", j.MatR[i][0], j.MatR[i][1], j.MatR[i][2]);
                        txtMatR3.appendText(vetor[i] + "\n");
                    }
            }
        } else {
            switch (j.painelCerto) {
                case 1:
                    txtMatR1.appendText("\n\n");
                    txtMatR1.appendText(String.format("\t%5d\t", j.det));
                    txtMatR1.appendText("\n");

                    txtMatR2.appendText("\n\n");
                    txtMatR2.appendText(String.format("\t%5d\t", j.detE1));
                    txtMatR2.appendText("\n");

                    txtMatR3.appendText("\n\n");
                    txtMatR3.appendText(String.format("\t%5d\t", j.detE2));
                    txtMatR3.appendText("\n");

                    break;
                case 2:
                    txtMatR1.appendText("\n\n");
                    txtMatR1.appendText(String.format("\t%5d\t", j.detE1));
                    txtMatR1.appendText("\n");

                    txtMatR2.appendText("\n\n");
                    txtMatR2.appendText(String.format("\t%5d\t", j.det));
                    txtMatR2.appendText("\n");

                    txtMatR3.appendText("\n\n");
                    txtMatR3.appendText(String.format("\t%5d\t", j.detE2));
                    txtMatR3.appendText("\n");

                    break;
                case 3:
                    txtMatR1.appendText("\n\n");
                    txtMatR1.appendText(String.format("\t%5d\t", j.detE1));
                    txtMatR1.appendText("\n");

                    txtMatR2.appendText("\n\n");
                    txtMatR2.appendText(String.format("\t%5d\t", j.detE2));
                    txtMatR2.appendText("\n");

                    txtMatR3.appendText("\n\n");
                    txtMatR3.appendText(String.format("\t%5d\t", j.det));
                    txtMatR3.appendText("\n");
                    break;
            }
        }

    }

    public void controladorTempo() throws IOException {
        final int valor1 = this.controladorTempo;
        //
        tempo = new Thread() {
            public void run() {
                try {
                    for (int i = 1000; i >= 0; i--) {
                        sleep(10);
                        progressTempo.setProgress((double) i / 1000);
                    }
                    jogo.vefificaAcerto(0);
                    jogo.defineJogo();
                    defineLabelsEMatrizes(jogo);
                    setMatrizResposta(jogo);
                } catch (final Exception ie) {
                    throw new UnsupportedOperationException("Interrupts not supported.", ie);
                }
            }
        };
        tempo.start();
    }

    @FXML
    private Label lblPontos;

    @FXML
    private JFXProgressBar progressTempo;

    @FXML
    private Label lblNivel;

    @FXML
    private TextArea txtMatB;

    @FXML
    private TextArea txtMatA;

    @FXML
    private Label lblOperacao;

    @FXML
    private Label lblResposta;

    @FXML
    private ImageView imgViewResposta;

    @FXML
    private TextArea txtMatR1;

    @FXML
    private TextArea txtMatR2;

    @FXML
    private TextArea txtMatR3;

    @FXML
    private JFXButton btnStart;

    @FXML
    private JFXButton btnVoltar;

    @FXML
    private Label lblPontosFinal;

    @FXML
    private JFXButton btnSalvarJogo;

    @FXML
    private Label labelB;

    @FXML
    private Label labelA;

    @FXML
    private PieChart grafico;

    @FXML
    private Label lblDesempenho;

    @FXML
    void btnStartClicado(ActionEvent event) throws IOException {
        this.primeiroAcesso = true;
        jogo = new JogoMatrizes(JogoMatematicaFX.jogoPrincipal.tipoJogo);
        if (jogo.tipoJogo.equalsIgnoreCase("Determinantes") || jogo.tipoJogo.equalsIgnoreCase("Transposicao")) {
            labelB.setText("");
            txtMatB.setVisible(false);
            txtMatA.setLayoutX(txtMatA.getLayoutX() * 2);
            labelA.setLayoutX(labelA.getLayoutX() * 2.47);
        }
        jogo.defineJogo();
        defineLabelsEMatrizes(jogo);
        setMatrizResposta(jogo);
        txtMatA.setEditable(false);
        btnStart.setVisible(false);
    }

    @FXML
    void btnVoltarClicado(ActionEvent event) throws IOException, InterruptedException {
        // encerra o jogo
        Jogo novoJogo = new Jogo();
        switch (jogo.tipoJogo) {
            case "Soma":
                desempenhoSoma = (int) (((float) this.jogo.pontosSoma / jogo.contador) * 100);
                JogoMatematicaFX.jogoPrincipal.pSoma = desempenhoSoma;
                break;
            case "Multiplicacao":
                desempenhoMult = (int) (((float) this.jogo.pontosMult / jogo.contador) * 100);
                JogoMatematicaFX.jogoPrincipal.pMult = desempenhoMult;
                break;
            case "Transposicao":
                desempenhoTransp = (int) (((float) this.jogo.pontosTransp / jogo.contador) * 100);
                JogoMatematicaFX.jogoPrincipal.pTransp = desempenhoTransp;
                break;
            case "Determinantes":
                desempenhoDet = (int) (((float) this.jogo.pontosDet / jogo.contador) * 100);
                JogoMatematicaFX.jogoPrincipal.pDet = desempenhoDet;
                break;
        }
        JogoMatematicaFX.jogoPrincipal.data = novoJogo.data;
        tela_Info = new GerenciadorDeTelas("InfoJogador");
        if (!JogoMatematicaFX.jogadorPrincipal.nome.equals("Demonstração")) {
            tela_Info.controle_info.btnSalvaDados.setVisible(true);
        } else {
            tela_Info.controle_info.btnSalvaDados.setVisible(false);
        }
        tela_Info.voltaParaInformacoes();
    }

    @FXML
    void Mat1Clicada(javafx.scene.input.MouseEvent event) throws IOException, SQLException {
        if (jogo.contador >= 5 && !btnVoltar.isVisible()) {
            btnVoltar.setVisible(true);
        }
        jogo.vefificaAcerto(1);
        jogo.defineJogo();
        defineLabelsEMatrizes(jogo);
        setMatrizResposta(jogo);
    }

    @FXML
    void Mat2Clicada(javafx.scene.input.MouseEvent event) throws IOException, SQLException {
        if (jogo.contador >= 5 && !btnVoltar.isVisible()) {
            btnVoltar.setVisible(true);
        }
        jogo.vefificaAcerto(2);
        jogo.defineJogo();
        defineLabelsEMatrizes(jogo);
        setMatrizResposta(jogo);
    }

    @FXML
    void Mat3Clicada(javafx.scene.input.MouseEvent event) throws IOException, SQLException {
        if (jogo.contador >= 5 && !btnVoltar.isVisible()) {
            btnVoltar.setVisible(true);
        }
        jogo.vefificaAcerto(3);
        jogo.defineJogo();
        defineLabelsEMatrizes(jogo);
        setMatrizResposta(jogo);
    }

    @FXML
    void btnSalvarJogoClicado(ActionEvent event) throws SQLException, IOException {
        conexao = new Conexao();
        Jogo novoJogo = new Jogo();

        novoJogo.usuario = JogoMatematicaFX.jogadorPrincipal.usuario;
        novoJogo.pSoma = desempenhoSoma;
        novoJogo.pMult = desempenhoMult;
        novoJogo.pTransp = desempenhoTransp;
        novoJogo.pDet = desempenhoDet;

        conexao.enviaJogo(novoJogo);
        JogoMatematicaFX.jogoPrincipal = novoJogo;

        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);

        dialogoInfo.setTitle("");
        dialogoInfo.setHeaderText("Jogo Salvo Com Sucesso!");
        dialogoInfo.setContentText("Clique em voltar para um novo jogo");
        dialogoInfo.showAndWait();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.nivelJogador = JogoMatematicaFX.jogadorPrincipal.nivelJogador;
        switch (JogoMatematicaFX.jogoPrincipal.tipoJogo) {
            case "Soma":
                controladorTempo = 10;
                break;
            case "Multiplicacao":
                controladorTempo = 30;
                break;
            case "Transposicao":
                controladorTempo = 20;
                break;
            case "Determinantes":
                controladorTempo = 30;
                break;
        }
        btnVoltar.setVisible(false);
        txtMatR1.setEditable(false);
        txtMatR2.setEditable(false);
        txtMatR3.setEditable(false);
    }

}
