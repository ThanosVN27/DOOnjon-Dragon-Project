package jeu;

import java.util.ArrayList;
import java.util.List;

import personnage.MaitreDuJeu;
import personnage.Monstre;
import personnage.Personnage;
import personnage.Joueur;

public class Jeu {
    private final MaitreDuJeu maitreDuJeu;
    private List<Joueur> listeJoueurs;
    private List<Monstre> listeMonstres;
    private List<Personnage> listeEntite;
    private int tour;

    public Jeu() {
        this.maitreDuJeu = new MaitreDuJeu();
        this.tour = 0;
        listeJoueurs = new ArrayList<>();
        listeMonstres = new ArrayList<>();
        listeEntite = new ArrayList<>();
    }

    public void lancerJeu() {
        maitreDuJeu.miseEnPlace();
        afficherOrdreDeJeu();
        jouerTour();
    }

    public void jouerTour() {
        System.out.println("------------------Tour " + (tour + 1) + "-----------------");
        for (Personnage entite : listeEntite) {
            entite.jouerTour(maitreDuJeu.getDonjon());
        }
        tour++;

    }

    public void afficherOrdreDeJeu() {
        System.out.println();
        System.out.println("---------------- Ordre de Jeu ----------------");

        // Récupération des entités
        listeJoueurs = maitreDuJeu.getDonjon().ordreJeuJoueur();
        listeMonstres = maitreDuJeu.getDonjon().ordreJeuMonstre();

        // Fusion des deux listes
        listeEntite.clear();
        listeEntite.addAll(listeJoueurs);
        listeEntite.addAll(listeMonstres);

        // Tri unique
        listeEntite.sort((e1, e2) -> {
            if (e1.getInitiative() != e2.getInitiative()) {
                return Integer.compare(e2.getInitiative(), e1.getInitiative());
            } else {
                return e1.getNom().compareTo(e2.getNom());
            }

        });

        // Affichage
        for(Personnage entite : listeEntite) {
            System.out.println(entite.getNom() + " (Initiative: " + entite.getInitiative() + ")");
        }

        System.out.println("------------------------------------------------");
    }


    public void boucleJeu() {
        boolean fin = false;

        while (!fin) {
            jouerTour();
            fin = true;
        }
        System.out.println("🎉 Partie terminée !");
    }


}