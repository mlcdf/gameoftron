/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftron;

import processing.core.PApplet;

/**
 *
 * @author hikingyo
 */
public class App extends PApplet {

    private final int hauteurWindow = 720;
    private final int largeurWindow = 1280;
    int cA = color(255, 0, 0);
    int cB = color(0, 0, 255);

    Joueur joueurA, joueurB;
    char directionA, directionB;
    
    // Coordonnée de départ des joueurs
    float xA = largeurWindow / 3;
    float yA = hauteurWindow / 2;
    float xB = 2 * largeurWindow / 3;
    float yB = hauteurWindow / 2;

    float taille = 10; // taille d'une case

    @Override
    public void settings() {
        size(largeurWindow, hauteurWindow);
    }

    @Override
    public void setup() {
        frameRate(60); // définition du framerate
        background(0);
        // Initialisation des joueurs
        joueurA = new Joueur("Bob", cA, xA, yA, this);
        joueurB = new Joueur("Jean", cB, xB, yB, this);

    }

    @Override
    public void draw() {
        controler();
        joueurA.draw();
        joueurB.draw();
    }

    private void controler() {
        
        // Déplacement du joueur joueurA
        if (key == 's') {
            if (!collision('b', joueurA)) {
                directionA = 'b';
            }
        }
        if (key == 'z') {
            if (!collision('h', joueurA)) {
                directionA = 'h';
            }
        }
        if (key == 'q') {
            if (!collision('g', joueurA)) {
                directionA = 'g';
            }
        }
        if (key == 'd') {
            if (!collision('d', joueurA)) {
                directionA = 'd';
            }
        }
        // Joueur B
        if (keyCode == DOWN) {
            if (!collision('b', joueurB)) {
                directionB = 'b';
            }
        }
        if (keyCode == UP) {
            if (!collision('h', joueurB)) {
                directionB = 'h';
            }
        }
        if (keyCode == LEFT) {
            if (!collision('g', joueurB)) {
                directionB = 'g';
            }
        }
        if (keyCode == RIGHT) {
            if (!collision('d', joueurB)) {
                directionB = 'd';
            }
        }
        // Déplacement des joueurs
        joueurA.move(directionA);
        joueurB.move(directionB);
    }
    
    private Boolean collision(char direction, Joueur j){
        Boolean collision = true;
        // Si le pixel est noir(background), il n'y a pas colision
        switch (direction){
            case 'b': // Vers le bas
                if(get((int)j.getX(), (int)j.getY()+ (int)taille + 1) == color(0)){
                    collision = false;
                }
                System.out.println("collider" + collision);
                break;
                
            case 'h' : // Vers le haut
                if(get((int)j.getX(), (int)j.getY()-1) == color(0)){
                    collision = false;
                }
                break;
                
            case 'g' : // Vers la gauche
                if(get((int)j.getX()- 1, (int)j.getY()) == color(0)){
                    collision = false;
                }
                break;
                
            case 'd' : // Vers la droite
                if(get((int)j.getX()+ (int)taille + 1, (int)j.getY()) == color(0)){
                    collision = false;
                }
                break;
            default :
                collision = true;
        }
        return collision;        
    }

}
