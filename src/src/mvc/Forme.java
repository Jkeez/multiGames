/*
La classe forme definit un tetrominos d'un tetris avec une couleur, map de positions dans le jeu principalement.
 */
//package src.mvc;
package mvc;

import generique.FormeGenerique;



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
 
    private FormeGenerique forme1;
    
    public FormeGenerique getForme1() {
		return forme1;
	}

	public void setForme1(FormeGenerique forme1) {
		this.forme1 = forme1;
	}

	public Forme() {
       forme1 =new FormeGenerique();
    }
   
        //genere une forme aleatoire
    public void setShape() {

        //test avec S
        ArrayList<Integer> p1 = new ArrayList<Integer>();//liste de coordonnees
        p1.add(0);//ligne (index 0)
        p1.add(5);//colonne (index 1)

        this.getForme1().getPositions().put(1, p1);//insere les coordonnees dans la map

        //idem pour les autres cases
        ArrayList<Integer> p2 = new ArrayList<Integer>();
        p2.add(0);
        p2.add(6);

        this.getForme1().getPositions().put(2, p2);

        ArrayList<Integer> p3 = new ArrayList<Integer>();
        p3.add(1);
        p3.add(6);

        this.getForme1().getPositions().put(3, p3);

        ArrayList<Integer> p4 = new ArrayList<Integer>();
        p4.add(1);
        p4.add(7);

        this.getForme1().getPositions().put(4, p4);

        this.forme1.setShape("s");

    }
    
      //genere un camion pour le rushHour ,case initiale a (x,y), orientation designera DROIT si 1 BAS sinon
    public void objetCamion(int x,int y, int orientation) {
    	
       if(orientation==1){
            this.getForme1().setOrientation(1);
           ArrayList<Integer> p1 = new ArrayList<Integer>();//liste de coordonnees
        p1.add(x);//ligne (index 0)
        p1.add(y);//colonne (index 1)

        this.getForme1().getPositions().put(1, p1);//insere les coordonnees dans la map

        //idem pour les autres cases
        ArrayList<Integer> p2 = new ArrayList<Integer>();
        p2.add(x);
        p2.add(y+1);

        this.getForme1().getPositions().put(2, p2);

        ArrayList<Integer> p3 = new ArrayList<Integer>();
        p3.add(x);
        p3.add(y+2);

        this.getForme1().getPositions().put(3, p3);

        ArrayList<Integer> p4 = new ArrayList<Integer>();
        p4.add(x);
        p4.add(y+3);

        this.getForme1().getPositions().put(4, p4);
        
        ArrayList<Integer> p5 = new ArrayList<Integer>();
        p5.add(x);
        p5.add(y+4);

        this.getForme1().getPositions().put(5, p5);
        
        ArrayList<Integer> p6 = new ArrayList<Integer>();
        p6.add(x);
        p6.add(y+5);

        this.getForme1().getPositions().put(6, p6);

        this.forme1.setShape("camion");
       }else{
           ArrayList<Integer> p1 = new ArrayList<Integer>();//liste de coordonnees
        p1.add(x);//ligne (index 0)
        p1.add(y);//colonne (index 1)

        this.getForme1().getPositions().put(1, p1);//insere les coordonnees dans la map

        //idem pour les autres cases
        ArrayList<Integer> p2 = new ArrayList<Integer>();
        p2.add(x+1);
        p2.add(y);

        this.getForme1().getPositions().put(2, p2);

        ArrayList<Integer> p3 = new ArrayList<Integer>();
        p3.add(x+2);
        p3.add(y);

        this.getForme1().getPositions().put(3, p3);

        ArrayList<Integer> p4 = new ArrayList<Integer>();
        p4.add(x+3);
        p4.add(y);

        this.getForme1().getPositions().put(4, p4);
        
        ArrayList<Integer> p5 = new ArrayList<Integer>();
        p5.add(x+4);
        p5.add(y);

        this.getForme1().getPositions().put(5, p5);
        
        ArrayList<Integer> p6 = new ArrayList<Integer>();
        p6.add(x+5);
        p6.add(y);

        this.getForme1().getPositions().put(6, p6);

        this.forme1.setShape("camion");
       }
        

    }
    
     //genere une voiture pour le rushHour ,case initiale a (x,y), orientation designera DROIT si 1 BAS sinon
    public void objetVoiture(int x,int y, int orientation) {

       if(orientation==1){
    	   this.getForme1().setOrientation(1);
           ArrayList<Integer> p1 = new ArrayList<Integer>();//liste de coordonnees
        p1.add(x);//ligne (index 0)
        p1.add(y);//colonne (index 1)

        this.getForme1().getPositions().put(1, p1);//insere les coordonnees dans la map

        //idem pour les autres cases
        ArrayList<Integer> p2 = new ArrayList<Integer>();
        p2.add(x);
        p2.add(y+1);

        this.getForme1().getPositions().put(2, p2);

        ArrayList<Integer> p3 = new ArrayList<Integer>();
        p3.add(x);
        p3.add(y+2);

        this.getForme1().getPositions().put(3, p3);

        ArrayList<Integer> p4 = new ArrayList<Integer>();
        p4.add(x);
        p4.add(y+3);

        this.getForme1().getPositions().put(4, p4);
        
        

        this.forme1.setShape("voiture");
       }else{
           ArrayList<Integer> p1 = new ArrayList<Integer>();//liste de coordonnees
        p1.add(x);//ligne (index 0)
        p1.add(y);//colonne (index 1)

        this.getForme1().getPositions().put(1, p1);//insere les coordonnees dans la map

        //idem pour les autres cases
        ArrayList<Integer> p2 = new ArrayList<Integer>();
        p2.add(x+1);
        p2.add(y);

        this.getForme1().getPositions().put(2, p2);

        ArrayList<Integer> p3 = new ArrayList<Integer>();
        p3.add(x+2);
        p3.add(y);

        this.getForme1().getPositions().put(3, p3);

        ArrayList<Integer> p4 = new ArrayList<Integer>();
        p4.add(x+3);
        p4.add(y);

        this.getForme1().getPositions().put(4, p4);
        this.forme1.setShape("voiture");
       }
        

    }
    
    //genere une voiture joueur pour le rushHour qui est la voiture a deplacer jusqua l'arriver,case initiale a (x,y), orientation designera DROIT si 1 BAS sinon
    public void objetVoitureJoueur(int x,int y) {

     
    	this.getForme1().setOrientation(1);
           ArrayList<Integer> p1 = new ArrayList<Integer>();//liste de coordonnees
        p1.add(x);//ligne (index 0)
        p1.add(y);//colonne (index 1)

        this.getForme1().getPositions().put(1, p1);//insere les coordonnees dans la map

        //idem pour les autres cases
        ArrayList<Integer> p2 = new ArrayList<Integer>();
        p2.add(x);
        p2.add(y+1);

        this.getForme1().getPositions().put(2, p2);

        ArrayList<Integer> p3 = new ArrayList<Integer>();
        p3.add(x);
        p3.add(y+2);

        this.getForme1().getPositions().put(3, p3);

        ArrayList<Integer> p4 = new ArrayList<Integer>();
        p4.add(x);
        p4.add(y+3);

        this.getForme1().getPositions().put(4, p4);
        
        this.forme1.setShape("voitureJoueur");
       
        

    }


}