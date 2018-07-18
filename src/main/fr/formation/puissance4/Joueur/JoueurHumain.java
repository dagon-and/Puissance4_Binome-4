package fr.formation.puissance4.Joueur;

import fr.formation.puissance4.Board.Board;
import javafx.scene.paint.Color;

import java.util.Objects;
import java.util.Scanner;

public class JoueurHumain extends Joueur {
    private final int NB_ESSAIS = 3;

    public JoueurHumain(Color color, Board board) {
        super(color, board);
    }

    public String entrerPosition() {
        /*************************
         * à écrire le code :
         *  -> verifier le msg de scanner
         *  -> faire parseInt pour avoir ligne et colonne
         *  -> utiliser if( checkJetonPosiotion(ligne, colonne) )
         *  -> si les positions sont mauvaises alors redemander encore (utiliser boucle)
         */

        Scanner in = new Scanner(System.in);
        String strPosition;
        int i = 0;
        System.out.println("Veuillez choisir une position (ex., 'ligne,colonne'):");
        do {
            strPosition = in.nextLine();
            if (strPosition.matches("^\\d+,\\d+$")) {
                String[] intPosition = strPosition.split(",");
                int ligne = Integer.parseInt(intPosition[0]);
                int colonne = Integer.parseInt(intPosition[1]);
                if (setJeton(ligne, colonne))
                    return strPosition + "," + ((color.equals(Color.RED)) ? "RED" : "YELLOW");
            }
            System.out.println("(" + (++i) + ") Veuillez choisir une position correcte :");
        } while (i < NB_ESSAIS);

        return "Fin";
    }

    @Override
    public String envoyer() {// Messages :
        // - "Fin" termine le jeu
        // - "{ligne},{colonne},{color}" envoie le choix à adversaire, ex., "4,4,RED" ou "1,4,YELLOW"

        if (isFinish())
            return "Fin";
        return entrerPosition();
    }

    @Override
    public void recevoir(String messageRecu) {
        /*************************
         * à écrire le code pour le msg mal formaté pour terminer ce jeu
         */

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

