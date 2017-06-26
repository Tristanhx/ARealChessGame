package com.example.tristan.arealchessgame.chess_engine.player.computer_player;

import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.move.Move;
import com.example.tristan.arealchessgame.chess_engine.move.castle.MoveCastle;
import com.example.tristan.arealchessgame.chess_engine.pieces.Piece;
import com.example.tristan.arealchessgame.chess_engine.pieces.Rook;
import com.example.tristan.arealchessgame.chess_engine.player.Player;

/**
 * Created by trist on 6/20/2017.
 */

class StandardEvaluator implements Evaluator {

    private int CHECK = 6;
    private int CASTLE = 5;

    @Override
    public int evaluate(Board board, int depth) {
        return score(board, board.getWhitePlayer(), depth)-score(board, board.getBlackPlayer(), depth);
    }

    private int score(Board board, Player player, int depth) {
        return pieceValue(player) + isCheck(player) + possibleMoves(player) + castlePossibility(player);
    }

    private int pieceValue(Player player) {
        int pieceValue = 0;
        for (final Piece piece : player.getPlayerPieces()){
            pieceValue += piece.getPieceType().getValue();
        }
        return pieceValue;
    }

    private int isCheck(Player player){
        if (player.getOpponent().checkInCheck()){
            return CHECK;
        }
        return 0;
    }

    private int possibleMoves(Player player){
        return (player.getLegalMoves().size()/5);
    }

    private int castlePossibility(Player player){

        int count = 0;
        if (!player.getKing().isFirstMove()){
            return 0;
        }
        for (Piece piece : player.getPlayerPieces()){
            if (piece instanceof Rook){
                if (piece.isFirstMove()){
                    count++;
                }
            }
        }
        return (CASTLE * count);
    }

//    private int isCastled(Player player){
//        if (player.isCastled()){
//            return CASTLE;
//        }
//        return 0;
//    }
}
