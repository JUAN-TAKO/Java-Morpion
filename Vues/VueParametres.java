package Vues;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class VueParametres {
    
    private JFrame window;
    
    private final JComboBox listeDeroulante;
    
    private JPanel panelC2;
    private JPanel mainPanel;
    private JPanel panelTailleGrille;
    private JComboBox listeTailleGrille;
    private JPanel panelListeGrille;
    private JPanel panelLabelListeGrille;
    private JPanel panelNbCoups;
    private JPanel panelLabelNb;
    private JPanel panelListeNb;
    private JComboBox listeNbCoups;
    private JPanel panelNbJoueurs;
    private JPanel panelLabelNbJoueurs;
    private JPanel panelListeD;
    private JPanel panelPseudos;
    private JPanel panelLabelPseudos;
    private JPanel panelChampsPseudos;
    private JPanel panelPseudos2;
    private JPanel vide;
    private JPanel panelListeDeroulante;
    private JPanel panelQuitter;
    private JPanel panelValider;
    private JPanel panelBoutons;
    private JButton boutonValider;
    private JButton boutonQuitter;
    private JTextField nomJoueur;
    
    public VueParametres(){
    
    window = new JFrame();
    window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
    window.setSize(800,800);
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
    window.setTitle("PARAMETRAGE D'UNE PARTIE");

    mainPanel = new JPanel(new BorderLayout());
    window.add(mainPanel);

    
         // =================================================================================
        // NORD
        JPanel panelNord = new JPanel() ;
//        panelNord.add(new JLabel("Veuillez sélectionner le nombre de joueurs et leurs noms"));
//        mainPanel.add(panelNord, BorderLayout.NORTH);

         // =================================================================================
        // OUEST 
        JPanel panelOuest = new JPanel();
        panelOuest.add(new JLabel("  "));
        mainPanel.add(panelOuest, BorderLayout.WEST);
        
         // =================================================================================
        // CENTRE
        JPanel panelCentre = new JPanel(new GridLayout(4,1,2,2));
        panelCentre.setLocation(400,250);
        mainPanel.add(panelCentre, BorderLayout.CENTER);
         // =================================================================================
        // EST
        JPanel panelEst = new JPanel();
        panelEst.add(new JLabel("  "));
        mainPanel.add(panelEst, BorderLayout.EAST);
       
         // =================================================================================
        // SUD
        JPanel panelSud = new JPanel();
        mainPanel.add(panelSud, BorderLayout.SOUTH);
       
        
        //Sélection de la taille de la grille
        
        panelTailleGrille = new JPanel(new GridLayout(1,2,1,1));
        
        panelLabelListeGrille = new JPanel();
        panelLabelListeGrille.add(new JLabel("Taille de la grille"));
        panelTailleGrille.add(panelLabelListeGrille);
        panelListeGrille = new JPanel();
        listeTailleGrille = new JComboBox();
        listeTailleGrille.setPreferredSize(new Dimension(50,30));
        listeTailleGrille.addItem("3x3");
        listeTailleGrille.addItem("5x5");
        listeTailleGrille.addItem("7x7");
        listeTailleGrille.addItem("9x9");
        
        panelListeGrille.add(listeTailleGrille);
        panelTailleGrille.add(panelListeGrille);
        panelCentre.add(panelTailleGrille);
        
        listeTailleGrille.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
             nbCoups();
        }
    });
        //Sélection du nombre de coups pour gagner
          panelNbCoups = new JPanel(new GridLayout(1,2,2,2));
          panelCentre.add(panelNbCoups);
          panelLabelNb = new JPanel();
          panelLabelNb.add(new JLabel("Nombre de symboles à aligner pour gagner : "));
          panelNbCoups.add(panelLabelNb);
          panelListeNb = new JPanel();
          listeNbCoups = new JComboBox(); 
          listeNbCoups.setPreferredSize(new Dimension(50,30));
          listeNbCoups.addItem(3);
          listeNbCoups.setSelectedItem(0);
          panelListeNb.add(listeNbCoups);
         
          panelNbCoups.add(panelListeNb);
          
        // selection du nombre de joueurs
        panelNbJoueurs = new JPanel(new GridLayout(1,2));
        panelLabelNbJoueurs = new JPanel();
        panelNbJoueurs.add(panelLabelNbJoueurs);
        panelCentre.add(panelNbJoueurs);
        panelLabelNbJoueurs.add(new JLabel("Nombre de joueurs : "));
        panelListeD = new JPanel();
        listeDeroulante = new JComboBox();
        panelListeD.add(listeDeroulante);
        listeDeroulante.setPreferredSize(new Dimension(50,30));
        listeDeroulante.addItem(2);
        listeDeroulante.addItem(4);
        listeDeroulante.addItem(8);
        listeDeroulante.addItem(16);
        listeDeroulante.addItem(32);
        panelNbJoueurs.add(panelListeD);
        
        listeDeroulante.setSelectedIndex(0);
        
        listeDeroulante.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateNbr();
            }
            
        });
        updateNbr();
        
        // Noms Joueurs
        panelPseudos = new JPanel(new GridLayout(1,2));
        panelPseudos.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        panelCentre.add(panelPseudos);
        
        //Choix des pseudos
        panelLabelPseudos = new JPanel();
        panelLabelPseudos.add(new JLabel("Noms des joueurs : "));
        panelPseudos.add(panelLabelPseudos);
        panelChampsPseudos = new JPanel();
        panelPseudos.add(panelChampsPseudos);
       
       

        
        
        
        
        panelBoutons = new JPanel(new GridLayout(1,2));
        boutonQuitter = new JButton("Quitter");
        panelQuitter = new JPanel();
        boutonValider = new JButton("Valider");
        panelValider = new JPanel();
        panelQuitter.add(boutonQuitter);
        panelValider.add(boutonValider);
        panelBoutons.add(panelQuitter);
        panelBoutons.add(panelValider);
        panelSud.add(panelBoutons);
        
        boutonQuitter.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                window.dispose(); //ferme la fenêtre quand on clique sur quitter
            
            }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
          
        }

        @Override
        public void mouseExited(MouseEvent e) {
             
        }
        });
        
        
        boutonValider.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    });
        
        
    }
    
    private void updateNbr(){ //on adapte le nombre de champs texte au nombre de joueurs sélectionné dans la liste déroulante (LD)
         panelChampsPseudos.removeAll();
         int nbJ = (int)Math.pow(2.0,(double)listeDeroulante.getSelectedIndex()+1); //on récupère le nombre de joueur sélectionné en se servant de l'indice dans la liste
                if(listeDeroulante.getSelectedIndex() < 3){ //si le nombre de joueurs sélectionné est <= 8 (et donc l'indice dans la LD < 3) on ordonne les champs texte en 1 colonne
                     panelC2 = new JPanel(new GridLayout(nbJ,1));
                     panelChampsPseudos.add(panelC2);
                     for (int i = 0; i < nbJ; i++) {  
                        panelC2.add(new JTextField("                       "));
                    }
                }
                else{
                    panelC2 = new JPanel(new GridLayout(8,nbJ/8)); //s'il est > 8 on les ordonne par colonne de 8
                    panelChampsPseudos.add(panelC2);
                     for (int i = 0; i < nbJ; i++) {  
                        panelC2.add(new JTextField("                        "));
                    }
                }
        panelChampsPseudos.revalidate();
        panelChampsPseudos.repaint();
       }
        
    public void nbCoups(){
        String tailleG = listeTailleGrille.getSelectedItem().toString();
 
        listeNbCoups.removeAllItems();  
        listeDeroulante.setSelectedIndex(0);
        
        if(tailleG == "3x3"){
            listeNbCoups.addItem(3);  
        }
        
        else if(tailleG == "5x5"){
            listeNbCoups.addItem(3);  
            listeNbCoups.addItem(4);  
            listeNbCoups.addItem(5);  
            
        }
        
        else if(tailleG == "7x7"){
            listeNbCoups.addItem(3);  
            listeNbCoups.addItem(4);  
            listeNbCoups.addItem(5);
            listeNbCoups.addItem(7);  
            
            
        }
        else{
            listeNbCoups.addItem(3);  
            listeNbCoups.addItem(4);  
            listeNbCoups.addItem(5);
            listeNbCoups.addItem(7); 
            listeNbCoups.addItem(9);
        }
    }
    
    public void afficher(){
        window.setVisible(true);
    }
}