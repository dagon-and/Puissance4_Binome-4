package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import javafx.scene.paint.Color;

public abstract class Joueur {
    protected Color color;
    protected Board board;
    protected boolean isMsgError;
    protected String strMsg;
    protected boolean isDefaite;

    public Joueur(Color color, Board board) {
        this.color = color;
        this.board = board;
        this.isMsgError = false;
        this.strMsg = "";
        this.isDefaite = false;
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
        if (checkJetonPosiotion(ligne, colonne)) {
            board.getJetons()[ligne][colonne].setColor(color);
            return true;
        }
        return false;
    }

    public void setJetonAdversaire(int ligne, int colonne, Color adversaireColor) {
        if (checkJetonPosiotion(ligne, colonne)) {
            board.getJetons()[ligne][colonne].setColor(adversaireColor);
        } else {
            isMsgError = true;
        }
    }

    public boolean checkDefaite(int ligne, int colonne) {
        Color adversaireColor = board.getJetons()[ligne][colonne].getColor();
        int count;

        // vérification des couleur sur la verticale
        if (board.getJetons().length - ligne > 3) { // sur la verticale il doit être au moins 4 jetons
            count = 0;
            for (int i = 1; ligne + i < board.getJetons().length &&
                    board.getJetons()[ligne + i][colonne].getColor().equals(adversaireColor); i++)
                if ((++count) > 2)
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

        // vérification des couleur   deux diagonales

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
        if (isMsgError) {
            System.out.println("Erreur dans le message reçu > " + strMsg);
            return true;
        } else if (isDefaite) {
            System.out.println("Vous avez perdu");
            return true;
        } else if (board.isFullBoard()) {
            System.out.println("Table de jeu est remplie");
            return true;
        }
        return false;
    }

    public abstract String entrerPosition();

    public String envoyer() {
        // Messages :
        // - "Fin" termine le jeu
        // - "{ligne},{colonne},{color}" envoie le choix à adversaire, ex., "4,4,RED" ou "1,4,YELLOW"

        if (isFinish()) {
            return "Fin";
        }
        return entrerPosition();
    }

    public void recevoir(String messageRecu) {
        strMsg = messageRecu;

        if (!messageRecu.matches("^\\d+,\\d+,(RED|YELLOW)$")) {
            isMsgError = true;
            return;
        }

        String[] strings = messageRecu.split(",");
        int ligne = Integer.parseInt(strings[0]);
        int colonne = Integer.parseInt(strings[1]);
        Color adversaireColor = Color.valueOf(strings[2]);

        if (adversaireColor.equals(color)) {
            isMsgError = true;
            return;
        }

        setJetonAdversaire(ligne, colonne, adversaireColor);
        checkDefaite(ligne, colonne);
    }
}
