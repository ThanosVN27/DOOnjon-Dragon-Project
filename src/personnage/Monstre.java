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
        // Implémentation de l'attaque
    }

    public String getNom() {
        return nom;
    }

    // Getters et setters...
}

