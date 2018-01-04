
package Vues;


import Utils.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
/**
 *
 * @author royetju
 */
public class VuePartie1v1 extends Observable implements ActionListener{
    //Données du jeu
    private int size;
    private String nomJoueur1;
    private String nomJoueur2;
    
    
    private JFrame window;
    
    //structure
    private JPanel panelPrincipal;
    private JPanel panelCentre;
    private JPanel panelOuest;
    private JPanel panelEst;
    private JPanel panelNord;
    private JPanel panelGrille;
    
    private JLabel labelJoueur1;
    private JLabel labelJoueur2;
    private JLabel symboleJoueur1;
    private JLabel symboleJoueur2;

    //images
    private ImageIcon baseCircle;
    private ImageIcon baseCross;
    private ImageIcon cross;
    private ImageIcon circle;
    private ImageIcon crossHalf;
    private ImageIcon circleHalf;
    
    private ArrayList<State> cases;
    
    private final int BLINK_AMOUNT = 6;
    private Timer blinkTimer;
    private int blinkCount;
    private ArrayList<Integer> blinkingIndexes;
    private State blinkingState;
    
    public VuePartie1v1(int s, String nomJ1, String nomJ2){
        size = s;
        nomJoueur1 = nomJ1;
        nomJoueur2 = nomJ2;
        
        window = new JFrame();
        window.setSize(1150, 700);
        
        window.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                loadImages();
            }
        });
        baseCircle = new ImageIcon("src/images/circle512.png");
        baseCross = new ImageIcon("src/images/cross512.png");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        
        panelPrincipal = new JPanel(new BorderLayout());
        panelCentre = new JPanel(new BorderLayout());
        panelOuest = new JPanel(new GridLayout(4, 1));
        panelEst = new JPanel(new GridLayout(4, 1));
        panelNord = new JPanel(new BorderLayout());
        
        panelGrille = new JPanel(new GridLayout(size, size));
        panelPrincipal.add(panelOuest, BorderLayout.WEST);
        panelPrincipal.add(panelCentre, BorderLayout.CENTER);
        panelPrincipal.add(panelEst, BorderLayout.EAST);
        
        panelCentre.add(panelNord, BorderLayout.NORTH);
        panelCentre.add(panelGrille, BorderLayout.CENTER);
        window.add(panelPrincipal);
        window.setTitle(nomJoueur1 + " VS " + nomJoueur2);
        
        cases = new ArrayList<>();
        for(int i = 0; i < size * size; i++){
            JLabel l = new JLabel();
            l.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            panelGrille.add(l);
            cases.add(null);
        }
        JLabel l = ((JLabel)(panelGrille.getComponent(0)));
        panelGrille.setPreferredSize(new Dimension(700,700));
        
        for(int i = 0; i < size * size; i++){
            JLabel p = (JLabel)panelGrille.getComponent(i);
            addListener(p, i);
        }

        labelJoueur1 = new JLabel(nomJoueur1);
        labelJoueur2 = new JLabel(nomJoueur2);
        
        labelJoueur1.setHorizontalAlignment(SwingConstants.CENTER);
        labelJoueur1.setVerticalAlignment(SwingConstants.CENTER);
        
        labelJoueur2.setHorizontalAlignment(SwingConstants.CENTER);
        labelJoueur2.setVerticalAlignment(SwingConstants.CENTER);
        
        symboleJoueur1 = new JLabel();
        symboleJoueur2 = new JLabel();
        
        symboleJoueur1.setHorizontalAlignment(SwingConstants.CENTER);
        symboleJoueur1.setVerticalAlignment(SwingConstants.CENTER);
        
        symboleJoueur2.setHorizontalAlignment(SwingConstants.CENTER);
        symboleJoueur2.setVerticalAlignment(SwingConstants.CENTER);
        
        panelOuest.add(labelJoueur1);
        panelOuest.add(symboleJoueur1);
        
        panelEst.add(labelJoueur2);
        panelEst.add(symboleJoueur2);
        
        panelOuest.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));
        panelEst.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 40));
        
        update();
        blinkTimer = new Timer(400, this);
        blinkCount = 0;
    }
    private void addListener(JLabel l, final int i){
        l.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setChanged();
                MessageIndex m = new MessageIndex(MessageType.JOUER, i);
                notifyObservers(m);
                clearChanged();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });
    }
    private void loadImages(){
        JLabel l = ((JLabel)(panelGrille.getComponent(0)));
        int minSize = Math.min(l.getWidth(), l.getHeight());
        circle = resizeIcon(baseCircle, minSize);
        cross = resizeIcon(baseCross, minSize);
        crossHalf = resizeIcon(baseCross, (int)((double)minSize/1.5));
        circleHalf = resizeIcon(baseCircle, (int)((double)minSize/1.5));
        update();
    }
    public void updateCase(int i, State s){
        JLabel c = ((JLabel)(panelGrille.getComponent(i)));
        c.setHorizontalAlignment(SwingConstants.CENTER);
        c.setVerticalAlignment(SwingConstants.CENTER);
        c.setIcon(getIcon(s));
    }
    private ImageIcon getIcon(State s){
        ImageIcon r = null;
        if(s != null){
            switch (s){
                case Cross:
                    r = cross;
                    break;
                case Circle:
                    r = circle;
                    break;
            }
        }
        return r;
    }
    private ImageIcon resizeIcon(ImageIcon ii, int width){
        return new ImageIcon(ii.getImage().getScaledInstance(width, width, java.awt.Image.SCALE_AREA_AVERAGING));
    }
    public void setJoueurs(String nj1, String nj2){
        labelJoueur1.setText(nj1);
        labelJoueur2.setText(nj2);
        labelJoueur1.revalidate();
        labelJoueur2.revalidate();
        labelJoueur1.repaint();
        labelJoueur2.repaint();
    }
    public void setJoueurActif(){
        
    }
    public void update(ArrayList<State> states){
        cases = states;
        update();
    }
    public void update(){
        for(int i = 0; i < size * size; i++){
            State s = cases.get(i);
            updateCase(i, s);
        }
        symboleJoueur2.setIcon(circleHalf);
        symboleJoueur1.setIcon(crossHalf);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }
    private void blinkOnce(){
        if(blinkingState == null){
            blinkingState = cases.get(blinkingIndexes.get(0));
        }
        else{
            blinkingState = null;
        }
        for(Integer i : blinkingIndexes){
            JLabel l = (JLabel)panelGrille.getComponent(i);
            l.setIcon(getIcon(blinkingState));
            l.revalidate();
            l.repaint();
        }
        
    }
    public void blink(ArrayList<Integer> blinking){
        blinkingIndexes = blinking;
        blinkTimer.start();
    }
    public void setVisible(boolean v){
       window.setVisible(v);
    }
    public void dispose(){
        window.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(blinkCount == BLINK_AMOUNT){
            blinkCount = 0;
            blinkTimer.stop();
            setChanged();
            Message ms = new Message(MessageType.FINISHED_BLINKING);
            notifyObservers(ms);
            clearChanged();
        }
        else{
            blinkOnce();
            blinkCount++;
        }
    }
    
     
}
