package jeu;

import equipements.Equipement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import personnage.Joueur;
import personnage.Monstre;

public class Donjon {
    private final int hauteur;
    private final int largeur;
    private final String[][] carte;
    private List<Monstre> monstres;
    private List<Joueur> joueurs;
    private final List<Equipement> equipementsListe;

    public Donjon(int hauteur, int largeur) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.carte = new String[hauteur][largeur];
        this.equipementsListe = new ArrayList<>();
        this.monstres = new ArrayList<>();
        this.joueurs = new ArrayList<>();
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
            System.arraycopy(carte[i], 0, carteAffichage[i], 0, carte.length);
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

    public List<Equipement> getEquipementsListe() {
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
                this.monstres.add(monstre);
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
                monstres.add(monstre);
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
                carte[x][y] = "J" + numeroJoueur;
                joueur.setX(x);
                joueur.setY(y);
                this.joueurs.add(joueur);
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
        System.out.println();
        System.out.println("--------------------- Carte du Donjon --------------------");

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                System.out.print(String.format("%3s", carte[i][j]));
            }
            System.out.println();
        }
        System.out.println("---------------------------------------------------------");
    }

    public List<Joueur> ordreJeuJoueur() {
        List<Joueur> listeOrdre = new ArrayList<>(joueurs);
        for(Joueur c : listeOrdre) {
            int initiative = lancerDes() + c.getInitiative();
            c.setInitiative(initiative);
        }
        listeOrdre.sort((c1, c2) -> Integer.compare(c2.getInitiative(), c1.getInitiative()));
        return listeOrdre;
    }

    public List<Monstre> ordreJeuMonstre() {
        List<Monstre> listeOrdre = new ArrayList<>(monstres);
        for(Monstre c : listeOrdre) {
            int initiative = lancerDes() + c.getInitiative();
            c.setInitiative(initiative);
        }
        listeOrdre.sort((c1, c2) -> Integer.compare(c2.getInitiative(), c1.getInitiative()));
        return listeOrdre;
    }

    public int lancerDes() {
        return (int) (Math.random() * 20) + 1;
    }







}