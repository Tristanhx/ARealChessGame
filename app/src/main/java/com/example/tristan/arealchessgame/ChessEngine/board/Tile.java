package com.example.tristan.arealchessgame.ChessEngine.board;

import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tristan on 06/06/2017.
 */

public abstract class Tile {

    // Every Tile has an x and y Coordinate
    int xCoordinate;
    int yCoordinate;
    
    Map<Integer, TileEmpty> EMPTY_TILES = createAllEmptyTiles();

    private Map<Integer,TileEmpty> createAllEmptyTiles() {
        Map<Integer, TileEmpty> emptyTileMap = new HashMap<>();

        for (int i = 0 ; i < 8 ; i++){
            for (int j = 0 ; j < 8 ; j++){
                emptyTileMap.put(i*j, new TileEmpty(i, j));
            }
        }
        return emptyTileMap;
    }

    Tile (int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    // This returns whether or not a tile is occupied
    // It is easier to ask a tile if it is occupied that to ask every piece where it is
    public abstract boolean tileIsOccupied();

    // Returns an occupying piece
    public abstract Piece getPiece();
}
