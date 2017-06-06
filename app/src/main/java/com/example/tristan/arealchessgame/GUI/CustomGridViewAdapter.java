package com.example.tristan.arealchessgame.GUI;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.tristan.arealchessgame.ChessEngine.pieces.Piece;
import com.example.tristan.arealchessgame.R;

import java.util.ArrayList;

/**
 * Created by Tristan on 04/06/2017.
 */

public class CustomGridViewAdapter extends ArrayAdapter<Piece>{
    Context context;
    int layoutResourceId;
    ArrayList<Piece> pieces = new ArrayList<>();

    public CustomGridViewAdapter(Context context, int layoutResourceId, ArrayList<Piece> pieces){
        super(context, layoutResourceId, pieces);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.pieces = pieces;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;
        RecordHolder holder;

        if (row == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new RecordHolder();
            holder.imageItem = (ImageView) row.findViewById(R.id.piece_image);
            row.setTag(holder);
        }
        else{
            holder = (RecordHolder) row.getTag();
        }

        Piece piece = pieces.get(position);
        //holder.imageItem.setImageBitmap(piece.getImage());
        return row;
    }

    static class RecordHolder{
        ImageView imageItem;
    }
}
