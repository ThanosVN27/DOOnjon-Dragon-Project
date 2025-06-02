package jeu;

import java.util.ArrayList;
import java.util.List;
import personnage.Joueur;
import personnage.MaitreDuJeu;
import personnage.Monstre;


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
<<<<<<< HEAD

=======
                    
>>>>>>> 3d18a2db2e1fd16c1f41f5da3f1bfa3c83c55b57
                } else {
                    System.out.println(monstre.getNom() + " est mort et ne peut pas jouer.");
                }
            }
        }

    }

    public void afficherOrdreDeJeu() {
        System.out.println("---------------- Ordre de Jeu ----------------");
        listeJoueurs = maitreDuJeu.getDonjon().ordreJeuJoueur();
        listeMonstres = maitreDuJeu.getDonjon().ordreJeuMonstre();
        listeEntite.addAll(listeJoueurs);
        listeEntite.addAll(listeMonstres);
        listeEntite.sort((e1, e2) -> {
<<<<<<< HEAD
            if (e1 instanceof Joueur && e2 instanceof Joueur)
            {
                return Integer.compare(((Joueur) e2).getInitiative(), ((Joueur) e1).getInitiative());
            }
            else if (e1 instanceof Monstre && e2 instanceof Monstre)
            {
                return Integer.compare(((Monstre) e2).getInitiative(), ((Monstre) e1).getInitiative());
            }
            else if (e1 instanceof Joueur)
            {
                return -1;
            }
            else {
                return 1;
            }
=======
            if (e1 instanceof Joueur && e2 instanceof Joueur) 
            {
                return Integer.compare(((Joueur) e2).getInitiative(), ((Joueur) e1).getInitiative());
            } 
            else if (e1 instanceof Monstre && e2 instanceof Monstre) 
            {
                return Integer.compare(((Monstre) e2).getInitiative(), ((Monstre) e1).getInitiative());
            } 
            else if (e1 instanceof Joueur) 
            {
                return -1;
            } 
            else {
                return 1;
            }
>>>>>>> 3d18a2db2e1fd16c1f41f5da3f1bfa3c83c55b57
        });

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