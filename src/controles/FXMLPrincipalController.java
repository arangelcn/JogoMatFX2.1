/*
 * Essa classe está ligada à classe (container) de FXML Cadastro e FXML Infojogador, portanto vamos carregar 
    as duas classes citadas na inicialização e setar cada uma das telas se for necessário
 */
package controles;

import arquiteturajogos.Jogadores;
import arquiteturajogos.Jogo;
import com.jfoenix.controls.*;
import conexaobd.Conexao;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import jogomatematicafx.JogoMatematicaFX;

/**
 *
 * @author arcn
 */
public class FXMLPrincipalController implements Initializable {
    
    public GerenciadorDeTelas telaCadastro;
    public GerenciadorDeTelas telaUsr;
    
    public boolean usrRoot;
    
    public static String nomeJogador;
    
    @FXML
    private Label lblInfo;
    
    @FXML
    public JFXTextField txtUsr;
    
    @FXML
    private JFXButton btnEntrar;
    
    @FXML
    private JFXButton btnCadastrar;
    
    @FXML
    private JFXPasswordField txtSenha;

    /**
     *
     * @param event
     */
    @FXML
    void btnEnterClicado(javafx.scene.input.KeyEvent ev) throws IOException {
        if (ev.getCode() == KeyCode.ENTER) {
            if (txtUsr.getText().equalsIgnoreCase("root") && txtSenha.getText().equalsIgnoreCase("demo")) {
                JogoMatematicaFX.jogadorPrincipal.nome = "Demonstação";
                JogoMatematicaFX.jogadorPrincipal.nivelJogador = "Intermediário";
                JogoMatematicaFX.jogadorPrincipal.nivelJogador = "Iniciante";
                JogoMatematicaFX.jogoPrincipal.pSoma = 20;
                JogoMatematicaFX.jogoPrincipal.pMult = 50;
                JogoMatematicaFX.jogoPrincipal.pTransp = 60;
                JogoMatematicaFX.jogoPrincipal.pDet = 80;
                telaUsr = new GerenciadorDeTelas("InfoJogador");
                telaUsr.controle_info.usrRoot = true;
                telaUsr.carregaTela();
            } else {
                Conexao con = new Conexao();
                if (con.verificaUsuarioCadastrado(txtUsr.getText())) {
                    JogoMatematicaFX.jogadorPrincipal = con.pegaJogador(txtUsr.getText());
                    JogoMatematicaFX.jogoPrincipal = con.pegaJogo(txtUsr.getText());
                    calculaNivelJogador();
                    telaUsr = new GerenciadorDeTelas("InfoJogador");
                    if (JogoMatematicaFX.jogadorPrincipal.senha.equals(txtSenha.getText())) {
                        telaUsr.carregaTela();
                    } else {
                        lblInfo.setText("Senha Inválida!");
                    }
                } else {                    
                    lblInfo.setText("Usuário não cadastrado!");
                }
            }
        }
    }
    
    @FXML
    void btnCadastrarClicado(ActionEvent event) throws IOException {
        telaCadastro.carregaTela();
    }
    
    @FXML
    void btnEntrarClicado(ActionEvent event) throws IOException {
        // Carrega a tela de Pontuacao do Jogadror
        if (txtUsr.getText().equalsIgnoreCase("root") && txtSenha.getText().equalsIgnoreCase("demo")) {
            JogoMatematicaFX.jogadorPrincipal.nome = "Demonstação";
            JogoMatematicaFX.jogadorPrincipal.nivelJogador = "Intermediário";
            JogoMatematicaFX.jogadorPrincipal.nivelJogador = "Iniciante";
            JogoMatematicaFX.jogoPrincipal.pSoma = 20;
            JogoMatematicaFX.jogoPrincipal.pMult = 50;
            JogoMatematicaFX.jogoPrincipal.pTransp = 60;
            JogoMatematicaFX.jogoPrincipal.pDet = 80;
            telaUsr.controle_info.usrRoot = true;
            telaUsr.carregaTela();
        } else {
            Conexao con = new Conexao();
                if (con.verificaUsuarioCadastrado(txtUsr.getText())) {
                    JogoMatematicaFX.jogadorPrincipal = con.pegaJogador(txtUsr.getText());
                    JogoMatematicaFX.jogoPrincipal = con.pegaJogo(txtUsr.getText());
                    calculaNivelJogador();
                    telaUsr = new GerenciadorDeTelas("InfoJogador");
                    if (JogoMatematicaFX.jogadorPrincipal.senha.equals(txtSenha.getText())) {
                        telaUsr.carregaTela();
                    } else {
                        lblInfo.setText("Senha Inválida!");
                    }
                } else {                    
                    lblInfo.setText("Usuário não cadastrado!");
                }
        }
        
    }
    
    @FXML
    void teclaEnterClicada(javafx.scene.input.KeyEvent ev) throws IOException {
        if (ev.getCode() == KeyCode.ENTER) {
            if (txtUsr.getText().equalsIgnoreCase("root") && txtSenha.getText().equalsIgnoreCase("demo")) {
                JogoMatematicaFX.jogadorPrincipal.nome = "Demonstração";
                JogoMatematicaFX.jogadorPrincipal.nivelJogador = "Intermediário";
                JogoMatematicaFX.jogoPrincipal.pSoma = 20;
                JogoMatematicaFX.jogoPrincipal.pMult = 50;
                JogoMatematicaFX.jogoPrincipal.pTransp = 60;
                JogoMatematicaFX.jogoPrincipal.pDet = 80;
                telaUsr = new GerenciadorDeTelas("InfoJogador");
                telaUsr.controle_info.usrRoot = true;
                telaUsr.carregaTela();
            } else {
                Conexao con = new Conexao();
                if (con.verificaUsuarioCadastrado(txtUsr.getText())) {
                    JogoMatematicaFX.jogadorPrincipal = con.pegaJogador(txtUsr.getText());
                    JogoMatematicaFX.jogoPrincipal = con.pegaJogo(txtUsr.getText());
                    calculaNivelJogador();
                    telaUsr = new GerenciadorDeTelas("InfoJogador");
                    if (JogoMatematicaFX.jogadorPrincipal.senha.equals(txtSenha.getText())) {
                        telaUsr.carregaTela();
                    } else {
                        lblInfo.setText("Senha Inválida!");
                    }
                } else {                    
                    lblInfo.setText("Usuário não cadastrado!");
                }
            }
        }
    }
    
    public void calculaNivelJogador () { 
            int desempenhoGeral;
            desempenhoGeral = (JogoMatematicaFX.jogoPrincipal.pDet + JogoMatematicaFX.jogoPrincipal.pSoma + JogoMatematicaFX.jogoPrincipal.pMult
                    + JogoMatematicaFX.jogoPrincipal.pTransp) / 4;
            JogoMatematicaFX.jogoPrincipal.pontuacao = desempenhoGeral;
            if (desempenhoGeral < 30) {
                JogoMatematicaFX.jogadorPrincipal.nivelJogador = "Iniciante";
            } else if (desempenhoGeral < 75) {
                JogoMatematicaFX.jogadorPrincipal.nivelJogador = "Intermediário";
            } else {
                JogoMatematicaFX.jogadorPrincipal.nivelJogador = "Avançado";
            }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            JogoMatematicaFX.nivelProgresso = 0;
            // Como a tela Cadastro não usa nenhuma informacao previamente setada, ela pode ser carregada antes 
            telaCadastro = new GerenciadorDeTelas("Cadastro");
        } catch (IOException ex) {
            Logger.getLogger(FXMLPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
