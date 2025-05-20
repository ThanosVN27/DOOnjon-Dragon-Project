package jeu;

import personnage.MaitreDuJeu;
import personnage.Monstre;
import personnage.Joueur;

import java.util.ArrayList;


public class Jeu {
    private final MaitreDuJeu maitreDuJeu;
    private ArrayList<Object> listeJoueurs;
    private int tour;

    public Jeu() {
        this.maitreDuJeu = new MaitreDuJeu();
        this.tour = 0;
        this.listeJoueurs = new ArrayList<>();
    }

    public void lancerJeu() {
        maitreDuJeu.miseEnPlace();
        AffichierOrdreDeJeu();


    }

    public void jouerTour() {
        System.out.println("------------------Tour " + (tour + 1) + "-----------------");


    }

    public void AffichierOrdreDeJeu() {;
        listeJoueurs = maitreDuJeu.getDonjon().ordreJeu();
        System.out.println("----------------OrdreDesPersonnages-------------------------");
        for(Object o : listeJoueurs){
            if (o instanceof Joueur) {
                System.out.println("- " +((Joueur) o).getNom() + " (" + ((Joueur) o).getRace().getNomRaces() + " " + ((Joueur) o).getClasse().getNomClasse() + " || PV : " + ((Joueur) o).getPointsDeVie() + " || Position : " + ((Joueur) o).getX() + "," + ((Joueur) o).getY() + ")");
            } else if (o instanceof Monstre) {
                System.out.println("- " + ((Monstre) o).getNom() + " (" + " PV : " + ((Monstre) o).getPointsDeVie() + " || Position : " + ((Monstre) o).getX() + "," + ((Monstre) o).getY() + ")");
            }
        } System.out.println("------------------------------------------------------------");


    }



    public void equiperPersonnage(Joueur joueur) {
        for(Object o : listeJoueurs){
            if(o instanceof Joueur) {
                System.out.println("Choisissez une arme à équiper :");
                ((Joueur) o).equiperInventaire();
            }
        }
    }

    public int optionTour(int choix) {
        boolean valide = false;
        while (valide) {
            switch (choix) {
                case 1:
                    System.out.println("Vous avez choisi de vous déplacer.");
                    break;
                case 2:
                    System.out.println("Vous avez choisi d'attaquer.");
                    break;
                case 3:
                    System.out.println("Vous avez choisi de fuir.");
                    break;
                case 4:
                    System.out.println("Vous avez choisi de fouiller.");
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }

        return choix;



    }




}