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
    private Paint piecePaint = new Paint();
    private boolean[][] tileBlack;
    Context context;
    final Map<String, Integer> resourceMap;

    public Map<String, Integer> resourceMapMaker(){
        Map<String, Integer> tmpresourceMap = new HashMap<>();
        tmpresourceMap.put("bb", R.drawable.bb);
        tmpresourceMap.put("bw", R.drawable.bw);
        tmpresourceMap.put("kb", R.drawable.kb);
        tmpresourceMap.put("kw", R.drawable.kw);
        tmpresourceMap.put("nb", R.drawable.nb);
        tmpresourceMap.put("nw", R.drawable.nw);
        tmpresourceMap.put("pb", R.drawable.pb);
        tmpresourceMap.put("pw", R.drawable.pw);
        tmpresourceMap.put("qb", R.drawable.qb);
        tmpresourceMap.put("qw", R.drawable.qw);
        tmpresourceMap.put("rb", R.drawable.rb);
        tmpresourceMap.put("rw", R.drawable.rw);


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
//                setPiecePaint(j, i);
                Bitmap pieceIcon = createBitmap(xColumns, yRows);
                if (yRows%2 == 1 ^ xColumns%2 == 0){
                    if(pieceIcon != null){
                        canvas.drawBitmap(pieceIcon, yRows * tileDim, xColumns * tileDim, null);
                    }
                    continue;
                }
                else{
                    tileBlack[yRows][xColumns] = true;
                }
                if (tileBlack[yRows][xColumns]) {
                    canvas.drawRect(yRows * tileDim, xColumns * tileDim, (yRows + 1) * tileDim, (xColumns + 1) * tileDim,
                            blackPaint);
                }
//                canvas.drawRect(i * tileDim, j * tileDim, (i + 1) * tileDim, (j + 1) * tileDim, piecePaint);
                if(pieceIcon != null){
                    canvas.drawBitmap(pieceIcon, yRows * tileDim, xColumns * tileDim, null);
                }
            }
        }
        for (int yRows = 1; yRows < columns; yRows++){
            canvas.drawLine(yRows* tileDim, 0, yRows* tileDim, boardDim, blackPaint);
        }
        for(int xColumns = 1; xColumns <=rows; xColumns++){
            canvas.drawLine(0, xColumns* tileDim, boardDim, xColumns* tileDim, blackPaint);
        }
        for (int i = 0 ; i < columns ; i++){
            for (int j = 0; j < rows; j++){

            }
        }
//        int dx = 0;
//        int dy = 0;
//        canvas.translate(dx, dy);
    }

    private Bitmap createBitmap(int xCoordinate, int yCoordinate) {
        Piece piece = Board.getInstance().getTile(xCoordinate, yCoordinate).getPiece();
        if (piece != null) {
            String pathString = piece.toString().toLowerCase() + piece.getAlliance().toString().toLowerCase();
            return BitmapFactory.decodeResource(getResources(), resourceMap.get(pathString));
        }
        else{
            return null;
        }
    }

//    protected void setPiecePaint(int xCoordinate, int yCoordinate){
//        String pathString = Board.getInstance().getTile(xCoordinate, yCoordinate).getPiece()
//                .toString().toLowerCase() + Board.getInstance().getTile(xCoordinate, yCoordinate).getPiece()
//                .getAlliance().toString().toLowerCase();
//        int resId = this.getResources().getIdentifier(pathString, "drawable", "com.example.tristan.arealchessgame");
//        Bitmap pieceIcon = BitmapFactory.decodeResource(this.getResources(), resId);
//        piecePaint.setShader(new BitmapShader(pieceIcon, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
//    }

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

