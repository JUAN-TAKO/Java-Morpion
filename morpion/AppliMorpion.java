package morpion;
/**
 * AppliMorpion
 */
import Vues.*;
import Utils.*;
import java.util.Observable;
import java.util.Observer;
public class AppliMorpion implements Observer{
    private Grille grille;
    private Joueur j1;
    private Joueur j2;
    private final int SCORE_MIN = 3;
    private final int SIZE = 3;
    private Joueur jcourrant;
    private VuePartie1v1 ihmp;
    private VueMenu ihmm;
    
    public AppliMorpion(){
        grille = new Grille(SIZE);
        //TEST DE FONCTIONNEMENT
        j1 = new Joueur(State.Cross, grille);
        j2 = new Joueur(State.Circle, grille);
        jcourrant = j1;
        ihmp = new VuePartie1v1(grille.size(), j1.getNom(), j2.getNom());
        ihmm = new VueMenu();
        ihmp.addObserver(this);
        ihmp.afficher();
        ihmm.afficher();
    }
    
    public static void main(String[] args) {
        AppliMorpion apm = new AppliMorpion();
    }

    public void next(){
        if(jcourrant == j1){
            jcourrant = j2;
        }
        else{
            jcourrant = j1;
        }
    }
    @Override
    public void update(Observable o, Object arg) {
        Message m = (Message)arg;
        MessageIndex mi = (MessageIndex)arg;
        switch(m.getType()){
            case JOUER:
                int i = mi.getIndex();
                grille.set(i, jcourrant.getSymbole());
                int x = i % grille.size();
                int y = i / grille.size();
                if(grille.actionGagnante(x, y, jcourrant.getSymbole(), SCORE_MIN)){
                    System.out.println("Le joueur " + jcourrant.getSymbole() + " a gagne");
                    jcourrant.win();
                    grille.reset();
                }
                else if(grille.pleine()){
                    System.out.println("égalité");
                    grille.reset();
                }
                ihmp.update(grille.getStates());
                next();
                break;
                       
        }
    }
}