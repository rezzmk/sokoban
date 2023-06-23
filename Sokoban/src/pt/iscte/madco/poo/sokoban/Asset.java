package pt.iscte.madco.poo.sokoban;

import pt.iscte.madco.poo.sokoban.abstractions.AbstractSObject;
import pt.iscte.madco.poo.sokoban.sprites.*;
import pt.iul.ista.poo.utils.Point2D;

public enum Asset {
    DEFAULT    (GameConstants.SPRITE_FLOOR_ID    ),
    TARGET     (GameConstants.SPRITE_TARGET_ID   ),
    FLOORTILE  (GameConstants.SPRITE_FLOOR_ID    ),
    HOLE       (GameConstants.SPRITE_HOLE_ID     ),
    BOX        (GameConstants.SPRITE_BOX_ID      ),
    WALL       (GameConstants.SPRITE_WALL_ID     ),
    PLAYER     (GameConstants.SPRITE_PLAYER_ID   ),
    BATTERY    (GameConstants.SPRITE_BATTERY_ID  ),
    LSTONE     (GameConstants.SPRITE_LSTONE_ID   ),
    HAMMER     (GameConstants.SPRITE_HAMMER_ID   ),
    BROKENWALL (GameConstants.SPRITE_BRWALL_ID   ),
    PORTAL     (GameConstants.SPRITE_PORTAL_ID   ),
    ICE        (GameConstants.SPRITE_ICE_ID      ),
    SMALLSTONE (GameConstants.SPRITE_SMALLROCK_ID);

    private final char spriteType;

    Asset(char spriteType) {
        this.spriteType = spriteType;
    }

    public static Asset getSprite(final char spriteType) {
        for (Asset type : Asset.values())
            if (type.spriteType == spriteType)
                return type;

        return null;
    }

    public static AbstractSObject getTile(char spriteType, Point2D point2D) {
        return getTile(Asset.getSprite(spriteType), point2D);
    }

    public static boolean isDefault(AbstractSObject obj) {
        return obj instanceof FloorTile;
    }

    // Maps the sprites to its respective classes
    public static AbstractSObject getTile(Asset asset, Point2D point2D ) {

        if (asset == Asset.PLAYER)     return new Player(point2D);
        if (asset == Asset.TARGET)     return new Target(point2D);
        if (asset == Asset.BOX)        return new Box(point2D);
        if (asset == Asset.HOLE)       return new Hole(point2D);
        if (asset == Asset.WALL)       return new Wall(point2D);
        if (asset == Asset.FLOORTILE)  return new FloorTile(point2D);
        if (asset == Asset.BATTERY)    return new Battery(point2D);
        if (asset == Asset.SMALLSTONE) return new SmallStone(point2D);
        if (asset == Asset.LSTONE)     return new LargeStone(point2D);
        if (asset == Asset.HAMMER)     return new Hammer(point2D);
        if (asset == Asset.BROKENWALL) return new BrokenWall(point2D);
        if (asset == Asset.ICE)        return new Ice(point2D);
        if (asset == Asset.PORTAL) 	   return new Portal(point2D);

        return new FloorTile(point2D);
    }
}
