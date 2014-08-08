package at.green.world;

import at.green.camera.GameCamera;
import at.green.entity.Entity;
import at.green.entity.Player;
import at.green.input.GameInputMultiplexer;
import at.green.map.Map;
import at.green.world.collisionshapes.CollisionCircle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class GameWorld {

	private Map map;
	private GameCamera camera;
	private Player player;
	private Array<Entity> NPCs; //TODO refactor / no array!
	private GameInputMultiplexer inputMultiplexer;
	private SpriteBatch spriteBatch; //TODO move elsewhere
	private BitmapFont font; //TODO move elsewhere
	
	
	boolean isOrthogonal = true; 
	
	public GameWorld(){
		
		this.map = new Map(isOrthogonal);
		
		this.camera = new GameCamera(map.getWidth(),map.getHeight(), 50, isOrthogonal);
		
		this.NPCs = new Array<Entity>();
		
		spriteBatch = new SpriteBatch();
		font = new BitmapFont(isOrthogonal);
		
		float playerX = map.getWidth()/2;
		float playerY = map.getHeight()/2;
		float radius = 10;
		this.player = new Player(playerX, playerY, new CollisionCircle(playerX,playerY,radius), 10f); //FIXME
		
		CollisionManager.createCollisionShapesForMap(map);
		
		this.inputMultiplexer = new GameInputMultiplexer(player);
		Gdx.input.setInputProcessor(inputMultiplexer);

	}
	
	
	public void update(){
		this.player.update();
		this.camera.moveCameraTo(player.getPosition());
		this.camera.update();
	}
	
	
	public void render(){
		spriteBatch.setProjectionMatrix(this.camera.combined);
		
		map.render(this.camera);
		spriteBatch.begin();
		
		font.setColor(Color.RED); 
		Rectangle r = camera.getCameraBounds();
		font.draw(spriteBatch,""+Gdx.graphics.getFramesPerSecond(), r.x,r.y);
		
		for(Entity e: this.NPCs){
			e.render(spriteBatch);
		}
		//player.render(spriteBatch);
		

		ShapeRenderer shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(camera.combined);
		 
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(0, 1, 0, 1);
		Circle c = player.getBoundingBox();
		shapeRenderer.circle(c.x,c.y,c.radius);
		shapeRenderer.end();
		
		//TODO this.userInterface.render();
		
		
		spriteBatch.end();
		
	}
	
	public void addNPC(){
		
	}
	
	public void removeNPC(){
		
	}
	
	
}
