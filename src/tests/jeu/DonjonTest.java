package tests.jeu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jeu.Donjon;
import equipements.Equipement;
import equipements.Arme;
import personnage.Joueur;

public class DonjonTest {
    @Test
    public void testInitialisationDonjon() {
        Donjon donjon = new Donjon(25,25);
        Assertions.assertNotNull(donjon, "Le donjon doit être initialisé");
    }

    @Test
    public void testGetHauteurEtLargeur() {
        Donjon donjon = new Donjon(10, 15);
        Assertions.assertEquals(10, donjon.getHauteur());
        Assertions.assertEquals(15, donjon.getLargeur());
    }

    @Test
    public void testPlacerEquipement() {
        Donjon donjon = new Donjon(10, 10);
        Equipement equipement = Arme.BATON;
        boolean result = donjon.placerEquipement(equipement, 1, 1);
        Assertions.assertTrue(result, "L'équipement doit être placé sur une case vide");
        Assertions.assertEquals(equipement, donjon.getEquipementAtPosition(1, 1));
    }

    @Test
    public void testPlacerJoueur() {
        Donjon donjon = new Donjon(10, 10);
        Joueur joueur = new Joueur("Test");
        boolean result = donjon.placerJoueur(joueur, 1, 1, 1);
        Assertions.assertTrue(result, "Le joueur doit être placé sur une case vide");
        Assertions.assertTrue(donjon.getJoueurs().contains(joueur));
    }
}
