import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class AddPlayer extends JPanel{

  public AddPlayer(){
    super();
    JPanel content = this;

    content.setBackground(Color.BLUE);
    content.setLayout(new GridLayout(3,1));

    Font font = new Font("Verdana", Font.BOLD, 24);
		//Font reducedFont = new Font("Verdana", Font.BOLD, 24);

    JLabel header = new JLabel("ADD PLAYERS!");
    content.add(header);
    header.setFont(font);
    header.setForeground(Color.WHITE);

    JOptionPane warning = new JOptionPane();

    JPanel input = new JPanel();
    input.setLayout(new GridLayout(3,1));
    JTextField name = new JTextField("Add name");
    JTextField nationality = new JTextField("Add nationality");
    JTextField age = new JTextField("Add age");
    JTextField rating = new JTextField("Add rating");
    name.setFont(font);
    nationality.setFont(font);
    age.setFont(font);
    rating.setFont(font);
    input.add(name);
    input.add(nationality);
    input.add(age);
    input.add(rating);

    JPanel page_end = new JPanel();

    JButton submit = new JButton("Submit");
    submit.setFont(font);
    submit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String n = name.getText();
        String na = nationality.getText();
        String a = age.getText();
        String ra = rating.getText();
        if(isInteger(a) && isInteger(ra)){
          Player p = new Player(n,na,Integer.parseInt(a),Integer.parseInt(ra));
          Competition.players.add(p);
          Competition.playerChooser2.addItem(p);
          Competition.playerChooser.addItem(p);
          Collections.sort(Competition.players);
          Rankings.firstPlayer.setText(Competition.players.get(0).name);
          if(Competition.players.size()>1){
            Rankings.secondPlayer.setText(Competition.players.get(1).name);
          }
          if(Competition.players.size()>2){
            Rankings.thirdPlayer.setText(Competition.players.get(2).name);
          }
          if(Competition.players.size()>3){
            Rankings.fourthPlayer.setText(Competition.players.get(3).name);
          }
          if(Competition.players.size()>4){
            Rankings.fifthPlayer.setText(Competition.players.get(4).name);
          }
        }
        else {
            warning.showMessageDialog(content,
            "Failed to submit: Please enter integers",
            "Inane error",
            JOptionPane.ERROR_MESSAGE);
        }
      }
    });
    page_end.setBackground(Color.BLUE);
    page_end.add(submit, BorderLayout.CENTER);

    content.add(input);
    content.add(page_end);
  }

  public static boolean isInteger(String s){
    for (char c : s.toCharArray()){
      if(!Character.isDigit(c)) return false;
    }
    return true;
  }


}
