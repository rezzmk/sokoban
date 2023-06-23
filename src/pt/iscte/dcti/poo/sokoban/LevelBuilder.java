package pt.iscte.dcti.poo.sokoban;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pt.iscte.dcti.poo.sokoban.models.FloorTile;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class LevelBuilder {
	private LevelBuilder() {
		throw new IllegalStateException("LevelBuilder is a Utility class, should not be instantiated");
	}

	public static List<ImageTile> buildLevel(int level) {
		return readLevelFromFile(level);
	}

	public static boolean levelExists(int level) {
		Path path = Paths.get(SokobanConstants.SOKOBAN_LEVELS_DIR, String.format("level%d.txt", level));
		return FileHelper.fileExists(path.toString());
	}
	
	public static List<ImageTile> readLevelFromFile(int level) {
		// Gets the map level grid from the file
		char[][] levelGrid;
		try {
			Path path = Paths.get(SokobanConstants.SOKOBAN_LEVELS_DIR, String.format("level%d.txt", level));

			levelGrid = FileHelper.get2DArrayFromFile(path.toString());
									
			List<ImageTile> tiles = new ArrayList<>();

			for (int x = 0; x < 10; x++) {
				for (int y = 0; y < 10; y++) {
					ImageTile tile = Asset.getTile(levelGrid[x][y], new Point2D(x, y));
					tiles.add(tile);
					
					if ( !(tile instanceof FloorTile) ) {
						tiles.add(new FloorTile(new Point2D(x, y)));
					}
				}
			}

			return tiles;
			
		} catch (IOException e) { return Collections.emptyList(); }
	}
}
