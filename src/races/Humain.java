package races;

public class Humain extends Race {
    public Humain() {
        super("Humain");
    }

    public void appliquerPouvoir() {
        System.out.println("Le pouvoir de l'humain est d'avoir un bonus de +1 à toutes les caractéristiques !");
    }
}
