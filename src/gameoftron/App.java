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

    Joueur a, b;
    Trajectoire Ta, Tb;
    int xA = largeurWindow / 3;
    int yA = hauteurWindow / 2;
    int xB = 2 * largeurWindow / 3;
    int yB = hauteurWindow / 2;
    int taille = 10; // taille d'une case

    @Override
    public void settings() {
        size(largeurWindow, hauteurWindow);
    }

    @Override
    public void setup() {
        frameRate(60); // définition du framerate

        int cA = color(255, 0, 0);
        int cB = color(0, 0, 255);
        // Initialisation des trajectoires
        Trajectoire trajectoireA = new Trajectoire(cA);
        Trajectoire trajectoireB = new Trajectoire(cB);
        // Création des cases de départ des joeurs
        Case caseDepartA = new Case(xA, xY);
        Case caseDepartB = new Case(xB, yB);
        // Initialisation des joueurs
        a = new Joueur("Bob", caseDepartA, this);

    }

    public void controler() {

    }

    @Override
    public void draw() {

    }

}
