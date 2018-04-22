import java.util.Arrays;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.Collections;

public class ChessScorekeeperGUI extends JPanel{

  ChessScorekeeper gameScore = new ChessScorekeeper();

  public ChessScorekeeperGUI(){
    super();
    JPanel content = this;
    JLabel title = new JLabel("<html><h1>Chess Scorekeeper</h1></html>");
    title.setHorizontalAlignment(SwingConstants.CENTER);
    title.setVerticalAlignment(SwingConstants.CENTER);
    JLabel whitePlayerLabel = new JLabel("<html><h2>White: </h2></html>");
    JTextField enterWhiteName = new JTextField("Enter white player name");
    JTextField enterWhiteRating = new JTextField("Enter white player rating");
    JLabel blackPlayerLabel = new JLabel("<html><h2>Black: </h2></html>");
    JTextField enterBlackName = new JTextField("Enter black player name");
    JTextField enterBlackRating = new JTextField("Enter black player rating");
    JLabel result = new JLabel("<html><h2>Result: </h2></html>");
    JComboBox<String> resultCB = new JComboBox<String>();
    resultCB.addItem("1-0");
    resultCB.addItem("1/2-1/2");
    resultCB.addItem("0-1");
    JButton submit = new JButton("Submit");
    JPanel bottom = new JPanel();
    bottom.setLayout(new GridLayout(3,3));
    bottom.add(whitePlayerLabel);
    bottom.add(enterWhiteName);
    bottom.add(enterWhiteRating);
    bottom.add(blackPlayerLabel);
    bottom.add(enterBlackName);
    bottom.add(enterBlackRating);
    bottom.add(result);
    bottom.add(resultCB);
    bottom.add(submit);
    content.setLayout(new BorderLayout());
    content.add(title, BorderLayout.PAGE_START);
    content.add(bottom, BorderLayout.PAGE_END);
    submit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
        gameScore.whitePlayer = enterWhiteName.getText();
        gameScore.whiteRating = Integer.parseInt(enterWhiteRating.getText());
        gameScore.blackPlayer = enterBlackName.getText();
        gameScore.blackRating = Integer.parseInt(enterBlackRating.getText());
        if(resultCB.getSelectedIndex()==0){
          gameScore.result = 1;
        }
        else if(resultCB.getSelectedIndex()==1){
          gameScore.result = 0;
        }
        else if(resultCB.getSelectedIndex()==2){
          gameScore.result = -1;
        }
      }
   });

  }

  private static void createAndShowGUI(){
        //Create and set up the window.
        JFrame frame = new JFrame("Chess Scorekeeper");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new ChessScorekeeperGUI();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800,800);
  }

  public static void main(String[] args) throws FileNotFoundException{
     //Schedule a job for the event-dispatching thread:
     //creating and showing this application's GUI.


     //run GUI
     javax.swing.SwingUtilities.invokeLater(new Runnable() {
         public void run(){
             createAndShowGUI();
         }
     });
  }
}
