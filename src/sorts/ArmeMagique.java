package sorts;

import equipements.Arme;
import personnage.Joueur;
import personnage.Monstre;
import java.util.List;
import java.util.Scanner;

public class ArmeMagique implements Sort {
    @Override
    public String getNom() {
        return "Arme Magique";
    }

    @Override
    public void utiliserSort(Joueur lanceur, List<Joueur> personnages, List<Monstre> monstres) {
        Scanner scanner = new Scanner(System.in);

        // Choix du personnage
        System.out.println("Choisissez un personnage dont vous souhaitez améliorer une arme :");
        for (int i = 0; i < personnages.size(); i++) {
            System.out.println((i + 1) + ". " + personnages.get(i).getNom());
        }

        int choixPerso = scanner.nextInt() - 1;
        if (choixPerso < 0 || choixPerso >= personnages.size()) {
            System.out.println("❌ Choix invalide.");
            return;
        }

        Joueur cible = personnages.get(choixPerso);
        List<Arme> armes = cible.getArmes();

        if (armes.isEmpty()) {
            System.out.println("❌ Ce personnage ne possède aucune arme.");
            return;
        }

        //Choix de l'arme à améliorer
        System.out.println("Choisissez une arme à améliorer :");
        for (int i = 0; i < armes.size(); i++) {
            Arme a = armes.get(i);
            System.out.println((i + 1) + ". " + a.getNom());
        }

        int choixArme = scanner.nextInt() - 1;
        if (choixArme < 0 || choixArme >= armes.size()) {
            System.out.println("❌ Choix invalide.");
            return;
        }

        Arme armeCible = armes.get(choixArme);
        armeCible.ameliorer();

        System.out.println("✨ L'arme \"" + armeCible.getNom() + "\" a été enchantée avec succès !");
    }
}