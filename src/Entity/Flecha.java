/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import TileMap.TileMap;
import clase9menudinamico.PanelJuego;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Fran
 */
public class Flecha extends MapObject{
    
    
    
    BufferedImage[] spritenormal;
    BufferedImage[] spritehit; 
boolean golpe; 
boolean quitar;
    
    private int[] nframes={3,4};
    public Flecha(TileMap tm,boolean mirarderecha) throws IOException {
        super(tm);
        
        
        this.movespeed=4.5;
        if(mirarderecha){
           this.dx=movespeed;
        }else{
            this.dx=-movespeed;
        }
        
        this.width=32;
        this.height=32;
        this.cargarTiles("..\\Assets\\Sprites\\disparoplayer.png");
        this.colliderwidth=20;
        this.colliderheight=20; 
       
        golpe=false;
        this.quitar=false;
        
        this.animacion=new Animacion();
        this.animacion.setFrames(this.spritenormal);
        this.animacion.setDelay(90);
            
        
    }
    
    public void cargarTiles(String ruta) throws IOException{
        BufferedImage spritesheet=ImageIO.read(this.getClass().getResourceAsStream(ruta));
                        int contador=0;
			for(int i = 0; i < 2; i++) {
                            if(i==0){
                                 this.spritenormal=new BufferedImage[nframes[i]];
                                for(int j=0;j<nframes[i];j++){
                                     this.spritenormal[j]=spritesheet.getSubimage(j*width, i*this.height, this.width, this.height);
                                }
                                   
                                }else if(i==1){
                                   this.spritehit=new BufferedImage[nframes[i]];
                                    for(int j=0;j<nframes[i];j++){
                                       this.spritehit[j]=spritesheet.getSubimage(j*width, i*this.height, this.width, this.height); 
                                    }
                                        
                               }  
                            }   
    }//find e metodo cargar tiles dee imagen
    
    public void setGolpe(){
        golpe=true;
        this.animacion=new Animacion();
        this.animacion.setFrames(spritehit);
        this.animacion.setDelay(80);
    }
    
    public boolean isObsoleta(){
        return this.quitar;
    }
    
    
    
    public void update(){
        if((this.getX()>this.tilemap.getNumColsToDraw())&&(this.getX()>0)&&(this.getX()<this.tilemap.getLimitewidht()-32)){
        this.logicaColision();
        this.setPosition(xaux, yaux+0.10);
        
      
        if((dx==0)&&(!this.golpe)){//aqui calculamos que la felcha se ha chocado o no
            this.setGolpe();
        }
        
        this.animacion.update();
        
        if((this.golpe==true)&&(this.animacion.isCompletada())){
            this.quitar=true;
        }
        }else{
            quitar=true;
        }
        
    }
    
    public void draw(Graphics2D g) {
		
		this.setMapPosition();

		
		// draw player
	
                if(this.mirarderecha){
                    
                    g.drawImage(this.animacion.getImagen(),(int)(x+xmap-width/2), (int)(y+ymap-height/2),width,height,null);
                }else{
                    g.drawImage(this.animacion.getImagen(),(int)(x+xmap-width/2)+width, (int)(y+ymap-height/2),-width,height, null);
                }
		/*g.setColor(Color.red);
                g.drawString("x="+xaux, 100,100 );
                g.drawString("salto="+this.salto, 100,115 );
                g.drawString("dx"+this.dx, 100,125 );
                g.drawString("dy"+this.dy, 100,135 );
                g.drawString("delaya"+this.animacion.getDelay(), 100,145 );
                g.drawString("abajoderecha"+this.abajoderecha, 100,155 );
                g.drawString("abajoizquierda"+this.abajoizquierda, 100,165 );
               g.drawString("arribaizquierda"+this.arribaizquierda, 100,175 );
                g.drawString("arribaderecha"+this.arribaderecha, 100,185 );;
              
		*/
	}
    
}
