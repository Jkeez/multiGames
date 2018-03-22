package generique;
/*
La classe forme definit un tetrominos d'un tetris avec une couleur, map de positions dans le jeu principalement.
 */
//package src.mvc;

import java.util.Random;


import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import mvc.Forme;

/**
 *
 * @author Cebrail
 */
public class FormeGenerique {
	private boolean Falling;
    private int couleur;
    private Color colorType;

    private String shape;
    
    private int orientation;
   

    public Color getColorType() {
        return colorType;
    }

    public void setColorType(Color colorType) {
        this.colorType = colorType;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    private Map<Integer, ArrayList> positions = new HashMap<Integer, ArrayList>();

    public Map<Integer, ArrayList> getPositions() {
        return positions;
    }

    public void setPositions(Map<Integer, ArrayList> positions) {
        this.positions = positions;
    }

    public FormeGenerique() {
        couleur = genererCouleur();
        setColorType(couleur);
    }

    public void setShape(String shape) {
		this.shape = shape;
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

 

    public String getShape() {
        return shape;
    }

}
