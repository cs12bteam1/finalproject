import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class AddPlayer extends JPanel{

  public AddPlayer(){
    super();
    JPanel content = this;


    JLabel header = new JLabel("ADD PLAYERS!");
    content.add(header, BorderLayout.PAGE_START);

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

    JButton submit = new JButton("Submit");
    submit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String n = name.getText();
        String na = nationality.getText();
        String a = age.getText();
        String ra = rating.getText();
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
    });

    content.add(input, BorderLayout.CENTER);
    content.add(submit, BorderLayout.PAGE_END);
  }

}
