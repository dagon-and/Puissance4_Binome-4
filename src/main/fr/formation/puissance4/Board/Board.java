package fr.formation.puissance4.Board;

import fr.formation.puissance4.Piece.Jeton;
import javafx.scene.paint.Color;

public class Board {
    private Jeton[][] jetons;

    public Board(Jeton[][] jetons) {
        this.jetons = jetons;
    }

    public Jeton[][] getJetons() {
        return jetons;
    }

    public boolean isFullBoard() {
        for (int line = 0; line < jetons.length; line++) {
            for (int column = 0; column < jetons[line].length; column++) {
                if (jetons[line][column].getColor().equals(Color.TRANSPARENT))
                    return false;
            }
        }
        return true;
    }
}
