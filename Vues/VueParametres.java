package Vues;

import javax.swing.*;
import java.awt.*;



public class VueParametres {
    
    private JFrame window;
    
    private final JComboBox listeDeroulante;
    
    private JPanel mainPanel;
    private JPanel panelC1;
    private JPanel panelC2;
    
    private JButton boutonValider;
    private JButton boutonQuitter;
    
    public VueParametres(){
    
    window = new JFrame();
    window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
    window.setSize(600,400);
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
    window.setTitle("PARAMETRAGE D'UNE PARTIE");
    mainPanel = new JPanel(new BorderLayout());
    
         // =================================================================================
        // NORD
        JPanel panelHaut = new JPanel() ;
        mainPanel.add(panelHaut, BorderLayout.NORTH);
  
        
         // =================================================================================
        // OUEST 
        JPanel panelOuest = new JPanel();
        mainPanel.add(panelOuest, BorderLayout.WEST);
        
         // =================================================================================
        // EST
        JPanel panelEst = new JPanel();
        mainPanel.add(panelEst, BorderLayout.EAST);
        
         // =================================================================================
        // CENTRE
        JPanel panelCentre = new JPanel(new GridLayout(3,1));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        
        // selection du nombre de joueur
        panelCentre.add(panelC1 = new JPanel(new GridLayout(1,2)));
        panelC1.add(new JLabel("nombre de joueur : ", JLabel.RIGHT));
        listeDeroulante = new JComboBox();
        listeDeroulante.addItem(2);
        listeDeroulante.addItem(3);
        listeDeroulante.addItem(4);
        panelC1.add(listeDeroulante);
        listeDeroulante.setSelectedIndex(0);
        
            // titre selection des pseudos
        panelCentre.add(new JLabel("SELECTION DES PSEUDOS", JLabel.CENTER));
        
        //Choix des pseudos
        panelCentre.add(panelC2 = new JPanel(new GridLayout(4,2)));
        
        for (int i = 1; i < 5; i++) {
            panelC2.add(new JLabel("NOM JOUEUR " + i + " :", JLabel.RIGHT));
            panelC2.add(new JTextField());
        }
    }
    
    
    public void afficher(){
        window.setVisible(true);
    }
}
