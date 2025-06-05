package personnage;

import classes.*;
import equipements.Armure;
import equipements.Equipement;
import equipements.TypeEquipement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import equipements.Arme;
import jeu.Delai;
import jeu.Donjon;
import races.*;
import sorts.Sort;

public class Joueur extends Personnage {
    private final Race race;
    private final Classe classe;
    private List<Equipement> inventaire;
    private Arme armeEquipe;
    private Armure armurEquipe;
    private final int pointsDeVieMax;
    private List<Sort> sorts;

    public Joueur(String nom) {
        super(nom, 0, 0, 0, 0);
        this.race = choisirRace();
        this.classe = choisirClasse();
        this.pointsDeVie = classe.getPointsDeVie() + race.getPointsDeVie();
        this.force = race.getForce() + lancerDesDepart();
        this.dexterite = race.getDexterite() + lancerDesDepart();
        this.vitesse = race.getVitesse() + lancerDesDepart();
        this.inventaire = new ArrayList<>();
        this.pointsDeVieMax = pointsDeVie;
        this.classeArmure =  0;
        initialiserEquipement();
    }

    public int getPointsDeVieMax() {
        return pointsDeVieMax;
    }

    public void modifierForce(int points) {
        force += points ;
    }

    public void modifierVitesse(int points) {
        vitesse += points;
    }

    public List<Arme> getArmes() {
        List<Arme> armes = new ArrayList<>();
        for (Equipement equipement : inventaire) {
            if(equipement.getType() == TypeEquipement.ARME) {
                armes.add((Arme) equipement);
            }
        }
        return armes;
    }


    public void setPointsDeVie(int pv) {
        this.pointsDeVie = Math.max(0, pv); // empêche les valeurs négatives
    }


    public void setVitesse(int vitesse) { this.vitesse = vitesse; }


    public Classe getClasse() { return classe; }


    public void restaurerVie() {
        this.pointsDeVie = this.pointsDeVieMax;
    }

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



    public int lancerDesDepart() {
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
        StringBuilder sb = new StringBuilder();

        sb.append("\u001B[34m")
                .append("╔════════════════════════════════════╗\n")
                .append(String.format("║ %-34s ║\n", nom))
                .append("╚════════════════════════════════════╝\n")
                .append("\u001B[0m");

        sb.append(String.format("Race        : %s\n", race.getNomRace()));
        sb.append(String.format("Classe      : %s\n", classe.getNomClasse()));
        sb.append(String.format("PV          : %d\n", pointsDeVie));
        sb.append(String.format("Force       : %d\n", force));
        sb.append(String.format("Dextérité   : %d\n", dexterite));
        sb.append(String.format("Vitesse     : %d\n", vitesse));
        sb.append(String.format("Position    : (%d, %d)\n", x, y));

        sb.append(String.format("Arme        : %s\n", armeEquipe != null ? armeEquipe.getNom() : "Aucune"));
        sb.append(String.format("Armure      : %s\n", armurEquipe != null ? armurEquipe.getNom() : "Aucune"));

        sb.append("Inventaire  :\n");
        if (inventaire.isEmpty()) {
            sb.append(" (Vide)\n");
        } else {
            for (Equipement item : inventaire) {
                sb.append("- ").append(item.getNom()).append("\n");
            }
        }

        return sb.toString();
    }


    public String afficherInfos() {
        return getNom() + " (" + classe.getNomClasse() + " " + race.getNomRace() +
                ", PV: " + getPointsDeVie() +
                ", Position: " + getX() + "," + getY() + ")" + " ARME: " + (armeEquipe != null ? armeEquipe.getNom() : "Aucune" )  + " || "  + "ARMURE: " + (armurEquipe != null ? armurEquipe.getNom() : "Aucune");
    }


    public void seDeplacer(int x, int y) {
        int portee = Math.max(1, getVitesse() / 3);
        int posX = getX();
        int posY = getY();



        System.out.println("\n╠══════════════════════ SE DÉPLACER ════════════════════════════╣");
        System.out.println(getNom() + " se prépare à se déplacer.");
        System.out.println("Position actuelle : (" + posX + ", " + posY + ")");
        System.out.println("Portée de déplacement : " + portee);


        while (!estDansLaPortee(x, y, posX, posY, portee)) {
            System.out.println("❌ Déplacement impossible, trop loin !");
            System.out.println("Veuillez entrer une nouvelle position (X, Y) dans la portée de " + portee + " :");
            x = lireEntier("X : ");
            y = lireEntier("Y : ");
        }

        // Met à jour la position
        this.x = x;
        this.y = y;

        System.out.println(getNom() + " se déplace vers la position (" + x + ", " + y + ")");
        System.out.println("╠═════════════════════════════════════════════════════════════╣\n");
        Delai.attendre();
    }


    private boolean estDansLaPortee(int destX, int destY, int posX, int posY, int portee) {
        return Math.abs(destX - posX) <= portee && Math.abs(destY - posY) <= portee;
    }

    public void ramasser(Equipement equipement) {
        System.out.println();

        System.out.println("╠══════════════════════ RAMASSAGE ════════════════════════════╣");

        inventaire.add(equipement);
        System.out.println(getNom() + " a ramassé l'équipement : " + equipement.getNom());
        System.out.println("Position de l'équipement : (" + x + ", " + y + ")");

        System.out.println("╠═════════════════════════════════════════════════════════════╣\n");


    }

    public void equiperInventaire() {
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        System.out.println("╠═══════════════════════INVENTAIRE═══════════════════════════╣");
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
                armeEquipe = (Arme) e;
            } else {
                if (armurEquipe != null) inventaire.add(armurEquipe);
                armurEquipe = (Armure) e;
            }

            e.appliquerEffets(this);
            System.out.println("Équipé : " + e.getNom());
        } else if (choix == inventaire.size()) {
            System.out.println("Choix aucun.");
        }
        else {
            System.out.println("Erreur !!");
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
        System.out.println();
        }

    public boolean estMort() {
        return pointsDeVie <= 0;
    }

    @Override
    public void attaquer(Personnage cible) {
        System.out.println();
        System.out.println("╠════════════════════════════ ATTAQUE ═════════════════════════════════╣");

        if (armeEquipe == null) {
            System.out.println("❌ " + nom + " n'a pas d'arme équipée pour attaquer.");
            return;
        }

        int distance = (int) Math.sqrt(Math.pow(cible.getX() - this.x, 2) + Math.pow(cible.getY() - this.y, 2));
        if (distance > armeEquipe.getPortee()) {
            System.out.println("❌ " + nom + " est trop loin pour attaquer " + cible.getNom() + " avec " + armeEquipe.getNom() + ".");
            return;
        }

        System.out.println("🎯 " + nom + " attaque " + cible.getNom() + " (" + cible.getX() + ", " + cible.getY() + ") avec " + armeEquipe.getNom() + ".");

        int jetAttaque = (int) (Math.random() * 20) + 1; // 1d20
        int bonus = armeEquipe.estDistance() ? dexterite : force;
        int totalAttaque = jetAttaque + bonus;

        System.out.println("🎲 Jet d'attaque : " + jetAttaque + " + " + bonus + " = " + totalAttaque);
        System.out.println("🛡️ Classe d'armure de " + cible.getNom() + " : " + cible.getClasseArmure());

        if (totalAttaque > cible.getClasseArmure()) {
            int degats = armeEquipe.getDegatsNumeriques();
            System.out.println("💥 Attaque réussie ! " + cible.getNom() + " subit " + degats + " points de dégâts.");
            cible.setPointsDeVie(cible.getPointsDeVie() - degats);
            System.out.println("❤️ Points de vie restants de " + cible.getNom() + " : " + cible.getPointsDeVie());

            // Vérification si la cible est morte
            if (cible.getPointsDeVie() <= 0) {
                System.out.println("💀 " + cible.getNom() + " est mort !");
            }

        } else {
            System.out.println("❌ Attaque échouée. Aucun dégât infligé.");
        }

        System.out.println("╠═══════════════════════════════════════════════════════════════════════╣");
        Delai.attendre();
    }



    @Override
    public void mourir(Donjon donjon) {
        System.out.println("💀 " + getNom() + " est mort !");

    }


    @Override
    public void jouerTour(Donjon donjon) {
        System.out.println();
        Delai.attendre();
        Scanner scanner = new Scanner(System.in);
        int actions = 3;
        while (actions > 0) {
            donjon.afficherCarte();
            System.out.println("╠══════════════════════════ CHOIX DU JOUEUR ═══════════════════════════════════╣\n");
            System.out.println(afficherJoueur());
            System.out.println("\n🎮 Tour de " + getNom() + " - Actions restantes : " + actions);
            System.out.println("1. Se déplacer");
            System.out.println("2. Attaquer");
            System.out.println("3. S'équiper");
            System.out.println("4. Ramasser");
            System.out.println("5. Lancer un sort");
            System.out.println("0. Terminer le tour");
            System.out.print("Choisissez une action : ");


            if (!scanner.hasNextInt()) {
                System.out.println("❌ Veuillez entrer un nombre valide.");
                scanner.next();
                continue;
            }

            int choix = scanner.nextInt();
            System.out.println("---------------------------------------------------------");
            System.out.println();

            switch (choix) {
                case 1 -> {
                    donjon.afficherCarte();
                    System.out.println("Position actuelle : (" + getX() + ", " + getY() + ")");
                    System.out.println("Vitesse = " + vitesse / 3);

                    int x = demanderCoordonnee(scanner, "X");
                    int y = demanderCoordonnee(scanner, "Y");

                    if (donjon.mettreAPositionJoueur(this, x, y)) {
                        actions--;
                    } else {
                        System.out.println("❌ Déplacement impossible, case occupée ou position invalide.");
                    }

                }


                case 2 -> {
                    if (armeEquipe != null) {
                        List<Monstre> monstres = donjon.getMonstres();
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
                    Delai.attendre();
                    actions--;
                }
                case 4 -> {
                    System.out.println("Position actuelle : (" + getX() + ", " + getY() + ")");
                    donjon.verifierPositionJoueur(this);
                    actions--;

                }
                case 5 -> {
                    utiliserSort(donjon);
                    donjon.mettreAjourTous();
                    actions--;

                }
                case 0 -> actions = 0;
                default -> System.out.println("❌ Choix invalide.");
            }
        }
        System.out.println("╠════════════════════════════ FIN TOUR ══════════════════════════════════════╣\n");
    }

    public void utiliserSort(Donjon donjon) {
        if (classe.getSortsDisponibles().isEmpty()) {
            System.out.println("❌ Aucun sort disponible.");
            return;
        }

        System.out.println("Choisissez un sort à utiliser :");
        List<Sort> sorts = classe.getSortsDisponibles();
        for (int i = 0; i < sorts.size(); i++) {
            System.out.println((i + 1) + " - " + sorts.get(i).getNom());
        }

        Scanner scanner = new Scanner(System.in);
        int choix = scanner.nextInt() - 1;

        Sort sort = sorts.get(choix);
        sort.utiliserSort(this, donjon.getJoueurs(), donjon.getMonstres());
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