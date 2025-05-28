package jeu;

import personnage.Combattant;
import personnage.MaitreDuJeu;
import personnage.Monstre;
import personnage.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingFormatArgumentException;


public class Jeu {
    private final MaitreDuJeu maitreDuJeu;
    private List<Combattant> listeJoueurs;
    private int tour;

    public Jeu() {
        this.maitreDuJeu = new MaitreDuJeu();
        this.tour = 0;
        listeJoueurs = new ArrayList<>();
    }

    public void lancerJeu() {
        maitreDuJeu.miseEnPlace();
        afficherOrdreDeJeu();


    }

    public void jouerTour() {
        System.out.println("------------------Tour " + (tour + 1) + "-----------------");





    }




    public void afficherOrdreDeJeu() {
        System.out.println("---------------- Ordre de Jeu ----------------");
        listeJoueurs = maitreDuJeu.getDonjon().ordreJeu();
        for (Combattant c : listeJoueurs) {
            if (c.estJoueur()) {
                System.out.println("👤 Joueur  : " + c.afficherInfos() + " | Initiative : " + c.getInitiative());
            } else {
                System.out.println("👾 Monstre : " + c.afficherInfos() + " | Initiative : " + c.getInitiative());
            }
        }

        System.out.println("------------------------------------------------");
    }

    public void boucleJeu() {

    }





    public void joueurTour() {
        System.out.println("C'est au tour du joueur " + listeJoueurs.get(tour).getNom() + " de jouer.");

        int optionRestant = 3;
        for(Combattant c : listeJoueurs) {
            if (c.estJoueur()) {
                System.out.println("C'est au tour du joueur " + c.getNom() + " de jouer.");
            }
            while (optionRestant > 0) {
                System.out.println("Options restantes : " + optionRestant);
                System.out.println("1. Attaquer un monstre");
                System.out.println("2. Se déplacer");
                System.out.println("3. Passer le tour");
                System.out.print("Choisissez une option (1-3) : ");
                int choix;
                System.out.println("Choisissez une option (1-3) : ");


        }



        }





    }




}