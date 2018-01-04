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
import java.util.HashMap;
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
        vv.addObserver(this);
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
                 HashMap<String, Integer> mapNoms = new HashMap<>();
                 ArrayList<Joueur>joueurs = new ArrayList<>();
                 for(int i = 0; i < nomsJoueurs.size(); i++){
                    System.out.println("\"" + nomsJoueurs.get(i) + "\"");
                    if(nomsJoueurs.get(i).equals("") || nomsJoueurs.get(i) == null){
                        nomsJoueurs.set(i, "Joueur");
                    }
                    String s = nomsJoueurs.get(i);
                    Integer n = mapNoms.get(s);
                    if(n == null){
                        mapNoms.put(s, -1);
                    }
                    else if(n == -1){
                        mapNoms.put(s, 2);
                    }
                    else{
                        mapNoms.put(s, n+1);
                    }
                 }
                 for(String s : nomsJoueurs){
                    Integer n = mapNoms.get(s);
                    if(n == -1){
                        joueurs.add(new Joueur(null, s));
                    }
                    else{
                        joueurs.add(new Joueur(null, s + " " + n));
                    }
                    mapNoms.put(s, n-1);
                 }
                 j1 = joueurs.get(1);
                 j1.setSymbole(State.Cross);
                 j2 = joueurs.get(0);
                 j2.setSymbole(State.Circle);
                 controleur = new Controleur1V1(mp.getTailleGrille(), mp.getNbCoups(), j1, j2);
                 controleur.addObserver(this);
                 vp.dispose();
            break;
            
            case RETOUR:
                vv.dispose();
                controleur.dispose();
                 setChanged();
                Message mb = new Message(MessageType.RETOUR);; 
                notifyObservers(mb);
                clearChanged();
            break;    
        }
    }
}
