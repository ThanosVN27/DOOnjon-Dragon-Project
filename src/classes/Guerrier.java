package classes;

import equipements.Arme;
import equipements.Armure;

public class Guerrier extends Classe {
    public Guerrier() {
        super("Guerrier", 20);
        ajouterEquipement(Armure.COTTE_MAILLES);
        ajouterEquipement(Arme.EPEE_LONGUE);
        ajouterEquipement(Arme.ARBALETE_LEGERE);
    }
}