/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftron;

/**
 *
 * @author hikingyo
 */
class Joueur {

    private String nom;
    private float x, y; // Coordonnées grahique
    private int posX, posY; // Coordonnées dans le monde
    private int couleur;
    App window;

    public Joueur(String nom,int color, float x, float y, App window) {
        this.nom = nom;
        this.window = window;
        this.x = x;
        this.y = y;
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

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    public void move(char direction){
        switch(direction){
            case 'b' :
                this.y += window.taille;
                break;
            case 'h' :
                this.y -= window.taille;
                break;
            case 'g' :
                this.x -= window.taille;
                break;
            case 'd' :
                this.x += window.taille;
                break;
            default :
                break;
        }
    }
    
    public void draw(){
        window.fill(this.couleur);
        window.noStroke();
        window.rect(this.x, this.y, window.taille, window.taille);
    }

}
