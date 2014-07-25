package at.green.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player {

	
	private float x;
	private float y;
	private int width = 32;
	private int height = 32;
	private Texture t;
	
	public Player(float x, float y){
		this.x = x;
		this.y = y;
		t = new Texture(Gdx.files.internal("player.png"));
	}
	
	
	public void render(SpriteBatch batch){
		batch.draw(t, x, y);
	}
	

	public float getX() {
		return x;
	}

	public void setX(float posX) {
		this.x = posX;
	}

	public float getY() {
		return y;
	}

	public void setY(float posY) {
		this.y = posY;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
