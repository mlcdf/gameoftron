/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoftron;

import java.awt.Color;
import static javafx.scene.paint.Color.color;
import processing.core.PApplet;

/**
 *
 * @author hikingyo
 */
public class App extends PApplet{
    
    Joueur a, b;
    Trajectoire Ta, Tb;
    int taille = 10; // taille d'une case
    
    @Override
    public void settings(){
        size(1280, 720);  
    }
    
    @Override
    public void setup(){
        frameRate(60); // définition du framerate
        
        int cA = color(255, 0, 0);
        int cB = color(0, 0, 255);
        
        Trajectoire trajectoireA = new Trajectoire(cA);
        Trajectoire trajectoireB = new Trajectoire(cB);
        
        
        
    }
    
    public void controler(){
        
    }
    
    @Override
    public void draw(){
        
    }
    
}
