package sorts;
import personnage.Personnage;
import jeu.Donjon;


public interface Sort {
    String getNom();
    void utiliserSort(Personnage lanceur, Donjon donjon);
}

