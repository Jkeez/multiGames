
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;




import java.util.Observable;
import java.util.Observer;

import generique.vueGenerique;
import static generique.vueGenerique.ajouterObserver;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.util.Duration;
import src.mvc.Forme;
import src.mvc.tableauJeu;


public class VueControleur extends Application {

    vueGenerique vueG = new vueGenerique();
    
    
    tableauJeu tj = new tableauJeu();

    //int[][] mainBoard = vueG.getBibliotheque().getMainBoard();//recupere le tableau d'entier qui indique la position des pieces


    @Override
    public void start(Stage primaryStage) throws InterruptedException {
    	

        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
       
       
        // permet de placer les diffrents boutons dans une grille

        int column = 0;
        int row = 0;
        GridPane gpane=new GridPane();

        vueG.setgPane(gpane);
        vueG.setCenter(vueG.getgPane());
        vueG.getgPane().setGridLinesVisible(true);
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
        vueG.getBibliotheque().setPieceCourrante(tetroCourrant);
        
        
        
        //ajoute un evenement a capturer sur la scene, capture les saisies claviers
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.RIGHT) {
                    vueG.getBibliotheque().verificationMouvementDroit(vueG.getBibliotheque().getPieceCourrante());
                }
                if (event.getCode() == KeyCode.LEFT) {
                    vueG.getBibliotheque().verificationMouvementGauche(vueG.getBibliotheque().getPieceCourrante());
                }
            }
        });

        

        if (vueG.getBibliotheque().ajouterPiece(vueG.getBibliotheque().getPieceCourrante()) == true) {
            vueG.afficherGrille(vueG.getgPane(), vueG.getBibliotheque());

            vueG.getBibliotheque().mouvementBasAuto(vueG.getBibliotheque().getPieceCourrante());//fait tomber la piece

        } else {
            System.out.println("Fin de partie");
        }
        //Forme tetro = new Forme();
        //m.testGrille();
        //tetro=tj.popTetrominoes( mainBoard);//ajoute tetrominos au jeu + recupere forme

        //ajouterTetrominosJeu(tetro, gPane);
        //faireTomberPiece(tetro, gPane);
    }


    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
        launch(args);
    }

}
