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
    private ArrayList<ArrayList<Joueur>> gagnants;
    private int size;
    private int ligneMin;
    private int SCORE_MIN;
    private int nombreJoueurs;
    private int indexJoueurs;
    private int indexGagnants;
    private Joueur j1;
    private Joueur j2;
    
    public ControleurTournoi(int taille, int ligne, int score, ArrayList<String> noms){
        gagnants = new ArrayList<>();
        joueurs = new ArrayList<>();
        size = taille;
        ligneMin = ligne;
        SCORE_MIN = score;
        indexJoueurs = 0;
        indexGagnants = 0;
        nombreJoueurs = noms.size();
        Collections.shuffle(noms);
        State symbole = State.Cross;
        HashMap<String, Integer> mapNoms = new HashMap<>();
        for(int i = 0; i < noms.size(); i++){
            if(noms.get(i) == ""){
                noms.set(i, "Joueur");
            }
            String s = noms.get(i);
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
        for(String s : noms){
            Integer n = mapNoms.get(s);
            if(n==-1){
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
        gagnants.add(joueurs);
        gagnants.add(new ArrayList<>());
        nouvellePartie();
    }
    private void nouvellePartie(){
        j1 = gagnants.get(indexGagnants).get(indexJoueurs);
        j2 = gagnants.get(indexGagnants).get(indexJoueurs+1);
        j1.setSymbole(State.Cross);
        j2.setSymbole(State.Circle);
        System.out.println(j1.getNom() + " VS " + j2.getNom());
        controleur = new Controleur1V1(size, ligneMin, j1, j2);
        controleur.addObserver(this);
        indexJoueurs += 2;
        if(indexJoueurs >= nombreJoueurs){
            nombreJoueurs /= 2;
            indexJoueurs = 0;
            gagnants.add(new ArrayList<>());
            indexGagnants++;
        }
        
    }
    @Override
    public void update(Observable o, Object arg) {
        Message m = (Message)arg;
        switch(m.getType()){
            case TERMINE:
                MessageIndex mi = (MessageIndex)arg;
                if(j1.getWins() >= SCORE_MIN){
                    System.out.println(j1.getNom() + " a gagné la partie");
                    gagnants.get(indexGagnants+1).add(j1);
                    controleur.dispose();
                    nouvellePartie();
                }
                else if(j2.getWins() >= SCORE_MIN){
                    System.out.println(j2.getNom() + " a gagné la partie");
                    gagnants.get(indexGagnants+1).add(j2);
                    controleur.dispose();
                    nouvellePartie();
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
