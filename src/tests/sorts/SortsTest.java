package tests.sorts;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import sorts.ArmeMagique;
import sorts.Guerison;
import sorts.BoogieWoogie;

public class SortsTest {

    @Test
    public void testCreationArmeMagique() {
        ArmeMagique sort = new ArmeMagique();
        Assertions.assertNotNull(sort);
        Assertions.assertTrue(sort.getNom().toLowerCase().contains("arme"));
    }

    @Test
    public void testCreationGuerison() {
        Guerison sort = new Guerison();
        Assertions.assertNotNull(sort);
        Assertions.assertTrue(sort.getNom().toLowerCase().contains("guerison"));
    }

    @Test
    public void testCreationBoogieWoogie() {
        BoogieWoogie sort = new BoogieWoogie();
        Assertions.assertNotNull(sort);
        Assertions.assertTrue(sort.getNom().toLowerCase().contains("boogie"));
    }
}
