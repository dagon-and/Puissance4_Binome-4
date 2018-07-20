package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import javafx.scene.paint.Color;

import java.util.Random;

public class JoueurAIRandom extends Joueur {
    private final int NB_ESSAIS = 700;

    public JoueurAIRandom(Color color, Board board) {
        super(color, board);
    }


    @Override
    public String entrerPosition() {

        Random random = new Random();
        int count = 0;

        do {
            int colonne = random.nextInt(7);
            int ligne = 0;
            while (ligne < board.getJetons().length && !checkJetonPosiotion(ligne, colonne)) {
                // ystem.out.println(ligne);
                ligne++;
            }
            System.out.println(ligne);
            if (setJeton(ligne, colonne)) {
                return ligne + "," + colonne + "," + ((color.equals(Color.RED)) ? "RED" : "YELLOW");
            }
        } while (++count < NB_ESSAIS);

        return "Fin";

    }
}

