package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import javafx.scene.paint.Color;

import java.util.Scanner;

public class JoueurHumain extends Joueur {
    private final int NB_ESSAIS = 10;

    public JoueurHumain(Color color, Board board) {
        super(color, board);
    }

    @Override
    public String entrerPosition() {
        Scanner scanner = new Scanner(System.in);
        String position;
        int i = 0;
        System.out.println("Veuillez entrer une colonne (1-7) :");
        do {
            position = scanner.nextLine();
            if (position.matches("^\\d+$")) {
                int ligne=-1;
                int colonne=Integer.parseInt(position)-1;
                while (!checkJetonPosiotion(++ligne, colonne)) ;
                if (setJeton(ligne,colonne)){

                    return ligne+","+colonne+","+((color.equals(Color.RED))? "RED": "YELLOW");
                }
            }
            System.out.println("(" + (++i) + ") Veuillez choisir une position correcte :");
        } while (i < NB_ESSAIS);

        return "Fin";
    }
}
