package gameoftron;

import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.PApplet;
import processing.core.PFont;

/**
 *
 * @author @Hikingyo & @mlcdf
 * 
 * Built with the Processing library for Java
 * 
 */

public class App extends PApplet {

    private final int hauteurWindow = 720;
    private final int largeurWindow = 1280;

    int couleurOrange = color(246, 106, 53); // définition de la couleur Orange
    int couleurBleu = color(24, 202, 230); // définition de la couleur Bleu

    int backgroundColor = color(5, 13, 16); // définition de la couleur du background

    Collider collider = Collider.getInstance(this);

    // Flag de fin de partie :d = début partie, a = joueur A perdant, b = joueur B perdant, sinon n = continuer
    private char etatPartie;

    // Nom du joueur perdant
    private String nomPerdant;

    Joueur joueurA, joueurB;

    // Coordonnée de départ des joueurs
    float xA = largeurWindow / 3;
    float yA = hauteurWindow / 2;
    float xB = 2 * largeurWindow / 3;
    float yB = hauteurWindow / 2;

    float taille = 10; // taille d'un joueur

    long timeSet; // Temps de référence pour le chrono

    private PFont fontTron;
    private PFont fontDefault;
    private float fontSize;

    @Override
    public void settings() {
        size(largeurWindow, hauteurWindow);
    }

    @Override
    public void setup() {
        //Chargement de la police de caractères
        fontTron = createFont("TRON", 24);
        fontDefault = createFont("Roboto-Regular", 24);
        // Taille de la police
        fontSize = 120;

        // Initialisation du flag
        etatPartie = 's';

        // Initialisation du chrono
        timeSet = System.currentTimeMillis();

        frameRate(60); // définition du framerate

        background(backgroundColor);// Affichage du background

        // Initialisation et positionnement des joueurs
        joueurA = new Joueur("Orange", couleurOrange, xA, yA, 'b', this);
        joueurB = new Joueur("Bleu", couleurBleu, xB, yB, 'b', this);

        // Dessin des positions de départ
        joueurA.draw();
        joueurB.draw();

    }

    @Override
    public void draw() {

        //Affichage de l'écran d'accueil
        if (etatPartie == 's') {
            ecran_accueil();
        } else if (etatPartie == 'd') {

            background(backgroundColor);
            joueurA.draw();
            joueurB.draw();
            chrono();
        } else if (etatPartie == 'n') {
            controller();
            joueurA.draw();
            joueurB.draw();
        } //Gestion de fin de partie
        else {
            finPartie(etatPartie);
        }
    }

    /**
     * Méthode de contrôle des mouvements des joueurs Si un joueur appuie sur
     * une touche qui lui est attribuée On modifie la direction de son avatar
     * Puis on teste la is_possible
     */
    private void controller() {

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
            etatPartie = 'a';
        }
        if (collider.is_possible(joueurB.getDirection(), joueurB)) {
            joueurB.move();
        } else {
            etatPartie = 'b';
        }
    }

    /**
     * Gestion de fin partie Affichage du nom du perdant Demande de nouvelle
     * partie
     *
     * @param finPartie <char> le flag qui désigne le perdant (a || b)
     */
    private void finPartie(char finPartie) {
        // On récupère le nom du perdant
        if (finPartie == 'a') {
            nomPerdant = joueurA.getNom();
        } else if (finPartie == 'b') {
            nomPerdant = joueurB.getNom();
        } else {
            System.out.println("Erreur dans le flag de fin de partie" + finPartie);
            System.exit(0);
        }

        fill(255, 255, 255, 40);
        textFont(fontDefault, 18);
        textAlign(LEFT);
        text(nomPerdant + " a perdu", 10, 30);
        text("Voulez vous refaire une partie ? (O/N)", 10, 60);

        if (keyPressed == true) {
            switch (key) {
                case 'o':
                    setup();
                    etatPartie = 'd';
                    break;
                case 'n':
                    etatPartie = 's';
                    break;
                default: ;
                    break;
            }
        }
    }

    private void chrono() {

        // Positionnement du chrono
        float chronoPosX = largeurWindow / 2 - fontSize / 2;
        float chronoPosY = hauteurWindow / 2 + fontSize / 2;
        double duree = System.currentTimeMillis() - timeSet;
        int currentSecond = 3 - (int) (duree / 1000);

        textFont(fontTron, fontSize);
        textAlign(LEFT);
        fill(255, 255, 255);
        if (currentSecond > 0) {
            text(currentSecond, chronoPosX, chronoPosY);
        } else if (currentSecond == 0) {
            text("GO !!", chronoPosX - 3 * (fontSize / 2), chronoPosY);
        } else {
            etatPartie = 'n';
        }
    }

    private void ecran_accueil() {
        background(backgroundColor);// Affichage du background
        textAlign(CENTER);
        fill(couleurBleu);
        textFont(fontTron, fontSize);
        text("Tron", largeurWindow / 2 - fontSize / 4, hauteurWindow / 3 + fontSize / 2);

        fill(255, 255, 255, 50);
        textFont(fontDefault, fontSize / 5);
        text("Made by @Hikingyo & @mlcdf", largeurWindow / 2 - fontSize / 4, hauteurWindow / 3 + fontSize);
        
        int clignotement = (int)(System.currentTimeMillis()/300) % 2;

        if (clignotement == 0) {
            fill(255, 255, 255);
            textFont(fontDefault, fontSize / 4);
            text("Press ENTER to continue", largeurWindow / 2 - fontSize / 4, 3 * hauteurWindow / 4 + fontSize / 2);
        }

        if (keyPressed == true) {
            if (key == ENTER || key == ' ') {
                setup();
                etatPartie = 'd';

            }
            if (key == ESC) {
                exit();
            }
        }

    }
}
