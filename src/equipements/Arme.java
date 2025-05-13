package equipements;

public enum Arme {
    BATON("Bâton", "1d6", 1),
    MASSE_ARMES("Masse d'armes", "1d6", 1),
    EPEE_LONGUE("Épée longue", "1d8", 1),
    RAPIERE("Rapière", "1d8", 1),
    ARBALETE_LEGERE("Arbalète légère", "1d8", 16),
    FRONDE("Fronde", "1d4", 6),
    ARC_COURT("Arc court", "1d6", 16);

    private final String nom;
    private final String degats;
    private final int portee;

    Arme(String nom, String degats, int portee) {
        this.nom = nom;
        this.degats = degats;
        this.portee = portee;
    }


    public String getNom() {
        return nom;
    }

    public String getDegats() {
        return degats;
    }

    public int getPortee() {
        return portee;
    }

    @Override
    public String toString() {
        return nom + " (Dégâts: " + degats + ", Portée: " + portee + " cases)";
    }
}