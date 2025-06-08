package sorts;
import personnage.Joueur;
import personnage.Monstre;
import personnage.Personnage;
import jeu.Donjon;

import java.util.List;

public interface Sort {
    String getNom();
    void utiliserSort(Joueur lanceur, List<Joueur> cibles, List<Monstre> monstres);
}

