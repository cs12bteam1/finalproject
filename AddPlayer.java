import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AddPlayer extends JPanel{

  ArrayList<Player> players;

  public AddPlayer(){
    super();
    JPanel content = this;
    players = new ArrayList<Player>();


    JLabel header = new JLabel("ADD PLAYERS!");
    content.add(header, BorderLayout.PAGE_START);

    JPanel input = new JPanel();
    input.setLayout(new GridLayout(3,1));
    JTextField name = new JTextField("Add name");
    JTextField nationality = new JTextField("Add nationality");
    JTextField age = new JTextField("Add age");
    input.add(name);
    input.add(nationality);
    input.add(age);

    JButton submit = new JButton("Submit");
    submit.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String n = name.getText();
        String na = nationality.getText();
        String a = age.getText();
        players.add(new Player(n,na,a));
      }
    });

    content.add(input, BorderLayout.CENTER);
    content.add(submit, BorderLayout.PAGE_END);
  }

}
