/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstadoJuego;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author Fran
 */
public abstract class EstadoJuego {
  protected ManagerEstados sm;
  public abstract void iniciar();
  public abstract void draw(Graphics2D g);
  public abstract void update();
  public abstract void keyTyped(KeyEvent e); 
  public abstract void keyPressed(KeyEvent e); 
  public abstract void keyReleased(KeyEvent e);
  public abstract void parar();
  
  


       
    
 
  
  
    
}
