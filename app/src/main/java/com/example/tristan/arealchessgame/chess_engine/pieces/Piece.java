package com.example.tristan.arealchessgame.chess_engine.pieces;

import com.example.tristan.arealchessgame.chess_engine.Alliance;
import com.example.tristan.arealchessgame.chess_engine.Tools;
import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.board.Tile;
import com.example.tristan.arealchessgame.chess_engine.move.Move;
import com.example.tristan.arealchessgame.chess_engine.PieceType;
import com.example.tristan.arealchessgame.chess_engine.move.MoveAttack;
import com.example.tristan.arealchessgame.chess_engine.move.MoveNormal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Tristan on 04/06/2017.
 */

public abstract class Piece {
    //    protected final int xCor, yCor;
    protected final int xPosition;
    protected final int yPosition;
    protected final int position;
    protected final Alliance alliance;
    protected final PieceType pieceType;
    final boolean isFirstMove;

    Piece(final PieceType pieceType, final int xPosition, final int yPosition, final Alliance alliance, final boolean isFirstMove){
        this.alliance = alliance;
        this.pieceType = pieceType;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.position = Tools.convertPosition(xPosition, yPosition);
        this.isFirstMove = isFirstMove;
    }

    public List<Move> bishopRookQueenMoves(final Board board, final int[][][] POSSIBLE_MOVES){
        final List<Move> legalMoves = new ArrayList<>();
        int xCoorDest, yCoorDest;

        for (final int[][] currentDir : POSSIBLE_MOVES) {
            for (final int[] currentPM : currentDir) {

                xCoorDest = this.xPosition + currentPM[0];
                yCoorDest = this.yPosition + currentPM[1];

                if (Tools.isValid(xCoorDest, yCoorDest)) {
                    final Tile destinationTile = board.getTile(xCoorDest, yCoorDest);

                    if (!destinationTile.tileIsOccupied()) {
                        legalMoves.add(new MoveNormal(board, this, xCoorDest, yCoorDest));
                    } else {
                        final Piece pieceAtDest = destinationTile.getPiece();
                        final Alliance destPieceAlliance = pieceAtDest.getAlliance();

                        // if the piece at destination tile has a different alliance it is enemy piece, attack!
                        if (this.alliance != destPieceAlliance) {
                            legalMoves.add(new MoveAttack(board, this, pieceAtDest, xCoorDest, yCoorDest));
                            break;
                        } else {
                            break; // No use in looking further in this direction
                        }
                    }
                }
            }
        }
        return legalMoves;
    }

    public abstract Collection<Move> legalMoves(final Board board);

    public boolean isFirstMove(){
        return isFirstMove;
    }


    public Alliance getAlliance() {
        return this.alliance;
    }

    public int getXPos() {
        return xPosition;
    }

    public int getYPos() {
        return yPosition;
    }

    public PieceType getPieceType() {
        return pieceType;
    }



    public abstract Piece movePiece(Move move);

    @Override
    public boolean equals(final Object other){
        if (this == other){
            return true;
        }
        if (!(other instanceof Piece)){
            return false;
        }
        final Piece otherPiece = (Piece) other;
        return xPosition == otherPiece.getXPos() && yPosition == otherPiece.getYPos() &&
                pieceType == otherPiece.getPieceType() && alliance == otherPiece.getAlliance();
    }

    @Override
    public int hashCode(){
        int result = pieceType.hashCode();
        result = 31 * result + alliance.hashCode();
        result = 31 * result + xPosition;
        result = 31 * result + yPosition;
        return result;
    }
}

