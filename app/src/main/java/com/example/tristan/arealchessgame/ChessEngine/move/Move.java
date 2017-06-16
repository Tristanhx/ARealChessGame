package com.example.tristan.arealchessgame.ChessEngine.move;

import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.board.Tile;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;

/**
 * Created by Tristan on 06/06/2017.
 */

public abstract class Move {

    public final Board board;
    public final Piece piece;
    final int xDestination;
    final int yDestination;
    public static final Move NO_MOVE = new NoMove();

    public Move(final Board board, final Piece piece, final int xDestination, final int yDestination){
        this.board = board;
        this.piece = piece;
        this.xDestination = xDestination;
        this.yDestination = yDestination;
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
        return getxDestination() == otherMove.getxDestination() && getyDestination() ==
                otherMove.getyDestination() && getPiece() == otherMove.getPiece();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + this.xDestination;
        result = 31 * result + this.yDestination;
        result = 31 * result + this.piece.hashCode();
        return result;
    }

    public abstract boolean isAttackMove();

    public Piece getAttackedPiece(){
        return null;
    }

    public Piece getPiece(){
        return this.piece;
    }

    public int getxDestination(){
        return this.xDestination;
    }

    public int getyDestination(){
        return this.yDestination;
    }

    public Board execute() {
        final Board.Builder builder = new Board.Builder();

        // Set Current Player pieces on new Board
        for (final Piece piece : this.board.getCurrentPlayer().getPlayerPieces()){
            if (!this.piece.equals(piece)){
                builder.setPiece(piece);
            }

        }
        // Move Piece
        builder.setPiece(this.piece.movePiece(this));

        // Set Enemy Player pieces on new Board
        for (final Piece piece : this.board.getCurrentPlayer().getOpponent().getPlayerPieces()){
                builder.setPiece(piece);
        }

        builder.setPlayer(this.board.getCurrentPlayer().getOpponent().getAlliance());
//        builder.setChosenMove(this);
        return builder.build();
    }

    public int getCurrentXPos() {
        return this.piece.getXPos();
    }

    public int getCurrentYPos() {
        return this.piece.getYPos();
    }
}
