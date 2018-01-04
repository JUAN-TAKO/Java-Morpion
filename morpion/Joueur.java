package morpion;
/**
 * Joueur
 */
import Utils.*;
public class Joueur {

    private State symbole;
    private int wins;
    private String nom;
    
    public Joueur(State s, String nom){
        wins = 0;
        symbole = s;
        this.nom = nom;
    }
    public int getWins(){
        return wins;
    }
    public void setWins(int w){
        wins = w;
    }
    public void win(){
        wins++;
    }
    public State getSymbole(){
        return symbole;
    }
    public void setSymbole(State s){
       symbole = s;
    }
    public String getNom(){
        return nom;
    }
}