package com.example.tristan.arealchessgame.ChessEngine.player.computerPlayer;

import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.move.Move;
import com.example.tristan.arealchessgame.ChessEngine.player.AlternateBoard;

/**
 * Created by trist on 6/20/2017.
 */

public class MiniMax implements Strategy {
    private final Evaluator evaluator;

    public MiniMax() {
        this.evaluator = null;
    }

    @Override
    public Move execute(Board board, int depth) {
        return null;
    }

    public int min(final Board board, final int depth){
        if (depth == 0){
            return this.evaluator.evaluate(board, depth);
        }
        int lowest = Integer.MAX_VALUE;
        for (Move move : board.getCurrentPlayer().getLegalMoves()){
            final AlternateBoard newBoard = board.getCurrentPlayer().makeMove(move);
            if (newBoard.getMoveWas().isExecuted()){
                final int current = max(newBoard.getBoard(), depth-1);
                if (current <= lowest){
                    lowest = current;
                }
            }
        }
        return lowest;
    }

    public int max(final Board board, final int depth){
        if (depth == 0){
            return this.evaluator.evaluate(board, depth);
        }
        int highest = Integer.MIN_VALUE;
        for (Move move : board.getCurrentPlayer().getLegalMoves()){
            final AlternateBoard newBoard = board.getCurrentPlayer().makeMove(move);
            if (newBoard.getMoveWas().isExecuted()){
                final int current = min(newBoard.getBoard(), depth-1);
                if (current >= highest){
                    highest = current;
                }
            }
        }
        return highest;
    }
}
