package races;

public class Halfelin extends Race{
    public Halfelin() {
        super("Halfelin>D");
    }


    public void appliquerPouvoir() {
        System.out.println("Le pouvoir du halfelin est d'avoir un bonus de +2 en Dextérité et un bonus de +1 en Charisme !");
    }
}
