/*
    La classe Tetris est la classe Main de notre application, qui va agire comme 
    vue en constituant notre vueGenerique(borderpane)
 */
package tetris;

import generique.vueGenerique;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import mvc.Forme;

/**
 *
 * @author cebrail
 */
public class Tetris extends Application {

    vueGenerique vueG = new vueGenerique();//recupere la vue generique (borderpane)
    tableauJeu tj = new tableauJeu();//modele du tetris
    int[][] mainBoard = vueG.getBibliotheque().getMainBoard();//recupere le tableau d'entier qui indique la position des pieces

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        //configuration
        vueG.getChrono().start(); // démarrage du chrono

        GridPane gpane = new GridPane();
        Scene scene = new Scene(vueG, 600, 400, Color.LIGHTBLUE);

        vueG.lancerParametreDefaut(vueG, gpane, primaryStage, scene);//configure la vue
        primaryStage.setTitle("MultiGames");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Debut du jeu
        Forme tetroCourrant = new Forme();//creer le premier tetrominoe
        tetroCourrant.setShape();//definit la forme de la piece
        vueG.getBibliotheque().setPieceCourrante(tetroCourrant);//actualise la piece qui est joue

        //ajoute un evenement a capturer sur la scene, capture les saisies claviers
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.RIGHT) {//verification mouvement droit
                    vueG.getBibliotheque().verificationMouvementDroit(vueG.getBibliotheque().getPieceCourrante(), vueG);
                }
                if (event.getCode() == KeyCode.LEFT) {//verification mouvement gauche
                    vueG.getBibliotheque().verificationMouvementGauche(vueG.getBibliotheque().getPieceCourrante(), vueG);
                }
            }
        });

        if (tj.ajouterPiece(vueG.getBibliotheque().getPieceCourrante(), vueG) == true) {//test de l'ajout par securite, meme s'il devrait etre toujours possible au lancement
            vueG.afficherGrille(vueG.getgPane(), vueG.getBibliotheque());//actualisation "manuelle" afin d'afficher la piece aux positions initiales
            tj.mouvementBasAuto(vueG.getBibliotheque().getPieceCourrante(), vueG, tj);//fait tomber la piece
        } else {
            System.out.println("Fin de partie");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
