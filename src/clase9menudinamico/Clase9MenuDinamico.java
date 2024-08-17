/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clase9menudinamico;

import javax.swing.JFrame;

/**
 *
 * @author Fran
 */
public class Clase9MenuDinamico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame ventana=new JFrame("One Day Later");
        ventana.setContentPane(new PanelJuego());
        ventana.setVisible(true);
        ventana.pack();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
