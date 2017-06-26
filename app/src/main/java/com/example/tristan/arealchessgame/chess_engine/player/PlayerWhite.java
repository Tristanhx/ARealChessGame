package com.example.tristan.arealchessgame.chess_engine.player;

import com.example.tristan.arealchessgame.GameController;
import com.example.tristan.arealchessgame.chess_engine.Alliance;
import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.board.Tile;
import com.example.tristan.arealchessgame.chess_engine.move.Move;
import com.example.tristan.arealchessgame.chess_engine.move.castle.MoveShortCastle;
import com.example.tristan.arealchessgame.chess_engine.pieces.Piece;
import com.example.tristan.arealchessgame.chess_engine.pieces.Rook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Tristan on 14/06/2017.
 */

public class PlayerWhite extends Player{

    public PlayerWhite(Board board, Collection<Move> whiteMoves, Collection<Move> blackMoves) {
        super(board, whiteMoves, blackMoves);
    }

    @Override
    protected Collection<Move> castlingMoves(Collection<Move> legalMoves, Collection<Move> enemyMoves){
        final List<Move> castlingMoves = new ArrayList<>();

        if (this.playerKing.isFirstMove() && !this.checkInCheck()){
            //short
            if (!this.board.getTile(5, 7).tileIsOccupied() && !this.board.getTile(6, 7).tileIsOccupied()){
                final Tile tile = this.board.getTile(7, 7);

                if (tile.tileIsOccupied() && tile.getPiece().isFirstMove()){
                    if (Player.attacksOnTile(5, 7, enemyMoves).isEmpty() &&
                            Player.attacksOnTile(6, 7, enemyMoves).isEmpty() && tile.getPiece() instanceof Rook){
                        castlingMoves.add(new MoveShortCastle(board, this.playerKing, (Rook) tile.getPiece(), 6, 7, 5, 7));
                    }
                }
            }
            //long
            if (!this.board.getTile(1, 7).tileIsOccupied() && !this.board.getTile(2, 7).tileIsOccupied() && !this.board.getTile(3, 7).tileIsOccupied()){
                final Tile tile = this.board.getTile(0, 7);
                if (tile.tileIsOccupied() && tile.getPiece().isFirstMove()){
                    if (Player.attacksOnTile(1, 7, enemyMoves).isEmpty() &&
                            Player.attacksOnTile(2, 7, enemyMoves).isEmpty() &&
                            Player.attacksOnTile(3, 7, enemyMoves).isEmpty() && tile.getPiece() instanceof Rook){
                        castlingMoves.add(new MoveShortCastle(board, this.playerKing, (Rook) tile.getPiece(), 2, 7, 3, 7));
                    }
                }
            }
        }
        return castlingMoves;
    }

    @Override
    public Collection<Piece> getPlayerPieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.WHITE;
    }

    @Override
    public Player getOpponent() {
        return this.board.getBlackPlayer();
    }

    @Override
    public void setForfeited() {
        this.forfeited = true;
        GameController.boardGridView.invalidate();
    }
}
