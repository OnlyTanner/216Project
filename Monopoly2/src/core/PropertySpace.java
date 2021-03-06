package core;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.io.IOException;

/**
 * A space that contains a property of some type.
 */
public class PropertySpace implements BoardSpace, MouseListener {

	public enum Ownership {
		OWNED_BY_CURRENT_PLAYER, OWNED_BY_ANOTHER_PLAYER, UNOWNED
	}

	private Property property;
	private StandardProperty st;
	private Sprite sprite;


	private Ownership ownership;

	private Color color;

	/**
	 * Constructs a new property space that refers to the given property.
	 * @param property the property that this space represents
	 */
	public PropertySpace(Property property) {
		this.property = property;
		this.color = new Color(0, 0, 0, 0);
		if(property.getClass() != RailroadProperty.class && property.getClass() != UtilityProperty.class)
		st = (StandardProperty) property;
	}

	/**
	 * Returns the name of the property this space represents.
	 * @return property name
	 */
	public String getName() {
		return property.getName();
	}

	/**
	 * Return the current sprite being drawn.
	 * @return current sprite
     */
	public Sprite getSprite() {
		return sprite;
	}

	/**
	 * Sets the sprite to be drawn to the given sprite.
	 * @param sprite sprite to draw
     */
	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	/**
	 * Draws the card space on screen.
	 * @param g the graphics context
	 * @param observer the image observer, which is "this" from the app class
	 */
	public void draw(Graphics g, ImageObserver observer) {
		sprite.draw(g, observer);
		switch(ownership) {
			case OWNED_BY_CURRENT_PLAYER:
				g.setColor(color);
				break;
			case OWNED_BY_ANOTHER_PLAYER:
				g.setColor(color);
				break;
			case UNOWNED:
				g.setColor(new Color(0, 0, 0, 0));
		}
		((Graphics2D) g).setStroke(new BasicStroke(2.0f));
		g.drawRect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
		if(st != null)
		for(int i = 1 ; i < st.getNumUpgrades()+1; i++) {

			if(sprite.getY() < 125) {

				g.drawRect(sprite.getX(), sprite.getHeight()+sprite.getY()/2, sprite.getWidth() / 5 * i, sprite.getHeight() / 4);
			}
			if(sprite.getY() > 650 ) {

				g.drawRect(sprite.getX(), sprite.getY(), sprite.getWidth() / 5 * i, sprite.getHeight() / 4);
			}
			if(sprite.getX() < 125) {

				g.drawRect(sprite.getWidth() + sprite.getX()/2, sprite.getY(), sprite.getWidth() / 4, sprite.getHeight() / 5 * i);
			}
			if(sprite.getX() > 650) {

				g.drawRect(sprite.getX(), sprite.getY(), sprite.getWidth() / 4, sprite.getHeight() / 5 * i);
			}
		}
	}

	/**
	 * Sets the ownership status of the property space to the given value.
	 * @param ownership ownership status from the point of view of the current player
     */
	public void setOwnership(Ownership ownership) {
		this.ownership = ownership;
	}

	/**
	 * Returns the property this space represents.
	 * @return property
	 */
	public Property getProperty() {
		return property;
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseClicked(MouseEvent e) {
		int x = (int) e.getPoint().getX();
		int y = (int) e.getPoint().getY();
		// Check if the click was within the space
		if(x > sprite.getX() && y > sprite.getY() &&
				x < sprite.getX() + sprite.getWidth() && y < sprite.getY() + sprite.getHeight()) {
			try {
				PropertyOpMenu.setProperty(property);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
}
