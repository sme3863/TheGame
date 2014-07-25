package at.green.screen;

import java.util.ArrayList;

import at.green.TheGame;
import at.green.input.GameInputMultiplexer;
import at.green.map.Map;
import at.green.map.RuleSet;
import at.green.map.TileType;
import at.green.player.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameScreen implements Screen{

	private TheGame game;
	private Map map;
	private SpriteBatch spriteBatch;
	private OrthographicCamera camera;
	private Player player;
	private GameInputMultiplexer inputMultiplexer;
	
	
	private TiledMap tiledmap;
	private TiledMapRenderer renderer;
	
	private boolean isOrthogonal = true;
	
	
	public GameScreen(TheGame game){
		
		this.player = new Player(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
		
		
		//create and set the input handling
		this.inputMultiplexer = new GameInputMultiplexer();
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		
		
		this.game = game;
		//this.camera = new OrthographicCamera(game.getWidth(),game.getHeight());
		//this.camera.position.set(game.getWidth()/2,game.getHeight()/2,0);
		
		ArrayList<RuleSet> rules = new ArrayList<RuleSet>();
		//rules.add(new RuleSet(0.4f, 3, 4));
		rules.add(new RuleSet(0.5f, 4, 3));
		//map = new Map(100, 100, 4, rules);
		map = new Map(100, 100, 3, rules);
		map.buildNewMap();
		//map.writeToFile("C:/Users/Green/Desktop/map.txt");
		
		
		
		camera = new OrthographicCamera();
		
		camera.zoom = 50f;
		
		if(this.isOrthogonal){
			tiledmap = new TmxMapLoader().load("map2.tmx");
			renderer = new OrthogonalTiledMapRenderer(tiledmap);
		}
		else{
			tiledmap = new TmxMapLoader().load("map1.tmx");
			renderer = new IsometricTiledMapRenderer(tiledmap);
		}
		camera.setToOrtho(isOrthogonal, 30, 20); //true for Y down coord.sys.
		buildMap();
		camera.update();
	}
	
	private void buildMap(){
		TiledMapTileLayer layer = (TiledMapTileLayer) tiledmap.getLayers().get(1);
		TiledMapTileLayer.Cell wall = layer.getCell(0,0);
		TiledMapTileLayer.Cell floor = layer.getCell(1,1);
		
		
		for(int x = 0;x < 100;x++){
			for(int y = 0;y < 100;y++){
				if(map.getTiles()[x][y] == TileType.Floor){
					layer.setCell(x,y,floor);
				}else{
					layer.setCell(x,y,wall);
				}
			}
		}
		
		
	}
	
	
	float x = 1500;
	float y = 0;
	float zoom = 100;
	@Override
	public void render(float delta) {
		
		// clear the screen
		Gdx.gl.glClearColor(0.7f, 0.7f, 1.0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		spriteBatch.setProjectionMatrix(camera.combined);
		renderer.setView(camera);
		renderer.render();
		spriteBatch.begin();
		
		float boost = 0.0f;
		float speed = 5;
		if(Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)){
			boost = 20;//0.02f;
		}
		
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			x -= speed + boost;
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			x += speed + boost;
		}
		if(Gdx.input.isKeyPressed(Keys.UP)){
			y -= speed + boost;
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			y += speed + boost;
		}
		
		if(Gdx.input.isKeyPressed(Keys.W)){
			zoom += 0.1;
			//if(zoom >= 5){
			//	zoom = 5;
			//}
		}
		if(Gdx.input.isKeyPressed(Keys.S)){
			zoom -= 0.1;
			if(zoom < 1){
				zoom = 1;
			}
		}
		//map.render(spriteBatch,x,y,zoom);
		camera.position.x = x;
		camera.position.y = y;
		camera.zoom = zoom;
		
		player.setX(camera.position.x);
		player.setY(camera.position.y);
		camera.update();
		player.render(spriteBatch);
		spriteBatch.end();

		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		spriteBatch = new SpriteBatch();
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}
}
