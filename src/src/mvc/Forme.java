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
    private int couleur;
    private Color colorType;

    private String shape;

    private Map<Integer, ArrayList> positions = new HashMap<Integer, ArrayList>();

    public Map<Integer, ArrayList> getPositions() {
        return positions;
    }

    public void setPositions(Map<Integer, ArrayList> positions) {
        this.positions = positions;
    }

    public Forme() {
        couleur = genererCouleur();
        setColorType(couleur);
        setShape();

    }

    public boolean isFalling() {
        return Falling;
    }

    public void setFalling(boolean isFalling) {
        this.Falling = isFalling;
    }

    public int getCouleur() {
        return couleur;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }
    
    //la couleur est officiellement liee a la forme, on decide de la rendre aleatoire independament de la forme
    public int genererCouleur(){
        Random r = new Random();
        int Low = 1;
        int High = 8;
        int Result = r.nextInt(High-Low) + Low;
        
        return Result;
    }
    
    public void setColorType(int couleur){
        switch(couleur){
            case 1:
                this.colorType=Color.AQUA;
                break;
            case 2:
                this.colorType=Color.YELLOW;
                break;
            case 3:
                this.colorType=Color.VIOLET;
                break;
            case 4:
                this.colorType=Color.BURLYWOOD;
                break;
            case 5:
                this.colorType=Color.BLUE;
                break;
            case 6:
                this.colorType=Color.RED;
                break;
            case 7:
                this.colorType=Color.GREEN;
                break;
            default:
                this.colorType=Color.BLACK;
                break;
                
              
                
        }
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