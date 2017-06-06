package com.example.tristan.arealchessgame.ChessEngine.pieces;

import com.example.tristan.arealchessgame.Alliance;
import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.board.Move;

import java.util.List;

/**
 * Created by Tristan on 05/06/2017.
 */

public class Knight extends Piece{

    Knight(final int position, final Alliance alliance) {
        super(position, alliance);
    }

    @Override
    public List<Move> legalMoves(Board board) {
        return null;
    }
}
