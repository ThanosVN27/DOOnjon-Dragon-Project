package jeu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import personnage.Combattant;
import personnage.Joueur;
import personnage.Monstre;
import personnage.MaitreDuJeu;


public class Jeu {
    private final MaitreDuJeu maitreDuJeu;
    private List<Combattant> listeJoueurs;
    private int tour;

    public Jeu() {
        this.maitreDuJeu = new MaitreDuJeu();
        this.tour = 0;
        listeJoueurs = new ArrayList<>();
    }

    public void lancerJeu() {
        maitreDuJeu.miseEnPlace();
        afficherOrdreDeJeu();


    }

    public void jouerTour() {
        System.out.println("------------------Tour " + (tour + 1) + "-----------------");





    }

    public void afficherOrdreDeJeu() {
        System.out.println("---------------- Ordre de Jeu ----------------");
        listeJoueurs = maitreDuJeu.getDonjon().ordreJeu();
        for (Combattant c : listeJoueurs) {
            if (c.estJoueur()) {
                System.out.println("👤 Joueur  : " + c.afficherInfos() + " | Initiative : " + c.getInitiative());
            } else {
                System.out.println("👾 Monstre : " + c.afficherInfos() + " | Initiative : " + c.getInitiative());
            }
        }

        System.out.println("------------------------------------------------");
    }

    public void boucleJeu() {
        boolean fin = false;

        while (!fin) {
            jouerTour();
            fin = true;
        }

        System.out.println("🎉 Partie terminée !");
    }

    public void joueurTour() {
        System.out.println("C'est au tour du joueur " + listeJoueurs.get(tour).getNom() + " de jouer.");

        for (Combattant c : listeJoueurs) {
            int optionRestant = 3;
            if (c.estJoueur()) {
                System.out.println("C'est au tour du joueur " + c.getNom() + " de jouer.");
                while (optionRestant > 0) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Options restantes : " + optionRestant);
                    System.out.println("1. Attaquer");
                    System.out.println("2. Se déplacer");
                    System.out.println("3. S'équiper");
                    System.out.println("4. Ramasser");
                    System.out.println("5. Terminer");
                    System.out.print("Choisissez une option (1-5) : ");
                    int choix = scanner.nextInt();

                    Joueur joueur = (Joueur) c;
                    
                    while (choix < 1 || choix > 5) {
                        System.out.println("❌ Choix invalide. Veuillez entrer un nombre entre 1 et 5.");
                        System.out.print("Choisissez une option (1-5) : ");
                        choix = scanner.nextInt();
                    }
                    if (choix == 1) {
                        System.out.println("Vous avez choisi d'attaquer.");
                        System.out.println("Sélectionnez une cible :");
                        for (int i = 0; i < listeJoueurs.size(); i++) {
                            Combattant cible = listeJoueurs.get(i);
                            if (!cible.estJoueur()) {
                                System.out.println((i + 1) + ". " + cible.afficherInfos());
                            }
                        }
                        System.out.print("Entrez le numéro de la cible : ");
                        int cibleIndex = scanner.nextInt() - 1;
                        while (cibleIndex < 0 || cibleIndex >= listeJoueurs.size() || listeJoueurs.get(cibleIndex).estJoueur()) {
                            System.out.println("❌ Cible invalide. Veuillez entrer un numéro valide.");
                            System.out.print("Entrez le numéro de la cible : ");
                            cibleIndex = scanner.nextInt() - 1;
                        }
                        Combattant cible = listeJoueurs.get(cibleIndex);
                        c.attaquer(cible);
                        optionRestant--;
                    } else if (choix == 2) {
                        System.out.println("Vous avez choisi de vous déplacer.");
                        System.out.print("Entrez la nouvelle position X : ");
                        int nouvelleX = scanner.nextInt();
                        System.out.print("Entrez la nouvelle position Y : ");
                        int nouvelleY = scanner.nextInt();
                        c.deplacer(nouvelleX, nouvelleY);
                    } else if (choix ==
            }
            else {
                System.out.println("C'est au tour du monstre " + c.getNom() + " de jouer.");
            }
        }

        /*
        int optionRestant = 3;
        for(Combattant c : listeJoueurs) {
            if (c.estJoueur()) {
                System.out.println("C'est au tour du joueur " + c.getNom() + " de jouer.");
            }
            while (optionRestant > 0) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Options restantes : " + optionRestant);
                System.out.println("1. Attaquer");
                System.out.println("2. Se déplacer");
                System.out.println("3. S'équiper");
                System.out.println("4. Rammasser");
                System.out.println("5. Terminer");
                System.out.print("Choisissez une option (1-5) : ");
                int choix = scanner.nextInt();
                while (choix < 1 || choix > 5) {
                    System.out.println("❌ Choix invalide. Veuillez entrer un nombre entre 1 et 5.");
                    System.out.print("Choisissez une option (1-5) : ");
                    choix = scanner.nextInt();
                }
                if (choix == 1) {
                    System.out.println("Vous avez choisi d'attaquer.");
                    System.out.println("Sélectionnez une cible :");
                    for (int i = 0; i < listeJoueurs.size(); i++) {
                        Combattant cible = listeJoueurs.get(i);
                        if (!cible.estJoueur()) {
                            System.out.println((i + 1) + ". " + cible.afficherInfos());
                        }
                    }
                    System.out.print("Entrez le numéro de la cible : ");
                    int cibleIndex = scanner.nextInt() - 1;
                    while (cibleIndex < 0 || cibleIndex >= listeJoueurs.size() || listeJoueurs.get(cibleIndex).estJoueur()) {
                        System.out.println("❌ Cible invalide. Veuillez entrer un numéro valide.");
                        System.out.print("Entrez le numéro de la cible : ");
                        cibleIndex = scanner.nextInt() - 1;
                    }
                    Combattant cible = listeJoueurs.get(cibleIndex);
                    c.attaquer(cible);
                    optionRestant--;
                }

                else if (choix == 2) {
                    System.out.println("Vous avez choisi de vous déplacer.");
                    System.out.print("Entrez la nouvelle position X : ");
                    int nouvelleX = scanner.nextInt();
                    System.out.print("Entrez la nouvelle position Y : ");
                    int nouvelleY = scanner.nextInt();
                    c.seDeplacer(nouvelleX, nouvelleY);
                } 
                
                else if (choix == 3) {
                    System.out.println("Vous avez choisi de vous équiper.");
                    System.out.println("Sélectionnez un équipement :");
                    c.getEquipements().forEach(e -> {
                        System.out.println(e.getType() + " : " + e.getNom());
                    });
                } else if (choix == 4) {
                    System.out.println("Vous avez choisi de ramasser un objet.");
                    // Logique de ramassage ici
                } else if (choix == 5) {
                    System.out.println("Vous avez terminé votre tour.");
                    optionRestant = 0; // Fin du tour
                }
            } */
        }
    }
}