package nineboxpuzzle;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Rules extends JPanel{

	private final JFrame frame;
	private NineBoxPuzzle mainMenu;
	private ScoreBoard scoreboard;
	
	Rules (JFrame frame)
	{
		this.frame = frame;

	}
	
	public void setMainMenuR(NineBoxPuzzle panel, ScoreBoard scoreboard) {
		mainMenu = panel;
		scoreboard = scoreboard;
	}
	
	    
	public void showRules() {
        
        JPanel panel = this;
        JPanel Np = new JPanel();
        Color myColor1 = new Color(180,199,231);
    	Np.setBackground(myColor1);
        // panel.removeAll();
        JMenuBar menubar = new JMenuBar();
        JMenu options = new JMenu("Options");
        
        //
        JMenuItem newGameButton = new JMenuItem("Start New Game");
        newGameButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        newGameButton.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    panel.setVisible(false);
                    panel.removeAll();
                    frame.setContentPane(new PlayArea(frame, mainMenu, scoreboard));
                }
            }
        );
        options.add(newGameButton);
        
        //
        JMenuItem mainMenuButton = new JMenuItem("Back To Main Menu");
        mainMenuButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        mainMenuButton.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                	
                    panel.setVisible(false);
                    panel.removeAll();
                    frame.repaint();
                    frame.setContentPane(mainMenu);
                    mainMenu.setMenu();
                    mainMenu.setVisible(true);
                }
            }
        );
        
        options.add(mainMenuButton);
        options.setFont(new Font("Dialog", Font.PLAIN, 20));
        menubar.add(options);
        frame.setJMenuBar(menubar);
        frame.validate();
        frame.repaint();
        
        JLabel l2 = labelWithoutImage("RULES",80);
        
        JLabel l3 = labelWithoutImage("Hello, your purpose is to arrange the box in order as shown below.",20);
        
        JLabel l1 = labelImage("PuzzlePic");
        
       
        JLabel l4 =labelWithoutImage("You can swap empty box with its any adjacent box.",20);
        
        JLabel l45 = labelWithoutImage("----------------",20);
        
        JLabel l5 = labelWithoutImage("When Empty Box is at Center",20);
        
        JLabel l6 =labelImage("Center");
        
        
        JLabel l65 =labelWithoutImage("----------------",20);
        
        JLabel l7 = labelWithoutImage("When Empty Box is at Edge Middle",20);
        
        JLabel l8 = labelImage("EdgeMid");
        
        JLabel l85 = labelWithoutImage("----------------",20);
      
        JLabel l9 =labelWithoutImage("When Empty Box is at Corner.",20);
        
        
        JLabel l10 =labelImage("EdgeCorner");
        JLabel l105 = labelWithoutImage("----------------",20);
        
        
        ///////////////////////
        JPanel btn = new JPanel(); 
        
        JButton startButton2 = new JButton("Lets Play !! ");
        startButton2.setFont(new Font("Dialog", Font.PLAIN, 40));
        startButton2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	panel.setVisible(false);
            	panel.removeAll();
                frame.setContentPane(new PlayArea(frame, mainMenu, scoreboard));
            }
            
        });
        JButton backToMenu = new JButton("Back");
        backToMenu.setFont(new Font("Dialog", Font.PLAIN, 40));
        backToMenu.addActionListener(
        		new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	 panel.setVisible(false);
            	 
            	 Np.setVisible(false);
            	 panel.removeAll();
            	 frame.repaint();
            	 frame.setContentPane(mainMenu);
                 mainMenu.setMenu();
                 mainMenu.setVisible(true);
                 
            }
            
        });
        btn.add( startButton2);
        btn.add(backToMenu);
        Color myColor2 = new Color(180,199,231);
    	btn.setBackground(myColor2);
        /////////////
        JScrollPane pane = new JScrollPane(Np);
        
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        Np.setLayout(new BoxLayout(Np, BoxLayout.Y_AXIS));
        Np.add(l2);
        Np.add(l3);
        Np.add(l1);
        Np.add(l4);
        Np.add(l45);
        Np.add(l5);
        Np.add(l6);
        Np.add(l65);
        Np.add(l7);
        Np.add(l8);
        Np.add(l85);
        Np.add(l9);
        Np.add(l10);
        Np.add(l105);
        Np.add(btn);
        panel.add(pane);
        setVisible(true);
       
    }
	
	JLabel labelWithoutImage(String text, int size)
	{
		 JLabel l9 = new JLabel(text,SwingConstants.LEFT);
	        l9.setFont(new Font("Dialog", Font.PLAIN, size));
	        l9.setBorder(new EmptyBorder(20,100,20,100));//top,left,bottom,right
	        l9.setAlignmentX(CENTER_ALIGNMENT);
	        l9.setAlignmentY(CENTER_ALIGNMENT);
	        return l9;
	        
	}
	JLabel labelImage(String name)
	{

        JLabel l10 = new JLabel("",SwingConstants.CENTER);
        String path = new File("src/resources/image/"+name+".png").getAbsolutePath();
        ImageIcon i1 = new ImageIcon(path);
        l10.setIcon(i1);
        l10.setBorder(new EmptyBorder(0,100,0,100));//top,left,bottom,right
        l10.setAlignmentX(CENTER_ALIGNMENT);
        l10.setAlignmentY(CENTER_ALIGNMENT);
        return l10;
	}
	
	
}
