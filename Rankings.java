import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

//The Rankings class creates a JPanel that displays the relative rankings of
//various players in a chess tournament

public class Rankings extends JPanel{

  JPanel content;
  JFrame frame;
  //count the number of players
  int count = 1;


  public Rankings(){

    //Create a graphical user interface
    super();
    content = this;
    frame = new JFrame();
    Font font = new Font("Verdana", Font.BOLD, 36);
    content.setFont(font);

    content.setLayout(new GridLayout(Competition.players.size(),2));
    content.setVisible(true);
  }

//This function can be called by the AddPlayer class to add new players to the rankings.
//It can also be called when a result is inputted via the Competition class to reorder the rankings.

  public void addPlayer(Player p){
    JLabel label = new JLabel(count+"."); //the number the player is ranked
    count++;
    JButton nameLabel = new JButton(p.name);//the player's name

    //If the user clicks on the player's name, they get information about that player
    nameLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
        String s = "Name: " + p.name + "\n" + "Points: " + p.points + "\n Rating: " + p.rating + "\n Age: " + p.age + "\n Nationality: " + p.nationality;
        JOptionPane.showMessageDialog(frame, s);
      }
    });

    content.add(label);
    content.add(nameLabel);
    content.revalidate();
    content.repaint();
  }
}
