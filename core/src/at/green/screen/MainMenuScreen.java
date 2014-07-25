package at.green.screen;

import at.green.TheGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MainMenuScreen implements Screen{

	
	private SpriteBatch spriteBatch;
	private Texture image;
	private Texture options;
	private TheGame game;
	Stage stage;
	
	
	public MainMenuScreen(TheGame game) {
		this.game = game;
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
	}
	
	int x = 0;
	int y = 0;
	
	boolean switching = false;
	boolean menu = true;
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		spriteBatch.begin();
		if(Gdx.input.isKeyPressed(Keys.A)){
			switching = true;
		}
		if(Gdx.input.isKeyPressed(Keys.SPACE)){
			game.setScreen(new GameScreen(game));
		}
		
		
		
		
		if(switching){
			if(menu){
				x -= 256;
				if(x < -game.getWidth()){
					x = -game.getWidth();
					switching = false;
					menu = false;
				}
			}else{
				x += 256;
				if(x > 0){
					x = 0;
					switching = false;
					menu = true;
				}
			}
			
		}
		spriteBatch.draw(image, x, y, game.getWidth(), game.getHeight());
		spriteBatch.draw(options, x+game.getWidth(), y, game.getWidth(), game.getHeight());
		spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		spriteBatch = new SpriteBatch();
		image = new Texture(Gdx.files.internal("mainmenu.png"));
		options = new Texture(Gdx.files.internal("options.png"));
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
