package morpion;
import java.util.ArrayList;
import Utils.*;

/**
 * Grille
 */
public class Grille {
    private int size;
    private ArrayList<State> cases;
    private int nombreCasesLibres;

    public Grille(int tailleCote){
        this.size = tailleCote;
        cases = new ArrayList<>();
        for(int i = 0; i < size*size; i++){
            cases.add(null);
        }
        nombreCasesLibres = size*size;
    }
    public void reset(){
        for(int i = 0; i < cases.size(); i++){
            cases.set(i, null);
        }
        nombreCasesLibres = size*size;
    }
    public ArrayList<State> getStates(){
        return cases;
    }
    public boolean pleine(){
        return nombreCasesLibres == 0;
    }
    //VERIFICATION
    public boolean set(int posX, int posY, State value){
        
        int index = posX + size*posY;
        return set(index, value);
    }
    public boolean set(int index, State value){
        if(cases.get(index) != null){
            System.out.println("Cette case a deja ete remplie");
            return false;
        }
        else{
            nombreCasesLibres--;
            cases.set(index, value);
            return true;
        }
    }
    public State get(int posX, int posY){
        if(posX < 0 || posY < 0 || posX >= size || posY >= size){
            return null;
        }
        return cases.get(posX + size*posY);
    }
    public int size(){
        return size;
    }
    public ArrayList<Integer> getWinningCoordinates(int posX, int posY, State symbole, int casesAligneesMinPourGagner){
        int directionX = -1;
        int directionY = 0;
        if(get(posX, posY) != symbole){
            return new ArrayList<>();
        }
        ArrayList<Integer> winningCoordinates = new ArrayList<>();
        winningCoordinates.addAll(verifierLigne(casesAligneesMinPourGagner, posX, posY, directionX, directionY));
        directionY--;
        winningCoordinates.addAll(verifierLigne(casesAligneesMinPourGagner, posX, posY, directionX, directionY));
        directionX++;
        winningCoordinates.addAll(verifierLigne(casesAligneesMinPourGagner, posX, posY, directionX, directionY));
        directionX++;
        winningCoordinates.addAll(verifierLigne(casesAligneesMinPourGagner, posX, posY, directionX, directionY));

        return winningCoordinates;
    }
    private ArrayList<Integer> verifierLigne(int casesAligneesMinPourGagner, int posX, int posY, int directionX, int directionY){
        ArrayList<Integer> coordsLigneGagnante = new ArrayList<>();
        coordsLigneGagnante.add(new Integer(posX + posY*size));
        int tempX = posX + directionX;
        int tempY = posY + directionY;
        int score = 1;
        State symbole = get(posX, posY);
        if(symbole == null){
            return new ArrayList<>();
        }
        State tempSymbole = get(tempX, tempY);
        while (tempSymbole == symbole){
            score++;
            coordsLigneGagnante.add(new Integer(tempX + tempY*size));
            tempX += directionX;
            tempY += directionY;
            tempSymbole = get(tempX, tempY);
        }
        tempX = posX - directionX;
        tempY = posY - directionY;
        tempSymbole = get(tempX, tempY);
        while (tempSymbole == symbole){
            score++;
            coordsLigneGagnante.add(new Integer(tempX + tempY*size));
            tempX -= directionX;
            tempY -= directionY;
            tempSymbole = get(tempX, tempY);
        }
        if(score >= casesAligneesMinPourGagner){
            return coordsLigneGagnante;
        }
        else{
            return new ArrayList<>();
        }
    }
}