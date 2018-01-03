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
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private boolean active;
    
    public Controleur1V1(int taille, int ligne, Joueur joueur1, Joueur joueur2){
        LIGNE_MIN = ligne;
        SIZE = taille;
        grille = new Grille(taille);
        vue = new VuePartie1v1(SIZE, joueur1.getNom(), joueur2.getNom());
        j1 = joueur1;
        j2 = joueur2;
        jcourant = j1;
        active = true;
        vue.addObserver(this);
        vue.afficher();
    }
    public void reset(Joueur joueur1, Joueur joueur2){
        j1 = joueur1;
        j2 = joueur2;
        jcourant = j1;
        System.out.println(j1.getNom());
        System.out.println(j1.getSymbole());
        grille.reset();
        vue.update(grille.getStates());
    }
    private void next(){
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
                if(active){
                    MessageIndex mi = (MessageIndex)arg;
                    int i = mi.getIndex();
                    if(grille.set(i, jcourant.getSymbole())){
                        int x = i % grille.size();
                        int y = i / grille.size();
                        ArrayList<Integer> wc = grille.getWinningCoordinates(x, y, jcourant.getSymbole(), LIGNE_MIN);
                        if(wc.size() > 0){
                            System.out.println(jcourant.getNom() + " a gagné la manche");
                            jcourant.win();
                            active = false;
                            vue.blink(wc);
                        }
                        else if(grille.pleine()){
                            System.out.println("égalité");
                            j1.win();
                            j2.win();
                        }
                        vue.update(grille.getStates());
                        next();
                    }
                }
                break;
            case FINISHED_BLINKING:
                
                try {
                    Thread.sleep(300);
                }catch (InterruptedException ex) {
                    Logger.getLogger(Controleur1V1.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                int in = 1;
                if(jcourant==j1){
                    in = 0;
                }
                setChanged();
                MessageIndex ms = new MessageIndex(MessageType.TERMINE, in);
                notifyObservers(ms);
                clearChanged();
                active = true;
                grille.reset();
                vue.update();
        }
    }
    
}
