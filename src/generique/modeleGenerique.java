/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generique;



import static java.lang.Thread.sleep;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import src.mvc.Forme;

/**
 *
 * @author Cebrail
 */
public class modeleGenerique extends Observable {

    private int[][] mainBoard = new int[12][12];
    private Forme pieceCourrante;

    public Forme getPieceCourrante() {
        return pieceCourrante;
    }

    public void setPieceCourrante(Forme pieceCourrante) {
        this.pieceCourrante = pieceCourrante;
    }

    public modeleGenerique() {
        initializeBoard();

    }

    public int[][] initializeBoard() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                mainBoard[i][j] = 0;
            }
        }
        return mainBoard;
    }

    public int[][] getMainBoard() {
        return mainBoard;
    }

    //ajoute au modele les positions de la piece que l'on souhaite ajouter, renvoie false si l'ajout est impossible.
    public boolean ajouterPiece(Forme tetro) throws InterruptedException {
        int ligne, colonne;

        //parcours chaque case de la piece pour recuperer sa ligne et sa colonne
        for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);

            if (caseLibre(ligne, colonne)) {
                mainBoard[ligne][colonne] = tetro.getCouleur();
            } else {
                return false;
            }

        }

        return true;
    }

    //fonction qui declenche l'observer afin de mettre a jour l'UI
    public void actualiserUI() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setChanged();
                notifyObservers();
            }
        });

    }

    //permet d'arreter le thread en cours
    public void pauseUneSeconde() throws InterruptedException {
        Thread one = new Thread() {
            public void run() {
                try {

                    Thread.sleep(2000);
                    actualiserUI();

                } catch (InterruptedException v) {
                    System.out.println(v);
                }
            }
        };

        one.start();

    }

    //verifie si la case cible est libre
    public boolean caseLibre(int ligne, int colonne) {
        if (mainBoard[ligne][colonne] > 0) {
            return false;
        }
        return true;
    }

    //verifie que la case cible n'est pas hors du tableau
    public static boolean isOutOfBound(int a, int b) {
        if (a >= 12 || b >= 12 || a < 0 || b < 0) {
            return true;
        }
        return false;

    }

    //verifie pour chaque case que le mouvement vers le bas est autorise, ajoute une nouvelle piece sinon
    public void faireTomberPiece(Forme tetro, Timer time) throws InterruptedException {
        int ligne = 0, colonne = 0;
        int a = 0, b = 0;
        int compteur = 0;

        
        
        for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne);
            tetro.getPositions().get(i).add(1, colonne);

            a = ligne + 1;
            b = colonne;
            mainBoard[ligne][colonne]=0;
           

        }
        
        for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne);
            tetro.getPositions().get(i).add(1, colonne);

            a = ligne + 1;
            b = colonne;
            if (modeleGenerique.isOutOfBound(a, b) == false) {
                if(caseLibre(a,b)==true){
                    compteur = compteur + 1;
                }
                
                
            }

        }

        if (compteur == 4) {
            descendrePiece(tetro);
        } else {
            for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne);
            tetro.getPositions().get(i).add(1, colonne);

            a = ligne + 1;
            b = colonne;
            mainBoard[ligne][colonne]=tetro.getCouleur();
           

        }
            time.cancel();
            Forme newF = new Forme();
            this.ajouterPiece(newF);
            actualiserUI();
            this.setPieceCourrante(newF);
            this.mouvementBasAuto(newF);

        }
    }

    //fait tomber une forme de une case
    public void descendrePiece(Forme tetro) throws InterruptedException {

        int ligne = 0, colonne = 0;
        int a = 0, b = 0;
        int compteur = 0;

        //efface la forme du tableau afin d'eviter les chevauchements de cases de la meme piece
        for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne);
            tetro.getPositions().get(i).add(1, colonne);

            a = ligne + 1;
            b = colonne;
            mainBoard[ligne][colonne] = 0;

        }

        //ajoute la forme a la ligne suivante
        for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne + 1);
            tetro.getPositions().get(i).add(1, colonne);

            a = ligne + 1;
            b = colonne;
            mainBoard[ligne + 1][colonne] = tetro.getCouleur();

        }

    }

    //lance un timer pour faire descendre la piece courrante d une case chaque seconde
    public void mouvementBasAuto(Forme tetro) {
        Timer time = new Timer();
        time.schedule(
                new TimerTask() {

            @Override
            public void run() {
                try {
                    actualiserUI();
                    faireTomberPiece(tetro, time);
                    actualiserUI();
                } catch (InterruptedException ex) {
                    Logger.getLogger(modeleGenerique.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, 0, 1000);

    }

    //verifie que le mouvement vers la droite est autorise
    public void verificationMouvementDroit(Forme tetro) {
        int ligne = 0, colonne = 0;
        int a = 0, b = 0;
        int compteur = 0;
        
        for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne);
            tetro.getPositions().get(i).add(1, colonne);

            a = ligne;
            b = colonne+1;
            mainBoard[ligne][colonne]=0;
           

        }

        for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne);
            tetro.getPositions().get(i).add(1, colonne);

            a = ligne;
            b = colonne;

            if (modeleGenerique.isOutOfBound(a, b + 1) != true) {
                if(caseLibre(a,b+1)==true){
                    compteur = compteur + 1;
                }
                

            }

        }

        if (compteur == 4) {
            mouvementDroit(tetro);
        }
        else{
            for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne);
            tetro.getPositions().get(i).add(1, colonne);

            a = ligne;
            b = colonne+1;
            mainBoard[ligne][colonne]=tetro.getCouleur();
           

        }
        }
    }

    //deplacement vers la droite de la piece
    public void mouvementDroit(Forme tetro) {
        int ligne = 0, colonne = 0;
        int a = 0, b = 0;
        int compteur = 0;

        for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne);
            tetro.getPositions().get(i).add(1, colonne);

            a = ligne;
            b = colonne + 1;
            mainBoard[ligne][colonne] = 0;

        }

        for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne);
            tetro.getPositions().get(i).add(1, colonne + 1);

            a = ligne;
            b = colonne + 1;
            mainBoard[ligne][colonne + 1] = tetro.getCouleur();

        }
        actualiserUI();
    }

    //verifie que le mouvement vers la gauche est autorise
    public void verificationMouvementGauche(Forme tetro) {
        int ligne = 0, colonne = 0;
        int a = 0, b = 0;
        int compteur = 0;
        
        for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne);
            tetro.getPositions().get(i).add(1, colonne);

            a = ligne;
            b = colonne-1;
            mainBoard[ligne][colonne]=0;
           

        }
        

        for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne);
            tetro.getPositions().get(i).add(1, colonne);

            a = ligne;
            b = colonne;

            if (modeleGenerique.isOutOfBound(a, b - 1) != true) {
                if(caseLibre(a,b-1)==true){
                    compteur = compteur + 1;
                }

            }

        }

        if (compteur == 4) {
            mouvementGauche(tetro);
        }
        else{
            for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne);
            tetro.getPositions().get(i).add(1, colonne);

            a = ligne;
            b = colonne;
            mainBoard[ligne][colonne]=tetro.getCouleur();
           

        }
        }
    }

    //deplacement vers la gauche
    public void mouvementGauche(Forme tetro) {
        int ligne = 0, colonne = 0;
        int a = 0, b = 0;
        int compteur = 0;

        for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne);
            tetro.getPositions().get(i).add(1, colonne);

            a = ligne;
            b = colonne - 1;
            mainBoard[ligne][colonne] = 0;

        }

        for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne);
            tetro.getPositions().get(i).add(1, colonne - 1);

            a = ligne;
            b = colonne - 1;
            mainBoard[ligne][colonne - 1] = tetro.getCouleur();

        }
        actualiserUI();
    }

}

