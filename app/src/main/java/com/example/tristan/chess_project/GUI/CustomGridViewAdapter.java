package com.example.tristan.chess_project.GUI;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.tristan.chess_project.ChessEngine.pieces.Pieces;
import com.example.tristan.chess_project.R;

import java.util.ArrayList;

/**
 * Created by Tristan on 04/06/2017.
 */

public class CustomGridViewAdapter extends ArrayAdapter<Pieces>{
    Context context;
    int layoutResourceId;
    ArrayList<Pieces> pieces = new ArrayList<>();

    public CustomGridViewAdapter(Context context, int layoutResourceId, ArrayList<Pieces> pieces){
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

        Pieces piece = pieces.get(position);
        //holder.imageItem.setImageBitmap(piece.getImage());
        return row;
    }

    static class RecordHolder{
        ImageView imageItem;
    }
}
