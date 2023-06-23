package pt.iscte.madco.poo.sokoban.helpers.sokoban;

import pt.iscte.madco.poo.sokoban.GameConstants;
import pt.iscte.madco.poo.sokoban.Asset;
import pt.iscte.madco.poo.sokoban.Sokoban;
import pt.iscte.madco.poo.sokoban.abstractions.AbstractSObject;
import pt.iscte.madco.poo.sokoban.exceptions.LevelDoesNotExistException;
import pt.iscte.madco.poo.sokoban.helpers.FileHelper;
import pt.iscte.madco.poo.sokoban.sprites.Portal;
import pt.iul.ista.poo.utils.Point2D;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LevelBuilder {
    private LevelBuilder() {
        throw new IllegalStateException("LevelBuilder is a Utility class, should not be instantiated");
    }

    public static boolean isLastLevel(int level) {
        File levelsFolder = new File(GameConstants.SOKOBAN_LEVELS_DIR);
        File[] listOfFiles = levelsFolder.listFiles();

        if (listOfFiles == null) return false;

        for (File file : listOfFiles) {
            if (file.getName().startsWith("level")) {
                String fileNameNoExt = file.getName().replaceFirst("[.][^.]+$", "");
                if (Integer.parseInt(fileNameNoExt.substring(5)) > level) return false;
            }
        }

        return true;
    }

    public static List <AbstractSObject> build(int level) throws LevelDoesNotExistException {
        // Gets the map level grid from the file
        char[][] levelGrid;
        try {
            Path path = Paths.get(GameConstants.SOKOBAN_LEVELS_DIR, String.format("level%d.txt", level));

            if (!FileHelper.fileExists(path.toString())) {
                throw new LevelDoesNotExistException(String.format("Level does not exist (%d on %s)", level, path.toString()));
            }

            levelGrid = FileHelper.get2DArrayFromFile(path.toString());

            List <AbstractSObject> tiles = new ArrayList <>();

            for (int x = 0; x < levelGrid.length; x++) {
                for (int y = 0; y < levelGrid[0].length; y++) {
                    AbstractSObject tile = Asset.getTile(levelGrid[x][y], new Point2D(x, y));
                    tiles.add(tile);

                    if (!Asset.isDefault(tile))
                        tiles.add(Asset.getTile(Asset.DEFAULT, new Point2D(x, y)));
                }
            }

            // This is quite ugly, couldn't figure out a better way to do it in time :(
            Portal portal = (Portal) tiles.stream().filter(p -> p instanceof Portal).findFirst().orElse(null);
            if (portal != null) {
                portal.swapImage();
            }

            Sokoban.getInstance().setCanvasSize(levelGrid.length, levelGrid[0].length);
            return tiles;

        } catch (IOException e) {
            return Collections.emptyList();
        }
    }
}
