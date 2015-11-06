package gameoftron;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

/**
 *
 * @author @Hikingyo & @mlcdf
 *
 * Built with the Processing library for Java
 *
 */
public class App extends PApplet {

    private final int hauteurWindow = 720;
    private final int largeurWindow = 1260;

    int couleurOrange = color(246, 106, 53); // définition de la couleur Orange
    int couleurBleu = color(24, 202, 230); // définition de la couleur Bleu

    int backgroundColor = color(5, 13, 16); // définition de la couleur du background

    PImage arrow;

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
        fontTron = createFont("TRON.TTF", 24);
        fontDefault = createFont("Roboto-Regular.ttf", 24);
        // Taille de la police
        fontSize = 120;

        // Initialisation du flag
        etatPartie = 's';

        // Initialisation du chrono
        timeSet = System.currentTimeMillis();

        frameRate(30); // définition du framerate

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
        }
        else if (etatPartie == 'd') {

            background(backgroundColor);
            joueurA.draw();
            joueurB.draw();
            chrono();
        }
        else if (etatPartie == 'n') {
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
        if (key == 's' && joueurA.getDirection() != 'h') {
            joueurA.setDirection('b');
        }
        if (key == 'z' && joueurA.getDirection() != 'b') {
            joueurA.setDirection('h');
        }
        if (key == 'q' && joueurA.getDirection() != 'd') {
            joueurA.setDirection('g');
        }
        if (key == 'd' && joueurA.getDirection() != 'g') {
            joueurA.setDirection('d');
        }
        // Joueur B
        if (keyCode == DOWN && joueurB.getDirection() != 'h') {
            joueurB.setDirection('b');
        }
        if (keyCode == UP && joueurB.getDirection() != 'b') {
            joueurB.setDirection('h');
        }
        if (keyCode == LEFT && joueurB.getDirection() != 'd') {
            joueurB.setDirection('g');
        }
        if (keyCode == RIGHT && joueurB.getDirection() != 'g') {
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

    private void displayControl() {

        int posLeft = largeurWindow / 6;
        int posRight = 5 * largeurWindow / 6;

        stroke(255);
        fill(backgroundColor);
        rect(posLeft, hauteurWindow / 2 + 30, 50, 50, 7);
        rect(posLeft, hauteurWindow / 2 - 70, 50, 50, 7);
        rect(posLeft - 50, hauteurWindow / 2 - 20, 50, 50, 7);
        rect(posLeft + 50, hauteurWindow / 2 - 20, 50, 50, 7);

        fill(255);
        textFont(fontDefault, fontSize / 4);
        text("Z", posLeft + fontSize / 8, hauteurWindow / 2 - 50 + fontSize / 8);
        text("S", posLeft + fontSize / 8, hauteurWindow / 2 + 50 + fontSize / 8);
        text("Q", posLeft + fontSize / 8 - 50, hauteurWindow / 2 + fontSize / 8);
        text("D", posLeft + fontSize / 8 + 50, hauteurWindow / 2 + fontSize / 8);

        stroke(255);
        fill(backgroundColor);
        rect(posRight, hauteurWindow / 2 + 30, 50, 50, 7);
        rect(posRight, hauteurWindow / 2 - 70, 50, 50, 7);
        rect(posRight - 50, hauteurWindow / 2 - 20, 50, 50, 7);
        rect(posRight + 50, hauteurWindow / 2 - 20, 50, 50, 7);

        fill(255);
        textFont(fontDefault, fontSize / 4);

        arrow = loadImage("src/up.png");
        image(arrow, posRight + 1, hauteurWindow / 2 - 70);
        arrow = loadImage("src/down.png");
        image(arrow, posRight + 1, hauteurWindow / 2 + 30);
        arrow = loadImage("src/left.png");
        image(arrow, posRight - 50, hauteurWindow / 2 - 19);
        arrow = loadImage("src/right.png");
        image(arrow, posRight + 50, hauteurWindow / 2 - 19);
    }

    // Gestion du chronomètre de début de partie
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
            // Affichage des touches de contrôles des joueurs
            displayControl();
        } else if (currentSecond == 0) {
            text("GO !!", chronoPosX - 3 * (fontSize / 2), chronoPosY);
        } else {
            etatPartie = 'n';
        }
    }

    // Gestion de l'écran d'accueil

    private void ecran_accueil() {
        background(backgroundColor);
        textAlign(CENTER);

        fill(couleurBleu);
        textFont(fontTron, fontSize);
        text("Tron", largeurWindow / 2 - fontSize / 4, hauteurWindow / 3 + fontSize / 2);

        fill(255, 255, 255, 50);
        textFont(fontDefault, fontSize / 5);
        text("Made by @Hikingyo & @mlcdf", largeurWindow / 2 - fontSize / 4, hauteurWindow / 3 + fontSize);

        // Gestion du clignotement 
        int clignotement = (int) (System.currentTimeMillis() / 300) % 2;

        if (clignotement == 0) {
            fill(255, 255, 255);
            textFont(fontDefault, fontSize / 4);
            text("Press ENTER to continue", largeurWindow / 2 - fontSize / 4, 3 * hauteurWindow / 4 + fontSize / 2);
        }

        // Navigation
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
