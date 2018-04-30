package core;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a single player on the board.
 */
public class Player {
	private byte id;
	private int playerPos;
	private int money;
	private int getOutOfJail;
	private int turnsLeftInJail;
	private boolean stillInGame;
	private ArrayList<Property> properties;
	private Sprite token;
	private Color color;
	private static Random rand = new Random();

	public Player(byte id) {
		this(id, new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
	}

	public Player(byte id, Color color) {
		playerPos =0;
		money = 1000;
		getOutOfJail = 0;
		turnsLeftInJail =0;
		stillInGame = true;
        this.id = id;
		properties = new ArrayList<>();
		this.color = color;
	}

	public Player(int tokenType, byte id, Color color) throws IOException {
		this(id, color);
		token = new Sprite("/resources/images/tokens/" + tokenType + ".png");

		//Color player's piece
		BufferedImage colorImg = new BufferedImage(token.getWidth(), token.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = colorImg.createGraphics();
		g.drawImage(token.getImage(), 0, 0, null);
		g.setComposite(AlphaComposite.SrcAtop);
		g.setColor(new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), 55));
		g.fillRect(0, 0, token.getWidth(), token.getHeight());
		g.dispose();
		token.setImage(colorImg);
	}

	public Sprite getSprite() {
		return token;
	}

	// setter and getter for playerPos
	public void setPlayerPos(int playerPos) {
		if (playerPos >= 0 && playerPos <= 39)
			this.playerPos = playerPos;
	}
	public int getPlayerPos()
		{
			return playerPos;
		}

	/**
	 * Adds the given amount to the player position.
	 * @param amount amount to move the player
     */
	public void addPlayerPos(int amount) {
		this.playerPos += amount;
	}

	/**
	 * Sets the player's money to the given value.
	 *
	 * @param amount money in dollars
	 */
	public void set_money(int amount) {
		money = amount;
	}

	/**
	 * Adds the given amount to the player's total money.
	 *
	 * @param amount money in dollars
	 */
	public void giveMoney(int amount) {
		if (amount > 0)
			money += amount;
	}

	/**
	 * Subtracts the given amount to the player's total money.
	 *
	 * @param amount money in dollars
	 */
	public void takeMoney(int amount) {
		if (amount > 0) {
			money -= amount;
			if (money < 0) {
				money = 0;
				stillInGame = false;
			}
		}
	}

	/**
	 * Returns the user's current money in dollars.
	 *
	 * @return player's current money in dollars
	 */
	public int get_money() {
		return money;
	}

	// setter and getter for getOutOfJail
	public void setGetOutOfJailFreeCardCnt(int get_out)
		{
			getOutOfJail = get_out;
		}

	/**
	 * Gives the player one Get Out of Jail Free card.
	 */
	public void giveGetOutOfJailFreeCard() {
		getOutOfJail++;
	}

	public int getGetOutOfJailFreeCards()
		{
			return getOutOfJail;
		}

	// setter and getter for turns left in jail
	public void setTurnsLeftInJail(int turnsLeftInJail)
	{
		if (turnsLeftInJail >= 0)
			this.turnsLeftInJail = turnsLeftInJail;
	}

	public int getTurnsLeftInJail()
		{
			return turnsLeftInJail;
		}

	/**
	 * Decrements the turns left in jail by one.
	 */
	public void decTurnsLeftInJail() {
		turnsLeftInJail--;
	}

	// setter and getter for still in game
	public void setStillInGame(boolean inJail )
	{
		stillInGame = inJail;
	}

	public boolean getStillInGame()
	{
		return stillInGame;
	}

	/**
	 * Gives the user a property.
	 * @param property property to give the user
     */
	public void addProperty(Property property) {
		properties.add(property);
	}

	/**
	 * Takes away a property from the user.
	 * @param property property to take from the user
     */
	public void removeProperty(Property property) {
		properties.remove(property);
	}

	/**
	 * Returns the list of owned properties
	 * @return list of owned properties
     */
	public ArrayList<Property> getProperties() {
		return properties;
	}

    /**
     * Returns the Player's ID
     *
     * @return id ID.
     */
    public byte getID() {
        return id;
    }

    public Color getColor() {
		return color;
	}
}
