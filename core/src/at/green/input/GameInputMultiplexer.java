package at.green.input;

import com.badlogic.gdx.InputMultiplexer;

public class GameInputMultiplexer extends InputMultiplexer{

	
	public GameInputMultiplexer() {
		this.addProcessor(new HUDInputProcessor());
		this.addProcessor(new DesktopInputProcessor());
	}
	
}
