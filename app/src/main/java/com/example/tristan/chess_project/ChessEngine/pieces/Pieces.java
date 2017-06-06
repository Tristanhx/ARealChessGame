package com.example.tristan.chess_project.ChessEngine.pieces;

import com.example.tristan.chess_project.Alliance;
import com.example.tristan.chess_project.ChessEngine.board.Board;
import com.example.tristan.chess_project.ChessEngine.board.Move;

import java.util.List;

/**
 * Created by Tristan on 04/06/2017.
 */

public abstract class Pieces {
//    protected final int xCor, yCor;
    protected final int position;
    protected final Alliance alliance;

    Pieces(final int position, final Alliance alliance){
//        this.xCor = xCor;
//        this.yCor =yCor;
        this.alliance = alliance;
//        this.position = xCor * yCor;
        this.position = position;
    }

    public abstract List<Move> legalMoves(final Board board);



}
