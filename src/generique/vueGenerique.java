/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generique;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Platform;
import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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
        private int nbCoup;
        private Chrono chrono;
        private Text temps;

    public Chrono getChrono() {
        return chrono;
    }

    public void setChrono(Chrono chrono) {
        this.chrono = chrono;
    }

    public int getNbCoup() {
        return nbCoup;
    }

    public void setNbCoup(int nbCoup) {
        this.nbCoup = nbCoup;
    }
        ArrayList<Forme> listePieces =new ArrayList<Forme>();
        private boolean partieTerminee;

    public boolean isPartieTerminee() {
        return partieTerminee;
    }

    public void setPartieTerminee(boolean partieTerminee) {
        this.partieTerminee = partieTerminee;
    }

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
        this.partieTerminee=false;
        this.chrono=new Chrono();
        
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
                vueG.setRight(null);
                vueG.temps=new Text(vueG.getChrono().getDureeTxt());
                vueG.setRight(vueG.temps);//tentative affichage temps
                
            }
            
        });
    }
  
   

    
    //initialise un gridpane vide pour le rushHour
    public void initialiserGrille() {
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
    //construit le plateau du rush hour
    public void initialiserPiecesRushHour(){
        Forme camion=new Forme();
        camion.objetCamion(0, 0, 0);//camion sur (0;0), vertical
        this.getListePieces().add(camion);
        camion=new Forme();
        camion.objetCamion(0, 2, 1);//camion sur (0;0), horizontal
        this.getListePieces().add(camion);
        camion=new Forme();
        camion.objetVoitureJoueur(5, 2);//voiture du joueur sur (5;0), horizontal
        this.getListePieces().add(camion);
        
        
        //initialise le tableau d'entiers
        for(int i=0;i<this.getListePieces().size();i++){
            for(int j=1;j<=this.getListePieces().get(i).getPositions().size();j++){
                this.getBibliotheque().getMainBoard()[(int) this.getListePieces().get(i).getPositions().get(j).get(0)][(int) this.getListePieces().get(i).getPositions().get(j).get(1)]=this.getListePieces().get(i).getCouleur();

            }
        }
    }
    
    //recherche la forme aux indices passees en parametre dans le tableau d'entiers
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


    
     //rempli le gridpane avec le tableau du modele contenant les entiers pour le rush hour
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

    


    
  

    
