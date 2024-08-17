/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import TileMap.TileMap;

/**
 *
 * @author Fran
 */
public abstract class Enemy  extends MapObject{
    
    
    
    protected  int salud;
    protected  int saludmax;
    protected  int golpeenemigo;
    protected  boolean neutralizado;
   
    
    public Enemy(TileMap tm) {
        super(tm);
    }
    
    public void recibirDamage(int damage){
        if(neutralizado){
            return;
        }
        salud-=damage;
        if(salud<=0){
            neutralizado=true;
        }
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getSaludmax() {
        return saludmax;
    }

    public void setSaludmax(int saludmax) {
        this.saludmax = saludmax;
    }

    public int getGolpeenemigo() {
        return golpeenemigo;
    }

    public void setGolpeenemigo(int golpeenemigo) {
        this.golpeenemigo = golpeenemigo;
    }

    public boolean isNeutralizado() {
        return neutralizado;
    }

    public void setNeutralizado(boolean neutralizado) {
        this.neutralizado = neutralizado;
    }
    
    
    
    
    
    
    
    
}
