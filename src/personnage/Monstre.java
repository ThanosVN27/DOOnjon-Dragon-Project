package personnage;

import jeu.Donjon;
import personnage.Personnage;

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

    @Override
    public void attaquer(Personnage cible) {
        System.out.println(nom + " #" + numero + " attaque " + cible.getNom() + " avec " + attaque);
        int degatsInfliges = lancerDes(degats);
        cible.setPointsDeVie(cible.getPointsDeVie() - degatsInfliges);
        System.out.println("Dégâts infligés : " + degatsInfliges);
        System.out.println("Points de vie restants de " + cible.getNom() + " : " + cible.getPointsDeVie());
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
    
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int getInitiative() {
        return initiative;
    }

    public String getNom() {
        return "\u001B[31m" + nom + "\u001B[0m";
    }

    public int getNumero() {
        return numero;
    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }
    
    public void setPointsDeVie(int pv) {
        this.pointsDeVie = Math.max(0, pv); //valeur negatif
    }

    public int getVitesse() {
        return vitesse;
    }

    public int getForce() {
        return force;
    }

    public int getDexterite() {
        return dexterite;
    }

    public int getClasseArmure() {
        return classeArmure;
    }

    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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

    public boolean estJoueur() {
        return false;
    }

    public void deplacer(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println(nom + " se déplace vers la position(" + x + ", " + y + ")");
    }



    public void seDeplacer(int x, int y) {
        // Logique de déplacement pour le monstre
        System.out.println(nom + " se déplace vers la position (" + x + ", " + y + ")");
        this.x = x;
        this.y = y;
    }

    @Override
    public void jouerTour(Donjon donjon) {
        // Logique de jeu pour le monstre, par exemple attaquer un joueur ou se déplacer
        System.out.println(nom + " joue son tour.");
        // Exemple d'attaque aléatoire sur un joueur
        if (donjon.getJoueurs().size() > 0) {
            Joueur cible = donjon.getJoueurs().get((int) (Math.random() * donjon.getJoueurs().size()));
            attaquer(cible);
        }
    }
}