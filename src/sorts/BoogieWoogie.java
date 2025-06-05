package sorts;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import personnage.Joueur;
import personnage.Monstre;
import personnage.Personnage;

public class BoogieWoogie implements Sort {
    @Override
    public String getNom() {
        return "Boogie Woogie";
    }

    @Override
    public void utiliserSort(Joueur lanceur, List<Joueur> personnages, List<Monstre> monstres) {
        System.out.println("╠═══════════════════════ BOOGIE WOOGIE ═══════════════════════════╣");
        Scanner scanner = new Scanner(System.in);
        List<Personnage> toutes = new ArrayList<>();
        toutes.addAll(personnages);
        toutes.addAll(monstres);

        if (toutes.size() < 2) {
            System.out.println("❌ Pas assez d'entités pour échanger leurs positions.");
            return;
        }

        System.out.println("🔄 Choisissez deux entités à échanger :");
        for (int i = 0; i < toutes.size(); i++) {
            Personnage entite = toutes.get(i);
            System.out.printf("%d - %s (position : %d, %d)%n", i + 1, entite.getNom(), entite.getX(), entite.getY());
        }

        int choix1 = -1, choix2 = -1;

        try {
            System.out.print("Entité 1 : ");
            choix1 = scanner.nextInt() - 1;

            System.out.print("Entité 2 : ");
            choix2 = scanner.nextInt() - 1;

            if (choix1 < 0 || choix1 >= toutes.size() || choix2 < 0 || choix2 >= toutes.size() || choix1 == choix2) {
                System.out.println("❌ Choix invalides ou identiques.");
                return;
            }

            Personnage e1 = toutes.get(choix1);
            Personnage e2 = toutes.get(choix2);

            int tempX = e1.getX(), tempY = e1.getY();
            e1.setX(e2.getX());
            e1.setY(e2.getY());
            e2.setX(tempX);
            e2.setY(tempY);

            System.out.println("✨ " + e1.getNom() + " et " + e2.getNom() + " ont échangé leurs positions !");
            System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
        } catch (Exception e) {
            System.out.println("❌ Entrée invalide. Veuillez entrer des numéros valides.");
            scanner.nextLine();
        }
    }

}

