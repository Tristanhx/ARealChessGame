package com.example.tristan.arealchessgame.ChessEngine.pieces;

import com.example.tristan.arealchessgame.Alliance;
import com.example.tristan.arealchessgame.ChessEngine.Tools;
import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.move.Move;
import com.example.tristan.arealchessgame.ChessEngine.move.MoveNormal;
import com.example.tristan.arealchessgame.ChessEngine.board.Tile;
import com.example.tristan.arealchessgame.ChessEngine.move.pawn.MoveEnPassant;
import com.example.tristan.arealchessgame.ChessEngine.move.pawn.MovePawn;
import com.example.tristan.arealchessgame.ChessEngine.move.pawn.MovePawnAttack;
import com.example.tristan.arealchessgame.ChessEngine.move.pawn.MovePawnLeap;
import com.example.tristan.arealchessgame.PieceType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Tristan on 11/06/2017.
 */

public class Pawn extends Piece {

    private final static int[][] POSSIBLE_MOVES = {{0, 1}, {0, 2}, {-1, 1}, {1, 1}};

    public Pawn(final int xPosition, final int yPosition, final Alliance alliance, final boolean isFirstMove) {
        super(PieceType.PAWN, xPosition, yPosition, alliance, isFirstMove);
    }

    @Override
    public String toString(){
        return PieceType.PAWN.toString();
    }

    @Override
    public Collection<Move> legalMoves(Board board) {

            final List<Move> legalMoves = new ArrayList<>();
            int xCoorDest, yCoorDest;

            for (final int[] currentPM : POSSIBLE_MOVES) {
                xCoorDest = this.xPosition + currentPM[0];
                yCoorDest = this.yPosition + (alliance.getDir() * currentPM[1]);

                if (!Tools.isValid(xCoorDest, yCoorDest)) {
                    continue;
                }
                final Tile destinationTile = board.getTile(xCoorDest, yCoorDest);
                //normal move
                if (currentPM == POSSIBLE_MOVES[0] && !destinationTile.tileIsOccupied()) {
                    legalMoves.add(new MovePawn(board, this, xCoorDest, yCoorDest));
                }
                //pawn leap
                else if (currentPM == POSSIBLE_MOVES[1] && this.isFirstMove){
                    //both the next and the destinationtile must be unoccupied
                    final int inBetweenTile = this.yPosition + (this.alliance.getDir());
                    if (!board.getTile(this.xPosition, inBetweenTile).tileIsOccupied() && !board.getTile(xCoorDest, yCoorDest).tileIsOccupied()){
                        legalMoves.add(new MovePawnLeap(board, this, xCoorDest, yCoorDest));
                    }
                }
                //attack move
                else if (currentPM == POSSIBLE_MOVES[2]){
                    if (board.getTile(xCoorDest, yCoorDest).tileIsOccupied()){
                        final Piece attackedPiece = board.getTile(xCoorDest, yCoorDest).getPiece();
                        if (this.alliance != attackedPiece.getAlliance()){
                            legalMoves.add(new MovePawnAttack(board, this, attackedPiece, xCoorDest, yCoorDest));
                        }
                        else if (board.getEnPassantPawn() != null) {
                            if (board.getEnPassantPawn().getXPos() == this.xPosition -1 && board.getEnPassantPawn().getYPos() == this.yPosition) {
                                final Piece enPassantPiece = board.getEnPassantPawn();
                                if(this.alliance != enPassantPiece.getAlliance()) {
                                    legalMoves.add(new MoveEnPassant(board, this, enPassantPiece, xCoorDest, yCoorDest));
                                }
                            }
                        }

                    }
                }
                else if (currentPM ==POSSIBLE_MOVES[3]){
                    if (board.getTile(xCoorDest, yCoorDest).tileIsOccupied()){
                        final Piece attackedPiece = board.getTile(xCoorDest, yCoorDest).getPiece();
                        if (this.alliance != attackedPiece.getAlliance()){
                            legalMoves.add(new MovePawnAttack(board, this, attackedPiece, xCoorDest, yCoorDest));
                        }
                        else if (board.getEnPassantPawn() != null) {
                            if (board.getEnPassantPawn().getXPos() == this.xPosition +1 && board.getEnPassantPawn().getYPos() == this.yPosition) {
                                final Piece enPassantPiece = board.getEnPassantPawn();
                                if(this.alliance != enPassantPiece.getAlliance()) {
                                    legalMoves.add(new MoveEnPassant(board, this, enPassantPiece, xCoorDest, yCoorDest));
                                }
                            }
                        }
                    }
                }
            }
        return legalMoves;
    }

    @Override
    public Piece movePiece(Move move) {
        return new Pawn(move.getxDestination(), move.getyDestination(), move.getPiece().getAlliance(), false);
    }

    public boolean isFirstMove(){
        return isFirstMove;
    }
}
