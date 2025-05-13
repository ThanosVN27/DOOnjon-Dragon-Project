package equipements;

public enum Armure {
    ARMURE_ECAILLES("Armure d'écailles", 9),
    DEMI_PLATE("Demi-plate", 10),
    COTTE_MAILLES("Cotte de mailles", 11),
    HARNOIS("Harnois", 12);

    private final String nom;
    private final int classeArmure;

    Armure(String nom, int classeArmure) {
        this.nom = nom;
        this.classeArmure = classeArmure;
    }


    public String getNom() {
        return nom;
    }

    public int getClasseArmure() {
        return classeArmure;
    }

    @Override
    public String toString() {
        return nom + " (Classe d'Armure: " + classeArmure + ")";
    }
}