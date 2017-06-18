package com.example.tristan.arealchessgame.ChessEngine.pieces;

import com.example.tristan.arealchessgame.Alliance;
import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.Tools;
import com.example.tristan.arealchessgame.ChessEngine.move.Move;
import com.example.tristan.arealchessgame.ChessEngine.move.MoveAttack;
import com.example.tristan.arealchessgame.ChessEngine.move.MoveNormal;
import com.example.tristan.arealchessgame.ChessEngine.board.Tile;
import com.example.tristan.arealchessgame.PieceType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Tristan on 05/06/2017.
 */

public class Knight extends Piece{

    private static final int[][] POSSIBLE_MOVES =
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
        final List<Move> legalMoves = new ArrayList<>();
        int xCoorDest, yCoorDest;

        for (final int[] currentPM : POSSIBLE_MOVES){
            xCoorDest = this.xPosition + currentPM[0];
            yCoorDest = this.yPosition + currentPM[1];

            if (Tools.isValid(xCoorDest, yCoorDest)){
                final Tile destinationTile = board.getTile(xCoorDest, yCoorDest);

                if(!destinationTile.tileIsOccupied()){
                    legalMoves.add(new MoveNormal(board, this, xCoorDest, yCoorDest));
                }
                else{
                    final Piece pieceAtDest = destinationTile.getPiece();
                    final Alliance destPieceAlliance =  pieceAtDest.getAlliance();

                    // if the piece at destination tile has a different alliance it is enemy piece, attack!
                    if (this.alliance != destPieceAlliance){
                        legalMoves.add(new MoveAttack(board, this, pieceAtDest, xCoorDest, yCoorDest));
                    }
                    else{

                    }
                }
            }
        }
        return legalMoves;
    }

    @Override
    public Piece movePiece(Move move) {
        return new Knight(move.getxDestination(), move.getyDestination(), move.getPiece().getAlliance(), false);
    }

}
