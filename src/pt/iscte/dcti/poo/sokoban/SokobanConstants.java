package pt.iscte.dcti.poo.sokoban;

/**
* This class provides some configuration values for the game, 
* allowing to easily change tile map formats among other variables
* 
* @author Marcos Caramalho 90292 LEI-PL
*/
public final class SokobanConstants {

    private SokobanConstants() { }

    public static final String SOKOBAN_LEVELS_DIR = "levels";
    public static final String SOKOBAM_SCORES_FILENAME = "scores.txt";
    
    // set in milliseconds (I think) TODO: check this
    public static final int SOKOBAN_WIN_ANIMATION_TIMEOUT = 25;
    public static final int SOKOBAN_LOSE_ANIMATION_TIMEOUT = 5;
    
    public static final int GAME_HEIGHT = 10;
    public static final int GAME_WIDTH = 10;
    
    // File reading configuration, each character implies a different sprite
    public static final char SPRITE_TARGET_ID    = 'X';
    public static final char SPRITE_FLOOR_ID     = ' ';
    public static final char SPRITE_HOLE_ID      = 'O';
    public static final char SPRITE_BOX_ID       = 'C';
    public static final char SPRITE_WALL_ID      = '#';
    public static final char SPRITE_PLAYER_ID    = 'E';
    public static final char SPRITE_BATTERY_ID   = 'b';
    public static final char SPRITE_SMALLROCK_ID = 'p';
    public static final char SPRITE_LSTONE_ID    = 'P';

    // The player sprite can have multiple directions, having multiple images associated
    public static final String SPRITE_PLAYER_UP_IMAGE    = "Empilhadora_U";
    public static final String SPRITE_PLAYER_DOWN_IMAGE  = "Empilhadora_D";
    public static final String SPRITE_PLAYER_LEFT_IMAGE  = "Empilhadora_L";
    public static final String SPRITE_PLAYER_RIGHT_IMAGE = "Empilhadora_R";   
    
    public static final String SPRITE_TARGET_IMAGE    = "Alvo";
    public static final String SPRITE_WALL_IMAGE      = "Parede";
    public static final String SPRITE_FLOOR_IMAGE     = "Chao";
    public static final String SPRITE_BOX_IMAGE       = "Caixote";
    public static final String SPRITE_HOLE_IMAGE      = "Buraco";
    public static final String SPRITE_BATTERY_IMAGE   = "Bateria";
    public static final String SPRITE_SMALLROCK_IMAGE = "SmallStone";
    public static final String SPRITE_LSTONE_IMAGE    = "BigStone";
    
    // Every sprite has to have a layer in the map, we use this for collision detection
    public static final int SPRITE_FLOOR_LAYER     = 0;
    public static final int SPRITE_HOLE_LAYER      = 1;
    public static final int SPRITE_BATTERY_LAYER   = 1;
    public static final int SPRITE_TARGET_LAYER    = 1;
    public static final int SPRITE_PLAYER_LAYER    = 2;
    public static final int SPRITE_BOX_LAYER       = 2;
    public static final int SPRITE_SMALLROCK_LAYER = 2;
    public static final int SPRITE_LSTONE_LAYER    = 2;
    public static final int SPRITE_WALL_LAYER      = 3;           
}
