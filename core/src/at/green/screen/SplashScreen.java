package at.green.screen;

import at.green.TheGame;
import at.green.utils.Timers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SplashScreen implements Screen{

	
	private SpriteBatch spriteBatch;
	private Texture splash;
	private TheGame game;
	private boolean keyPressed = false;
	String timerName = "splash";
	
	public SplashScreen(TheGame game) {
		this.game = game;
		Gdx.input.setInputProcessor(new SplashScreenInput());
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		spriteBatch.begin();
		spriteBatch.draw(splash, 0, 0, game.getWidth(), game.getHeight());
		spriteBatch.end();

		if(Timers.matchThenRemove(timerName) || keyPressed){
			this.game.setScreen(new MainMenuScreen(this.game));
		}
	}
	
	public void setKeyPressed(){
		this.keyPressed = true;
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		spriteBatch = new SpriteBatch();
		splash = new Texture(Gdx.files.internal("splash.png"));
		timerName = Timers.createTimer(timerName,2000);
		Timers.startTimer(timerName);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	private class SplashScreenInput implements InputProcessor {


		@Override
		public boolean keyDown(int keycode) {
			setKeyPressed();
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			setKeyPressed();
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			setKeyPressed();
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer,
				int button) {
			setKeyPressed();
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			setKeyPressed();
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			setKeyPressed();
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			return false;
		}
		
		
	}
}
