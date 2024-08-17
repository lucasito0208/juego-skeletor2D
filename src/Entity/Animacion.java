
package Entity;

import java.awt.Image;
import java.awt.image.BufferedImage;

/**
 *
 * @author Fran
 */
public class Animacion {
    BufferedImage[] frames;
    long delay;
    int currentFrame;
    boolean completada;
    long tiempoStart;
    
    public Animacion(){
        completada=false;
    }
    
    public void cargarFrames(BufferedImage[] animacion){
        frames=animacion;
        currentFrame=0;
        tiempoStart=System.nanoTime();
        completada=false;
    }
    
    public void update(){
       if(delay==-1) return;
            long elapsed=System.nanoTime()-tiempoStart;
            if(delay<elapsed){
                currentFrame++;
                tiempoStart=System.nanoTime();
                
            }
            if(currentFrame==frames.length){
                currentFrame=0;
                completada=true;
                    
                }
    }
        
        
    
    


    public BufferedImage[] getFrames() {
        return frames;
    }

    public void setFrames(BufferedImage[] frames) {
        this.frames = frames;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay*1000000;
    }

    public int getAnimacionActual() {
        return currentFrame;
    }

    public void setAnimacionActual(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public boolean isCompletada() {
        return completada;
    }
    public BufferedImage getImagen(){
        return this.frames[this.currentFrame];
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public long getTiempoStart() {
        return tiempoStart;
    }

    public void setTiempoStart(long tiempoStart) {
        this.tiempoStart = tiempoStart;
    }

}
