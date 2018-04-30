package core;

import org.json.JSONException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 * Manages the main game screen.
 */
public class GameScreen {
    private static final boolean DEBUG_MODE = false;
    private Board board;

    private Vector<Player> players;
    private int currPlayer;
    private int moveAmount;
    private long lastMove;
    private int lastRoll;
    private int delayToZoom;

    private int screenWidth;
    private int screenHeight;

    private Button rollButton;
    private Button drawCardButton;
    private Button purchaseButton;
    private Button auctionButton;
    private Button continueButton;
    private Button payRentButton;
    private Button viewPlayerDataButton;
    private Button instructionButton;
    private Button getOutOfJailCardButton;

    private boolean showDie;
    private Die die1;
    private Die die2;
    private boolean repeatTurn;

    private Hud hud;

    private Card currCard;

    private Graphics g;
    private ImageObserver observer;

    private JFrame playerDataWindow, instructions;

    public GameScreen(JFrame parent, int screenWidth, int screenHeight) throws IOException, FontFormatException, JSONException {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        board = new Board(140, 140, "/resources/config/board.json", parent);
        BoardLuaLibrary.setBoard(board);
        players = new Vector<>();

        // Initialize the heads up display
        hud = new Hud(screenWidth, screenHeight);

        // Create the roll button
        rollButton = new Button("Roll");
        rollButton.setX((screenWidth / 2) - (rollButton.getWidth() / 2));
        rollButton.setY((screenHeight / 2) - (rollButton.getHeight() / 2));
        rollButton.setRunnable(this::roll);
        parent.addMouseListener(rollButton);

        // Create the draw card button
        drawCardButton = new Button("Draw");
        drawCardButton.setX((screenWidth / 2) - (drawCardButton.getWidth() / 2));
        drawCardButton.setY(((screenHeight*3) / 4) - (drawCardButton.getHeight() / 2));
        drawCardButton.setActive(false);
        parent.addMouseListener(drawCardButton);

        // Create the purchase button
        purchaseButton = new Button("Purchase");
        purchaseButton.setX((screenWidth / 3) - (purchaseButton.getWidth() / 2));
        purchaseButton.setY(((screenHeight*3) / 4) - (purchaseButton.getHeight() / 2));
        purchaseButton.setActive(false);
        parent.addMouseListener(purchaseButton);

        // Create the auction button
        auctionButton = new Button("Pass");
        auctionButton.setX(((screenWidth * 2) / 3) - (auctionButton.getWidth() / 2));
        auctionButton.setY(((screenHeight*3) / 4) - (auctionButton.getHeight() / 2));
        auctionButton.setActive(false);
        parent.addMouseListener(auctionButton);

        // Create the continue button
        continueButton = new Button("Continue");
        continueButton.setX((screenWidth / 2) - (continueButton.getWidth() / 2));
        continueButton.setY(((screenHeight*3) / 4) - (continueButton.getHeight() / 2));
        continueButton.setActive(false);
        parent.addMouseListener(continueButton);

        // Create the pay rent button
        payRentButton = new Button("Pay Rent");
        payRentButton.setX((screenWidth / 2) - (continueButton.getWidth() / 2));
        payRentButton.setY(((screenHeight*3) / 4) - (continueButton.getHeight() / 2));
        payRentButton.setActive(false);
        parent.addMouseListener(payRentButton);

        // Create the view player data button
        viewPlayerDataButton = new Button(0, 0, 160, 78);
        parent.addMouseListener(viewPlayerDataButton);
        viewPlayerDataButton.setRunnable(() -> {
            try {
                displayPlayerWindow();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FontFormatException e) {
                e.printStackTrace();
            }
        });

        instructionButton = new Button((App.SCREEN_WIDTH / 2) - 106, App.SCREEN_HEIGHT - 39, 212, 39);
        parent.addMouseListener(instructionButton);
        instructionButton.setActive(true);
        instructionButton.setRunnable(() -> {
            try {
                if (!instructions.isShowing())
                    showInstructions();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FontFormatException e) {
                e.printStackTrace();
            }
        });

        // Create the Get Out Of Jail Card button
        getOutOfJailCardButton = new Button("Jail Free Card");
        getOutOfJailCardButton.setX(((screenWidth * 2) / 3) - (getOutOfJailCardButton.getWidth() / 2));
        getOutOfJailCardButton.setY(((screenHeight * 2) / 3) - (getOutOfJailCardButton.getHeight() / 2));
        getOutOfJailCardButton.setActive(false);
        getOutOfJailCardButton.setRunnable(this::useGetOutOfJailFreeCard);
        parent.addMouseListener(getOutOfJailCardButton);

        die1 = new Die();
        die1.setX((screenWidth / 2) - die1.getWidth() - 10);
        die1.setY((screenHeight / 2) - (die1.getHeight() / 2));
        die2 = new Die();
        die2.setX(die1.getX() + die2.getWidth() + 10);
        die2.setY(die1.getY());

        playerDataWindow = new JFrame("Player Stats");
        playerDataWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        playerDataWindow.setLocation(board.getX() + App.SCREEN_WIDTH * 2, board.getY() + App.SCREEN_HEIGHT / 4);

        instructions = new JFrame("Instructions");
        instructions.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        instructions.setLocation(board.getX(), board.getY());
    }

    /**
     * Initialize the game screen to be ready to display and take input.
     * @param playerCnt the amount of players playing
     * @param parent the parent JFrame for user input
     * @throws IOException lack of resources. This should bubble up to the top
     */
    public void init(int playerCnt, JFrame parent, Graphics g, ImageObserver observer, Vector<Integer> playerSprites) throws IOException, FontFormatException {
        ArrayList<Integer> chosenTokens = new ArrayList<>();

        Color[] colors = new Color[4]; //default colors
        colors[0] = Color.BLUE;
        colors[1] = Color.RED;
        colors[2] = Color.GREEN;
        colors[3] = Color.YELLOW;

        Random random = new Random();
        // Create all the players
        for(int i=0; i<playerCnt; i++) {
            Player player = new Player(playerSprites.get(i), (byte)(i + 1), colors[i]);
            players.add(player);
        }
        viewPlayerDataButton.setActive(true);

        currPlayer = 0;
        PlayerLuaLibrary.setPlayers(players);
        PlayerLuaLibrary.setCurrPlayer(currPlayer);
        PropertyOpMenu.setPlayer(players.get(currPlayer));
        hud.setCurrPlayer(players.get(currPlayer), players.get(currPlayer).getID());

        rollButton.setActive(true);

        PropertyOpMenu.init(screenWidth, screenHeight, parent);
        PropertyOpMenu.setActive(true);

        this.g = g;
        this.observer = observer;

        Notification.notify("Welcome to Monopoly!");
    }

    /**
     * Roll the dice and move the current player that amount.
     */
    private void roll() {
        // Roll the dice
        Random random = new Random();
        int amount1 = (random.nextInt(6) + 1);
        int amount2 = (random.nextInt(6) + 1);
        lastRoll = amount1 + amount2;

        die1.setNum(amount1);
        die2.setNum(amount2);
        showDie = true;
        rollButton.setActive(false);

        // Check if the player is in jail
        if(players.get(currPlayer).getTurnsLeftInJail() == 0) {
            // Set the player ready to move
            moveAmount = amount1 + amount2;
            if(amount1 == amount2) {
                repeatTurn = true;
            }
        } else {
            if(amount1 == amount2) {
                // Release the player if they rolled doubles
                players.get(currPlayer).setTurnsLeftInJail(0);
                Notification.notify("Player " + players.get(currPlayer).getID() + " was released from jail!");
                moveAmount = amount1 + amount2;
            } else {
                players.get(currPlayer).decTurnsLeftInJail();
                Notification.notify(players.get(currPlayer).getTurnsLeftInJail() + " turns left in jail!");
                delayToZoom = 100;
            }
        }

        PropertyOpMenu.setActive(false);

        removePlayers();
    }

    /**
     *  Removes the player from jail and takes one Get Out Of Jail Free Card.
     */
    private void useGetOutOfJailFreeCard ()
    {
        players.get(currPlayer).setTurnsLeftInJail(0);
        Notification.notify("Player " + players.get(currPlayer).getID() + " was released from jail!");
        roll();
    }

    /**
     * Does the required tasks of the space the current player is on.
     */
    private void processSpace() {
        showDie = false;
        board.zoomPlayer(players.get(currPlayer), screenWidth, screenHeight);
        PropertyOpMenu.setActive(false);

        // Add menu options based on what space was hit
        BoardSpace space = board.getBoardSpace(players.get(currPlayer));
        if(space instanceof CardSpace) {
            // The user is on a card space
            CardSpace cardSpace = (CardSpace) space;
            drawCardButton.setActive(true);

            // Show the user the card and apply the effects
            drawCardButton.setRunnable(() -> {
                currCard = cardSpace.drawCard();
                currCard.getSprite().setX((screenWidth / 2) - (currCard.getSprite().getWidth() / 2));
                currCard.getSprite().setY((screenHeight / 2) - (currCard.getSprite().getHeight() / 2));
                currCard.run(players.get(currPlayer));
                resetButtons();
                continueButton.setActive(true);
                continueButton.setRunnable(() -> {
                    currCard = null;
                    turnOver();
                });
            });
        } else if(space instanceof PropertySpace) {
            // The user is on a property space
            PropertySpace propertySpace = (PropertySpace) space;

            Player owner = getOwner(propertySpace.getProperty());
            if(owner == null) {
                purchaseButton.setActive(true);
                auctionButton.setActive(true);

                // Give the user the property to own
                purchaseButton.setRunnable(() -> {
                    // Check if the player has enough money to buy the property
                    if(players.get(currPlayer).get_money() < propertySpace.getProperty().getCost()) {
                        Notification.notify("You don't have enough money to buy that!");
                    } else {
                        players.get(currPlayer).takeMoney(propertySpace.getProperty().getCost());
                        players.get(currPlayer).addProperty(propertySpace.getProperty());
                        Notification.notify("Player " + players.get(currPlayer).getID() + " bought " + propertySpace.getName() + "!");
                        ((PropertySpace) space).setColor(players.get(currPlayer).getColor());
                        turnOver();
                    }
                });

                // Auction the property off to other players
                auctionButton.setRunnable(() -> {
                    turnOver();
                });
            } else {
                if(owner == players.get(currPlayer) || propertySpace.getProperty().getMortgaged()) {
                    // Don't charge rent on the property if the owner is the current player or the property is mortgaged
                    continueButton.setActive(true);

                    continueButton.setRunnable(() -> {
                        turnOver();
                    });
                } else {
                    payRentButton.setActive(true);

                    payRentButton.setRunnable(() -> {
                        int rent = propertySpace.getProperty().getRent(owner, lastRoll);
                        players.get(currPlayer).takeMoney(rent * (DEBUG_MODE ? 200 : 1));
                        owner.giveMoney(rent);
                        removePlayers();
                        turnOver();
                    });
                }
            }
        } else if(space instanceof OneOpSpace) {
            // The user is on a one-op space. This also includes spaces that do nothing, like free parking.
            OneOpSpace oneOpSpace = (OneOpSpace) space;
            continueButton.setActive(true);

            // Run whatever event is appropriate, if any
            continueButton.setRunnable(() -> {
                oneOpSpace.run(players.get(currPlayer));
                turnOver();
            });
        }

        removePlayers();
    }

    private Player getOwner(Property property) {
        for(Player player : players) {
            for(Property p : player.getProperties()) {
                if(p == property) {
                    return player;
                }
            }
        }

        return null;
    }

    /**
     * Resets all buttons to be hidden.
     */
    private void resetButtons() {
        rollButton.setActive(false);
        drawCardButton.setActive(false);
        purchaseButton.setActive(false);
        auctionButton.setActive(false);
        continueButton.setActive(false);
        payRentButton.setActive(false);
        getOutOfJailCardButton.setActive(false);
    }

    /**
     * Pass the turn off to the next player.
     */
    private void turnOver() {
        resetButtons();
        rollButton.setActive(true);
        board.zoomOut();
        if(players.get(currPlayer).getTurnsLeftInJail() == 0 && players.get(currPlayer).getGetOutOfJailFreeCards() > 0) {
            getOutOfJailCardButton.setActive(true);
        }
        if (!repeatTurn) {
            currPlayer++;
            if (currPlayer >= players.size()) {
                currPlayer = 0;
            }
        } else {
            repeatTurn = false;
        }

        PlayerLuaLibrary.setCurrPlayer(currPlayer);
        hud.setCurrPlayer(players.get(currPlayer), players.get(currPlayer).getID());
        PropertyOpMenu.setPlayer(players.get(currPlayer));

        PropertyOpMenu.setActive(true);

        removePlayers();

        try {
            if (playerDataWindow.isShowing())
                displayPlayerWindow();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * Draws the board and other game elements.
     * @param g graphics context
     * @param observer image observer, usually "this".
     */
    public void draw(Graphics g, ImageObserver observer) {
        board.draw(g, observer, players, currPlayer);
        board.drawPlayers(players, g, observer);
        // Move the current player every half a second
        if(moveAmount > 0 && System.currentTimeMillis() - lastMove > 500) {
            players.get(currPlayer).setPlayerPos(board.fixBoardPos(players.get(currPlayer).getPlayerPos() + 1));
            if(players.get(currPlayer).getPlayerPos() == 0) {
                // The player passed Go
                players.get(currPlayer).giveMoney(200);
            }
            moveAmount--;
            if(moveAmount == 0) {
                delayToZoom = 60;
            }
            lastMove = System.currentTimeMillis();
        }

        if(delayToZoom > 0) {
            delayToZoom--;
            if(delayToZoom == 0) {
                // Process the player's current space
                processSpace();
            }
        }

        rollButton.draw(g, observer);
        drawCardButton.draw(g, observer);
        purchaseButton.draw(g, observer);
        auctionButton.draw(g, observer);
        continueButton.draw(g, observer);
        payRentButton.draw(g, observer);
        getOutOfJailCardButton.draw(g, observer);

        if(showDie) {
            die1.draw(g, observer);
            die2.draw(g, observer);
        }

        hud.draw(g, observer);
        PropertyOpMenu.draw(g, observer);

        if(currCard != null) {
            currCard.getSprite().draw(g, observer);
        }
    }

    public void removePlayers() {
        if (!players.get(currPlayer).getStillInGame()) {
            Notification.notify("Player " + players.get(currPlayer).getID() + " has gone bankrupt!");
            players.remove(currPlayer);
            board.draw(g, observer, players, currPlayer);
            board.drawPlayers(players, g, observer);
        }

        if (currPlayer >= players.size())
            currPlayer = 0;

        if (players.size() == 1) {
            Notification.notify("Game Over. Player " + players.get(currPlayer).getID() + " has won!");
            playerDataWindow.dispose();
            App.RESET = true;
        }
    }

    public void displayPlayerWindow() throws IOException, FontFormatException {
        playerDataWindow.getContentPane().removeAll();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        panel.setBackground(App.BACKGROUND_COLOR);

        for (int i = 0; i < players.size(); i++) {
            JLabel p = new JLabel("--- Player " + players.get(i).getID() + " ---");
            p.setAlignmentX(Component.CENTER_ALIGNMENT);
            p.setFont(Resources.getFont("/resources/fonts/kabel.ttf").deriveFont(32.0f));
            p.setForeground((i == currPlayer) ? Color.WHITE : players.get(i).getColor());
            panel.add(p);

            JLabel money = new JLabel("Money: $" + players.get(i).get_money());
            money.setAlignmentX(Component.CENTER_ALIGNMENT);
            money.setFont(Resources.getFont("/resources/fonts/roboto-bold.ttf").deriveFont(18.0f));
            panel.add(money);

            JLabel jail = new JLabel("Get Out of Jail Cards: " + players.get(i).getGetOutOfJailFreeCards());
            jail.setAlignmentX(Component.CENTER_ALIGNMENT);
            jail.setFont(Resources.getFont("/resources/fonts/roboto-bold.ttf").deriveFont(18.0f));
            panel.add(jail);

            JLabel properties = new JLabel("Number of Properties: " + players.get(i).getProperties().size());
            properties.setAlignmentX(Component.CENTER_ALIGNMENT);
            properties.setFont(Resources.getFont("/resources/fonts/roboto-bold.ttf").deriveFont(18.0f));
            panel.add(properties);

            JLabel ls = new JLabel("List of Properties...");
            ls.setAlignmentX(Component.CENTER_ALIGNMENT);
            ls.setFont(Resources.getFont("/resources/fonts/roboto-bold.ttf").deriveFont(18.0f));
            panel.add(ls);

            for (int j = 0; j < players.get(i).getProperties().size(); j++) {
                String list = players.get(i).getProperties().get(j).getName() + "\n";
                JLabel propertiesList = new JLabel(list);
                propertiesList.setAlignmentX(Component.CENTER_ALIGNMENT);
                propertiesList.setFont(Resources.getFont("/resources/fonts/roboto-bold.ttf").deriveFont(12.0f));
                panel.add(propertiesList);
            }

            panel.add(new JLabel(" "));
        }

        playerDataWindow.add(panel);
        playerDataWindow.pack();
        playerDataWindow.setVisible(true);
    }

    public void showInstructions() throws IOException, FontFormatException {
        JPanel panel = new JPanel();

        JEditorPane pane = new JEditorPane();
        pane.setPage(new File("Monopoly2/src/resources/config/instructions.html").toURI().toURL());
        pane.setEditable(false);
        pane.setBorder(BorderFactory.createLineBorder(App.BACKGROUND_COLOR));

        panel.add(pane);
        //panel.setBorder(new EmptyBorder(0, 15, 0, 15));
        panel.setBackground(App.BACKGROUND_COLOR);

        instructions.add(panel);
        instructions.setResizable(false);
        instructions.pack();
        instructions.setTitle("Instructions");
        instructions.setVisible(true);
    }
}