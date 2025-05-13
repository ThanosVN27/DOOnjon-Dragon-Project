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
        Monstre dragon = new Monstre("Dragon");



        System.out.println("Votre personnage a été créé avec succès !");
        System.out.println(joueur.toString());

        int choix;
        Scanner scanner1 = new Scanner(System.in);
        choix = scanner1.nextInt();

        joueur.equiperEquipement(choix);

        System.out.println(joueur.toString());


    }
}