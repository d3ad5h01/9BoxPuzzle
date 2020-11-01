package nineboxpuzzle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class PlayArea extends JPanel {  	// it is of JPanel Type
    private GameCPU mygamecpu;		//It has its game engine
    private ScoreBoard scoreboard;			// Stats are passed
    private JFrame frame;					// frame is told
    private NineBoxPuzzle mainMenu;			// now we also need main menu , which is passed
    private JLabel score;					// each game has a score
    
    
    
    //Play area CONSTRUCTOR//
    PlayArea(JFrame frame, NineBoxPuzzle mainMenu, ScoreBoard panel2) {
        score = new JLabel("Moves Count : 0");
        score.setFont(new Font("Dialog", Font.PLAIN, 20));
        score.setBorder(new EmptyBorder(0,50,0,50));//top,left,bottom,right
        this.mainMenu = mainMenu;
        this.scoreboard = panel2;
        this.frame = frame;
        mygamecpu = new GameCPU(this, panel2);
        initializeComponents();
        setVisible(true);
        JPanel panel = this;
        
        //OPTIONS _ MENU BAR
        JMenuBar menubar = new JMenuBar();
        
        
        JButton restartGame = new  JButton("Restart");
        restartGame.setFont(new Font("Dialog", Font.PLAIN, 20));
        restartGame.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        panel.setVisible(false);
                        frame.setContentPane(new PlayArea(frame, mainMenu, panel2));		// new PlayArea is instanciated
                    }
                }
            );
        JButton mainMenuButtonTop = new JButton("Main Menu");
        mainMenuButtonTop.setFont(new Font("Dialog", Font.PLAIN, 20));
        mainMenuButtonTop.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    panel.setVisible(false);
                    frame.setContentPane(mainMenu);
                    mainMenu.setMenu();
                    mainMenu.setVisible(true);
                }
            }
        );
        JButton scoreButton = new JButton("Show Scores");
        scoreButton.setFont(new Font("Dialog", Font.PLAIN, 20));
        scoreButton.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    panel.setVisible(false);
                    frame.setContentPane(panel2);
                    panel2.showList();
                    panel2.setVisible(true);
                }
            }
        );
        
        menubar.add(mainMenuButtonTop);
        menubar.add(restartGame);
        menubar.add(scoreButton);
        menubar.add(score);
        frame.setJMenuBar(menubar);
        frame.validate();
        frame.repaint();
    }
    //////////////////Start Component//////////////////////
    private void initializeComponents() {
        setLayout(new GridLayout(3, 3));
        Box[][] boxes = mygamecpu.getBoxes();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                add(boxes[i][j]);
                boxes[i][j].addActionListener(new EventHandler(mygamecpu, i, j));
            }
        }
        setVisible(true);
    }
    /////////////Winning Dialogue///////////////
    protected void showWinningDialog() {
        JTextField name = new JTextField();
        Object[] input = {
            "Enter your name : ", name
        };
        int option = JOptionPane.showConfirmDialog(this, input, "Yippe!! You Won.Submit You Score", JOptionPane.OK_CANCEL_OPTION);
        if(option == JOptionPane.OK_OPTION) {
            mygamecpu.setPlayerData(name.getText().trim());
            JOptionPane.showMessageDialog(this, "Saved!! Try again , You can do better .\n");
            setVisible(false);
            frame.setContentPane(scoreboard);
            scoreboard.setVisible(true);
            scoreboard.showList();
        }
    }
    //////////////////////////////
    protected void setCount(int movesCount) {
        score.setText("Moves Count : " + movesCount);
    }
}
