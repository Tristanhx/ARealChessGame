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

    Player(final Board board, final Collection<Move> legalMoves, final Collection<Move> enemyMoves){
        this.board = board;
        this.playerKing = whoIsMyKing();
        this.legalMoves = ImmutableList.copyOf(Iterables.concat(legalMoves, castlingMoves(legalMoves, enemyMoves)));
        this.enemyMoves = enemyMoves;
    }

    public King whoIsMyKing() {
        for (final Piece piece : getPlayerPieces()){
            if (piece.getPieceType().isKing()){
                return (King) piece;
            }
        }
        throw new RuntimeException("You have no King! Not a Valid Board!");
    }

    public boolean isInCheck(Iterable<Move> moves){
        for (final Move move : moves){
            if (move.getAttackedPiece() == playerKing){
                return true;
            }
        }
        return false;
    }

    protected static Collection<Move> attacksOnTile(int xPos, int yPos, Collection<Move> moves){
        final List<Move> attacks = new ArrayList<>();
        for (final Move move : moves){
            if (xPos == move.getXDestination() && yPos == move.getCurrentYPos()){
                attacks.add(move);
            }
        }
        return attacks;
    }

    protected abstract Collection<Move> castlingMoves(Collection<Move> legalMoves,
                                                      Collection<Move> enemyMoves);



    public abstract Collection<Piece> getPlayerPieces();

    public Collection<Move> getLegalMoves(){
        return this.legalMoves;
    }

    public abstract Alliance getAlliance();

    public abstract Player getOpponent();

    //should look into legalMoves, but the move isn't in there for some reason
    private boolean isLegalMove(final Move move){
        return this.legalMoves.contains(move);
    }

    public AlternateBoard makeMove(final Move move){
        if (!isLegalMove(move)){
            Log.d("LegalMoves", "ILLEGAL");
            return new AlternateBoard(this.board, move, MoveWas.ILLEGAL);
        }

        final Board newBoard = move.execute();
        return new AlternateBoard(newBoard, move, MoveWas.LEGAL);
    }




}
