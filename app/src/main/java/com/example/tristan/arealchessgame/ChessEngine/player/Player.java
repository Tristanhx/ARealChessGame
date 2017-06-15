package com.example.tristan.arealchessgame.ChessEngine.player;

import com.example.tristan.arealchessgame.Alliance;
import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.board.Move;
import com.example.tristan.arealchessgame.ChessEngine.pieces.King;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;

import java.util.Collection;

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
        this.legalMoves = legalMoves;
        this.enemyMoves = enemyMoves;
        this.playerKing = whoIsMyKing();
    }

    private King whoIsMyKing() {
        for (final Piece piece : getPlayerPieces()){
            if (piece.getPieceType().isKing()){
                return (King) piece;
            }
        }
        throw new RuntimeException("You have no King! Not a Valid Board!");
    }

    public abstract Collection<Piece> getPlayerPieces();

    public abstract Alliance getAlliance();

    public abstract Player getOpponent();

    public boolean isLegalMove(final Move move){
        return this.legalMoves.contains(move);
    }

    public AlternateBoard makeMove(final Move move){
        if (!isLegalMove(move)){
            return new AlternateBoard(this.board, move, MoveWas.ILLEGAL);
        }

        final Board newBoard = move.execute();
        return new AlternateBoard(newBoard, move, MoveWas.LEGAL);
    }




}