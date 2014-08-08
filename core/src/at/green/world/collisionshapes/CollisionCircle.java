package at.green.world.collisionshapes;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public class CollisionCircle extends Circle implements CollisionShape{

	public CollisionCircle(){
		super();
	}
	
	public CollisionCircle(float x, float y, float radius){
			super(x,y,radius);
	}
	
	public CollisionCircle(Vector2 position, float radius){
		super(position,radius);
	}
	
	public CollisionCircle(Circle circle){
		super(circle);
	}

	@Override
	public boolean collidesWith(CollisionShape collisionShape) {
		// TODO Auto-generated method stub
		return false;
	}
}
