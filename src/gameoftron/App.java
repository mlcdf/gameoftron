package gameoftron;

import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.PApplet;

/**
 *
 * @author Hikingyo & MLCDF
 */
public class App extends PApplet {

    private final int hauteurWindow = 720;
    private final int largeurWindow = 1280;
    
    int cA = color(246, 106, 53); // définition de la couleur du joueur A
    int cB = color(24, 202, 230); // définition de la couleur du joueur B
    
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

    @Override
    public void settings() {
        size(largeurWindow, hauteurWindow);
    }

    @Override
    public void setup() {
        // Initialisation du flag
        etatPartie = 'd';
        
        // Initialisation du chrono
        timeSet = System.currentTimeMillis();
        
        frameRate(60); // définition du framerate
        
        background(backgroundColor);// Affichage du background

        // Initialisation et positionnement des joueurs
        joueurA = new Joueur("Orange", cA, xA, yA, 'b', this);
        joueurB = new Joueur("Bleu", cB, xB, yB, 'b', this);

        // Dessin des positions de départ
        joueurA.draw();
        joueurB.draw();
       
    }

    @Override
    public void draw() {
        if(etatPartie =='d'){
            background(backgroundColor);
            joueurA.draw();
            joueurB.draw();
            chrono();
        }
        else if(etatPartie == 'n'){
            controller();
            joueurA.draw();
            joueurB.draw();
        }
        //Gestion de fin de partie
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
     * Gestion de fin partie
     * Affichage du nom du perdant
     * Demande de nouvelle partie 
     * @param finPartie <char> le flag qui désigne le perdant (a || b)
     */
    private void finPartie(char finPartie){
        // On récupère le nom du perdant
        if (finPartie == 'a') {
            nomPerdant = joueurA.getNom();
        } else if (finPartie == 'b') {
            nomPerdant = joueurB.getNom();
        } else {
            System.out.println("Erreur dans le flag de fin de partie" + finPartie);
            System.exit(0);
        }

        textSize(24);
        textAlign(LEFT);
        fill(255, 255, 255);
        text(nomPerdant + " a perdu", 10, 30);
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
    
    private void chrono(){
        double duree = System.currentTimeMillis() - timeSet;
        int currentSecond = 3 - (int)(duree / 1000);
        
        textSize(24);
        textAlign(LEFT);
        fill(255, 255, 255);
        if(currentSecond > 0){
           text(currentSecond, 10, 30); 
        }
        else if(currentSecond == 0){
            text("GO !!", 10, 30);
        }
        else{
            etatPartie = 'n';
        }
    }
}
