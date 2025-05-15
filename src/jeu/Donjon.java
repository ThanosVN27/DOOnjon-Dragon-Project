package jeu;

import personnage.Monstre;
import personnage.Personnage;

import java.util.Random;

public class Donjon {
    private int hauteur;
    private int largeur;
    private String[][] carte;

    public Donjon(int hauteur, int largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.carte = new String[hauteur][largeur];
        creerCarte();
    }

    private void creerCarte() {
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                carte[0][0] = "0";
                if (i == 0 && j > 0) {
                    carte[i][j] = String.valueOf(j );
                } else if (j == 0 && i > 0) {
                    carte[i][j] = String.valueOf(i);
                } else {
                    carte[i][j] = ".";
                }
            }
        }
    }



    public void positionnerObstacles(int nombreObstacles) {
        Random random = new Random();
        for (int i = 0; i < nombreObstacles; i++) {
            int x = random.nextInt(hauteur);
            int y = random.nextInt(largeur);
            if (carte[x][y] == ".") {
                carte[x][y] = "#";
            } else {
                i--;
            }
        }
    }

    public String[][] getCarte() {
        String [][] carteAffichage = new String[hauteur][largeur];
        for (int i = 0; i < carte.length; i++) {
            for (int j = 0; j < carte.length; j++) {
                carteAffichage[i][j] = carte[i][j];
            }
        }
        return carteAffichage;
    }

    public void placerEquipement(Object equipement, int x, int y) {
        if (carte[x][y] == ".") {
            carte[x][y] = "E";
        } else {
            System.out.println("Impossible de positionner l'équipement ici. Case occupée.");
        }
    }


    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void placerMonstreAleatoirement(Monstre monstre) {
        Random random = new Random();
        boolean place = false;
        while (!place) {
            int x = random.nextInt(hauteur);
            int y = random.nextInt(largeur);
            if (carte[x][y] == ".") {
                carte[x][y] = "M";
                place = true;
            }
        }
    }

    public boolean placerJoueur(Personnage joueur, int x, int y) {
        if (x >= 0 && x < hauteur && y >= 0 && y < largeur) {
            if (carte[x][y] == ".") {
                carte[x][y] = "J";
                return true;
            } else {
                System.out.println("Impossible de positionner le joueur ici. Case occupée.");
            }
        } else {
            System.out.println("Position invalide.");
        }
        return false;
    }



    public void afficherCarte() {
        for (int i = 0; i < carte.length; i++) {
            for (int j = 0; j < carte.length; j++) {
                System.out.print(String.format("%3s", carte[i][j]));
            }
            System.out.println();
        }
    }

}