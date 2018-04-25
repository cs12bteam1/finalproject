import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

//PhoneBookGUI creates a graphical interface that allows the user to input names and phone numbers.
//Then, the user can search for a the number of a person they know or vice versa.

public class Rankings extends JPanel{

  //Use treemaps to store map phone numbers to names, and the reverse

  public static JLabel firstPlayer = new JLabel("");
  public static JLabel secondPlayer = new JLabel("");
  public static JLabel thirdPlayer = new JLabel("");
  public static JLabel fourthPlayer = new JLabel("");
  public static JLabel fifthPlayer = new JLabel("");


  public Rankings(){

    //Create a graphical user interface
    super();
    JPanel content = this;

    JLabel placeOne = new JLabel("1. ");
    if(Competition.players.size()>0){
      firstPlayer.setText(Competition.players.get(0).name);
    }
    JLabel placeTwo = new JLabel("2. ");
    if(Competition.players.size()>1){
      secondPlayer.setText(Competition.players.get(1).name);
    }
    JLabel placeThree = new JLabel("3. ");
    if(Competition.players.size()>2){
      thirdPlayer.setText(Competition.players.get(2).name);
    }
    JLabel placeFour = new JLabel("4. ");
    if(Competition.players.size()>3){
      fourthPlayer.setText(Competition.players.get(3).name);
    }
    JLabel placeFive = new JLabel("5. ");
    if(Competition.players.size()>4){
      fifthPlayer.setText(Competition.players.get(4).name);
    }

    content.setLayout(new GridLayout(5,2));
    content.add(placeOne);
    content.add(firstPlayer);
    content.add(placeTwo);
    content.add(secondPlayer);
    content.add(placeThree);
    content.add(thirdPlayer);
    content.add(placeFour);
    content.add(fourthPlayer);
    content.add(placeFive);
    content.add(fifthPlayer);
  }
}
