package personnage;

import equipements.Arme;
import jeu.Donjon;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import equipements.Armure;


public class MaitreDuJeu {
    private Donjon donjon;



    public MaitreDuJeu() {
        this.donjon = null;
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
            int nombreDeMonstres = scanner.nextInt();

            for (int i = 0; i < nomsMonstres.length; i++) {
                System.out.println((i + 1) + ". " + nomsMonstres[i]);
            }

            for (int i = 0; i < nombreDeMonstres; i++) {
                System.out.println("Choisissez le numéro du monstre #" + (i + 1) + " : ");
                int choix = scanner.nextInt();

                if (choix < 1 || choix > nomsMonstres.length) {
                    System.out.println("\u001B[31mChoix invalide. Réessayez.\u001B[0m");
                    i--;
                    continue;
                }

                String nom = nomsMonstres[choix - 1];

                System.out.println("Entrez les points de vie du monstre :");
                int pointsDeVie = scanner.nextInt();

                System.out.println("Entrez la vitesse du monstre :");
                int vitesse = scanner.nextInt();

                System.out.println("Entrez la force du monstre :");
                int force = scanner.nextInt();

                System.out.println("Entrez la dextérité du monstre :");
                int dexterite = scanner.nextInt();

                System.out.println("Entrez la classe d'armure du monstre :");
                int classeArmure = scanner.nextInt();

                System.out.println("Entrez l'attaque du monstre :");
                scanner.nextLine();
                String attaque = scanner.nextLine();

                System.out.println("Entrez la portée de l'attaque :");
                int portee = scanner.nextInt();

                System.out.println("Entrez les dégâts de l'attaque (ex: 1d6) :");
                scanner.nextLine();
                String degats = scanner.nextLine();

                Monstre monstre = new Monstre(nom, i + 1, attaque, portee, degats, pointsDeVie, vitesse, force, dexterite, classeArmure);
                donjon.getMonstresListe().add(monstre);

                donjon.placerMonstreAleatoirement(monstre);
                System.out.println("Monstres ajoutés et positionnés.");


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
                donjon.getMonstresListe().add(monstre);
                donjon.placerMonstreAleatoirement(monstre);

            }


        } else {
            System.out.println("\u001B[31mOption invalide. Réessayez.\u001B[0m");
        }

        System.out.println("Monstres ajoutés et positionnés.");
        donjon.afficherCarte();
    }

    public void ajouterJoueur() {
        Scanner scanner = new Scanner(System.in);

        //nombre de joueurs
        int nombreDeJoueurs = 0;
        while (nombreDeJoueurs <= 0) {
            System.out.println("Combien de joueurs voulez-vous ajouter ?");
            if (scanner.hasNextInt()) {
                nombreDeJoueurs = scanner.nextInt();
                if (nombreDeJoueurs <= 0) {
                    System.out.println("Veuillez entrer un nombre strictement positif.");
                }
            } else {
                System.out.println("\u001B[31mEntrée invalide. Veuillez entrer un nombre.\u001B[0m");
                scanner.next(); // Vider
            }
        }
        scanner.nextLine(); // Vider

        // Ajout de chaque joueur
        for (int i = 0; i < nombreDeJoueurs; i++) {
            System.out.println("\n--- Joueur #" + (i + 1) + " ---");


            System.out.println("Entrez le nom du joueur :");
            String nom = scanner.nextLine();

            int x = -1, y = -1;
            boolean positionValide = false;

            while (!positionValide) {
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
                        scanner.next(); // Vide l'entrée invalide
                    }
                }
                scanner.nextLine(); // Vide le reste de la ligne

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
                        scanner.next(); // Vide l'entrée invalide
                    }
                }
                scanner.nextLine(); // Vide le reste de la ligne

                // Placement du joueur
                Personnage joueur = new Personnage(nom);
                if (donjon.placerJoueur(joueur, x, y)) {
                    donjon.getJoueursListe().add(joueur);
                    System.out.println("✅ Joueur " + nom + " ajouté en (" + x + "," + y + ")");
                    positionValide = true;
                } else {
                    System.out.println("\u001B[31mCase occupée, choisissez une autre position\u001B[0m");
                    x = y = -1; //
                }
            }

            donjon.afficherCarte();
        }
    }

    public void ajouterEquiment() {
        ArrayList<Object> equipementListe = new ArrayList<>();
        for (Arme arme : Arme.values()) {
            equipementListe.add(arme);
        }
        for (Armure armure : Armure.values()) {
            equipementListe.add(armure);
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Combien d'équipements voulez-vous ajouter ?");
        int nombreEquipement = scanner.nextInt();

        for (int i = 0; i < nombreEquipement; i++) {
            System.out.println("Choisissez un équipement Arme/Armure :");
            for (int j = 0; j < equipementListe.size(); j++) {
                System.out.println((j + 1) + ". " + equipementListe.get(j));
            }

            System.out.println("Entrez le numéro de l'équipement :");
            int choix = scanner.nextInt();
            scanner.nextLine();

            if (choix < 1 || choix > equipementListe.size()) {
                System.out.println("\u001B[31mChoix invalide. Réessayez. \u001B[0m");
                i--;
                continue;
            }

            Object equipementChoisi = equipementListe.get(choix - 1);
            System.out.println("Vous avez choisi : " + equipementChoisi);

            donjon.getEquipementsListe().add(equipementChoisi);

            System.out.println("Entrez la position X de l'équipement (entre 1 et + " + (donjon.getHauteur() - 1) +  ":");
            int x = scanner.nextInt();
            System.out.println("Entrez la position Y de l'équipement (entre 1 et " + (donjon.getHauteur() - 1) + ":");
            int y = scanner.nextInt();

            if (x < 1 || x >= donjon.getHauteur() || y < 1 || y >= donjon.getLargeur()) {
                System.out.println("\u001B[31mPosition hors limites. Réessayez.\u001B[0m");
                i--;
                continue;
            }

            if (!donjon.placerEquipement(equipementChoisi, x, y)) {
                System.out.println("\u001B[31mImpossible de positionner l'équipement ici. Case occupée.\u001B[0m");
                i--;
            } else {
                System.out.println("Équipement ajouté à la position (" + x + ", " + y + ").");
            }
            donjon.afficherCarte();
        }
    }






    public void commencerLeJeu(){
        Scanner scanner = new Scanner(System.in);
        creerDonjon();
        ajouterMonstre();
        ajouterJoueur();
        ajouterEquiment();
        System.out.println("Voulez-vous commencer le jeu ? (O/N)");
        String reponse = scanner.nextLine();

        if (reponse.equalsIgnoreCase("O")) {
            System.out.println("Le jeu commence !");
        } else {
            System.out.println("Jeu annulé.");
        }
    }
}