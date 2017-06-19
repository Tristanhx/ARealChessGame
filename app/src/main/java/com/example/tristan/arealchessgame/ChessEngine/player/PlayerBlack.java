package com.example.tristan.arealchessgame.ChessEngine.player;

import com.example.tristan.arealchessgame.Alliance;
import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.board.Tile;
import com.example.tristan.arealchessgame.ChessEngine.move.Move;
import com.example.tristan.arealchessgame.ChessEngine.move.castle.MoveShortCastle;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Rook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Tristan on 14/06/2017.
 */

public class PlayerBlack extends Player{
    public PlayerBlack(Board board, Collection<Move> whiteMoves, Collection<Move> blackMoves) {
        super(board, blackMoves, whiteMoves);

    }

    @Override
    protected Collection<Move> castlingMoves(Collection<Move> legalMoves, Collection<Move> enemyMoves){
        final List<Move> castlingMoves = new ArrayList<>();

        if (this.playerKing.isFirstMove() && !this.isInCheck()){
            //short
            if (!this.board.getTile(5, 0).tileIsOccupied() && !this.board.getTile(6, 0).tileIsOccupied()){
                final Tile tile = this.board.getTile(7, 0);
                if (tile.tileIsOccupied() && tile.getPiece().isFirstMove()){
                    if (Player.attacksOnTile(5, 0, enemyMoves).isEmpty() &&
                            Player.attacksOnTile(6, 0, enemyMoves).isEmpty() && tile.getPiece() instanceof Rook){
                        castlingMoves.add(new MoveShortCastle(board, this.playerKing, (Rook) tile.getPiece(), 6, 0, 5, 0));
                    }
                }
            }
            //long
            if (!this.board.getTile(1, 0).tileIsOccupied() && !this.board.getTile(2, 0).tileIsOccupied() && !this.board.getTile(3, 0).tileIsOccupied()){
                final Tile tile = this.board.getTile(0, 0);
                if (tile.tileIsOccupied() && tile.getPiece().isFirstMove()){
                    if (Player.attacksOnTile(1, 0, enemyMoves).isEmpty() &&
                            Player.attacksOnTile(2, 0, enemyMoves).isEmpty() &&
                            Player.attacksOnTile(3, 0, enemyMoves).isEmpty() && tile.getPiece() instanceof Rook){
                        castlingMoves.add(new MoveShortCastle(board, this.playerKing, (Rook) tile.getPiece(), 2, 0, 3, 0));
                    }
                }
            }
        }
        return castlingMoves;
    }

    @Override
    public Collection<Piece> getPlayerPieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }

    @Override
    public Player getOpponent() {
        return this.board.getWhitePlayer();
    }
}
