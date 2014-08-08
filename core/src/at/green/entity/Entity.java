package at.green.entity;

import at.green.world.CollisionManager;
import at.green.world.collisionshapes.CollisionCircle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	
	private Vector2 position;
	private Vector2 previousPosition;
	private CollisionCircle boundingBox;
	public float moveSpeed;
	private Vector2 direction;
	
	public Vector2 getDirection(){//TODO REMOVE
		return direction;
	}
	
	public Entity(Vector2 position, CollisionCircle boundingBox, float moveSpeed){
		this.position = position;
		this.boundingBox = boundingBox;
		this.moveSpeed = moveSpeed;
		this.direction = new Vector2(0,0);
		this.previousPosition = position;
	}

	
	public abstract void render(SpriteBatch spriteBatch);
	
	public void moveTo(float x, float y){
		this.position.set(x, y);
	}
	
	public void moveBy(float x, float y){
		this.position.set(x, y);
	}

	public void update(){
		CollisionCircle c = new CollisionCircle(this.boundingBox);
		float nextX = this.position.x + (direction.x * moveSpeed);
		float nextY = this.position.y + (direction.y * moveSpeed);
		c.x = nextX;
		
		if(!CollisionManager.collidesWithMap(c)){
			this.position.x = this.position.x + (direction.x * moveSpeed);
		}
		c.x = this.position.x;
		c.y = nextY;
		
		if(!CollisionManager.collidesWithMap(c)){
			this.position.y = this.position.y + (direction.y * moveSpeed);
		}
		this.boundingBox.setPosition(position);
	}

	
	public void resetDirection(){
		this.direction.x = 0;
		this.direction.y = 0;
	}
	
	public void setDirection(Vector2 direction){
		this.direction.x += direction.x;
		this.direction.y += direction.y;
	}
	
	public Vector2 getPosition(){
		return this.position;
	}
	
	public CollisionCircle getBoundingBox(){
		return this.boundingBox;
	}
	
}
