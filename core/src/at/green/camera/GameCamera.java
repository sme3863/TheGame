package at.green.camera;


import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameCamera extends OrthographicCamera{

	
	
	private int mapLeft;
	private int mapRight;
	private int mapBottom;
	private int mapTop;
	
	private float cameraHalfWidth;
	private float cameraHalfHeight;
	
	private float cameraLeft;
	private float cameraRight;
	private float cameraBottom;
	private float cameraTop;
	
	final public float zoom; //restrict zooming outside of camera class
	
	public GameCamera(int mapWidth, int mapHeight, float zoom, boolean isOrthogonal){
		this.setToOrtho(isOrthogonal, 30, 20); //true for Y down coord.sys. / viewport width/height
		this.zoom = zoom;
		super.zoom = zoom;
		
		this.position.x = mapWidth / 2;
		this.position.y = mapHeight / 2;
		
		this.mapLeft = 0;
		this.mapRight = mapWidth;
		this.mapBottom = mapHeight;
		this.mapTop = 0;
		
		this.cameraHalfWidth = super.zoom * this.viewportWidth * .5f;
		this.cameraHalfHeight = super.zoom * this.viewportHeight * .5f;
		
		
		
		this.update();
	}
	
	public void moveCameraTo(Vector2 position){
		moveCameraTo(position.x, position.y);
	}
	
	public void moveCameraTo(float x, float y){
		this.position.x = x;
		this.position.y = y;
		validateCameraPosition();
	}
	
	public void moveCameraBy(float x, float y){
		this.position.x += x;
		this.position.y += y;
		validateCameraPosition();
	}
	
	private void validateCameraPosition(){
		
		this.cameraLeft = this.position.x - cameraHalfWidth;
		this.cameraRight = this.position.x + cameraHalfWidth;
		this.cameraBottom = this.position.y + cameraHalfHeight;
		this.cameraTop = this.position.y - cameraHalfHeight;
		
		if(cameraLeft <= mapLeft)
		{
		    this.position.x = mapLeft + cameraHalfWidth;
		    cameraLeft = mapLeft;
		}
		else if(cameraRight >= mapRight)
		{
			this.position.x = mapRight - cameraHalfWidth;
			cameraRight = mapRight;
		}

		if(cameraBottom >= mapBottom)
		{
			this.position.y = mapBottom - cameraHalfHeight;
			cameraBottom = mapBottom;
		}
		else if(cameraTop <= mapTop)
		{
			this.position.y = mapTop + cameraHalfHeight;
			cameraTop = mapTop;
		}
	}
	
	public Rectangle getCameraBounds(){
		return new Rectangle(this.position.x - this.cameraHalfWidth,this.position.y - this.cameraHalfHeight,this.mapRight, this.mapBottom);
	}
	
}
