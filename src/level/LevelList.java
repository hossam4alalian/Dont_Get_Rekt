package level;

import java.util.ArrayList;

public class LevelList {
	
	private ArrayList<Level>levels=new ArrayList<Level>();
	
	public LevelList() {
		levels.add(new Level("map1.png"));
		levels.add(new Level("map2.png"));
	}

	
	public Level getLevels(int index) {
		return levels.get(index);
	}
	
	

}
