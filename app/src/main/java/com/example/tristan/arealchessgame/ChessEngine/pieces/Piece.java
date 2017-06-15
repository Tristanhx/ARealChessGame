package com.example.tristan.arealchessgame.ChessEngine.pieces;

import com.example.tristan.arealchessgame.Alliance;
import com.example.tristan.arealchessgame.ChessEngine.PositionConverter;
import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.move.Move;
import com.example.tristan.arealchessgame.PieceType;

import java.util.Collection;

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
    PositionConverter posCon = new PositionConverter();

    Piece(final PieceType pieceType, final int xPosition, final int yPosition, final Alliance alliance){
        this.alliance = alliance;
        this.pieceType = pieceType;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.position = posCon.convertPosition(xPosition, yPosition);
    }

    public abstract Collection<Move> legalMoves(final Board board);


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

