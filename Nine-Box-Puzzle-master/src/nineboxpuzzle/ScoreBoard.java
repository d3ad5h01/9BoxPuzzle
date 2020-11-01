
package nineboxpuzzle;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class ScoreBoard extends JPanel {		// Its is of panel type
    private ArrayList<PlayerData> list;
    private final JFrame frame;
    private NineBoxPuzzle mainMenu;
    
	
    ScoreBoard(JFrame frame) {		// everything is added to list 
        this.frame = frame;
        list = new ArrayList<>();
        list.add(new PlayerData("Krishna", 14));
        list.add(new PlayerData("Deepak", 52));
        list.add(new PlayerData("Divyatez", 10));
        list.add(new PlayerData("Divyatez", 38));
        list.add(new PlayerData("Akshat", 16));
        list.add(new PlayerData("Krishna", 32));
        list.add(new PlayerData("Divyansh", 34));
        list.add(new PlayerData("Akshat", 21));
        list.add(new PlayerData("Deepak", 20));
    }
    
    public void setMainMenu(NineBoxPuzzle panel) {
        mainMenu = panel;
    }
    
    public static void setCellsAlignment(JTable table, int alignment)
    {
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(alignment);

        TableModel tableModel = table.getModel();

        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++)
        {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
        }
    }
    
    public void addPlayerData(PlayerData playerData) {
        list.add(playerData);
    }
    
    public void showList() {
        
        JPanel panel = this;
        
        panel.removeAll();
        Color myColor1 = new Color(180,199,231);
        panel.setBackground(myColor1);
        JMenuBar menubar = new JMenuBar();
        JMenu options = new JMenu("Options");
        
        JMenuItem newGameButton = new JMenuItem("Start New Game");
        newGameButton.addActionListener(
            new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    panel.setVisible(false);
                    frame.setContentPane(new PlayArea(frame, mainMenu, (ScoreBoard)panel));
                }
            }
        );
        options.add(newGameButton);
        
        JMenuItem mainMenuButton = new JMenuItem("Back To Main Menu");
        mainMenuButton.addActionListener(
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
        options.add(mainMenuButton);
        
        //menubar.add(options);
        frame.setJMenuBar(menubar);
        frame.validate();
        frame.repaint();
        
        Collections.sort(list, null);
        String col[] = {"Rank", "Name", "Number Of Moves"};
        DefaultTableModel model = new DefaultTableModel(col, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        
        int rank = 1;
        for (PlayerData playerData:list) {
            String row[] = new String[3];
            row[0] = ""+rank++;
            row[1] = playerData.getName();
            row[2] = ""+playerData.getScore();
            model.addRow(row);
        }
        
        
        
        JTable myTable = new JTable(model );
        
        myTable.setFont(new Font("Dialog", Font.PLAIN, 20));
        JTableHeader tableHeader = myTable.getTableHeader();
        //tableHeader.setForeground(Color.BLACK);
        //tableHeader.setBackground(Color.WHITE);
        Font headerFont = new Font("Verdana", Font.PLAIN, 25);
        tableHeader.setFont(headerFont);
        setCellsAlignment(myTable, SwingConstants.CENTER);
        myTable.setRowHeight(40);
        setLayout(new BorderLayout());
        
        
        JLabel l9 =	labMaker("Score Board", 40, 20,100,20,100);  
        JLabel l10 = labMaker("", 20, 0,50,20,50); 
        JLabel l11 =labMaker("", 20, 0,50,20,50);
        JLabel l12 = labMaker("", 20, 50,50,50,50);
        
        add(l9, BorderLayout.NORTH);
        add(l10,BorderLayout.EAST);
        add(l11,BorderLayout.WEST);
        JPanel downOptions = new JPanel();
        
        JButton startGame = new JButton("Lets Play !!");
        startGame.setFont(new Font("Dialog", Font.PLAIN, 40));
        startGame.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        panel.setVisible(false);
                        frame.setContentPane(new PlayArea(frame, mainMenu, (ScoreBoard)panel));
                    }
                }
            );
        downOptions.add(startGame);
        downOptions.setBackground(myColor1);
        JButton backMainMenu = new JButton("Main Menu");
        backMainMenu.setFont(new Font("Dialog", Font.PLAIN, 40));
        backMainMenu.addActionListener(
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
        
        downOptions.add(backMainMenu);
        add(new JScrollPane(myTable), BorderLayout.CENTER);
        add(downOptions, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    
    static JLabel labMaker(String text, int size, int a , int b, int c , int d)
    {
    	JLabel l12 = new JLabel(text,SwingConstants.CENTER);
        l12.setFont(new Font("Dialog", Font.PLAIN, size));
        l12.setBorder(new EmptyBorder(a,b,c,d));//top,left,bottom,right
        l12.setAlignmentX(CENTER_ALIGNMENT);
        l12.setAlignmentY(CENTER_ALIGNMENT);
        return l12;
    }

}
