package tests.equipements;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import equipements.Equipement;
import equipements.Arme;
import equipements.TypeEquipement;
import equipements.Armure;

public class EquipementTest {
    @Test
    void testGetNom() {
        Arme arme = Arme.BATON;
        Assertions.assertEquals("Bâton", arme.getNom()); 
    }
}
