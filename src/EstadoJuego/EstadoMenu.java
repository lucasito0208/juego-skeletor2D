/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstadoJuego;


import TileMap.BackGround;
import jaco.mp3.player.MP3Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 *
 * @author Fran
 */
public class EstadoMenu extends EstadoJuego{
    BackGround bg;
    
    String[] lopciones={"Nuevo Juego","Creditos","Salir"};
    int opcionactual=0;
 
   MP3Player mp3player;
 
  
 
    
    public EstadoMenu(ManagerEstados sm) throws IOException{
        this.sm=sm;
       bg=new BackGround("../Assets/Background/bgmenu.gif",1.0);
       bg.setPosicion(0, 0);
       bg.setVector(0, 0.08);

    }

    @Override
    public void iniciar() {
        this.reproducirMusica();
        
    }

    public void reproducirMusica(){
        this.mp3player=new MP3Player(this.getClass().getResource("..\\Assets\\Music\\bgmusicodl.mp3"));
        this.mp3player.play();
        this.mp3player.setRepeat(true);
        
    }
    
    


    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);//pinta el fondo
        
        //pintamos el titulo
        Font titlefont=new Font("Arial",Font.PLAIN,20);
        g.setFont(titlefont);
        g.setColor(Color.red);
        g.drawString("One Day Later", 90, 50);
        
        Font font=new Font("Arial",Font.PLAIN,10);
        g.setFont(font);
         
        for(int i=0;i<this.lopciones.length;i++){
            if(i==this.opcionactual){
                g.setColor(Color.BLUE);
            }else{
                g.setColor(Color.GREEN);
            }
             g.drawString(this.lopciones[i], 120, 165+(i*20));
        }
    }

    @Override
    public void update() {
        bg.update();
    }
    
    public void seleccionar(){
        if(this.opcionactual==0){
            this.sm.setEstadoactual(sm.LEVEL1);
        }
        if(this.opcionactual==2){
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
       int tecla=e.getKeyCode();
       switch(tecla){
           case KeyEvent.VK_UP: {this.opcionactual=this.opcionactual-1;
                                  if(opcionactual<0){
                                      opcionactual=this.lopciones.length-1;
                                  }} break;
           case KeyEvent.VK_DOWN: {this.opcionactual=this.opcionactual+1;
                                  if(opcionactual>this.lopciones.length-1){
                                      opcionactual=0;
                                  }} break;
           case KeyEvent.VK_ENTER: {this.seleccionar();} break;
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
      
    }

    @Override
    public void parar() {
       this.mp3player.stop();
    }
}
