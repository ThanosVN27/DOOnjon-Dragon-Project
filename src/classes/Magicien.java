package classes;

import equipements.Arme;

public class Magicien extends Classe {
    public Magicien() {
        super("Magicien", 12);
        ajouterEquipement(Arme.BATON);
        ajouterEquipement(Arme.FRONDE);
    }
}