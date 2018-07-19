package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import javafx.scene.paint.Color;

public class JoueurAIAlgo extends Joueur {
    public JoueurAIAlgo(Color color, Board board) {
        super(color, board);
    }

    @Override
    public String entrerPosition() {
        return "Fin";
    }
}
