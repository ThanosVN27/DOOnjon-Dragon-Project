package personnage;

public interface Combattant {
    String getNom();
    int getPointsDeVie();
    void setPointsDeVie(int pv);
    int getX();
    int getY();
    int getInitiative();
    void setInitiative(int initiative);
    String afficherInfos();
    void attaquer(Combattant cible);
    boolean estJoueur();
    void deplacer(int x, int y);
}