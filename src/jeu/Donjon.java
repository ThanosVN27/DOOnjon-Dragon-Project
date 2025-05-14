package jeu;

import personnage.Monstre;
import personnage.Personnage;

import java.util.Random;

public class Donjon {
    private int hauteur;
    private int largeur;
    private String[][] carte;

    public Donjon(int hauteur, int largeur) {
        if (largeur < 15 || largeur > 25 || hauteur < 15 || hauteur > 25) {
            throw new IllegalArgumentException("Les dimensions de la carte doivent être comprises entre 15 et 25 cases.");
        }
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.carte = new String[hauteur][largeur];
        creerCarte();
    }

    private void creerCarte() {
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                carte[i][j] = "."; // Empty space
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
        String[][] carteAffichage = new String[hauteur][largeur];
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                carteAffichage[i][j] = carte[i][j];
            }
        }
        return carteAffichage;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
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
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                System.out.print(carte[i][j] + " ");
            }
            System.out.println();
        }
    }
}