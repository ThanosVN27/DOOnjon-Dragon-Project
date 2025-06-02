package DonjonEtDragon.classes;

import DonjonEtDragon.equipements.Arme;

public class Roublard extends Classe {
    public Roublard() {
        super("Roublard", 16);
        ajouterEquipement(Arme.RAPIERE);
        ajouterEquipement(Arme.ARC_COURT);
    }
}