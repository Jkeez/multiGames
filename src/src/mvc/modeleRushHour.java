/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.mvc;

import generique.*;

import mvc.Forme;

import java.util.ArrayList;

/**
 *
 * @author riwan
 */
public class modeleRushHour {
    private ArrayList<Forme> listePieces;

    public modeleRushHour() {
         listePieces = new ArrayList<Forme>();
    }

    public ArrayList<Forme> getListePieces() {
        return listePieces;
    }

    public void setListePieces(ArrayList<Forme> listePieces) {
        this.listePieces = listePieces;
    }
    
        // construit le plateau du rush hour
    	public void initialiserPiecesRushHour(vueGenerique vueG) {
		Forme camion = new Forme();
		camion.objetCamion(0, 0, 0);// camion sur (0;0), vertical
		this.getListePieces().add(camion);
		camion = new Forme();
		camion.objetCamion(0, 2, 1);// camion sur (0;0), horizontal
		this.getListePieces().add(camion);
		camion = new Forme();
		camion.objetVoitureJoueur(5, 2);// voiture du joueur sur (5;0), horizontal
		this.getListePieces().add(camion);

		// initialise le tableau d'entiers
		for (int i = 0; i < this.getListePieces().size(); i++) {
			for (int j = 1; j <= this.getListePieces().get(i).getForme1().getPositions().size(); j++) {
				vueG.getBibliotheque()
						.getMainBoard()[(int) this.getListePieces().get(i).getForme1().getPositions().get(j).get(0)][(int) this
								.getListePieces().get(i).getForme1().getPositions().get(j).get(1)] = this.getListePieces().get(i)
										.getForme1().getCouleur();

			}
		}
	}
        
        public Boolean deplacementDroite(vueGenerique vueG){
            vueG.getBibliotheque().verificationMouvementDroit(vueG.getBibliotheque().getPieceCourrante(), vueG);
                        
            if(vueG.getBibliotheque().getPieceCourrante().getForme1().getShape().equals("voitureJoueur")){
               
                if(vueG.getBibliotheque().isOutOfBound((int)vueG.getBibliotheque().getPieceCourrante().getForme1().getPositions().get(4).get(0), 
                    (int)(vueG.getBibliotheque().getPieceCourrante().getForme1().getPositions().get(4).get(1))+1)==true){
                    vueG.setPartieTerminee(true);
                    vueG.getBibliotheque().actualiserUI();
            }
            }
            
            return false;
        }
        public Boolean deplacementGauche(vueGenerique vueG){
            vueG.getBibliotheque().verificationMouvementGauche(vueG.getBibliotheque().getPieceCourrante(), vueG);
            return false;
        }
        public Boolean deplacementBas(vueGenerique vueG){
            vueG.getBibliotheque().verificationMouvementBas(vueG.getBibliotheque().getPieceCourrante(), vueG);
            
            return false;
        }
        public Boolean deplacementHaut(vueGenerique vueG){
            vueG.getBibliotheque().verificationMouvementHaut(vueG.getBibliotheque().getPieceCourrante(), vueG);
            
            return false;
        }
        
         	// recherche la forme aux indices passees en parametre dans le tableau d'entiers
	public Forme rechercheFormeClickee(int ligne, int colonne, modeleRushHour modRH) {
		for (int i = 0; i < modRH.getListePieces().size(); i++) {
			for (int j = 1; j <= modRH.getListePieces().get(i).getForme1().getPositions().size(); j++) {
				if ((int) modRH.getListePieces().get(i).getForme1().getPositions().get(j).get(0) == ligne
						&& (int) modRH.getListePieces().get(i).getForme1().getPositions().get(j).get(1) == colonne) {
					return modRH.getListePieces().get(i);
				}

			}
		}
		return null;
	}
    
    
    
}
