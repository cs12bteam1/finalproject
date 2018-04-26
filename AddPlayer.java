import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

//The AddPlayer class creates a JPanel that allows the user to create new players
//that will be participating in a chess tournament.

public class AddPlayer extends JPanel{

  public AddPlayer(){

    //create the GUI
    super();
    JPanel content = this;

    content.setBackground(Color.BLUE);
    content.setLayout(new GridLayout(3,1));


    Font font = new Font("Verdana", Font.BOLD, 24);
		//Font reducedFont = new Font("Verdana", Font.BOLD, 24);

    JLabel header = new JLabel("ADD PLAYERS!");
    header.setForeground(Color.WHITE);
    content.add(header);

    header.setFont(font);

    JOptionPane warning = new JOptionPane();

    //Let the user enter information

    JPanel input = new JPanel();
    input.setLayout(new GridLayout(3,1));
    JTextField name = new JTextField("Add name");
    JTextField nationality = new JTextField("Add nationality");
    JTextField age = new JTextField("Add age");
    JTextField rating = new JTextField("Add rating");
    input.add(name);
    input.add(nationality);
    input.add(age);
    input.add(rating);

    //When the user submits a new player, update the players ArrayList and the Rankings

    JButton submit = new JButton("Submit");
    submit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String n = name.getText();
        String na = nationality.getText();
        String a = age.getText();
        String ra = rating.getText();
        if(isInteger(a)&&isInteger(ra)){
          Player p = new Player(n,na,Integer.parseInt(a),Integer.parseInt(ra));
          Competition.players.add(p);
          Competition.playerChooser2.addItem(p);
          Competition.playerChooser.addItem(p);
          Collections.sort(Competition.players);
          Competition.rankingsPanel.removeAll();
          Competition.rankingsPanel.updateUI();
          Competition.rankingsPanel.count = 1;
          for(int i = 0; i<Competition.players.size(); i++){
            Competition.rankingsPanel.addPlayer(Competition.players.get(i));
            System.out.println("Hi");
          }
        }
        else{
          warning.showMessageDialog(content,
              "Failed to submit: Please enter integers",
              "Inane error",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });

    JPanel page_end = new JPanel();
    page_end.add(submit, BorderLayout.CENTER);

    page_end.setBackground(Color.BLUE);

    content.add(input);
    content.add(page_end);
  }

  //check whether user input is valid

  public static boolean isInteger(String s){
    for (char c : s.toCharArray()){
      if(!Character.isDigit(c)) return false;
    }
    return true;
  }

}
