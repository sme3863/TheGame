package at.green.entity;

import at.green.world.collisionshapes.CollisionCircle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity{

	
	//TODO remove
	Texture t = new Texture(Gdx.files.internal("player.png"));
	
	
	public Player(float posX, float posY, CollisionCircle boundingBox, float moveSpeed){
		super(new Vector2(posX,posY),boundingBox,moveSpeed);
	}
	
	public Player(Vector2 position, CollisionCircle boundingBox, float moveSpeed){
		super(position,boundingBox,moveSpeed);
	}
	
	@Override
	public void render(SpriteBatch spriteBatch){
		spriteBatch.draw(t,this.getPosition().x,this.getPosition().y);
	}
	
	
}
