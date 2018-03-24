
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
    tableauJeu tj = new tableauJeu();
    

    int[][] mainBoard = vueG.getBibliotheque().getMainBoard();//recupere le tableau d'entier qui indique la position des pieces


    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        Chrono chrono = new Chrono();
        
      
        
        
        List<String> choices = new ArrayList<>();
        
        choices.add("Tetris");
        choices.add("Rush Hour");
        
        ChoiceDialog<String> dialog = new ChoiceDialog<>("--Liste de Jeux--", choices);
        dialog.setTitle("Choisir un jeu");
        dialog.setContentText("Jeu :");
        
        Optional<String> result = dialog.showAndWait();
        
        if(result.isPresent()){
           /* if(result.get()=="Tetris"){
                chrono.start(); // démarrage du chrono
        
                System.out.println("choix :"+result.get());


                int column = 0;
                int row = 0;
                GridPane gpane=new GridPane();


                vueG.setgPane(gpane);
                vueG.setCenter(vueG.getgPane());
                vueG.getgPane().setGridLinesVisible(false);


                ajouterObserver(vueG);
                vueG.setPrefSize(600,400);
                vueG.setStyle("-fx-border-color: black;");
                Parent border = null;

                vueG.initialiserGrille();
                Scene scene = new Scene(vueG,600,400, Color.LIGHTBLUE);


                primaryStage.setTitle("MultiGames");
                primaryStage.setScene(scene);

                primaryStage.show();


                Forme tetroCourrant = new Forme();
                tetroCourrant.setShape();
                vueG.getBibliotheque().setPieceCourrante(tetroCourrant);



                //ajoute un evenement a capturer sur la scene, capture les saisies claviers
                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent event) {
                        if (event.getCode() == KeyCode.RIGHT) {
                            vueG.getBibliotheque().verificationMouvementDroit(vueG.getBibliotheque().getPieceCourrante(), vueG);
                        }
                        if (event.getCode() == KeyCode.LEFT) {
                            vueG.getBibliotheque().verificationMouvementGauche(vueG.getBibliotheque().getPieceCourrante(),vueG);
                        }
                    }
                });



                if (vueG.getBibliotheque().ajouterPiece(vueG.getBibliotheque().getPieceCourrante()) == true) {
                    vueG.afficherGrille(vueG.getgPane(), vueG.getBibliotheque());

                    tj.mouvementBasAuto(vueG.getBibliotheque().getPieceCourrante(),vueG);//fait tomber la piece

                } else {
                    System.out.println("Fin de partie");
                }
                //Forme tetro = new Forme();
                //m.testGrille();
                //tetro=tj.popTetrominoes( mainBoard);//ajoute tetrominos au jeu + recupere forme

                //ajouterTetrominosJeu(tetro, gPane);
                //faireTomberPiece(tetro, gPane);
            }*/
            if(result.get()=="Rush Hour"){
                
                
                vueG.getChrono().start(); // démarrage du chrono
                int nbCoup;
                
                System.out.println("choix :"+result.get());
                GridPane gpane=new GridPane();
                vueG.lancerParametreDefaut(vueG, gpane, primaryStage);
                

            }
            }
     
    }


    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	   
        launch(args);
    }

}
