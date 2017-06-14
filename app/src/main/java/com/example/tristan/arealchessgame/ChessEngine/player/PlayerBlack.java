package com.example.tristan.arealchessgame.ChessEngine.player;

import com.example.tristan.arealchessgame.Alliance;
import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.board.Move;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;

import java.util.Collection;

/**
 * Created by Tristan on 14/06/2017.
 */

public class PlayerBlack extends Player{
    public PlayerBlack(Board board, Collection<Move> whiteMoves, Collection<Move> blackMoves) {
        super(board, blackMoves, whiteMoves);

    }

    @Override
    public Collection<Piece> getPlayerPieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.getWhitePlayer();
    }
}
