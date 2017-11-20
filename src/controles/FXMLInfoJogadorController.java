package controles;

import arquiteturajogos.Jogo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXProgressBar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import conexaobd.Conexao;
import java.awt.Component;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import jogomatematicafx.JogoMatematicaFX;

/**
 * FXML Controller class
 *
 * @author arcn
 */
public class FXMLInfoJogadorController implements Initializable {

    public boolean usrRoot;

    GerenciadorDeTelas telaMatriz;
    GerenciadorDeTelas telaLogin;
    ObservableList<PieChart.Data> pieChartData;

    XYChart.Series series1;

    /**
     * Initializes the controller class.
     */
    public static String nome;
    public Image imagemMedalha;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private Label lblNivel;

    @FXML
    private JFXProgressBar progress;

    @FXML
    public Label lblNome;

    @FXML
    public JFXButton btnIniciarNovoJogo;

    @FXML
    public JFXButton btnSalvaDados;
    
    @FXML
    private ImageView imgMedalha;

    @FXML
    private PieChart graficoDesempenho;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis categorias;

    @FXML
    private NumberAxis numerosGrafico;

    @FXML
    private JFXComboBox<String> checkBokOpcoes;

    @FXML
    private JFXButton btnCarregaDesempenho;

    @FXML
    void btnCarregaDesempenhoClicado(ActionEvent event) throws InterruptedException {
        switch (checkBokOpcoes.getValue()) {
            case "Soma":
                this.graficoDesempenho.getData().removeAll(pieChartData);
                this.barChart.setVisible(false);
                this.graficoDesempenho.setVisible(true);
                // Define grafico da Soma
                pieChartData
                        = FXCollections.observableArrayList(
                                new PieChart.Data("Acertos: " + Integer.toString(JogoMatematicaFX.jogoPrincipal.pSoma) + "%", JogoMatematicaFX.jogoPrincipal.pSoma),
                                new PieChart.Data("Erros", 100 - JogoMatematicaFX.jogoPrincipal.pSoma)
                        );
                // pinta o grafico
                this.graficoDesempenho.setTitle("Último Desempenho Soma");
                this.graficoDesempenho.getData().addAll(pieChartData);
                btnIniciarNovoJogo.setVisible(true);
                btnSalvaDados.setVisible(false);
                break;
            case "Multiplicação":

                this.graficoDesempenho.getData().removeAll(pieChartData);
                this.barChart.setVisible(false);
                this.graficoDesempenho.setVisible(true);
                // Define grafico da multiplicação
                pieChartData
                        = FXCollections.observableArrayList(
                                new PieChart.Data("Acertos: " + Integer.toString(JogoMatematicaFX.jogoPrincipal.pMult) + "%", JogoMatematicaFX.jogoPrincipal.pMult),
                                new PieChart.Data("Erros", 100 - JogoMatematicaFX.jogoPrincipal.pMult)
                        );
                // pinta o grafico
                this.graficoDesempenho.setTitle("Último Desempenho Multiplicação");
                this.graficoDesempenho.getData().addAll(pieChartData);
                btnIniciarNovoJogo.setVisible(true);
                btnSalvaDados.setVisible(false);
                break;
            case "Transposição":
                this.graficoDesempenho.getData().removeAll(pieChartData);
                this.barChart.setVisible(false);
                this.graficoDesempenho.setVisible(true);
                // Define grafico da Transposição
                pieChartData
                        = FXCollections.observableArrayList(
                                new PieChart.Data("Acertos: " + Integer.toString(JogoMatematicaFX.jogoPrincipal.pTransp) + "%", JogoMatematicaFX.jogoPrincipal.pTransp),
                                new PieChart.Data("Erros", 100 - JogoMatematicaFX.jogoPrincipal.pTransp)
                        );
                // pinta o grafico
                this.graficoDesempenho.setTitle("Último Desempenho Transposição");
                this.graficoDesempenho.getData().addAll(pieChartData);
                btnIniciarNovoJogo.setVisible(true);
                btnSalvaDados.setVisible(false);
                break;
            case "Determinantes":
                this.graficoDesempenho.getData().removeAll(pieChartData);
                this.barChart.setVisible(false);
                this.graficoDesempenho.setVisible(true);
                // Define grafico da Transposição
                pieChartData
                        = FXCollections.observableArrayList(
                                new PieChart.Data("Acertos: " + Integer.toString(JogoMatematicaFX.jogoPrincipal.pDet) + "%", JogoMatematicaFX.jogoPrincipal.pDet),
                                new PieChart.Data("Erros", 100 - JogoMatematicaFX.jogoPrincipal.pDet)
                        );
                // pinta o grafico
                this.graficoDesempenho.setTitle("Último Desempenho Determinantes");
                this.graficoDesempenho.getData().addAll(pieChartData);
                btnIniciarNovoJogo.setVisible(true);
                btnSalvaDados.setVisible(false);
                break;
            default:
                this.barChart.getData().removeAll(series1);
                this.graficoDesempenho.getData().removeAll(pieChartData);
                this.graficoDesempenho.setVisible(false);
                series1 = new XYChart.Series<>();
                series1.getData().add(new XYChart.Data("Soma", JogoMatematicaFX.jogoPrincipal.pSoma));
                series1.getData().add(new XYChart.Data("Multiplicacao", JogoMatematicaFX.jogoPrincipal.pMult));
                series1.getData().add(new XYChart.Data("Transposta", JogoMatematicaFX.jogoPrincipal.pTransp));
                series1.getData().add(new XYChart.Data("Determinantes", JogoMatematicaFX.jogoPrincipal.pDet));

                barChart.setTitle("Ultimo Jogo");
                barChart.getData().add(series1);
                this.barChart.setVisible(true);
                if (!JogoMatematicaFX.jogadorPrincipal.nome.equalsIgnoreCase("Demonstração")) {
                    btnSalvaDados.setVisible(true);
                }
                btnIniciarNovoJogo.setVisible(false);
                break;
        }
    }

    @FXML
    void btnIniciaNovoJogo(ActionEvent event) throws IOException {
        switch (checkBokOpcoes.getValue()) {
            case "Soma":
                if (JogoMatematicaFX.primeiroJogoSoma) {
                    JogoMatematicaFX.nivelProgresso += 0.25;
                    progress.setProgress(JogoMatematicaFX.nivelProgresso);
                    JogoMatematicaFX.primeiroJogoSoma = false;
                }
                JogoMatematicaFX.jogoPrincipal.tipoJogo = "Soma";
                break;
            case "Multiplicação":
                if (JogoMatematicaFX.primeiroJogoMult) {
                    JogoMatematicaFX.nivelProgresso += 0.25;
                    progress.setProgress(JogoMatematicaFX.nivelProgresso);
                    JogoMatematicaFX.primeiroJogoMult = false;
                }
                JogoMatematicaFX.jogoPrincipal.tipoJogo = "Multiplicacao";
                break;
            case "Transposição":
                if (JogoMatematicaFX.primeiroJogoTransp) {
                    JogoMatematicaFX.nivelProgresso += 0.25;
                    progress.setProgress(JogoMatematicaFX.nivelProgresso);
                    JogoMatematicaFX.primeiroJogoTransp = false;
                }
                JogoMatematicaFX.jogoPrincipal.tipoJogo = "Transposicao";
                break;
            case "Determinantes":
                if (JogoMatematicaFX.primeiroJogoDet) {
                    JogoMatematicaFX.nivelProgresso += 0.25;
                    progress.setProgress(JogoMatematicaFX.nivelProgresso);
                    JogoMatematicaFX.primeiroJogoDet = false;
                }
                JogoMatematicaFX.jogoPrincipal.tipoJogo = "Determinantes";
                break;
            default:
                JogoMatematicaFX.jogoPrincipal.tipoJogo = "Soma";
                break;
        }
        telaMatriz.carregaTelaMatrizes();
    }

    @FXML
    void btnLogoutClicado(ActionEvent event) throws IOException {
        telaLogin = new GerenciadorDeTelas("Principal");
        telaLogin.carregaTela();
    }

    @FXML
    void btnSalvarDados(ActionEvent event) {
        int desempenhoGeral;
        desempenhoGeral = (JogoMatematicaFX.jogoPrincipal.pDet + JogoMatematicaFX.jogoPrincipal.pSoma + JogoMatematicaFX.jogoPrincipal.pMult
            + JogoMatematicaFX.jogoPrincipal.pTransp) / 4;
        JogoMatematicaFX.jogoPrincipal.pontuacao = desempenhoGeral;   
        Conexao conecta = new Conexao();
        JogoMatematicaFX.jogoPrincipal.usuario = JogoMatematicaFX.jogadorPrincipal.usuario;
        JogoMatematicaFX.jogoPrincipal.atualizaData();
        conecta.enviaJogo(JogoMatematicaFX.jogoPrincipal);
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("");
        dialogoInfo.setHeaderText("Jogo Salvo Com Sucesso!");
        dialogoInfo.showAndWait();
    }

    public void atualizaDadosGrafico() {
        progress.setProgress(JogoMatematicaFX.nivelProgresso);
        series1 = new XYChart.Series<>();
        series1.getData().add(new XYChart.Data("Soma", JogoMatematicaFX.jogoPrincipal.pSoma));
        series1.getData().add(new XYChart.Data("Multiplicacao", JogoMatematicaFX.jogoPrincipal.pMult));
        series1.getData().add(new XYChart.Data("Transposta", JogoMatematicaFX.jogoPrincipal.pTransp));
        series1.getData().add(new XYChart.Data("Determinantes", JogoMatematicaFX.jogoPrincipal.pDet));

        barChart.setTitle("Ultimo Jogo");
        barChart.getData().add(series1);
    }
    
    public void carregaMedalha () { 
        if (JogoMatematicaFX.jogadorPrincipal.nivelJogador.equals("Iniciante")) { 
            imagemMedalha = new Image("imagens/medalha-bronze.png");
        } else if (JogoMatematicaFX.jogadorPrincipal.nivelJogador.equals("Intermediário")) {
            imagemMedalha = new Image("imagens/medalha-prata.png");
        } else { 
            imagemMedalha = new Image("imagens/medalha-ouro.png");
        }
        imgMedalha.setImage(imagemMedalha);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            graficoDesempenho.setVisible(false);
            carregaMedalha();

            progress.setProgress(JogoMatematicaFX.nivelProgresso);

            checkBokOpcoes.setPromptText("Ultimo Desempenho");
            checkBokOpcoes.getItems().add(0, "Soma");
            checkBokOpcoes.getItems().add(1, "Multiplicação");
            checkBokOpcoes.getItems().add(2, "Transposição");
            checkBokOpcoes.getItems().add(3, "Determinantes");
            checkBokOpcoes.getItems().add(4, "Todos os jogos");
            
            series1 = new XYChart.Series<>();
            series1.getData().add(new XYChart.Data("Soma", JogoMatematicaFX.jogoPrincipal.pSoma));
            series1.getData().add(new XYChart.Data("Multiplicacao", JogoMatematicaFX.jogoPrincipal.pMult));
            series1.getData().add(new XYChart.Data("Transposta", JogoMatematicaFX.jogoPrincipal.pTransp));
            series1.getData().add(new XYChart.Data("Determinantes", JogoMatematicaFX.jogoPrincipal.pDet));

            btnIniciarNovoJogo.setVisible(false);
            btnSalvaDados.setVisible(false);

            //Carrega previa do grafico de pizza
            pieChartData
                    = FXCollections.observableArrayList(
                            new PieChart.Data("Acertos: " + Integer.toString(JogoMatematicaFX.jogoPrincipal.pSoma) + "%", JogoMatematicaFX.jogoPrincipal.pSoma),
                            new PieChart.Data("Erros", 100 - JogoMatematicaFX.jogoPrincipal.pSoma)
                    );
            // pinta o grafico
            this.graficoDesempenho.getData().addAll(pieChartData);
            //

            barChart.setTitle("Ultimo Jogo");
            barChart.getData().add(series1);

            // Coloca o nome do Jogador e o ultimo desempenho geral
            lblNome.setText(JogoMatematicaFX.jogadorPrincipal.nome);
            lblNivel.setText(JogoMatematicaFX.jogadorPrincipal.nivelJogador);
            //Carrega a tela do Jogo que pode ser chamada a qualquer momento
            telaMatriz = new GerenciadorDeTelas("Matrizes");
        } catch (IOException ex) {
            Logger.getLogger(FXMLInfoJogadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
