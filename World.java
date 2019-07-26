import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class World {
	public static final String grass_path = "assets/grass.png";
	public static final String water_path = "assets/water.png";
	private static final int water_edge_Y = 336;
	public static final int tile_size = 48;
	private Image grass;
	private Image water;
	
	private static World world;
	public static World getInstance() {
		if (world == null) {
			world = new World();
		}
		return world;
	}
	
	private ArrayList<Sprite> sprites = new ArrayList<>();
	
	public void addSprite(Sprite sprite) {
		sprites.add(sprite);
	}
	
	public World() {
		try {
			grass = new Image(grass_path);
			water = new Image(water_path);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		// Create sprites
		for (int i = 0; i < App.SCREEN_WIDTH; i += tile_size) {
			sprites.add(new Water(i, water_edge_Y));
		}
		sprites.add(new Player());
		
		world = this;
	}
	
	public void update(Input input, int delta) {
		// Update all sprites
		for (int i = 0; i < sprites.size(); ++i) {
			sprites.get(i).update(input, delta);
		}
		// Handle collisions
		for (Sprite sprite : sprites) {
			for (Sprite other : sprites) {
				if (sprite != other && sprite.getBoundingBox().intersects(other.getBoundingBox())) {
					sprite.contactSprite(other);
				}
			}
		}
		// Clean up inactive sprites
		for (int i = 0; i < sprites.size(); ++i) {
			if (sprites.get(i).getActive() == false) {
				sprites.remove(i);
				// decrement counter to make sure we don't miss any
				--i;
			}
		}
	}
	
	public void render(Graphics g) {
		// Tile the background image
		for (int i = 0; i <= App.SCREEN_WIDTH; i+= World.tile_size) {
			grass.drawCentered(i,384);
			grass.drawCentered(i,672);
			for (int j = (336 - tile_size); j > 48; j-= World.tile_size) {
				water.drawCentered(i, j);
			}
		// Draw all sprites
		}
		for (Sprite sprite : sprites) {
			sprite.render();
		}		
	}
}