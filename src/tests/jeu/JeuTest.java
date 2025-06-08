package tests.jeu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jeu.Jeu;
import personnage.MaitreDuJeu;
import jeu.Donjon;

public class JeuTest {

    @Test
    public void testInitialisationJeu() {
        Jeu jeu = new Jeu();
        Assertions.assertNotNull(jeu, "Le jeu doit être initialisé");
    }

    @Test
    public void testMaitreDuJeuPresent() {
        Jeu jeu = new Jeu();
        Assertions.assertNotNull(jeu.getMaitreDuJeu(), "Le maître du jeu doit être présent");
    }

    @Test
    public void testDonjonPresent() {
        Jeu jeu = new Jeu();
        MaitreDuJeu mj = jeu.getMaitreDuJeu();
        Assertions.assertNotNull(mj.getDonjon(), "Le donjon doit être présent après initialisation du maître du jeu");
    }
}
