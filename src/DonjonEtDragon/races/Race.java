package tests.races;

public abstract class Race {
    private final String nom;
    private int dexterite;
    private int vitesse;
    private int force;
    private int pointsDeVie;


    public Race(String nom) {
        this.nom = nom;
        this.dexterite = 3;
        this.vitesse = 3;
        this.force = 3;
        this.pointsDeVie = 0;

    }

    public int getDexterite() {
        return dexterite;
    }

    public void setDexterite(int dexterite) {
        this.dexterite = dexterite;
    }
    public int getVitesse() {
        return vitesse;
    }
    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }
    public int getForce() {
        return force;
    }
    public void setForce(int force) {
        this.force = force;
    }


    public int getPointsDeVie() {
        return pointsDeVie;
    }
    public void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }


    public String getNomRaces() {
        return "\u001B[32m" + nom + "\u001B[0m";
    }





}
