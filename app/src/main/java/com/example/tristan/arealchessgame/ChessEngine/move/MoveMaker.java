package com.example.tristan.arealchessgame.ChessEngine.move;

import com.example.tristan.arealchessgame.ChessEngine.board.Board;

/**
 * Created by Tristan on 15/06/2017.
 */

public class MoveMaker {

    private MoveMaker(){
        throw new RuntimeException("not instantiable");
    }

    public static Move createMove(final Board board, final int xCoordinate, final int yCoordinate,
                                  final int xDestinationCoor, final int yDestinationCoor){
        for (final Move move : board.getAllLegalMoves()){
            if (move.getCurrentXPos() == xCoordinate && move.getCurrentYPos() == yCoordinate &&
            move.getXDestination() == xDestinationCoor && move.getYDestination() == yDestinationCoor){
                return move;
            }
        }
        return Move.NO_MOVE;
    }

    public static Move getNoMove() {
        return Move.NO_MOVE;
    }
}
