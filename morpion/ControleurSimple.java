/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion;

import Utils.*;
import Vues.VueVictoire;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author JUAN
 */
public class ControleurSimple extends Observable implements Observer{

    private final int SCORE_MIN;
    private Controleur1V1 controleur;
    private Joueur j1;
    private Joueur j2;
    private VueVictoire vv;
    
    public ControleurSimple(int taille, int ligne, int score, ArrayList<String> noms){
        SCORE_MIN = score;
        j1 = new Joueur(State.Cross, noms.get(0));
        j2 = new Joueur(State.Circle, noms.get(1));
        vv = new VueVictoire();
        controleur = new Controleur1V1(taille, ligne, j1, j2);
        controleur.addObserver(this);
    }
    
    public void update(Observable o, Object arg){
        Message m = (Message)arg;
        switch(m.getType()){
            case TERMINE:
                MessageIndex mi = (MessageIndex)arg;
                if(j1.getWins() >= SCORE_MIN){
                    System.out.println("oui");
                    vv.afficher();
                    vv.afficherJoueur(j1.getNom());
                    vv.afficherSymbole();
                }
                else if(j2.getWins() >= SCORE_MIN){
                    vv.afficher();
                    vv.afficherJoueur(j2.getNom());
                    vv.afficherSymbole();
                     System.out.println("non");
                }
                else{
                    State stmp = j2.getSymbole();
                    j2.setSymbole(j1.getSymbole());
                    j1.setSymbole(stmp);
                   
                }
                break;
                       
        }
    }
}
