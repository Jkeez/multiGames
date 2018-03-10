/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    enum Tetrominoes {
        NoShape, ZShape, SShape, LineShape,
        TShape, SquareShape, LShape, MirroredLShape
    };

    private Tetrominoes pieceShape;
    private int coords[][];
    private int[][][] coordsTable;
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

    private void setX(int index, int x) {
        coords[index][0] = x;
    }

    private void setY(int index, int y) {
        coords[index][1] = y;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public int x(int index) {
        return coords[index][0];
    }

    public int y(int index) {
        return coords[index][1];
    }

    public void setShape() {
        //appel la fonction randomShape pour determiner la forme et faire un switchCase
        //test avec S
        ArrayList<Integer> p1 = new ArrayList<Integer>();
        p1.add(0);
        p1.add(5);

        this.positions.put(1, p1);

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

    public void setRandomShape() {
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        Tetrominoes[] values = Tetrominoes.values();
    }

    public void testGrille() {
        setChanged();
        notifyObservers();
    }

}
