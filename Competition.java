import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

//PhoneBookGUI creates a graphical interface that allows the user to input names and phone numbers.
//Then, the user can search for a the number of a person they know or vice versa.

public class Rankings extends JPanel{

  //Use treemaps to store map phone numbers to names, and the reverse

  

  public Rankings(){

    //Create a graphical user interface
    super();
    JPanel content = this;
    JLabel title = new JLabel("<html><h3>Digital Phone Book!");
    JLabel nameLabel = new JLabel("<html>Name:</html>");
    JTextField enterName = new JTextField("");
    JLabel phoneLabel = new JLabel("<html>Phone:</html>");
    JTextField enterPhone = new JTextField("");
    JButton saveNumber = new JButton("save");

    //Add a name and number to phoneBook and reversePhoneBook when the user clicks the save button
    saveNumber.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e) {
        if(!(enterName.getText().equals(""))&&!(enterPhone.getText().equals(""))){
          phoneBook.put(enterName.getText(),enterPhone.getText());
          reversePhoneBook.put(enterPhone.getText(),enterName.getText());
        }
      }
   });

   JButton lookupNumber = new JButton("lookup");
   //Search for a name or number when the user presses the lookup button
   lookupNumber.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent e) {

       //search for name using number
       if(enterName.getText().equals("")&&!(enterPhone.getText().equals(""))){
         Set set = reversePhoneBook.entrySet();
         Iterator it = set.iterator();
         boolean b = true;
         while(it.hasNext()){
           Map.Entry entryMap = (Map.Entry)it.next();
           if(String.valueOf(entryMap.getKey()).equals(enterPhone.getText())){
             //update value of name textfield
             enterName.setText(String.valueOf(entryMap.getValue()));
             b = false;
             break;
           }
         }
         if(b){
           enterName.setText("unknown");
         }
       }

       //search for number using name
       else if(!(enterName.getText().equals(""))&&(enterPhone.getText().equals(""))){
         Set set = phoneBook.entrySet();
         Iterator it = set.iterator();
         boolean b = true;
         while(it.hasNext()){
           Map.Entry entryMap = (Map.Entry)it.next();
           if(String.valueOf(entryMap.getKey()).equals(enterName.getText())){
             //update value of phone textfield
             enterPhone.setText(String.valueOf(entryMap.getValue()));
             b = false;
             break;
           }
         }
         if(b){
           enterPhone.setText("unknown");
         }
       }
     }
  });
    content.setLayout(new BorderLayout());
    content.add(title, BorderLayout.PAGE_START);
    JPanel bottom = new JPanel();
    bottom.setLayout(new GridLayout(2,2));
    bottom.add(nameLabel);
    bottom.add(enterName);
    bottom.add(phoneLabel);
    bottom.add(enterPhone);
    JPanel right = new JPanel();
    right.add(saveNumber);
    right.add(lookupNumber);
    content.add(bottom, BorderLayout.PAGE_END);
    content.add(right, BorderLayout.LINE_END);
  }

  private static void createAndShowGUI(){
        //Create and set up the window.
        JFrame frame = new JFrame("Rankings");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new Rankings();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setSize(350,160);
  }

  public static void main(String[] args){
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
