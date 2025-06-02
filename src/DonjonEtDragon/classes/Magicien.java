package DonjonEtDragon.classes;

import DonjonEtDragon.equipements.Arme;

public class Magicien extends Classe {
    public Magicien() {
        super("Magicien", 12);
        ajouterEquipement(Arme.BATON);
        ajouterEquipement(Arme.FRONDE);
    }
}