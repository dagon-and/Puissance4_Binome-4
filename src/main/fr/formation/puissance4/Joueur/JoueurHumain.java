package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import javafx.scene.paint.Color;

import java.util.Scanner;

public class JoueurHumain extends Joueur {
    public JoueurHumain(Color color, Board board) {
        super(color, board);
    }

    public String entrerPosition() {
        Scanner scanner = new Scanner(System.in);
        boolean isBad = true;
        System.out.println("Veuillez entrer une position:");
        do {
            String[] position = scanner.nextLine().split(",");

            isBad = checkJetonPosiotion(Integer.parseInt(position[0]), Integer.parseInt(position[1]));
            if (!isBad)
                isBad = false;
            else {
                System.out.println("Veuillez entrer une position correcte !");
            }

        } while (isBad);








        /*************************
         * à écrire le code :
         *  -> verifier le msg de scanner
         *  -> faire parseInt pour avoir ligne et colonne
         *  -> utiliser if( checkJetonPosiotion(ligne, colonne) )
         *  -> si les positions sont mauvaises alors redemander encore (utiliser boucle)
         */
        return "#,#,COLOR";
    }

    @Override
    public String envoyer() {
        /*************************
         * à modifier le retour en utilisant entrerPosition()
         */
        if (Color.RED.equals(color))
            return "4,4,RED";
        else
            return "1,4,YELLOW";
    }

    @Override
    public void recevoir(String messageRecu) {
        /*************************
         * à écrire le code pour le msg mal formaté pour terminer ce jeu
         */

        String[] strings = messageRecu.split(",");
        int ligne = Integer.parseInt(strings[0]);
        int colonne = Integer.parseInt(strings[1]);
        board.getJetons()[ligne][colonne].setColor(Color.valueOf(strings[2]));
    }


}
