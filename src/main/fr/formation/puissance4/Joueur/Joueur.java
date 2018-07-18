package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import javafx.scene.paint.Color;

public abstract class Joueur {
    protected Color color;
    protected Board board;
    protected boolean isDefaite;
    protected boolean isMsgError;

    public Joueur(Color color, Board board) {
        this.color = color;
        this.board = board;
        isDefaite = isMsgError = false;
    }

    public boolean checkJetonPosiotion(int ligne, int colonne) {
        // vérification si la place est en dehors de board
        if (ligne < 0 || ligne + 1 > board.getJetons().length || colonne < 0 || colonne + 1 > board.getJetons()[ligne].length)
            return false;

        // vérification si la place est libre
        if (!board.getJetons()[ligne][colonne].getColor().equals(Color.TRANSPARENT))
            return false;

        // vérification si c'est le fond ou s'il y a un jeton en dessous
        if (ligne + 1 == board.getJetons().length)
            return true;
        if (board.getJetons()[ligne + 1][colonne].getColor().equals(Color.TRANSPARENT))
            return false;

        return true;
    }

    public boolean setJeton(int ligne, int colonne) {
        if (!checkJetonPosiotion(ligne, colonne))
            return false;
        board.getJetons()[ligne][colonne].setColor(color);
        return true;
    }

    public void setJetonAdversaire(int ligne, int colonne, Color adversaireColor) {
        if (!checkJetonPosiotion(ligne, colonne)) {
            isMsgError = true;
            return;
        }
        board.getJetons()[ligne][colonne].setColor(adversaireColor);
    }

    public boolean checkDefaite(int ligne, int colonne) {
        Color adversaireColor = board.getJetons()[ligne][colonne].getColor();
        int count;

        // vérification des couleur sur la verticale
        if (board.getJetons().length - ligne > 3) { // sur la verticale il doit être au moins 4 jetons
            count = 0;
            for (int i = 1; ligne + i < board.getJetons().length &&
                    board.getJetons()[ligne + i][colonne].getColor().equals(adversaireColor); i++)
                if (++count > 2)
                    return isDefaite = true;
        }

        // vérification des couleur sur la horizontale
        count = 0;
        // - vérification à droite
        for (int i = 1; colonne + i < board.getJetons()[ligne].length &&
                board.getJetons()[ligne][colonne + i].getColor().equals(adversaireColor); i++)
            if (++count > 2)
                return isDefaite = true;
        // - vérification à gauche
        for (int i = 1; 0 < colonne - i &&
                board.getJetons()[ligne][colonne - i].getColor().equals(adversaireColor); i++)
            if (++count > 2)
                return isDefaite = true;

        // vérification des couleur sur deux diagonales

        // - diagonale avec la pente positive à droite et à gauche
        count = 0;
        for (int i = 1; ligne + i < board.getJetons().length && colonne + i < board.getJetons()[ligne].length &&
                board.getJetons()[ligne + i][colonne + i].getColor().equals(adversaireColor); i++)
            if (++count > 2)
                return isDefaite = true;
        for (int i = 1; 0 < ligne - i && 0 < colonne - i &&
                board.getJetons()[ligne - i][colonne - i].getColor().equals(adversaireColor); i++)
            if (++count > 2)
                return isDefaite = true;

        // - diagonale avec la pente négative à droite et à gauche
        count = 0;
        for (int i = 1; 0 < ligne - i && colonne + i < board.getJetons()[ligne].length &&
                board.getJetons()[ligne - i][colonne + i].getColor().equals(adversaireColor); i++)
            if (++count > 2)
                return isDefaite = true;
        for (int i = 1; ligne + i < board.getJetons().length && 0 < colonne - i &&
                board.getJetons()[ligne + i][colonne - i].getColor().equals(adversaireColor); i++)
            if (++count > 2)
                return isDefaite = true;

        return isDefaite;
    }

    public boolean isFinish() {
        // vérification :
        // - s'il une erreur dans le message reçu
        // - si l'adversaire a gagné
        // - si le board est complètement rempli
        if (isMsgError || isDefaite || board.isFullBoard())
            return true;

        return false;
    }

    public abstract String envoyer();

    public abstract void recevoir(String messageRecu);
}
