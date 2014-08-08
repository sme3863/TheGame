package at.green.world.collisionshapes;

import com.badlogic.gdx.math.Polygon;

public class CollisionPolygon extends Polygon implements CollisionShape{

	
	public CollisionPolygon() {
		super();
	}

	public CollisionPolygon(float[] vertices) {
		super(vertices);
	}
	
	@Override
	public boolean collidesWith(CollisionShape collisionShape) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
