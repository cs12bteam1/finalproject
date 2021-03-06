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

// This class allows the user to keep information to partially run a chess tournament.
// It allows the user to add players to the tournament, and then to record the results
// of games between those players. It automatically shows an updated rankings window.

public class Competition {

	public static ArrayList<Player> players = new ArrayList<Player>();
	public static JComboBox<Player> playerChooser;
	public static JComboBox<Player> playerChooser2;
	public static Rankings rankingsPanel;

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		/**
		 * ESSENTIALS Create frame, and main panel, and set the layout Create fonts
		 * Create the hashmap
		 */

		 //create the GUI
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

		//create a menu with instructions
		JMenu menu;
		JMenuItem i1, i2;
		JMenuBar mb = new JMenuBar();
		menu = new JMenu("Menu");
		mb.setBackground(Color.GREEN);
		menu.setFont(font);
		i1 = new JMenuItem("Instructions");
		i1.setBackground(Color.GREEN);
		i1.setFont(font);
		i2 = new JMenuItem("Exit");
		i2.setFont(font);
		i2.setBackground(Color.GREEN);
		menu.add(i1);
		i1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int instructions = JOptionPane.showConfirmDialog(null,
						"Hello, there!\nThis is a chess score keeper. You insert new players in the Add Player window, and then you set them against each other in the Competition window (MAKE SURE TO ALWAYS SELECT THE PLAYERS).\nYou choose the result, and the players' ranking will change accordingly in the Rankings window.\n\nThanks for using our application.",
						"Instructions", JOptionPane.DEFAULT_OPTION);
			}
		});
		i2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int exit = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?", "Exit",
						JOptionPane.YES_NO_OPTION);

				if (exit == JOptionPane.YES_OPTION) {
					window.dispose();
				}
			}
		});
		menu.add(i2);
		mb.add(menu);
		window.setJMenuBar(mb);

		//create results buttons

		JButton win1 = new JButton("1-0");
		win1.setToolTipText("Click if the player below won the game.");
		win1.setBackground(Color.CYAN);
		win1.setFont(font);

		JButton draw = new JButton("1/2-1/2");
		draw.setToolTipText("Click if it's a draw.");
		draw.setBackground(Color.CYAN);
		draw.setFont(font);

		JButton win2 = new JButton("0-1");
		win2.setToolTipText("Click if the player below won the game.");
		win2.setBackground(Color.CYAN);
		win2.setFont(font);

		JLabel vs = new JLabel("VS", SwingConstants.CENTER);
		vs.setFont(font);
		vs.setForeground(Color.WHITE);

		//allow the user to select which players are playing

		playerChooser = new JComboBox<Player>();
		playerChooser.setToolTipText("Choose the player.");
		playerChooser2 = new JComboBox<Player>();
		playerChooser2.setToolTipText("Choose the player.");

		playerChooser.setFont(font);
		playerChooser.setBackground(Color.WHITE);

		playerChooser2.setFont(font);
		playerChooser2.setBackground(Color.WHITE);

		//show recent player history

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

		// Allow the user to scroll through history
		JScrollPane vertical = new JScrollPane(myHistory);
       		vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       		JScrollPane vertical2 = new JScrollPane(myHistory2);
        	vertical2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

       		row1.add(vertical);
       		row3.add(vertical2);
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

		//For the win1, win2, and draw buttons, the Rankings window is completely redrawn whenever those are clicked

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
				Collections.sort(Competition.players);
        Competition.rankingsPanel.removeAll();
        Competition.rankingsPanel.updateUI();
        Competition.rankingsPanel.count = 1;
        for(int i = 0; i<Competition.players.size(); i++){
          Competition.rankingsPanel.addPlayer(Competition.players.get(i));
          System.out.println("Hi");
        }
			}
		});

		win2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				writer.println(playerChooser.getSelectedItem() + ": lost to " + playerChooser2.getSelectedItem());
				writer.println(playerChooser2.getSelectedItem() + ": won against " + playerChooser.getSelectedItem());
				((Player)playerChooser2.getSelectedItem()).incrementPoints();
				writer.flush();
				Collections.sort(Competition.players);
        Competition.rankingsPanel.removeAll();
        Competition.rankingsPanel.updateUI();
        Competition.rankingsPanel.count = 1;
        for(int i = 0; i<Competition.players.size(); i++){
          Competition.rankingsPanel.addPlayer(Competition.players.get(i));
          System.out.println("Hi");
        }
			}
		});

		draw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				writer.println(playerChooser.getSelectedItem() + ": drew with " + playerChooser2.getSelectedItem());
				((Player)playerChooser.getSelectedItem()).incrementHalfPoints();
				writer.println(playerChooser2.getSelectedItem() + ": drew with " + playerChooser.getSelectedItem());
			  ((Player)playerChooser2.getSelectedItem()).incrementHalfPoints();
				writer.flush();
				Collections.sort(Competition.players);
        Competition.rankingsPanel.removeAll();
        Competition.rankingsPanel.updateUI();
        Competition.rankingsPanel.count = 1;
        for(int i = 0; i<Competition.players.size(); i++){
          Competition.rankingsPanel.addPlayer(Competition.players.get(i));
        }
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

		//create AddPlayer window

		JFrame addPlayerWindow = new JFrame("Add Player");
		JPanel panel = new AddPlayer();
		panel.setBackground(Color.BLUE);
		addPlayerWindow.setContentPane(panel);
		addPlayerWindow.setSize(300,300);
		addPlayerWindow.setVisible(true);

		//create Rankings window

		JFrame rankingsWindow = new JFrame("Rankings");
		rankingsPanel = new Rankings();
		rankingsPanel.setBackground(Color.GREEN);
		rankingsWindow.setContentPane(rankingsPanel);
		rankingsWindow.setSize(300,500);
		rankingsWindow.setLocation(300,0);
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
