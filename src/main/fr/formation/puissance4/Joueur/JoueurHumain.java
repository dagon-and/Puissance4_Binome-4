package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import javafx.scene.paint.Color;

import java.util.Scanner;

public class JoueurHumain extends Joueur {
    private final int NB_ESSAIS = 3;

    public JoueurHumain(Color color, Board board) {
        super(color, board);
    }

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

    @Override
    public String envoyer() {
        // Messages :
        // - "Fin" termine le jeu
        // - "{ligne},{colonne},{color}" envoie le choix à adversaire, ex., "4,4,RED" ou "1,4,YELLOW"

        if (isFinish()) {
            return "Fin";
        }
        return entrerPosition();
    }

    @Override
    public void recevoir(String messageRecu) {
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
