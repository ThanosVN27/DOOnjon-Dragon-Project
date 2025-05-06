package personnage;

import java.util.Scanner;
import races.*;
import classes.*;

public class Personnage {
    private final String nom;
    private final Race race;
    private final Classe classe;
    private int pointsDeVie;
    private int force;
    private int dexterite;
    private int vitesse;
    private int initiative;

    public Personnage(String nom) {
        this.nom = nom;
        this.race = choisirRace();
        this.classe = choisirClasse();

    }

    // Getters et setters
    public void setPointsDeVie(int pv) { this.pointsDeVie = pv; }
    public void setForce(int force) { this.force = force; }
    public void setDexterite(int dexterite) { this.dexterite = dexterite; }
    public void setVitesse(int vitesse) { this.vitesse = vitesse; }
    public void setInitiative(int initiative) { this.initiative = initiative; }

    public Race getRace() { return race; }
    public Classe getClasse() { return classe; }

    public static Classe choisirClasse() {
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("Choisissez une classe :");
            System.out.println("1. Guerrier\n2. Magicien\n3. Clerc\n4. Roublard");
            System.out.print("Votre choix : ");

            if (scanner.hasNextInt()) {
                int choix = scanner.nextInt();
                switch (choix) {
                    case 1: return new Guerrier();
                    case 2: return new Magicien();
                    case 3: return new Clerc();
                    case 4: return new Roublard();
                    default:
                        System.out.println("❌ Choix invalide. Veuillez entrer un nombre entre 1 et 4.");
                }
            } else {
                System.out.println("❌ Entrée invalide. Veuillez entrer un nombre.");
                scanner.next();
            }
        }
    }

    public static Race choisirRace() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choisissez une race :");
            System.out.println("1. Humain\n2. Elfe\n3. Halfelin\n4. Nain");
            System.out.print("Votre choix : ");

            if (scanner.hasNextInt()) {
                int choix = scanner.nextInt();
                switch (choix) {
                    case 1: return new Humain();
                    case 2: return new Elfe();
                    case 3: return new Halfelin();
                    case 4: return new Nain();
                    default:
                        System.out.println("❌ Choix invalide. Veuillez entrer un nombre entre 1 et 4.");
                }
            } else {
                System.out.println("❌ Entrée invalide. Veuillez entrer un nombre.");
                scanner.next();
            }
        }
    }



    public String getNom() {
        return "\u001B[34m" + nom + "\u001B[0m";
    }


    public String toString() {
        return "Personnage  {" +
                " nom = ' " + "\u001B[34m" + nom + "\u001B[0m + '\'" +
                ", race = " + race.getNomRaces() +
                ", classe = " + classe.getNomClasse() +
                ", pointsDeVie = " + pointsDeVie +
                ", force = " + force +
                ", dexterite = " + dexterite +
                ", vitesse = " + vitesse +
                ", initiative = " + initiative +
                '}';
    }





    public void seDeplacer() {
        System.out.println(nom + " se déplace.");
    }

    public void attaquer(Monstre cible) {
        System.out.println(nom + " attaque " + cible.getNom());
    }
}
