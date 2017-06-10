package com.example.tristan.arealchessgame.ChessEngine.pieces;

import com.example.tristan.arealchessgame.Alliance;
import com.example.tristan.arealchessgame.ChessEngine.PositionConverter;
import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.board.Move;

import java.util.List;

/**
 * Created by Tristan on 04/06/2017.
 */

public abstract class Piece {
    //    protected final int xCor, yCor;
    protected final int xPosition;
    protected final int yPosition;
    protected final int position;
    protected final Alliance alliance;
    PositionConverter posCon = new PositionConverter();

    Piece(final int xPosition, final int yPosition, final Alliance alliance){
        this.alliance = alliance;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.position = posCon.convertPosition(xPosition, yPosition);
    }

    public abstract List<Move> legalMoves(final Board board);



}

