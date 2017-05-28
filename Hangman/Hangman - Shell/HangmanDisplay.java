/*
             Author: 
   
               Date:
          
         Time Spent:
(Outside of Class)
     
        Description:
   
             Review:

   
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class HangmanDisplay extends JPanel
{ 
   private JLabel lblNumPlayers, lblEnterWord, lblCurGuess;  // Labels for "# of Players", prompting Player 2 to enter word and displaying current guess status
   private JLabel lblGuess, lblLetters, lblReplay;        // Labels for over "Contains" button, list of guessed letters and "Play Again?"
   private JTextField txtEnterWord, txtGuess;             // Textboxes for user input
   private JButton btnOnePlayer, btnTwoPlayer, btnSetWord, btnGuess, btnReplayY, btnReplayN;  // All buttons
   private String[] arrWords;                                // Array to hold the words in the dictionary
   private String[] goalWordArr = new String[20];            // Array to hold the letters of the word to guess
   private String goalWordStr;                               // String representation of goalWordArr
   private String[] guessWordArr = new String [20];          // Array to hold the letters of the word that have been guessed
   private String guessWordStr;                              // String representation of guessWordArr
   private String guessedLetters = "";                       // String containing guessed letters (with a space between each)
   private int numIncorrectGuesses = 0;                      // The number of times the player has incorrectly guessed
   private boolean g1 = false, g2 = false, g3 = false;       // Booleans to power drawing stick-figure body parts
   private boolean g4 = false, g5 = false, g6 = false;  
   private boolean lose = false, win = false;                // Booleans determining when a player has won or lost
            
   public HangmanDisplay()
   {  
      Scanner infile = null;     // input file
      
      // Read in the dictionary file (words.txt); leverage code!
      
      /* ENTER CODE HERE */
      
      
      // Fill in arrWords with the words from the dictionary file
      
      /* ENTER CODE HERE */


      // Sort array

      /* ENTER CODE HERE */


      // Give the main panel null layout

      /* ENTER CODE HERE */
      
      
      // Initialize goalWordStr, guessWordStr to be "" and goalWordArr, guessWordStr, guessWordArr to be filled with ""
      
      
      /* ENTER CODE HERE */
      
            
         
   // LABELS, TEXTBOXES AND BUTTONS -- DO NOT CHANGE! THESE HAVE BEEN GIVEN TO YOU
      
      // Label and Buttons for asking # of players
      lblNumPlayers = new JLabel("# of Players", SwingConstants.CENTER);
      lblNumPlayers.setFont(new Font("Arial", Font.PLAIN, 50));
      lblNumPlayers.setBounds(1400, 25, 500, 55);
      add(lblNumPlayers);
      
      btnOnePlayer = new JButton("One");
      btnOnePlayer.setFont(new Font("Arial", Font.PLAIN, 40));
      btnOnePlayer.addActionListener(new ListenerOnePlayer());
      btnOnePlayer.setBounds(1450, 100, 200, 55);
      add(btnOnePlayer);
      
      btnTwoPlayer = new JButton("Two");
      btnTwoPlayer.setFont(new Font("Arial", Font.PLAIN, 40));
      btnTwoPlayer.addActionListener(new ListenerTwoPlayer());
      btnTwoPlayer.setBounds(1650, 100, 200, 55);
      add(btnTwoPlayer);
      
      // Label, Textbox and Button for asking Player 2 to enter word to be guessed
      // (Shows up if there are two players)
      lblEnterWord = new JLabel("<html><br>Player One, look away.</br>" +
         "<br>Player Two, enter a word.</br><br>(Between 4 and 20 characters)</br>", SwingConstants.LEFT);
      lblEnterWord.setFont(new Font("Arial", Font.PLAIN, 40));
      lblEnterWord.setBounds(1450, 25, 600, 175);
      add(lblEnterWord);
      lblEnterWord.setVisible(false);
         
      txtEnterWord = new JTextField("", 15);
      txtEnterWord.setFont(new Font("Arial", Font.PLAIN, 40));
      txtEnterWord.setHorizontalAlignment(SwingConstants.LEFT);
      txtEnterWord.setBounds(1450, 225, 400, 55);
      add(txtEnterWord);
      txtEnterWord.setVisible(false);
         
      btnSetWord = new JButton("Set as Word");
      btnSetWord.setFont(new Font("Arial", Font.PLAIN, 40));
      btnSetWord.addActionListener(new ListenerEnter2PlayerWord());
      btnSetWord.setBounds(1500, 300, 300, 55);
      add(btnSetWord);
      btnSetWord.setVisible(false);
   
      // Label, Textbox and Button for a players guess
      lblGuess = new JLabel("Guess Letter or Word", SwingConstants.CENTER);
      lblGuess.setFont(new Font("Arial", Font.PLAIN, 50));
      lblGuess.setBounds(1400, 400, 500, 55);
      add(lblGuess);
      
      txtGuess = new JTextField("", 15);
      txtGuess.setFont(new Font("Arial", Font.PLAIN, 40));
      txtGuess.setHorizontalAlignment(SwingConstants.LEFT);
      txtGuess.setBounds(1450, 500, 400, 55);
      add(txtGuess);
      
      btnGuess = new JButton("Contains");
      btnGuess.setFont(new Font("Arial", Font.PLAIN, 40));
      btnGuess.addActionListener(new ListenerGuess());
      btnGuess.setBounds(1500, 575, 300, 55);
      add(btnGuess);
      btnGuess.setEnabled(false);
      
      // Status of guessWordStr and guessedLetters
      lblCurGuess = new JLabel(guessWordStr, SwingConstants.CENTER);
      lblCurGuess.setFont(new Font("Arial", Font.PLAIN, 70));
      lblCurGuess.setBounds(100, 700, 1800, 100);
      add(lblCurGuess);
      
      lblLetters = new JLabel(guessedLetters, SwingConstants.CENTER);
      lblLetters.setFont(new Font("Arial", Font.PLAIN, 50));
      lblLetters.setBounds(100, 800, 1800, 100);
      add(lblLetters);
      
      // Prompt for Replay and Buttons
      lblReplay = new JLabel("Play Again?", SwingConstants.CENTER);
      lblReplay.setFont(new Font("Arial", Font.PLAIN, 50));
      lblReplay.setBounds(750, 25, 500, 55);
      add(lblReplay);
      lblReplay.setVisible(false);
      
      btnReplayY = new JButton("Yes");
      btnReplayY.setFont(new Font("Arial", Font.PLAIN, 40));
      btnReplayY.addActionListener(new ListenerYesReplay());
      btnReplayY.setBounds(775, 100, 200, 55);
      add(btnReplayY);
      btnReplayY.setVisible(false);
      
      btnReplayN = new JButton("No");
      btnReplayN.setFont(new Font("Arial", Font.PLAIN, 40));
      btnReplayN.addActionListener(new ListenerNoReplay());
      btnReplayN.setBounds(1000, 100, 200, 55);
      add(btnReplayN);
      btnReplayN.setVisible(false);
      
      // Calls PaintComponent to create hangman gallows
      repaint();
   }
   
   // METHODS -- DO NOT CHANGE! THESE HAVE BEEN GIVEN TO YOU
   private void initGuessString(String s, String[] sAGoal, JLabel lab){
      s = "";
      for(int i = 0; i < sAGoal.length; i++){
         if(sAGoal[i].equals(" "))
            s += " ";
         else if(sAGoal[i].equals(""))
            s += "";
         else
            s += "-";
      }
      lab.setText(s);
   }
   private void updateGuessString(String s, String[] sA, JLabel lab){
      s = "";
      for(int z = 0; z < sA.length; z++){
         s += sA[z];
      }
      lab.setText(s);
      if(s.indexOf("-") == -1)
         win = true;
   }
   private void tryAgain(){
      lblReplay.setVisible(true);
      btnReplayY.setVisible(true);
      btnReplayN.setVisible(true);
   }
 
   // LISTENERS

   // Listener for btnOnePlayer; this is completely done so you don't need to change it!
   private class ListenerOnePlayer implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String word = "";
         // Picks random index of arrWords to find random word
         int rand = (int)(Math.random()*arrWords.length);
         word = arrWords[rand].toLowerCase(); // toLowerCase() prevents issues w/uppercase vs lowercase guesses
         for(int i = 0; i < word.length(); i++){
            goalWordArr[i] = word.substring(i, i+1);
         }
         goalWordStr = word;
         // Fills guessWordArr with dashes where there are letters in the goalWordArr
         for(int i = 0; i < word.length(); i++){
            if(goalWordArr[i].equals(" "))
               guessWordArr[i] = " ";
            else if(goalWordArr[i].equals(""))
               guessWordArr[i] = "";
            else
               guessWordArr[i] = "-";
         }
         initGuessString(guessWordStr, goalWordArr, lblCurGuess);
         
         btnOnePlayer.setVisible(false);
         btnTwoPlayer.setVisible(false);
         lblNumPlayers.setVisible(false);
      
         btnGuess.setEnabled(true);
      }
   }
   // Listener for btnTwoPlayer; this is completely done so you don't need to change it!
   private class ListenerTwoPlayer implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String word = "";
         
         btnOnePlayer.setVisible(false);
         btnTwoPlayer.setVisible(false);
         lblNumPlayers.setVisible(false);
      
         lblEnterWord.setVisible(true);
         txtEnterWord.setVisible(true);
         btnSetWord.setVisible(true);         
      }
   }
   // Listener for btnSetWord; this is completely done so you don't need to change it!
   private class ListenerEnter2PlayerWord implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         String word = "";
         word = txtEnterWord.getText().toLowerCase(); // toLowerCase() prevents issues w/uppercase vs lowercase guesses
         if(word.length() <= 20 && word.length() >= 4){
            for(int i = 0; i < word.length(); i++){
               goalWordArr[i] = word.substring(i, i+1);
            }  
            goalWordStr = word;  
            // Fills guessWordArr with dashes where there are letters in the goalWordArr 
            for(int i = 0; i < word.length(); i++){
               if(goalWordArr[i].equals(" "))
                  guessWordArr[i] = " ";
               else if(goalWordArr[i].equals(""))
                  guessWordArr[i] = "";
               else
                  guessWordArr[i] = "-";
            }
            initGuessString(guessWordStr, goalWordArr, lblCurGuess);
                     
            lblEnterWord.setVisible(false);
            txtEnterWord.setVisible(false);
            btnSetWord.setVisible(false);
         
            btnOnePlayer.setVisible(false);
            btnTwoPlayer.setVisible(false);
            lblNumPlayers.setVisible(false);
            txtEnterWord.setText("");
            
            btnGuess.setEnabled(true);
         }
         else{ // if the word is not valid
            txtEnterWord.setText("Invalid Word");
         }          
      }
   }
   // Listener for btnReplayY
   // When a user says they would like to replay the game...
   private class ListenerYesReplay implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         lblReplay.setVisible(false);
         btnReplayY.setVisible(false);
         btnReplayN.setVisible(false);
         
         // Look back at the variables at the top of the page
         // Reset all variables, including arrays, strings, and booleans
         
         
         
         /* ENTER CODE HERE */
         
         
         
         // DO NOT TOUCH! THIS HAS BEEN GIVEN TO YOU
         lblNumPlayers.setVisible(true);
         btnOnePlayer.setVisible(true);
         btnTwoPlayer.setVisible(true);
         lblCurGuess.setText("");
         lblLetters.setText("");
         txtGuess.setText("");     
         btnGuess.setEnabled(false);  
         repaint();
      
      }
   }
   // Listener for btnReplayN
   // When the user says they would not like to replay the game...
   private class ListenerNoReplay implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {         
         // Shutdown the game (HINT: Look at ListenerExit in HangmanPanel)
         /* ENTER CODE HERE */

      }
   }
   // Listener for Contains button -- Checks if the single-letter or full-word guess is in the goal word 
   private class ListenerGuess implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {  
         boolean found = false;           // Boolean for single-letter guess is in the string
         boolean correctGuess = false;    // Boolean for full-word guess matches the string
      
         // Get the word from the txtGuess textbox and set it to a string variable called myGuess...make sure it is lowercase
         /* ENTER CODE HERE */
         
         // If-statement: Make sure the user typed something in, that their guess is not in the list of guessedLetters and they did guess a space
         /* ENTER CODE HERE */
         
             // If they guessed a word (more than one character)
             /* ENTER CODE HERE */   
             
               // See if it matches the goalWordStr
               /* ENTER CODE HERE */
               
               
            /* If they guessed a letter
            
               1. Keep track of the guess, adding it to guessedLetters and update lblLetters

               2. Iterate through the goalWordArr

                  2-1. See if goalWordArr contains myGuess
                   
                     2-1-1. Make sure to update the guessWordArr  
            */   
            /* ENTER CODE HERE */                             

            // Check the booleans
            if(found){ // Do not change! But understand what is happening... Look at the updateGuessString method
               updateGuessString(guessWordStr, guessWordArr, lblCurGuess); 
               if(guessWordStr.indexOf("-")<0)
                  win = true;
            } else if(correctGuess){ // Update lblCurGuess, change win boolean, repaint to get win message
               
              /* ENTER CODE HERE */
              
            } else if(!found && !correctGuess){ 
               // Increment numIncorrectGuesses and use it to update guess booleans
               // NOTE: If they get to g6, they lose...
               /* ENTER CODE HERE */
               
               // Repaint to get body parts
               /* ENTER CODE HERE */
            }
            // Reset the txtGuess textbox
            /* ENTER CODE HERE */
         }
      }
   } 
   /* <- BEFORE DOING ACTIVITY FOUR, TAKE OUT THIS COMMENT START
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D gD = (Graphics2D) g;   // Allows us to set stroke width
      gD.setStroke(new BasicStroke(5)); // Sets stroke width
      gD.setColor(Color.BLUE);          // You may change the color

      // Gallows -- This has been given to you!
      gD.drawLine(200, 0, 200, 600); // vertical support
      gD.drawLine(200, 0, 500, 0); // beam across
      gD.drawLine(500, 0, 500, 50); // downward notch
      gD.drawLine(0, 600, 400, 600); // base
      
      // Draw the stick figure parts
      if (g1) { //head
         //ENTER CODE HERE 
      } 
      if (g2) { //body
         // ENTER CODE HERE
      }  
      if (g3) { //left leg
         // ENTER CODE HERE 
      }  
      if (g4) { //right leg
         // ENTER CODE HERE 
      }  
      if (g5) { //left arm
         // ENTER CODE HERE 
      }  
      if (g6) { //right arm
         // ENTER CODE HERE
      } 
      // If they lose -- Don't change this! It has been given to you...
      if (lose){
         gD.setColor(Color.RED);
         gD.setFont(new Font( "Arial", Font.BOLD, 120));
         gD.drawString("Game Over.", 650, 450);
         gD.setFont(new Font( "Arial", Font.BOLD, 50));
         gD.drawString(goalWordStr, 150, 760);
      }
      if (win) {
         gD.setColor(Color.GREEN);
         gD.setFont(new Font( "Arial", Font.BOLD, 120));
         gD.drawString("You Win!", 750, 450);
      }
      // Either way, call the method that asks if we want to play again
      if(win || lose)
         tryAgain();       
   }
} 
   BEFORE DOING ACTIVITY FOUR, TAKE OUT THIS COMMENT END -> */
