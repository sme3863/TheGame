package at.green.entity;

import at.green.utils.Timers;
import at.green.world.collisionshapes.CollisionCircle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Dummy extends Entity{

	public Dummy(Vector2 position, CollisionCircle boundingBox, float moveSpeed) {
		super(position, boundingBox, moveSpeed);
	}

	@Override
	public void render(SpriteBatch spriteBatch) {
		// TODO Auto-generated method stub
		
	}
	
	public void update(boolean change){
		
		if(change){
			int x = MathUtils.random(-1, 1);
			int y = MathUtils.random(-1, 1);
			this.getDirection().x = x;
			this.getDirection().y = y;
		}
		super.update();
	}

}
