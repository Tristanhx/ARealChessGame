package com.example.tristan.arealchessgame.ChessEngine.pieces;

import com.example.tristan.arealchessgame.Alliance;
import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.board.Move;

import java.util.List;

/**
 * Created by Tristan on 04/06/2017.
 */

public abstract class Piece {
    //    protected final int xCor, yCor;
    protected final int position;
    protected final Alliance alliance;

    Piece(final int position, final Alliance alliance){
//        this.xCor = xCor;
//        this.yCor =yCor;
        this.alliance = alliance;
//        this.position = xCor * yCor;
        this.position = position;
    }

    public abstract List<Move> legalMoves(final Board board);



}

