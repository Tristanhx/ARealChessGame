package com.example.tristan.arealchessgame.ChessEngine.board;

import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;

/**
 * Created by Tristan on 06/06/2017.
 */

public abstract class Tile {

    // Every Tile has an x and y Coordinate
    int xCoordinate;
    int yCoordinate;

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
