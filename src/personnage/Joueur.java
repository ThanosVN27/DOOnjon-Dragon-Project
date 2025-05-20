package personnage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import equipements.Equipement;
import equipements.TypeEquipement;
import races.*;
import classes.*;

public class Joueur {
    private final String nom;
    private final Race race;
    private final Classe classe;
    private int pointsDeVie;
    private int force;
    private int dexterite;
    private int vitesse;
    private int initiative;
    private List<Equipement> inventaire;
    private Equipement armeEquipe;
    private Equipement armurEquipe;
    private int x;
    private int y;


    public Joueur(String nom) {
        this.nom = nom;
        this.race = choisirRace();
        this.classe = choisirClasse();


        this.pointsDeVie = classe.getPointsDeVie() + race.getPointsDeVie() ;
        this.force = race.getForce() +  lancerDes();
        this.dexterite = race.getDexterite() + lancerDes();
        this.vitesse = race.getVitesse() +   lancerDes() ;
        this.initiative = 0;
        this.inventaire = new ArrayList<>();
        initialiserEquipement();


    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getForce() {
        return force;
    }
    public int getDexterite() {
        return dexterite;
    }
    public int getVitesse() {
        return vitesse;
    }
    public int getInitiative() {
        return initiative;
    }

    public void modifierForce(int points) {
        force += points ;
    }

    public void modifierVitesse(int points) {
        vitesse += points;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void setX(int x) {
        this.x = x;
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
            //System.out.println("Lancer de dé " + (i + 1) + " : " + des);
            total += des;
        }
        System.out.println("Total des caractéristiques : " + total);
        return total;
    }

    private void initialiserEquipement() {
        inventaire.addAll(classe.getEquipementDeBase());
        System.out.println("Équipement initial ajouté à l'inventaire : " + inventaire);
    }

    public void equiperInventaire() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("----------------------Inventaire-----------------");
        for (int i = 0; i < inventaire.size(); i++) {
            System.out.println((i + 1) + " : " + inventaire.get(i).getNom());
        }
        System.out.println(inventaire.size() + 1 + " : Aucun équipement");


        System.out.println("Choisissez un équipement à équiper :");
        int choix = scanner.nextInt() - 1;

        if (choix >= 0 && choix < inventaire.size()) {
            Equipement e = inventaire.remove(choix);

            if (e.getType() == TypeEquipement.ARME) {
                if (armeEquipe != null) inventaire.add(armeEquipe);
                armeEquipe = e;
            } else {
                if (armurEquipe != null) inventaire.add(armurEquipe);
                armurEquipe = e;
            }

            e.appliquerEffets(this);
            System.out.println("Équipé : " + e.getNom());
        } else if (choix == inventaire.size()) {
            System.out.println("Choix aucun.");
        }
        else {
            System.out.println("Erreur !!");
        }
        System.out.println("--------------------------------------------------");
    }

    public void recuperEquipement(Equipement e) {
        inventaire.add(e);
        System.out.println("Récupéré : " + e.getNom());
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
                "ArmureEquipe = " + (armurEquipe != null ? armurEquipe.toString(): "Aucune") + "\n" +
                "Position = (" + x + ", " + y + ")";


    }


}