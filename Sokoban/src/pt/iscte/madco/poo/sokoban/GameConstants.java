package pt.iscte.madco.poo.sokoban;

public class GameConstants {
    private GameConstants () { }

    public static final String SOKOBAN_LEVELS_DIR = "levels";

    // File reading configuration, each character implies a different sprite
    public static final char SPRITE_TARGET_ID    = 'X';
    public static final char SPRITE_FLOOR_ID     = ' ';
    public static final char SPRITE_HOLE_ID      = 'O';
    public static final char SPRITE_BOX_ID       = 'C';
    public static final char SPRITE_WALL_ID      = '#';
    public static final char SPRITE_PLAYER_ID    = 'E';
    public static final char SPRITE_BATTERY_ID   = 'b';
    public static final char SPRITE_SMALLROCK_ID = 'p';
    public static final char SPRITE_HAMMER_ID    = 'm';
    public static final char SPRITE_LSTONE_ID    = 'P';
    public static final char SPRITE_BRWALL_ID    = '%';
    public static final char SPRITE_PORTAL_ID    = 't';
    public static final char SPRITE_ICE_ID       = 'g';

    // The player sprite can have multiple directions, having multiple images associated
    public static final String SPRITE_PLAYER_UP_IMAGE    = "Empilhadora_U";
    public static final String SPRITE_PLAYER_DOWN_IMAGE  = "Empilhadora_D";
    public static final String SPRITE_PLAYER_LEFT_IMAGE  = "Empilhadora_L";
    public static final String SPRITE_PLAYER_RIGHT_IMAGE = "Empilhadora_R";
    public static final String SPRITE_PORTAL_ONE_IMAGE   = "Portal_Azul";
    public static final String SPRITE_PORTAL_TWO_IMAGE   = "Portal_Verde";

    public static final String SPRITE_TARGET_IMAGE    = "Alvo";
    public static final String SPRITE_WALL_IMAGE      = "Parede";
    public static final String SPRITE_FLOOR_IMAGE     = "Chao";
    public static final String SPRITE_BOX_IMAGE       = "Caixote";
    public static final String SPRITE_HOLE_IMAGE      = "Buraco";
    public static final String SPRITE_BATTERY_IMAGE   = "Bateria";
    public static final String SPRITE_SMALLROCK_IMAGE = "SmallStone";
    public static final String SPRITE_LSTONE_IMAGE    = "BigStone";
    public static final String SPRITE_HAMMER_IMAGE    = "Martelo";
    public static final String SPRITE_BRWALL_IMAGE    = "Parede_Partida";
    public static final String SPRITE_ICE_IMAGE       = "Gelo";

    // Every sprite has to have a layer in the map, we use this for collision detection
    public static final int SPRITE_FLOOR_LAYER     = 0;
    public static final int SPRITE_HOLE_LAYER      = 1;
    public static final int SPRITE_BATTERY_LAYER   = 1;
    public static final int SPRITE_TARGET_LAYER    = 1;
    public static final int SPRITE_PLAYER_LAYER    = 2;
    public static final int SPRITE_BOX_LAYER       = 2;
    public static final int SPRITE_SMALLROCK_LAYER = 2;
    public static final int SPRITE_LSTONE_LAYER    = 2;
    public static final int SPRITE_HAMMER_LAYER    = 1;
    public static final int SPRITE_BRWALL_LAYER    = 3;
    public static final int SPRITE_WALL_LAYER      = 3;
    public static final int SPRITE_PORTAL_LAYER    = 1;
    public static final int SPRITE_ICE_LAYER       = 1;

    public static final String HAMMER_INTERACTION_PROPERTY = "hasHammer";
    public static final String OBJECT_BLOCKED_ATTR_NAME = "blocked";

    public static int MAX_ENERGY = 100;
    public static String SCORES_FILE_NAME = "scores.out";
}
