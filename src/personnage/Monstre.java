package personnage;

import jeu.Delai;
import jeu.Donjon;


public class Monstre extends Personnage{
    private final int numero;
    private final String attaque;
    private final int portee;
    private final String degats;
    private final int classeArmure;

    public Monstre(String nom, int numero, String attaque, int portee, String degats,
                   int pointsDeVie, int vitesse, int force, int dexterite, int classeArmure) {
        super(nom, pointsDeVie, force, dexterite, vitesse);
        this.numero = numero;
        this.attaque = attaque;
        this.portee = portee;
        this.degats = degats;
        this.classeArmure = classeArmure;
    }
    public String getNom() {
        return "\u001B[31m" + nom + "\u001B[0m";
    }

    public int getNumero() {
        return numero;
    }


    public void setPointsDeVie(int pv) {
        this.pointsDeVie = Math.max(0, pv); //valeur negatif
    }

    public int getClasseArmure() {
        return classeArmure;
    }

    @Override
    public void attaquer(Personnage cible) {

        System.out.println("\n╠════════════════════════════ ATTAQUE ═════════════════════════════════╣");
        System.out.println(" " + getNom() + " #" + numero + " tente d'attaquer " + cible.getNom());

        int distance = Math.abs(this.x - cible.getX()) + Math.abs(this.y - cible.getY());
        if (distance > portee) {
            System.out.println(" " + nom + " #" + numero + " est trop loin pour attaquer " + cible.getNom());
            return;
        }

        System.out.println(" " + nom + " #" + numero + " attaque " + cible.getNom() + " avec " + attaque);

        int jetAttaque = (int)(Math.random() * 20) + 1; // 1d20
        int mod = portee == 1 ? force : dexterite; // portee==1 => corps-à-corps
        int total = jetAttaque + mod;

        System.out.println(" Jet d'attaque : " + jetAttaque + " + " + mod + " = " + total);
        if (total >= cible.getClasseArmure()) {
            int degatsInfliges = lancerDes(degats);
            cible.setPointsDeVie(cible.getPointsDeVie() - degatsInfliges);
            System.out.println(" Attaque réussie ! Dégâts infligés : " + degatsInfliges);
            System.out.println(" " + cible.getNom() + " a maintenant " + cible.getPointsDeVie() + " points de vie.");
            if (cible.getPointsDeVie() <= 0) {
                System.out.println("💀 " + cible.getNom() + " est mort !");
            }
        } else {
            System.out.println(" Attaque échouée. Classe d'armure de la cible : " + cible.getClasseArmure());
        }


        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝\n");
    }

    private int lancerDes(String des) {
        String[] parts = des.split("d");
        int nombreDeDes = Integer.parseInt(parts[0]);
        int valeurDes = Integer.parseInt(parts[1]);
        int total = 0;
        for (int i = 0; i < nombreDeDes; i++) {
            total += (int) (Math.random() * valeurDes) + 1;
        }
        return total;
    }


    public String afficherMonster() {
        return "\u001B[31m --[Monstre]--\u001B[0m = [" +
                "Espece = " + nom +
                ", Numéro = " + numero +
                ", Attaque = '" + attaque + '\'' +
                ", Portée = " + portee +
                ", Dégâts = '" + degats + '\'' +
                ", Points de Vie = " + pointsDeVie +
                ", Vitesse = " + vitesse +
                ", Force =" + force +
                ", Dextérité = " + dexterite +
                ", Classe d'Armure =" + classeArmure +
                ", Initiative = " + initiative +
                ']';
    }

    public String afficherInfos() {
        return getNom() + " (PV: " + getPointsDeVie() +
                ",Position: " + getX() + "," + getY() + ")";
    }


    public void seDeplacer(int x, int y) {
        // Logique de déplacement pour le monstre
        System.out.println("\n╠════════════════════════════ DÉPLACEMENT ═══════════════════════════════╣");
        System.out.println(getNom() + " se déplace vers la position (" + x + ", " + y + ")");
        this.x = x;
        this.y = y;
        System.out.println("Nouvelle position de " + getNom() + " : (" + this.x + ", " + this.y + ")");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════╝\n");
    }

    @Override
    public void jouerTour(Donjon donjon) {
        Delai.attendre();
        System.out.println("\n╠══════════════════════════ TOUR DU MONSTRE ═══════════════════════════╣");
        System.out.println(getNom() + " joue son tour.");

        int action = 3;

        while (action > 0) {
            Joueur cible = trouverCibleAPortee(donjon);

            if (cible != null) {
                attaquer(cible);
                Delai.attendre();
            } else {
                seDeplacerVersJoueur(donjon);
                Delai.attendre();
            }
            action--;
        }

        System.out.println("╚═══════════════════════════ FIN DU TOUR ══════════════════════════════╝\n");
    }

    private Joueur trouverCibleAPortee(Donjon donjon) {
        for (Joueur joueur : donjon.getJoueurs()) {
            int distance = Math.abs(this.x - joueur.getX()) + Math.abs(this.y - joueur.getY());
            if (distance <= portee) {
                return joueur;
            }
        }
        return null;
    }

    private void seDeplacerVersJoueur(Donjon donjon) {
        Joueur cible = donjon.getJoueurLePlusProche(this);
        if (cible == null) return;

        int distanceMax = vitesse / 3;
        int dx = Integer.compare(cible.getX(), x);
        int dy = Integer.compare(cible.getY(), y);

        int nouveauX = x;
        int nouveauY = y;
        for (int i = 0; i < distanceMax; i++) {
            int tentativeX = nouveauX + dx;
            int tentativeY = nouveauY + dy;
            if (donjon.estCaseLibre(tentativeX, tentativeY)) {
                nouveauX = tentativeX;
                nouveauY = tentativeY;
            } else {
                break;
            }
        }

        if (nouveauX != x || nouveauY != y) {
            donjon.mettreAJourMonstre(this, x, y, nouveauX, nouveauY);
            seDeplacer(nouveauX, nouveauY);
        }
    }


    @Override
    public boolean estMort() {
        return pointsDeVie <= 0;
    }

    @Override
    public void mourir(Donjon donjon) {
        System.out.println("💀 " + getNom() + " est mort !");
        donjon.supprimerMonstre(this);
    }

}