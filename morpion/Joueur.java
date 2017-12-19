package morpion;
/**
 * Joueur
 */
import java.util.Scanner;
import Utils.*;
public class Joueur {

    private State symbole;
    private int wins;
    private Grille grille;
    private String nom;
    
    public Joueur(State s, Grille g){
        wins = 0;
        symbole = s;
        grille = g;
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