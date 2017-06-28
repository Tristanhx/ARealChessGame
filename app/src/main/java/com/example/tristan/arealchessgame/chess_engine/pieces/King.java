package com.example.tristan.arealchessgame.chess_engine.pieces;

import com.example.tristan.arealchessgame.chess_engine.Alliance;
import com.example.tristan.arealchessgame.chess_engine.Tools;
import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.move.Move;
import com.example.tristan.arealchessgame.chess_engine.move.MoveAttack;
import com.example.tristan.arealchessgame.chess_engine.move.MoveNormal;
import com.example.tristan.arealchessgame.chess_engine.board.Tile;
import com.example.tristan.arealchessgame.chess_engine.PieceType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Tristan on 11/06/2017.
 */

public class King extends Piece {

    private final int[][] POSSIBLE_MOVES =
            {{-1, -1}, {1, -1}, {-1, 1}, {1, 1}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public King(int xPosition, int yPosition, Alliance alliance, final boolean isFirstMove) {
        super(PieceType.KING, xPosition, yPosition, alliance, isFirstMove);
    }

    @Override
    public String toString(){
        return PieceType.KING.toString();
    }

    @Override
    public Collection<Move> legalMoves(Board board) {
        return kingKnightMoves(board, POSSIBLE_MOVES);
    }

    @Override
    public Piece movePiece(Move move) {
        return new King(move.getXDestination(), move.getYDestination(), move.getPiece().getAlliance(), false);
    }
}
