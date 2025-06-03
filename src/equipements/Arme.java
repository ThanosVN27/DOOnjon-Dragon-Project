package equipements;
import java.util.random.RandomGenerator;
import personnage.*;

public enum Arme implements Equipement {
    BATON("Bâton", "1d6", 1, false),
    MASSE_ARMES("Masse d'armes", "1d6", 1, false),
    EPEE_LONGUE("Épée longue", "1d8", 1, true),
    RAPIERE("Rapière", "1d8", 1, true),
    ARBALETE_LEGERE("Arbalète légère", "1d8", 16, false),
    FRONDE("Fronde", "1d4", 6, false),
    ARC_COURT("Arc court", "1d6", 16, false);

    private final String nom;
    private final String degats;
    private final int portee;
    private final boolean estArmeDeGuerre;
    private int positionX = 0;
    private int positionY = 0;

    Arme(String nom, String degats, int portee, boolean estArmeDeGuerre) {
        this.nom = nom;
        this.degats = degats;
        this.portee = portee;
        this.estArmeDeGuerre = estArmeDeGuerre;
    }

    @Override
    public String getNom() {
        return nom;
    }

    public int getDegatsNumeriques() {
        if (degats.equals("1d4")) return RandomGenerator.getDefault().nextInt(1, 5); // 1d4
        if (degats.equals("1d6")) return RandomGenerator.getDefault().nextInt(1, 7); // 1d6
        if (degats.equals("1d8")) return RandomGenerator.getDefault().nextInt(1, 9); // 1d8
        return 0;
    }

    public String getDegats() {
        return degats;
    }

    public int getPortee() {
        return portee;
    }

    public boolean estArmeDeGuerre() {
        return estArmeDeGuerre;
    }

    @Override
    public TypeEquipement getType() {
        return TypeEquipement.ARME;
    }

    @Override
    public void appliquerEffets(Joueur p) {
        if (estArmeDeGuerre) {
            p.modifierForce(4);
            p.modifierVitesse(-2);
            System.out.println("Force +4, Vitesse -2 (arme de guerre)");
        }
    }

    @Override
    public String toString() {
        return nom + " (Dégâts : " + degats + ", Portée : " + portee + ")";
    }

    public int getPositionX() {
        return positionX; // Position X par défaut
    }
    public int getPositionY() {
        return positionY; // Position Y par défaut
    }

    public void setPositionX(int x) {
        this.positionX = x;
    }

    public void setPositionY(int y) {
        this.positionY = y;
    }
}
