package pt.iscte.dcti.poo.sokoban;

import java.util.List;

import pt.iscte.dcti.poo.sokoban.models.Target;
import pt.iscte.dcti.poo.sokoban.interfaces.Controllable;
import pt.iscte.dcti.poo.sokoban.models.Battery;
import pt.iscte.dcti.poo.sokoban.models.Hole;
import pt.iscte.dcti.poo.sokoban.models.Box;
import pt.iscte.dcti.poo.sokoban.models.FloorTile;
import pt.iscte.dcti.poo.sokoban.models.LargeStone;
import pt.iscte.dcti.poo.sokoban.models.Wall;
import pt.iscte.dcti.poo.sokoban.models.SmallStone;
import pt.iscte.dcti.poo.sokoban.models.Player;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

/**
* This enum represents the Sprites for a sokoban game. These are commonly known as assets
* What is a Sprite? https://pt.wikipedia.org/wiki/Sprite_(computa%C3%A7%C3%A3o_gr%C3%A1fica)
* 
* @author Marcos Caramalho 90292 LEI-PL
*/
public enum Asset {
	TARGET     (SokobanConstants.SPRITE_TARGET_ID   ),
	FLOORTILE  (SokobanConstants.SPRITE_FLOOR_ID    ),
	HOLE       (SokobanConstants.SPRITE_HOLE_ID     ),
	BOX        (SokobanConstants.SPRITE_BOX_ID      ),
	WALL       (SokobanConstants.SPRITE_WALL_ID     ),
	PLAYER     (SokobanConstants.SPRITE_PLAYER_ID   ),
	BATTERY    (SokobanConstants.SPRITE_BATTERY_ID  ),
	LSTONE     (SokobanConstants.SPRITE_LSTONE_ID   ),
	SMALLSTONE (SokobanConstants.SPRITE_SMALLROCK_ID);

	private char assetType;

	Asset(char assetType) {
		this.assetType = assetType;
	}

	public static Asset getAsset(final char assetType) {
		for (Asset type : Asset.values())
			if (type.assetType == assetType)
				return type;

		return null;
	}

	public static ImageTile getTile(char assetType, Point2D point2D) {
		return getTileInternal(Asset.getAsset(assetType), point2D);
	}
	
	public static ImageTile getTile(Asset assetType, Point2D point2D) {
		return getTileInternal(assetType, point2D);
	}
	
	// Maps the sprites to its respective classes
	private static ImageTile getTileInternal(Asset asset, Point2D point2D ) {

		if (asset == Asset.PLAYER)     return new Player(point2D);
		if (asset == Asset.TARGET)     return new Target(point2D);
		if (asset == Asset.BOX)        return new Box(point2D);
		if (asset == Asset.HOLE)       return new Hole(point2D);
		if (asset == Asset.WALL)       return new Wall(point2D);
		if (asset == Asset.FLOORTILE)  return new FloorTile(point2D);	
		if (asset == Asset.BATTERY)    return new Battery(point2D);
		if (asset == Asset.SMALLSTONE) return new SmallStone(point2D);
		if (asset == Asset.LSTONE)     return new LargeStone(point2D);

		return new FloorTile(point2D);
	}

	/**
	 * <p>
	 * Checks if a given ImageTile is of type Asset.BOX or not
	 * </p>
	 * @param tile the ImageTile to check
	 * @return wether the given ImageTile is Asset.BOX or not
	 */
	public static boolean isBox(ImageTile tile) {
		return tile instanceof Box;
	}
	
	/**
	 * <p>
	 * Checks if a given ImageTile is of type Asset.BOX or not
	 * </p>
	 * @param tile the ImageTile to check
	 * @return wether the given ImageTile is Asset.BOX or not
	 */
	public static boolean hasBox(List<ImageTile> tiles) {
		for(ImageTile tile : tiles) if (tile instanceof Box) return true;
		return false;
	}
	
	/**
	 * <p>
	 * Checks if a given ImageTile is of type Asset.WALL or not
	 * </p>
	 * @param tile the ImageTile to check
	 * @return wether the given ImageTile is Asset.WALL or not
	 */
	public static boolean hasWall(List<ImageTile> tiles) {
		for(ImageTile tile : tiles) if (tile instanceof Wall) return true;
		return false;
	}
	
	/**
	 * <p>
	 * Checks if a given ImageTile is of type Asset.BATTERY or not
	 * </p>
	 * @param tile the ImageTile to check
	 * @return wether the given ImageTile is Asset.BATTERY or not
	 */
	public static boolean isBattery(ImageTile tile) {
		return tile instanceof Battery;
	}
	
	/**
	 * <p>
	 * Checks if a given ImageTile is of type Asset.BATTERY or not
	 * </p>
	 * @param tile the ImageTile to check
	 * @return wether the given ImageTile is Asset.BATTERY or not
	 */
	public static boolean hasBattery(List<ImageTile> tiles) {
		for(ImageTile tile : tiles) if (tile instanceof Battery) return true;
		return false;
	}
	
	/**
	 * <p>
	 * Checks if a given ImageTile is of type Asset.BATTERY or not
	 * </p>
	 * @param tile the ImageTile to check
	 * @return wether the given ImageTile is Asset.BATTERY or not
	 */
	public static boolean hasHole(List<ImageTile> tiles) {
		for(ImageTile tile : tiles) if (tile instanceof Hole) return true;
		return false;
	}
	
	/**
	 * <p>
	 * Checks if a given ImageTile is of type Asset.HOLE or not
	 * </p>
	 * @param tile the ImageTile to check
	 * @return wether the given ImageTile is Asset.HOLE or not
	 */
	public static boolean isHole(ImageTile tile) {
		return tile instanceof Hole;
	}
	
	/**
	 * <p>
	 * Checks if a given ImageTile is of type Asset.TARGET or not
	 * </p>
	 * @param tile the ImageTile to check
	 * @return wether the given ImageTile is Asset.TARGET or not
	 */
	public static boolean isTarget(ImageTile tile) {
		return tile instanceof Target;
	}
	
	/**
	 * <p>
	 * Checks if a given ImageTile is of type Asset.WALL or not
	 * </p>
	 * @param tile the ImageTile to check
	 * @return wether the given ImageTile is Asset.WALL or not
	 */
	public static boolean isWall(ImageTile tile) {
		return tile instanceof Wall;
	}
	
	/**
	 * <p>
	 * Checks if a given ImageTile is of type Asset.PLAYER or not
	 * </p>
	 * @param tile the ImageTile to check
	 * @return wether the given ImageTile is Asset.PLAYER or not
	 */
	public static boolean isPlayer(ImageTile tile) {
		return tile instanceof Controllable;
	}
	
	/**
	 * <p>
	 * Gets a cast version of a Asset.BOX. Consumer of this method will be
	 * abstracted of the target class
	 * </p>
	 * @param tile the ImageTile to cast
	 * @return casted tile
	 */
	public static Box toBoxObject(ImageTile tile) {
		return (Box) tile;
	}
	
	/**
	 * <p>
	 * Gets a cast version of a Asset.PLAYER. Consumer of this method will be
	 * abstracted of the target class
	 * </p>
	 * @param tile the ImageTile to cast
	 * @return casted tile
	 */
	public static Player toPlayerObject(ImageTile tile) {
		return (Player) tile;
	}
}