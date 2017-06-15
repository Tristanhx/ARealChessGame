package com.example.tristan.arealchessgame.GUI;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.GridView;

import com.example.tristan.arealchessgame.ChessEngine.Tools;
import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.move.Move;
import com.example.tristan.arealchessgame.ChessEngine.move.MoveMaker;
import com.example.tristan.arealchessgame.ChessEngine.board.Tile;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;
import com.example.tristan.arealchessgame.ChessEngine.player.AlternateBoard;
import com.example.tristan.arealchessgame.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tristan on 04/06/2017.
 */

public class BoardGridView extends GridView {
    private int columns = Tools.BOARD_DIM, rows = Tools.BOARD_DIM, tileDim;
    private Paint blackPaint = new Paint();
    private Paint borderPaint = new Paint();
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
        blackPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    // Setters
    public void setColumns(int columns){
        this.columns = columns;
    }

    public void setRows(int rows){
        this.rows = rows;
    }

    // Getters
    public int getColumns(){
        return columns;
    }

    public int getRows(){
        return rows;
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

//        tileBlack = new boolean[columns][rows];

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas){
        // Create a canvas for our board
        canvas.drawColor(Color.WHITE);
        blackPaint.setColor(ContextCompat.getColor(this.getContext(), R.color.darkTileColor2));

        // Check if col/row isn't 0, now redundant since size is now hardcoded.
        if(columns == 0 || rows == 0){
            return;
        }

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
                            (xColumns + 1) * tileDim, blackPaint);
                }
            }
        }
        for (int yRows = 1; yRows < columns; yRows++){
            canvas.drawLine(yRows* tileDim, 0, yRows* tileDim, boardDim, blackPaint);
        }
        for(int xColumns = 1; xColumns <=rows; xColumns++){
            canvas.drawLine(0, xColumns* tileDim, boardDim, xColumns* tileDim, blackPaint);
        }

        //drawing borders
        borderPaint.setStrokeWidth(25);
        borderPaint.setColor(ContextCompat.getColor(this.getContext(), R.color.darkTileColor3));
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
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            try {
                int xColumn = (int) (event.getX() / tileDim);
                int yRow = (int) (event.getY() / tileDim);
                String message = Integer.toString(xColumn) +", " + Integer.toString(yRow);
                Log.d("aroutbound", message);
//                TODO: send coordinates to Move/Board class to select piece
                if (startTile == null) {
                    startTile = oldBoard.getTile(xColumn, yRow);
                    selectedPiece = startTile.getPiece();
                    if (selectedPiece == null) {
                        startTile = null;
                    }
                    Log.d("invalidated", "I am here 1");
                } else{
                    destinationTile = oldBoard.getTile(xColumn, yRow);
                    final Move move = MoveMaker.createMove(oldBoard,
                            startTile.getxCoordinate(), startTile.getyCoordinate(),
                            destinationTile.getxCoordinate(),
                            destinationTile.getyCoordinate());
                    Log.d("LegalMoves Move", move.toString());
                    final AlternateBoard newBoard =  oldBoard.getCurrentPlayer().makeMove(move);
                    Log.d("invalidated", "I am here 2");
                    if (newBoard.getMoveWas().isExecuted()){
                        Board.instance = newBoard.getBoard();
                        invalidate();
                        Log.d("invalidated", "I am here 3");
                    }
                    startTile = null;
                    destinationTile = null;
                    selectedPiece = null;
                }
//                TODO: send coordinates and piece to Move/Board class, make new board with moved piece
            }catch (ArrayIndexOutOfBoundsException ar){
                Log.d("aroutbound", "That was out of bounds, solve later?");
            }
        }
        return true;
    }

}

