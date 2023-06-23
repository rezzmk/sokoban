package pt.iscte.madco.poo.sokoban.helpers.sokoban;

import pt.iscte.madco.poo.sokoban.Sokoban;
import pt.iscte.madco.poo.sokoban.abstractions.AbstractSObject;
import pt.iscte.madco.poo.sokoban.commands.MoveCommand;
import pt.iscte.madco.poo.sokoban.interfaces.Actor;
import pt.iscte.madco.poo.sokoban.interfaces.Command;
import pt.iscte.madco.poo.sokoban.interfaces.Interactable;
import pt.iscte.madco.poo.sokoban.abstractions.Movable;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AssetBehaviorHandler {
    private AssetBehaviorHandler() {
        throw new IllegalStateException("AssetBehaviorHandler is a Utility class, should not be instantiated");
    }
    /**
     * Runs the batch of interactions for the objects in the target position, relative to the base, which is
     * the object that's moving/interacting with something.
     */
    public static boolean runInteractions(AbstractSObject base, Direction direction) {
        List<Command> commands = new ArrayList<>();

        // 1 -> Sets the target/new position on the base object
        Point2D pos = base.setAndValidateNewPosition(direction);

        // 2 -> Gets a list of objects in the target/new position that can be interacted with
        //      CRITERIA: Has to be Interactable and/or Movable, based on position
        List<AbstractSObject> tilesToRunInteractionsOn = Sokoban
                .getInstance()
                .getGameSession()
                .getLoadedTiles()
                .stream()
                .filter(
                        o -> (   o instanceof Interactable || o instanceof Movable )
                              && o.getPosition().equals(pos)
                )
                .collect(Collectors.toList());

        // When there are no tiles to interact with, we check if the base is Movable, and if so, move it freely
        if (tilesToRunInteractionsOn.size() == 0) {
            if (base instanceof Movable) {
                base.setPosition(pos);
                Sokoban.getInstance().getGameSession().updateScores();
                Sokoban.getInstance().updateScreen();
            }
        }

        // Sort the list by order of importance.
        // We have to run the Movables first or we risk issues with certain after run commands
        tilesToRunInteractionsOn.sort((o1, o2) -> {
            if (o1 instanceof Movable && o2 instanceof Movable) return 0;
            if (o1 instanceof Movable) return -1;
            if (o2 instanceof Movable) return 1;

            return 0;
        });


        // Iterate the tiles and run the interactions
        for (AbstractSObject tile : tilesToRunInteractionsOn) {
            if (tile instanceof Movable) {
                // If interacting with a Movable but base is not the Actor (player), we shouldn't do anything, IE: blocked
                if (!(base instanceof Actor)) return false;

                // Move the tile if the base is at the same layer
                if (base.getLayer() == tile.getLayer()) {
                    Boolean commandResult = new MoveCommand((Movable) tile, direction).execute();
                    if (!(commandResult))
                        return false;
                }
            }

            // When not dealing with Movables, interactions might exist, like consuming, breaking etc...
            // run them here
            if (tile instanceof Interactable) {
                if (!((Interactable)tile).interact(base)) {
                    return false;
                }

                // Get the list of commands to run after the interactions are done
                commands = ((Interactable)tile).afterInteractionCommands(base);
            }
        }

        if (commands == null) return false;

        // This is most likely not the most elegant solution but it seems to be the easiest fix
        // for a specific object in the game... ICE, placing this here prevents common stack overflow problem
        //
        // ? This mostly happens because we're using Command pattern and have to sacrifice a little
        // for expected recursive behaviors, such as the ICE sliding ...
        if (base instanceof Movable) {
            base.setPosition(pos);
            Sokoban.getInstance().updateScreen();
        }

        // Run the commands
        commands.forEach(Command::execute);

        // If it gets here, it has effectively ran everything OK, return true and went on.
        // This is a leave early pattern method, therefore, there's a chance that it gets out early when moving objects
        // and so on...
        return true;
    }
}

