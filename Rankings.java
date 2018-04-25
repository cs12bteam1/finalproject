import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

//PhoneBookGUI creates a graphical interface that allows the user to input names and phone numbers.
//Then, the user can search for a the number of a person they know or vice versa.

public class Rankings extends JPanel{

  //Use treemaps to store map phone numbers to names, and the reverse

  JPanel content;
  JFrame frame;
  int count = 1;


  public Rankings(){

    //Create a graphical user interface
    super();
    content = this;
    frame = new JFrame();



    content.setLayout(new GridLayout(Competition.players.size(),2));
    content.setVisible(true);
  }

  public void addPlayer(Player p){
    JLabel label = new JLabel(count+".");
    count++;
    JButton nameLabel = new JButton(p.name);
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
