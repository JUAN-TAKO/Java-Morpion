/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion;

import Utils.*;
import Vues.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author JUAN
 */
public class Controleur1V1 implements Observer{
    private Grille grille;
    private Joueur j1;
    private Joueur j2;
    private final int SCORE_MIN;
    private final int SIZE;
    private Joueur jcourant;
    private VuePartie1v1 ihmp;
    
    public Controleur1V1(int taille, int score, ArrayList<String> noms){
        SCORE_MIN = score;
        SIZE = taille;
        grille = new Grille(taille);
        j1 = new Joueur(State.Cross, grille, noms.get(0));
        
    }
    
    public void next(){
        if(jcourant == j1){
            jcourant = j2;
        }
        else{
            jcourant = j1;
        }
    }
    
    @Override
    public void update(Observable o, Object arg) {
        Message m = (Message)arg;
        MessageIndex mi = (MessageIndex)arg;
        switch(m.getType()){
            case JOUER:
                int i = mi.getIndex();
                grille.set(i, jcourant.getSymbole());
                int x = i % grille.size();
                int y = i / grille.size();
                if(grille.actionGagnante(x, y, jcourant.getSymbole(), SCORE_MIN)){
                    System.out.println("Le joueur " + jcourant.getSymbole() + " a gagne");
                    jcourant.win();
                    grille.reset();
                }
                else if(grille.pleine()){
                    System.out.println("égalité");
                    grille.reset();
                }
                ihmp.update(grille.getStates());
                next();
                break;
                       
        }
    }
    
}
