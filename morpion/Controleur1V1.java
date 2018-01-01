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
public class Controleur1V1 extends Observable implements Observer{
    private Grille grille;
    private Joueur j1;
    private Joueur j2;
    private final int LIGNE_MIN;
    private final int SIZE;
    private Joueur jcourant;
    private VuePartie1v1 vue;
    
    public Controleur1V1(int taille, int ligne, ArrayList<String> noms){
        LIGNE_MIN = ligne;
        SIZE = taille;
        grille = new Grille(taille);
        vue = new VuePartie1v1(SIZE, noms.get(0), noms.get(1));
        j1 = new Joueur(State.Cross, grille, noms.get(0));
        j2 = new Joueur(State.Cross, grille, noms.get(1));
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
        switch(m.getType()){
            case JOUER:
                MessageIndex mi = (MessageIndex)arg;
                int i = mi.getIndex();
                grille.set(i, jcourant.getSymbole());
                int x = i % grille.size();
                int y = i / grille.size();
                if(grille.actionGagnante(x, y, jcourant.getSymbole(), LIGNE_MIN)){
                    System.out.println("Le joueur " + jcourant.getSymbole() + " a gagne");
                    jcourant.win();
                    setChanged();
                    int in = 1;
                    if(jcourant==j1){
                        in = 0;
                    }
                    MessageIndex ms = new MessageIndex(MessageType.GAGNE, in);
                    notifyObservers(ms);
                    clearChanged();
                    grille.reset();
                }
                else if(grille.pleine()){
                    System.out.println("égalité");
                    j1.win();
                    j2.win();
                    grille.reset();
                }
                vue.update(grille.getStates());
                next();
                break;
                       
        }
    }
    
}
