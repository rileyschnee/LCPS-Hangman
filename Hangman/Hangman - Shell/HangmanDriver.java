/*
     Author: 

*/

import javax.swing.JFrame;

public class HangmanDriver
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame("Hangman by *your name here*");
      frame.setSize(2200, 1300);
      frame.setLocation(900, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setContentPane(new HangmanPanel());
      frame.setResizable(false);
      frame.setVisible(true);
   }
}