package personnage;

public class Monstre {
    private String nom;
    private int pointsDeVie;
    private int force;
    private int dexterite;
    private int vitesse;

    public Monstre(String nom) {
        this.nom = nom;
        this.pointsDeVie = 100; // Exemple
        this.force = 10;
        this.dexterite = 10;
        this.vitesse = 10;
    }

    public void attaquer(Personnage cible) {
        System.out.println(nom + " attaque " + cible.getNom());
        int degats = this.force;
        cible.setPointsDeVie(cible.getPointsDeVie() - degats);
        System.out.println(nom + " inflige " + force + " points de dégâts à " + cible.getNom());
        System.out.println(cible.getPointsDeVie());

    }

    public String getNom() {
        return nom;
    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }
    public void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }
    public void ToString() {
        System.out.println("Monstre  {" +
                " nom =  " + nom + "\u001B[0m" +
                ", pointsDeVie = " + pointsDeVie +
                ", force = " + force +
                ", dexterite = " + dexterite +
                ", vitesse = " + vitesse +
                '}');
    }
}

