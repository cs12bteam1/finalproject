/**
 *		@author Osamah Mandawi
 *		@email oamandawi@brandeis.edu
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.*;
import java.util.*;

public class Competition {

	public static ArrayList<Player> players = new ArrayList<Player>();
	public static JComboBox<Player> playerChooser;
	public static JComboBox<Player> playerChooser2;
	public static Rankings rankingsPanel;
	/********
	 * main should be changed if we are to merge our windows in another class
	 *******/
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		/**
		 * ESSENTIALS Create frame, and main panel, and set the layout Create fonts
		 * Create the hashmap
		 */
		JFrame window = new JFrame("Competition");
		JPanel content = new JPanel();
		window.setContentPane(content);
		GridLayout layout = new GridLayout(0, 3, 50, 50);
		content.setLayout(layout);
		Font font = new Font("Verdana", Font.BOLD, 36);
		Font reducedFont = new Font("Verdana", Font.BOLD, 24);
		HashMap<String, String> map = new HashMap<String, String>();
		content.setBackground(Color.BLUE);
		GridLayout rower = new GridLayout(3, 0, 50, 50);


		/**
		 * GUI pieces Create the JComponents
		 */
		JButton win1 = new JButton("1-0");
		win1.setBackground(Color.CYAN);
		win1.setFont(font);

		JButton draw = new JButton("1/2-1/2");
		draw.setBackground(Color.CYAN);
		draw.setFont(font);

		JButton win2 = new JButton("0-1");
		win2.setBackground(Color.CYAN);
		win2.setFont(font);

		JLabel vs = new JLabel("VS", SwingConstants.CENTER);
		vs.setFont(font);
		vs.setForeground(Color.WHITE);

		playerChooser = new JComboBox<Player>();
		playerChooser2 = new JComboBox<Player>();

		playerChooser.setFont(font);
		playerChooser.setBackground(Color.WHITE);

		playerChooser2.setFont(font);
		playerChooser2.setBackground(Color.WHITE);

		JTextArea myHistory = new JTextArea("No History Available"); // I used text areas instead of labels for
																		// multiline property
		myHistory.setFont(reducedFont);
		myHistory.setForeground(Color.BLACK);

		JTextArea myHistory2 = new JTextArea("No History Available");
		myHistory2.setFont(reducedFont);
		myHistory2.setForeground(Color.BLACK);

		/**
		 * GLUE the JComponents together
		 */
		JPanel row1 = new JPanel();
		row1.setLayout(rower);
		row1.add(win1);
		row1.add(playerChooser);
		row1.add(myHistory);
		row1.setBackground(Color.GREEN);

		JPanel row2 = new JPanel();
		row2.setLayout(rower);
		row2.add(draw);
		row2.add(vs);
		row2.setBackground(Color.GREEN);

		JPanel row3 = new JPanel();
		row3.setLayout(rower);
		row3.add(win2);
		row3.add(playerChooser2);
		row3.add(myHistory2);
		row3.setBackground(Color.GREEN);

		/**
		 * FUNCTIONALITY Create the text file, and establish a writer to enter
		 * information in the file Add buttons functionality
		 */
		PrintWriter writer = new PrintWriter("history.txt", "UTF-8"); // up to change, I suppose

		playerChooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String player = playerChooser.getSelectedItem().toString(); // get selected player from the JComboBox
				String history = ""; // create a variable to take printer's output
				try {
					history = iShowHistory(map, player); // call the method with map and player
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if (player.equals(player)) { // filler conditional for functionality
					myHistory.setText(history);
					System.out.print(history); // test
					if (history.equals("")) { // a little redundant
						myHistory.setText("No History Available");
					}
				}
			}
		});
		playerChooser2.addActionListener(new ActionListener() {
			// same as above
			public void actionPerformed(ActionEvent e) {
				String player = playerChooser2.getSelectedItem().toString();
				String history = "";
				try {
					history = iShowHistory(map, player);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if (player.equals(player)) {
					myHistory2.setText(history);
					System.out.print(history);
					if (history.equals("")) { // a little redundant
						myHistory2.setText("No History Available");
					}
				}
			}
		});

		win1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				/******
				 * A bug used to be here. The key was always the left player, until I made this
				 * redundancy
				 *****/
				writer.println(playerChooser.getSelectedItem() + ": won against " + playerChooser2.getSelectedItem());
				((Player)playerChooser.getSelectedItem()).incrementPoints();
				writer.println(playerChooser2.getSelectedItem() + ": lost to " + playerChooser.getSelectedItem());
				writer.flush(); // used instead of writer.close() to stay connected to the stream
			}
		});

		win2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				writer.println(playerChooser.getSelectedItem() + ": lost to " + playerChooser2.getSelectedItem());
				writer.println(playerChooser2.getSelectedItem() + ": won against " + playerChooser.getSelectedItem());
				((Player)playerChooser2.getSelectedItem()).incrementPoints();
				writer.flush();
			}
		});

		draw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				writer.println(playerChooser.getSelectedItem() + ": drew with " + playerChooser2.getSelectedItem());
				((Player)playerChooser.getSelectedItem()).incrementHalfPoints();
				writer.println(playerChooser2.getSelectedItem() + ": drew with " + playerChooser.getSelectedItem());
			  ((Player)playerChooser2.getSelectedItem()).incrementHalfPoints();
				writer.flush();
			}
		});
		/**
		 * Final boiler plate Gluing and window properties
		 */
		content.add(row1);
		content.add(row2);
		content.add(row3);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocation(1500, 700);
		window.setSize(800, 800);
		window.setVisible(true);
		
		JFrame addPlayerWindow = new JFrame("Add Player");
		JPanel panel = new AddPlayer();
		panel.setBackground(Color.WHITE);
		addPlayerWindow.setContentPane(panel);
		addPlayerWindow.setSize(300,500);
		addPlayerWindow.setLocation(500,0);
		addPlayerWindow.setVisible(true);

		JFrame rankingsWindow = new JFrame("Rankings");
		rankingsPanel = new Rankings();
		rankingsPanel.setBackground(Color.WHITE);
		rankingsWindow.setContentPane(rankingsPanel);
		rankingsWindow.setSize(500,500);
		rankingsWindow.setLocation(500,500);
		rankingsWindow.setVisible(true);
		
		window.addWindowListener(new WindowListener() {
			/*****
			 * Not as unnecessary as it seems. Notice how it contains writer.close() to
			 * flush and release any leftover from writer
			 *****/
			@Override
			public void windowClosing(WindowEvent e) {

				int exit = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?", "Exit",
						JOptionPane.YES_NO_OPTION);

				if (exit == JOptionPane.YES_OPTION) {
					writer.close();
					window.dispose();
				} else if (exit == JOptionPane.NO_OPTION) {
					int bye = JOptionPane.showConfirmDialog(null, "Too bad", "XD", JOptionPane.DEFAULT_OPTION); // LOL
				}
			}

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

		});
	}

	/**
	 *
	 * @param map
	 *            the Hash Map we are using
	 * @param player
	 *            the Player whose histroy we are trying to retrieve (and hash)
	 * @return printer, a string of player's history
	 * @throws IOException
	 *             for BufferdReader
	 */
	private static String iShowHistory(HashMap<String, String> map, String player) throws IOException {
		String path = "history.txt";
		String line;
		String printer = "";
		int count = 1;
		BufferedReader reader = new BufferedReader(new FileReader(path));
		while ((line = reader.readLine()) != null) {
			String[] parts = line.split(":", 2);
			if (parts.length >= 2 && parts[0].equals(player)) { // while I have two parts split and the first part is
																// the same as the player whose history we want
				String key = parts[0] + count; // I use count after suffering from keys colliding multiple times that
												// map.get became very troubling and only showed me one key at a time
				String value = parts[1]; // the value part after key
				System.out.println("key is " + key); // test
				System.out.println("count is " + count); // test
				System.out.println("value is " + value); // test
				map.put(key, value); // hashes
			}
			count++; // to differentiate keys
		}
		for (int i = 1; i <= 50; i++) { // may change up to 3 or however many (isn't very consuming)
			if (map.get(player + i) != null) { // makes use of hash functionality
				printer += player + map.get(player + i) + "\n"; // notice that I omit ':'
			}
		}
		reader.close(); // necessary, but not as harmful as writer.close() because notice that I open
						// the stream every time. If I did that for writer, I would overwrite my text
						// file every time.
		return printer;
	}
}
