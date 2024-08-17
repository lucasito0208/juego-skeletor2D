/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clase9menudinamico;

import EstadoJuego.ManagerEstados;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Fran
 */
public class PanelJuego extends JPanel implements Runnable,KeyListener{
    public static final int WIDTH=320;
    public static final int HEIGHT=240;
    public static final int SCALE=4;
    public int ejecucion;
    public long tiempoObjetivo;
    public int FPS;
    ManagerEstados sm;
    BufferedImage bi;
    Graphics2D g2d;
    
    

    public PanelJuego() {
        super();
        this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(this);
        
    }
    
    public void iniciar() throws IOException{
        bi=new BufferedImage(this.WIDTH,this.HEIGHT,BufferedImage.TYPE_INT_BGR);
        g2d=(Graphics2D) bi.getGraphics();
        this.ejecucion=0;
        FPS=60;
        this.tiempoObjetivo= 1000/FPS;
        sm=new ManagerEstados();
        sm.setEstadoactual(sm.MENU);
        
        
    }
    public void addNotify(){
        super.addNotify();
        Thread hilo=new Thread(this);
        hilo.start();
    }

    
    @Override
    public void run() {
        try {
            this.iniciar();
            long timestart;
            long timeelapsed;
            long timewait;
            while(this.ejecucion==0){
                timestart=System.nanoTime();
                this.draw();
                this.update();
                this.drawinPanel(g2d);
                
                timeelapsed=System.nanoTime()-timestart;
                timewait=tiempoObjetivo-timeelapsed/1000000;
                
                try {
                    Thread.sleep(Math.abs(timewait));
                } catch (InterruptedException ex) {
                    Logger.getLogger(PanelJuego.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } } catch (IOException ex) {
            Logger.getLogger(PanelJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
  public  void draw(){
        sm.draw(g2d);
  }
  public  void update(){
      sm.update();
  }
  
   public  void drawinPanel(Graphics2D g){
       Graphics2D g2 = (Graphics2D) this.getGraphics();
     
       g2.drawImage(bi, 0, 0, this.WIDTH*SCALE, this.HEIGHT*SCALE, null);
       g2.dispose();
   } 

    @Override
    public void keyTyped(KeyEvent e) {
        sm.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
     sm.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
       sm.keyReleased(e);
    }
    
    
}
