package at.green.input;

import at.green.entity.Player;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class DesktopInputProcessor implements InputProcessor{

	private Player player;
	
	public DesktopInputProcessor(Player player){
		this.player = player;
	}
	
	
	@Override
	public boolean keyDown(int keycode) {
		switch(keycode){
			case Keys.W:
				player.setDirection(new Vector2(0,-1));
				return true;
			case Keys.A:
				player.setDirection(new Vector2(-1,0));
				return true;
			case Keys.S:
				player.setDirection(new Vector2(0,1));
				return true;
			case Keys.D:
				player.setDirection(new Vector2(1,0));
				return true;
			default:
				return false;
		}
	}

	@Override
	public boolean keyUp(int keycode) {
		switch(keycode){
			case Keys.W:
				player.setDirection(new Vector2(0,1));
				return true;
			case Keys.A:
				player.setDirection(new Vector2(1,0));
				return true;
			case Keys.S:
				player.setDirection(new Vector2(0,-1));
				return true;
			case Keys.D:
				player.setDirection(new Vector2(-1,0));
				return true;
			default:
				return false;
		}
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
