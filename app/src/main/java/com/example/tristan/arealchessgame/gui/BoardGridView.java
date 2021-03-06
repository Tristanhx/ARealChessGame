package com.example.tristan.arealchessgame.gui;

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

import com.example.tristan.arealchessgame.GameController;
import com.example.tristan.arealchessgame.R;
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
import com.example.tristan.arealchessgame.chess_engine.board.AlternateBoard;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tristan on 04/06/2017.
 */

public class BoardGridView extends GridView{

    private int tileDim, boardDim;
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
    final Map<String, Integer> resourceMap;

    private Tile startTile;
    private Tile destinationTile;
    private Piece selectedPiece;



    public BoardGridView(Context context){
        this(context, null);
    }

    public BoardGridView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        this.resourceMap = resourceMapMaker();
        setPaints();
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);
        calculateDimensions();
    }

    private void calculateDimensions() {
        tileDim = getWidth() / Tools.BOARD_DIM;

        invalidate();
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

    private void setPaints() {
        darkTilePaint.setColor(ContextCompat.getColor(this.getContext(), R.color.bark));
        darkTilePaint.setStyle(Paint.Style.FILL_AND_STROKE);

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
    }

    @Override
    protected void onDraw(Canvas canvas){
        // Set boardDimensions, always square according to shortest side.
        int boardWidth = getWidth();
        int boardHeight = getHeight();

        if (boardWidth<=boardHeight){
            boardDim = boardWidth;
        }
        else{
            boardDim = boardHeight;
        }

        canvas.drawColor(ContextCompat.getColor(this.getContext(), R.color.stone));
        drawBoard(canvas);
        drawPieces(canvas);
        highlightLastMoves(canvas);
        highlightSelection(canvas);
        highlightPossibleMoves(canvas);
        drawEndState(canvas);
    }

    private void drawBoard(Canvas canvas) {
        for (int yRows = 0; yRows < Tools.BOARD_DIM; yRows++) {
            for (int xColumns = 0; xColumns < Tools.BOARD_DIM; xColumns++) {
                if (yRows%2 == 1 ^ xColumns%2 == 0){
                    continue;
                }
                else{
                    canvas.drawRect(yRows * tileDim, xColumns * tileDim, (yRows + 1) * tileDim,
                            (xColumns + 1) * tileDim, darkTilePaint);
                }
            }
        }

        //top
        canvas.drawLine(0, 0, boardDim, 0, borderPaint);
        //left
        canvas.drawLine(0, 0, 0, boardDim, borderPaint);
        //bottom
        canvas.drawLine(0, boardDim, boardDim, boardDim, borderPaint);
        //right
        canvas.drawLine(boardDim, 0, boardDim, boardDim, borderPaint);
    }

    private void drawPieces(Canvas canvas) {
        for (int yRows = 0 ; yRows < Tools.BOARD_DIM ; yRows++){
            for (int xColumns = 0; xColumns < Tools.BOARD_DIM; xColumns++){
                Bitmap pieceIcon = createBitmap(xColumns, yRows);
                if(pieceIcon != null){
                    canvas.drawBitmap(pieceIcon, xColumns * tileDim, yRows * tileDim, null);
                }
            }
        }
    }

    private Bitmap createBitmap(int xCoordinate, int yCoordinate) {
        Piece piece = Board.getInstance().getTile(xCoordinate, yCoordinate).getPiece();
        String pathString;
        if (piece != null) {
            if (!GameController.getInstance().getSetup().isComputer(Board.getInstance().getBlackPlayer())
                    && !GameController.getInstance().getSetup().isComputer(Board.getInstance().getWhitePlayer())
                    && piece.getAlliance().isBlack()){
                pathString = "U" + piece.toString() + piece.getAlliance().toString();
            }
            else {
                pathString = piece.toString() + piece.getAlliance().toString();
            }
            return BitmapFactory.decodeResource(getResources(), resourceMap.get(pathString));
        }
        else{
            return null;
        }
    }

    public Map<String, Integer> resourceMapMaker(){
        Map<String, Integer> tmpresourceMap = new HashMap<>();
        tmpresourceMap.put("BW", R.drawable.bw);
        tmpresourceMap.put("KW", R.drawable.kw);
        tmpresourceMap.put("NW", R.drawable.nw);
        tmpresourceMap.put("PW", R.drawable.pw);
        tmpresourceMap.put("RW", R.drawable.rw);
        tmpresourceMap.put("QW", R.drawable.qw);
        tmpresourceMap.put("BB", R.drawable.bb);
        tmpresourceMap.put("KB", R.drawable.kb);
        tmpresourceMap.put("NB", R.drawable.nb);
        tmpresourceMap.put("PB", R.drawable.pb);
        tmpresourceMap.put("QB", R.drawable.qb);
        tmpresourceMap.put("RB", R.drawable.rb);
        tmpresourceMap.put("UBB", R.drawable.ubb);
        tmpresourceMap.put("UKB", R.drawable.ukb);
        tmpresourceMap.put("UNB", R.drawable.unb);
        tmpresourceMap.put("UPB", R.drawable.upb);
        tmpresourceMap.put("UQB", R.drawable.uqb);
        tmpresourceMap.put("URB", R.drawable.urb);




        return tmpresourceMap;
    }

    private void highlightLastMoves(Canvas canvas) {
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
    }

    private void highlightSelection(Canvas canvas) {
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
    }

    private void highlightPossibleMoves(Canvas canvas) {
        if (selectedPiece != null){
            if(selectedPiece.getAlliance() == Board.getInstance().getCurrentPlayer().getAlliance()) {
                Collection<Move> legalMoves = selectedPiece.legalMoves(Board.getInstance());
                Collection<Move> castleMoves = Board.getInstance().getCurrentPlayer().getLegalMoves();
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
            }
        }
    }

    private void drawEndState(Canvas canvas) {
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



    // TouchEvent, because we are obviously going to need that in a touch-based-chess-game.
    @Override
    public boolean onTouchEvent(MotionEvent event){
        Board oldBoard = Board.getInstance();
        Boolean allowed = false;
        if (!GameController.getInstance().getSetup().isComputer(oldBoard.getCurrentPlayer())){
            allowed = true;
        }
        else if (GameController.getInstance().isFirstMove){
            allowed = true;
        }
        if (allowed) {
            if(oldBoard.endGame() == Alliance.NONE) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    try {
                        int xColumn = (int) (event.getX() / tileDim);
                        int yRow = (int) (event.getY() / tileDim);
                        if (startTile == null) {
                            startTile = oldBoard.getTile(xColumn, yRow);
                            selectedPiece = startTile.getPiece();
                            if (selectedPiece == null) {
                                startTile = null;
                            }
                            invalidate();
                        } else {
                            destinationTile = oldBoard.getTile(xColumn, yRow);
                            if (destinationTile.equals(startTile)) {
                                startTile = null;
                                destinationTile = null;
                                selectedPiece = null;
                                invalidate();
                            } else {
                                doMove(oldBoard);
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

    private void doMove(Board oldBoard) {
        final Move move = MoveMaker.createMove(oldBoard,
                startTile.getxCoordinate(), startTile.getyCoordinate(),
                destinationTile.getxCoordinate(),
                destinationTile.getyCoordinate());
        final AlternateBoard newBoard = oldBoard.getCurrentPlayer().makeMove(move);
        if (newBoard.getMoveWas().isExecuted()) {
            Board.instance = newBoard.getBoard();
            GameController.getInstance().notFirstMove();
            if (GameController.getInstance().getSetup().isComputer(Board.getInstance().getCurrentPlayer())) {
                GameController.getInstance().moveUpdate(GameController.Type.HUMAN);
            }
            GameController.backGroundView.invalidate();
            invalidate();
        }
        startTile = null;
        destinationTile = null;
        selectedPiece = null;
        invalidate();
    }

}

