package nineboxpuzzle;

import java.awt.Color;
import java.util.*;

public class GameCPU {
    
    private Box[][] boxes;
    private Box emptyBox;	// this is imp
    private PlayArea playArea;
    private int init;
    private int movesCount;
    private ScoreBoard scoreboard;

    GameCPU(PlayArea playArea, ScoreBoard panel2) {
        this.playArea = playArea;
        this.scoreboard = panel2;
        init = 0;
        movesCount = 0;
        boxes = getRandomBoxes();
    }
   
    
    protected Box[][] getBoxes(){
        return boxes;
    }
    
    protected int getMovesCount() {
        return movesCount;
    }
    
    boolean canMakeMove(int positionX, int positionY) {
        int differenceX = Math.abs(emptyBox.getPositionX() - positionX);
        int differenceY = Math.abs(emptyBox.getPositionY() - positionY);
        if (differenceX <= 1 && differenceY <= 1 && !(differenceX == 1 && differenceY == 1))
            return true;
        return false;
    }
    
    private boolean checkWinningStatus() {
        boolean hasWon = true;
        
        int num = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                try {
                    if (Integer.parseInt(boxes[i][j].getText().trim()) != num) {
                        hasWon = false;
                    }
                } catch (NumberFormatException e) {
                    hasWon  = false;
                }
                num++;
                if(num==9) return hasWon;
            }
        }
        
        return hasWon;
    }
    
    protected void makeMove(int positionX, int positionY) {
        if (canMakeMove(positionX, positionY)) {
        	Color myColor2 = new Color(180,199,231);//back border
        	Color myColor1 = new Color(103,128,199); // colored non empty
            boxes[emptyBox.getPositionX()][emptyBox.getPositionY()].setBackground(myColor1);
            boxes[positionX][positionY].setBackground(myColor2);
            Box box = boxes[positionX][positionY];
            emptyBox.setText(box.getText());
            System.out.println("Clicked on : " + box.getText());
            emptyBox.setEnabled(true);
            box.setEnabled(false);
            box.setText("");
            emptyBox = box;
            if(init==1) {
                movesCount++;
                playArea.setCount(movesCount);
            }
            if (checkWinningStatus() && init!=0) {
                //System.out.println("Yippee  , You Won!!");
                playArea.showWinningDialog();
            }
            
        }
    }    

    private ArrayList<Box> getValidMoves(Box boxes[][]) {
        ArrayList<Box> validMoves = new ArrayList<Box>();
        int emptyBoxX = emptyBox.getPositionX();
        int emptyBoxY = emptyBox.getPositionY();
        
        for (int i=-1; i<=1; i++) {
            for (int j=-1; j<=1; j++) {
                if(Math.abs(j)==Math.abs(i)) continue;
                int x = emptyBoxX + i;
                int y = emptyBoxY + j;
                System.out.println("do "+x+y);
                if(x < 3 && x >= 0 && y < 3 && y >= 0) {
                    validMoves.add(boxes[x][y]);
                }
            }
        }

        return validMoves;
    }
    
    private Box[][] getRandomBoxes() {
        
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 8; i++) {
            list.add(i);
        }
        
        Box[][] boxes = new Box[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int boxValue = list.get(3*i+j) + 1;		//Value b/w [1 and 9]
                boxes[i][j] = new Box(i, j, boxValue);
                boxes[i][j]. setForeground(Color. WHITE);
                if (boxValue == 9) {
                    boxes[i][j].setEnabled(false); //disable 9th box > we can red it 
                    boxes[i][j].setText("");
                    Color myColor2 = new Color(180,199,231);
                    boxes[i][j].setBackground(myColor2);
                    emptyBox = boxes[i][j];
                }
            }
        }
        
        this.boxes = boxes;
        makeMove(2, 1);
        //time to randomize the boxes by making a random number of moves
        Random random = new Random();
        int minMovesCount = 5;                                 //100 is sweet spot
        int movesCount =  minMovesCount + random.nextInt(10);  //HARDNESS OF GAME 1000 is sweet spot
        
        
        for (int i = 0; i < movesCount; i++) {
            ArrayList<Box> validMoves = getValidMoves(boxes);
            int r = random.nextInt(validMoves.size());
            Box randomBox = validMoves.get(r);
            makeMove(randomBox.getPositionX(), randomBox.getPositionY());
        }
        init = 1;
        Color myColor2 = new Color(180,199,231);
        boxes[emptyBox.getPositionX()][emptyBox.getPositionY()].setBackground(myColor2);
        return boxes;
    }

    void setPlayerData(String name) {
        PlayerData playerData = new PlayerData(name, movesCount);
        scoreboard.addPlayerData(playerData);
    }
}
