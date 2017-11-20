/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles;

import arquiteturajogos.Jogadores;
import arquiteturajogos.Jogo;
import com.jfoenix.controls.*;
import conexaobd.Conexao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import jogomatematicafx.JogoMatematicaFX;

/**
 * FXML Controller class
 *
 * @author arcn
 */
public class FXMLCadastroController implements Initializable {

    GerenciadorDeTelas telaPrincipal;

    @FXML
    private JFXButton btnVoltar;

    @FXML
    private JFXTextField txtNome;

    @FXML
    private JFXTextField txtUsr;

    @FXML
    private JFXPasswordField txtSenha;

    @FXML
    private JFXPasswordField txtConfereSenha;

    @FXML
    private JFXButton btnEnviaCadastro;

    @FXML
    private Label lblConfirmaCadastro;

    @FXML
    void btnVoltarClicado(ActionEvent event) throws IOException {
        telaPrincipal = new GerenciadorDeTelas("Principal");
        telaPrincipal.carregaTela();
    }

    @FXML
    void EnviaCadastroClicado(ActionEvent event) throws IOException, InterruptedException {
        lblConfirmaCadastro.setVisible(true);
        if (txtConfereSenha.getText().equals(txtSenha.getText()) && !txtSenha.getText().equals("")) {
            Conexao conect = new Conexao();
            if (!txtNome.getText().equals("") && !txtUsr.getText().equals("") && !conect.verificaUsuarioCadastrado(txtUsr.getText())) {
                telaPrincipal = new GerenciadorDeTelas("Principal");
                Jogadores j = new Jogadores();
                Jogo jogo = new Jogo();
                j.nome = txtNome.getText();
                j.usuario = txtUsr.getText();
                j.senha = txtSenha.getText();
                j.nivelJogador = "Iniciante";                 
                jogo.usuario = txtUsr.getText();
                jogo.atualizaData();
                conect.enviaJogo(jogo);
                conect.enviaJogador(j);  
                lblConfirmaCadastro.setText("Cadastro Enviado!");
            } else {
                lblConfirmaCadastro.setText("Esse usuario ja foi cadastrado!");
            }
        } else {
            lblConfirmaCadastro.setText("As senhas não conferem");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lblConfirmaCadastro.setVisible(false);
    }

}
