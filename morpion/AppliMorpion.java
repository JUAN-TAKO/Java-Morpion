package morpion;
/**
 * AppliMorpion
 */
import Vues.*;
import Utils.*;
import java.util.Observable;
import java.util.Observer;
public class AppliMorpion implements Observer{
    
    private VueMenu ihmm;
    
    public AppliMorpion(){
        ihmm = new VueMenu();
        ihmm.afficher();
    }
    
    public static void main(String[] args) {
        AppliMorpion apm = new AppliMorpion();
    }

    
    @Override
    public void update(Observable o, Object arg) {
        
    }
}