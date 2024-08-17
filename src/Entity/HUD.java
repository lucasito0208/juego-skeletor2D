/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import TileMap.TileMap;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import javax.imageio.ImageIO;

/**
 *
 * @author Fran
 */
public class HUD extends MapObject{
    private int salud;
    private int mana;
    
    private ArrayList<BufferedImage[]> spritessalud;
     private ArrayList<BufferedImage[]> spritesmana;
    private final int[] numFrames = {2};
    private static final int MOVERC = 0;
    private static final int MOVERM = 0;
    Animacion asalud;
    Animacion amana;

    public HUD(TileMap tm,int salud,int mana) throws IOException {
        super(tm);
        this.salud=salud;
        this.mana=mana;
        this.width=32;
        this.height=32;
        
      spritessalud=new   ArrayList<BufferedImage[]>(); 
      spritesmana=new   ArrayList<BufferedImage[]>(); 
        
        this.cargarSpriteSheetSalud("/Assets/Sprites/salud.png");
        this.cargarSpriteSheetMana("/Assets/Sprites/mana.png");
        
        
        
        asalud=new Animacion();
        asalud.setFrames(this.spritessalud.get(0));
        asalud.setDelay(200);
        
        amana=new Animacion();
        amana.cargarFrames(this.spritesmana.get(0));
        amana.setDelay(200);
        
    }
    
     public void cargarSpriteSheetSalud(String ruta) throws IOException{
            BufferedImage spritesheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
            BufferedImage[] bi=new BufferedImage[2];
            for(int i=0;i<2;i++){
               bi[i]=spritesheet.getSubimage(i*this.width,0, this.width, this.height);
            }
            this.spritessalud.add(bi);
			
        }
     
       public void cargarSpriteSheetMana(String ruta) throws IOException{
            BufferedImage spritesheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
            BufferedImage[] bi=new BufferedImage[2];
            for(int i=0;i<2;i++){
              bi[i]=spritesheet.getSubimage(i*this.width,0, this.width, this.height);
            }
            this.spritesmana.add(bi);
			
        }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
       
       
       
       public void update(){
           asalud.update();
           amana.update();
       }
       
       public void draw(Graphics2D g){
          
           for(int i=0;i<salud;i++){
                g.drawImage(asalud.getImagen(), i*25,10,25,25,null);
           }
        
           int resul=mana/200;
          for(int i=0;i<resul;i++){
              
                 g.drawImage(amana.getImagen(), i*25,30,25,25,null);
              }
            }    
         

    
    
    
}
