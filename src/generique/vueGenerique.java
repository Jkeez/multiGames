/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generique;

import javafx.scene.layout.BorderPane;

/**
 *
 * vueGenerique est la Vue generale de notre application qui va contenir notre bibliotheque ainsi que
 * le borderpane qui est un composant graphique fonctionnant comme un conteneur de composants.
 */


public class vueGenerique extends BorderPane{
   public modeleGenerique bibliotheque;

    public modeleGenerique getBibliotheque() {
        return bibliotheque;
    }

    public void setBibliotheque(modeleGenerique bibliotheque) {
        this.bibliotheque = bibliotheque;
    }

    public vueGenerique(){
        super();
        bibliotheque=new modeleGenerique();
    }
    
    
}