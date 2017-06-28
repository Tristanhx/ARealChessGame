package com.example.tristan.arealchessgame.chess_engine.pieces;

import com.example.tristan.arealchessgame.chess_engine.Alliance;
import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.move.Move;
import com.example.tristan.arealchessgame.chess_engine.PieceType;

import java.util.Collection;

/**
 * Created by Tristan on 11/06/2017.
 */

public class Rook extends Piece {

    final boolean isFirstMove;

    private static final int[][][] POSSIBLE_MOVES =
            /* Up */
            {{{0, -1}, {0, -2}, {0, -3}, {0, -4}, {0, -5}, {0, -6}, {0, -7}},
                    /* Down */
            {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7}},
                    /* Left */
            {{-1, 0}, {-2, 0}, {-3, 0}, {-4, 0}, {-5, 0}, {-6, 0}, {-7, 0}},
                    /* Right */
            {{1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}}};

    public Rook(int xPosition, int yPosition, Alliance alliance, final boolean isFirstMove) {
        super(PieceType.ROOK, xPosition, yPosition, alliance, isFirstMove);
        this.isFirstMove = isFirstMove;
    }

    @Override
    public String toString(){
        return PieceType.ROOK.toString();
    }

    @Override
    public Collection<Move> legalMoves(Board board) {

        return bishopRookQueenMoves(board, POSSIBLE_MOVES);
    }

    @Override
    public Piece movePiece(Move move) {
        return new Rook(move.getXDestination(), move.getYDestination(), move.getPiece().getAlliance(), false);
    }
    public boolean isFirstMove(){
        return isFirstMove;
    }
}
