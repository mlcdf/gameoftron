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
    private Case caseCourante;
    private int x, y; // Coordonnées grahique
    App window;

    public Joueur(String nom, Case caseCourante, App window) {
        this.nom = nom;
        this.caseCourante = caseCourante;
        this.window = window;
    }
    
    
}
