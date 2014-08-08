package at.green.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import at.green.camera.GameCamera;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class Map extends TiledMap{

	private int columns;
	private int rows;
	private int totalWidth;
	private int totalHeight;
	private int tileWidth;
	private int tileHeight;
	
	private TiledMap tiledMap;
	private TileType[][] tiles;
	private List<RuleSet> rules;
	private int numberIterations;
	
	private TiledMapRenderer renderer;
	
	//TODO remove tiles array, streamline process buildEnvironment -> buildTiledMap
	
	public Map(boolean isOrthogonal){
		this.rules = new ArrayList<RuleSet>();
		rules.add(new RuleSet(0.5f, 4, 3));
		this.columns = 100;
		this.rows = 100;
		this.tileWidth = 64;
		this.tileHeight = 64;
		this.totalWidth = columns * tileWidth;
		this.totalHeight = rows * tileHeight;
		this.tiles = new TileType[columns][rows];
		this.numberIterations = 3;
		
		if(isOrthogonal){
			this.tiledMap = new TmxMapLoader().load("map2.tmx");
			this.renderer = new OrthogonalTiledMapRenderer(this.tiledMap);
		}
		else{
			this.tiledMap = new TmxMapLoader().load("map1.tmx");
			this.renderer = new IsometricTiledMapRenderer(this.tiledMap);
		}
		buildEnvironment();
		buildTiledMap();
	}
	
	public int getTileWidth() {
		return tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public Map(int columns, int rows, int numberIterations, List<RuleSet> rules, boolean isOrthogonal) {
		this.columns = columns;
		this.rows = rows;
		this.totalWidth = columns * tileWidth;
		this.totalHeight = rows * tileHeight;
		this.tiles = new TileType[columns][rows];
		this.rules = rules;
		this.numberIterations = numberIterations;
		
		
		if(isOrthogonal){
			this.tiledMap = new TmxMapLoader().load("map2.tmx");
			this.renderer = new OrthogonalTiledMapRenderer(this.tiledMap);
		}
		else{
			this.tiledMap = new TmxMapLoader().load("map1.tmx");
			this.renderer = new IsometricTiledMapRenderer(this.tiledMap);
		}
		buildEnvironment();
		buildTiledMap();
	}
	
	public void render(GameCamera camera){
		renderer.setView(camera);
		renderer.render();
	}
	
	public void buildTiledMap(){
		TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get(1);
		TiledMapTileLayer.Cell wall = layer.getCell(0,0);
		TiledMapTileLayer.Cell floor = layer.getCell(1,1);
		
		for(int x = 0;x < 100;x++){
			for(int y = 0;y < 100;y++){
				if(this.tiles[x][y] == TileType.Floor){
					layer.setCell(x,y,floor);
				}else{
					layer.setCell(x,y,wall);
				}
			}
		}
	}
	
	private boolean buildEnvironment() {
		if (rules == null) {
			return false;
		}
		int nrRuleSets = rules.size();
		if (nrRuleSets < 1) {
			return false;
		}
		RuleSet currentRules = rules.get(0);
		initMap(currentRules);

		for (int iteration = 0; iteration < numberIterations; iteration++) {
			if (iteration < nrRuleSets) {
				currentRules = rules.get(iteration);
			}
			nextIteration(currentRules);
		}
		smoothWalls();
		return true;

	}

	private void initMap(RuleSet currentRules) {
		Random random = new Random();
		for (int x = 0; x < this.columns; x++) {
			for (int y = 0; y < this.rows; y++) {
				if (currentRules.chanceToStartAsWall > random.nextFloat()) {
					this.tiles[x][y] = TileType.Wall;
				} else {
					this.tiles[x][y] = TileType.Floor;
				}
			}
		}

	}

	private void nextIteration(RuleSet currentRules) {
		TileType[][] newGrid = copyGrid(this.tiles);
		// Array.Copy(this.tiles, 0, newGrid, 0, this.tiles.length);
		for (int x = 0; x < this.columns; x++) {
			for (int y = 0; y < this.rows; y++) {
				if (isEdge(x, y)) {
					newGrid[x][y] = TileType.Wall;
				} else {
					int count = getNeigbouringWallsCount(x, y);
					if (newGrid[x][y] == TileType.Wall) {
						if (count < currentRules.maxActive) {
							newGrid[x][y] = TileType.Floor;
						}
					} else if (newGrid[x][y] == TileType.Floor) {
						if (count < currentRules.maxInactive) {
							newGrid[x][y] = TileType.Wall;
						}
					}
				}
			}
		}
		this.tiles = newGrid;
	}

	private TileType[][] copyGrid(TileType[][] oldMap) {
		TileType[][] newMap = new TileType[oldMap.length][];
		for (int i = 0; i < oldMap.length; i++) {
			TileType[] aMatrix = oldMap[i];
			int aLength = aMatrix.length;
			newMap[i] = new TileType[aLength];
			System.arraycopy(aMatrix, 0, newMap[i], 0, aLength);
		}
		return newMap;
	}
	
	/*
	//TODO missing tiles e.g wallSN & wall WE
	private Texture wall = new Texture(Gdx.files.internal("wall.png"));
	private Texture wallN = new Texture(Gdx.files.internal("wallN.png"));
	private Texture wallNE = new Texture(Gdx.files.internal("wallNE.png"));
	private Texture wallNW = new Texture(Gdx.files.internal("wallNW.png"));
	private Texture wallS = new Texture(Gdx.files.internal("wallS.png"));
	private Texture wallSE = new Texture(Gdx.files.internal("wallSE.png"));
	private Texture wallSW = new Texture(Gdx.files.internal("wallSW.png"));
	private Texture wallNES = new Texture(Gdx.files.internal("wallNES.png"));
	private Texture wallNEW = new Texture(Gdx.files.internal("wallNEW.png"));
	private Texture wallNWS = new Texture(Gdx.files.internal("wallNWS.png"));
	private Texture wallWSE = new Texture(Gdx.files.internal("wallWSE.png"));
	private Texture wallE = new Texture(Gdx.files.internal("wallE.png"));
	private Texture wallW = new Texture(Gdx.files.internal("wallW.png"));
	
	private Texture floor = new Texture(Gdx.files.internal("floor.png"));
	
	
	//TODO renderbug, switched N with S tiles, maybe cause screen and map coordinates are switched map = y up screen = y down?
	public void render(SpriteBatch batch, float offX, float offY,float zoom){
		float tileSize = 32 * zoom;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				switch(tiles[x][y]){
					case Wall:
						batch.draw(wall, (x+offX)*tileSize, (y+offY)*tileSize,tileSize,tileSize);
						break;
					case WallS:
						batch.draw(wallN, (x+offX)*tileSize, (y+offY)*tileSize,tileSize,tileSize);
						break;
					case WallSE:
						batch.draw(wallNE, (x+offX)*tileSize, (y+offY)*tileSize,tileSize,tileSize);
						break;
					case WallE:
						batch.draw(wallE, (x+offX)*tileSize, (y+offY)*tileSize,tileSize,tileSize);
						break;
					case WallNE:
						batch.draw(wallSE, (x+offX)*tileSize, (y+offY)*tileSize,tileSize,tileSize);
						break;
					case WallN:
						batch.draw(wallS, (x+offX)*tileSize, (y+offY)*tileSize,tileSize,tileSize);
						break;
					case WallNW:
						batch.draw(wallSW, (x+offX)*tileSize, (y+offY)*tileSize,tileSize,tileSize);
						break;
					case WallW:
						batch.draw(wallW, (x+offX)*tileSize, (y+offY)*tileSize,tileSize,tileSize);
						break;
					case WallSW:
						batch.draw(wallNW, (x+offX)*tileSize, (y+offY)*tileSize,tileSize,tileSize);
						break;
					case WallNEW:
						batch.draw(wallWSE, (x+offX)*tileSize, (y+offY)*tileSize,tileSize,tileSize);
						break;
					case WallNES:
						batch.draw(wallNES, (x+offX)*tileSize, (y+offY)*tileSize,tileSize,tileSize);
						break;
					case WallNWS:
						batch.draw(wallNWS, (x+offX)*tileSize, (y+offY)*tileSize,tileSize,tileSize);
						break;
					case WallWSE:
						batch.draw(wallNEW, (x+offX)*tileSize, (y+offY)*tileSize,tileSize,tileSize);
						break;
					case Floor:
						batch.draw(floor, (x+offX)*tileSize, (y+offY)*tileSize,tileSize,tileSize);
						break;
					default:
						batch.draw(floor, (x+offX)*tileSize, (y+offY)*tileSize,tileSize,tileSize);
						break;
				}
			}
		}
	}
	*/
	
	private int getNeigbouringWallsCount(int posX, int posY) {
		int count = 0;
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				int neighbourX = posX + i;
				int neighbourY = posY + j;
				if (i == 0 && j == 0) {
					// do not count current tile
				} else if (neighbourX < 0 || neighbourY < 0
						|| neighbourX >= this.columns
						|| neighbourY >= this.rows) {
					count++;
				} else if (this.tiles[neighbourX][neighbourY] == tiles[posX][posY]) {
					count++;
				}
			}
		}
		return count;
	}
	
	private void smoothWalls() {
		for (int x = 0; x < this.columns; x++) {
			for (int y = 0; y < this.rows; y++) {

				if (tiles[x][y] != TileType.Floor) {
					int walls = 0;

					final int N = 3;
					final int E = 5;
					final int S = 7;
					final int W = 11;

					
					if(x - 1 >= 0){
						if (tiles[x - 1][y] == TileType.Floor) {
							walls += W;
						}
					}
					
					if(x + 1 < this.columns){
						if (tiles[x + 1][y] == TileType.Floor) {
							walls += E;
						}
					}
					
					if(y - 1 >= 0){
						if (tiles[x][y - 1] == TileType.Floor) {
							walls += N;
						}
					}
					
					if(y + 1 < this.rows){
						if (tiles[x][y + 1] == TileType.Floor) {
							walls += S;
						}
					}
					
					switch (walls) {
						case 0:
							tiles[x][y] = TileType.Wall;
							break;
						case N:
							tiles[x][y] = TileType.WallN;
							break;
						case N+E:
							tiles[x][y] = TileType.WallNE;
							break;
						case E:
							tiles[x][y] = TileType.WallE;
							break;
						case S+E:
							tiles[x][y] = TileType.WallSE;
							break;
						case S:
							tiles[x][y] = TileType.WallS;
							break;
						case S+W:
							tiles[x][y] = TileType.WallSW;
							break;
						case W:
							tiles[x][y] = TileType.WallW;
							break;
						case N+W:
							tiles[x][y] = TileType.WallNW;
							break;
						case N+E+W:
							tiles[x][y] = TileType.WallNEW;
							break;
						case N+E+S:
							tiles[x][y] = TileType.WallNES;
							break;
						case N+W+S:
							tiles[x][y] = TileType.WallNWS;
							break;
						case W+S+E:
							tiles[x][y] = TileType.WallWSE;
							break;
						default:
							tiles[x][y] = TileType.Wall;
					}
				}
			}
		}
	}

	private boolean isEdge(int posX, int posY) {
		if (posX == 0 || posX == this.columns - 1) {
			return true;
		}
		if (posY == 0 || posY == this.rows - 1) {
			return true;
		}
		return false;
	}

	/*
	public boolean writeToFile(String filepath) {
		try {
			PrintWriter printer = new PrintWriter(filepath);
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					if (x == width - 1) {
						printer.write(getCharForTileType(tiles[x][y]) + "\n");
					} else {
						printer.write(getCharForTileType(tiles[x][y]));
					}
					printer.flush();
				}
			}
			printer.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	*/
	private char getCharForTileType(TileType tile) {
		switch (tile) {
			case Wall:
				return '#';
			case WallN:
				return 'T';
			case WallNE:
				return '\\';
			case WallE:
				return '|';
			case WallSE:
				return '/';
			case WallS:
				return '_';
			case WallSW:
				return '\\';
			case WallW:
				return '|';
			case WallNW:
				return '/';
			case WallNEW:
				return 'A';
			case WallNES:
				return '>';
			case WallNWS:
				return '<';
			case WallWSE:
				return 'V';
			case Floor:
				return ' ';
			default:
				return 'x';
		}
	}

	
	public int getWidth() {
		return this.totalWidth;
	}


	public int getHeight() {
		return this.totalHeight;
	}
	
	public TileType[][] getTiles(){
		return this.tiles;
	}

}
