package com.example.tristan.arealchessgame.GUI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by trist on 6/8/2017.
 */

public class TileView extends android.support.v7.widget.AppCompatImageView {
    final int tileId;
    final int tileDim = 10;
    final Paint whitePaint = new Paint();
    boolean isBlack;

    public TileView(Context context, final int tileId) {
        super(context);
        this.tileId = tileId;
        assignColor();
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawRect(0, 0, tileDim, tileDim, whitePaint);


    }

    protected void assignColor(){
        this.isBlack = true;
    }
}
