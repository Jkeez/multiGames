
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

/**
 *
 * @author freder
 */
public class VueControleur extends Application {

    // modèle : ce qui réalise le calcule de l'expression
    Modele m;
    // affiche la saisie et le résultat
    Text affichage;

    tableauJeu tj = new tableauJeu();

    boolean[][] mainBoard = tj.getMainBoard();

    Timer chrono = new Timer("1000");

    @Override
    public void start(Stage primaryStage) throws InterruptedException {

        // initialisation du modèle que l'on souhaite utiliser
        m = new Modele();

        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();

        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();

        int column = 0;
        int row = 0;

        affichage = new Text("");
        affichage.setFont(Font.font("Verdana", 20));
        affichage.setFill(Color.RED);
        border.setTop(affichage);

        tj.addObserver(new Observer() {

            @Override
            public void update(Observable o, Object arg) {
                initialiserGrille(gPane);
                afficherGrille(gPane, tj);
            }
        });

        // on efface affichage lors du clic
        affichage.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                affichage.setText("");
            }

        });

        /*
        // création des bouton et placement dans la grille
        for (int i=0;i<12;i++) {
            for(int j=0;j<12;j++){
                
                final Text t = new Text("");
                t.setWrappingWidth(30);
                t.setTextAlignment(TextAlignment.CENTER);
                Rectangle r=new Rectangle(20,20,20,20);
              
                r.setFill(Color.WHITE);
                
                
                    
                r.setStroke(Color.BLACK);

                
                gPane.add(r, column++, row);

                if (column > 11) {
                    column = 0;
                    row++;
                }

                // un controleur (EventHandler) par bouton écoute et met à jour le champ affichage
                t.setOnMouseClicked(new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        affichage.setText(affichage.getText() + t.getText());
                    }

                });

            
            
        }
        }
        
      
        
    
        final Text t = new Text("");
        t.setWrappingWidth(30);
      
        //gPane.add(t, column++, row);
        t.setTextAlignment(TextAlignment.CENTER);
        //t.setEffect(new Shadow());
        
        // un controleur écoute le bouton "=" et déclenche l'appel du modèle
        t.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                m.calc(affichage.getText());
            }
        });
         */
        gPane.setGridLinesVisible(true);

        border.setCenter(gPane);

        Scene scene = new Scene(border, Color.LIGHTBLUE);

        primaryStage.setTitle("MultiGames");
        primaryStage.setScene(scene);

        primaryStage.show();
        Forme tetroCourrant = new Forme();
        tj.setPieceCourrante(tetroCourrant);
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

            tj.mouvementBasAuto(tj.getPieceCourrante());

        } else {
            System.out.println("Fin de partie");
        }
        //Forme tetro = new Forme();
        //m.testGrille();
        //tetro=tj.popTetrominoes( mainBoard);//ajoute tetrominos au jeu + recupere forme

        //ajouterTetrominosJeu(tetro, gPane);
        //faireTomberPiece(tetro, gPane);
    }

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

    public void ajouterTetrominosJeu(Forme tetro, GridPane gPane) throws InterruptedException {

        //test avec S
        int ligne, colonne;

        for (int i = 1; i <= 4; i++) {
            Rectangle rect = new Rectangle(20, 20, 20, 20);
            rect.setFill(tetro.getCouleur());
            rect.setStroke(Color.BLACK);

            ligne = (int) tetro.getPositions().get(i).get(0);
            colonne = (int) tetro.getPositions().get(i).get(1);

            gPane.add(rect, colonne, ligne);

        }

    }

    public void faireTomberPiece(Forme tetro, GridPane gPane) {

        int ligne = 0, colonne = 0;
        int a = 0, b = 0;

        do {

            for (int i = 1; i <= 4; i++) {
                Rectangle rect = new Rectangle(20, 20, 20, 20);
                Rectangle rectVide = new Rectangle(20, 20, 20, 20);

                rect.setStroke(Color.BLACK);
                rectVide.setStroke(Color.BLACK);

                ligne = (int) tetro.getPositions().get(i).get(0);
                colonne = (int) tetro.getPositions().get(i).get(1);
                tetro.getPositions().get(i).add(0, ligne + 1);
                tetro.getPositions().get(i).add(1, colonne);

                a = ligne;
                b = colonne;

                if (tableauJeu.isOutOfBound(a, b) != true) {
                    rectVide.setFill(Color.WHITE);
                    if (a != 0) {
                        //Node n=getNodeByRowColumnIndex(a-1,b,gPane); 
                        //gPane.getChildren().remove(n);
                        if (i != 3) {
                            gPane.add(rectVide, colonne, ligne - 1);
                        }
                        rect.setFill(tetro.getCouleur());
                        gPane.add(rect, colonne, ligne);
                    } else {
                        //Rectangle n=(Rectangle)getNodeByRowColumnIndex(a,b,gPane);
                        //gPane.getChildren().remove(n);
                        gPane.add(rectVide, colonne, ligne);
                        rect.setFill(tetro.getCouleur());
                        gPane.add(rect, colonne, ligne + 1);
                    }

                } else {
                    System.out.println("OutOfBound");
                }

            }

            a = ligne + 1;
            b = colonne;
        } while (tableauJeu.isOutOfBound(a, b) != true);

    }

    private Node getNodeByRowColumnIndex(int row, int column,
            GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();
        for (Node node : childrens) {
            if (gridPane.getRowIndex(node) == row
                    && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }

    /*
    
    private Forme getTetrominos(GridPane gridPane, int col, int row) {
    for (Node node : gridPane.getChildren()) {
        if (gridPane.getColumnIndex(node) == col && gridPane.getRowIndex(node) == row) {
            return (Forme)node;
        }
    }
    return null;
}
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
