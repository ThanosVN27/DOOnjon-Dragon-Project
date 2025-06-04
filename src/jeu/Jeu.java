package jeu;

import java.util.ArrayList;
import java.util.List;

import personnage.MaitreDuJeu;
import personnage.Monstre;
import personnage.Personnage;
import personnage.Joueur;

public class Jeu {
    private int tour;
    private MaitreDuJeu maitreDuJeu;
    private List<Monstre> listeMonstres;
    private List<Joueur> listeJoueurs;
    private List<Personnage> listeEntite;


    public Jeu() {
        this.maitreDuJeu = new MaitreDuJeu();
        this.tour = 0;
        this.listeJoueurs = new ArrayList<>();
        this.listeMonstres = new ArrayList<>();
        this.listeEntite = new ArrayList<>();
    }

    public void lancerJeu() {
        maitreDuJeu.miseEnPlace();
        boucleJeu();
    }

    public void jouerTour() {
        Delai.attendre();
        System.out.println("\n╠════════════════════════════ TOUR " + (tour + 1) + " ════════════════════════════════╣");
        for (Personnage entite : listeEntite) {
            if(entite.estMort()) {
                System.out.println(entite.getNom() + " est mort et ne peut pas jouer ce tour.");
                entite.mourir(maitreDuJeu.getDonjon());
                continue;
            }
            entite.jouerTour(maitreDuJeu.getDonjon());
        }
        tour++;
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");

    }

    public void afficherOrdreDeJeu() {
        System.out.println();
        System.out.println("╠══════════════════════════ ORDRE DU JEU ═══════════════════════════╣");


        listeJoueurs = maitreDuJeu.getDonjon().getJoueurs();
        listeMonstres = maitreDuJeu.getDonjon().getMonstres();

        listeEntite.clear();
        listeEntite.addAll(listeJoueurs);
        listeEntite.addAll(listeMonstres);


        // Trier la liste par initiative décroissante
        listeEntite.sort((p1, p2) -> Integer.compare(p2.getInitiative(), p1.getInitiative()));

        // Affichage de l'ordre de jeu trié
        for (Personnage entite : listeEntite) {
            System.out.println(entite.getNom() + " (Initiative: " + entite.getInitiative() + ")");
        }

        System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
    }



    public void boucleJeu() {
        boolean finPartie = false;

        while (!finPartie) {
            afficherOrdreDeJeu();
            jouerTour();
            finPartie = estFinDonjon();
        }

        System.out.println("🎉 Partie terminée !");
    }

    private boolean estFinDonjon() {
        listeJoueurs = maitreDuJeu.getDonjon().getJoueurs();
        listeMonstres = maitreDuJeu.getDonjon().getMonstres();

        boolean joueurMort = listeJoueurs.stream().anyMatch(j -> j.getPointsDeVie() <= 0);
        boolean tousMonstresMorts = listeMonstres.stream().allMatch(m -> m.getPointsDeVie() <= 0);

        if (joueurMort) {
            System.out.println("💀 Un joueur est mort. Défaite !");
            return true;
        }

        if (tousMonstresMorts) {
            System.out.println("🏆 Tous les monstres ont été vaincus. Victoire !");
            restaurerVieJoueurs();
            passerAuDonjonSuivant();
            return true;
        }

        return false;
    }

    private void restaurerVieJoueurs() {
        for (Joueur joueur : listeJoueurs) {
            {
                joueur.restaurerVie();
            }
            System.out.println("Tous les joueurs ont été soignés !");
        }
    }



    private void passerAuDonjonSuivant() {
        maitreDuJeu.passerAuDonjon();
        System.out.println("Passage au donjon suivant...");
        listeMonstres.clear();
        tour = 0;
        boucleJeu();
    }


}