package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import javafx.scene.paint.Color;

import java.util.Random;

public class JoueurAIRandom extends Joueur {

    public JoueurAIRandom(Color color, Board board) {
        super(color, board);
    }


    @Override
    public String entrerPosition() {

        Random random = new Random();
        int colonne = random.nextInt(7);
        int ligne = -1;
        while (!checkJetonPosiotion(++ligne, colonne)) ;
        if (setJeton(ligne, colonne)) {
            String robotPosition;
            return ligne+","+colonne+","+((color.equals(Color.RED))? "RED": "YELLOW");
        }

        return "Fin";

    }
}

