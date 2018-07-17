package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import javafx.scene.paint.Color;

public abstract class Joueur {
    protected Color color;
    protected Board board;

    public Joueur(Color color, Board board) {
        this.color = color;
        this.board = board;
    }

    public boolean checkJetonPosiotion(int ligne, int colonne) {
        // vérification si la place est libre : not(expr)
        if (!board.getJetons()[ligne][colonne].getColor().equals(Color.TRANSPARENT))
            return false;

        // vérifier si c'est le fond ou s'il y a un jeton en desous : not(expr1 ou expr2) = not(expr1) && not(expr2)
        if (colonne + 1 != board.getJetons()[ligne].length && !board.getJetons()[ligne][colonne + 1].getColor().equals(Color.TRANSPARENT))
            return false;

        return true;
    }

    public abstract String envoyer();

    public abstract void recevoir(String messageRecu);
}
