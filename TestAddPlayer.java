import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class TestAddPlayer{

  public static void main(String[] args){
    JFrame window = new JFrame("Results");
    JPanel content = new JPanel();

    JPanel add = new AddPlayer();
    content.add(add);

    window.setContentPane(content);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setLocation(120,70);
    window.setSize(400,300);
    window.setVisible(true);
  }
}
