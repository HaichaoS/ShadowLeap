import org.newdawn.slick.Input;

public class Player extends Sprite {
	public final static String PLAYER_PATH = "assets/frog.png";
	public final static int PLAYER_INITIAL_X = 512;
	public final static int PLAYER_INITIAL_Y = 720;
	
	public Player() {
		super(PLAYER_PATH, PLAYER_INITIAL_X, PLAYER_INITIAL_Y);
	}
	
	@Override
	public void update(Input input, int delta) {
		float playerx = 0, playery = 0;
		if (input.isKeyPressed(Input.KEY_UP)) {
				playery -= World.tile_size;
		}
		if (input.isKeyPressed(Input.KEY_DOWN)) {
				playery += World.tile_size;
		}
		if (input.isKeyPressed(Input.KEY_LEFT)) {
				playerx -= World.tile_size;
		}
		if (input.isKeyPressed(Input.KEY_RIGHT)) {
				playerx += World.tile_size;
		}
		
		move(playerx, playery);
		clampToScreen();
	}
		

}
