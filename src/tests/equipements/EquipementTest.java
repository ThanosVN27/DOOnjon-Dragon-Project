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

    @Test
    void testGetType() {
        Arme arme = Arme.BATON;
        Assertions.assertEquals(TypeEquipement.ARME, arme.getType(), "Le type de l'équipement doit être ARME");
    }

    @Test
    void testGetDegatsNumeriques() {
        Arme arme = Arme.BATON;
        int degats = arme.getDegatsNumeriques();
        Assertions.assertTrue(degats >= 1 && degats <= 4, "Dégâts doivent être entre 1 et 4 pour un bâton");
    }
}
