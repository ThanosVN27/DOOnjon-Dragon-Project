package tests.jeu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jeu.Donjon;

public class DonjonTest {
    @Test
    public void testInitialisationDonjon() {
        Donjon donjon = new Donjon(25,25);
        Assertions.assertNotNull(donjon, "Le donjon doit être initialisé");
        Assertions.assertTrue(donjon.getMonstres().isEmpty(), "Le donjon doit commencer sans monstres");
    }

    @Test
    public void testAjoutMonstre() {
        Donjon donjon = new Donjon(25,25);
        Assertions.assertFalse(donjon.getMonstres().isEmpty(), "Le donjon doit contenir des monstres après ajout");
        Assertions.assertEquals(1, donjon.getMonstres().size(), "Le donjon doit contenir un monstre");
    }
}
