package jeu;

import personnage.Personnage;

import java.util.Scanner;

public class Jeu {
    private Personnage joueur;


    public void lancerJeu() {
        Scanner scanner = new Scanner(System.in);


        System.out.println("Entrez le nom de votre personnage :");
        String nom = scanner.nextLine();


        joueur = new Personnage(nom);


        System.out.println("Votre personnage a été créé avec succès !");
        System.out.println(joueur.toString());
    }
}