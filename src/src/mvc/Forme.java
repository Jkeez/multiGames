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
import java.util.concurrent.ThreadLocalRandom;
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

        int randomNum = ThreadLocalRandom.current().nextInt(1, 7 + 1);
        ArrayList<Integer> p1 = new ArrayList<Integer>();//liste de coordonnees
        ArrayList<Integer> p2 = new ArrayList<Integer>();
        ArrayList<Integer> p3 = new ArrayList<Integer>();
        ArrayList<Integer> p4 = new ArrayList<Integer>();


        switch(randomNum){
            case 1:
                p1.add(0);//ligne (index 0)
                p1.add(5);//colonne (index 1)
                p2.add(0);
                p2.add(6);
                p3.add(1);
                p3.add(6);
                p4.add(1);
                p4.add(7);
                this.forme1.setShape("z");
                System.out.println("z");
                break;
            case 2:
                p1.add(1);//ligne (index 0)
                p1.add(5);//colonne (index 1)
                p2.add(1);
                p2.add(6);
                p3.add(0);
                p3.add(6);
                p4.add(0);
                p4.add(7);
                System.out.println("s");
                this.forme1.setShape("s");
                break;
            case 3:
                p1.add(0);//ligne (index 0)
                p1.add(5);//colonne (index 1)
                p2.add(0);
                p2.add(6);
                p3.add(0);
                p3.add(7);
                p4.add(1);
                p4.add(7);
                System.out.println("j");
                this.forme1.setShape("j");
                break;
            case 4:
                p1.add(0);//ligne (index 0)
                p1.add(5);//colonne (index 1)
                p2.add(1);
                p2.add(5);
                p3.add(0);
                p3.add(6);
                p4.add(0);
                p4.add(7);
                System.out.println("l");
                this.forme1.setShape("l");
                break;
            case 5:
                p1.add(0);//ligne (index 0)
                p1.add(5);//colonne (index 1)
                p2.add(0);
                p2.add(6);
                p3.add(0);
                p3.add(7);
                p4.add(1);
                p4.add(6);
                System.out.println("t");
                this.forme1.setShape("t");
                break;
            case 6:
                p1.add(0);//ligne (index 0)
                p1.add(5);//colonne (index 1)
                p2.add(0);
                p2.add(6);
                p3.add(1);
                p3.add(5);
                p4.add(1);
                p4.add(6);
                System.out.println("o");
                this.forme1.setShape("o");
                break;
            case 7:
                p1.add(0);//ligne (index 0)
                p1.add(5);//colonne (index 1)
                p2.add(0);
                p2.add(6);
                p3.add(0);
                p3.add(7);
                p4.add(0);
                p4.add(8);
                System.out.println("i");
                this.forme1.setShape("i");
                break;
        }
        this.getForme1().getPositions().put(1, p1);//insere les coordonnees dans la map
        this.getForme1().getPositions().put(2, p2);
        this.getForme1().getPositions().put(3, p3);
        this.getForme1().getPositions().put(4, p4);

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