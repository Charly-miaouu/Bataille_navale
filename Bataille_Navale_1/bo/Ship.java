package bo;

import java.util.Objects;
import java.util.Random;

public class Ship {
    private int size;

    public Ship(int size) {
        this.size = size;
    }

    public String[][] placeBoat(String[][] map, int sizeXMax, int sizeYMax) {
        String length = "U";
        for (int y = 0; y < sizeYMax; y++) {
            for (int x = 0; x < sizeXMax; x++) {
                if (Objects.equals(map[y][x], "U"))
                    length = "S";
            }
        }
        String myChar = sizeShip(length);
        Random random = new Random();
        //random dir
        int direction = random.nextInt(2);

        int startX, startY;

        do {
            if (direction == 0) {
                //to the right
                int maxX = sizeXMax - size;
                startX = random.nextInt(maxX + 1);
                startY = random.nextInt(sizeYMax);
            } else {
                //to the bottom
                int maxY = sizeYMax - size;
                startX = random.nextInt(sizeXMax);
                startY = random.nextInt(maxY + 1);
            }
        } while (isFree(map, startY, startX, sizeXMax, sizeYMax, direction));

        if (direction == 0) {
            for (int i = 0; i < size; i++) {
                map[startY][startX + i] = myChar;
            }
        } else {
            for (int i = 0; i < size; i++) {
                map[startY + i][startX] = myChar;
            }
        }
        return map;
    }

    public String sizeShip(String length) {
        if (size == 2)
            return "T";
        if (size == 3)
            return length;
        if (size == 4)
            return "C";
        if (size == 5)
            return "P";
        return "O";
    }

    public boolean isFree(String[][] map, int startY, int startX, int sizeXMax, int sizeYMax, int direction) {
        for (int i = 0; i < size; i++) {
            // check border
            if (startX + i >= sizeXMax || startY + i >= sizeYMax) {
                return true;
            }
            // check boat right
            if (direction == 0) {
                switch (map[startY][startX + i]) {
                    case "P", "C", "S", "T", "U" -> {
                        return true;
                    }
                }
            } else {
                // check boat bottom
                switch (map[startY + i][startX]) {
                    case "P", "C", "S", "T", "U" -> {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}