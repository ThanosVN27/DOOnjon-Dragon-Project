package personnage;



public class Monstre implements Combattant {
    private final String nom;
    private final int numero;
    private final String attaque;
    private final int portee;
    private final String degats;
    private int pointsDeVie;
    private final int vitesse;
    private final int force;
    private final int dexterite;
    private final int classeArmure;
    private int initiative;
    private int x;
    private int y;

    public Monstre(String nom, int numero, String attaque, int portee, String degats, int pointsDeVie, int vitesse, int force, int dexterite, int classeArmure) {
        this.nom = nom;
        this.numero = numero;
        this.attaque = attaque;
        this.portee = portee;
        this.degats = degats;
        this.pointsDeVie = pointsDeVie;
        this.vitesse = vitesse;
        this.force = force;
        this.dexterite = dexterite;
        this.classeArmure = classeArmure;
        this.initiative = 0;
    }

    public void attaquer(Combattant cible) {
        System.out.println(nom + " #" + numero + " attaque " + cible.getNom() + " avec " + attaque);
        int degatsInfliges = lancerDes(degats);
        cible.setPointsDeVie(cible.getPointsDeVie() - degatsInfliges);
        System.out.println("Dégâts infligés : " + degatsInfliges);
        System.out.println("Points de vie restants de " + cible.getNom() + " : " + cible.getPointsDeVie());
    }

    private int lancerDes(String des) {
        String[] parts = des.split("d");
        int nombreDeDes = Integer.parseInt(parts[0]);
        int valeurDes = Integer.parseInt(parts[1]);
        int total = 0;
        for (int i = 0; i < nombreDeDes; i++) {
            total += (int) (Math.random() * valeurDes) + 1;
        }
        return total;
    }

    @Override
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    @Override
    public int getInitiative() {
        return initiative;
    }

    public String getNom() {
        return "\u001B[31m" + nom + "\u001B[0m";
    }

    public int getNumero() {
        return numero;
    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }

    @Override
    public void setPointsDeVie(int pv) {
        this.pointsDeVie = Math.max(0, pv); //valeur negatif
    }


    public int getVitesse() {
        return vitesse;
    }

    public int getForce() {
        return force;
    }

    public int getDexterite() {
        return dexterite;
    }

    public int getClasseArmure() {
        return classeArmure;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }



    public String afficherMonster() {
        return "\u001B[31m --[Monstre]--\u001B[0m = [" +
                "Espece = " + nom +
                ", Numéro = " + numero +
                ", Attaque = '" + attaque + '\'' +
                ", Portée = " + portee +
                ", Dégâts = '" + degats + '\'' +
                ", Points de Vie = " + pointsDeVie +
                ", Vitesse = " + vitesse +
                ", Force =" + force +
                ", Dextérité = " + dexterite +
                ", Classe d'Armure =" + classeArmure +
                ", Initiative = " + initiative +
                ']';
    }

    @Override
    public String afficherInfos() {
        return getNom() + " (PV: " + getPointsDeVie() +
                ",Position: " + getX() + "," + getY() + ")";
    }


    @Override
    public boolean estJoueur() {
        return false;
    }

    @Override
    public void deplacer(int x, int y) {
        this.x = x;
        this.y = y;
        System.out.println(nom + " se déplace vers la position(" + x + ", " + y + ")");
    }
}