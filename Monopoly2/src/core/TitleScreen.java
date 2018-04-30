package core;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * Manages the title screen.
 */
public class TitleScreen {

    private enum MenuType {
        MAIN, PLAYER_COUNT
    }

    private MenuType currMenu;

    private Sprite logo;

    private Map<MenuType, ArrayList<Button>> menues;

    private int screenWidth;
    private int screenHeight;

    private int playerCnt;
    private boolean finished;

    private Vector<Integer> playerSprites;


    /**
     * Load resources for the title screen.
     * @param parent The parent JFrame that receives user input
     * @param screenWidth the width of the destination screen
     * @param screenHeight the height of the destination screen
     * @throws IOException, FontFormatException lack of resources. This should bubble up to the top
     */
    public TitleScreen(JFrame parent, int screenWidth, int screenHeight) throws IOException, FontFormatException {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        menues = new HashMap<>();
        currMenu = MenuType.MAIN;

        playerSprites = new Vector<Integer>();

        logo = new Sprite("/resources/images/logo.png");
        logo.setX((screenWidth / 2) - (logo.getWidth() / 2));
        logo.setY((screenHeight / 3) - (logo.getHeight() / 2));

        // Create the main menu
        ArrayList<Button> main = new ArrayList<>();

        Button play = new Button("Play");
        play.setRunnable(() -> switchMenu(MenuType.PLAYER_COUNT) );
        main.add(play);
        Button quit = new Button("Quit");
        quit.setRunnable(() -> System.exit(0) );
        main.add(quit);

        menues.put(MenuType.MAIN, main);

        // Create the player count menu
        ArrayList<Button> playerCount = new ArrayList<>();

        Button twoPlayers = new Button("Two Players");
        twoPlayers.setRunnable(() -> {
            playerCnt = 2;
            try {
                if (playerSprites.isEmpty())
                    spriteSelection();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FontFormatException e) {
                e.printStackTrace();
            }
        });
        playerCount.add(twoPlayers);
        Button threePlayers = new Button("Three Players");
        threePlayers.setRunnable(() -> {
            playerCnt = 3;
            try {
                if (playerSprites.isEmpty())
                    spriteSelection();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FontFormatException e) {
                e.printStackTrace();
            }
        });
        playerCount.add(threePlayers);
        Button fourPlayers = new Button("Four Players");
        fourPlayers.setRunnable(() -> {
            playerCnt = 4;
            try {
                if (playerSprites.isEmpty())
                    spriteSelection();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (FontFormatException e) {
                e.printStackTrace();
            }
        });
        playerCount.add(fourPlayers);

        menues.put(MenuType.PLAYER_COUNT, playerCount);

        // Set up the positions of the buttons
        for(ArrayList<Button> menu : menues.values()) {
            Button button = menu.get(0);
            button.setX((screenWidth / 2) - (button.getWidth() / 2));
            button.setY(((screenHeight * 2) / 3) - button.getHeight());
            parent.addMouseListener(button);

            for(int i=1; i<menu.size(); i++) {
                button = menu.get(i);
                parent.addMouseListener(button);
                button.setX((screenWidth / 2) - (button.getWidth() / 2));
                button.setY(menu.get(i-1).getY() + menu.get(i-1).getHeight());
            }
        }

        // Start at the main menu
        switchMenu(MenuType.MAIN);
    }

    /**
     * Switch to the given menu, turning off the buttons from the last menu.
     * @param menuType menu to switch to
     */
    private void switchMenu(MenuType menuType) {
        ArrayList<Button> oldMenu = menues.get(this.currMenu);
        for(Button button : oldMenu) {
            button.setActive(false);
        }
        currMenu = menuType;
        ArrayList<Button> newMenu = menues.get(this.currMenu);
        for(Button button : newMenu) {
            button.setActive(true);
        }
    }

    /**
     * Draws the title screen.
     * @param g graphics context
     * @param observer image observer, usually "this".
     */
    public void draw(Graphics g, ImageObserver observer) {
        logo.draw(g, observer);

        ArrayList<Button> menu = menues.get(currMenu);
        for (Button button : menu) {
            button.draw(g, observer);
        }
    }

    /**
     * Returns true if the title screen has finished and the next screen should
     * start.
     * @return true if title screen is finished
     */
    public boolean getFinished() {
        return finished;
    }

    /**
     * Returns the amount of players the user wants.
     * @return player count
     */
    public int getPlayerCnt() {
        return playerCnt;
    }

    public void spriteSelection() throws IOException, FontFormatException {
        JFrame frame = new JFrame("Sprite Selection");
        frame.setLayout(new GridBagLayout());

        JPanel pieces = new JPanel(new GridBagLayout()), selection = new JPanel(new GridBagLayout());
        pieces.setBackground(App.BACKGROUND_COLOR);
        selection.setBackground(App.BACKGROUND_COLOR);
        GridBagConstraints c1 = new GridBagConstraints(), c2 = new GridBagConstraints(), c3 = new GridBagConstraints();

        for (int i = 0; i < 8; i++) {
            JLabel label = new JLabel("[" + (i + 1) + "]");
            label.setFont(Resources.getFont("/resources/fonts/kabel.ttf").deriveFont(32.0f));
            c1.gridx = i;
            c1.gridy = 0;
            pieces.add(label, c1);

            BufferedImage img = ImageIO.read(new File("Monopoly2/src/resources/images/tokens/" + i + ".png"));
            label = new JLabel(new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_DEFAULT)));
            label.setSize(new Dimension(50, 50));
            c1.gridx = i;
            c1.gridy = 1;
            pieces.add(label, c1);
        }
        c3.gridx = 0;
        c3.gridy = 0;
        c3.gridwidth = 2;
        pieces.setBorder(new EmptyBorder(20, 20, 10, 20));
        frame.add(pieces, c3);

        for (int i = 0; i < playerCnt; i++) {
            JLabel label = new JLabel("Player " + (i + 1) + " Selection");
            label.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
            c2.gridx = i;
            c2.gridy = 0;
            selection.add(label, c2);

            ButtonGroup group = new ButtonGroup();
            for (int j = 0; j < 8; j++) {
                JRadioButton button = new JRadioButton((j + 1) + "");
                button.setBackground(App.BACKGROUND_COLOR);
                group.add(button);
                button.setActionCommand(j + "");
                button.addActionListener(e -> playerSprites.add(Integer.parseInt(e.getActionCommand())));
                c2.gridy = j + 1;
                selection.add(button, c2);
            }
        }

        selection.setBorder(new EmptyBorder(10, 20, 20, 20));
        c3.gridwidth = 1;
        c3.gridy = 1;
        frame.add(selection, c3);

        JPanel buttonPanel = new JPanel();
        java.awt.Button start = new java.awt.Button("Start");
        start.addActionListener((e) -> {
            if (playerSprites.size() == playerCnt) {
                frame.dispose();
                finished = true;
            }
        });

        start.setFont(Resources.getFont("/resources/fonts/kabel.ttf").deriveFont(32.0f));
        buttonPanel.add(start);
        c3.gridx = 1;
        c3.gridwidth = GridBagConstraints.CENTER;
        c3.gridheight = GridBagConstraints.CENTER;
        c3.fill = GridBagConstraints.BOTH;
        buttonPanel.setBackground(App.BACKGROUND_COLOR);
        frame.add(buttonPanel, c3);

        frame.setBackground(App.BACKGROUND_COLOR);
        frame.pack();
        frame.setVisible(true);
    }

    public Vector<Integer> getPlayerSprites() {
        return playerSprites;
    }
}
