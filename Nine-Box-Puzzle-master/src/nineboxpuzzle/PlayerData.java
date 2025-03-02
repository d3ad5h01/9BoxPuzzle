
package nineboxpuzzle;

import java.util.Comparator;

public class PlayerData implements Comparable<PlayerData> {
    private String name;
    private int score;

    public PlayerData(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    
    @Override
    public String toString() {
        return name + "\t" + score;
    }

    @Override
    public int compareTo(PlayerData o) {
        return this.getScore() - o.getScore();
    }
}
