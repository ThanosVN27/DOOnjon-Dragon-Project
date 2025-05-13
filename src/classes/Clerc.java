package classes;

import equipements.Arme;
import equipements.Armure;

public class Clerc extends Classe {
    public Clerc() {
        super("Clerc", 16);
        ajouterEquipement(Arme.MASSE_ARMES);
        ajouterEquipement(Armure.ARMURE_ECAILLES);
        ajouterEquipement(Arme.ARBALETE_LEGERE);
    }
}