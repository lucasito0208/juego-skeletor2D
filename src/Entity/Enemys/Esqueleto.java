/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity.Enemys;

import Entity.Animacion;
import Entity.Enemy;
import Entity.Flecha;
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
public class Esqueleto extends Enemy{
    public final int ANDAR=11;
    private final int[] numFrames = {9};
    ArrayList<BufferedImage[]> sprites;
    public final int npasostotales=150;
    public int npasos;
    public Esqueleto(TileMap tm) throws IOException {
        super(tm);
        this.caida=true;
        this.mirarderecha=true;
        this.movespeed=0.5;
        this.maxspeed=0.8;
        this.right=true;
        this.npasos=0;
        this.width=64;
        this.height=64;
        this.colliderwidth=32;
        this.colliderheight=50;
        
        this.caidaspeed = 0.2;
        this.maxcaidaspeed=4.0;
        this.maxspeedsalto = -4.8;
        
        this.saludmax=10;
        this.salud=this.saludmax;
        this.neutralizado=false;
        this.golpeenemigo=1;
        this.cargarSpriteSheet("/Assets/Sprites/esqueletor.png");
        this.animacion=new Animacion();
        this.animacion.setFrames(sprites.get(0));
        this.animacion.setDelay(200);
    }
    
  public void cargarSpriteSheet(String ruta) throws IOException{
            BufferedImage spritesheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
			
			sprites = new ArrayList<BufferedImage[]>();
                        int contador=0;
			for(int i = 0; i < 21; i++) {
				if((i==ANDAR)){
				BufferedImage[] bi =new BufferedImage[numFrames[contador]];
                               
				for(int j = 0; j < numFrames[contador]; j++) {
                                    bi[j] = spritesheet.getSubimage(j* width,i * height,64,64);
					}	
				sprites.add(bi);
                                contador++;
                                }
                        }
        }//fin de cargar sprites
  
  private void getNextPosition() {
		
		// movement
		if(left) {
			dx -= movespeed;
			if(dx < -maxspeed) {
				dx = -maxspeed;
			}
		}
		else if(right) {
			dx += movespeed;
			if(dx > maxspeed) {
				dx = maxspeed;
			}
		}
		else {
			if(dx > 0) {
				dx -= minspeed;
				if(dx < 0) {
					dx = 0;
				}
			}
			else if(dx < 0) {
				dx += minspeed;
				if(dx > 0) {
					dx = 0;
				}
			}
		}
                 
                
 
		if(caida){
			 dy += this.caidaspeed;
			if(dy < 0 && !this.salto) dy += this.minspeedsalto;
			
			if(dy > this.maxcaidaspeed) dy = this.maxcaidaspeed;
			
		}
                
		
	}//fin de metodo posicion siguiente
  
  
  public void update() throws IOException {
		// update position
               
		getNextPosition();
		this.logicaColision();
		setPosition(xaux, yaux);
               this.caida=true;
                
		if(left || right) {
                   
			if(this.animacionActual != ANDAR) {
				this.animacionActual = ANDAR;
                                this.animacion=new Animacion();
				this.animacion.setFrames(sprites.get(0));
				this.animacion.setDelay(60);
				//width = 30;
			}
		}
		if((dx==0)&&(this.right)){//colision con algo ala deercha
                    this.mirarderecha=false;
                    this.right=false;
                    this.left=true;
                }
                if((dx==0)&&(this.left)){//coliaison con algo mientars miraba a la izquieraa
                    this.mirarderecha=true;
                    this.right=true;
                    this.left=false;
                }
                npasos++;
           
                if(npasos==this.npasostotales){
                    npasos=0;
                    if((this.right)){//colision con algo ala deercha
                    this.mirarderecha=false;
                    this.right=false;
                    this.left=true;
                }
                    else if((this.left)){//coliaison con algo mientars miraba a la izquieraa
                    this.left=false;
                    this.mirarderecha=true;
                    this.right=true;
                   
                }
                }
           
		this.animacion.update();
                        // set direction
			
		
		
	}//fin de metodo actualizar
  
  public void draw(Graphics2D g) {
	if(!this.neutralizado){
		this.setMapPosition();

		
		// draw playerfor(Flecha f:this.listaflechas){
                   
	
                if(this.mirarderecha){
                    
                    g.drawImage(this.animacion.getImagen(),(int)(x+xmap-width/2), (int)(y+ymap-height/2),width,height,null);
                }else{
                    g.drawImage(this.animacion.getImagen(),(int)(x+xmap-width/2)+width, (int)(y+ymap-height/2),-width,height, null);
                }
                 }
  }
  
    
}
