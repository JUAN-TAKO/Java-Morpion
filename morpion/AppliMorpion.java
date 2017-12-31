package morpion;
/**
 * AppliMorpion
 */
import Vues.*;
import Utils.*;
import java.util.Observable;
import java.util.Observer;
public class AppliMorpion implements Observer{
    
    private VueMenu menu;
    
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
        switch(m.getType()){
            case QUITTER:
            break;
            
            case SIMPLE:
            break;
            
            case TOURNOI:
            break;
            
            case MULTI:
            break;
        }
    }
}