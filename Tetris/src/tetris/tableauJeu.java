/*
la classe tableauJeu represente le modele du Tetris,
qui inclut les methodes permettant de faire tomber une piece, ajouter une piece au jeu ou bien de declarer
la partie terminée, qui va etre gerer par le package Generique
 */
package tetris;

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
import mvc.Forme;

/**
 *
 * @author Cebrail
 */
public class tableauJeu extends Observable {

    public tableauJeu() {
    }

    //verifie pour chaque case que le mouvement vers le bas est autorise, ajoute une nouvelle piece sinon et si 
    //l'ajout est impossible, termine la partie
    public void faireTomberPiece(Forme tetro, Timer time, vueGenerique vueG, tableauJeu tj) throws InterruptedException {
        int ligne = 0, colonne = 0;
        int a = 0, b = 0;
        int compteur = 0;

        for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getForme1().getPositions().get(i).get(0);
            colonne = (int) tetro.getForme1().getPositions().get(i).get(1);
            tetro.getForme1().getPositions().get(i).add(0, ligne);
            tetro.getForme1().getPositions().get(i).add(1, colonne);

            a = ligne + 1;
            b = colonne;
            vueG.getBibliotheque().getMainBoard()[ligne][colonne] = 0;

        }

        for (int i = 1; i <= tetro.getForme1().getPositions().size(); i++) {

            ligne = (int) tetro.getForme1().getPositions().get(i).get(0);
            colonne = (int) tetro.getForme1().getPositions().get(i).get(1);
            tetro.getForme1().getPositions().get(i).add(0, ligne);
            tetro.getForme1().getPositions().get(i).add(1, colonne);

            a = ligne + 1;
            b = colonne;
            if (modeleGenerique.isOutOfBound(a, b) == false) {
                if (vueG.getBibliotheque().caseLibre(a, b) == true) {
                    compteur = compteur + 1;
                }

            }

        }

        if (compteur == tetro.getForme1().getPositions().size()) {
            descendrePiece(tetro, vueG);
        } else {
            for (int i = 1; i <= 4; i++) {

                ligne = (int) tetro.getForme1().getPositions().get(i).get(0);
                colonne = (int) tetro.getForme1().getPositions().get(i).get(1);
                tetro.getForme1().getPositions().get(i).add(0, ligne);
                tetro.getForme1().getPositions().get(i).add(1, colonne);

                a = ligne + 1;
                b = colonne;
                vueG.getBibliotheque().getMainBoard()[ligne][colonne] = tetro.getForme1().getCouleur();

            }
            time.cancel();
            Forme newF = new Forme();
            newF.setShape();
            if (tj.ajouterPiece(newF, vueG) == true) {

                vueG.getBibliotheque().actualiserUI();
                vueG.getBibliotheque().setPieceCourrante(newF);
                this.mouvementBasAuto(newF, vueG, tj);

            } else {

                vueG.setPartieTerminee(true);
            }

        }
    }

    //ajoute au modele(de notre librairie) les positions de la piece que l'on souhaite ajouter, renvoie false si l'ajout est impossible.
    public boolean ajouterPiece(Forme tetro, vueGenerique vueG) throws InterruptedException {
        int ligne, colonne;

        //parcours chaque case de la piece pour recuperer sa ligne et sa colonne
        for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getForme1().getPositions().get(i).get(0);
            colonne = (int) tetro.getForme1().getPositions().get(i).get(1);

            if (vueG.getBibliotheque().caseLibre(ligne, colonne)) {
                vueG.getBibliotheque().getMainBoard()[ligne][colonne] = tetro.getForme1().getCouleur();
            } else {
                return false;
            }

        }

        return true;
    }

    //fait tomber une forme de une case
    public void descendrePiece(Forme tetro, vueGenerique vueG) throws InterruptedException {

        int ligne = 0, colonne = 0;
        int a = 0, b = 0;
        int compteur = 0;

        //efface la forme du tableau afin d'eviter les chevauchements de cases de la meme piece
        for (int i = 1; i <= tetro.getForme1().getPositions().size(); i++) {

            ligne = (int) tetro.getForme1().getPositions().get(i).get(0);
            colonne = (int) tetro.getForme1().getPositions().get(i).get(1);
            tetro.getForme1().getPositions().get(i).add(0, ligne);
            tetro.getForme1().getPositions().get(i).add(1, colonne);

            a = ligne + 1;
            b = colonne;
            vueG.getBibliotheque().getMainBoard()[ligne][colonne] = 0;

        }

        //ajoute la forme a la ligne suivante
        for (int i = 1; i <= tetro.getForme1().getPositions().size(); i++) {

            ligne = (int) tetro.getForme1().getPositions().get(i).get(0);
            colonne = (int) tetro.getForme1().getPositions().get(i).get(1);
            tetro.getForme1().getPositions().get(i).add(0, ligne + 1);
            tetro.getForme1().getPositions().get(i).add(1, colonne);

            a = ligne + 1;
            b = colonne;
            vueG.getBibliotheque().getMainBoard()[ligne + 1][colonne] = tetro.getForme1().getCouleur();

        }

    }

    //lance un timer pour faire descendre la piece courrante d une case chaque seconde
    public void mouvementBasAuto(Forme tetro, vueGenerique vueG, tableauJeu tj) {
        Timer time = new Timer();
        time.schedule(
                new TimerTask() {

            @Override
            public void run() {
                vueG.getBibliotheque().actualiserUI();
                try {
                    faireTomberPiece(tetro, time, vueG, tj);
                } catch (InterruptedException ex) {
                    Logger.getLogger(tableauJeu.class.getName()).log(Level.SEVERE, null, ex);
                }
                vueG.getBibliotheque().actualiserUI();
            }
        }, 0, 1000);

    }

}
