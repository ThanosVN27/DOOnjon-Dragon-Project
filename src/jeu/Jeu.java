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
        tour++;
        for (Personnage entite : listeEntite) {
            if (entite instanceof Joueur) {
                Joueur joueur = (Joueur) entite;
                actionsJoueur(joueur);
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
        System.out.println();
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

    public void actionsJoueur(Joueur joueur) {
        for(Personnage entite : listeEntite) {
            entite.jouerTour(maitreDuJeu);
        }

    }
}