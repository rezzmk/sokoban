package pt.iscte.madco.poo.sokoban.data;

import java.io.Serializable;

/**
 * Represents a user save, which is just the score for a certain level, highly extendable
 */
public class UserSave implements Serializable {
    static final long serialVersionUID = 0L;

    private final int level;
    private int score;

    public UserSave(int level, int score) {
        this.level = level;
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
