/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.ArrayList;

/**
 *
 * @author Delphine Clary
 */
public class MessageParametrage extends Message {
    
    private int tailleGrille;
    private int nbCoups;
    private ArrayList<String> nomsJoueurs;
    
    public MessageParametrage(MessageType m, int tG, int nbC, ArrayList<String> nomsJ){   
        super(m);
        tailleGrille = tG;
        nbCoups = nbC;
        nomsJoueurs = nomsJ;
    }
    
    public int getTailleGrille(){
        return tailleGrille;
    }
    
    public int getNbJoueurs(){
        return nomsJoueurs.size();
    }
    
    public int getNbCoups(){
        return nbCoups;
    }
    public ArrayList<String> getNomsJoueurs(){
        return nomsJoueurs;
    }
    
}
