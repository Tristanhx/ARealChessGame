package com.example.tristan.arealchessgame.GUI;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.example.tristan.arealchessgame.Alliance;
import com.example.tristan.arealchessgame.ChessEngine.Tools;
import com.example.tristan.arealchessgame.ChessEngine.board.Board;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Knight;
import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;
import com.example.tristan.arealchessgame.GameActivity;
import com.example.tristan.arealchessgame.R;

import java.util.HashMap;
import java.util.Map;

import static android.support.constraint.R.id.parent;

/**
 * Created by Tristan on 04/06/2017.
 */

public class BoardGridView extends GridView {
    private int columns = Tools.BOARD_DIM, rows = Tools.BOARD_DIM, tileDim;
    private Paint blackPaint = new Paint();
    private boolean[][] tileBlack;
    final Map<String, Integer> resourceMap;

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

        tileBlack = new boolean[columns][rows];

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas){
        // Create a canvas for our board
        canvas.drawColor(Color.WHITE);

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
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            try {
                int xColumn = (int) (event.getX() / tileDim);
                int yRow = (int) (event.getY() / tileDim);
                String message = Integer.toString(xColumn) +", " + Integer.toString(yRow);
                Log.d("aroutbound", message);
//                TODO: send coordinates to Move/Board class to select piece
                Piece selectedPiece = Board.getInstance().getTile(xColumn, yRow).getPiece();
//                TODO: send coordinates and piece to Move/Board class, make new board with moved piece
                invalidate();
            }catch (ArrayIndexOutOfBoundsException ar){
                Log.d("aroutbound", "That was out of bounds, solve later?");
            }
        }
        return true;
    }

}

