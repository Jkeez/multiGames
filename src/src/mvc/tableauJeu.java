/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.mvc;

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

    private boolean[][] mainBoard = new boolean[12][12];
    private Forme pieceCourrante;

    public Forme getPieceCourrante() {
        return pieceCourrante;
    }

    public void setPieceCourrante(Forme pieceCourrante) {
        this.pieceCourrante = pieceCourrante;
    }

    public tableauJeu() {
        initializeBoard();

    }

    public boolean[][] initializeBoard() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                mainBoard[i][j] = false;
            }
        }
        return mainBoard;
    }

    public boolean[][] getMainBoard() {
        return mainBoard;
    }

    public Forme popTetrominoes(boolean[][] tj) {
        //faire fonction randome pour choix tetrominoes
        Forme f1 = new Forme();
        f1.setCouleur(Color.RED);
        //test avec S
        int ligne, colonne;

        for (int i = 1; i <= 4; i++) {
            ligne = (int) f1.getPositions().get(i).get(0);
            colonne = (int) f1.getPositions().get(i).get(1);
            tj[ligne][colonne] = true;//faire verif avant ajout (libre ou pas)

        }

        return f1;//renvoi la forme afin de l'ajouter en dur au jeu

    }

    public boolean ajouterPiece(Forme tetro) throws InterruptedException {
        int ligne, colonne;

        for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);

            if (caseLibre(ligne, colonne)) {
                mainBoard[ligne][colonne] = true;
            } else {
                return false;
            }

        }

        return true;
    }

    public void actualiserUI() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setChanged();
                notifyObservers();
            }
        });

    }

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

    public boolean caseLibre(int ligne, int colonne) {
        if (mainBoard[ligne][colonne] == true) {
            return false;
        }
        return true;
    }

    public static boolean isOutOfBound(int a, int b) {
        if (a >= 12 || b >= 12 || a < 0 || b < 0) {
            return true;
        }
        return false;

    }

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
            System.out.println(a);
            if (tableauJeu.isOutOfBound(a, b) == false) {
                compteur = compteur + 1;

            }

        }

        if (compteur == 4) {
            descendrePiece(tetro);
        } else {
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

        for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne);
            tetro.getPositions().get(i).add(1, colonne);

            a = ligne + 1;
            b = colonne;
            mainBoard[ligne][colonne] = false;

        }

        for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne + 1);
            tetro.getPositions().get(i).add(1, colonne);

            a = ligne + 1;
            b = colonne;
            mainBoard[ligne + 1][colonne] = true;

        }

    }

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
                    Logger.getLogger(tableauJeu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, 0, 1000);

    }

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
            b = colonne;

            if (tableauJeu.isOutOfBound(a, b + 1) != true) {
                compteur = compteur + 1;

            }

        }

        if (compteur == 4) {
            mouvementDroit(tetro);
        }
    }

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
            mainBoard[ligne][colonne] = false;

        }

        for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne);
            tetro.getPositions().get(i).add(1, colonne + 1);

            a = ligne;
            b = colonne + 1;
            mainBoard[ligne][colonne + 1] = true;

        }
        actualiserUI();
    }

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
            b = colonne;

            if (tableauJeu.isOutOfBound(a, b - 1) != true) {
                compteur = compteur + 1;

            }

        }

        if (compteur == 4) {
            mouvementGauche(tetro);
        }
    }

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
            mainBoard[ligne][colonne] = false;

        }

        for (int i = 1; i <= 4; i++) {

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);
            tetro.getPositions().get(i).add(0, ligne);
            tetro.getPositions().get(i).add(1, colonne - 1);

            a = ligne;
            b = colonne - 1;
            mainBoard[ligne][colonne - 1] = true;

        }
        actualiserUI();
    }

}
