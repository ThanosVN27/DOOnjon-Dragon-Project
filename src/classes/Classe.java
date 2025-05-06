package classes;

import personnage.Personnage;

import java.util.Scanner;

public abstract class Classe {
    private final String nom;

    public Classe(String nom) {
        this.nom = nom;
    }

    public String getNomClasse() {
        return "\u001B[33m" + nom + "\u001B[0m"; // Yellow
    }

    public abstract void appliquerClasse();





}
