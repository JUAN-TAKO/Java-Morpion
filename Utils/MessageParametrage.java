/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author Delphine Clary
 */
public class MessageParametrage extends Message {
    
    private int tailleGrille;
    private int nbJoueurs;
    
    public MessageParametrage(MessageType m, int tG, int nbJ){
        
        super(m);
        tailleGrille = tG;
        nbJoueurs = nbJ;
    }
    
    public int getTailleGrille(){
        return tailleGrille;
    }
    
    public int getNbJoueurs(){
        return nbJoueurs;
    }
    
}