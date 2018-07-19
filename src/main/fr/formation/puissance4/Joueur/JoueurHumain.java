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
        String inPosition;
        int i = 0;
        System.out.println("Veuillez entrer une position (ex., 'ligne,colonne') :");
        do {
            inPosition = scanner.nextLine();
            if (inPosition.matches("^\\d+,\\d+$")) {
                String[] position = inPosition.split(",");
                int ligne=Integer.parseInt(position[0]);
                int colonne=Integer.parseInt(position[1]);
                if (setJeton(ligne,colonne)){
                    return inPosition + "," + ((color.equals(Color.RED))? "RED": "YELLOW");
                }
            }
            System.out.println("(" + (++i) + ") Veuillez choisir une position correcte :");
        } while (i < NB_ESSAIS);

        return "Fin";
    }
}
