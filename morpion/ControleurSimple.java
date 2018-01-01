/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion;

import Utils.*;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author JUAN
 */
public class ControleurSimple extends Observable{

    private final int SCORE_MIN;
    private Controleur1V1 controleur;
    private ArrayList<Joueur> joueurs;
    
    public ControleurSimple(int taille, int ligne, int score, ArrayList<String> noms){
        SCORE_MIN = score;
        controleur = new Controleur1V1(taille, ligne, noms);
    }
    
    public void update(Observable o, Object arg) {
        Message m = (Message)arg;
        switch(m.getType()){
            case GAGNE:
                MessageIndex mi = (MessageIndex)arg;
                
                break;
                       
        }
    }
}
