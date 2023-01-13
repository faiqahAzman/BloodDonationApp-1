package com.blooddonationapp.startactivity.Utils;

import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.blooddonationapp.startactivity.R;

public class requestViewHolder extends RecyclerView.ViewHolder{


    public TextView name,date,time, status;

    public requestViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        name = itemView.findViewById(R.id.RequestFragment_textView_name);
        date = itemView.findViewById(R.id.RequestFragment_textView_date);
        time = itemView.findViewById(R.id.RequestFragment_textView_time);
        status = itemView.findViewById(R.id.RequestFragment_textView_status);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(recyclerViewInterface !=null){
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION)
                        recyclerViewInterface.onItemClick(position);

                }
            }
        });

    }


}
