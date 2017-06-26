package com.example.tristan.arealchessgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.GridView;

import com.example.tristan.arealchessgame.chess_engine.Alliance;
import com.example.tristan.arealchessgame.chess_engine.Tools;
import com.example.tristan.arealchessgame.chess_engine.board.Board;
import com.example.tristan.arealchessgame.chess_engine.move.Move;
import com.example.tristan.arealchessgame.chess_engine.move.MoveAttack;
import com.example.tristan.arealchessgame.chess_engine.move.MoveMaker;
import com.example.tristan.arealchessgame.chess_engine.board.Tile;
import com.example.tristan.arealchessgame.chess_engine.move.castle.MoveCastle;
import com.example.tristan.arealchessgame.chess_engine.move.pawn.MoveEnPassant;
import com.example.tristan.arealchessgame.chess_engine.pieces.King;
import com.example.tristan.arealchessgame.chess_engine.pieces.Piece;
import com.example.tristan.arealchessgame.chess_engine.player.AlternateBoard;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tristan on 04/06/2017.
 */

public class BoardGridView extends GridView{

    private int columns = Tools.BOARD_DIM, rows = Tools.BOARD_DIM, tileDim;
    private Paint darkTilePaint = new Paint();
    private Paint borderPaint = new Paint();
    private Paint highlightPaint = new Paint();
    private Paint highlightPaintAttack = new Paint();
    private Paint specialMovePaint = new Paint();
    private Paint scorePaintWhite = new Paint();
    private Paint scorePaintBlack = new Paint();
    private Paint lastMoveBluePaint = new Paint();
    private Paint lastMoveRedPaint = new Paint();
    private Paint blackWinsPaint = new Paint();
    private Paint whiteWinsPaint = new Paint();
//    private Paint seemPaint = new Paint();
    final Map<String, Integer> resourceMap;

    private Tile startTile;
    private Tile destinationTile;
    private Piece selectedPiece;

    public Map<String, Integer> resourceMapMaker(){
        Map<String, Integer> tmpresourceMap = new HashMap<>();
        tmpresourceMap.put("BB", R.drawable.bb);
        tmpresourceMap.put("BW", R.drawable.bw);
        tmpresourceMap.put("KB", R.drawable.kb);
        tmpresourceMap.put("KW", R.drawable.kw);
        tmpresourceMap.put("NB", R.drawable.nb);
        tmpresourceMap.put("NW", R.drawable.nw);
        tmpresourceMap.put("PB", R.drawable.pb);
        tmpresourceMap.put("PW", R.drawable.pw);
        tmpresourceMap.put("QB", R.drawable.qb);
        tmpresourceMap.put("QW", R.drawable.qw);
        tmpresourceMap.put("RB", R.drawable.rb);
        tmpresourceMap.put("RW", R.drawable.rw);


        return tmpresourceMap;
    }

    public BoardGridView(Context context){
        this(context, null);
    }

    public BoardGridView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        this.resourceMap = resourceMapMaker();
        darkTilePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);
        calculateDimensions();
    }

    private void calculateDimensions() {
        if (columns < 1 || rows < 1) {
            return;
        }

        tileDim = getWidth() / columns;

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas){
        // Create a canvas for our board
        canvas.drawColor(ContextCompat.getColor(this.getContext(), R.color.stone));
        darkTilePaint.setColor(ContextCompat.getColor(this.getContext(), R.color.bark));

        lastMoveBluePaint.setColor(ContextCompat.getColor(this.getContext(), R.color.lastMoveBlue));
        lastMoveBluePaint.setStyle(Paint.Style.STROKE);
        lastMoveBluePaint.setStrokeWidth(5);
        lastMoveRedPaint.setColor(ContextCompat.getColor(this.getContext(), R.color.lastMoveRed));
        lastMoveRedPaint.setStyle(Paint.Style.STROKE);
        lastMoveRedPaint.setStrokeWidth(5);

        highlightPaintAttack.setStrokeWidth(5);
        highlightPaintAttack.setStyle(Paint.Style.STROKE);
        highlightPaintAttack.setColor(ContextCompat.getColor(this.getContext(), R.color.highLightColorAttack));
        specialMovePaint.setStrokeWidth(5);
        specialMovePaint.setStyle(Paint.Style.STROKE);
        specialMovePaint.setColor(ContextCompat.getColor(this.getContext(), R.color.enPassantColor));

        borderPaint.setStrokeWidth(20);
        borderPaint.setColor(ContextCompat.getColor(this.getContext(), R.color.darkTileColor3));

        highlightPaint.setColor(ContextCompat.getColor(this.getContext(), R.color.highLightColor));
        highlightPaint.setStrokeWidth(5);
        highlightPaint.setStyle(Paint.Style.STROKE);

        scorePaintWhite.setColor(ContextCompat.getColor(this.getContext(), R.color.white));
        scorePaintWhite.setAlpha(127);
        scorePaintBlack.setColor(ContextCompat.getColor(this.getContext(), R.color.black));
        scorePaintBlack.setAlpha(127);

        blackWinsPaint.setColor(ContextCompat.getColor(this.getContext(), R.color.white));
        blackWinsPaint.setTextSize(100);
        whiteWinsPaint.setColor(ContextCompat.getColor(this.getContext(), R.color.black));
        whiteWinsPaint.setTextSize(100);

        // Set boardDimensions, always square according to shortest side.
        int boardDim;
        int boardWidth = getWidth();
        int boardHeight = getHeight();

        if (boardWidth<=boardHeight){
            boardDim = boardWidth;
        }
        else{
            boardDim = boardHeight;
        }

        // draw rectangles and make black or white depending on a mismatch between col/row modulus.
        for (int yRows = 0; yRows < rows; yRows++) {
            for (int xColumns = 0; xColumns < columns; xColumns++) {
                if (yRows%2 == 1 ^ xColumns%2 == 0){
                    continue;
                }
                else{
                    canvas.drawRect(yRows * tileDim, xColumns * tileDim, (yRows + 1) * tileDim,
                            (xColumns + 1) * tileDim, darkTilePaint);
                }
            }
        }
        for (int yRows = 1; yRows < columns; yRows++){
            canvas.drawLine(yRows* tileDim, 0, yRows* tileDim, boardDim, darkTilePaint);
        }
        for(int xColumns = 1; xColumns <=rows; xColumns++){
            canvas.drawLine(0, xColumns* tileDim, boardDim, xColumns* tileDim, darkTilePaint);
        }

        //drawing borders
//        seemPaint.setStrokeWidth(5);
        //top
        canvas.drawLine(0, 0, boardDim, 0, borderPaint);
//        canvas.drawLine(-1, 0, boardDim, 0, seemPaint);
        //left
        canvas.drawLine(0, 0, 0, boardDim, borderPaint);
//        canvas.drawLine(-1, 0, 0, boardDim, seemPaint);
        //bottom
        canvas.drawLine(0, boardDim, boardDim, boardDim, borderPaint);
//        canvas.drawLine(-1, boardDim, boardDim, boardDim, seemPaint);
        //right
        canvas.drawLine(boardDim, 0, boardDim, boardDim, borderPaint);
//        canvas.drawLine(boardDim, 0, boardDim, boardDim, seemPaint);

        // create a bitmap of a piece and place it in the right square.
        for (int yRows = 0 ; yRows < columns ; yRows++){
            for (int xColumns = 0; xColumns < rows; xColumns++){
                Bitmap pieceIcon = createBitmap(xColumns, yRows);
                if(pieceIcon != null){
                    canvas.drawBitmap(pieceIcon, xColumns * tileDim, yRows * tileDim, null);
                }
            }
        }
        //highlight lastMove
        Move lastMove = Board.getInstance().getLastMove();
        if (lastMove != null){
            int xStart = lastMove.getCurrentXPos();
            int yStart =lastMove.getCurrentYPos();
            int xDest = lastMove.getXDestination();
            int yDest = lastMove.getYDestination();


            if (!(lastMove instanceof MoveAttack)) {
                canvas.drawRect(xStart * tileDim, yStart * tileDim, (xStart + 1) * tileDim, (yStart + 1) * tileDim, lastMoveBluePaint);
                canvas.drawRect(xDest * tileDim, yDest * tileDim, (xDest + 1) * tileDim, (yDest + 1) * tileDim, lastMoveBluePaint);
            }
            else{
                canvas.drawRect(xStart * tileDim, yStart * tileDim, (xStart + 1) * tileDim, (yStart + 1) * tileDim, lastMoveRedPaint);
                canvas.drawRect(xDest * tileDim, yDest * tileDim, (xDest + 1) * tileDim, (yDest + 1) * tileDim, lastMoveRedPaint);
            }
        }
        //highlight selection
        if (selectedPiece != null){
            int x = startTile.getxCoordinate();
            int y = startTile.getyCoordinate();
            if(selectedPiece.getAlliance() == Board.getInstance().getCurrentPlayer().getAlliance()) {
                canvas.drawRect(x * tileDim, y * tileDim, (x + 1) * tileDim, (y + 1) * tileDim, highlightPaint);
            }
            else{
                startTile = null;
                selectedPiece = null;
            }
        }
        //highlight possible moves
        if (selectedPiece != null){
            if(selectedPiece.getAlliance() == Board.getInstance().getCurrentPlayer().getAlliance()) {
                Collection<Move> legalMoves = selectedPiece.legalMoves(Board.getInstance());
                Collection<Move> castleMoves = Board.getInstance().getCurrentPlayer().getLegalMoves();
                Collection<Move> enemyMoves = Board.getInstance().getCurrentPlayer().getOpponent().getLegalMoves();
                for (Move move : legalMoves) {
                    final AlternateBoard testBoard = Board.getInstance().getCurrentPlayer().makeMove(move);
                    if (testBoard.getMoveWas().isExecuted()) {
                        int x = move.getXDestination();
                        int y = move.getYDestination();

                        if (move instanceof MoveAttack) {
                            canvas.drawRoundRect(x * tileDim, y * tileDim, (x + 1) * tileDim, (y + 1) * tileDim, tileDim / 2, tileDim / 2, highlightPaintAttack);
                        } else if (move instanceof MoveEnPassant) {
                            canvas.drawRoundRect(x * tileDim, y * tileDim, (x + 1) * tileDim, (y + 1) * tileDim, tileDim / 2, tileDim / 2, specialMovePaint);
                        } else {
                            canvas.drawRoundRect(x * tileDim, y * tileDim, (x + 1) * tileDim, (y + 1) * tileDim, tileDim / 2, tileDim / 2, highlightPaint);
                        }
                    }
                }
                for (Move castleMove : castleMoves){
                    int x = castleMove.getXDestination();
                    int y = castleMove.getYDestination();
                    if (castleMove instanceof MoveCastle && selectedPiece instanceof King){
                        canvas.drawRoundRect(x * tileDim, y * tileDim, (x + 1) * tileDim, (y + 1) * tileDim, tileDim/2, tileDim/2, specialMovePaint);
                    }
                }




//                //debug highlight enemymoves
//                for (Move move : enemyMoves){
//                    int x = move.getXDestination();
//                    int y = move.getYDestination();
//                    canvas.drawRect(x * tileDim, y * tileDim, (x+1) * tileDim, (y+1) * tileDim, borderPaint);
//                }
            }
        }
        //endgame
        if(Board.getInstance().endGame() == Alliance.WHITE){
            if (Board.getInstance().getCurrentPlayer().isForfeited()) {
                canvas.drawRect(0, 0, boardDim, boardDim, scorePaintBlack);
                canvas.drawText("White Forfeited!", 150, 500, blackWinsPaint);
            }
            else{
                canvas.drawRect(0, 0, boardDim, boardDim, scorePaintBlack);
                canvas.drawText("Checkmate!", 200, 450, blackWinsPaint);
                canvas.drawText("Black Wins!", 200, 550, blackWinsPaint);
            }
        }
        else if(Board.getInstance().endGame() == Alliance.BLACK){
            if (Board.getInstance().getCurrentPlayer().isForfeited()) {
                canvas.drawRect(0, 0, boardDim, boardDim, scorePaintWhite);
                canvas.drawText("Black Forfeited!", 150, 500, whiteWinsPaint);
            }
            else{
                canvas.drawRect(0, 0, boardDim, boardDim, scorePaintWhite);
                canvas.drawText("Checkmate!", 200, 450, whiteWinsPaint);
                canvas.drawText("White Wins!", 200, 550, whiteWinsPaint);
            }
            }
        }

    private Bitmap createBitmap(int xCoordinate, int yCoordinate) {
        Piece piece = Board.getInstance().getTile(xCoordinate, yCoordinate).getPiece();
        if (piece != null) {
            String pathString = piece.toString() + piece.getAlliance().toString();
            return BitmapFactory.decodeResource(getResources(), resourceMap.get(pathString));
        }
        else{
            return null;
        }
    }

    // Force gridView to be square on every screenSize.
    @Override
    protected void onMeasure(int widthMeasurement, int heightMeasurement) {
        super.onMeasure(widthMeasurement, heightMeasurement);
        int size;
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        if (width >= height) {
            size = height;
        } else {
            size = width;
        }
        setMeasuredDimension(size, size);
    }

    // TouchEvent, because we are obviously going to need that.
    @Override
    public boolean onTouchEvent(MotionEvent event){
        Board oldBoard = Board.getInstance();
        Boolean allowed = false;
        if (!GameChanger.getInstance().setup.isComputer(oldBoard.getCurrentPlayer())){
            allowed = true;
        }
        if (GameChanger.getInstance().setup.isComputer(oldBoard.getCurrentPlayer()) &&
                GameChanger.getInstance().isFirstMove){
            allowed = true;
        }
        if (allowed) {
            if(oldBoard.endGame() == Alliance.NONE) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    try {
                        int xColumn = (int) (event.getX() / tileDim);
                        int yRow = (int) (event.getY() / tileDim);
                        String message = Integer.toString(xColumn) + ", " + Integer.toString(yRow);
                        Log.d("aroutbound", message);
                        Boolean curComp = GameChanger.getInstance().getSetup().isComputer(Board.getInstance().getCurrentPlayer());
                        Log.d("typeSet", curComp ? "Current Player is Computer " + curComp : "Current Player is Human " + curComp);
                        if (startTile == null) {
                            startTile = oldBoard.getTile(xColumn, yRow);
                            selectedPiece = startTile.getPiece();
                            if (selectedPiece == null) {
                                startTile = null;
                            }
                            Log.d("invalidated", "I am here 1");
                            invalidate();
                        } else {
                            destinationTile = oldBoard.getTile(xColumn, yRow);
                            if (destinationTile.equals(startTile)) {
                                startTile = null;
                                destinationTile = null;
                                selectedPiece = null;
                                invalidate();
                            } else {
                                final Move move = MoveMaker.createMove(oldBoard,
                                        startTile.getxCoordinate(), startTile.getyCoordinate(),
                                        destinationTile.getxCoordinate(),
                                        destinationTile.getyCoordinate());
                                Log.d("LegalMoves Move", move.toString());
                                final AlternateBoard newBoard = oldBoard.getCurrentPlayer().makeMove(move);
                                Log.d("invalidated", "I am here 2");
                                if (newBoard.getMoveWas().isExecuted()) {
//                                    int oldMoveCount = oldBoard.getMoveCount();
                                    Board.instance = newBoard.getBoard();
                                    Board.getInstance().setLastMove(move);
//                                    Board.getInstance().setMoveCount(oldMoveCount + 1);
                                    GameChanger.getInstance().notFirstMove();
                                    if (GameChanger.getInstance().getSetup().isComputer(Board.getInstance().getCurrentPlayer())) {
                                        GameChanger.getInstance().moveUpdate(GameChanger.Type.HUMAN);
                                    }
                                    invalidate();
                                    Log.d("invalidated", "I am here 3");
                                }
                                startTile = null;
                                destinationTile = null;
                                selectedPiece = null;
                                invalidate();
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException ar) {
                        Log.d("aroutbound", "That was out of bounds, solve later?");
                    }
                }
            }
        }
        return true;
    }

}

