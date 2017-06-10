package com.example.tristan.arealchessgame.ChessEngine.board;

import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;

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
    public boolean tileIsOccupied() {
        return true;
    }

    @Override
    public Piece getPiece() {
        return this.occPiece;
    }
}
