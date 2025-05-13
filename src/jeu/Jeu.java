package jeu;

import personnage.Monstre;
import personnage.Personnage;

import java.util.Scanner;

public class Jeu {



    public void lancerJeu() {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Entrez le nom de votre personnage :");
        String nom = scanner.nextLine();


        Personnage joueur = new Personnage(nom);
        Monstre monstre = new Monstre("Dragon", 1, "Souffle de feu", 5, "2d6", 100, 10, 20, 15, 18);



        System.out.println("Votre personnage a été créé avec succès !");
        System.out.println(joueur.toString());



        System.out.println(joueur.toString());
        joueur.attaquer(monstre);
        monstre.attaquer(joueur);

        System.out.println(monstre);

    }
}