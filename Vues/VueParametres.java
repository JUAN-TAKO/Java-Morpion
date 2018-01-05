package Vues;

import Utils.Message;
import Utils.MessageIndex;
import Utils.MessageParametrage;
import Utils.MessageType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Observable;



public class VueParametres extends Observable{
    
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
    
    private int[] tableauListeDeroulante;
    
    public VueParametres(int version){
    
    window = new JFrame();
    window.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
    window.setSize(900,450);
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
    window.setTitle("PARAMETRAGE D'UNE PARTIE");
    
    window.addWindowListener(new java.awt.event.WindowAdapter(){
        @Override
        public void windowClosing(WindowEvent winEvt){
            setChanged();
            Message m = new Message(MessageType.QUITTER);
            notifyObservers(m);
            clearChanged();
        }
    });

    mainPanel = new JPanel(new BorderLayout());
    window.add(mainPanel);

    


         // =================================================================================
        // OUEST 
        JPanel panelOuest = new JPanel();
        panelOuest.add(new JLabel("  "));
        mainPanel.add(panelOuest, BorderLayout.WEST);
        

        JPanel panelCentre;
        JPanel panelNord;
        if(version == 1){
            panelNord = new JPanel(new GridLayout(3,1)) ;
            mainPanel.add(panelNord, BorderLayout.NORTH);
            // =================================================================================
            // CENTRE
            panelCentre = new JPanel(new GridLayout());
            mainPanel.add(panelCentre, BorderLayout.CENTER);
        }
        else{         
            // =================================================================================
            // NORD
            panelNord = new JPanel(new GridLayout(2,1)) ;
            mainPanel.add(panelNord, BorderLayout.NORTH);
            panelCentre = new JPanel(new GridLayout(3,1));
            mainPanel.add(panelCentre, BorderLayout.CENTER);
        }
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
        panelTailleGrille.add(new JLabel("Taille de la grille : ", SwingConstants.RIGHT));
        panelListeGrille = new JPanel();
        listeTailleGrille = new JComboBox();
        listeTailleGrille.setPreferredSize(new Dimension(50,30));
        listeTailleGrille.addItem("3x3");
        listeTailleGrille.addItem("4x4");
        listeTailleGrille.addItem("5x5");
        listeTailleGrille.addItem("7x7");
        listeTailleGrille.addItem("9x9");
        
        int[] tableauTailleGrille = new int[]{3,4,5,7,9};
        panelListeGrille.add(listeTailleGrille);
        panelTailleGrille.add(panelListeGrille);
        panelNord.add(panelTailleGrille);
        
        listeTailleGrille.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
             nbCoups();
        }
    });
        //Sélection du nombre de coups pour gagner
          panelNbCoups = new JPanel(new GridLayout(1,2,2,2));
          panelNord.add(panelNbCoups);
          panelNbCoups.add(new JLabel("Nombre de symboles à aligner : ", SwingConstants.RIGHT));
          panelListeNb = new JPanel();
          listeNbCoups = new JComboBox(); 
          listeNbCoups.setPreferredSize(new Dimension(50,30));
          listeNbCoups.addItem(3);
          listeNbCoups.setSelectedItem(0);
          panelListeNb.add(listeNbCoups);
         
          panelNbCoups.add(panelListeNb);
          
          int[] tableauNbCoups = new int[]{3,4,5};
          
        // selection du nombre de joueurs
        
        listeDeroulante = new JComboBox();
        listeDeroulante.setPreferredSize(new Dimension(50,30));
        listeDeroulante.addItem(2);
        listeDeroulante.addItem(4);
        listeDeroulante.addItem(8);
        listeDeroulante.addItem(16);
        listeDeroulante.addItem(32);
        tableauListeDeroulante = new int[]{2,4,8,16,32};
        
          if(version == 1){ //si on est en mode tournoi alors on peut choisir un nombre de joueurs
              panelNbJoueurs = new JPanel(new GridLayout(1,2));
              JPanel panelListeNbJoueurs;
              panelListeNbJoueurs = new JPanel();
              panelNbJoueurs.add(new JLabel("Nombre de joueurs : ", SwingConstants.RIGHT));
              panelNord.add(panelNbJoueurs);

              panelListeNbJoueurs.add(listeDeroulante);
              panelNbJoueurs.add(panelListeNbJoueurs);
              panelChampsPseudos = new JPanel();
              
              // Noms Joueurs
              panelPseudos = new JPanel(new GridLayout(1,2));
              panelPseudos.add(new JLabel("Noms des joueurs : ", SwingConstants.RIGHT));
              panelPseudos.add(panelChampsPseudos);
              panelPseudos.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
              panelCentre.add(panelPseudos);

              //Choix des pseudos
             updateNbr();
          }
          else if(version == 0){
              panelPseudos = new JPanel(new GridLayout(1,2));              
             
              panelPseudos.add(new JLabel("Noms des joueurs : ", SwingConstants.RIGHT));
              panelChampsPseudos = new JPanel();
              panelPseudos.add(panelChampsPseudos);
              panelCentre.add(panelPseudos);
              updateNbr();
          }
        
     
        
        listeDeroulante.setSelectedIndex(0);
        
        listeDeroulante.addActionListener (new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateNbr();
            }
            
        });
          
        

        
        panelBoutons = new JPanel(new GridLayout(1,2));
        boutonQuitter = new JButton("Quitter");
        boutonQuitter.setCursor(Cursor.getPredefinedCursor(12));
        boutonQuitter.setPreferredSize(new Dimension(150,30));
        panelQuitter = new JPanel();
        boutonValider = new JButton("Valider");
        boutonValider.setBackground(Color.GRAY);
        boutonValider.setCursor(Cursor.getPredefinedCursor(12));
        boutonValider.setPreferredSize(new Dimension(150,30));
        panelValider = new JPanel();
        panelQuitter.add(boutonQuitter);
        boutonQuitter.setBackground(Color.GRAY);
        panelValider.add(boutonValider);
        panelBoutons.add(panelQuitter);
        panelBoutons.add(panelValider);
        panelSud.add(panelBoutons);
        
      
        
       
        
        
        boutonValider.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            
                setChanged();
                MessageParametrage m = new MessageParametrage(MessageType.PARAMETRE, 
                                                              tableauTailleGrille[listeTailleGrille.getSelectedIndex()],
                                                              tableauNbCoups[listeNbCoups.getSelectedIndex()],
                                                              getText()
                                                              );
                notifyObservers(m);
                clearChanged();
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            boutonValider.setBackground(Color.lightGray);
        }

        @Override
        public void mouseExited(MouseEvent e) {
             boutonValider.setBackground(Color.GRAY);
        }
    });
        
        boutonQuitter.addMouseListener(new MouseListener(){
        @Override
        public void mouseClicked(MouseEvent e) {
            setChanged();
            Message m = new Message(MessageType.QUITTER);
            notifyObservers(m);
            clearChanged();
                    }

        @Override
        public void mousePressed(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            boutonQuitter.setBackground(Color.lightGray);        }

        @Override
        public void mouseExited(MouseEvent e) {
            boutonQuitter.setBackground(Color.GRAY);        
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
                        JTextField a = new JTextField("");
                        a.setPreferredSize(new Dimension(100,25));
                        panelC2.add(a);
                    }
                }
                else{
                    panelC2 = new JPanel(new GridLayout(8,nbJ/8)); //s'il est > 8 on les ordonne par colonne de 8
                    panelChampsPseudos.add(panelC2);
                     for (int i = 0; i < nbJ; i++) {  
                         JTextField a = new JTextField("");
                         a.setPreferredSize(new Dimension(100,25));
                         panelC2.add(a);
                    }
                }
        panelChampsPseudos.revalidate();
        panelChampsPseudos.repaint();
       }
        
    public void nbCoups(){ //on adapte le nombre de symbole à aligner en fonction de la taille de la grille choisie
        int tailleG = listeTailleGrille.getSelectedIndex(); //on récupère l'index de la taille de la grille choisie
 
        listeNbCoups.removeAllItems();  
        listeDeroulante.setSelectedIndex(0);
        
        if(tailleG == 0){ //si la taille de la grille est de 3x3 seuls 3 symboles sont à aligner pour gagner
            listeNbCoups.addItem(3);  
        }
        
        else if(tailleG == 1){ //si elle est de 5x5 on peut choisir d'aligner 3, 4 ou 5 symboles pour gagner
            listeNbCoups.addItem(3);  
            listeNbCoups.addItem(4);  
            
        }
        else{
            listeNbCoups.addItem(3);  
            listeNbCoups.addItem(4);  
            listeNbCoups.addItem(5);
        }
    }
    
    public ArrayList<String> getText(){
        ArrayList<String> nomsJoueurs = new ArrayList<>();
        for(int i = 0 ; i < panelC2.getComponentCount() ; i++){
           nomsJoueurs.add(((JTextField)panelC2.getComponent(i)).getText());
        }
        return nomsJoueurs;
    }
    public void afficher(){
        window.setVisible(true);
    }
    
    public void dispose(){
        window.dispose();
    }
    
    public void hide(){
        window.setVisible(false);
    }
}