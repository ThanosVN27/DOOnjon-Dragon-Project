package races;

public class Humain extends Race {

    public Humain() {
        super("Humain");
        setPointsDeVie(getPointsDeVie() + 2 );
        setDexterite(getForce() + 2);
        setVitesse(getVitesse() + 2);
        setForce(getForce() + 2);

    }
}