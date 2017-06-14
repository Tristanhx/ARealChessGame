package com.example.tristan.arealchessgame.ChessEngine.board;

import com.example.tristan.arealchessgame.ChessEngine.PositionConverter;
import com.example.tristan.arealchessgame.ChessEngine.Tools;
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
    
    private static final Map<Integer, TileEmpty> EMPTY_TILES = createAllEmptyTiles();

    private static Map<Integer,TileEmpty> createAllEmptyTiles() {
        Map<Integer, TileEmpty> emptyTileMap = new HashMap<>();

        for (int yRow = 0; yRow < Tools.BOARD_DIM ; yRow++){
            for (int xColumn = 0 ; xColumn < Tools.BOARD_DIM ; xColumn++){
                emptyTileMap.put(Tools.convertPosition(xColumn, yRow), new TileEmpty(xColumn, yRow));
            }
        }
        return emptyTileMap;
    }



    public static Tile createTile(final int xCoordinate, final int yCoordinate, final Piece piece){
        return piece != null ? new TileOcc(xCoordinate, yCoordinate, piece) :
                EMPTY_TILES.get(Tools.convertPosition(xCoordinate, yCoordinate));
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

    public int getxCoordinate(){
        return xCoordinate;
    }

    public int getyCoordinate(){
        return yCoordinate;
    }
}
