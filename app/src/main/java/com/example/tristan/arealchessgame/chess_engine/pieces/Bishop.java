package com.example.tristan.arealchessgame.chess_engine.pieces;

import com.example.tristan.arealchessgame.chess_engine.Alliance;
import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.move.Move;
import com.example.tristan.arealchessgame.chess_engine.PieceType;

import java.util.Collection;

/**
 * Created by Tristan on 11/06/2017.
 */

public class Bishop extends Piece {

    private final int[][][] POSSIBLE_MOVES =
            /* LeftUP */
            {{{-1, -1}, {-2, -2}, {-3, -3}, {-4, -4}, {-5, -5}, {-6, -6}, {-7, -7}},
                    /* LeftDown */
                    {{-1, 1}, {-2, 2}, {-3, 3}, {-4, 4}, {-5, 5}, {-6, 6}, {-7, 7}},
                    /* RightUp */
                    {{1, -1}, {2, -2}, {3, -3}, {4, -4}, {5, -5}, {6, -6}, {7, -7}},
                    /* RightDown */
                    {{1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}, {6, 6}, {7, 7}}};

    public Bishop(int xPosition, int yPosition, Alliance alliance, final boolean isFirstMove) {
        super(PieceType.BISHOP, xPosition, yPosition, alliance, isFirstMove);
    }

    @Override
    public String toString(){
        return PieceType.BISHOP.toString();
    }

    @Override
    public Collection<Move> legalMoves(Board board) {
        return bishopRookQueenMoves(board, POSSIBLE_MOVES);
    }

    @Override
    public Piece movePiece(Move move) {
        return new Bishop(move.getXDestination(), move.getYDestination(), move.getPiece().getAlliance(), false);
    }
}
