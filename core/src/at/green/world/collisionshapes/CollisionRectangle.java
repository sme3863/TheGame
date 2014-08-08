package at.green.world.collisionshapes;

import com.badlogic.gdx.math.Rectangle;

public class CollisionRectangle extends Rectangle implements CollisionShape{

	
	public CollisionRectangle() {
	}
	
	public CollisionRectangle(float x, float y, float width, float height) {
		super(x,y,width,height);
	}
	
	public CollisionRectangle(Rectangle rect) {
		super(rect);
	}
	
	
	
	@Override
	public boolean collidesWith(CollisionShape collisionShape) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
