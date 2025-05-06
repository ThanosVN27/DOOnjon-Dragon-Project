package races;

import java.util.Scanner;

public abstract class Race {
    private final String nom;

    public Race(String nom) {
        this.nom = nom;
    }

    public String getNomRaces() {
        return "\u001B[32m" + nom + "\u001B[0m";
    }

    public abstract void appliquerPouvoir();



}
