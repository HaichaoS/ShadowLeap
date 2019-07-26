public class Water extends Sprite {
	public final static String water_path = "assets/water.png";
	
	public Water(float x, float y) {
		super(water_path, x, y);
	}

	@Override
	public void contactSprite(Sprite other) {
		// Check if the enemy made contact with the player
		// and if so, end the game
		if (other instanceof Player) {
			System.exit(0);
		}
	}
}