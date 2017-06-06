package com.example.tristan.chess_project.ChessEngine.pieces;

import com.example.tristan.chess_project.Alliance;
import com.example.tristan.chess_project.ChessEngine.board.Board;
import com.example.tristan.chess_project.ChessEngine.board.Move;

import java.util.List;

/**
 * Created by Tristan on 05/06/2017.
 */

public class Knight extends Pieces{

    Knight(final int position, final Alliance alliance) {
        super(position, alliance);
    }

    @Override
    public List<Move> legalMoves(Board board) {
        return null;
    }
}
