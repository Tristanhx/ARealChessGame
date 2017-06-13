package com.example.tristan.arealchessgame.ChessEngine.board;

import com.example.tristan.arealchessgame.Alliance;
import com.example.tristan.arealchessgame.ChessEngine.PositionConverter;
import com.example.tristan.arealchessgame.ChessEngine.Tools;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Bishop;
import com.example.tristan.arealchessgame.ChessEngine.pieces.King;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Knight;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Pawn;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Queen;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Rook;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tristan on 06/06/2017.
 */

public class Board {



    private final List<Tile> mBoard;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;
    private final Collection<Move> whiteMoves;
    private final Collection<Move> blackMoves;

    private Board(Builder builder){
        this.mBoard = createNewBoard(builder);
        this.whitePieces = trackPieces(this.mBoard, Alliance.WHITE);
        this.blackPieces = trackPieces(this.mBoard, Alliance.BLACK);
        this.whiteMoves = trackMoves(this.whitePieces);
        this.blackMoves = trackMoves(this.blackPieces);
    }

    private Collection<Move> trackMoves(Collection<Piece> pieces) {

        final List<Move> legalMoves = new ArrayList<>();
        for (final Piece piece : pieces){
            legalMoves.addAll(piece.legalMoves(this));
        }
        return legalMoves;
    }

    private Collection<Piece> trackPieces(List<Tile> mBoard, Alliance alliance) {

        List<Piece> piecesList = new ArrayList<>();
        for (final Tile tile : mBoard){
            
            if (tile.tileIsOccupied()){
                final Piece piece = tile.getPiece();
                if (piece.getAlliance() == alliance){
                    piecesList.add(piece);
                }
            }
        }
        return piecesList;
    }

    @Override
    public String toString(){
        final StringBuilder stringBuilder = new StringBuilder();
        for (int yRow = 0 ; yRow < Tools.BOARD_DIM ; yRow++){
            for (int xColumn = 0 ; xColumn < Tools.BOARD_DIM ; xColumn++){
                int conv = Tools.convertPosition(xColumn, yRow);
                final String sign = this.mBoard.get(conv).toString();
                stringBuilder.append(String.format("%3s", sign));
                if ((conv + 1) % Tools.BOARD_DIM == 0){
                    stringBuilder.append("\n");
                }
            }
        }
        return stringBuilder.toString();
    }

    private List<Tile> createNewBoard(final Builder builder) {
        final List<Tile> tileList = new ArrayList<>();
        for(int yRow = 0 ; yRow < Tools.BOARD_DIM ; yRow++){
            for (int xColumn = 0 ; xColumn < Tools.BOARD_DIM ; xColumn++){

                Tile tmp = Tile.createTile(xColumn, yRow, builder.boardLayout.get(Tools.convertPosition
                (xColumn, yRow)));
                tileList.add(tmp);
            }
        }
        return tileList;
    }

    public static Board createDefaultBoard(){
        final Builder builder = new Builder();
        // Black
        builder.setPiece(new Rook(0, 0, Alliance.BLACK));
        builder.setPiece(new Knight(1, 0, Alliance.BLACK));
        builder.setPiece(new Bishop(2, 0, Alliance.BLACK));
        builder.setPiece(new Queen(3, 0, Alliance.BLACK));
        builder.setPiece(new King(4, 0, Alliance.BLACK));
        builder.setPiece(new Bishop(5, 0, Alliance.BLACK));
        builder.setPiece(new Knight(6, 0, Alliance.BLACK));
        builder.setPiece(new Rook(7, 0, Alliance.BLACK));

        // White
        builder.setPiece(new Rook(0, 7, Alliance.WHITE));
        builder.setPiece(new Knight(1, 7, Alliance.WHITE));
        builder.setPiece(new Bishop(2, 7, Alliance.WHITE));
        builder.setPiece(new Queen(3, 7, Alliance.WHITE));
        builder.setPiece(new King(4, 7, Alliance.WHITE));
        builder.setPiece(new Bishop(5, 7, Alliance.WHITE));
        builder.setPiece(new Knight(6, 7, Alliance.WHITE));
        builder.setPiece(new Rook(7, 7, Alliance.WHITE));

        // Pawns for Black and White
        for(int i = 0 ; i < Tools.BOARD_DIM ; i++){
            builder.setPiece(new Pawn(i, 1, Alliance.BLACK));
            builder.setPiece(new Pawn(i, 6, Alliance.WHITE));
        }
        return builder.build();
    }

    public static class Builder{
        Map<Integer, Piece> boardLayout = new HashMap<>();
        Alliance nextPlayer;

        public Builder(){

        }

        public Builder setPiece(final Piece piece){
            this.boardLayout.put(Tools.convertPosition(piece.getXPos(), piece.getYPos()), piece);
            return this;
        }

        public Builder setPlayer(final Alliance nextPlayer){
            this.nextPlayer = nextPlayer;
            return this;
        }

        public Board build(){
            return new Board(this);
        }
    }

    public Tile getTile(int xCoor, int yCoor){
        return mBoard.get(Tools.convertPosition(xCoor, yCoor));
    }
}
