package equipements;
import personnage.*;

public interface Equipement {
    
    String getNom();
    void appliquerEffets(Joueur p);
    TypeEquipement getType();
    @Override
    String toString();
    int getPortee();
    int getDegatsNumeriques();
    int getPositionX();
    int getPositionY();
    void setPositionX(int x);
    void setPositionY(int y);
}

