/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generique;

import java.util.ArrayList;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mvc.Forme;
import src.mvc.modeleRushHour;

/**
 *
 * vueGenerique est la Vue generale de notre application qui va contenir notre
 * bibliotheque ainsi que le borderpane qui est un composant graphique
 * fonctionnant comme un conteneur de composants.
 */

public class vueGenerique extends BorderPane {

	private modeleGenerique bibliotheque;
	private GridPane gPane;
	private int nbCoup;
	private Chrono chrono;
	
	private boolean partieTerminee;

	public Chrono getChrono() {
		return chrono;
	}

	public void setChrono(Chrono chrono) {
		this.chrono = chrono;
	}

	public int getNbCoup() {
		return nbCoup;
	}

	public void setNbCoup(int nbCoup) {
		this.nbCoup = nbCoup;
	}

	public boolean isPartieTerminee() {
		return partieTerminee;
	}

	public void setPartieTerminee(boolean partieTerminee) {
		this.partieTerminee = partieTerminee;
	}


	public modeleGenerique getBibliotheque() {
		return bibliotheque;
	}

	public void setBibliotheque(modeleGenerique bibliotheque) {
		this.bibliotheque = bibliotheque;
	}

	public vueGenerique() {
		super();
		this.bibliotheque = new modeleGenerique();
		this.partieTerminee = false;
		this.chrono = new Chrono();

	}

        public void lancerParametreDefaut(vueGenerique vueG,GridPane gpane,Stage primaryStage,Scene scene){
                vueG.setgPane(gpane);
                vueG.setCenter(vueG.getgPane());
                
                ajouterObserver(vueG);
                vueG.setPrefSize(600,400);
                vueG.setStyle("-fx-border-color: black;");
                vueG.getBibliotheque().autoRefresh(vueG);
                vueG.initialiserGrille();
                vueG.afficherGrille(gpane, vueG.getBibliotheque());
        }
	public GridPane getgPane() {
		return gPane;
	}

	public void setgPane(GridPane gPane) {
		this.gPane = gPane;
	}

	public static void ajouterObserver(vueGenerique vueG) {
		vueG.getBibliotheque().addObserver(new Observer() {

			@Override
			public void update(Observable o, Object arg) {
				vueG.initialiserGrille();
				vueG.afficherGrille(vueG.gPane, vueG.getBibliotheque());
                                if(vueG.isPartieTerminee()){
                                    vueG.getChrono().stop(); // arrêt
                                        System.out.println(vueG.getChrono().getDureeTxt()); // affichage au format "1 h 26 min 32 s"
                                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                        alert.setTitle("Partie terminee !");
                                        alert.setHeaderText("Score");
                                        alert.setContentText("Temps: "+vueG.getChrono().getDureeTxt()+" avec "+vueG.getNbCoup()+" coup(s)!");
                                        alert.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);
                                        Optional<ButtonType> result = alert.showAndWait();
                                        
                                        if (result.get() == ButtonType.OK){
                                            Platform.exit();
                                        } else{
                                            Platform.exit();
                                        }
                                }
			}

		});
	}

	// initialise un gridpane vide pour le rushHour
	public void initialiserGrille() {
		modeleGenerique tj = new modeleGenerique();

		int[][] mainBoard = tj.getMainBoard();
		int column = 0, row = 0;

		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				Rectangle r = new Rectangle(20, 20, 20, 20);

				r.setFill(Color.WHITE);
				r.setStroke(Color.BLACK);

				GridPane.setRowIndex(r, row);
				GridPane.setColumnIndex(r, column++);

				gPane.getChildren().add(r);

				if (column > 11) {
					column = 0;
					row++;
				}

			}
		}
	}

	




	// rempli le gridpane avec le tableau du modele contenant les entiers pour le
	// rush hour
	public void afficherGrille(GridPane gPane, modeleGenerique tj) {

		int[][] mainBoard = tj.getMainBoard();
		int column = 0, row = 0;

		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 12; j++) {
				Rectangle r = new Rectangle(20, 20, 20, 20);

				r.setFill(Color.WHITE);
				r.setStroke(Color.BLACK);

				if (mainBoard[i][j] > 0) {
					switch (mainBoard[i][j]) {
					case 1:
						r.setFill(Color.AQUA);
						break;
					case 2:
						r.setFill(Color.YELLOW);
						break;
					case 3:
						r.setFill(Color.VIOLET);
						break;
					case 4:
						r.setFill(Color.BURLYWOOD);
						break;
					case 5:
						r.setFill(Color.BLUE);
						break;
					case 6:
						r.setFill(Color.RED);
						break;
					case 7:
						r.setFill(Color.GREEN);
						break;
					}

				}
				GridPane.setRowIndex(r, row);
				GridPane.setColumnIndex(r, column++);

				gPane.getChildren().add(r);

				if (column > 11) {
					column = 0;
					row++;
				}

			}
		}
	}
}
