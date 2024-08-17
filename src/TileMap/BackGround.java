/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TileMap;

import clase9menudinamico.PanelJuego;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

/**
 *
 * @author Fran
 */
public class BackGround {
   double x;
   double y;
   double dx;
   double dy;
   BufferedImage image;
   
   double escalamovimiento;

    public BackGround(String nombre,double escalamovimiento) throws IOException {
        image=ImageIO.read(Objects.requireNonNull(this.getClass().getResourceAsStream(nombre)));
        this.escalamovimiento = escalamovimiento;
    }
    
    public void setPosicion(double x,double y){
        this.x=(x*this.escalamovimiento)%(PanelJuego.WIDTH);
        this.y=(y*this.escalamovimiento)%PanelJuego.HEIGHT;
    }
    
    public void setVector(double dx,double dy){
        this.dx=dx;
        this.dy=dy;
    }
    
    public void update(){
        x+=dx;
        y+=dy;
    }
    
    public void draw(Graphics2D g){
        g.drawImage(image, (int)x, (int)y,null);
    }
   
   
   
}
