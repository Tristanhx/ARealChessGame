package com.example.tristan.arealchessgame.chess_engine.pieces;

import com.example.tristan.arealchessgame.chess_engine.Alliance;
import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.Tools;
import com.example.tristan.arealchessgame.chess_engine.move.Move;
import com.example.tristan.arealchessgame.chess_engine.move.MoveAttack;
import com.example.tristan.arealchessgame.chess_engine.move.MoveNormal;
import com.example.tristan.arealchessgame.chess_engine.board.Tile;
import com.example.tristan.arealchessgame.chess_engine.PieceType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Tristan on 05/06/2017.
 */

public class Knight extends Piece{

    private final int[][] POSSIBLE_MOVES =
            {{-1, -2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}};


    public Knight(final int xPosition, int yPosition, final Alliance alliance, final boolean isFirstMove) {
        super(PieceType.KNIGHT, xPosition, yPosition, alliance, isFirstMove);
    }

    @Override
    public String toString(){
        return PieceType.KNIGHT.toString();
    }

    @Override
    public Collection<Move> legalMoves(Board board) {

        return kingKnightMoves(board, POSSIBLE_MOVES);
    }

    @Override
    public Piece movePiece(Move move) {
        return new Knight(move.getXDestination(), move.getYDestination(), move.getPiece().getAlliance(), false);
    }

}
