package com.example.tristan.arealchessgame.ChessEngine.move.castle;

import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.pieces.King;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Rook;

/**
 * Created by Tristan on 18/06/2017.
 */

public class MoveLongCastle extends MoveCastle {


    public MoveLongCastle(Board board, King king, Rook rook, int xDestinationKing, int yDestinationKing, int xDestinationRook, int yDestinationRook) {
        super(board, king, rook, xDestinationKing, yDestinationKing, xDestinationRook, yDestinationRook);
    }
}
