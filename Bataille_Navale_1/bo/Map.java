package bo;

import go.Main;
import bo.Game;
import java.util.Arrays;

public class Map {

    private int size_x;

    private int size_y;


    public Map(int size_x, int size_y) {
        this.size_x = size_x;
        this.size_y = size_y;
    }
    public String[][] create_map() {

        String[][] map = new String[size_y][size_x];

        for (int i = 0; i < size_y; i++) {
            for (int j = 0; j < size_x; j++) {
                map[i][j] = "~";
            }
        }
        return map;
    }
    public void displayPlayerSide(String[][] defMap, String[][] atkMap) {
        System.out.println("Defensive Map:");
        displayMap(defMap);

        System.out.println("Offensive Map:");
        displayMap(atkMap);
    }

    private void displayMap(String[][] map) {
        for (String[] row : map) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
