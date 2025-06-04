package personnage;

import equipements.Arme;
import equipements.Armure;
import equipements.Equipement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import jeu.Donjon;
import java.util.InputMismatchException;

public class MaitreDuJeu {
    private Donjon donjon;

    public MaitreDuJeu() {
        this.donjon = creerDonjon();
    }


    public Donjon creerDonjon() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez la hauteur du donjon (entre 15 et 25) :");
        int hauteur = scanner.nextInt();

        System.out.println("Entrez la largeur du donjon (entre 15 et 25) :");
        int largeur = scanner.nextInt();

        while (largeur < 15 || largeur > 25 || hauteur < 15 || hauteur > 25) {
            System.out.println("\u001B[31mLes dimensions de la carte doivent être comprises entre 15 et 25 cases. Réessayez.\u001B[0m");
            System.out.println("Entrez la hauteur du donjon (entre 15 et 25) :");
            hauteur = scanner.nextInt();
            System.out.println("Entrez la largeur du donjon (entre 15 et 25) :");
            largeur = scanner.nextInt();
        }

        this.donjon = new Donjon(hauteur + 1, largeur + 1);
        System.out.println("Le donjon a été créé avec succès !");

        System.out.println("Entrez le nombre d'obstacles à positionner :");
        int nombreObstacles = scanner.nextInt();
        while (nombreObstacles < 0 || nombreObstacles > largeur * hauteur)
        {
            System.out.println("\u001B[31mLe nombre d'obstacles doit être compris entre 0 et " + (largeur * hauteur) + ". Réessayez.\u001B[0m");
            System.out.println("Entrez le nombre d'obstacles à positionner :");
            nombreObstacles = scanner.nextInt();
        }
        donjon.positionnerObstacles(nombreObstacles);


        return donjon;
    }


    public void ajouterMonstre() {
        int option = choisirOption();
        int nbMonstres = demanderNombreMonstres();

        if (option == 1) {
            ajouterMonstresManuellement(nbMonstres);
        } else {
            genererMonstresAleatoires(nbMonstres);
        }

        donjon.afficherCarte();
    }



    private int choisirOption() {
        System.out.println("Choisissez une option :\n1. Manuellement\n2. Aléatoirement");
        return lireEntier(1, 2);
    }

    private int demanderNombreMonstres() {
        System.out.println("Combien de monstres voulez-vous ajouter ?");
        return lireEntier(1, 20);
    }

    private void ajouterMonstresManuellement(int nbMonstres) {
        String[] nomsMonstres = initialiserNomsMonstres();

        for (int i = 0; i < nbMonstres; i++) {
            afficherListeMonstres(nomsMonstres);
            Monstre monstre = creerMonstreManuellement(nomsMonstres, i);
            placerMonstre(monstre, i);
        }
    }

    private String[] initialiserNomsMonstres() {
        return new String[]{"Gobelin", "Orc", "Dragon", "Vampire", "Loup",
                "Squelette", "Zombie", "Fantome", "Troll", "Minotaure"};
    }

    private void afficherListeMonstres(String[] nomsMonstres) {
        System.out.println("Choisissez un monstre :");
        for (int i = 0; i < nomsMonstres.length; i++) {
            System.out.printf("%d. %s%n", i+1, nomsMonstres[i]);
        }
    }

    private Monstre creerMonstreManuellement(String[] nomsMonstres, int id) {
        int choixMonstre = lireEntier(1, nomsMonstres.length) - 1;
        String nom = nomsMonstres[choixMonstre];


        System.out.println("Entrez les caractéristiques du monstre :");
        System.out.println("Points de vie (10-50) :");
        int pv = lireEntier(10, 50);

        System.out.println("Force (1-10) :");
        int force = lireEntier(1, 10);

        System.out.println("Dégâts (ex: 1d6) :");
        String degats = lireEntier(1, 6) + "d" + lireEntier(4, 10); // Exemple de format "1d6"


        System.out.println("Vitesse (1-10) :");
        int vitesse = lireEntier(1, 10);

        System.out.println("Dextérité (1-10) :");
        int dexterite = lireEntier(1, 10);

        System.out.println("Classe d'armure (1-20) :");
        int classeArmure = lireEntier(1, 20);

        System.out.println("Portée de l'attaque (1-5) :");
        int portee = lireEntier(1, 5);

        System.out.println("Entrez le nom de l'attaque :");
        Scanner scanner = new Scanner(System.in);
        String attaque = scanner.nextLine();

        return new Monstre(nom, id,attaque,
                force, degats, pv, vitesse, dexterite, classeArmure, portee);
    }

    private void genererMonstresAleatoires(int nbMonstres) {
        Random random = new Random();
        String[] nomsMonstres = initialiserNomsMonstres();

        for (int i = 0; i < nbMonstres; i++) {
            String nom = nomsMonstres[random.nextInt(nomsMonstres.length)];
            int pv = random.nextInt(30) + 10;
            int vitesse = random.nextInt(10) + 1;

            Monstre monstre = new Monstre(nom, i, "Attaque aléatoire",
                    random.nextInt(3)+1, "1d"+(random.nextInt(6)+4),
                    pv, vitesse, 10, 10, 5);
            donjon.placerMonstreAleatoirement(monstre, i);
        }
    }

    private void placerMonstre(Monstre monstre, int id) {
        System.out.println("Position x :");
        int x = lireEntier(0, donjon.getLargeur()-1);

        System.out.println("Position y :");
        int y = lireEntier(0, donjon.getHauteur()-1);

        donjon.placerMonstre(monstre, x, y, id);
    }

    private int lireEntier(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                int valeur = scanner.nextInt();
                if (valeur >= min && valeur <= max) {
                    return valeur;
                }
                System.out.printf("Veuillez entrer un nombre entre %d et %d%n", min, max);
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre valide");
                scanner.next();
            }
        }
    }

    public void ajouterJoueur() {
        Scanner scanner = new Scanner(System.in);

        int nbJoueurs = 0;
        while (nbJoueurs <= 0) {
            System.out.println("Combien de joueurs voulez-vous ajouter ?");
            if (scanner.hasNextInt()) {
                nbJoueurs = scanner.nextInt();
                if (nbJoueurs <= 0) {
                    System.out.println("Veuillez entrer un nombre strictement positif.");
                }
            } else {
                System.out.println("\u001B[31mEntrée invalide. Veuillez entrer un nombre.\u001B[0m");
                scanner.next();
            }
        }

        scanner.nextLine();

        for (int i = 0; i < nbJoueurs; i++) {
            System.out.println("\n--- Joueur #" + (i + 1) + " ---");

            System.out.println("Entrez le nom du joueur :");
            String nom = scanner.nextLine();
            Joueur joueur = new Joueur(nom);
            joueur.equiperInventaire();

            int x = -1, y = -1;
            boolean valide = false;

            while (!valide) {
                // Posiotion X
                while (x < 1 || x >= donjon.getHauteur()) {
                    System.out.println("Position X (1-" + (donjon.getHauteur()-1) + ") :");
                    if (scanner.hasNextInt()) {
                        x = scanner.nextInt();
                        if (x < 1 || x >= donjon.getHauteur()) {
                            System.out.println("\u001B[31mX hors limites\u001B[0m");
                        }
                    } else {
                        System.out.println("\u001B[31mEntrez un nombre valide\u001B[0m");
                        scanner.next();
                    }
                }
                scanner.nextLine();

                // Position Y
                while (y < 1 || y >= donjon.getLargeur()) {
                    System.out.println("Position Y (1-" + (donjon.getLargeur()-1) + ") :");
                    if (scanner.hasNextInt()) {
                        y = scanner.nextInt();
                        if (y < 1 || y >= donjon.getLargeur()) {
                            System.out.println("\u001B[31mY hors limites\u001B[0m");
                        }
                    } else {
                        System.out.println("\u001B[31mEntrez un nombre valide\u001B[0m");
                        scanner.next();
                    }
                }

                scanner.nextLine();

                // Placement
                if (donjon.placerJoueur(joueur, x, y,i + 1)) {
                    System.out.println("✅ Joueur " + nom + " ajouté en (" + x + "," + y + ")");
                    valide = true;
                } else {
                    System.out.println("\u001B[31mCase occupée, choisissez une autre position\u001B[0m");
                    x = y = -1;
                }
            }

            donjon.afficherCarte();
        }
    }

    public void ajouterEquipement() {
        List<Equipement> equipementListe = new ArrayList<>();
        equipementListe.addAll(List.of(Arme.values()));
        equipementListe.addAll(List.of(Armure.values()));

        Scanner scanner = new Scanner(System.in);

        System.out.println("Combien d'équipements voulez-vous ajouter ?");
        int nombreEquipement = scanner.nextInt();

        for (int i = 0; i < nombreEquipement; i++) {

            System.out.println("\nChoisissez un équipement parmi la liste suivante :");
            for (int j = 0; j < equipementListe.size(); j++) {
                System.out.println((j + 1) + ". " + equipementListe.get(j));
            }

            int choix = -1;
            while (choix < 1 || choix > equipementListe.size()) {
                System.out.print("Entrez le numéro de l'équipement : ");
                choix = scanner.nextInt();
                if (choix < 1 || choix > equipementListe.size()) {
                    System.out.println("\u001B[31mChoix invalide. Réessayez.\u001B[0m");
                }
            }

            Equipement equipementChoisi = equipementListe.get(choix - 1);
            System.out.println("Vous avez choisi : " + equipementChoisi);



            boolean positionValide = false;
            while (!positionValide) {
                System.out.print("Entrez la position X de l'équipement (entre 1 et " + (donjon.getHauteur() - 1) + ") : ");
                int x = scanner.nextInt();
                System.out.print("Entrez la position Y de l'équipement (entre 1 et " + (donjon.getLargeur() - 1) + ") : ");
                int y = scanner.nextInt();

                if (x < 1 || x >= donjon.getHauteur() || y < 1 || y >= donjon.getLargeur()) {
                    System.out.println("\u001B[31mPosition hors limites. Réessayez.\u001B[0m");
                    continue;
                }

                if (!donjon.placerEquipement(equipementChoisi, x, y)) {
                    System.out.println("\u001B[31mImpossible de positionner l'équipement ici. Case occupée.\u001B[0m");
                } else {
                    positionValide = true;
                    System.out.println("Équipement ajouté à la position(" + x + ", " + y + ").");
                    donjon.afficherCarte();
                }
            }
        }
    }


    public Donjon getDonjon() {
        return donjon;
    }




    public void miseEnPlace(){
        ajouterMonstre();
        ajouterEquipement();
        ajouterJoueur();

    }



    public void afficherCarte() {
        donjon.afficherCarte();
    }

    public void passerAuDonjon() {
        System.out.println("Passage au donjon suivant...");

        // Sauvegarde des joueurs
        List<Joueur> anciensJoueurs = donjon.getJoueurs();

        // Nouveau donjon
        donjon = creerDonjon();

        // Réinitialiser la carte sans perdre les joueurs
        for (int i = 0; i < anciensJoueurs.size(); i++) {
            Joueur joueur = anciensJoueurs.get(i);
            Scanner scanner = new Scanner(System.in);
            int x = -1, y = -1;
            boolean valide = false;

            while (!valide) {
                System.out.println("Pour le joueur " + joueur.getNom() + ", choisissez une nouvelle position :");

                while (x < 1 || x >= donjon.getHauteur()) {
                    System.out.println("Position X (1-" + (donjon.getHauteur() - 1) + ") :");
                    if (scanner.hasNextInt()) {
                        x = scanner.nextInt();
                        if (x < 1 || x >= donjon.getHauteur()) {
                            System.out.println("\u001B[31mX hors limites\u001B[0m");
                        }
                    } else {
                        System.out.println("\u001B[31mEntrez un nombre valide\u001B[0m");
                        scanner.next();
                    }
                }

                scanner.nextLine();

                while (y < 1 || y >= donjon.getLargeur()) {
                    System.out.println("Position Y (1-" + (donjon.getLargeur() - 1) + ") :");
                    if (scanner.hasNextInt()) {
                        y = scanner.nextInt();
                        if (y < 1 || y >= donjon.getLargeur()) {
                            System.out.println("\u001B[31mY hors limites\u001B[0m");
                        }
                    } else {
                        System.out.println("\u001B[31mEntrez un nombre valide\u001B[0m");
                        scanner.next();
                    }
                }

                scanner.nextLine();

                if (donjon.placerJoueur(joueur, x, y, i + 1)) {
                    System.out.println("✅ Joueur " + joueur.getNom() + " ajouté en (" + x + "," + y + ")");
                    valide = true;
                } else {
                    System.out.println("\u001B[31mCase occupée, choisissez une autre position\u001B[0m");
                    x = y = -1;
                }
            }
        }

        ajouterMonstre();
        ajouterEquipement();// ajouter monstres et équipements pour le nouveau donjon
        donjon.afficherCarte();
    }




}
