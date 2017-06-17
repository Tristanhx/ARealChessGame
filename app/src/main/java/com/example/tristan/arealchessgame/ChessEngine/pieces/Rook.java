package com.example.tristan.arealchessgame.ChessEngine.pieces;

import com.example.tristan.arealchessgame.Alliance;
import com.example.tristan.arealchessgame.ChessEngine.Tools;
import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.move.Move;
import com.example.tristan.arealchessgame.ChessEngine.move.MoveAttack;
import com.example.tristan.arealchessgame.ChessEngine.move.MoveNormal;
import com.example.tristan.arealchessgame.ChessEngine.board.Tile;
import com.example.tristan.arealchessgame.PieceType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Tristan on 11/06/2017.
 */

public class Rook extends Piece {

    private static final int[][][] POSSIBLE_MOVES =
            /* Up */
            {{{0, -1}, {0, -2}, {0, -3}, {0, -4}, {0, -5}, {0, -6}, {0, -7}},
                    /* Down */
            {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7}},
                    /* Left */
            {{-1, 0}, {-2, 0}, {-3, 0}, {-4, 0}, {-5, 0}, {-6, 0}, {-7, 0}},
                    /* Right */
            {{1, 0}, {2, 0}, {3, 0}, {4, 0}, {5, 0}, {6, 0}, {7, 0}}};

    public Rook(int xPosition, int yPosition, Alliance alliance) {
        super(PieceType.ROOK, xPosition, yPosition, alliance);
    }

    @Override
    public String toString(){
        return PieceType.ROOK.toString();
    }

    @Override
    public Collection<Move> legalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();
        int xCoorDest, yCoorDest;

        for (final int[][] currentDir : POSSIBLE_MOVES) {
            for (final int[] currentPM : currentDir) {

                xCoorDest = this.xPosition + currentPM[0];
                yCoorDest = this.yPosition + currentPM[1];

                if (Tools.isValid(xCoorDest, yCoorDest)) {
                    final Tile destinationTile = board.getTile(xCoorDest, yCoorDest);

                    if (!destinationTile.tileIsOccupied()) {
                        legalMoves.add(new MoveNormal(board, this, xCoorDest, yCoorDest));
                    } else {
                        final Piece pieceAtDest = destinationTile.getPiece();
                        final Alliance destPieceAlliance = pieceAtDest.getAlliance();

                        // if the piece at destination tile has a different alliance it is enemy piece, attack!
                        if (this.alliance != destPieceAlliance) {
                            legalMoves.add(new MoveAttack(board, this, pieceAtDest, xCoorDest, yCoorDest));
                            break;
                        } else {
                            break; // No use in looking further in this direction
                        }
                    }
                }
            }
        }
        return legalMoves;
    }

    @Override
    public Piece movePiece(Move move) {
        return new Rook(move.getxDestination(), move.getyDestination(), move.getPiece().getAlliance());
    }
}
