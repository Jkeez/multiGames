/*
la classe tableauJeu permet la gestion des deplacements des pieces du jeu.
 */
package src.mvc;

import generique.modeleGenerique;
import generique.vueGenerique;
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

/**
 *
 * @author Cebrail
 */
public class tableauJeu extends Observable {

    private int[][] mainBoard = new int[12][12];
    private Forme pieceCourrante;

    public Forme getPieceCourrante() {
        return pieceCourrante;
    }

    public void setPieceCourrante(Forme pieceCourrante) {
        this.pieceCourrante = pieceCourrante;
    }

    public tableauJeu() {
        

    }

    //verifie pour chaque case que le mouvement vers le bas est autorise, ajoute une nouvelle piece sinon
    public void faireTomberPiece(Forme tetro, Timer time, vueGenerique vueG) throws InterruptedException {
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
            vueG.getBibliotheque().getMainBoard()[ligne][colonne]=0;
           

        }
        
        for (int i = 1; i <= tetro.getPositions().size(); i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne);
            tetro.getPositions().get(i).add(1, colonne);

            a = ligne + 1;
            b = colonne;
            if (modeleGenerique.isOutOfBound(a, b) == false) {
                if(vueG.getBibliotheque().caseLibre(a,b)==true){
                    compteur = compteur + 1;
                }
                
                
            }

        }

        if (compteur == tetro.getPositions().size()) {
            descendrePiece(tetro,vueG);
        } else {
            for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne);
            tetro.getPositions().get(i).add(1, colonne);

            a = ligne + 1;
            b = colonne;
            vueG.getBibliotheque().getMainBoard()[ligne][colonne]=tetro.getCouleur();
           

        }
            time.cancel();
            Forme newF = new Forme();
            vueG.getBibliotheque().ajouterPiece(newF);
            vueG.getBibliotheque().actualiserUI();
            vueG.getBibliotheque().setPieceCourrante(newF);
            this.mouvementBasAuto(newF,vueG);

        }
    }

    //fait tomber une forme de une case
    public void descendrePiece(Forme tetro, vueGenerique vueG) throws InterruptedException {

        int ligne = 0, colonne = 0;
        int a = 0, b = 0;
        int compteur = 0;

        //efface la forme du tableau afin d'eviter les chevauchements de cases de la meme piece
        for (int i = 1; i <= tetro.getPositions().size(); i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne);
            tetro.getPositions().get(i).add(1, colonne);

            a = ligne + 1;
            b = colonne;
            vueG.getBibliotheque().getMainBoard()[ligne][colonne]=0;

        }

        //ajoute la forme a la ligne suivante
        for (int i = 1; i <= tetro.getPositions().size(); i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne + 1);
            tetro.getPositions().get(i).add(1, colonne);

            a = ligne + 1;
            b = colonne;
            vueG.getBibliotheque().getMainBoard()[ligne + 1][colonne] = tetro.getCouleur();

        }

    }

    //lance un timer pour faire descendre la piece courrante d une case chaque seconde
    public void mouvementBasAuto(Forme tetro,vueGenerique vueG) {
        Timer time = new Timer();
        time.schedule(
                new TimerTask() {

            @Override
            public void run() {
                try {
                    vueG.getBibliotheque().actualiserUI();
                    faireTomberPiece(tetro, time,vueG);
                    vueG.getBibliotheque().actualiserUI();
                } catch (InterruptedException ex) {
                    Logger.getLogger(tableauJeu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, 0, 1000);

    }


}
