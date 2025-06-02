package tests.classes;

import org.junit.jupiter.api.Test;

import DonjonEtDragon.classes.Classe;
import DonjonEtDragon.classes.Guerrier;

import org.junit.jupiter.api.Assertions;

public class ClasseTest {
    @Test
    void testGetNom() {
        Classe guerrier = new Guerrier();
        Assertions.assertEquals("\u001B[33mGuerrier\u001B[0m", guerrier.getNomClasse());
    }

    @Test
    void testGetPointsDeVie() {
        Classe guerrier = new Guerrier();
        Assertions.assertEquals(20, guerrier.getPointsDeVie());
    }

    @Test
    void testGetEquipementDeBase() {
        Classe guerrier = new Guerrier();
        Assertions.assertTrue(guerrier.getEquipementDeBase().isEmpty());
    }
}
