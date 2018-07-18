package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import javafx.scene.paint.Color;

public abstract class Joueur {
    protected Color color;
    protected Board board;
    protected boolean isMsgError;
    protected boolean isDefaite;


    public Joueur(Color color, Board board) {
        this.color = color;
        this.board = board;
        this.isMsgError = false;
        this.isDefaite = false;
    }

    public boolean checkJetonPosiotion(int ligne, int colonne) {
        // vérification si la place est en dehors de board
        if (ligne < 0 || ligne + 1 > board.getJetons().length || colonne < 0 || colonne + 1 > board.getJetons()[ligne].length)
            return false;

        // vérification si la place est libre : not(expr)
        if (!board.getJetons()[ligne][colonne].getColor().equals(Color.TRANSPARENT))
            return false;

        // vérification si c'est le fond ou s'il y a un jeton en dessous : not(expr1 ou expr2) = not(expr1) && not(expr2)
        if (colonne + 1 != board.getJetons()[ligne].length && !board.getJetons()[ligne][colonne + 1].getColor().equals(Color.TRANSPARENT))
            return false;

        return true;
    }

    public boolean setJeton(int ligne, int colonne) {
        if (checkJetonPosiotion(ligne, colonne)) {
            board.getJetons()[ligne][colonne].setColor(color);
            return true;
        }
        return false;

    }

    public void setJetonAdversaire(int ligne, int colonne, Color adversaireColor) {
        if (checkJetonPosiotion(ligne, colonne)) {
            board.getJetons()[ligne][colonne].setColor(color);

        } else {
            isMsgError = true;
        }


    }

    public boolean isFinish() {
        // vérification :
        // - s'il une erreur dans le message reçu
        // - si l'adversaire a gagné
        // - si le board est complètement rempli
        if (isMsgError || isDefaite || board.isFullBoard()) {
            return true;
        }
        return false;
    }

    public abstract String envoyer();

    public abstract void recevoir(String messageRecu);
}
