package com.example.tristan.arealchessgame.ChessEngine.move;

import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;

/**
 * Created by Tristan on 10/06/2017.
 */

public class MoveAttack extends Move {

    Piece attackedPiece;

    public MoveAttack(Board board, Piece piece, Piece attackedPiece, int xDestination, int yDestination) {
        super(board, piece, xDestination, yDestination);
        this.attackedPiece = attackedPiece;
    }

    //we want a unique hashcode for this attack
    @Override
    public int hashCode(){
        if (attackedPiece == null){
            return super.hashCode();
        }
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
        return super.equals(attackMove) && getAttackedPiece().equals(attackMove.getAttackedPiece());
    }

    @Override
    public boolean isAttackMove() {
        return true;
    }

    @Override
    public Board execute() {
        return null;
    }
}
