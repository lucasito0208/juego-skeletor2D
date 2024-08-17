/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TileMap;

import java.awt.image.BufferedImage;

/**
 *
 * @author Fran
 */
public class Tile {
    private BufferedImage image;
    private int type;
    private int id;
    
    public static final int NORMAL=0;
    public static final int COLISION=1;

    public int[] vectorBlockint={53,54,55,56,57,58,59,86,87,88,89,90,118,119,120,121,122,150,151,152,153,241,240,136};
    public Tile() {
    }

    public Tile(BufferedImage image, int type, int id) {
        this.image = image;
        this.type = type;
        this.id = id;
    }

    public Tile(BufferedImage image, int type) {
        this.image = image;
        this.type = type;
    }
    
    

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getType() {
       return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int asignarTipo(int contador){
        int result=Tile.NORMAL;
        for(int c:this.vectorBlockint){
            if(c==contador){
                result=Tile.COLISION;
            }
        }
        return result;
    }
    
}
