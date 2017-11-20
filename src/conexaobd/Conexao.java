/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexaobd;

import arquiteturajogos.Jogadores;
import arquiteturajogos.Jogo;
import java.awt.Component;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jogomatematicafx.JogoMatematicaFX;

/**
 *
 * @author arcn
 */
public class Conexao {
    //objeto da classe connection do sql
    public Connection conecta;

    
    private final String driver = "com.mysql.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost/Jogo_APS";
    private final String usrAdmin = "root";
    private final String senha = ""; 
    
    public Conexao () { 
        
    }
    
    //metodo para conectar ao bando de dados: 
    public void abrirConexao() { 
        try {
            Class.forName(driver).newInstance();
            conecta = DriverManager.getConnection(url, usrAdmin, senha);
            System.out.println("Conectado!");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public void fecharConexao () throws SQLException { 
        conecta.close();
        System.out.println("Desconectado!");
    }
    
    //Metodo para enviar jogador para o banco de dados
    public void enviaJogador(Jogadores j) { 
        String sql = "insert into jogador (usuario, nome, senha) values (?,?,?)";
        try {     
            abrirConexao();
              
            PreparedStatement stmt = (PreparedStatement) conecta.prepareStatement(sql);
                stmt.setString(1, j.usuario);
                stmt.setString(2, j.nome);
                stmt.setString(3, j.senha);
                stmt.execute();
             
            fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Jogadores pegaJogador (String usuario) { 
        try {
            Jogadores jogador = new Jogadores();
            String sql = "select senha, nome, usuario from jogador where usuario = '"+usuario+"'";
            
            abrirConexao();
            
            PreparedStatement stmt = (PreparedStatement) conecta.prepareStatement(sql); 
           
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                jogador.usuario = rs.getString("usuario");
                jogador.nome = rs.getString("nome"); 
                jogador.senha = rs.getString("senha"); 
            }
            
            stmt.close();
            fecharConexao();
            return jogador;
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public void enviaJogo(Jogo jogo) { 
        String sql = "insert into jogo (data, usuario, pontos, pontoSoma, pontosMult, pontosTransp, pontosDet) values (?,?,?,?,?,?,?)";      
        try {     
            abrirConexao();
              
            PreparedStatement stmt = (PreparedStatement) conecta.prepareStatement(sql);
                stmt.setString(1, jogo.data);
                stmt.setString(2, jogo.usuario);
                stmt.setInt(3, jogo.pontuacao);
                stmt.setInt(4, jogo.pSoma);
                stmt.setInt(5, jogo.pMult);
                stmt.setInt(6, jogo.pTransp);
                stmt.setInt(7, jogo.pDet);
                
                stmt.execute();
           
            fecharConexao();
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Jogo pegaJogo (String usuario) { 
        try {
            Jogo jogo= new Jogo();
            String sql = "select * from jogo where usuario = '"+usuario+"'";
            
            abrirConexao();
            
            PreparedStatement stmt = (PreparedStatement) conecta.prepareStatement(sql); 
           
            ResultSet rs = stmt.executeQuery(sql);
            
            for (int i =0; rs.next(); i++) { 
                jogo.data = rs.getString("data");
                jogo.usuario = rs.getString("usuario"); 
                jogo.pontuacao = rs.getInt("pontos"); 
                jogo.pSoma = rs.getInt("pontoSoma"); 
                jogo.pMult = rs.getInt("pontosMult"); 
                jogo.pTransp = rs.getInt("pontosTransp"); 
                jogo.pDet = rs.getInt("pontosDet");
            }
       
            stmt.close();
            fecharConexao();
            return jogo;
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public boolean verificaUsuarioCadastrado(String usuario) { 
        try {
            Jogadores jogador = new Jogadores();
            String sql = "select senha, nome, usuario from jogador where usuario = '"+usuario+"'";
            
            abrirConexao();
            
            PreparedStatement stmt = (PreparedStatement) conecta.prepareStatement(sql); 
            int i = 0;
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                return true;
            }
            
            stmt.close();
            fecharConexao();
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            return false; 
        }
    }
    
}
