package at.green;

import at.green.screen.SplashScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxNativesLoader;

public class TheGame extends Game {

	private int width = 0;
	private int height = 0;
	
	
	@Override
	public void create () {
		
		GdxNativesLoader.load();
		
		//allow images that are not a power of 2
		//Texture.setEnforcePotImages(false);
		//FULLSCREEN
		Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode().width, Gdx.graphics.getDesktopDisplayMode().height, true);
		Gdx.graphics.setVSync(true);
		this.width = Gdx.graphics.getDesktopDisplayMode().width;
		this.height = Gdx.graphics.getDesktopDisplayMode().height;
		
		this.setScreen(new SplashScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
}
