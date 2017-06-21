package com.example.tristan.arealchessgame.chess_engine.board;

import com.example.tristan.arealchessgame.chess_engine.pieces.Piece;

/**
 * Created by Tristan on 10/06/2017.
 */

public class TileOcc extends Tile{
    Piece occPiece;

    TileOcc(int xCoordinate, int yCoordinate, Piece occPiece) {
        super(xCoordinate, yCoordinate);
        this.occPiece = occPiece;

    }

    @Override
    public String toString(){
        return getPiece().getAlliance().isBlack() ? getPiece().toString().toLowerCase()
                : getPiece().toString();
    }

    @Override
    public boolean tileIsOccupied() {
        return true;
    }

    @Override
    public Piece getPiece() {
        return this.occPiece;
    }
}
