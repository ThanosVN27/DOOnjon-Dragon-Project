package jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import personnage.Joueur;
import personnage.MaitreDuJeu;
import personnage.Monstre;
import personnage.Personnage;

public class Jeu {
    private int tour;
    private MaitreDuJeu maitreDuJeu;
    private List<Monstre> listeMonstres;
    private List<Joueur> listeJoueurs;
    private List<Personnage> listeEntite;

    public Jeu() {
        this.maitreDuJeu = new MaitreDuJeu();
        this.tour = 0;
        this.listeJoueurs = new ArrayList<>();
        this.listeMonstres = new ArrayList<>();
        this.listeEntite = new ArrayList<>();
    }

    public void lancerJeu() {
        maitreDuJeu.miseEnPlace();
        boucleJeu();
    }

    public void jouerTour() {
        Delai.attendre();
        System.out.println("\n╠════════════════════════════ TOUR " + (tour + 1) + " ════════════════════════════════╣");
        for (Personnage entite : listeEntite) {
            if(entite.estMort()) {
                System.out.println(entite.getNom() + " est mort et ne peut pas jouer ce tour.");
                entite.mourir(maitreDuJeu.getDonjon());
                continue;
            }
            entite.jouerTour(maitreDuJeu.getDonjon());
            int choix = 0;
            String rep = "";
            // Vérification si le MJ veut faire une action spéciale 
            while (rep != "0" || rep != "1") {
                System.out.println("\nMJ : veux-tu faire une action spéciale ? [0] Non, [1] Oui");
                Scanner scanner = new Scanner(System.in);
                rep = scanner.nextLine();
                if (rep.equals("0")) {
                    break;
                } else if (rep.equals("1")) {
                    choix = 1;
                    break;
                } else {
                    System.out.println("MJ : choix invalide, essaie encore.");
                }
            }
            if (choix == 1) {
                while (choix != 0 || choix != 1 || choix != 2 || choix != 3) {
                    rep = "";
                    choix = 0;
                    while (rep.equals("0") == false || rep.equals("1") == false || rep.equals("2") == false || rep.equals("3") == false) {
                        System.out.println("\nMJ : quelle action spéciale veux-tu faire ?");
                        System.out.println("0. Suivant...");
                        System.out.println("1. déplacer un joueur ou un monstre");
                        System.out.println("2. Infliger des dégâts à un joueur ou un monstre");
                        System.out.println("3. Ajouter des obstacles");
                        Scanner scanner = new Scanner(System.in);
                        rep = scanner.nextLine();
                        if (rep.equals("0")) {
                            choix = 0;
                            System.out.println("MJ : suivant...");
                            break;
                        } else if (rep.equals("1")) {
                            choix = 1;
                            break;
                        } else if (rep.equals("2")) {
                            choix = 2;
                            break;
                        } else if (rep.equals("3")) {
                            choix = 3;
                            break;
                        } else {
                            System.out.println("MJ : choix invalide, essaie encore.");
                        }
                    }
                    int x = 1;
                    switch (choix) {
                        case 1 -> {
                            System.out.println("\nMJ : quel joueur ou monstre veux-tu déplacer ?");
                            for (Personnage p : listeEntite) {
                                System.out.println( x + ". "+ p.getNom());
                                x++;
                            }
                            choix = 0;
                            while (choix < 1 || choix > listeEntite.size()) {
                                System.out.println("\nMJ : entre le numéro du personnage à déplacer :");
                                Scanner scanner = new Scanner(System.in);
                                rep = scanner.nextLine();
                                try {
                                    choix = Integer.parseInt(rep);
                                } catch (NumberFormatException e) {
                                    System.out.println("\nMJ : entrée invalide, essaie encore.");
                                }
                            }
                            Personnage personnageADecalage = listeEntite.get(choix - 1);
                            System.out.println("\nMJ : où veux-tu déplacer " + personnageADecalage.getNom() + " ?");
                            int xCoos = 0;
                            int yCoos = 0;
                            choix = 0;
                            while (xCoos < 1 || xCoos > maitreDuJeu.getDonjon().getLargeur()) {
                                System.out.println("\nMJ : entre la coordonnée X (1 à " + (maitreDuJeu.getDonjon().getLargeur()-1) + ") :");
                                Scanner scanner = new Scanner(System.in);
                                rep = scanner.nextLine();
                                try {
                                    xCoos = Integer.parseInt(rep);
                                } catch (NumberFormatException e) {
                                    System.out.println("\nMJ : entrée invalide, essaie encore.");
                                }
                            }
                            while (yCoos < 1 || yCoos > maitreDuJeu.getDonjon().getHauteur()) {
                                System.out.println("\nMJ : entre la coordonnée Y (1 à " + (maitreDuJeu.getDonjon().getHauteur()-1) + ") :");
                                Scanner scanner = new Scanner(System.in);
                                rep = scanner.nextLine();
                                try {
                                    yCoos = Integer.parseInt(rep);
                                } catch (NumberFormatException e) {
                                    System.out.println("\nMJ : entrée invalide, essaie encore.");
                                }
                            }
                            for (Personnage p : listeJoueurs) {
                                if (p == personnageADecalage) {
                                    Joueur joueur = (Joueur) p;
                                    maitreDuJeu.getDonjon().mettreAPositionJoueur(joueur, xCoos, yCoos);
                                }
                            }
                            for (Personnage p : listeMonstres) {
                                if (p == personnageADecalage) {
                                    Monstre monstre = (Monstre) p;
                                    maitreDuJeu.getDonjon().mettreAPositionMonstre(monstre, xCoos, yCoos);
                                }
                            }
                        }
                        case 2 -> {
                            System.out.println("\nMJ : quel joueur ou monstre veux-tu blesser ?");
                            for (Personnage p : listeEntite) {
                                System.out.println( x + ". "+ p.getNom());
                                x++;
                            }
                            choix = 0;
                            while (choix < 1 || choix > listeEntite.size()) {
                                System.out.println("\nMJ : entre le numéro du personnage à blesser :");
                                Scanner scanner = new Scanner(System.in);
                                rep = scanner.nextLine();
                                try {
                                    choix = Integer.parseInt(rep);
                                } catch (NumberFormatException e) {
                                    System.out.println("\nMJ : entrée invalide, essaie encore.");
                                    continue;
                                }
                            }
                            Personnage personnageADecalage = listeEntite.get(choix - 1);
                            choix = 0;
                            while (choix < 1) {
                                System.out.println("\nMJ : entre le nombre de dès de dégâts à lancer :");
                                Scanner scanner = new Scanner(System.in);
                                rep = scanner.nextLine();
                                try {
                                    choix = Integer.parseInt(rep);
                                } catch (NumberFormatException e) {
                                    System.out.println("\nMJ : entrée invalide, essaie encore.");
                                    continue;
                                }
                            }
                            int totalDesDegats = choix;
                            int totalDegats = 0;
                            choix = 0;
                            while (choix < 1) {
                                System.out.println("\nMJ : entre le nombre de face par dès :");
                                Scanner scanner = new Scanner(System.in);
                                rep = scanner.nextLine();
                                try {
                                    choix = Integer.parseInt(rep);
                                } catch (NumberFormatException e) {
                                    System.out.println("\nMJ : entrée invalide, essaie encore.");
                                    continue;
                                }
                            }
                            for (int i = 0; i < totalDesDegats; i++) {
                                int degats = (int) (Math.random() * choix) + 1;
                                totalDegats += degats;
                                System.out.println("Dégâts infligés : " + degats);
                            }
                            System.out.println("\nTotal des dégâts infligés à " + personnageADecalage.getNom() + " : " + totalDegats);
                            personnageADecalage.setPointsDeVie(personnageADecalage.getPointsDeVie() - totalDegats);
                        }
                        case 3 -> {
                            x = 0;
                            while (x < 1) {
                                System.out.println("\nMJ : combien d'obstacles veux-tu ajouter ?");
                                Scanner scanner = new Scanner(System.in);
                                rep = scanner.nextLine();
                                try {
                                    x = Integer.parseInt(rep);
                                } catch (NumberFormatException e) {
                                    System.out.println("\nMJ : entrée invalide, essaie encore.");
                                }
                            }
                            maitreDuJeu.getDonjon().positionnerObstacles(x);

                        }
                        case 0 -> {
                            return;
                        }
                        default -> System.out.println("\nMJ : choix invalide, essaie encore.");
                    }
                }
            }
        }
        tour++;
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝");

    }

    public void afficherOrdreDeJeu() {
        System.out.println();
        listeMonstres = maitreDuJeu.getDonjon().getMonstres();
        listeJoueurs = maitreDuJeu.getDonjon().getJoueurs();
        System.out.println("╠══════════════════════════ ORDRE DU JEU ═══════════════════════════╣");


        listeEntite.clear();
        listeEntite.addAll(listeJoueurs);
        listeEntite.addAll(listeMonstres);

        // Trier la liste par initiative décroissante
        listeEntite.sort((p1, p2) -> Integer.compare(p2.getInitiative(), p1.getInitiative()));

        // Affichage de l'ordre de jeu trié
        for (Personnage entite : listeEntite) {
            System.out.println(entite.getNom() + " (Initiative: " + entite.getInitiative() + ")");
        }

        System.out.println("╚══════════════════════════════════════════════════════════════════════╝");
    }

    public void boucleJeu() {
        boolean finPartie = false;

        while (!finPartie) {
            afficherOrdreDeJeu();
            jouerTour();
            finPartie = estFinDonjon();

        }

        System.out.println("🎉 Partie terminée !");
    }

    private boolean estFinDonjon() {
        listeJoueurs = maitreDuJeu.getDonjon().getJoueurs();
        listeMonstres = maitreDuJeu.getDonjon().getMonstres();

        boolean joueurMort = listeJoueurs.stream().anyMatch(j -> j.getPointsDeVie() <= 0);
        boolean tousMonstresMorts = listeMonstres.stream().allMatch(m -> m.getPointsDeVie() <= 0);

        if (joueurMort) {
            System.out.println("💀 Un joueur est mort. Défaite !");
            return true;
        }

        if (tousMonstresMorts) {
            System.out.println("🏆 Tous les monstres ont été vaincus. Victoire !");
            restaurerVieJoueurs();
            passerAuDonjonSuivant();
            return true;
        }

        return false;
    }

    private void restaurerVieJoueurs() {
        for (Joueur joueur : listeJoueurs) {
            {
                joueur.restaurerVie();
            }
            System.out.println("Tous les joueurs ont été soignés !");
        }
    }

    private void passerAuDonjonSuivant() {
        maitreDuJeu.passerAuDonjon();
        listeMonstres.clear();
        tour = 0;
        boucleJeu();
    }

    public MaitreDuJeu getMaitreDuJeu() {
        return maitreDuJeu;
    }
}