package level;

import java.awt.image.BufferedImage;

import hilsDontGetRekt.ImageLoader;

public class Level {
	
	private BufferedImage map;
	
	public Level(String mapName) {
		map=ImageLoader.loadImage(mapName);
	}

	public BufferedImage getMap() {
		return map;
	}

	public void setMap(BufferedImage map) {
		this.map = map;
	}
	
	
	

}
