/*
     Author: 

*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HangmanPanel extends JPanel
{
   private HangmanDisplay display;
   private JButton button1, button2;
   private JLabel lblGraphic;
   
   public HangmanPanel()
   {
      // Main panel has null layout
      setLayout(null);
      
      // The display object containing the GUI objects
      display = new HangmanDisplay();
      display.setBounds(100, 100, 2000, 1000);
      add(display);
        
      // Button at the bottom to allow the user to quit the app
      JButton btnExit = new JButton("Exit");
      btnExit.setFont(new Font("Arial", Font.PLAIN, 40));
      btnExit.addActionListener(new ListenerExit());
      btnExit.setBounds(1100-125, 1100, 250, 55);
      add(btnExit);
            
   }
   
   private class ListenerExit implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         // Code to quit the app
         System.exit(0);                  
      }
   }
}