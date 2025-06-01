package equipements;
import personnage.Joueur;

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
}
