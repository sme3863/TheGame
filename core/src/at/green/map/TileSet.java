package at.green.map;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;

public class TileSet {

	
	public BufferedImage[] loadSpriteSheet(String internalPathToSpriteSheet, int tileWidth, int tileHeight) throws IOException {
		Texture tileSheet = new Texture(Gdx.files.internal(internalPathToSpriteSheet));
		TextureData asd = tileSheet.getTextureData();
		
		BufferedImage spriteSheet = ImageIO.read(new File(""));
		return loadSpriteSheet(spriteSheet, tileWidth, tileHeight);
	}

	private BufferedImage[] loadSpriteSheet(BufferedImage spriteSheet,
			int tileWidth, int tileHeight) {
		int columns = 9;//spriteSheet.getWidth() / tileWidth;
		int rows = 7;//spriteSheet.getHeight() / tileHeight;
		return loadSpriteSheet(spriteSheet, tileWidth, tileHeight, rows, columns);
	}

	private BufferedImage[] loadSpriteSheet(BufferedImage spriteSheet,
		int tileWidth, int tileHeight, int rows, int columns) {
		BufferedImage[] sprites = new BufferedImage[rows * columns];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				sprites[(i * columns) + j] = spriteSheet.getSubimage(i
						* tileHeight, j * tileWidth, tileWidth, tileHeight);
			}
		}
		return sprites;
	}
	
	final class BackgroundFilter extends java.awt.image.RGBImageFilter {
	    @Override
	    public int filterRGB(int x, int y, int rgb) {
	        if((rgb & 0xffffff) == 0x0000ff) return 0;
	        else return rgb;
	    }
	}
}
