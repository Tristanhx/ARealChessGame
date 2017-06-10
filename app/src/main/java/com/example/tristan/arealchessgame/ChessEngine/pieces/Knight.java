package com.example.tristan.arealchessgame.ChessEngine.pieces;

import com.example.tristan.arealchessgame.Alliance;
import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.Tools;
import com.example.tristan.arealchessgame.ChessEngine.board.Move;
import com.example.tristan.arealchessgame.ChessEngine.board.Tile;

import java.util.ArrayList;
import java.util.Collection;
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
    public Collection<Move> legalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        int xCoorDest = 0, yCoorDest = 0;

        for (final int[] currentPM : POSSIBLE_MOVES){
            xCoorDest = this.xPosition + currentPM[0];
            yCoorDest = this.yPosition + currentPM[1];
        }

        if (Tools.isValid(xCoorDest, yCoorDest)){
            final Tile destinationTile = board.getTile(xCoorDest, yCoorDest);

            if(!destinationTile.tileIsOccupied()){
                legalMoves.add(new Move());
            }
            else{
                final Piece pieceAtDest = destinationTile.getPiece();
                final Alliance destPieceAlliance =  pieceAtDest.getAlliance();

                // if the piece at destination tile has a different alliance it is enemy piece, attack!
                if (this.alliance != destPieceAlliance){
                    legalMoves.add(new /*Attack */Move());
                }
                else{

                }
            }
        }
        return legalMoves;
    }

}
