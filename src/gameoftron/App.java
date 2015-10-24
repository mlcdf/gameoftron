/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftron;

import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.PApplet;

/**
 *
 * @author hikingyo
 */
public class App extends PApplet {

    private final int hauteurWindow = 720;
    private final int largeurWindow = 1280;
    int cA = color(246, 106, 53);
    int cB = color(24, 202, 230);
    int backgroundColor = color(5, 13, 16);
    Collider collider = Collider.getInstance(this);
    // Récupereur le nom du joueur perdant et sert de flag de fin de partie
    private String joueurPerdant;

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
        background(backgroundColor);

        // Initialisation et positionnement des joueurs
        joueurA = new Joueur("Bleu", cA, xA, yA, 'b', this);
        joueurB = new Joueur("Orange", cB, xB, yB, 'b', this);

        // Dessin des positions de départ
        joueurA.draw();
        joueurB.draw();
        
        // Tempo de 1s 
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Initialisation du flag
        joueurPerdant = "";
    }

    @Override
    public void draw() {
        controler();
        joueurA.draw();
        joueurB.draw();

        //Gestion de l'après-partie
        if (!joueurPerdant.equals("")) {
            
            textSize(24);
            textAlign(LEFT);
            fill(255, 255, 255);
            text(joueurPerdant + " a perdu", 10, 30);
            text("Voulez vous refaire une partie ? (O/n)", 10, 60);

            if (keyPressed == true) {
                switch (key) {
                    case 'o':
                        setup();
                        break;
                    case 'n':
                        exit();
                        break;
                    default: ;
                        break;
                }
            }
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
        if (collider.is_possible(joueurA.getDirection(), joueurA) && joueurPerdant.equals("")) {
            joueurA.move();
        } else {
            joueurPerdant = joueurA.getNom();
        }
        if (collider.is_possible(joueurB.getDirection(), joueurB) && joueurPerdant.equals("")) {
            joueurB.move();
        } else {
            joueurPerdant = joueurB.getNom();
        }
    }
}
