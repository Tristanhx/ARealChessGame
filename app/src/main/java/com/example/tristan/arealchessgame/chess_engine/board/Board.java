package com.example.tristan.arealchessgame.chess_engine.board;

import android.util.Log;

import com.example.tristan.arealchessgame.ScoreObject;
import com.example.tristan.arealchessgame.chess_engine.Alliance;
import com.example.tristan.arealchessgame.chess_engine.move.Move;
import com.example.tristan.arealchessgame.chess_engine.move.MoveNormal;
import com.example.tristan.arealchessgame.chess_engine.player.Player;
import com.example.tristan.arealchessgame.chess_engine.player.PlayerBlack;
import com.example.tristan.arealchessgame.chess_engine.player.PlayerWhite;
import com.example.tristan.arealchessgame.chess_engine.Tools;
import com.example.tristan.arealchessgame.chess_engine.pieces.Bishop;
import com.example.tristan.arealchessgame.chess_engine.pieces.King;
import com.example.tristan.arealchessgame.chess_engine.pieces.Knight;
import com.example.tristan.arealchessgame.chess_engine.pieces.Pawn;
import com.example.tristan.arealchessgame.chess_engine.pieces.Piece;
import com.example.tristan.arealchessgame.chess_engine.pieces.Queen;
import com.example.tristan.arealchessgame.chess_engine.pieces.Rook;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tristan on 06/06/2017.
 */

public class Board {

    public static Board instance;
    private final List<Tile> mBoard;
    private final Collection<Piece> whitePieces;
    private final Collection<Piece> blackPieces;
    private final Collection<Move> whiteMoves;
    private final Collection<Move> blackMoves;

    private final PlayerWhite whitePlayer;
    private final PlayerBlack blackPlayer;
    private final Player currentPlayer;
    private final Pawn enPassantPawn;

    private final Move lastMove;

    public static synchronized Board getInstance(){
        if (instance == null){
            instance = createDefaultBoard();
        }

        return instance;
    }

    private Board(Builder builder){
        this.mBoard = createNewBoard(builder);
        //get all pieces of an alliance
        this.whitePieces = trackPieces(this.mBoard, Alliance.WHITE);
        this.blackPieces = trackPieces(this.mBoard, Alliance.BLACK);
        //get the moves of those pieces
        this.whiteMoves = trackMoves(this.whitePieces);
        this.blackMoves = trackMoves(this.blackPieces);
        //give those moves to the players
        this.whitePlayer = new PlayerWhite(this, whiteMoves, blackMoves);
        this.blackPlayer = new PlayerBlack(this, whiteMoves, blackMoves);
        this.currentPlayer = builder.nextPlayer.chooseNextPlayer(this.whitePlayer, this.blackPlayer);
        this.enPassantPawn = builder.enPassantPiece;
        lastMove = builder.lastMove;
    }

    public Alliance endGame(){
        if (whitePlayer.checkMate() || whitePlayer.isForfeited()) {
            ScoreObject.getInstance().setBlackPlayerScore(1);
            return Alliance.WHITE;
        }
        else if(blackPlayer.checkMate() || blackPlayer.isForfeited()){
            ScoreObject.getInstance().setWhitePlayerScore(1);
            return Alliance.BLACK;
        }
        return Alliance.NONE;
    }

    public Move getLastMove(){
        return this.lastMove;
    }


    public Iterable<Move> getAllLegalMoves(){
        return Iterables.unmodifiableIterable(Iterables.concat(this.whitePlayer.getLegalMoves(), blackPlayer.getLegalMoves()));
    }

    private Collection<Piece> getAllPieces(List<Tile> mBoard) {

        List<Piece> piecesList = new ArrayList<>();
        for (final Tile tile : mBoard){
            if (tile.tileIsOccupied()){
                final Piece piece = tile.getPiece();
                piecesList.add(piece);
            }
        }
        return piecesList;
    }

    private Collection<Move> trackMoves(Collection<Piece> pieces) {

        final List<Move> legalMoves = new ArrayList<>();
        for (final Piece piece : pieces){
            legalMoves.addAll(piece.legalMoves(this));
        }
        Log.d("LegalMoves TrackMoves", legalMoves.toString());
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

    public Pawn getEnPassantPawn(){
        return this.enPassantPawn;
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
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
        builder.setPiece(new Rook(0, 0, Alliance.BLACK, true));
        builder.setPiece(new Knight(1, 0, Alliance.BLACK, true));
        builder.setPiece(new Bishop(2, 0, Alliance.BLACK, true));
        builder.setPiece(new Queen(3, 0, Alliance.BLACK, true));
        builder.setPiece(new King(4, 0, Alliance.BLACK, true));
        builder.setPiece(new Bishop(5, 0, Alliance.BLACK, true));
        builder.setPiece(new Knight(6, 0, Alliance.BLACK, true));
        builder.setPiece(new Rook(7, 0, Alliance.BLACK, true));

        // White
        builder.setPiece(new Rook(0, 7, Alliance.WHITE, true));
        builder.setPiece(new Knight(1, 7, Alliance.WHITE, true));
        builder.setPiece(new Bishop(2, 7, Alliance.WHITE, true));
        builder.setPiece(new Queen(3, 7, Alliance.WHITE, true));
        builder.setPiece(new King(4, 7, Alliance.WHITE, true));
        builder.setPiece(new Bishop(5, 7, Alliance.WHITE, true));
        builder.setPiece(new Knight(6, 7, Alliance.WHITE, true));
        builder.setPiece(new Rook(7, 7, Alliance.WHITE, true));

        // Pawns for Black and White
        for(int i = 0 ; i < Tools.BOARD_DIM ; i++){
            builder.setPiece(new Pawn(i, 1, Alliance.BLACK, true));
            builder.setPiece(new Pawn(i, 6, Alliance.WHITE, true));
        }

        builder.setPlayer(Alliance.WHITE);
        return builder.build();
    }

    public Collection<Piece> getBlackPieces() {
        return this.blackPieces;
    }

    public Collection<Piece> getWhitePieces() {
        return this.whitePieces;
    }

    public PlayerBlack getBlackPlayer() {
        return blackPlayer;
    }

    public PlayerWhite getWhitePlayer() {
        return whitePlayer;
    }

    public static class Builder{
        Map<Integer, Piece> boardLayout = new HashMap<>();
        Alliance nextPlayer;

        Pawn enPassantPiece;
        private Move lastMove;


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

        public void setEnPassantPiece(Pawn enPassantPiece) {
            this.enPassantPiece = enPassantPiece;
        }

        public void setLastMove(Move lastMove) {
            this.lastMove = lastMove;
        }
    }

    public Tile getTile(int xCoor, int yCoor){
        try {
            return mBoard.get(Tools.convertPosition(xCoor, yCoor));
        }catch (IndexOutOfBoundsException e){
            Log.d("outofbounds", "Index out of bounds");
        }
        return null;
    }
}
