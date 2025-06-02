package DonjonEtDragon.equipements;
import DonjonEtDragon.personnage.Joueur;

public enum Armure implements Equipement {
    ARMURE_ECAILLES("Armure d'écailles", 9, false),
    DEMI_PLATE("Demi-plate", 10, false),
    COTTE_MAILLES("Cotte de mailles", 11, true),
    HARNOIS("Harnois", 12, true);

    private final String nom;
    private final int classeArmure;
    private final boolean estArmureLourde;

    Armure(String nom, int classeArmure, boolean estArmureLourde) {
        this.nom = nom;
        this.classeArmure = classeArmure;
        this.estArmureLourde = estArmureLourde;
    }

    @Override
    public String getNom() {
        return nom;
    }

    public int getClasseArmure() {
        return classeArmure;
    }

    public boolean estArmureLourde() {
        return estArmureLourde;
    }

    @Override
    public TypeEquipement getType() {
        return TypeEquipement.ARMURE;
    }

    @Override
    public void appliquerEffets(Joueur p) {
        if (estArmureLourde) {
            p.modifierVitesse(-4);
            System.out.println("Vitesse -4 (armure lourde)");
        }
    }


    
    @Override
    public String toString() {
        return nom + " (Classe d'armure : " + classeArmure + ")";
    }

    public int getDegatsNumeriques() {
        return 0; // Les armures n'ont pas de dégâts associés
    }
    public int getPortee() {
        return 0; // Les armures n'ont pas de portée
    }
}
