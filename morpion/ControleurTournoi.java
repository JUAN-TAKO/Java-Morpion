/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion;

import Utils.*;
import Vues.VueVictoire;
import Vues.VueParametres;
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
    private VueParametres vp;
    
    public ControleurTournoi(int taille, int ligne, int score, ArrayList<String> noms){
        gagnants = new ArrayList<>();
        joueurs = new ArrayList<>();
        size = taille;
        ligneMin = ligne;
        SCORE_MIN = score;
        indexJoueurs = 0;
        indexGagnants = 0;
        nombreJoueurs = noms.size();
        vp = new VueParametres(1);
        vp.afficher();
        Collections.shuffle(noms);
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
            if(n == -1){
                joueurs.add(new Joueur(null, s));
            }
            else{
                joueurs.add(new Joueur(null, s + " " + n));
            }
            mapNoms.put(s, n-1);
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
        j1.setWins(0);
        j2.setWins(0);
        System.out.println(j1.getNom() + " VS " + j2.getNom());
        controleur = new Controleur1V1(size, ligneMin, j1, j2);
        controleur.addObserver(this);
        
    }
    private void finPartie(Joueur j){
        
        System.out.println(j.getNom() + " a gagné la partie");
        gagnants.get(indexGagnants+1).add(j);
        controleur.dispose();
        
        indexJoueurs += 2;
        
        if(indexJoueurs >= nombreJoueurs){
            nombreJoueurs /= 2;
            indexJoueurs = 0;
            gagnants.add(new ArrayList<>());
            indexGagnants++;
        }
        if(nombreJoueurs == 1){
            VueVictoire v = new VueVictoire();
            v.afficherJoueur(j.getNom());
            v.afficherSymbole();
            v.afficher();
        }
        else{
            nouvellePartie();
        }
        
    }
    @Override
    public void update(Observable o, Object arg) {
        Message m = (Message)arg;
        switch(m.getType()){
            case TERMINE:
                MessageIndex mi = (MessageIndex)arg;
                if(j1.getWins() >= SCORE_MIN){
                    finPartie(j1);
                }
                else if(j2.getWins() >= SCORE_MIN){
                    finPartie(j2);
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
