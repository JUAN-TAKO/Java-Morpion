package morpion;
/**
 * AppliMorpion
 */
import Vues.*;
import Utils.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
public class AppliMorpion implements Observer{
    
    private VueMenu menu;
    Observable controleur;
    public AppliMorpion(){
        menu = new VueMenu();
        menu.addObserver(this);
        menu.afficher();
    }
    
    public static void main(String[] args) {
        AppliMorpion apm = new AppliMorpion();
    }

    
    @Override
    public void update(Observable o, Object arg) {
        Message m = (Message)arg;
        ArrayList<String> n = new ArrayList();
        switch(m.getType()){
            case QUITTER:
                menu.hide();
            break;
            
            case SIMPLE:
                menu.hide();
               
                controleur = new ControleurSimple(2);
                controleur.addObserver(this);
            break;
            
            case TOURNOI:
                menu.hide();
                n.add("Delphine");
                n.add("Julian");
                n.add("Delphine");
                n.add("");
                n.add("");
                n.add("Damien");
                n.add("Thibaut");
                n.add("Joueur");
                controleur = new ControleurTournoi(3, 3, 2, n);
                controleur.addObserver(this);
                break;
            
            case MULTI:
            break;
            
            case TERMINE:
                menu.afficher();
            break;
            
            case RETOUR:
                menu.afficher();
            break;    
        }
    }
}