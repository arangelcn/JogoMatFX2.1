package controles;

import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.util.Duration;
import javax.swing.GroupLayout;
import jogomatematicafx.JogoMatematicaFX;

/**
 *
 * @author arcn
 */
public class GerenciadorDeTelas {

    private final FXMLLoader carregador_login;
    private final FXMLLoader carregador_cadastro;
    private final FXMLLoader carregador_info;
    private final FXMLLoader carregador_matriz;

    private Parent tela;
    private Scene cena;

    // Definição dos controladores 
    public FXMLPrincipalController controle_login;
    public FXMLCadastroController controle_cadastro;
    public FXMLInfoJogadorController controle_info;
    public FXMLMatrizesController controle_matrizes;

    //Tempo transicao
    private int valorTransicao;

    public GerenciadorDeTelas(String fxml) throws IOException {

        this.carregador_login = new FXMLLoader(getClass().getResource("/telas/FXMLPrincipal.fxml"));
        this.carregador_info = new FXMLLoader(getClass().getResource("/telas/FXMLInfoJogador.fxml"));
        this.carregador_cadastro = new FXMLLoader(getClass().getResource("/telas/FXMLCadastro.fxml"));
        this.carregador_matriz = new FXMLLoader(getClass().getResource("/telas/FXMLMatrizes.fxml"));

        if (fxml.equalsIgnoreCase("Principal")) {
            this.tela = carregador_login.load();
            this.controle_login = carregador_login.getController();
        }
        if (fxml.equalsIgnoreCase("Cadastro")) {
            this.tela = carregador_cadastro.load();
            this.controle_cadastro = carregador_cadastro.getController();
        }
        if (fxml.equalsIgnoreCase("InfoJogador")) {
            this.tela = carregador_info.load();
            this.controle_info = carregador_info.getController();
        }
        if (fxml.equalsIgnoreCase("Matrizes")) {
            this.valorTransicao = 1;
            this.tela = carregador_matriz.load();
            this.controle_matrizes = carregador_matriz.getController();
        }
        this.cena = new Scene(tela);
    }

    public void carregaTela() throws IOException {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.setDuration(Duration.seconds(3));
        fadeTransition.setNode((Node) tela);
        fadeTransition.play();
        JogoMatematicaFX.stage.setScene(this.cena);
        JogoMatematicaFX.stage.centerOnScreen();
    }

    public void carregaTelaMatrizes() {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setFromX(this.cena.getX() - 500);
        translateTransition.setToX(this.cena.getX());
        translateTransition.setDuration(Duration.seconds(1));
        translateTransition.setNode((Node) tela);
        translateTransition.play();
        JogoMatematicaFX.stage.setScene(this.cena);
        JogoMatematicaFX.stage.centerOnScreen();
    }

    public void voltaParaInformacoes() {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setFromX(this.cena.getX() + 1000);
        translateTransition.setToX(this.cena.getX());
        translateTransition.setDuration(Duration.seconds(1));
        translateTransition.setNode((Node) tela);
        translateTransition.play();
        JogoMatematicaFX.stage.setScene(this.cena);
        JogoMatematicaFX.stage.centerOnScreen();
    }

    public Parent getTela() {
        return tela;
    }

    public void setTela(Parent tela) {
        this.tela = tela;
    }

    public Scene getCena() {
        return cena;
    }

    public void setCena(Scene cena) {
        this.cena = cena;
    }

}
