package com.example.tristan.arealchessgame.chess_engine.board;

import com.example.tristan.arealchessgame.chess_engine.Tools;
import com.example.tristan.arealchessgame.chess_engine.pieces.Piece;

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

    @Override
    public boolean equals(Object other){
        if (this == other){
            return true;
        }
        if (!(other instanceof Tile)){
            return false;
        }
        final Tile otherTile = (Tile) other;
        return getxCoordinate() == otherTile.getxCoordinate() && getyCoordinate() ==
                otherTile.getyCoordinate() && getPiece() == otherTile.getPiece();
    }



    public static Tile createTile(final int xCoordinate, final int yCoordinate, final Piece piece){
        return piece != null ? new TileOcc(xCoordinate, yCoordinate, piece) :
                EMPTY_TILES.get(Tools.convertPosition(xCoordinate, yCoordinate));
    }

    Tile (int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getxCoordinate(){
        return xCoordinate;
    }

    public int getyCoordinate(){
        return yCoordinate;
    }

    // This returns whether or not a tile is occupied
    // It is easier to ask a tile if it is occupied that to ask every piece where it is
    public abstract boolean tileIsOccupied();

    // Returns an occupying piece
    public abstract Piece getPiece();
}
