package com.example.tristan.arealchessgame.chess_engine.player;

import android.util.Log;

import com.example.tristan.arealchessgame.chess_engine.Alliance;
import com.example.tristan.arealchessgame.chess_engine.board.AlternateBoard;
import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.move.Move;
import com.example.tristan.arealchessgame.chess_engine.move.MoveWas;
import com.example.tristan.arealchessgame.chess_engine.pieces.King;
import com.example.tristan.arealchessgame.chess_engine.pieces.Piece;
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
    protected final Boolean isInCheck;
    public boolean forfeited;

    Player(final Board board, final Collection<Move> legalMoves, final Collection<Move> enemyMoves){
        this.board = board;
        this.playerKing = whoIsMyKing();
        this.isInCheck = !(attacksOnTile(playerKing.getXPos(), playerKing.getYPos(), enemyMoves).isEmpty());
        this.enemyMoves = enemyMoves;
        this.legalMoves = ImmutableList.copyOf(Iterables.concat(legalMoves, castlingMoves(legalMoves, enemyMoves)));
        this.forfeited = false;
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

    public Boolean isForfeited(){
        return forfeited;
    }

    public King whoIsMyKing() {
        for (final Piece piece : getPlayerPieces()){
            if (piece.getPieceType().isKing()){
                return (King) piece;
            }
        }
        throw new RuntimeException("You have no King! Not a Valid Board!");
    }

    public boolean checkInCheck(){
        Log.d("check", playerKing.getAlliance().toString() + " check " + isInCheck);
        return isInCheck;
    }

    public boolean checkMate(){
        Log.d("checkmate", playerKing.getAlliance().toString() + " checkmate " + (checkInCheck() && !canEscape()));
        return checkInCheck() && !canEscape();
    }

    public boolean staleMate(){
        return !checkInCheck() && !canEscape();
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

    public abstract void setForfeited();
}
