package personnage;

import jeu.Donjon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class MaitreDuJeu {
    private Donjon donjon;
    private final ArrayList<Monstre> monstres;
    private final ArrayList<Personnage> joueurs;
    private final ArrayList<Object> equipements;



    public MaitreDuJeu() {
        this.monstres = new ArrayList<>();
        this.joueurs = new ArrayList<>();
        this.equipements = new ArrayList<Object>( );
        this.donjon = null;
    }

    public Donjon creerDonjon() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez la hauteur du donjon (entre 15 et 25) :");
        int hauteur = scanner.nextInt();

        System.out.println("Entrez la largeur du donjon (entre 15 et 25) :");
        int largeur = scanner.nextInt();

        while (largeur < 15 || largeur > 25 || hauteur < 15 || hauteur > 25) {
            System.out.println("Les dimensions de la carte doivent être comprises entre 15 et 25 cases.");
            System.out.println("Entrez la hauteur du donjon (entre 15 et 25) :");
            hauteur = scanner.nextInt();
            System.out.println("Entrez la largeur du donjon (entre 15 et 25) :");
            largeur = scanner.nextInt();
        }

        this.donjon = new Donjon(largeur + 1, hauteur + 1);
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
                    System.out.println("Choix invalide.");
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
                monstres.add(monstre);

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
                monstres.add(monstre);
                donjon.placerMonstreAleatoirement(monstre);

            }


        } else {
            System.out.println("Option invalide.");
        }

        System.out.println("Monstres ajoutés et positionnés.");
        donjon.afficherCarte();

    }



public void ajouterEquiment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le nom de l'équipement :");
        String nom = scanner.nextLine();

        ArrayList<Object> equipement = new ArrayList<>();
        equipement.add(Arme.BATON);





    }

    public void ajouterJoueur() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Combien de joueurs voulez-vous ajouter ?");
        int nombreDeJoueurs = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < nombreDeJoueurs; i++) {
            System.out.println("Entrez le nom du joueur #" + (i + 1) + " :");
            String nom = scanner.nextLine();

            System.out.println("Entrez la position X du joueur #" + (i + 1) + " (entre 1 et 25) :");
            int x = scanner.nextInt();

            System.out.println("Entrez la position Y du joueur #" + (i + 1) + " (entre 1 et 25) :");
            int y = scanner.nextInt();
            scanner.nextLine();

            // Validate the input range
            if (x >= 1 && x <= 25 && y >= 1 && y <= 25) {
                if (donjon.getCarte()[x][y].equals(".")) {
                    Personnage joueur = new Personnage(nom);
                    if (donjon.placerJoueur(joueur, x , y )) {
                        joueurs.add(joueur);
                        System.out.println("Joueur " + nom + " ajouté à la position (" + x + ", " + y + ").");
                    } else {
                        System.out.println("Impossible de positionner le joueur ici. Case occupée.");
                        i--;
                    }
                } else {
                    System.out.println("Position invalide. Réessayez.");
                    i--;
                }
            } else {
                System.out.println("Position hors limites. Réessayez.");
                i--;
            }

            donjon.afficherCarte();
        }
    }




    public void commencerLeJeu(){
        Scanner scanner = new Scanner(System.in);
        creerDonjon();
        ajouterMonstre();
        ajouterJoueur();
        System.out.println("Voulez-vous commencer le jeu ? (O/N)");
        String reponse = scanner.nextLine();

        if (reponse.equalsIgnoreCase("O")) {
            System.out.println("Le jeu commence !");
        } else {
            System.out.println("Jeu annulé.");
        }
    }
}