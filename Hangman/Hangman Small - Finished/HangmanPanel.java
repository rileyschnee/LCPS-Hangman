/*
     Author: 

       Date:
  
Description:



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
      // Set the main panel to have null layout
      setLayout(null);
      
      // The display object containing the GUI objects
      display = new HangmanDisplay();
      display.setBounds(50, 50, 1000, 500);
      add(display);
        
      // Create a button at the bottom to allow the user to quit the app
      JButton btnExit = new JButton("Exit");
      btnExit.setFont(new Font("Arial", Font.PLAIN, 20));
      btnExit.addActionListener(new ListenerExit());
      btnExit.setBounds(550-65, 550, 125, 25);
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