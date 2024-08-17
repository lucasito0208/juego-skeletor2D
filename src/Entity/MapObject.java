/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import TileMap.Tile;
import TileMap.TileMap;
import clase9menudinamico.PanelJuego;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Fran
 */
public abstract class MapObject {
    
    
    //TileSet
    protected TileMap tilemap;
    protected int tsize;
    protected double xmap;
    protected double ymap;
    
    
    //dimension
    
    protected int width;
    protected int height;
    //posicion
    protected double x;
    protected double y;
    protected double dx;
    protected double dy;
    
    //colision objetos 
    protected int filaActual;
    protected int colActual;
    protected double xdestino;
    protected double ydestino;
    protected boolean  arribaizquierda;
    protected boolean arribaderecha;
    protected boolean abajoizquierda;
    protected boolean abajoderecha;
    protected double xaux;
    protected double yaux;
    
    //atributes
    protected double movespeed;
    protected double maxspeed;//aceleracion desplazamiento lateral
    protected double minspeed;//deceleracion desplazamiento lateral
    protected double maxspeedsalto;//aceleracion
    protected double minspeedsalto;//deceleracion
    protected double caidaspeed;//deceleracion
    protected double maxcaidaspeed;
    
    //movimiento
    
    protected boolean left;
    protected boolean right;
    protected boolean up;
   protected  boolean down;
    protected boolean caida;
    protected boolean salto;
    protected boolean disparando;
    
    //caja colisiones
   protected int colliderwidth;
   protected int  colliderheight;
   
   // Animacion
   protected Animacion animacion;
   protected int animacionActual;
   protected int animacionPrev;
 protected boolean mirarderecha;
    
    public MapObject(TileMap tm){
        this.tilemap=tm;
        this.tsize=tm.getTileSize();
    }
    
    public Rectangle2D crearRectangulo(){
        return new Rectangle((int)x-this.colliderwidth,(int)y-this.colliderheight,this.colliderwidth,this.colliderheight);
    }
    
    public boolean hayColisionRectangulo(MapObject o2){
        Rectangle2D rectpropio=this.crearRectangulo();
        Rectangle2D rectajeno=o2.crearRectangulo();
        
        return rectpropio.intersects(rectajeno);
        
    }
    
    
    
      public void logicaColision(){
      this.filaActual=(int) (y/this.tsize);
      this.colActual=(int) (x/this.tsize);
      this.xdestino=x+dx;
      this.ydestino=y+dy;
      this.xaux=x;
      this.yaux=y;
      
      this.calcularColision(x, this.ydestino);
      if(dy<0){
          if(this.arribaizquierda||this.arribaderecha){
              dy=0;
              this.yaux=filaActual*tsize+this.colliderheight/2;
          }else{
              this.yaux+=dy;
          }//hacia arriba
      }if(dy>0){
           if(this.abajoizquierda||this.abajoderecha){
              dy=0;
              caida=false;
              
          }else{
              this.yaux+=dy;
          }
      }//hacia abajo
      this.calcularColision(this.xdestino, y);
       if(dx<0){//hacia izq
          if(this.arribaizquierda||this.abajoizquierda){
              dx=0;
              this.xaux=colActual*tsize+this.colliderwidth/2;
          }else{
              this.xaux+=dx;
          }//hacia izquierda
      }if(dx>0){
           if((this.arribaderecha)||(this.abajoderecha)){
               
              dx=0;
              this.xaux=((colActual+1)*tsize-this.colliderwidth/2);
          }else{
               this.xaux+=dx;
          }//hacia izquierda
      }
      
      if(this.caida){
          this.calcularColision(x, this.ydestino+1);
          if((!this.abajoizquierda)&&(!this.abajoderecha)){
              this.caida=true;
          }
      }
        
    }
    
   public void calcularColision(double x,double y){
        int tileizquierdo=((int)x-this.colliderwidth/2)/this.tsize;
        int tilederecha=((int)x+this.colliderwidth/2-1)/this.tsize;
        int tileabajo=((int)y+this.colliderheight/2-1)/this.tsize;
        int tilearriba=((int)y-this.colliderheight/2)/this.tsize;
        
        if(tilemap.getTipo(tilearriba, tileizquierdo)==Tile.COLISION){
            this.arribaizquierda=true;
        }else{
            this.arribaizquierda=false;
        }
        if(tilemap.getTipo(tilearriba, tilederecha)==Tile.COLISION){
            this.arribaderecha=true;
        }else{
             this.arribaderecha=false;
        }
        if(tilemap.getTipo(tileabajo, tileizquierdo)==Tile.COLISION){
            this.abajoizquierda=true;
        }else{
            this.abajoizquierda=false;
        }
        if(tilemap.getTipo(tileabajo, tilederecha)==Tile.COLISION){
            this.abajoderecha=true;
        }else{
           this.abajoderecha=false;
        }
    }
    
    public void setPosition(double x,double y){
        this.x=x;
        this.y=y;
    }
    
    public void setMapPosition(){
      this.xmap=tilemap.getX();
      this.ymap=tilemap.getY();
    }
    
    public void setVector(double dx,double dy){
        this.dx=dx;
        this.dy=dy;
    }
    
    
    public boolean fueraDeLugar(){
        return (x+xmap-width<0)||(x+xmap+width>PanelJuego.WIDTH)||(y+ymap-height<0)||(y+ymap+height>PanelJuego.HEIGHT);
    }
    public boolean dentroDeLugar(){
        return !this.fueraDeLugar();
    }

    public TileMap getTilemap() {
        return tilemap;
    }

    public void setTilemap(TileMap tilemap) {
        this.tilemap = tilemap;
    }

    public int getTsize() {
        return tsize;
    }

    public void setTsize(int tsize) {
        this.tsize = tsize;
    }

    public double getXmap() {
        return xmap;
    }

    public void setXmap(double xmap) {
        this.xmap = xmap;
    }

    public double getYmap() {
        return ymap;
    }

    public void setYmap(double ymap) {
        this.ymap = ymap;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public int getFilaActual() {
        return filaActual;
    }

    public void setFilaActual(int filaActual) {
        this.filaActual = filaActual;
    }

    public int getColActual() {
        return colActual;
    }

    public void setColActual(int colActual) {
        this.colActual = colActual;
    }

    public double getXdestino() {
        return xdestino;
    }

    public void setXdestino(double xdestino) {
        this.xdestino = xdestino;
    }

    public double getYdestino() {
        return ydestino;
    }

    public void setYdestino(double ydestino) {
        this.ydestino = ydestino;
    }

    public boolean isArribaizquierda() {
        return arribaizquierda;
    }

    public void setArribaizquierda(boolean arribaizquierda) {
        this.arribaizquierda = arribaizquierda;
    }

    public boolean isArribaderecha() {
        return arribaderecha;
    }

    public void setArribaderecha(boolean arribaderecha) {
        this.arribaderecha = arribaderecha;
    }

    public boolean isAbajoizquierda() {
        return abajoizquierda;
    }

    public void setAbajoizquierda(boolean abajoizquierda) {
        this.abajoizquierda = abajoizquierda;
    }

    public boolean isAbajoderecha() {
        return abajoderecha;
    }

    public void setAbajoderecha(boolean abajoderecha) {
        this.abajoderecha = abajoderecha;
    }

    public double getXaux() {
        return xaux;
    }

    public void setXaux(double xaux) {
        this.xaux = xaux;
    }

    public double getYaux() {
        return yaux;
    }

    public void setYaux(double yaux) {
        this.yaux = yaux;
    }

    public double getMovespeed() {
        return movespeed;
    }

    public void setMovespeed(double movespeed) {
        this.movespeed = movespeed;
    }

    public double getMaxspeed() {
        return maxspeed;
    }

    public void setMaxspeed(double maxspeed) {
        this.maxspeed = maxspeed;
    }

    public double getMinspeed() {
        return minspeed;
    }

    public void setMinspeed(double minspeed) {
        this.minspeed = minspeed;
    }

    public double getMaxspeedsalto() {
        return maxspeedsalto;
    }

    public void setMaxspeedsalto(double maxspeedsalto) {
        this.maxspeedsalto = maxspeedsalto;
    }

    public double getMinspeedsalto() {
        return minspeedsalto;
    }

    public void setMinspeedsalto(double minspeedsalto) {
        this.minspeedsalto = minspeedsalto;
    }

    public double getCaidaspeed() {
        return caidaspeed;
    }

    public void setCaidaspeed(double caidaspeed) {
        this.caidaspeed = caidaspeed;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isCaida() {
        return caida;
    }

    public void setCaida(boolean caida) {
        this.caida = caida;
    }

    public boolean isSalto() {
        return salto;
    }

    public void setSalto(boolean salto) {
        this.salto = salto;
    }

    public int getColliderwidth() {
        return colliderwidth;
    }

    public void setColliderwidth(int colliderwidth) {
        this.colliderwidth = colliderwidth;
    }

    public int getColliderheight() {
        return colliderheight;
    }

    public void setColliderheight(int colliderheight) {
        this.colliderheight = colliderheight;
    }

    public boolean isDisparando() {
        return disparando;
    }

    public void setDisparando(boolean disparando) {
        this.disparando = disparando;
    }

    public Animacion getAnimacion() {
        return animacion;
    }

    public void setAnimacion(Animacion animacion) {
        this.animacion = animacion;
    }

    public boolean isMirarderecha() {
        return mirarderecha;
    }

    public void setMirarderecha(boolean mirarderecha) {
        this.mirarderecha = mirarderecha;
    }
    
    
    
    
    
    
    
    
}
