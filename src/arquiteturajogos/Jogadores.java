/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arquiteturajogos;


/**
 *
 * @author arcn
 */
public class Jogadores {
    
    public String usuario; 
    public String nome; 
    public String senha; 
    public String nivelJogador;
    
    //Usar para gerenciar se o primeiro jogo vai comecar zerado 
    public int qtdJogos;  
    
    public Jogadores () { 
        nivelJogador = ""; 
        qtdJogos= 0;
    }
    
}
