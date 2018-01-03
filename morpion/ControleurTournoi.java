/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion;

import Utils.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class ControleurTournoi extends Observable implements Observer{
    private Controleur1V1 controleur;
    private ArrayList<Joueur> joueurs;
    
    public ControleurTournoi(int taille, int ligne, int score, ArrayList<String> noms){
        Collections.shuffle(noms);
        State symbole = State.Cross;
        HashMap<String, Integer> mapNoms = new HashMap<>();
        for(String s : noms){
            if(s == ""){
                s = "Joueur";
            }
            int n = mapNoms.get(s);
            if(s == null){
                mapNoms.put(s, 1);
            }
            else{
                mapNoms.put(s, n+1);
            }
        }
        for(String s : noms){
            int n = mapNoms.get(s);
            if(n==1){
                joueurs.add(new Joueur(symbole, s));
            }
            else{
                joueurs.add(new Joueur(symbole, s + " " + n));
            }
            mapNoms.put(s, n-1);
            if(symbole == State.Cross){
                symbole = State.Circle;
            }
            else{
                symbole = State.Cross;
            }
        }
    }
    @Override
    public void update(Observable o, Object arg) {
        Message m = (Message)arg;
        
    }
}
