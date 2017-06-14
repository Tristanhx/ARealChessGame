package com.example.tristan.arealchessgame.ChessEngine.board;

import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;

/**
 * Created by Tristan on 15/06/2017.
 */

public class NoMove extends Move {
    NoMove() {
        super(null, null, -1, -1);
    }

    @Override
    public Board execute() {
        throw new RuntimeException("NO MOVE");
    }
}
