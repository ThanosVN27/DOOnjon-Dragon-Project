package personnage;

import classes.*;
import equipements.Equipement;
import equipements.TypeEquipement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import races.*;

public class Joueur extends Personnage {
    private final Race race;
    private final Classe classe;
    private List<Equipement> inventaire;
    private Equipement armeEquipe;
    private Equipement armurEquipe;

    public Joueur(String nom) {
        super(nom, 0, 0, 0, 0); // Initialisé temporairement
        this.race = choisirRace();
        this.classe = choisirClasse();
        this.pointsDeVie = classe.getPointsDeVie() + race.getPointsDeVie();
        this.force = race.getForce() + lancerDes();
        this.dexterite = race.getDexterite() + lancerDes();
        this.vitesse = race.getVitesse() + lancerDes();
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

    public void setPointsDeVie(int pv) {
        this.pointsDeVie = Math.max(0, pv); // empêche les valeurs négatives
    }

    public void setForce(int force) { this.force = force; }

    public void setDexterite(int dexterite) { this.dexterite = dexterite; }

    public void setVitesse(int vitesse) { this.vitesse = vitesse; }

    public void setInitiative(int initiative) { this.initiative = initiative; }

    public Race getRace() { return race; }

    public Classe getClasse() { return classe; }

    private static Classe choisirClasse() {
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

    private static Race choisirRace() {
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


    public String afficherJoueur() {
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

    public String afficherInfos() {
        return getNom() + " (" + classe.getNomClasse() + " " + race.getNomRaces() +
                ", PV: " + getPointsDeVie() +
                ", Position: " + getX() + "," + getY() + ")" + " ARME: " + (armeEquipe != null ? armeEquipe.getNom() : "Aucune" )  + " || "  + "ARMURE: " + (armurEquipe != null ? armurEquipe.getNom() : "Aucune");
    }
    @Override
    public void seDeplacer(int x, int y) {
        int portee = Math.max(1, getVitesse() / 3);
        int posX = getX();
        int posY = getY();

        while (x < posX - portee || x > posX + portee || y < posY - portee || y > posY + portee) {
            System.out.println("❌ Déplacement impossible, trop loin !");
            System.out.println("Veuillez entrer une nouvelle position (X, Y) dans la portée de " + portee + " :");
            x = lireEntier("X : ");
            y = lireEntier("Y : ");
        }

        setX(x);
        setY(y);
        System.out.println(getNom() + " se déplace vers la position (" + x + ", " + y + ")");
    }

    public void ramasser(Equipement equipement) {
        System.out.println();
        System.out.println("*--------------------Object trouvé------------------------------*");
        inventaire.add(equipement);
        System.out.println(nom + " ramasse l'équipement : " + equipement.getNom());
        System.out.println("Équipement ajouté à l'inventaire.");
        System.out.println("Inventaire actuel :");
        for (int i = 0; i < inventaire.size(); i++) {
            System.out.println((i + 1) + " : " + inventaire.get(i).getNom());
        }
        System.out.println("*--------------------------------------------------------------*");
    }

    public void equiperInventaire() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("*--------------------------Inventaire-----------------*");
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
        System.out.println("*-------------------------------------------------------*");}

    @Override
    public void attaquer(Personnage cible) {
        System.out.println();
        System.out.println("*----------------------Attaque------------------------------*");
        if (this.armeEquipe != null) {
            //vérifie la portée de l'arme
            int distance = (int) Math.sqrt(Math.pow(cible.getX() - this.x, 2) + Math.pow(cible.getY() - this.y, 2));
            if (distance > this.armeEquipe.getPortee()) {
                System.out.println(nom + " est trop loin pour attaquer " + cible.getNom() + " avec " + this.armeEquipe.getNom());
                return;
            }
            System.out.println(nom + " attaque " + cible.getNom() + " avec " + this.armeEquipe.getNom());
            int degats = this.force + this.armeEquipe.getDegatsNumeriques();
            cible.setPointsDeVie(cible.getPointsDeVie() - degats);
            System.out.println("Dégâts infligés : " + degats);
            System.out.println("Points de vie restants de " + cible.getNom() + " : " + cible.getPointsDeVie());
        } else {
            System.out.println(nom + " n'a pas d'arme équipée pour attaquer.");
        }
        System.out.println("*-----------------------------------------------------------*");
    }

    @Override
    public void jouerTour(MaitreDuJeu mj) {
        System.out.println();
        System.out.println("--------------------Tour de " + getNom() + " ------------------------------");
        Scanner scanner = new Scanner(System.in);
        int actions = 3;
        mj.afficherCarte();
        while (actions > 0) {
            System.out.println(afficherInfos());
            System.out.println("\n🎮 Tour de " + getNom() + " - Actions restantes : " + actions);
            System.out.println("1. Se déplacer");
            System.out.println("2. Attaquer");
            System.out.println("3. S'équiper");
            System.out.println("4. Ramasser");
            System.out.println("0. Terminer le tour");
            System.out.print("Choisissez une action : ");


            if (!scanner.hasNextInt()) {
                System.out.println("❌ Veuillez entrer un nombre valide.");
                scanner.next(); // consomme la mauvaise entrée
                continue;
            }

            int choix = scanner.nextInt();

            switch (choix) {
                case 1 -> {
                    mj.afficherCarte();
                    System.out.println("Vitesse = " + vitesse / 3);
                    System.out.println("Entrez les coordonnées de destination :");

                    int x = demanderCoordonnee(scanner, "X");
                    int y = demanderCoordonnee(scanner, "Y");

                    // Vérifie si déplacement valide + mise à jour de la carte
                    if (mj.getDonjon().mettreAPositionJoueur(this, x, y)) {
                        System.out.println(getNom() + " s'est déplacé en (" + x + ", " + y + ").");
                        actions--;
                    } else {
                        System.out.println("❌ Déplacement échoué.");
                    }

                }

                case 2 -> {
                    if (armeEquipe != null) {
                        List<Monstre> monstres = mj.getDonjon().ordreJeuMonstre();
                        mj.afficherCarte();
                        Monstre cible = null;

                        for (Monstre monstre : monstres) {
                            int distance = (int) Math.sqrt(Math.pow(monstre.getX() - this.x, 2) + Math.pow(monstre.getY() - this.y, 2));
                            if (distance <= armeEquipe.getPortee()) {
                                cible = monstre;
                                break;
                            }
                        }

                        if (cible != null) {
                            attaquer(cible);
                            actions--;
                        } else {
                            System.out.println("❌ Aucun monstre à portée.");
                        }
                    } else {
                        System.out.println("❌ Aucun arme équipée pour attaquer.");
                    }
                }
                case 3 -> {
                    equiperInventaire();
                    actions--;
                }
                case 4 -> {
                    mj.afficherCarte();
                    int x = demanderCoordonnee(scanner, "X");
                    int y = demanderCoordonnee(scanner, "Y");

                    Equipement eq = mj.getEquipementSurCase(x, y);
                    if (eq != null) {
                        ramasser(eq);
                        mj.getDonjon().mettreAPositionJoueur(this, x, y); // Met à jour la position du joueur
                        actions--;
                    } else {
                        System.out.println("❌ Rien à ramasser ici.");
                    }
                }
                case 0 -> actions = 0;
                default -> System.out.println("❌ Choix invalide.");
            }
        }
        System.out.println("---------------------------------------------------------");
    }

    public static int lireEntier(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(message);
        return scanner.nextInt();
    }

    private int demanderCoordonnee(Scanner scanner, String axe) {
        int val;
        while (true) {
            System.out.print(axe + " : ");
            if (scanner.hasNextInt()) {
                val = scanner.nextInt();
                break;
            } else {
                System.out.println("Entrer un nombre entier valide.");
                scanner.next();
            }
        }
        return val;
    }

}