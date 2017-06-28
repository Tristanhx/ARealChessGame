package com.example.tristan.arealchessgame.chess_engine.move;

import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.pieces.Piece;

/**
 * Created by Tristan on 06/06/2017.
 */

public abstract class Move {

    public final Board board;
    public final Piece piece;
    private final int xDestination;
    private final int yDestination;
    static final Move NO_MOVE = new NoMove();

    public Move(final Board board, final Piece piece, final int xDestination, final int yDestination){
        this.board = board;
        this.piece = piece;
        this.xDestination = xDestination;
        this.yDestination = yDestination;
    }

    public void placeOwnPieces(Board.Builder builder){
        for (final Piece piece : this.board.getCurrentPlayer().getPlayerPieces()){
            if (!this.piece.equals(piece)){
                builder.setPiece(piece);
            }

        }
    }

    public void placeEnemyPieces(Board.Builder builder){
        for (final Piece piece : this.board.getCurrentPlayer().getOpponent().getPlayerPieces()){
            builder.setPiece(piece);
        }
    }

    public void placeAttackedEnemyPieces(Board.Builder builder, Piece attackedPiece){
        for (final Piece piece : this.board.getCurrentPlayer().getOpponent().getPlayerPieces()){
            if(piece != attackedPiece) {
                builder.setPiece(piece);
            }
        }
    }

    @Override
    public boolean equals(final Object other){
        if (this == other){
            return true;
        }
        if (!(other instanceof Move)){
            return false;
        }
        final Move otherMove = (Move) other;
        return getXDestination() == otherMove.getXDestination() && getYDestination() ==
                otherMove.getYDestination() && getPiece() == otherMove.getPiece();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + this.xDestination;
        result = 31 * result + this.yDestination;
        result = 31 * result + this.piece.hashCode();
        return result;
    }

    public boolean isAttackMove(){
        return false;
    }

    public Piece getAttackedPiece(){
        return null;
    }

    public Piece getPiece(){
        return this.piece;
    }

    public int getXDestination(){
        return this.xDestination;
    }

    public int getYDestination(){
        return this.yDestination;
    }

    public abstract Board execute();

    public int getCurrentXPos() {
        return this.piece.getXPos();
    }

    public int getCurrentYPos() {
        return this.piece.getYPos();
    }
}
