package races;

public class Nain extends Race{
    public Nain() {
        super("Nain");
    }

    @Override
    public void appliquerPouvoir() {
        System.out.println("Le pouvoir du nain est d'avoir une résistance accrue aux poisons et un bonus de +2 en Constitution !");
    }
}
