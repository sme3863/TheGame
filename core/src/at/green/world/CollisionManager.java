package at.green.world;

import java.util.ArrayList;
import java.util.List;

import at.green.entity.Entity;
import at.green.map.Map;
import at.green.map.TileType;
import at.green.world.collisionshapes.CollisionRectangle;
import at.green.world.collisionshapes.CollisionShape;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class CollisionManager {

	
	
	private static List<CollisionShape> collisionMapObjects = new ArrayList<CollisionShape>();
	private static List<Entity> collisionEntities  = new ArrayList<Entity>();
	
	public static void createCollisionShapesForMap(Map map){
		TileType[][] tiles = map.getTiles();
		int tileWidth = map.getTileWidth();
		int tileHeight = map.getTileHeight();
		for(int x = 0; x < tiles.length; x++){
			for(int y = 0; y < tiles[0].length; y++){
				if(tiles[x][y] != TileType.Floor){
					addCollisionMapObject(new CollisionRectangle(x*tileWidth,y*tileHeight,tileWidth,tileHeight));
				}
			}
		}
		
	}
	
	
	public static void addCollisionMapObject(CollisionShape shape){
		collisionMapObjects.add(shape); //TODO check duplicates
	}
	
	public static void removeCollisionMapObject(CollisionShape shape){
		collisionMapObjects.remove(shape);
	}
	
	public static void addCollisionEntity(Entity shape){
		collisionEntities.add(shape); //TODO check duplicates
	}
	
	public static void removeCollisionEntity(Entity shape){
		collisionEntities.remove(shape);
	}

	public static boolean collidesWithMap(CollisionShape shape){
		Circle entity = (Circle)shape;
		for(CollisionShape r: collisionMapObjects){
			Rectangle mapObject = (Rectangle)r;
			if(Intersector.overlaps(entity, mapObject)){
				return true;
			}
		}
		return false;
	}
	
	
	//TODO untested
	public static Entity collidesWithEntity(Entity entity){
		Circle entity1 = (Circle)entity.getBoundingBox();
		for(Entity e: collisionEntities){
			if(e != entity){
				Circle entity2 = (Circle)e.getBoundingBox();
				if(Intersector.overlaps(entity1, entity2)){
					return e;
				}
			}
		}
		return null;
	}
	
}
