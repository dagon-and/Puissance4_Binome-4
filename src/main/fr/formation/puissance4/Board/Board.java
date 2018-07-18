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
        for (int ligne = 0; ligne < jetons.length; ligne++)
            for (int colonne = 0; colonne < jetons[ligne].length; colonne++)
                if (jetons[ligne][colonne].getColor().equals(Color.TRANSPARENT))
                    return false;
        return true;
    }
}
