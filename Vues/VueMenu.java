/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Utils.*;
/**
 *
 * @author claryd
 */
public class VueMenu extends Observable {
    
    private JFrame window;
     
         //structure
    private JPanel panelPrincipal;
    private JPanel panelNord;
    private JPanel panelCentre;
    
    private JPanel panelNordEst;
    private JPanel panelNordCentre;
    private JPanel panelNordOuest;
    
    private JPanel panelCentreOuest;
    private JPanel panelCentreEst;
    private JPanel panelCentreCentre;

    private JPanel panelPartieSimple;
    private JPanel panelTournoi;
    private JPanel panelPartieMultiple;
    private JPanel panelQuitter;
    
    private JButton BPSimple ;
    private JButton BTournoi;
    private JButton BPMultiple;
    private JButton BQuitter;
    
    private JLabel menu;
    
        public VueMenu(){
            
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        window.setSize(600,400);
        
        panelPrincipal = new JPanel(new BorderLayout());
        panelCentre = new JPanel(new BorderLayout());
        panelNord = new JPanel(new BorderLayout());
        
        panelCentreOuest = new JPanel(new BorderLayout());
        panelCentreEst = new JPanel(new BorderLayout());
        panelCentreCentre= new JPanel(new GridLayout(4, 1, 0, 0));
        
        panelNordEst = new JPanel();
        panelNordCentre = new JPanel();
        panelNordOuest = new JPanel();
        
        
        menu = new JLabel("JEU DU MORPION");
        Font f = new Font("Serif", Font.PLAIN, 36);
        menu.setFont(f);
        
        
        panelPrincipal.add(panelNord, BorderLayout.NORTH);
        panelPrincipal.add(panelCentre, BorderLayout.CENTER);

        panelNord.add(panelNordEst, BorderLayout.EAST);
        panelNord.add(panelNordCentre, BorderLayout.CENTER);
        panelNord.add(panelNordOuest, BorderLayout.WEST);
        
        panelNordCentre.add(menu);
        
        
        panelCentre.add(panelCentreEst, BorderLayout.EAST);
        panelCentre.add(panelCentreCentre, BorderLayout.CENTER);
        panelCentre.add(panelCentreOuest, BorderLayout.EAST);
        panelNordCentre.add(menu);
        panelPartieSimple = new JPanel();
        panelTournoi = new JPanel();
        panelPartieMultiple = new JPanel();
        panelQuitter = new JPanel();
       
        panelCentreCentre.add(panelPartieSimple);
        panelCentreCentre.add(panelTournoi);
        panelCentreCentre.add(panelPartieMultiple);
        panelCentreCentre.add(panelQuitter);
        
        Font f2 = new Font("Serif", Font.PLAIN, 16);
        
        BPSimple = new JButton("Partie Simple");
        BPSimple.setBackground(Color.GRAY);
        BPSimple.setCursor(Cursor.getPredefinedCursor(12));
        BPSimple.setPreferredSize(new Dimension(150,30));
        BPSimple.setFont(f2);
        
        BTournoi = new JButton("Tournoi");
        BTournoi.setBackground(Color.GRAY);
        BTournoi.setCursor(Cursor.getPredefinedCursor(12));
        BTournoi.setPreferredSize(new Dimension(150,30));
        BTournoi.setFont(f2);
        
        BPMultiple = new JButton("Partie Multiple");
        BPMultiple.setBackground(Color.GRAY);
        BPMultiple.setCursor(Cursor.getPredefinedCursor(12));
        BPMultiple.setPreferredSize(new Dimension(150,30));
        BPMultiple.setFont(f2);
        
        BQuitter = new JButton("Quitter");
        BQuitter.setBackground(Color.GRAY);
        BQuitter.setCursor(Cursor.getPredefinedCursor(12));
        BQuitter.setPreferredSize(new Dimension(150,30));
        BQuitter.setFont(f2);
        
        panelPartieSimple.add(BPSimple);
        panelTournoi.add(BTournoi);
        panelPartieMultiple.add(BPMultiple);
        panelQuitter.add(BQuitter);
        
        window.add(panelPrincipal);
        
        BPSimple.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                //envoyer message pr jouer à une partie simple
                setChanged();
                Message pSimple = new Message(MessageType.SIMPLE);; 
                notifyObservers(pSimple);
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
                 BPSimple.setBackground(Color.lightGray);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                BPSimple.setBackground(Color.GRAY);
                
            }
            
            });
        
        BTournoi.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                //envoyer message pr lancer un tournoi
                setChanged();
                Message pSimple = new Message(MessageType.TOURNOI); 
                notifyObservers(pSimple);
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
                 BTournoi.setBackground(Color.lightGray);
            
            }

            @Override
            public void mouseExited(MouseEvent e) {
               
             BTournoi.setBackground(Color.GRAY);
             
            }
 
        });
        
        BPMultiple.addMouseListener(new MouseListener(){
             

            @Override
            public void mouseClicked(MouseEvent e) {
                //envoyer message pr lancer une partie multiple
                setChanged();
                Message pSimple = new Message(MessageType.MULTI);; 
                notifyObservers(pSimple);
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
                 BPMultiple.setBackground(Color.lightGray);
                   }
            
            @Override
            public void mouseExited(MouseEvent e) {
                 BPMultiple.setBackground(Color.GRAY);
                   }
            
            
        });
        
         BQuitter.addMouseListener(new MouseListener(){
             

            @Override
            public void mouseClicked(MouseEvent e) {
                setChanged();
                Message m = new Message(MessageType.QUITTER);; 
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
                 BQuitter.setBackground(Color.lightGray);
                   }
            
            @Override
            public void mouseExited(MouseEvent e) {
                 BQuitter.setBackground(Color.GRAY);
                   }
   
        });
   
        }
    public void afficher(){
        window.setVisible(true);
    }
    public void hide(){
        window.dispose();
    }
}
