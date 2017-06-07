package com.example.tristan.arealchessgame.ChessEngine.pieces;

import com.example.tristan.arealchessgame.Alliance;
import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.board.Move;

import java.util.List;

/**
 * Created by trist on 6/7/2017.
 */

public class EmptyTile extends Piece {

    EmptyTile(int position, Alliance alliance) {
        super(position, alliance);
    }

    @Override
    public List<Move> legalMoves(Board board) {
        return null;
    }
}
