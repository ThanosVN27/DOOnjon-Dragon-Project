package classes;

import equipements.Equipement;

import java.util.ArrayList;
import java.util.List;

public abstract class Classe {
    private final String nom;
    private int pointsDeVie;
    private final List<Equipement> equipementDeBase;

    public Classe(String nom,int pointsDeVie) {
        this.nom = nom;
        this.pointsDeVie = pointsDeVie;
        this.equipementDeBase = new ArrayList<>();
    }

    public String getNomClasse() {
        return "\u001B[33m" + nom + "\u001B[0m"; // Yellow
    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }

    public List<Equipement> getEquipementDeBase() {
        return equipementDeBase;
    }

    protected void ajouterEquipement(Equipement equipement) {
        equipementDeBase.add(equipement);
    }


}