package bo;

public class Map {

    private int sizeX;

    private int sizeY;


    public Map(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }
    public String[][] createMap() {

        String[][] map = new String[sizeY][sizeX];

        for (int i = 0; i < sizeY; i++) {
            for (int j = 0; j < sizeX; j++) {
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
