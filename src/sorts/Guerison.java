package sorts;

import personnage.Joueur;
import personnage.Monstre;

import java.util.List;
import java.util.Scanner;

public class Guerison implements Sort {
    @Override
    public String getNom() {
        return "Guérison";
    }

    @Override
    public void utiliserSort(Joueur lanceur, List<Joueur> personnages, List<Monstre> monstres) {
        System.out.println("╠═══════════════════════ GUÉRISON ═══════════════════════════╣");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choisissez un personnage à soigner :");
        for (int i = 0; i < personnages.size(); i++) {
            System.out.println((i + 1) + " - " + personnages.get(i).getNom() + " (PV : " + personnages.get(i).getPointsDeVie() + ")");
        }

        int choix = scanner.nextInt() - 1;
        Joueur cible = personnages.get(choix);

        int soin = (int) (Math.random() * 10) + 1; // 1d10
        int pvMax = cible.getPointsDeVieMax();
        int nouveauxPV = Math.min(cible.getPointsDeVie() + soin, pvMax);

        cible.setPointsDeVie(nouveauxPV);

        System.out.println("✨ " + cible.getNom() + " a été soigné de " + soin + " points de vie (PV actuels : " + nouveauxPV + ").");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");
    }
}