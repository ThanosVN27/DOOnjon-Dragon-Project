package races;

public class Humain extends Race {
    private int pointsDeVie;

    public Humain() {
        super("Humain");
        setPointsDeVie(getPointsDeVie() + 2 );
        setDexterite(getForce() + 2);
        setVitesse(getVitesse() + 2);
        setForce(getForce() + 2);
        setInitiative(getInitiative() + 2);
    }
}