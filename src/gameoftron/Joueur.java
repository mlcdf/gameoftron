package gameoftron;

/**
 *
 * @author Hikingyo & MLCDF
 */
class Joueur {

    private String nom;
    private float x, y; // Coordonnées grahique
    private int couleur;
    private char direction;
    App window;

    public Joueur(String nom, int color, float x, float y, char direction, App window) {
        this.nom = nom;
        this.window = window;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.couleur = color;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getCouleur() {
        return couleur;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public char getDirection() {
        return this.direction;
    }
    /**
     * Gestion du déplacement du joueur en fonction de la direction
     * et de sa taille
     */
    public void move() {
        switch (this.direction) {
            case 'b':
                this.y += window.taille;
                break;
            case 'h':
                this.y -= window.taille;
                break;
            case 'g':
                this.x -= window.taille;
                break;
            case 'd':
                this.x += window.taille;
                break;
            default:
                break;
        }
    }

    public void draw() {
        window.fill(this.couleur);
        window.noStroke();
        window.rect(this.x, this.y, window.taille, window.taille);
    }

}
