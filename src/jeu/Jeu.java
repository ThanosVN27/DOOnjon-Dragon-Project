package jeu;

import java.util.ArrayList;
import java.util.List;
import personnage.Joueur;
import personnage.MaitreDuJeu;
import personnage.Monstre;
import java.util.Scanner;


public class Jeu {
    private final MaitreDuJeu maitreDuJeu;
    private List<Joueur> listeJoueurs;
    private List<Monstre> listeMonstres;
    private List<Object> listeEntite;
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
    }

    public void jouerTour() {
        System.out.println("------------------Tour " + (tour + 1) + "-----------------");
        tour++;
        for (Object entite : listeEntite) {
            if (entite instanceof Joueur) {
                Joueur joueur = (Joueur) entite;
                if (joueur.getPointsDeVie() > 0) {
                    System.out.println(joueur.getNom() + " joue son tour.");
                } else {
                    System.out.println(joueur.getNom() + " est mort et ne peut pas jouer.");
                }
            } else if (entite instanceof Monstre) {
                Monstre monstre = (Monstre) entite;
                if (monstre.getPointsDeVie() > 0) {
                    System.out.println(monstre.getNom() + " joue son tour.");
                    
                } else {
                    System.out.println(monstre.getNom() + " est mort et ne peut pas jouer.");
                }
            }
        }

    }

    public void afficherOrdreDeJeu() {
        System.out.println("---------------- Ordre de Jeu ----------------");

        // Récupération des entités
        listeJoueurs = maitreDuJeu.getDonjon().ordreJeuJoueur();
        listeMonstres = maitreDuJeu.getDonjon().ordreJeuMonstre();

        // Fusion des deux listes
        listeEntite.clear(); // important pour éviter des doublons si la méthode est appelée plusieurs fois
        listeEntite.addAll(listeJoueurs);
        listeEntite.addAll(listeMonstres);

        // Tri unique
        listeEntite.sort((e1, e2) -> {
            int i1 = (e1 instanceof Joueur) ? ((Joueur) e1).getInitiative() : ((Monstre) e1).getInitiative();
            int i2 = (e2 instanceof Joueur) ? ((Joueur) e2).getInitiative() : ((Monstre) e2).getInitiative();
            return Integer.compare(i2, i1); // ordre décroissant

            // Si égalité d'initiative et types différents, on peut définir une priorité
            // Mais ici, on suppose que les initiatives sont suffisantes
        });

        // Affichage
        for (Object entite : listeEntite) {
            if (entite instanceof Joueur) {
                Joueur joueur = (Joueur) entite;
                System.out.println(joueur.getNom() + " - Initiative: " + joueur.getInitiative());
            } else if (entite instanceof Monstre) {
                Monstre monstre = (Monstre) entite;
                System.out.println(monstre.getNom() + " - Initiative: " + monstre.getInitiative());
            }
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

    public int optionsJouer() {

    }
}