package nineboxpuzzle;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import javafx.scene.layout.Border;


public class Box extends JButton {
    private int positionX;
    private int positionY;
    
    protected int getPositionX() {
        return positionX;
    }
    
    protected int getPositionY() {
        return positionY;
    }
    
    
    // used to replace the box position
    Box(int positionX, int positionY, int num) {
        super(String.valueOf(num));			// string to int then pass the value 
        this.positionX = positionX;
        this.positionY = positionY;
        Color myColor2 = new Color(180,199,231);
        LineBorder thickBorder = new LineBorder(myColor2, 12);
        this.setBorder(thickBorder);
        
        Color myColor1 = new Color(103,128,199);
        setBackground(myColor1);
        setFont(new Font("Tahoma", Font.BOLD, 100));
        setFocusable(false);
    }
}
