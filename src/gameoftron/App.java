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
    private final int largeurWindow = 1260;
    int cA = color(255, 0, 0);
    int cB = color(0, 0, 255);
    Collider collider = Collider.getInstance(this);
    // Flag de fin de partie
    private char finPartie; // n = non, a = joueurA perdu, b = joueurB perdu

    Joueur joueurA, joueurB;

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
        // Initialisation du flag
        finPartie = 'n';

    }

    @Override
    public void draw() {
        controler();
        joueurA.draw();
        joueurB.draw();
        // Fin de partie ?
        if (finPartie != 'n') {
            System.out.println(" Le joueur " + finPartie + " a perdu !!");
        }
    }

    /**
     * Méthode de contrôle des mouvements des joueurs Si un joueur appuie sur
     * une touche qui lui est attribuée On modifie la direction de son avatar
     * Puis on teste la is_possible
     */
    private void controler() {

        // Déplacement du joueur joueurA
        if (key == 's') {
            joueurA.setDirection('b');
        }
        if (key == 'z') {
            joueurA.setDirection('h');
        }
        if (key == 'q') {
            joueurA.setDirection('g');
        }
        if (key == 'd') {
            joueurA.setDirection('d');
        }
        // Joueur B
        if (keyCode == DOWN) {
            joueurB.setDirection('b');
        }
        if (keyCode == UP) {
            joueurB.setDirection('h');
        }
        if (keyCode == LEFT) {
            joueurB.setDirection('g');
        }
        if (keyCode == RIGHT) {
            joueurB.setDirection('d');
        }
        // Gestion is_possible
        if (collider.is_possible(joueurA.getDirection(), joueurA)) {
            joueurA.move();
        } else {
            finPartie = 'a';
        }
        if (collider.is_possible(joueurB.getDirection(), joueurB)) {
            joueurB.move();
        } else {
            finPartie = 'b';
        }
    }
}
