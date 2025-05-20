package equipements;
import personnage.Joueur;

public interface Equipement {
    String getNom();
    void appliquerEffets(Joueur p);
    TypeEquipement getType();
    String toString();
}

