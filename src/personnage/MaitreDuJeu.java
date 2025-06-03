package personnage;

import equipements.Arme;
import equipements.Armure;
import equipements.Equipement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import jeu.Donjon;


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
        Scanner scanner = new Scanner(System.in);
        String[] nomsMonstres = {
                "Gobelin", "Orc", "Dragon", "Vampire", "Loup",
                "Squelette", "Zombie", "Fantome", "Troll", "Minotaure"
        };

        System.out.println("Choisissez une option :");
        System.out.println("1. Décider les monstres manuellement");
        System.out.println("2. Générer des monstres aléatoirement");
        int option = scanner.nextInt();

        if (option == 1) {
            // Option: Décider
            System.out.println("Combien de monstres voulez-vous ajouter ?");
            int nbMonstre = scanner.nextInt();

            for (int i = 0; i < nomsMonstres.length; i++) {
                System.out.println((i + 1) + ". " + nomsMonstres[i]);
            }

            for (int i = 0; i < nbMonstre; i++) {
                System.out.println("Choisissez le numéro du monstre #" + (i + 1) + " : ");
                int choix = scanner.nextInt();

                if (choix < 1 || choix > nomsMonstres.length) {
                    System.out.println("\u001B[31mChoix invalide. Réessayez.\u001B[0m");
                    i--;
                    continue;
                }

                String nom = nomsMonstres[choix - 1];

                System.out.println("Entrez les points de vie:");
                int pointsDeVie = scanner.nextInt();

                System.out.println("Entrez la vitesse:");
                int vitesse = scanner.nextInt();

                System.out.println("Entrez la force:");
                int force = scanner.nextInt();

                System.out.println("Entrez la dextérité:");
                int dexterite = scanner.nextInt();

                System.out.println("Entrez la classe d'armure:");
                int classeArmure = scanner.nextInt();

                System.out.println("Entrez l'attaque:");
                scanner.nextLine();
                String attaque = scanner.nextLine();

                System.out.println("Entrez la portée de l'attaque :");
                int portee = scanner.nextInt();

                System.out.println("Entrez les dégâts de l'attaque (ex: 1d6) :");
                scanner.nextLine();
                String degats = scanner.nextLine();

                System.out.println("La position du x du monstre :");
                int x = scanner.nextInt();

                System.out.println("La position du y du monstre :");
                int y = scanner.nextInt();

                Monstre monstre = new Monstre(nom, i + 1, attaque, portee, degats, pointsDeVie, vitesse, force, dexterite, classeArmure);

                donjon.placerMonstre(monstre,x,y,i + 1);
                System.out.println("Monstres ajoutés et positionnés.!!");


            }
        } else if (option == 2) {
            // Option: Random
            System.out.println("Combien de monstres voulez-vous générer ?");
            int nombreDeMonstres = scanner.nextInt();

            Random random = new Random();
            for (int i = 0; i < nombreDeMonstres; i++) {
                String nom = nomsMonstres[random.nextInt(nomsMonstres.length)];
                int pointsDeVie = random.nextInt(50) + 10; // Random points de vie
                int vitesse = random.nextInt(10) + 1;      // Random vitesse
                int force = random.nextInt(10) + 1;       // Random force
                int dexterite = random.nextInt(10) + 1;   // Random dexterité
                int classeArmure = random.nextInt(5) + 1; // Random armoure
                String attaque = "Attaque aléatoire";
                int portee = random.nextInt(5) + 1;       // Random portée
                String degats = "1d" + (random.nextInt(6) + 4); // Random dégâts (1d4 à 1d10)

                Monstre monstre = new Monstre(nom, i + 1, attaque, portee, degats, pointsDeVie, vitesse, force, dexterite, classeArmure);
                donjon.placerMonstreAleatoirement(monstre, i + 1);

            }


        } else {
            System.out.println("\u001B[31mOption invalide. Réessayez.\u001B[0m");
        }

        System.out.println("Monstres ajoutés et positionnés.");
        donjon.afficherCarte();
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
                if (donjon.placerJoueur(nom, x, y,i + 1)) {
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

            donjon.getEquipementsListe().add(equipementChoisi);

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

    public Equipement getEquipementSurCase(int x, int y) {
        for (Equipement equipement : donjon.getEquipementsListe()) {
            if (equipement.getPositionX() == x && equipement.getPositionY() == y) {
                donjon.mettreAJourEquipement(equipement,x,y);
                }
                afficherCarte();
                return equipement;
            }

        return null;
    }


    public void afficherCarte() {
        donjon.afficherCarte();
    }




}
