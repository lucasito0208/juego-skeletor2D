package Entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import TileMap.TileMap;
import jaco.mp3.player.MP3Player;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;




public class Player extends MapObject{
	

	// this.animacions
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {2,9,11};
        private ArrayList<Enemy> lenemigos;
	
	// this.animacion actions
	private static final int REPOSO = 3;
	private static final int ANDAR = 11;
	private static final int DISPARAR = 19;
        
        //atributos jugador
       
        private int salud;
        private int saludmax;
        private int mana;
        private boolean neutralizado;
        
        
        //felchas
        ArrayList<Flecha> listaflechas;
         private int manamax;  
         private int costemana;
         private int damagearrow;
         //hud
          HUD playerhud;
        MP3Player mp3player;


      

	
	public Player(TileMap tm) throws IOException {	
		super(tm);
		width = 64;
		height = 64;
		this.colliderwidth = 20;
		colliderheight = 45;
		this.caida=true;
		movespeed = 0.6;
		maxspeed = 3;
		minspeed = 0.4;
		this.caidaspeed = 0.2;
                this.maxcaidaspeed=4.0;
		this.maxspeedsalto = -4.8;
		this.minspeedsalto = 0.3;		
		this.mirarderecha = true;
                this.neutralizado=false;
                //cargamos las imagenes del spritesheet
                this.cargarSpriteSheet("/Assets/Player/descarga.png");
                //poner la primera animacion
		this.animacion = new Animacion();
		this.animacionActual = REPOSO;
		this.animacion.setFrames(sprites.get(0));
		this.animacion.setDelay(200);
                
                this.lenemigos=new ArrayList<Enemy>();
                
                //ataques juagador
                this.listaflechas=new ArrayList<Flecha>();
                this.manamax=1000;
		this.mana=manamax;
                this.damagearrow=30;
                this.saludmax=3;
                this.salud=saludmax;
                this.costemana=500;
              this.playerhud =new HUD(this.tilemap,salud,mana);
	}
        
        
        public void reproducirMusica(String ruta){
        this.mp3player=new MP3Player(this.getClass().getResource(ruta));
        
        this.mp3player.play();
      
       

    }
        
        public void cargarSpriteSheet(String ruta) throws IOException{
            BufferedImage spritesheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(ruta)));
			
			sprites = new ArrayList<BufferedImage[]>();
                        int contador=0;
			for(int i = 0; i < 21; i++) {
				if((i==REPOSO)||(i==ANDAR)||(i==DISPARAR)){
				BufferedImage[] bi =new BufferedImage[numFrames[contador]];
                               
				for(int j = 0; j < numFrames[contador]; j++) {
                                    bi[j] = spritesheet.getSubimage(j* width,i * height,64,64);
					}	
				sprites.add(bi);
                                contador++;
                                }
                        }
        }
        
        public void calcularAtaques(){
            for(int i=0;i<this.listaflechas.size();i++){//por cada flecha
                Flecha flecha=this.listaflechas.get(i);
                 for(int j=0;j<this.lenemigos.size();j++){//por cada flecha
                     Enemy e=this.lenemigos.get(j);
                   
                     if(e.neutralizado){
                         this.lenemigos.remove(j);
                     }else
                     if(flecha.hayColisionRectangulo(e)){
                         this.reproducirMusica("..\\Assets\\SFX\\grito3.mp3");
                         this.listaflechas.remove(i);
                         e.recibirDamage(this.damagearrow); 
                         
                     }
                     
                 }
                
            }
                
        }
        
        
        public void calcularHits(){
                 for(int j=0;j<this.lenemigos.size();j++){//por cada flecha
                     Enemy e=this.lenemigos.get(j);
                     if(e.neutralizado==false){
                     if(this.hayColisionRectangulo(e)){
                         this.recibirDamage(e.getGolpeenemigo());
                         this.setPosition(x-60, y-60);
                         this.caida=true;
                     }
                     
                 }
            }
        }
        
        public void recibirDamage(int damage){
            salud-=damage;
             if(salud<=0){
                         this.neutralizado=true;
                         JOptionPane.showMessageDialog(null, "gamee over");
                     }
        }

		
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
		// salto
		if(salto && !caida) {
			dy = this.maxspeedsalto;
			caida = true;
		}
		// caida
		if(caida){
			
			 dy += this.caidaspeed;
			
			if(dy > 0) this.salto = false;
			if(dy < 0 && !this.salto) dy += this.minspeedsalto;
			
			if(dy > this.maxcaidaspeed) dy = this.maxcaidaspeed;
			
		}
                   if(this.lenemigos.size()>0){
                    this.calcularAtaques();
                    this.calcularHits();
                }
		if(((int)dy==-1)){   
                    this.reproducirMusica("..\\Assets\\SFX\\jump3.mp3");
                }
	}
	
	public void update() throws IOException {
		
		// update position
		getNextPosition();
		this.logicaColision();
		setPosition(xaux, yaux);
                
                mana+=5;
                if(mana>manamax){
                    this.mana=manamax;
                }
                if(this.disparando&&(this.animacionActual!=DISPARAR)){
                    this.reproducirMusica("..\\Assets\\SFX\\arrow3.mp3");
               if(this.mana>this.costemana){
                   mana=mana-this.costemana;
                   
                   Flecha flecha=new Flecha(this.tilemap,this.mirarderecha);
                   flecha.setPosition(x, y);
                   this.listaflechas.add(flecha);   
               }else{
                   //sonido si no hubiera flechas
               }
               }
                
                for(int i=0;i<this.listaflechas.size();i++){
                    this.listaflechas.get(i).update();
                    if(this.listaflechas.get(i).isObsoleta()){
                        this.listaflechas.remove(i);
                    }
                }
                
                
	if(this.animacionActual==DISPARAR){
               if(animacion.isCompletada()){
                           this.disparando=false;
                          
                       }else{
                           this.disparando=true; 
                       }
        
        }
                if(this.disparando){
                    if(this.animacionActual!=DISPARAR){
                      this.animacionActual = DISPARAR;
                      this.animacion=new Animacion();
                      this.animacion.setFrames(sprites.get(2));
		      this.animacion.setDelay(30);
                      animacion.setCompletada(false);
                    }
                }else
                
		if(left || right) {
			if(this.animacionActual != ANDAR) {
				this.animacionActual = ANDAR;
                                this.animacion=new Animacion();
				this.animacion.setFrames(sprites.get(1));
				this.animacion.setDelay(60);
				//width = 30;
			}
		}
		else {
			if(this.animacionActual != REPOSO) {
				this.animacionActual = REPOSO;
                                this.animacion=new Animacion();
				this.animacion.setFrames(sprites.get(0));
				this.animacion.setDelay(200);
				//width = 30;
			}
		}
                
                
		
		this.animacion.update();
		// set direction
		if(this.animacionActual != DISPARAR) {
			if(right) this.mirarderecha = true;
			if(left) this.mirarderecha = false;
		}
                
                //actualizamos el hud
                this.playerhud.setMana(mana);
                this.playerhud.setSalud(salud);
                this.playerhud.update();
             
                
		
	}
	
	public void draw(Graphics2D g) {
		
		this.setMapPosition();

		
		// draw playerfor(Flecha f:this.listaflechas){
                 this.playerhud.draw(g);
	
                if(this.mirarderecha){
                    
                    g.drawImage(this.animacion.getImagen(),(int)(x+xmap-width/2), (int)(y+ymap-height/2),width,height,null);
                }else{
                    g.drawImage(this.animacion.getImagen(),(int)(x+xmap-width/2)+width, (int)(y+ymap-height/2),-width,height, null);
                }
                
                
		/*g.setColor(Color.red);
               // g.drawString("haycolisionflecha"+, 100,100 );
                g.drawString("salto="+this.salto, 100,115 );
                g.drawString("dx"+this.dx, 100,125 );
                g.drawString("dy"+this.dy, 100,135 );
                g.drawString("delaya"+this.animacion.getDelay(), 100,145 );
                g.drawString("abajoderecha"+this.abajoderecha, 100,155 );
                g.drawString("abajoizquierda"+this.abajoizquierda, 100,165 );
               g.drawString("arribaizquierda"+this.arribaizquierda, 100,175 );
                g.drawString("arribaderecha"+this.arribaderecha, 100,185 );;
              */
		
                for(Flecha f:this.listaflechas){
                    f.draw(g);
                }
	}

    public ArrayList<Enemy> getLenemigos() {
        return lenemigos;
    }

    public void setLenemigos(ArrayList<Enemy> lenemigos) {
        this.lenemigos = lenemigos;
    }
        
        
        
	
}


