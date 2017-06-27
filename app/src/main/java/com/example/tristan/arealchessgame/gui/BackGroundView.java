package com.example.tristan.arealchessgame.gui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.tristan.arealchessgame.GameActivity;
import com.example.tristan.arealchessgame.R;
import com.example.tristan.arealchessgame.StaticApplicationContext;
import com.example.tristan.arealchessgame.chess_engine.Alliance;
import com.example.tristan.arealchessgame.chess_engine.board.Board;

/**
 * Created by Tristan on 25/06/2017.
 */

public class BackGroundView extends View {
    private Paint textPaint = new Paint();
    DisplayMetrics displayMetrics = new DisplayMetrics();
    Bitmap greenBall = BitmapFactory.decodeResource(getResources(), R.drawable.green_ball);
    Bitmap redBall = BitmapFactory.decodeResource(getResources(), R.drawable.red_ball);

    public BackGroundView(Context context) {
        super(context);
    }

    public BackGroundView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    @Override
    protected void onDraw(Canvas canvas){
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        canvas.drawColor(ContextCompat.getColor(this.getContext(), R.color.prettyDarkGray));

        textPaint.setTextSize(50);
        textPaint.setColor(ContextCompat.getColor(StaticApplicationContext.context, R.color.white));

        if(Board.getInstance().getCurrentPlayer().getAlliance() == Alliance.WHITE){
            canvas.drawText("White's Turn", (width/2), (height-50), textPaint);
            if (Board.getInstance().getCurrentPlayer().checkInCheck()){
                canvas.drawBitmap(redBall, (width/2), (height-250), textPaint);
            }
            else{
                canvas.drawBitmap(greenBall, (width/2), (height-250), textPaint);
            }
        }
        else{
            canvas.drawText("Black's Turn", (width/2), 50, textPaint);
            if (Board.getInstance().getCurrentPlayer().checkInCheck()){
                canvas.drawBitmap(redBall, (width/2), 100, textPaint);
            }
            else{
                canvas.drawBitmap(greenBall, (width/2), 100, textPaint);
            }
        }
    }
}
