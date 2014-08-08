package at.green.input;

import at.green.entity.Player;

import com.badlogic.gdx.InputMultiplexer;

public class GameInputMultiplexer extends InputMultiplexer{

	
	public GameInputMultiplexer(Player player) {
		this.addProcessor(new HUDInputProcessor()); //TODO reference to HUD
		this.addProcessor(new DesktopInputProcessor(player));
	}
	
}
