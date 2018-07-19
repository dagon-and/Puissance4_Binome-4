package fr.formation.puissance4;

import fr.formation.puissance4.Joueur.*;
import fr.formation.puissance4.Socket.Client;
import fr.formation.puissance4.Socket.Serveur;
import fr.formation.puissance4.Board.Board;
import fr.formation.puissance4.Piece.Jeton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;
import java.util.Scanner;

public class Game extends Thread {
    private Jeton[][] jetons;
    Scanner scanner = new Scanner(System.in);

    public Game(List<Circle> circles) {
        this.jetons = new Jeton[6][7];
        int cpt = 0;
        for (int ligne = 0; ligne < this.jetons.length; ligne++) {
            for (int colonne = 0; colonne < this.jetons[ligne].length; colonne++) {
                this.jetons[ligne][colonne] = new Jeton(circles.get(cpt));
                cpt++;
            }
        }
    }

    public Joueur chooseJoueur(Color color) {
        System.out.println("Etes vous humain, AI Random ou AI Algo ?");
        System.out.println("1 - Humain | 2 - AI Random | 3 - AI Algo");
        switch (scanner.nextInt()) {
            case 2:
                return new JoueurAIRandom(color, new Board(jetons));
            case 3:
                return new JoueurAIAlgo(color, new Board(jetons));
            default:
                return new JoueurHumain(color, new Board(jetons));
        }
    }

    @Override
    public void run() {
        System.out.println("Etes vous serveur ou client ?");
        System.out.println("1 - Client | 2 - Serveur");
        if (scanner.nextInt() == 1)
            new Client(chooseJoueur(Color.YELLOW)).start();
        else
            new Serveur(chooseJoueur(Color.RED)).start();
    }
}