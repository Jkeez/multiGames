/*
La classe forme definit un tetrominos d'un tetris avec une couleur, map de positions dans le jeu principalement.
 */
package src.mvc;

import java.util.Random;
import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Cebrail
 */
public class Forme extends Observable {


    private boolean Falling;
    private Color couleur;

    private String shape;

    private Map<Integer, ArrayList> positions = new HashMap<Integer, ArrayList>();

    public Map<Integer, ArrayList> getPositions() {
        return positions;
    }

    public void setPositions(Map<Integer, ArrayList> positions) {
        this.positions = positions;
    }

    public Forme() {
        couleur = Color.RED;
        setShape();

    }

    public boolean isFalling() {
        return Falling;
    }

    public void setFalling(boolean isFalling) {
        this.Falling = isFalling;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    //genere une forme aleatoire
    public void setShape() {

        //test avec S
        ArrayList<Integer> p1 = new ArrayList<Integer>();//liste de coordonnees
        p1.add(0);//ligne (index 0)
        p1.add(5);//colonne (index 1)

        this.positions.put(1, p1);//insere les coordonnees dans la map

        //idem pour les autres cases
        ArrayList<Integer> p2 = new ArrayList<Integer>();
        p2.add(0);
        p2.add(6);

        this.positions.put(2, p2);

        ArrayList<Integer> p3 = new ArrayList<Integer>();
        p3.add(1);
        p3.add(6);

        this.positions.put(3, p3);

        ArrayList<Integer> p4 = new ArrayList<Integer>();
        p4.add(1);
        p4.add(7);

        this.positions.put(4, p4);

        this.shape = "s";

    }

    public String getShape() {
        return shape;
    }
}