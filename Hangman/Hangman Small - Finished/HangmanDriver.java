/*
     Author: 

       Date:
  
Description:



*/

import javax.swing.JFrame;

public class HangmanDriver
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Hangman by *your name here*");
      frame.setSize(1100, 650);
      frame.setLocation(100, 50);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new HangmanPanel());
      frame.setResizable(false);
      frame.setVisible(true);
   }
}