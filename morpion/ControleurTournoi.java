/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package morpion;

import Utils.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ControleurTournoi extends Observable implements Observer{
    private Controleur1V1 controleur;
    
    public ControleurTournoi(int taille, int ligne, int score, ArrayList<String> noms){
        
    }
    @Override
    public void update(Observable o, Object arg) {
        Message m = (Message)arg;
        
    }
}
