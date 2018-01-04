/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion;

import Utils.*;
import Vues.VueParametres;
import Vues.VueVictoire;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author JUAN
 */
public class ControleurSimple extends Observable implements Observer{

    private int scoreMin;
    private Controleur1V1 controleur;
    private Joueur j1;
    private Joueur j2;
    private VueVictoire vv;
    private VueParametres vp;
    
    public ControleurSimple(int score){
        scoreMin = score;
        vp = new VueParametres(0);
        vp.addObserver(this);
        vp.afficher();
        vv = new VueVictoire();
    }
    
    public void update(Observable o, Object arg){
        Message m = (Message)arg;
        switch(m.getType()){
            case TERMINE:
                MessageIndex mi = (MessageIndex)arg;
                if(j1.getWins() >= scoreMin){
                    vv.afficher();
                    vv.afficherJoueur(j1.getNom());  
                }
                else if(j2.getWins() >= scoreMin){
                    vv.afficher();
                    vv.afficherJoueur(j2.getNom());
                }
                else{
                    State stmp = j2.getSymbole();
                    j2.setSymbole(j1.getSymbole());
                    j1.setSymbole(stmp);
                   
                }
                break;
            case PARAMETRE:
                MessageParametrage mp = (MessageParametrage)arg;
                ArrayList<String> nomsJoueurs = new ArrayList<>();
                nomsJoueurs = mp.getNomsJoueurs();
                Collections.shuffle(nomsJoueurs);
                j1 = new Joueur(State.Cross, nomsJoueurs.get(0));
                j2 = new Joueur(State.Circle, nomsJoueurs.get(1));
                Controleur1V1 controleur = new Controleur1V1(mp.getTailleGrille(), mp.getNbCoups(), j1, j2);
                
        }
    }
}
