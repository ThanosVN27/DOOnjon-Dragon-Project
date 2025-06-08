package classes;

import equipements.Equipement;
import sorts.Sort;

import java.util.ArrayList;
import java.util.List;

public abstract class Classe {
    private final String nom;
    private final int pointsDeVie;
    private final List<Equipement> equipementDeBase;
    private final List<Sort> sortsDisponibles = new ArrayList<>();

    public Classe(String nom,int pointsDeVie) {
        this.nom = nom;
        this.pointsDeVie = pointsDeVie;
        this.equipementDeBase = new ArrayList<>();


    }

    public String getNomClasse() {
        return "\u001B[33m" + nom + "\u001B[0m";
    }

    public List<Sort> getSortsDisponibles() {
        return sortsDisponibles;
    }

    public void ajouterSort(Classe classe, Sort sort) {
        if (classe.getNomClasse().equals(this.getNomClasse())) {
            sortsDisponibles.add(sort);
        } else {
            System.out.println("Le sort ne peut pas être ajouté à cette classe.");
        }
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