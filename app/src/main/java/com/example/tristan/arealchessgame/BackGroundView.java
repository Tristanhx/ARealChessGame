package com.example.tristan.arealchessgame;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.view.View;

/**
 * Created by Tristan on 25/06/2017.
 */

public class BackGroundView extends View {
    public BackGroundView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawColor(ContextCompat.getColor(this.getContext(), R.color.stone_dark));


    }
}
