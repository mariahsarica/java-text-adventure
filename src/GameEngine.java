/**
 * GameEngine.java - NATURE'S PANTRY - A text adventure game in a GUI
 * @author Mariah Molenaer
 */

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class GameEngine {

	static JFrame frame;
	private JTextField input;
	static JTextArea output;
	static JTextArea inv;
	
	static JLabel currentLocInfo;
	static JLabel movesInfo;
	static JLabel backtrackInfo;
	static JLabel directionsLabel;
	static JLabel directionsInfo;
	
	// Ringing the bell in the deli triggers the end game scenario and player is unable to execute commands.
	// Game flows normally if false.
	private static boolean END_GAME_SCENARIO = false;
	
	/**
	 * Constructor
	 */
	public GameEngine() {
		
		JPanel gui = buildGUI();
		
		frame = new JFrame("Nature's Pantry Game");
		frame.add(gui);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		        showGoodbye();
		    }
		});
		frame.getContentPane().setPreferredSize(new Dimension(850, 450));
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
	
	
	public static void main (String[] args) {
        	
        World.init();
        	
        // builds and runs game engine
    	GameEngine eng = new GameEngine();
    	eng.runEngine();
    	
	}
	
	
	/**
	 * The getInput method gets the input the user enters in the text field and trims it.
	 */
	private String getInput() {
		return this.input.getText().trim();
	}
	
	
	/**
	 * The commandReader method readers the users input command and performs the proper action.
	 */
	private void commandReader() {
		
		// Used to read commands without spaces
		String userInput = this.getInput().toLowerCase();
        
		// Used to read commands with spaces
		String command;
        String nextToken;	
        if (userInput.contains(" ")) {
        	String [] rawInput = userInput.split(" ");
        	command = rawInput[0].toLowerCase();
        	nextToken = rawInput[1].toLowerCase();
        } else {
        	command = userInput.toLowerCase();
        	nextToken = null;
        }
        
        
        if (END_GAME_SCENARIO == false) { 
        
        	   if (userInput.equals("n")) {    				    // Moves north
        	Player.move(0);
    	} else if (userInput.equals("s")) {    			        // Moves south
    		Player.move(1);
    	} else if (userInput.equals("e")) {    				    // Moves east
    		Player.move(2);
    	} else if (userInput.equals("w")) {    				    // Moves west
    		Player.move(3);
    	} else if (userInput.equals("m")) {    				    // Displays map
    		World.map();
    	} else if (userInput.equals("l")) {						// Looks around location
    		Player.look();
    	} else if (userInput.equals("h")) {    				    // Displays help menu
    		JOptionPane.showMessageDialog(frame, HELP_MSG);;
    	} else if (userInput.equals("q")) {    				    // Quits game
    		quit();
    	
    	} else if (command.equals("t")) {    				    // Takes item
    		String item = nextToken;
    		if (item == null) {
    			errorMessage();
    		} else if (item.equals("cart")) {
    			Player.takeCart(World.items.get(0));
   			} else if (searchArr(item)) {
   				int index = getIndex(item);
   				Player.takeItem(World.items.get(index));
   			} else {
   				errorMessage();
   			}
   			
   		} else if (command.equals("d")) {					    // Drops item
   			String item = nextToken;
   			if (item == null) {
   				errorMessage();
   			} else if (searchArr(item)) {
   				int index = getIndex(item);
   				Player.drop(World.items.get(index));
   				if (item.equals("cart")) {
   					inv.setText("You need something to put your groceries in! \n\n");
   				}
   			} else {
   				errorMessage();
   			}
   		
   		} else if (command.equals("b")) {					    // Backtracks
   			String steps = nextToken;
   			if (steps == null) {
   				Player.backtrack(1);
   			} else {
   				try {
   					steps = steps.replaceAll("\\s", "");
   					Integer s = new Integer(steps);
   					Player.backtrack(s);
   				} catch (NumberFormatException nfe){
   					errorMessage();
   				}
   			}
   			
   		} else if (userInput.equals("talk")) {
   			Player.interactWithSpecialItem(4, 6);
   			
   		} else if (userInput.equals("smile")) {
   			Player.interactWithSpecialItem(1, 3);
   		
   		} else if (userInput.equals("ring") || userInput.equals("ring bell")) {
   			Player.interactWithSpecialItem(5, 7);
   			END_GAME_SCENARIO = true;
   			Player.displayHealthStats();
   			
   		} else {
   			errorMessage();
   		}
       
       
        // End Game commands
        } else {
        
        if (userInput.equals("q")) {    				    	               // Quits game
        	quit();
		
        } else if (userInput.equals("p")) {								   // Punches
        	Player.punch();
       
        } else if (command.equals("use")) {								   // Uses grocery items to fight with
        	String item = nextToken;
        	if (item == null) {
        		warningMessage("Enter an item you want to use.");
        	} else if (item.equals("map") || item.equals("flyer")) {
        		warningMessage("No, a grocery item.");
        	} else if (searchArr(item)) {
        		int index = getIndex(item);
        		if (World.items.get(index).getTaken() == true) {
        			Player.use(World.items.get(index));
        		}	
        	} 
        }
        
        }
        
        	
	}
	
	
	/**
	 * The getIndex method is used in the take and drop actions. It gets the index of the item in the
	 * ArrayList of items that the user wants to take/drop.
	 * @param item Name of item user wants to take/drop
	 * @return Index i of item in ArrayList or
	 *         -1 if it is not present
	 */    
	private int getIndex(String item) {
        for (int i = 0; i < World.items.size(); i++) {
        	if (item.equalsIgnoreCase(World.items.get(i).getName())) {
        		return i;
        	}
        }
        return -1;
    }
	
	
	/**
	 * The searchArr method is used in the take and drop actions. It searches the ArrayList of items
	 * to see if it contains the item the user wants to take/drop.
	 * @param item Name of item user wants to take/drop
	 * @return true if the item is in the ArrayList
	 * 		   false if it is not
	 */
	private boolean searchArr(String item) {
        for (int i = 0; i < World.items.size(); i++) {
        	if (item.equalsIgnoreCase(World.items.get(i).getName())) {
        		return true;
        	}
        }
        return false;
    }
     
	
	/**
	 * The buildInputControls method builds the input controls for the GUI and adds action listeners for them.
	 */
	private JPanel buildInputControls() {
        // used in the ActionListener inner classes
        final GameEngine eng = this;

        JButton helpBtn = new JButton("Help");
        helpBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(frame, HELP_MSG);
            }
        });

        this.input = new JTextField(8);
        this.input.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                eng.commandReader();
                ((JTextField)e.getSource()).setText(null);
            }
        });
        
        JPanel panel = new JPanel();
	    panel.add(new JLabel("Enter a command:"));
	    panel.add(this.input);
	    panel.add(helpBtn);
	    return panel;
        
	}
	
	
	/**
	 * The updateStatus method updates the player's status information
	 */
	public static void updateStatus() {
		currentLocInfo.setText("<html> <u>Current Location</u>: " + World.locs.get(Player.currentLoc).getName() + "</html>"); 
		movesInfo.setText("<html> <u>Total Moves</u>: " + Player.totalMoves + "</html>");
		backtrackInfo.setText("<html> <u>Steps from Beginning</u>: " + BreadCrumbTrail.stepsFromBeg + "</html>");
		directionsInfo.setText(World.locs.get(Player.currentLoc).getDir());
	}
	
	
	/**
	 * The buildStatusInfo method creates a panel to contain all the player's current information
	 */
	private JPanel buildStatusInfo() {
		
		currentLocInfo = new JLabel();
		
		directionsLabel = new JLabel("<html> <u>Directions Available</u>: </html>");
		directionsInfo = new JLabel();
		
		movesInfo = new JLabel();
		backtrackInfo = new JLabel();
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(206, 0));
			panel.add(Box.createVerticalStrut(10));
		panel.add(currentLocInfo);
			panel.add(Box.createVerticalStrut(10));
		panel.add(directionsLabel);
		panel.add(directionsInfo);
			panel.add(Box.createVerticalStrut(10));
		panel.add(movesInfo);
		panel.add(backtrackInfo);
		updateStatus();
		return panel;
		
	}
	
	
	/**
	 * The buildGUI method puts together all the input controls and output styles for the GUI.
	 */
	private JPanel buildGUI() {
		output = new JTextArea(this.intro);
   		output.setEditable(false);
   		output.setWrapStyleWord(true);
   		output.setLineWrap(true);
   		output.setCaretPosition(output.getDocument().getLength());
   		JScrollPane scroll = new JScrollPane(output);
   		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
   		 
   		JLabel title = new JLabel("<html><body><h1>Nature's Pantry</h1></body></html>");
   		title.setHorizontalAlignment(JLabel.CENTER);

   		JPanel inputsPanel = this.buildInputControls();
   		JPanel statusPanel = this.buildStatusInfo();
   		
   		inv = new JTextArea("You need something to put your groceries in! \n\n", 0, 13);
   		inv.setEditable(false);
   		inv.setLineWrap(true);
   		inv.setWrapStyleWord(true);

   		JPanel panel = new JPanel();
   		panel.setLayout(new BorderLayout(20, 0));
   		panel.add(title, BorderLayout.PAGE_START);
   		panel.add(statusPanel, BorderLayout.LINE_START);
   		panel.add(scroll, BorderLayout.CENTER);
   		panel.add(inv, BorderLayout.LINE_END);
   		panel.add(inputsPanel, BorderLayout.PAGE_END);
   		return panel;
   	}
	
	
	/**
	 * The runEngine method effectively starts the game.
	 */
	private void runEngine() {
    	this.showWelcome();
    	frame.setVisible(true);
    }

	
	/**
	 * The quit method exits the game and displays a goodbye message to the user.
	 */
	public static void quit() {
		showGoodbye();
		frame.setVisible(false);
		System.exit(0);
	}
	
	
	/**
	 * HELP_MSG contains all the help commands in a single string.
	 */
	private String HELP_MSG =
			"Items available for interaction in each room are listed in "
    		+ "CAPTIAL letters. \nThe following commands are permitted: \n\n"
    		+ "N - move north \n"
    		+ "S - move south \n"
    		+ "E - move east \n"
    		+ "W - move west \n"
    		+ "L - look around room \n"
    		+ "T [ITEM_NAME] - take item \n"
    		+ "D [ITEM_NAME] - drop item \n"
    		+ "B [NUMBER_OF_STEPS] - backtrack a specified number of steps \n"
    		+ "M - view map \n"
    		+ "H - display these help instructions \n"
    		+ "Q - quit game";
    	
    
	/**
	 * intro displays a introductory scene message to the user and starts the player in the lobby.
	 */
	private String intro =
			"Welcome to NATURE’S PANTRY, " + Player.name + ", your favorite "
			+ "alternative grocery store! \n"
			+ "What was it that I needed to get again? Oh yeah! "
			+ "Gluten free flour and tofu. Sounds like an easy enough plan... \n\n"
			+ World.locs.get(Player.currentLoc).getLoc();

	
	/**
	 * The showWelcome method informs the user that they are going to be starting the game.
	 */
	private void showWelcome() {
    	JOptionPane.showMessageDialog(frame, "Welcome to NATURE'S PANTRY, " + Player.name + ", click OK to being the game!");
    }
	
	
	/**
	 * The showGoodbye method informs the user that the game is over.
	 */
	private static void showGoodbye() {
    	JOptionPane.showMessageDialog(frame, "Thank you for visiting Nature's Pantry, " + Player.name + "!");
    }
	
	
    /**
     * The errorMessage method displays a message box to the user of type error.
     */
	public static void errorMessage() {
        JOptionPane.showMessageDialog(frame, "Invalid command.\nClick the help button for a list of available commands.", "Error", JOptionPane.ERROR_MESSAGE);
    }
	
	
	/**
	 * The warningMessage displays a message box to the user of type warning.
	 * @param msg Message to be displayed
	 */
	public static void warningMessage(String msg) {
        JOptionPane.showMessageDialog(frame, msg, "Warning", JOptionPane.WARNING_MESSAGE);
    }
     
	
	/**
	 * The informationMessage method displays a message box to the user of type informational.
	 * @param msg Message to be displayed
	 */
    public static void informationMessage(String msg) {
        JOptionPane.showMessageDialog(frame, msg);
    }
    	  	
	
}