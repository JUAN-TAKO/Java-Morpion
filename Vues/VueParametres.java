package Vues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class VueParametres {
    
    private JFrame window;
    
    private final JComboBox listeDeroulante;
    
    private JPanel panelC2;
    private JPanel mainPanel;
    private JPanel panelNbJoueurs;
    private JPanel panelPseudos;
    private JPanel panelPseudos2;
    private JPanel vide;
    private JPanel panelListeDeroulante;
    private JPanel panelQuitter;
    private JPanel panelValider;
    private JPanel panelBoutons;
    private JButton boutonValider;
    private JButton boutonQuitter;
    private JTextField nomJoueur;
    private static final int[] pow2 = {2, 4, 8, 16, 32};
    public VueParametres(){
    
    window = new JFrame();
    window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
    window.setSize(800,500);
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
    window.setTitle("PARAMETRAGE D'UNE PARTIE");

    mainPanel = new JPanel(new BorderLayout());
    window.add(mainPanel);

    
         // =================================================================================
        // NORD
        JPanel panelNord = new JPanel() ;
//        panelNord.add(new JLabel("Veuillez sÃ©lectionner le nombre de joueurs et leurs noms"));
//        mainPanel.add(panelNord, BorderLayout.NORTH);

         // =================================================================================
        // OUEST 
        JPanel panelOuest = new JPanel();
        panelOuest.add(new JLabel("  "));
        mainPanel.add(panelOuest, BorderLayout.WEST);
        
         // =================================================================================
        // CENTRE
        JPanel panelCentre = new JPanel(new GridLayout(2,1));
        panelCentre.setLocation(400,250);
        mainPanel.add(panelCentre, BorderLayout.CENTER);
         // =================================================================================
        // EST
        JPanel panelEst = new JPanel();
        panelEst.add(new JLabel("  "));
        mainPanel.add(panelEst, BorderLayout.EAST);
       

        // selection du nombre de joueurs
        panelNbJoueurs = new JPanel();
        panelNbJoueurs.setLayout(new BoxLayout(panelNbJoueurs, BoxLayout.LINE_AXIS));
        panelCentre.add(panelNbJoueurs);
        panelNbJoueurs.add(new JLabel("nombre de joueurs : "));
        listeDeroulante = new JComboBox();
        listeDeroulante.setMaximumSize(new Dimension(120,50));
        listeDeroulante.addItem(2);
        listeDeroulante.addItem(4);
        listeDeroulante.addItem(8);
        listeDeroulante.addItem(16);
        listeDeroulante.addItem(32);
        panelNbJoueurs.add(listeDeroulante);
        panelNbJoueurs.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        
        listeDeroulante.setSelectedIndex(0);
        
            // titre selection des pseudos
        panelPseudos = new JPanel();
        panelPseudos.add(new JLabel("oui"));
        panelPseudos.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        panelCentre.add(panelPseudos);
//        panelPseudos.add(new JLabel("SELECTION DES PSEUDOS"));
//        
//        panelPseudos2 = new JPanel();
//        panelPseudos2.add(new JLabel("test"));
//        panelPseudos.add(panelPseudos2);
//        
//        panelBoutons = new JPanel(new GridLayout(1,2));
//        boutonQuitter = new JButton();
//        panelQuitter = new JPanel();
//        boutonValider = new JButton();
//        panelValider = new JPanel();
//        panelQuitter.add(boutonQuitter);
//        panelCentre.add(panelBoutons);
      
        
        //Choix des pseudos
        panelPseudos.add(new JPanel());
        
//        for (int i = 1; i < 33; i++) {
//            panelC2.add(new JLabel("NOM JOUEUR " + i + " :", JLabel.RIGHT));
//            panelC2.add(new JTextField());
//        }
        
         listeDeroulante.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateNbr();
            }
        });
        updateNbr();
    }
    
    private void updateNbr(){
        int nbjoueurs = pow2[listeDeroulante.getSelectedIndex()];
        panelPseudos.remove(1);
        panelC2 = new JPanel(new GridLayout(nbjoueurs,1));
        panelPseudos.add(panelC2);
        for (int i = 0; i < nbjoueurs; i++) {
            if(listeDeroulante.getSelectedIndex() < 5){
                panelC2.add(new JTextField("                                       "));
            }
            else{
                
            }
        }
        panelPseudos.revalidate();
        panelPseudos.repaint();
    }
    
    public void afficher(){
        window.setVisible(true);
    }
    public static void main(String[] args){
        VueParametres v = new VueParametres();
        v.afficher();
    }
}
