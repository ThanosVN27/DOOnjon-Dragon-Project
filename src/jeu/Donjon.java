package jeu;

import equipements.Equipement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import personnage.Joueur;
import personnage.Monstre;

public class Donjon {
    private final int hauteur;
    private final int largeur;
    private final String[][] carte;
    private List<Monstre> monstres;
    private List<Joueur> joueurs;
    private List<Equipement> equipementsListe;

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
        carte[0][0] = " "; // case vide en haut à gauche
        for (int i = 1; i < hauteur; i++) {
            carte[i][0] = String.valueOf(i); // première colonne = index ligne
        }
        for (int j = 1; j < largeur; j++) {
            carte[0][j] = String.valueOf(j); // première ligne = index colonne
        }
        for (int i = 1; i < hauteur; i++) {
            for (int j = 1; j < largeur; j++) {
                carte[i][j] = "."; // reste de la carte
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
            equipement.setPositionX(x);
            equipement.setPositionY(y);
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

    public boolean placerJoueur(Joueur joueur, int x, int y,int numeroJoueur) {
        if (x >= 0 && x < hauteur && y >= 0 && y < largeur) {
            if (carte[x][y].equals(".")) {
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
        afficherTitreCarte();

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                System.out.print(String.format("%4s", carte[i][j]));
            }
            System.out.println();
        }

        afficherLigneBasCarte();
    }

    // Affiche le titre centré en fonction de la largeur de la carte
    private void afficherTitreCarte() {
        String titre = " Carte du Donjon ";
        int largeurTotale = largeur * 4; // 4 caractères par case
        int pad = (largeurTotale - titre.length()) / 2;

        String ligne = "─".repeat(Math.max(0, pad)) + titre + "─".repeat(Math.max(0, largeurTotale - pad - titre.length()));
        System.out.println(ligne);
    }

    // Affiche une ligne de fin pour fermer la carte proprement
    private void afficherLigneBasCarte() {
        int largeurTotale = largeur * 4;
        System.out.println("─".repeat(largeurTotale));
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

    public  List<Monstre> getMonstres() {
        return monstres;
    }

    public List<Joueur> getJoueurs() {
        return joueurs;
    }

    public boolean mettreAPositionJoueur(Joueur joueur, int x, int y) {
        if (x > 0 && x < hauteur && y > 0 && y < largeur) {
            if (carte[x][y].equals(".") || carte[x][y].equals("E")) {
                int ancienX = joueur.getX();
                int ancienY = joueur.getY();
                if (ancienX > 0 && ancienY > 0) {
                    carte[ancienX][ancienY] = ".";

                }

                joueur.seDeplacer(x, y);
                int nouveauX = joueur.getX();
                int nouveauY = joueur.getY();
                int numeroJoueur = joueurs.indexOf(joueur) + 1;
                carte[nouveauX][nouveauY] = "J" + numeroJoueur;
                mettreAJourEquipement();
                afficherCarte();

                return true;
            } else {
                joueur.seDeplacer(x, y);
                System.out.println("Impossible de déplacer le joueur ici. Case occupée.");
            }
        } else {
            System.out.println("Position invalide.");
        }
        return false;
    }


    public void mettreAPositionMonstre(Monstre monstre, int x, int y) {
        if (x >= 0 && x < hauteur && y >= 0 && y < largeur) {
            if (carte[x][y].equals(".")) {
                carte[monstre.getX()][monstre.getY()] = ".";
                int numeroMonstre = monstres.indexOf(monstre) + 1;
                carte[x][y] = "M" + numeroMonstre;
                monstre.setX(x);
                monstre.setY(y);
            } else {
                System.out.println("Impossible de déplacer le monstre ici. Case occupée.");
            }
        } else {
            System.out.println("Position invalide.");
        }
    }

    public void mettreAJourEquipement() {
        for(Equipement equipement : equipementsListe) {
            System.out.println("Mise à jour de l'équipement : " + equipement.getNom());
            int x = equipement.getPositionX();
            int y = equipement.getPositionY();
            if (carte[x][y].equals(".")) {
                carte[x][y] = "E";
            }
        }

    }

    public Equipement getEquipementAtPosition(int x, int y) {
        for (Equipement equipement : equipementsListe) {
            if (equipement.getPositionX() == x && equipement.getPositionY() == y) {
                return equipement;
            }
        }
        return null;
    }

    public void verifierPositionJoueur(Joueur joueur) {
        int x = joueur.getX();
        int y = joueur.getY();
        Equipement equipement = getEquipementAtPosition(x, y);

        if (equipement != null) {
            System.out.println("Vous avez trouvé un équipement : " + equipement.getNom());
            System.out.println("Voulez-vous le ramasser ? (oui/non)");

            Scanner scanner = new Scanner(System.in);
            String reponse = scanner.nextLine().trim().toLowerCase();

            if (reponse.equals("oui")) {
                retirerEquipementDeLaCarte(equipement);
                joueur.ramasser(equipement);

            } else {
                System.out.println("Vous avez choisi de ne pas ramasser l'équipement.");
            }
        } else {
            System.out.println("Aucun équipement à cette position.");
        }
    }

    private void retirerEquipementDeLaCarte(Equipement equipement) {
        if(equipementsListe.contains(equipement)) {
            equipementsListe.remove(equipement);
            mettreAJourEquipement();
        }


    }





}