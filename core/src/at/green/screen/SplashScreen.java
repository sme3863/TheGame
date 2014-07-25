package at.green.screen;

import at.green.TheGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class SplashScreen implements Screen{

	
	private SpriteBatch spriteBatch;
	private Texture splash;
	private TheGame game;
	private long startTime;
	
	public SplashScreen(TheGame game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		spriteBatch.begin();
		spriteBatch.draw(splash, 0, 0, game.getWidth(), game.getHeight());
		spriteBatch.end();

		if(TimeUtils.millis() - startTime >= 2000){
			this.game.setScreen(new MainMenuScreen(this.game));
		}
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		spriteBatch = new SpriteBatch();
		splash = new Texture(Gdx.files.internal("splash.png"));
		startTime = TimeUtils.millis();
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
}
