/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generique;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import src.mvc.tableauJeu;

/**
 *
 * vueGenerique est la Vue generale de notre application qui va contenir notre bibliotheque ainsi que
 * le borderpane qui est un composant graphique fonctionnant comme un conteneur de composants.
 */


public class vueGenerique extends BorderPane implements Observer {
    
	modeleGenerique bibliotheque;

    public modeleGenerique getBibliotheque() {
        return bibliotheque;
    }

    public void setBibliotheque(modeleGenerique bibliotheque) {
        this.bibliotheque = bibliotheque;
    }
    public vueGenerique(){
        super();
        
    }
    
   
    //initialise un gridpane vide
    public void initialiserGrille(GridPane gPane) {
        tableauJeu tj = new tableauJeu();

        int[][] mainBoard = tj.getMainBoard();
        int column = 0, row = 0;

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                Rectangle r = new Rectangle(20, 20, 20, 20);

                r.setFill(Color.WHITE);
                r.setStroke(Color.BLACK);

                gPane.add(r, column++, row);

                if (column > 11) {
                    column = 0;
                    row++;
                }

            }
        }
    }

    //rempli le gridpane avec le tableau du modele contenant les entiers
    public void afficherGrille(GridPane gPane, tableauJeu tj) {

        int[][] mainBoard = tj.getMainBoard();
        int column = 0, row = 0;

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                Rectangle r = new Rectangle(20, 20, 20, 20);

                r.setFill(Color.WHITE);
                r.setStroke(Color.BLACK);

                if (mainBoard[i][j] > 0) {
                    switch(mainBoard[i][j]){
                        case 1:
                            r.setFill(Color.AQUA);
                            break;
                        case 2:
                            r.setFill(Color.YELLOW);
                            break;
                        case 3:
                            r.setFill(Color.VIOLET);
                            break;
                        case 4:
                            r.setFill(Color.BURLYWOOD);
                            break;
                        case 5:
                            r.setFill(Color.BLUE);
                            break;
                        case 6:
                            r.setFill(Color.RED);
                            break;
                        case 7:
                            r.setFill(Color.GREEN);
                            break;
                    }
                    
                }
                gPane.add(r, column++, row);

                if (column > 11) {
                    column = 0;
                    row++;
                }

            }
        }
}

    


    
  

    
