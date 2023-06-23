package pt.iscte.madco.poo.sokoban.gui;

import pt.iscte.madco.poo.sokoban.GameConstants;
import pt.iscte.madco.poo.sokoban.abstractions.AbstractSObject;
import pt.iscte.madco.poo.sokoban.abstractions.GameEngine;
import pt.iscte.madco.poo.sokoban.commands.RemoveObjectCommand;
import pt.iscte.madco.poo.sokoban.sprites.FloorTile;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Runs winning, losing animations. Buggy since specs don't allow changing the GraphPack :(
 */
public class Animation {
    public static void runLosingAnimation(GameEngine engine, List<AbstractSObject> tiles) {
		List<AbstractSObject> filteredList = tiles.stream().filter(x -> !(x instanceof FloorTile)).collect(Collectors.toList());
		removeFromFilter(engine, filteredList, 2);
	}

	public static void runWinningAnimation(GameEngine engine, List<AbstractSObject> tiles) {
		List<AbstractSObject> filteredList = tiles.stream().filter(x -> x.getLayer() == GameConstants.SPRITE_WALL_LAYER).collect(Collectors.toList());
		removeFromFilter(engine, filteredList, 20 / filteredList.size() + 10);
	}

	private static void removeFromFilter(GameEngine engine, List<AbstractSObject> filteredList, int timeout) {
    	// This contains a sort of a bug.
		// As specified on the project specs, we can't change the code in Graphpack, which we'd need in order to
		// get this to not bug when we click any key. It works but it'l look like a freeze since we'd need to
		// manipulate key listening on the root
		for (AbstractSObject obj : filteredList) {
			new RemoveObjectCommand(obj).execute();
			engine.updateScreen();

			try {
				TimeUnit.MILLISECONDS.sleep(timeout);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
