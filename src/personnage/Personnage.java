package personnage;

import jeu.Donjon;

public abstract class Personnage {
    protected String nom;
    protected int pointsDeVie;
    protected int force;
    protected int dexterite;
    protected int vitesse;
    protected int initiative;
    protected int x; // Position en X
    protected int y; // Position en Y
    protected int classeArmure;


    public Personnage(String nom, int pointsDeVie, int force, int dexterite, int vitesse) {
        this.nom = nom;
        this.pointsDeVie = pointsDeVie;
        this.force = force;
        this.dexterite = dexterite;
        this.vitesse = vitesse;
        this.initiative = 0;
        this.x = 0;
        this.y = 0;
    }

    public abstract void jouerTour(Donjon donjon);
    public abstract void attaquer(Personnage cible);
    public abstract void seDeplacer(int x, int y);
    public abstract boolean estMort();


    public String getNom() {
        return nom;
    }

    public int getClasseArmure() {
        return classeArmure;
    }

    public void mourir(Donjon donjon) {
        System.out.println("💀 " + getNom() + " est mort !");

    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setPointsDeVie(int pv) {
        this.pointsDeVie = Math.max(0, pv);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int getVitesse() {
        return vitesse;
    }
}
