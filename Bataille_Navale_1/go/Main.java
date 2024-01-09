package go;

import bo.Game;
import bo.Map;
import bo.Ship;

public class Main {
    public static void main(String[] args) {
        int sizeX = 10;
        int sizeY = 10;
        Map map = new Map(sizeX, sizeY);

        //create 4 maps
        String[][] mapDefP1 = map.createMap();
        String[][] mapAtkP1 = map.createMap();
        String[][] mapDefP2 = map.createMap();
        String[][] mapAtkP2 = map.createMap();

        //create different boats
        Ship torpilleur = new Ship(2);
        Ship sousMarin = new Ship(3);
        Ship contreTorpilleur = new Ship(3);
        Ship croiseur = new Ship(4);
        Ship porteAvion = new Ship(5);

        //place boats for p1
        mapDefP1 = torpilleur.placeBoat(mapDefP1, sizeX, sizeY);
        mapDefP1 = sousMarin.placeBoat(mapDefP1, sizeX, sizeY);
        mapDefP1 = contreTorpilleur.placeBoat(mapDefP1, sizeX, sizeY);
        mapDefP1 = croiseur.placeBoat(mapDefP1, sizeX, sizeY);
        mapDefP1 = porteAvion.placeBoat(mapDefP1, sizeX, sizeY);

        //place boats for p2
        mapDefP2 = torpilleur.placeBoat(mapDefP2, sizeX, sizeY);
        mapDefP2 = sousMarin.placeBoat(mapDefP2, sizeX, sizeY);
        mapDefP2 = contreTorpilleur.placeBoat(mapDefP2, sizeX, sizeY);
        mapDefP2 = croiseur.placeBoat(mapDefP2, sizeX, sizeY);
        mapDefP2 = porteAvion.placeBoat(mapDefP2, sizeX, sizeY);

        //create new game
        Game play = new Game();

        //launch it
        play.playGame(map, mapDefP1, mapAtkP1, mapDefP2, mapAtkP2, 1);
    }
}
