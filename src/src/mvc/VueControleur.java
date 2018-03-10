
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import static java.lang.Thread.sleep;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.ObservableList;

import javafx.event.EventHandler;
import javafx.scene.Node;
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

    tableauJeu tj = new tableauJeu();

    boolean[][] mainBoard = tj.getMainBoard();//recupere le tableau d'entier qui indique la position des pieces


    @Override
    public void start(Stage primaryStage) throws InterruptedException {


        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();

        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();

        int column = 0;
        int row = 0;


        //observer appele afin de mettre a jour la vue (gridPane)
        tj.addObserver(new Observer() {

            @Override
            public void update(Observable o, Object arg) {
                initialiserGrille(gPane);
                afficherGrille(gPane, tj);
            }
        });

        gPane.setGridLinesVisible(true);

        border.setCenter(gPane);

        Scene scene = new Scene(border, Color.LIGHTBLUE);

        primaryStage.setTitle("MultiGames");
        primaryStage.setScene(scene);

        primaryStage.show();
        
        
        Forme tetroCourrant = new Forme();
        tj.setPieceCourrante(tetroCourrant);
        
        //ajoute un evenement a capturer sur la scene, capture les saisies claviers
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.RIGHT) {
                    tj.verificationMouvementDroit(tj.getPieceCourrante());
                }
                if (event.getCode() == KeyCode.LEFT) {
                    tj.verificationMouvementGauche(tj.getPieceCourrante());
                }
            }
        });

        initialiserGrille(gPane);

        if (tj.ajouterPiece(tj.getPieceCourrante()) == true) {
            afficherGrille(gPane, tj);

            tj.mouvementBasAuto(tj.getPieceCourrante());//fait tomber la piece

        } else {
            System.out.println("Fin de partie");
        }
        //Forme tetro = new Forme();
        //m.testGrille();
        //tetro=tj.popTetrominoes( mainBoard);//ajoute tetrominos au jeu + recupere forme

        //ajouterTetrominosJeu(tetro, gPane);
        //faireTomberPiece(tetro, gPane);
    }

    //initialise un gridpane vide
    public void initialiserGrille(GridPane gPane) {
        tableauJeu tj = new tableauJeu();

        boolean[][] mainBoard = tj.getMainBoard();
        int column = 0, row = 0;

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                Rectangle r = new Rectangle(20, 20, 20, 20);

                r.setFill(Color.WHITE);
                r.setStroke(Color.BLACK);

                gPane.add(r, column++, row);

                if (column > 11) {
                    column = 0;
                    row++;
                }

            }
        }
    }

    //rempli le gridpane avec le tableau du modele contenant les entiers
    public void afficherGrille(GridPane gPane, tableauJeu tj) {

        boolean[][] mainBoard = tj.getMainBoard();
        int column = 0, row = 0;

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                Rectangle r = new Rectangle(20, 20, 20, 20);

                r.setFill(Color.WHITE);
                r.setStroke(Color.BLACK);

                if (mainBoard[i][j] == true) {
                    r.setFill(Color.BLACK);
                }
                gPane.add(r, column++, row);

                if (column > 11) {
                    column = 0;
                    row++;
                }

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
