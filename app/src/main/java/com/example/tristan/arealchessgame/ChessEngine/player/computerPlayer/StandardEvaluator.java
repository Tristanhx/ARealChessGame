package com.example.tristan.arealchessgame.ChessEngine.player.computerPlayer;

import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;
import com.example.tristan.arealchessgame.ChessEngine.player.Player;

/**
 * Created by trist on 6/20/2017.
 */

class StandardEvaluator implements Evaluator {

    @Override
    public int evaluate(Board board, int depth) {
        return score(board, board.getWhitePlayer(), depth)-score(board, board.getBlackPlayer(), depth);
    }

    private int score(Board board, Player player, int depth) {
        return pieceValue(player);
    }

    private int pieceValue(Player player) {
        int pieceValue = 0;
        for (final Piece piece : player.getPlayerPieces()){
            pieceValue += piece.getPieceType().getValue();
        }
        return pieceValue;
    }
}
