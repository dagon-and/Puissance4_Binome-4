import fr.formation.puissance4.Board.Board;
import fr.formation.puissance4.Joueur.*;
import fr.formation.puissance4.Piece.Jeton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.lang.reflect.Field;
import java.util.Arrays;

public class Main {

    public static void printBoard(Jeton[][] jetons) {
        for (int ligne = 0; ligne < jetons.length; ligne++) {
            for (int colonne = 0; colonne < jetons[ligne].length; colonne++) {
                if (jetons[ligne][colonne].getColor().equals(Color.YELLOW))
                    System.out.print("Y ");
                else if (jetons[ligne][colonne].getColor().equals(Color.RED))
                    System.out.print("R ");
                else //if (jetons[ligne][colonne].getColor().equals(Color.TRANSPARENT))
                    System.out.print("- ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Jeton[][] jetons = new Jeton[6][7];
        for (int ligne = 0; ligne < jetons.length; ligne++) {
            for (int colonne = 0; colonne < jetons[ligne].length; colonne++) {
                Circle diskPreview = new Circle(40);
                diskPreview.setOpacity(1);
                diskPreview.setFill(Color.TRANSPARENT);
                jetons[ligne][colonne] = new Jeton(diskPreview);
            }
        }

        // Example pour réaliser des testes de fonctionalités des classes dérivées de class Joueur
        JoueurHumain joueur = new JoueurHumain(Color.YELLOW, new Board(jetons));

        System.out.print(joueur.setJeton(5, 2) + " " +
                joueur.setJeton(4, 2) + " " +
                joueur.setJeton(3, 2) + " " +
                joueur.setJeton(2, 2) + "\n");

        System.out.print(joueur.setJeton(5, 3) + " " +
                joueur.setJeton(5, 4) + " " +
                joueur.setJeton(5, 5) + "\n");

        jetons[4][3].setColor(Color.YELLOW);
        jetons[4][4].setColor(Color.RED);
        jetons[4][5].setColor(Color.YELLOW);
        jetons[3][3].setColor(Color.RED);
        jetons[3][4].setColor(Color.YELLOW);
        jetons[3][5].setColor(Color.RED);
        jetons[3][4].setColor(Color.YELLOW);

        //jetons[5][2].setColor(Color.RED);

        System.out.print(joueur.setJeton(5, 6) + " " +
                joueur.setJeton(2, 5) + " " +
                joueur.setJeton(2, 3) + "\n");

        System.out.println();
        System.out.println(joueur.checkDefaite(2, 2));
        System.out.println(joueur.checkDefaite(3, 2));
        System.out.println();
        System.out.println(joueur.checkDefaite(5, 3));
        System.out.println(joueur.checkDefaite(5, 6));
        System.out.println(joueur.checkDefaite(5, 4));
        System.out.println();
        System.out.println(joueur.checkDefaite(4, 5));
        System.out.println();
        System.out.println(joueur.checkDefaite(4, 3));

        printBoard(jetons);

        System.out.println();

//        for (Field field : Color.class.getFields())
//            System.out.println(field.getName());

        Color color = Color.YELLOW;
        Arrays.stream(Color.class.getFields())
                .filter(v -> Color.valueOf(v.getName()).equals(color))
                .forEach(v -> System.out.println("Le coleur est " + v.getName()));
    }
}
