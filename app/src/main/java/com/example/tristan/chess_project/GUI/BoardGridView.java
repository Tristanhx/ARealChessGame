package com.example.tristan.chess_project.GUI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.example.tristan.chess_project.GameActivity;

/**
 * Created by Tristan on 04/06/2017.
 */

public class BoardGridView extends View {
    private int columns = 8, rows = 8, tileDim;
    private Paint blackPaint = new Paint();
    private boolean[][] tileBlack;
    Context context;

    public BoardGridView(Context context){
        this(context, null);
    }

    public BoardGridView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
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
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (i%2 == 1 ^ j%2 == 0){
                    continue;
                }
                else{
                    tileBlack[i][j] = true;
                }
                if (tileBlack[i][j]) {
                    canvas.drawRect(i * tileDim, j * tileDim, (i + 1) * tileDim, (j + 1) * tileDim,
                            blackPaint);
                }
            }
        }
        for (int i = 1; i < columns; i++){
            canvas.drawLine(i* tileDim, 0, i* tileDim, boardDim, blackPaint);
        }
        for(int i = 1; i <=rows; i++){
            canvas.drawLine(0, i* tileDim, boardDim, i* tileDim, blackPaint);
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
                int columnInt = (int) (event.getX() / tileDim);
                int rowInt = (int) (event.getY() / tileDim);
                String message = Integer.toString(columnInt) +", " + Integer.toString(rowInt);
                Log.d("aroutbound", message);

//                this.context = context.getApplicationContext();
//                try {
//                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
//                }catch(NullPointerException nullPoint){
//                    Log.d("aroutbound", "context not found: " + context.toString());
//                }

//                tileBlack[columnInt][rowInt] = !tileBlack[columnInt][rowInt];
//                invalidate();
            }catch (ArrayIndexOutOfBoundsException ar){
                Log.d("aroutbound", "That was out of bounds, solve later?");
            }
        }
        return true;
    }

}
