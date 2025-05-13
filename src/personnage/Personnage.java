package personnage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import equipements.Arme;
import equipements.Armure;
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
    private final ArrayList<Object> inventaire;
    private Arme armeEquipe;
    private Armure armurEquipe;


    public Personnage(String nom) {
        this.nom = nom;
        this.race = choisirRace();
        this.classe = choisirClasse();


        this.pointsDeVie = classe.getPointsDeVie() + race.getPointsDeVie() ;
        this.force = race.getForce() +  lancerDes();
        this.dexterite = race.getDexterite() + lancerDes();
        this.vitesse = race.getVitesse() +   lancerDes() ;
        this.initiative = race.getInitiative() + lancerDes();
        this.inventaire = new ArrayList<>();
        initialiserEquipement();


    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }

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
        return  "\u001B[34m" + "--[Personnage]-- = "  + nom + "\u001B[0m [ " +
                "Race = " + race.getNomRaces()  +
                " ; Classe = " + classe.getNomClasse()  +
                " ; PointsDeVie = " + pointsDeVie  +
                " ; Force = " + force  +
                " ; Dexterite = " + dexterite  +
                " ; Vitesse = " + vitesse  +
                " ; Initiative = " + initiative + "\n" +
                "--[Inventaire]-- = " + inventaire  + "\n" +
                "ArmeEquipe = " + (armeEquipe != null ? armeEquipe.toString() : "Aucune") + "\n" +
                "ArmureEquipe = " + (armurEquipe != null ? armurEquipe.toString(): "Aucune");

    }

    public void seDeplacer() {
        System.out.println(nom + " se déplace.");
    }

    public void attaquer(Monstre cible) {
        System.out.println(nom + " attaque " + cible.getNom()) ;
        int degats = this.force;
        cible.setPointsDeVie(cible.getPointsDeVie() - degats);
        System.out.println("Dégâts infligés : " + degats);
        System.out.println("Points de vie restants de " + cible.getNom() + " : " + cible.getPointsDeVie());
    }

    public int lancerDes() {
        int total = 0;
        for (int i = 0; i < 4; i++) {
            int des = (int) (Math.random() * 4) + 1;
            System.out.println("Lancer de dé " + (i + 1) + " : " + des);
            total += des;
        }
        System.out.println("Total des caractéristiques : " + total);
        return total;
    }

    private void initialiserEquipement() {
        inventaire.addAll(classe.getEquipementDeBase());
        System.out.println("Équipement initial ajouté à l'inventaire : " + inventaire);
    }

    public void equiperEquipement(int choix) {
        choix = choix - 1;
        if (choix >= 0 && choix < inventaire.size()) {
            Object equipement = inventaire.get(choix);
            if (equipement instanceof Arme) {
                armeEquipe = (Arme) equipement;
                inventaire.remove(choix);
                System.out.println("Arme équipée : " + armeEquipe.getNom());
                if (armeEquipe.getNom().equals("Épée longue") || armeEquipe.getNom().equals("Rapière")) {
                    this.vitesse -= 2;
                    this.force += 4;
                    System.out.println("Vitesse diminuée de 2 en raison de l'arme lourde.");
                    System.out.println("Force augmentée de 4 en raison de l'arme lourde.");
                }

            } else if (equipement instanceof Armure) {
                armurEquipe = (Armure) equipement;
                inventaire.remove(choix);
                System.out.println("Armure équipée : " + armurEquipe.getNom());
                if (armurEquipe.getNom().equals("Cotte de mailles") || armurEquipe.getNom().equals("Harnois")) {
                    this.vitesse -= 4;
                    System.out.println("Vitesse diminuée de 4 en raison de l'armure lourde.");
                } else {
                    System.out.println("Équipement non valide.");
                }
            } else {
                System.out.println("Choix invalide.");
            }
        }
    }
}