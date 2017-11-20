
package arquiteturajogos;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/**
 *
 * @author arcn
 */

public class Jogo {
    
    public String usuario; 
    public int pontuacao;
    public int pSoma;
    public int pMult; 
    public int pTransp; 
    public int pDet;
    public String data;
    
    
    //tipo
    public String tipoJogo;
   
    public Jogo () { 
       this.pontuacao = this.pSoma = this.pMult = this.pTransp = this.pDet = 0;
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
       data = sdf.format(new Date());
       tipoJogo = "Soma";
    }  
    
    public void atualizaData() { 
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        data = sdf.format(new Date());
    }
}
