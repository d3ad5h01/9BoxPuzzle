
 package nineboxpuzzle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class NineBoxPuzzle extends JPanel {		// it is of JPanel type

    private JFrame frame;
    private ScoreBoard scoreboard;
    private NineBoxPuzzle panel;
	private Rules rules;
	final static boolean shouldFill = true; 
    //private Startup s1;
    
    
    
    //Constructor
    NineBoxPuzzle(JFrame frame, ScoreBoard scoreboard, Rules rules) {
        super();
        this.frame = frame;
        this.scoreboard = scoreboard;
        this.rules = rules;
        
        
        panel = this; // NineBoxPuzzle extends from JPanel;
        
        Color myColor1 = new Color(180,199,231);
    	panel.setBackground(myColor1);
        
        setLayout(new GridBagLayout());//Setting layout 
        GridBagConstraints gridBagLayout = new GridBagConstraints();// we have to do this to add it to container
        gridBagLayout.fill = GridBagConstraints.BOTH;
        gridBagLayout.anchor = GridBagConstraints.CENTER;
        gridBagLayout.ipady = 80;
        gridBagLayout.ipadx = 80;
        gridBagLayout.gridwidth = 1;
        gridBagLayout.gridheight = 1;
        gridBagLayout.insets = new Insets(2,20,2,30);
        
        ///FIRST BUTTON/////////////////////////////////
        JButton startButton = new JButton("Start Game");
        
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);		// abhi jo panel hai usko false kar diya
                frame.setContentPane(new PlayArea(frame, panel, scoreboard));	// new PlayArea is instanciated 
            }
            
        });
        startButton.setFont(new Font("Dialog", Font.PLAIN, 40)); gridBagLayout.gridx = 0;gridBagLayout.gridy = 0;add(startButton, gridBagLayout);
        //////////////////////////////////////////////////
        
        //SECOND BUTTON///////////////////////////////////
        JButton scoresButton = new JButton("View Scores");
        scoresButton.addActionListener(new ActionListener() {

            @Override
			public void actionPerformed(ActionEvent e) {
            	System.out.println("Scores CLICKED");
            	panel.setVisible(false);
                frame.setContentPane(scoreboard);
                scoreboard.showList();
                scoreboard.setVisible(true);
            }
            
        });    
        scoresButton.setFont(new Font("Dialog", Font.PLAIN, 40));gridBagLayout.gridx = 0;gridBagLayout.gridy = 1;add(scoresButton, gridBagLayout);
        //////////////////////////////////////////////////////
        
        //THIRD BUTTON////////////////////////////////////////
        JButton ruleButton = new JButton("Rules");
        ruleButton.addActionListener(new ActionListener() {

            @Override
			public void actionPerformed(ActionEvent e) {
            	System.out.println("RULES CLICKED");
            	frame.repaint();
                panel.setVisible(false);
                frame.setContentPane(rules);	
                rules.showRules();
                rules.setVisible(true);
            }
            
        });
        //x and y coordinate
        ruleButton.setFont(new Font("Dialog", Font.PLAIN, 40));gridBagLayout.gridx = 0;     gridBagLayout.gridy = 2;	add(ruleButton, gridBagLayout);
        ///////////////////////////////////////////////////////
        
        
        //Fourth BUTTON////////////////////////////////////////
        JButton exitButton = new JButton(" Exit ");
       
        exitButton.addActionListener(new ActionListener() {

            @Override
			public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }
            
        });
        exitButton.setFont(new Font("Dialog", Font.PLAIN, 40));gridBagLayout.gridx = 0;gridBagLayout.gridy = 3;add(exitButton, gridBagLayout);
        
        /////////////////////////////////////////////////////
        
        
        //Home screen name//////////////////////////////////
        JLabel lTop = new JLabel("9 BOX PUZZLE ");
        lTop.setFont(new Font("Dialog", Font.BOLD, 60));
        gridBagLayout.gridwidth = 1;
        gridBagLayout.gridheight = 1;      //make this component tall
        gridBagLayout.ipadx = 80;
        gridBagLayout.gridx = 1;
        gridBagLayout.gridy = 0; 
        add(lTop, gridBagLayout);
        ///////////////////////////////////////////////////////
        
        
        //Home screen image////////////////////////////////////
        JLabel l1 = new JLabel("");
        gridBagLayout.gridwidth = 1;
        gridBagLayout.gridheight = 3;      //make this component tall
        gridBagLayout.ipadx = 80;
        gridBagLayout.gridx = 1;
        gridBagLayout.gridy = 1;
        l1.setIcon(imageIconPath("PuzzlePicHome"));
        l1.setPreferredSize(new Dimension(200, 400));
        add(l1, gridBagLayout);
        ////////////////////////////////////////////////////////	
    }
    
    //Audio player//////////////////////////////////////
    protected static void playSound(File soundName)
    {
    	try 
      {
       AudioInputStream audioInputStream = AudioSystem.getAudioInputStream((soundName).getAbsoluteFile( ));
       Clip clip = AudioSystem.getClip( );
       clip.open(audioInputStream);
       clip.loop(Clip.LOOP_CONTINUOUSLY);//To remove loop , delete this line
       clip.start();
       
      }
      catch(Exception ex)
      {
        //System.out.println("Error with playing sound.");
        ex.printStackTrace( );
      }
   
    }
    /////////////////////////////////////////////////////
    
    
    //MENU BAR SETTING FUNCTION///////////////////////////////
    protected void setMenu() {			
       
    	NineBoxPuzzle panel = this;
        JMenuBar menubar = new JMenuBar();
        JMenu options = new JMenu("Options");
        
        frame.setJMenuBar(menubar);
        frame.validate();
        frame.repaint();
    }
    
    static ImageIcon imageIconPath(String name)
    {
    	String path = new File("src/resources/image/"+name+".png").getAbsolutePath();
        ImageIcon i1 = new ImageIcon(path);
		return i1;
    }
    
    static JLabel labelMaker(String text, int size)
    {
    	JLabel l2 = new JLabel(text,SwingConstants.CENTER);
        l2.setFont(new Font("Dialog", Font.PLAIN, size));
        l2.setAlignmentX(CENTER_ALIGNMENT);
        l2.setAlignmentY(CENTER_ALIGNMENT);
        //l3.setForeground(tCol);
        return l2;
    }
    
    public static void starter(JFrame frame)
    {
    	
    	JPanel jp = new JPanel(); 
    	Color myColor1 = new Color(103,128,199);
    	jp.setBackground(myColor1);
    	jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
    	
    	JLabel l1 = new JLabel("",SwingConstants.CENTER);
    	l1.setIcon(imageIconPath("iiita"));
        l1.setAlignmentX(CENTER_ALIGNMENT);
        l1.setAlignmentY(CENTER_ALIGNMENT);
        jp.add(l1);
        
        
        JPanel rP = new JPanel(); //Roll NUmber Panel
        rP.setBackground(myColor1);
        JLabel l2 = labelMaker("Group 1", 100);
        jp.add(l2);
        
        JLabel l3 = labelMaker("IIB2019024 ||", 40);
        l3.setForeground(Color.WHITE);
        rP.add(l3);
        
        JLabel l4 = labelMaker("IIT2019203 ||", 40);
        l4.setForeground(Color.WHITE);
        rP.add(l4);
        
        JLabel l5 = labelMaker("IIT2019214 ||", 40);
        l5.setForeground(Color.WHITE);
        rP.add(l5);
        
        JLabel l6 =labelMaker("IIT2019220", 40);
        l6.setForeground(Color.WHITE);
        rP.add(l6);
        
        jp.add(rP);
        
       
        
        
        jp.setAlignmentX(CENTER_ALIGNMENT);
        frame.add(jp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    
    public static void wait(int x)
    {
    	try {
			TimeUnit.SECONDS.sleep(x);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
        
    }
   
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        starter(frame);
        wait(3);
        frame.repaint();
        ScoreBoard scoreboard = new ScoreBoard(frame);
        Rules rules = new Rules(frame);
        frame.setTitle("Nine Box Puzzle!");
       
        playSound(new File("src/resources/audio/"+"Fur2"+".wav"));	
       
        NineBoxPuzzle panel = new NineBoxPuzzle(frame, scoreboard,rules);
        scoreboard.setMainMenu(panel);
        rules.setMainMenuR(panel,scoreboard);
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //panel.setMenu();
        
        
        
        
        
    }

}
