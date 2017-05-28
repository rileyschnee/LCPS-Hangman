/*
        Author: 
   
          Date:
     
   Description:
   
   
   
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
      
      // Read in the dictionary file; leverage code!
      try
      {
         infile = new Scanner( new File("words.txt") );      
      }
      catch (FileNotFoundException e) {
         JOptionPane.showMessageDialog(null, "Error: File not found.");
         System.exit(0);
      }
      // Fill in arrWords with the words from the dictionary file
      int numWords = infile.nextInt();
      arrWords = new String[numWords];
      
      for(int x=0; x < numWords; x++)
         arrWords[x] = infile.next();
   
      infile.close();
      
      // Sort array
      Arrays.sort(arrWords);
      
      // Give the main panel null layout
      setLayout(null);
      
      // Initialize goalWordStr, guessWordStr to be "" and goalWordArr, guessWordStr, guessWordArr to be filled with ""
      goalWordStr = "";
      for(int i = 0; i < goalWordArr.length; i++){
         goalWordArr[i] = "";
         goalWordStr += goalWordArr[i];
      }
      for(int i = 0; i < goalWordArr.length; i++){
         guessWordArr[i] = "";
      }
   
   // LABELS, TEXTBOXES AND BUTTONS -- DO NOT CHANGE! THESE HAVE BEEN GIVEN TO YOU
      
      // Label and Buttons for asking # of players
      lblNumPlayers = new JLabel("# of Players", SwingConstants.CENTER);
      lblNumPlayers.setFont(new Font("Arial", Font.PLAIN, 25));
      lblNumPlayers.setBounds(700, 12, 250, 25);
      add(lblNumPlayers);
      
      btnOnePlayer = new JButton("One");
      btnOnePlayer.setFont(new Font("Arial", Font.PLAIN, 20));
      btnOnePlayer.addActionListener(new ListenerOnePlayer());
      btnOnePlayer.setBounds(725, 50, 100, 25);
      add(btnOnePlayer);
      
      btnTwoPlayer = new JButton("Two");
      btnTwoPlayer.setFont(new Font("Arial", Font.PLAIN, 20));
      btnTwoPlayer.addActionListener(new ListenerTwoPlayer());
      btnTwoPlayer.setBounds(825, 50, 100, 25);
      add(btnTwoPlayer);
      
      // Label, Textbox and Button for asking Player 2 to enter word to be guessed
      // (Shows up if there are two players)
      lblEnterWord = new JLabel("<html><br>Player One, look away.</br>" +
         "<br>Player Two, enter a word.</br><br>(Between 4 and 20 characters)</br>", SwingConstants.LEFT);
      lblEnterWord.setFont(new Font("Arial", Font.PLAIN, 20));
      lblEnterWord.setBounds(725, 12, 300, 85);
      add(lblEnterWord);
      lblEnterWord.setVisible(false);
         
      txtEnterWord = new JTextField("", 7);
      txtEnterWord.setFont(new Font("Arial", Font.PLAIN, 20));
      txtEnterWord.setHorizontalAlignment(SwingConstants.LEFT);
      txtEnterWord.setBounds(725, 112, 200, 25);
      add(txtEnterWord);
      txtEnterWord.setVisible(false);
         
      btnSetWord = new JButton("Set as Word");
      btnSetWord.setFont(new Font("Arial", Font.PLAIN, 20));
      btnSetWord.addActionListener(new ListenerEnter2PlayerWord());
      btnSetWord.setBounds(750, 150, 150, 25);
      add(btnSetWord);
      btnSetWord.setVisible(false);
   
      // Label, Textbox and Button for a players guess
      lblGuess = new JLabel("Guess Letter or Word", SwingConstants.CENTER);
      lblGuess.setFont(new Font("Arial", Font.PLAIN, 25));
      lblGuess.setBounds(700, 200, 250, 25);
      add(lblGuess);
      
      txtGuess = new JTextField("", 15);
      txtGuess.setFont(new Font("Arial", Font.PLAIN, 20));
      txtGuess.setHorizontalAlignment(SwingConstants.LEFT);
      txtGuess.setBounds(725, 250, 200, 25);
      add(txtGuess);
      
      btnGuess = new JButton("Guess");
      btnGuess.setFont(new Font("Arial", Font.PLAIN, 20));
      btnGuess.addActionListener(new ListenerGuess());
      btnGuess.setBounds(750, 285, 150, 25);
      add(btnGuess);
      btnGuess.setEnabled(false);
      
      // Status of guessWordStr and guessedLetters
      lblCurGuess = new JLabel(guessWordStr, SwingConstants.CENTER);
      lblCurGuess.setFont(new Font("Arial", Font.PLAIN, 35));
      lblCurGuess.setBounds(50, 350, 900, 50);
      add(lblCurGuess);
      
      lblLetters = new JLabel(guessedLetters, SwingConstants.CENTER);
      lblLetters.setFont(new Font("Arial", Font.PLAIN, 25));
      lblLetters.setBounds(50, 400, 900, 50);
      add(lblLetters);
      
      // Prompt for Replay and Buttons
      lblReplay = new JLabel("Play Again?", SwingConstants.CENTER);
      lblReplay.setFont(new Font("Arial", Font.PLAIN, 25));
      lblReplay.setBounds(375, 12, 250, 25);
      add(lblReplay);
      lblReplay.setVisible(false);
      
      btnReplayY = new JButton("Yes");
      btnReplayY.setFont(new Font("Arial", Font.PLAIN, 20));
      btnReplayY.addActionListener(new ListenerYesReplay());
      btnReplayY.setBounds(390, 50, 100, 25);
      add(btnReplayY);
      btnReplayY.setVisible(false);
      
      btnReplayN = new JButton("No");
      btnReplayN.setFont(new Font("Arial", Font.PLAIN, 20));
      btnReplayN.addActionListener(new ListenerNoReplay());
      btnReplayN.setBounds(500, 50, 100, 25);
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
         System.out.print(goalWordStr);
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
         goalWordArr = new String[20];
         goalWordStr = "";
         guessWordArr = new String [20];
         guessWordStr = "";
         for(int i = 0; i < goalWordArr.length; i++){
            goalWordArr[i] = "";
            goalWordStr += goalWordArr[i];
         }
         for(int i = 0; i < goalWordArr.length; i++){
            guessWordArr[i] = "";
         }
         guessedLetters = "";
         numIncorrectGuesses = 0;
         g1 = false;
         g2 = false;
         g3 = false;
         g4 = false;
         g5 = false;
         g6 = false;
         lose = false;
         win = false; 
         
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
         System.exit(0);        
      }
   }
   // Listener for Contains button -- Checks if the single-letter or full-word guess is in the goal word 
   private class ListenerGuess implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {  
         boolean found = false;           // Boolean for single-letter guess is in the string
         boolean correctGuess = false;    // Boolean for full-word guess matches the string
      
         // Get the word from the txtGuess textbox and set it to a string variable named myGuess...make sure it is lowercase
         String myGuess = txtGuess.getText().toLowerCase();  
         
         // If-statement: Make sure the user typed something in, that their guess is not in the list of guessedLetters and they did guess a space
         if ( !myGuess.equals(null) && !myGuess.equals(" ") && !guessedLetters.contains(myGuess) ) 
         {
            
            // If they guessed a word (more than one character)
            if( myGuess.length() > 1){
               // See if it matches the goalWordStr
               if(myGuess.equals(goalWordStr)){
                  correctGuess = true;
                  btnGuess.setEnabled(false);
               }
            }
            // If they guessed a letter
            else{
               // Keep track of the guess, adding it to guessedLetters and update lblLetters
               guessedLetters += " " + myGuess;
               lblLetters.setText(guessedLetters);
               // Iterate through the goalWordArr
               for (int x = 0; x < goalWordStr.length(); x++)     
               {
                  // See if goalWordArr contains myGuess
                  if ( goalWordArr[x].contains(myGuess) )    // Contains checks a String to see if it contains another String
                  {
                     if(!found)
                        found = true;   
                     // Make sure to update the guessWordArr                                  
                     guessWordArr[x] = myGuess;
                  }
               }
            }
            // Check the booleans
            if(found){ // Do not change! But understand what is happening... Look at the updateGuessString method
               updateGuessString(guessWordStr, guessWordArr, lblCurGuess); 
            } else if(correctGuess){ // Update lblCurGuess, change win boolean, repaint to get win message
               lblCurGuess.setText(goalWordStr);
               win = true;
               repaint();
            } else if(!found && !correctGuess){ 
               // Increment numIncorrectGuesses and use it to update guess booleans
               numIncorrectGuesses++;
               if(numIncorrectGuesses == 1){
                  g1 = true;
               }
               else if(numIncorrectGuesses == 2){
                  g2 = true;
               }
               else if(numIncorrectGuesses == 3){
                  g3 = true;
               }
               else if(numIncorrectGuesses == 4){
                  g4 = true;
               }
               else if(numIncorrectGuesses == 5){
                  g5 = true;
               }
               else if(numIncorrectGuesses == 6){
                  g6 = true;
               }
               // Repaint to get body parts
               repaint();
            }
            // Reset the txtGuess textbox
            txtGuess.setText("");
         }
      }
   }
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D gD = (Graphics2D) g;   // Allows us to set stroke width
      gD.setStroke(new BasicStroke(3)); // Sets stroke width
      gD.setColor(Color.BLUE);          // You may change the color

      // Gallows -- This has been given to you!
      gD.drawLine(100, 0, 100, 300); // vertical support
      gD.drawLine(100, 0, 250, 0); // beam across
      gD.drawLine(250, 0, 250, 25); // downward notch
      gD.drawLine(0, 300, 200, 300); // base
      
      // Draw the stick figure parts
      if (g1) {
         gD.drawOval(212, 25, 75, 75); // head
      } 
      if (g2) {
         gD.drawLine(250, 100, 250, 200); // body
      }  
      if (g3) {
         gD.drawLine(300, 250, 250, 200); // left leg
      }  
      if (g4) {
         gD.drawLine(200, 250, 250, 200); // right leg
      }  
      if (g5) {
         gD.drawLine(300, 175, 250, 150); // left arm
      }  
      if (g6) {
         gD.drawLine(200, 175, 250, 150); // right arm
         lose = true;
      } 
      // If they lose -- Don't change this! It has been given to you...
      if (lose){
         gD.setColor(Color.RED);
         gD.setFont(new Font( "Arial", Font.BOLD, 60));
         gD.drawString("Game Over.", 325, 225);
         gD.setFont(new Font( "Arial", Font.BOLD, 25));
         gD.drawString(goalWordStr, 62, 385);
      }
      if (win) {
         gD.setColor(Color.GREEN);
         gD.setFont(new Font( "Arial", Font.BOLD, 60));
         gD.drawString("You Win!", 375, 225);
      }
      // Either way, call the method that asks if we want to play again
      if(win || lose)
         tryAgain();       
   }
}