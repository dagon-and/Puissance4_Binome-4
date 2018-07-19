package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import javafx.scene.paint.Color;

public class JoueurAIRandom extends Joueur {

    public JoueurAIRandom(Color color, Board board) {
        super(color, board);
    }

    @Override
    public String entrerPosition() {
        return "Fin";
    }
}
