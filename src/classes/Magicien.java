package classes;

import equipements.Arme;
import sorts.ArmeMagique;
import sorts.BoogieWoogie;
import sorts.Guerison;

public class Magicien extends Classe {
    public Magicien() {
        super("Magicien", 12);
        ajouterEquipement(Arme.BATON);
        ajouterEquipement(Arme.FRONDE);
        ajouterSort(this, new ArmeMagique());
        ajouterSort(this, new Guerison());
        ajouterSort(this,new BoogieWoogie());
    }
}