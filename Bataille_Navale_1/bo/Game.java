package bo;

import java.util.Scanner;

public class Game {
    private Scanner scanner;

    public Game() {
        this.scanner = new Scanner(System.in);
    }

    public void playGame(Map map, String[][] map_def_J1, String[][] map_atk_J1, String[][] map_def_J2, String[][] map_atk_J2, int player) {
        while (true) {
            System.out.println("Player " + player + " enter the row for your shot: ");
            int tir_y = scanner.nextInt() - 1;

            System.out.println("Player " + player + " enter the column for your shot: ");
            int tir_x = scanner.nextInt() - 1;

            if (tir_y < 0 || tir_y >= map_atk_J1.length || tir_x < 0 || tir_x >= map_atk_J1[0].length) {
                System.out.println("Invalid input, please try again.");
                continue;
            }

            String[][] currentMapDef;
            String[][] currentMapAtk;
            String[][] currentMapDefToPrint;

            if (player == 1) {
                currentMapDef = map_def_J2;
                currentMapAtk = map_atk_J1;
                currentMapDefToPrint = map_def_J1;
            } else {
                currentMapDef = map_def_J1;
                currentMapAtk = map_atk_J2;
                currentMapDefToPrint = map_def_J2;
            }

            if (currentMapDef[tir_y][tir_x].matches("[BLPCSUT]")) {
                String myLetter = ".";
                System.out.println("Nice shot!");
                currentMapAtk[tir_y][tir_x] = "T";

                switch (currentMapDef[tir_y][tir_x]) {
                    case "B" -> myLetter = "b";
                    case "L" -> myLetter = "l";
                    case "P" -> myLetter = "p";
                    case "C" -> myLetter = "c";
                    case "S" -> myLetter = "s";
                    case "U" -> myLetter = "u";
                    case "T" -> myLetter = "t";
                }

                currentMapDef[tir_y][tir_x] = myLetter;

                if (Couler(currentMapDef, myLetter))
                    currentMapAtk[tir_y][tir_x] = "C";

            } else {
                System.out.println("Splash");
                currentMapAtk[tir_y][tir_x] = "W";
                currentMapDef[tir_y][tir_x] = "o";
            }

            if (!checkEnd(currentMapDef)) {
                map.displayPlayerSide(currentMapDefToPrint, currentMapAtk);
                System.out.println("Player " + player + " wins!");
                System.exit(0);
            }

            map.displayPlayerSide(currentMapDefToPrint, currentMapAtk);

            int nextPlayer = (player == 1) ? 2 : 1;
            playGame(map, map_def_J1, map_atk_J1, map_def_J2, map_atk_J2, nextPlayer);
        }
    }

    public boolean checkEnd(String[][] map) {
        for (String[] row : map) {
            for (String cell : row) {
                if (cell.matches("[BLPCSUT]")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean Couler(String[][] map, String myLetter) {
        for (String[] row : map) {
            for (String cell : row) {
                if (cell.equals(myLetter))
                    return false;
            }
        }
        return true;
    }
}
