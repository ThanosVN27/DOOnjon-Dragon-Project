package equipements;
import personnage.*;

public enum Armure implements Equipement {
    ARMURE_ECAILLES("Armure d'écailles", 9, false),
    DEMI_PLATE("Demi-plate", 10, false),
    COTTE_MAILLES("Cotte de mailles", 11, true),
    HARNOIS("Harnois", 12, true);

    private final String nom;
    private final int classeArmure;
    private final boolean estArmureLourde;
    private int positionX;
    private int positionY;



    Armure(String nom, int classeArmure, boolean estArmureLourde) {
        this.nom = nom;
        this.classeArmure = classeArmure;
        this.estArmureLourde = estArmureLourde;
        this.positionX = 0; // Position par défaut
        this.positionY = 0; // Position par défaut
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
    public void retirerEffets(Joueur p) {
        if (estArmureLourde) {
            p.modifierVitesse(4);
            System.out.println("Vitesse +4 (effets retirés de l'armure lourde)");
        }
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



    public int getPositionX() {
        return positionX;
    }
    public int getPositionY() {
        return positionY;
    }

    public void setPositionX(int x) {
        this.positionX = x;
    }

    public void setPositionY(int y) {
        this.positionY = y;
    }

    public int getPortee() {
        return 0; // Les armures n'ont pas de portée
    }
    public int getDegatsNumeriques() {
        return 0;
    }
}
