package com.example.tristan.arealchessgame.chess_engine.move;

import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.pieces.Piece;

/**
 * Created by Tristan on 10/06/2017.
 */

public class MoveAttack extends Move {

    public Piece attackedPiece;

    public MoveAttack(Board board, Piece piece, Piece attackedPiece, int xDestination, int yDestination) {
        super(board, piece, xDestination, yDestination);
        this.attackedPiece = attackedPiece;
    }

    //we want a unique hashcode for this attack
    @Override
    public int hashCode(){
        return attackedPiece.hashCode() + super.hashCode();
    }

    @Override
    public boolean equals(Object other){
        if (this == other){
            return true;
        }
        if (!(other instanceof MoveAttack)){
            return false;
        }
        final MoveAttack attackMove = (MoveAttack) other;
        return super.equals(attackMove) && attackedPiece.equals(attackMove.getAttackedPiece());
    }

    @Override
    public Board execute() {
        final Board.Builder builder = new Board.Builder();

        // Set Current Player pieces on new Board
        placeOwnPieces(builder);
        // Set Enemy Player pieces on new Board
        placeAttackedEnemyPieces(builder, attackedPiece);
        // Move Piece
        builder.setPiece(this.piece.movePiece(this));



        builder.setPlayer(this.board.getCurrentPlayer().getOpponent().getAlliance());
        builder.setLastMove(this);
        return builder.build();
    }

    @Override
    public Piece getAttackedPiece() {
        return attackedPiece;
    }
}
