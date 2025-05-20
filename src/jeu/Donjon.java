package jeu;

import equipements.Equipement;
import personnage.Monstre;
import personnage.Joueur;

import java.util.ArrayList;
import java.util.Random;

public class Donjon {
    private int hauteur;
    private int largeur;
    private String[][] carte;
    private ArrayList<Monstre> monstresListe;
    private ArrayList<Joueur> joueursListe;
    private ArrayList<Equipement> equipementsListe;

    public Donjon(int hauteur, int largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.carte = new String[hauteur][largeur];
        this.monstresListe = new ArrayList<>();
        this.joueursListe = new ArrayList<>();
        this.equipementsListe = new ArrayList<>();
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
            if (carte[x][y].equals(".")) {
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

    public boolean placerEquipement(Equipement equipement, int x, int y) {
        if (carte[x][y].equals(".")) {
            carte[x][y] = "E";
            equipementsListe.add(equipement);
            return true;
        } else {

            System.out.println("Impossible de positionner l'équipement ici. Case occupée.");
            return false;
        }
    }

    public ArrayList<Monstre> getMonstresListe() {
        return monstresListe;
    }

    public ArrayList<Joueur> getJoueursListe() {
        return joueursListe;
    }

    public ArrayList<Equipement> getEquipementsListe() {
        return equipementsListe;
    }


    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void placerMonstreAleatoirement(Monstre monstre,int numero) {
        Random random = new Random();
        boolean place = false;
        while (!place) {
            int x = random.nextInt(hauteur);
            int y = random.nextInt(largeur);
            if (carte[x][y].equals(".")) {
                carte[x][y] = "M" + numero;
                monstre.setX(x);
                monstre.setY(y);
                this.monstresListe.add(monstre);
                place = true;
            }

        }
    }

    public void placerMonstre(Monstre monstre,int x, int y, int numero) {
        if (x >= 0 && x < hauteur && y >= 0 && y < largeur) {
            if (carte[x][y].equals(".")) {
                carte[x][y] = "M" + numero;
                monstre.setX(x);
                monstre.setY(y);
                this.monstresListe.add(monstre);
            } else {
                System.out.println("Impossible de positionner le monstre ici. Case occupée.");
            }
        } else {
            System.out.println("Position invalide.");
        }
    }

    public boolean placerJoueur(String nom, int x, int y,int numeroJoueur) {
        if (x >= 0 && x < hauteur && y >= 0 && y < largeur) {
            if (carte[x][y].equals(".")) {
                Joueur joueur = new Joueur(nom);
                joueur.equiperInventaire();
                joueur.toString();
                carte[x][y] = "J" + numeroJoueur;
                joueursListe.add(joueur);
                joueur.setX(x);
                joueur.setY(y);
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
                System.out.print(String.format("%3s", carte[i][j]));
            }
            System.out.println();
        }
    }

    public ArrayList<Object> ordreJeu() {
        ArrayList<Object> tousLesPersonnages = new ArrayList<>();

        for (Joueur joueur : joueursListe) {
            joueur.setInitiative(lancerDes());
        }

        for (Monstre monstre : monstresListe) {
            monstre.setInitiative(lancerDes());
        }

        tousLesPersonnages.addAll(joueursListe);
        tousLesPersonnages.addAll(monstresListe);

        tousLesPersonnages.sort((a, b) -> {
            int initiativeA = (a instanceof Joueur) ? ((Joueur) a).getInitiative() : ((Monstre) a).getInitiative();
            int initiativeB = (b instanceof Joueur) ? ((Joueur) b).getInitiative() : ((Monstre) b).getInitiative();
            return Integer.compare(initiativeB, initiativeA);
        });

        return tousLesPersonnages;
    }

    public int lancerDes() {
        return (int) (Math.random() * 20) + 1;
    }







}