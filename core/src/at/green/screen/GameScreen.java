package at.green.screen;

import at.green.TheGame;
import at.green.world.GameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen implements Screen{

	private TheGame game;
	private GameWorld world;
	private Rectangle UIbounds;
	
	
	public GameScreen(TheGame game){
		
		this.game = game;
		this.world = new GameWorld();
		this.UIbounds = new Rectangle();
	}
	
	

	
	@Override
	public void render(float delta) {
		
		// clear the screen
		Gdx.gl.glClearColor(0.7f, 0.7f, 1.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		this.world.render();
		this.world.update();
		
	}
	
	public void setUIBounds(Rectangle bounds){
		this.UIbounds = bounds;
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}
}
