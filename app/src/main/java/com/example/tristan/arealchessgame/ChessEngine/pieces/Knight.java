package com.example.tristan.arealchessgame.ChessEngine.pieces;

import com.example.tristan.arealchessgame.Alliance;
import com.example.tristan.arealchessgame.ChessEngine.CorPair;
import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.board.Move;

import java.util.List;

/**
 * Created by Tristan on 05/06/2017.
 */

public class Knight extends Piece{

//    private static final CorPair<Integer> firstMove = new CorPair<>(-1, -2);
//    private static final CorPair<Integer> secondMove = new CorPair<>(1, -2);
//    private static final CorPair<Integer> thirdMove = new CorPair<>(-2, -1);
//    private static final CorPair<Integer> fourthMove = new CorPair<>(2, -1);
//    private static final CorPair<Integer> fifthMove = new CorPair<>(-1, 2);
//    private static final CorPair<Integer> sixthMove = new CorPair<>(1, 2);
//    private static final CorPair<Integer> seventhMove = new CorPair<>(-1, -2);
//    private static final CorPair<Integer> eightMove = new CorPair<>(-1, -2);
//    private final static CorPair<Integer>[] POSSIBLE_MOVES = (firstMove, secondMove, thirdMove,
//    fourthMove, fifthMove, sixthMove, seventhMove, eightMove);

    private static final int[][] POSSIBLE_MOVES = {{-1, -2}, {1, -2}, {2, -1}, {2, 1}, {1, 2}, {-1, 2}, {-2, 1}, {-2, -1}};


    public Knight(final int xPosition, int yPosition, final Alliance alliance) {
        super(xPosition, yPosition, alliance);
    }

    @Override
    public List<Move> legalMoves(Board board) {
        return null;
    }
}
