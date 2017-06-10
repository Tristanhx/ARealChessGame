package com.example.tristan.arealchessgame.ChessEngine.board;

import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;

/**
 * Created by Tristan on 10/06/2017.
 */

public class MoveAttack extends Move {

    MoveAttack(Board board, Piece piece, int destination) {
        super(board, piece, destination);
    }
}
