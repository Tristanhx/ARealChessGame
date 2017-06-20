package com.example.tristan.arealchessgame.ChessEngine.player;

import android.util.Log;

import com.example.tristan.arealchessgame.Alliance;
import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.move.Move;
import com.example.tristan.arealchessgame.ChessEngine.pieces.King;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Tristan on 14/06/2017.
 */

public abstract class Player {

    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;
    protected final Collection<Move> enemyMoves;
    protected final boolean isInCheck;

    Player(final Board board, final Collection<Move> legalMoves, final Collection<Move> enemyMoves){
        this.board = board;
        this.playerKing = whoIsMyKing();
        this.legalMoves = ImmutableList.copyOf(Iterables.concat(legalMoves, castlingMoves(legalMoves, enemyMoves)));
        this.enemyMoves = enemyMoves;
        this.isInCheck = !attacksOnTile(playerKing.getXPos(), playerKing.getYPos(), enemyMoves).isEmpty();
    }

    protected static Collection<Move> attacksOnTile(int xPos, int yPos, Collection<Move> moves){
        final List<Move> attacks = new ArrayList<>();
        for (final Move move : moves){
            if (xPos == move.getXDestination() && yPos == move.getYDestination()){
                attacks.add(move);
            }
        }
        return attacks;
    }

    public King whoIsMyKing() {
        for (final Piece piece : getPlayerPieces()){
            if (piece.getPieceType().isKing()){
                return (King) piece;
            }
        }
        throw new RuntimeException("You have no King! Not a Valid Board!");
    }

    public boolean isInCheck(){
        Log.d("King", playerKing.getAlliance().toString() + " is in check " + String.valueOf(isInCheck));
        return isInCheck;
    }

    public boolean checkMate(){
        return this.isInCheck && !canEscape();
    }

    public boolean staleMate(){
        return !this.isInCheck && !canEscape();
    }

    protected boolean canEscape(){
        for(final Move move : this.legalMoves){
            final AlternateBoard board = makeMove(move);
            if (board.getMoveWas().isExecuted()){
                return true;
            }
        }
        return false;
    }



    protected abstract Collection<Move> castlingMoves(Collection<Move> legalMoves,
                                                      Collection<Move> enemyMoves);



    public abstract Collection<Piece> getPlayerPieces();

    public Collection<Move> getLegalMoves(){
        return this.legalMoves;
    }

    public abstract Alliance getAlliance();

    public abstract Player getOpponent();

    private boolean isLegalMove(final Move move){
        return this.legalMoves.contains(move);
    }

    public AlternateBoard makeMove(final Move move){
        if (!isLegalMove(move)){
            Log.d("LegalMoves", "ILLEGAL");
            return new AlternateBoard(this.board, move, MoveWas.ILLEGAL);
        }

        //check if the king is in check in the next board and discard any moves that leave the king in check
        final Board newBoard = move.execute();
        final King nextBoardPlayerKing = newBoard.getCurrentPlayer().getOpponent().getKing();

        final Collection<Move> nextAttacksOnKing = Player.attacksOnTile(nextBoardPlayerKing.getXPos(),
                nextBoardPlayerKing.getYPos(), newBoard.getCurrentPlayer().getLegalMoves());
        if (!nextAttacksOnKing.isEmpty()){
            Log.d("LegalMoves", "LEAVES KING IN CHECK");
            return new AlternateBoard(this.board, move, MoveWas.THIS_LEAVES_KING_IN_CHECK);
        }
        else {
            return new AlternateBoard(newBoard, move, MoveWas.LEGAL);
        }
    }


    public King getKing() {
        return this.playerKing;
    }
}
