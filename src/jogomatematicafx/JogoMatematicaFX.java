
package jogomatematicafx;

import arquiteturajogos.Jogadores;
import arquiteturajogos.Jogo;
import arquiteturajogos.JogoMatrizes;
import controles.FXMLCadastroController;
import controles.FXMLInfoJogadorController;
import controles.FXMLMatrizesController;
import controles.FXMLPrincipalController;
import controles.GerenciadorDeTelas;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
/**
 *
 * @author arcn
 */
public class JogoMatematicaFX extends Application {
    
    //controle do progressBar 
    public static boolean primeiroJogoSoma; 
    public static boolean primeiroJogoMult; 
    public static boolean primeiroJogoTransp; 
    public static boolean primeiroJogoDet;
    public static double nivelProgresso;
   
    public static Jogadores jogadorPrincipal;
    public static Jogo jogoPrincipal;
    
    //Definicao do stage principal
    public static Stage stage;
    
    //Definição dos fxml das telas 
    public static Parent tela_Login;
    
    //Definição dos controles: 
    public static FXMLPrincipalController controle_login;
    
    //Definicao dos scenes
    public static Scene scene_login;
        
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        primeiroJogoSoma = primeiroJogoDet = primeiroJogoMult = primeiroJogoTransp = true;
        nivelProgresso = 0;
        
        jogadorPrincipal = new Jogadores();
        jogoPrincipal = new Jogo();
        
        jogoPrincipal.pontuacao = 0; 
        jogoPrincipal.usuario = "";
        
        stage = primaryStage;
        
        FXMLLoader loader_Login = new FXMLLoader(JogoMatematicaFX.class.getResource("/telas/FXMLPrincipal.fxml"));
        
        tela_Login =  loader_Login.load();
  
        //Carrega os controles: 
        controle_login = loader_Login.getController();
       
        //// Carrega todas as screens do programa
        scene_login = new Scene(tela_Login); 
        
        stage.setResizable(false);
        stage.setScene(scene_login);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
