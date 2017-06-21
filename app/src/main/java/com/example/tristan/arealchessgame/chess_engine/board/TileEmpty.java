package com.example.tristan.arealchessgame.chess_engine.board;

import com.example.tristan.arealchessgame.chess_engine.pieces.Piece;

/**
 * Created by Tristan on 10/06/2017.
 */

public class TileEmpty extends Tile {

    TileEmpty(int xCoordinate, int yCoordinate) {
        super(xCoordinate, yCoordinate);
    }

    @Override
    public String toString(){
        return "-";
    }

    @Override
    public boolean tileIsOccupied() {
        return false;
    }

    @Override
    public Piece getPiece() {
        return null;
    }
}
