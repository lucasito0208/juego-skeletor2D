/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EstadoJuego;

import Entity.Enemy;
import Entity.Enemys.Esqueleto;
import Entity.Player;
import TileMap.BackGround;

import TileMap.TileMap;
import clase9menudinamico.PanelJuego;
import jaco.mp3.player.MP3Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fran
 */
public class Leve1Estado extends EstadoJuego{
    private TileMap tilemap;
    private Player player;
    private BackGround background;
    private ArrayList<Enemy> lenemigos;
   MP3Player mp3player;
 
    public Leve1Estado(ManagerEstados sm){
        this.sm=sm; 
        this.iniciar();
    }

    @Override
    public void iniciar() {
      
  
        try {
            this.background=new BackGround("/Assets/Background/bgmenu.gif",1);
            tilemap=new TileMap(32);
            tilemap.loadTiles("/Assets/TileSet/level1tileset.png");
            tilemap.loadMap("/Assets/Map/level1.txt");
            player=new Player(tilemap);
            
            player.setPosition(0, 0);
            
            this.lenemigos=new ArrayList<Enemy>();
            Esqueleto e=new Esqueleto(tilemap);
            e.setPosition(300, 0);
            Esqueleto e2=new Esqueleto(tilemap);
            e2.setPosition(400, 0);
            Esqueleto e3=new Esqueleto(tilemap);
            e3.setPosition(1200, 0);
            Esqueleto e4=new Esqueleto(tilemap);
            e4.setPosition(1400, 0);
            this.lenemigos.add(e);
            this.lenemigos.add(e2);
            this.lenemigos.add(e3);
            this.lenemigos.add(e4);
            player.setLenemigos(lenemigos);
        } catch (IOException ex) {
            Logger.getLogger(Leve1Estado.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        
            
      
    }
    
     public void reproducirMusica(){
        this.mp3player=new MP3Player(this.getClass().getResource("/Assets/Music/gamemusic.mp3"));
        this.mp3player.play();
       
        this.mp3player.setRepeat(true);
        
    }

    @Override
    public void draw(Graphics2D g) {
        this.background.draw(g);
        tilemap.draw(g);
        player.draw(g);
        for(int i=0;i<this.lenemigos.size();i++){
            Esqueleto e=(Esqueleto) this.lenemigos.get(i);
           e.draw(g);
        }
        
    }

    @Override
    public void update() {
       //tilemap.setPosicion(player.getX(), player.getY());
       tilemap.setPosition(PanelJuego.WIDTH / 2 - player.getX(),PanelJuego.HEIGHT / 2 - player.getY());
        try {
            player.update();
        } catch (IOException ex) {
            Logger.getLogger(Leve1Estado.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i=0;i<this.lenemigos.size();i++){
            Esqueleto e=(Esqueleto) this.lenemigos.get(i);
           try {
               e.update();
           } catch (IOException ex) {
               Logger.getLogger(Leve1Estado.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
      
       
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
       int keycode=e.getKeyCode();
       switch(keycode){
           case KeyEvent.VK_RIGHT:{player.setRight(true);}break;
           case KeyEvent.VK_LEFT:{player.setLeft(true);}break;
           case KeyEvent.VK_SPACE:{player.setSalto(true);}break;
           case KeyEvent.VK_Z:{player.setDisparando(true);}break;
       }
    }

    @Override
    public void keyReleased(KeyEvent e) {
       int keycode=e.getKeyCode();
       switch(keycode){
           case KeyEvent.VK_RIGHT:{player.setRight(false);}break;
           case KeyEvent.VK_LEFT:{player.setLeft(false);}break;
           case KeyEvent.VK_SPACE:{player.setSalto(false);}break;
           case KeyEvent.VK_Z:{player.setDisparando(false);}break;
       }
    }

    @Override
    public void parar() {
       this.mp3player.stop();
    }





    
    
}
