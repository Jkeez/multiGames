/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generique;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import src.mvc.Forme;
import src.mvc.tableauJeu;

/**
 *
 * vueGenerique est la Vue generale de notre application qui va contenir notre bibliotheque ainsi que
 * le borderpane qui est un composant graphique fonctionnant comme un conteneur de composants.
 */


public class vueGenerique extends BorderPane {
    
	modeleGenerique bibliotheque;
        GridPane gPane;
        ArrayList<Forme> listePieces =new ArrayList<Forme>();

    public ArrayList<Forme> getListePieces() {
        return listePieces;
    }

    public void setListePieces(ArrayList<Forme> listePieces) {
        this.listePieces = listePieces;
    }

    public modeleGenerique getBibliotheque() {
        return bibliotheque;
    }

    public void setBibliotheque(modeleGenerique bibliotheque) {
        this.bibliotheque = bibliotheque;
    }
    public vueGenerique(){
        super();  
        this.bibliotheque=new modeleGenerique();
        
    }

    public GridPane getgPane() {
        return gPane;
    }

    public void setgPane(GridPane gPane) {
        this.gPane = gPane;
    }
    
    
    public static void ajouterObserver(vueGenerique vueG){
        vueG.getBibliotheque().addObserver(new Observer() {

            @Override
            public void update(Observable o, Object arg) {
                vueG.initialiserGrille();
                vueG.afficherGrille(vueG.gPane, vueG.getBibliotheque());
            }
        });
    }
    
    //different de ajouterObserver car on appel des fonctions d'actualisations construites differements
    public static void ajouterObserverRushHour(vueGenerique vueG){
        vueG.getBibliotheque().addObserver(new Observer() {

            @Override
            public void update(Observable o, Object arg) {
                vueG.initialiserGrilleRushHour();
                vueG.afficherGrilleRushHour(vueG.gPane, vueG.getBibliotheque());
            }
        });
    }
   
    //initialise un gridpane vide
    public void initialiserGrille() {
        modeleGenerique tj = new modeleGenerique();

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
    
    //initialise un gridpane vide pour le rushHour
    public void initialiserGrilleRushHour() {
        modeleGenerique tj = new modeleGenerique();

        int[][] mainBoard = tj.getMainBoard();
        int column = 0, row = 0;

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                Rectangle r = new Rectangle(20, 20, 20, 20);

                r.setFill(Color.WHITE);
                r.setStroke(Color.BLACK);

                GridPane.setRowIndex(r, row);
                GridPane.setColumnIndex(r, column++);

                gPane.getChildren().add(r);

                if (column > 11) {
                    column = 0;
                    row++;
                }

            }
        }
    }
    
    public void initialiserPiecesRushHour(){
        Forme camion=new Forme();
        camion.objetCamion(0, 0, 1);
        this.getListePieces().add(camion);
        for(int i=0;i<this.getListePieces().size();i++){
            for(int j=1;j<=this.getListePieces().get(i).getPositions().size();j++){
                this.getBibliotheque().getMainBoard()[(int) this.getListePieces().get(i).getPositions().get(j).get(0)][(int) this.getListePieces().get(i).getPositions().get(j).get(1)]=this.getListePieces().get(i).getCouleur();

            }
        }
    }
    
    public Forme rechercheFormeClickee(int ligne, int colonne){
        for(int i=0;i<this.getListePieces().size();i++){
            for(int j=1;j<=this.getListePieces().get(i).getPositions().size();j++){
                if((int)this.getListePieces().get(i).getPositions().get(j).get(0)==ligne && (int)this.getListePieces().get(i).getPositions().get(j).get(1)==colonne){
                    return this.getListePieces().get(i);
                }

            }
        }
        return null;
    }

    //rempli le gridpane avec le tableau du modele contenant les entiers
    public void afficherGrille(GridPane gPane, modeleGenerique tj) {

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
    
     //rempli le gridpane avec le tableau du modele contenant les entiers pour le rush hour
    public void afficherGrilleRushHour(GridPane gPane, modeleGenerique tj) {

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
                GridPane.setRowIndex(r, row);
                GridPane.setColumnIndex(r, column++);

                gPane.getChildren().add(r);

                if (column > 11) {
                    column = 0;
                    row++;
                }

            }
        }
}
}

    


    
  

    
