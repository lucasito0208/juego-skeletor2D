/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstadoJuego;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Fran
 */
public class ManagerEstados {
    ArrayList<EstadoJuego> estadosJuego;
    int estadoactual;
    public final int MENU=0;
    public final int LEVEL1=1;
    int contador=0;

    public ManagerEstados() throws IOException {
        this.estadosJuego=new ArrayList<EstadoJuego>();
        this.estadoactual=LEVEL1;
        EstadoMenu menu=new EstadoMenu(this);
        this.estadosJuego.add(menu);
        Leve1Estado lvl1=new Leve1Estado(this);
        this.estadosJuego.add(lvl1);
      
    }

    public int getEstadoactual() {
        return estadoactual;
    }

    public void setEstadoactual(int estadoactual) {
        if(estadoactual==LEVEL1){
            EstadoMenu em=(EstadoMenu) this.estadosJuego.get(MENU);
            em.parar();
            Leve1Estado l1=(Leve1Estado) this.estadosJuego.get(LEVEL1);
            l1.reproducirMusica();
            
        }
        this.estadoactual = estadoactual;
        this.estadosJuego.get(estadoactual).iniciar();
        
        contador++;
    }
    
    public void update(){
        this.estadosJuego.get(estadoactual).update();
    }
    
   public void draw(Graphics2D g){
       this.estadosJuego.get(estadoactual).draw(g);
   }
   
  public  void keyTyped(KeyEvent e){
      this.estadosJuego.get(estadoactual).keyTyped(e);
  }
  public  void keyPressed(KeyEvent e){
      this.estadosJuego.get(estadoactual).keyPressed(e);
  } 
  public  void keyReleased(KeyEvent e){
       this.estadosJuego.get(estadoactual).keyReleased(e);
  }
    
    
    
    
    
    
    
   
}
