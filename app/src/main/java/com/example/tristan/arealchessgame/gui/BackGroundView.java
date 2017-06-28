package com.example.tristan.arealchessgame.gui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

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
    Bitmap greenIndicatorWhite = getBitmapFromVectorDrawable(this.getContext(), R.drawable.a_i_green_indicator_white);
    Bitmap greenIndicatorBlack = getBitmapFromVectorDrawable(this.getContext(), R.drawable.a_i_green_indicator_black);
    Bitmap checkIndicatorWhite = getBitmapFromVectorDrawable(this.getContext(), R.drawable.a_i_yellow_indicator_white);
    Bitmap checkIndicatorBlack = getBitmapFromVectorDrawable(this.getContext(), R.drawable.a_i_yellow_indicator_black);
    Bitmap checkMateIndicatorWhite = getBitmapFromVectorDrawable(this.getContext(), R.drawable.a_i_red_indicator_white);
    Bitmap checkMateIndicatorBlack = getBitmapFromVectorDrawable(this.getContext(), R.drawable.a_i_red_indicator_black);

    public BackGroundView(Context context) {
        super(context);
    }

    public BackGroundView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    //https://stackoverflow.com/questions/33696488/getting-bitmap-from-vector-drawable
    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    @Override
    protected void onDraw(Canvas canvas){
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        int whiteHeightOffset = 350;
        int blackHeightOffset = 75;
        int widthOffset = (width/2)-125;

        canvas.drawColor(ContextCompat.getColor(this.getContext(), R.color.prettyDarkGray));

        textPaint.setTextSize(50);
        textPaint.setColor(ContextCompat.getColor(StaticApplicationContext.context, R.color.white));

        if(Board.getInstance().getCurrentPlayer().getAlliance() == Alliance.WHITE){
            if(Board.getInstance().endGame() == Alliance.WHITE){
                canvas.drawBitmap(checkMateIndicatorWhite, widthOffset, (height-whiteHeightOffset), textPaint);
            }
            else if (Board.getInstance().getCurrentPlayer().checkInCheck()){
                canvas.drawBitmap(checkIndicatorWhite, widthOffset, (height-whiteHeightOffset), textPaint);
            }
            else{
                canvas.drawBitmap(greenIndicatorWhite, widthOffset, (height-whiteHeightOffset), textPaint);
            }
        }
        else{
            if (Board.getInstance().endGame() == Alliance.BLACK){
                canvas.drawBitmap(checkMateIndicatorBlack, widthOffset, blackHeightOffset, textPaint);
            }
            else if (Board.getInstance().getCurrentPlayer().checkInCheck()){
                canvas.drawBitmap(checkIndicatorBlack, widthOffset, blackHeightOffset, textPaint);
            }
            else{
                canvas.drawBitmap(greenIndicatorBlack, widthOffset, blackHeightOffset, textPaint);
            }
        }
    }
}
