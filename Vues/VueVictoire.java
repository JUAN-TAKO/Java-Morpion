/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Utils.*;
/**
 *
 * @author Delphine Clary
 */


public class VueVictoire {
    
    private JFrame window;
    private JPanel main;
    
    private ImageIcon victoire;
    
    private JPanel joueur ;
    private JLabel labelGagnant;
    private JPanel symbole;
    private JLabel s;
    
    
    public VueVictoire(){
    window = new JFrame();
    window.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
    window.setSize(400,300);
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
    window.setTitle("VICTOIRE");
    
    main = new JPanel(new BorderLayout());
    window.add(main);

    joueur = new JPanel();
    main.add(joueur, BorderLayout.NORTH);
    
    symbole = new JPanel();
    s = new JLabel();
    symbole.add(s);
    main.add(symbole, BorderLayout.CENTER);
    victoire = new ImageIcon("src/images/win.png");
    }

    
    public void afficherJoueur(String j){
        Font font = new Font("Arial",Font.PLAIN, 26);
        labelGagnant = new JLabel(j + " a gagné !");
        labelGagnant.setFont(font);
        joueur.add(labelGagnant);
        System.out.println("joueur");
    }
    
    private ImageIcon resizeIcon(ImageIcon ii, int width){
        return new ImageIcon(ii.getImage().getScaledInstance(width, width, java.awt.Image.SCALE_AREA_AVERAGING));
    }
    
    
    public void afficherSymbole(){
        s.setIcon(resizeIcon(victoire, 200));
        System.out.println("symbole");
    }
   
    public void afficher(){
        window.setVisible(true);
        afficherSymbole();
    }
}
