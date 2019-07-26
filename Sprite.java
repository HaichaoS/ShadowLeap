import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;

public class Sprite {
	private Image image;
	private float x;
	private float y;
	private BoundingBox bb;
	private boolean active = true;
	
	public Sprite(String imageSrc, float x, float y) {
		try {
			image = new Image(imageSrc);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.x = x;
		this.y = y;
		
		bb = new BoundingBox(image, x, y);
	}
	
	/*
	 * Returns true whenever the sprite is on screen.
	 */
	public boolean onScreen() {
		return x >= 0 && x <= App.SCREEN_WIDTH - bb.getWidth()
			&& y >= 0 && y <= App.SCREEN_HEIGHT - bb.getHeight();
	}
	
	/*
	 *  Forces the sprite to remain on the screen
	 */
	public void clampToScreen() {
		x = Math.max(x, World.tile_size);
		x = Math.min(x, App.SCREEN_WIDTH - World.tile_size);
		y = Math.max(y, World.tile_size);
		y = Math.min(y, App.SCREEN_HEIGHT - World.tile_size);
	}
	
	public void update(Input input, int delta) {
		
	}
	
	public void render() {
		image.drawCentered(x, y);
	}
	
	/*
	 * Called whenever this Sprite makes contact with another.
	 */
	public void contactSprite(Sprite other) {
		
	}

	public float getX() { return x; }
	public float getY() { return y; }
	public boolean getActive() { return active; }
	
	public BoundingBox getBoundingBox() {
		return new BoundingBox(bb);
	}
	public void move(float playerx, float playery) {
		x += playerx;
		y += playery;
		bb.setX(x);
		bb.setY(y);
	}
}