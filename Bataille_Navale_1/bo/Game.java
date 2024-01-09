package bo;

import java.util.Scanner;

public class Game {
    private Scanner scanner;

    public Game() {
        this.scanner = new Scanner(System.in);
    }

    public void playGame(Map map, String[][] mapDefP1, String[][] mapAtkP1, String[][] mapDefP2, String[][] mapAtkP2, int player) {
        while (true) {
            System.out.println("Player " + player + " enter the row for your shot: ");
            int tirY = scanner.nextInt() - 1;

            System.out.println("Player " + player + " enter the column for your shot: ");
            int tirX = scanner.nextInt() - 1;

            //valid attack ?
            if (tirY < 0 || tirY >= mapAtkP1.length || tirX < 0 || tirX >= mapAtkP1[0].length) {
                System.out.println("Invalid input, please try again.");
                continue;
            }

            //the attacked map
            String[][] currentMapDef;
            //the  "history" of the attacker
            String[][] currentMapAtk;
            //the attcker's boats
            String[][] currentMapDefToPrint;

            if (player == 1) {
                currentMapDef = mapDefP2;
                currentMapAtk = mapAtkP1;
                currentMapDefToPrint = mapDefP1;
            } else {
                currentMapDef = mapDefP1;
                currentMapAtk = mapAtkP2;
                currentMapDefToPrint = mapDefP2;
            }

            //hit
            if (currentMapDef[tirY][tirX].matches("[PCSUTO]")) {
                String myLetter = ".";
                System.out.println("Nice shot!");
                currentMapAtk[tirY][tirX] = "H";

                switch (currentMapDef[tirY][tirX]) {
                    case "O" -> myLetter = "o";
                    case "P" -> myLetter = "p";
                    case "C" -> myLetter = "c";
                    case "S" -> myLetter = "s";
                    case "U" -> myLetter = "u";
                    case "T" -> myLetter = "t";
                }

                currentMapDef[tirY][tirX] = myLetter;

                if (isFloating(currentMapDef, myLetter))
                    currentMapAtk[tirY][tirX] = "C";

            } else {
                //not hit
                System.out.println("Plouf");
                currentMapAtk[tirY][tirX] = "W";
                currentMapDef[tirY][tirX] = "X";
            }

            if (!checkEnd(currentMapDef)) {
                map.displayPlayerSide(currentMapDefToPrint, currentMapAtk);
                System.out.println("Player " + player + " wins!");
                System.exit(0);
            }

            map.displayPlayerSide(currentMapDefToPrint, currentMapAtk);

            //change player
            int nextPlayer = (player == 1) ? 2 : 1;
            //new turn
            playGame(map, mapDefP1, mapAtkP1, mapDefP2, mapAtkP2, nextPlayer);
        }
    }

    public boolean checkEnd(String[][] map) {
        for (String[] row : map) {
            for (String cell : row) {
                //check if there is still any boat (with letters)
                if (cell.matches("[PCSUTO]")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isFloating(String[][] map, String myLetter) {
        for (String[] row : map) {
            for (String cell : row) {
                //check if the actual letter is the last of the map
                if (cell.equals(myLetter))
                    return false;
            }
        }
        return true;
    }
}
