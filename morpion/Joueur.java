package morpion;
/**
 * Joueur
 */
import Utils.*;
public class Joueur {

    private State symbole;
    private int wins;
    private Grille grille;
    private String nom;
    
    public Joueur(State s, Grille g, String nom){
        wins = 0;
        symbole = s;
        grille = g;
        this.nom = nom;
    }
    public int getWins(){
        return wins;
    }
    public void win(){
        wins++;
    }
    public State getSymbole(){
        return symbole;
    }
    public String getNom(){
        return nom;
    }
}