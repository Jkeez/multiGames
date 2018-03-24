
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;




import java.util.Observable;
import java.util.Observer;

import generique.vueGenerique;
import generique.Chrono;
import static generique.vueGenerique.ajouterObserver;
import java.awt.Button;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.effect.Blend;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import mvc.Forme;
import src.mvc.modeleRushHour;


public class VueControleur extends Application {

    vueGenerique vueG = new vueGenerique();
    //tableauJeu tj = new tableauJeu();
    

    int[][] mainBoard = vueG.getBibliotheque().getMainBoard();//recupere le tableau d'entier qui indique la position des pieces


    @Override
    public void start(Stage primaryStage) throws InterruptedException {
                
                modeleRushHour modRH = new modeleRushHour();
                modRH.initialiserPiecesRushHour(vueG);

                vueG.getChrono().start(); // démarrage du chrono
                int nbCoup;
                
                GridPane gpane=new GridPane();
                
                Scene scene = new Scene(vueG,600,400, Color.LIGHTBLUE);

                vueG.lancerParametreDefaut(vueG, gpane, primaryStage,scene);
                

                primaryStage.setTitle("MultiGames");
                primaryStage.setScene(scene);
                
                primaryStage.show();
                gpane.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {

                        for( Node node: gpane.getChildren()) {

                            if( node instanceof Rectangle) {
                                if( node.getBoundsInParent().contains(e.getSceneX(),  e.getSceneY())) {
                                    int ligne=GridPane.getRowIndex( node);
                                    int colonne=GridPane.getColumnIndex( node);
                                    
                                    vueG.getBibliotheque().setPieceCourrante(modRH.rechercheFormeClickee(ligne, colonne, modRH));
                                    
                                }
                            }
                        }
                    }
                });
                
                                //ajoute un evenement a capturer sur la scene, capture les saisies claviers
                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent event) {
                        if(vueG.getBibliotheque().getPieceCourrante()!=null){
                            if(vueG.getBibliotheque().getPieceCourrante().getForme1().getOrientation()==1){
                               if (event.getCode() == KeyCode.RIGHT) {
                                   
                                   
                                modRH.deplacementDroite(vueG);
                                   
                                }
                                if (event.getCode() == KeyCode.LEFT) {
                                    modRH.deplacementGauche(vueG);
                                } 
                            }else{
                                if (event.getCode() == KeyCode.UP) {
                                    modRH.deplacementHaut(vueG);
                                }
                                if (event.getCode() == KeyCode.DOWN) {
                                    modRH.deplacementBas(vueG);
                                } 
                            }
                            
                        }
                    }
                });
            }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	   
        launch(args);
    }
            }
    



