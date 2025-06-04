package jeu;

public class Delai {
    private static final int DELAI = 2000; // Délai en millisecondes

    public static void attendre() {
        try {
            Thread.sleep(DELAI);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restauration de l'état d'interruption
            System.err.println("Le délai a été interrompu : " + e.getMessage());
        }
    }
}
