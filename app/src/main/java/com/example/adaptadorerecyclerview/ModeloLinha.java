package com.example.adaptadorerecyclerview;

import android.media.Image;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ModeloLinha extends RecyclerView.ViewHolder {
    public TextView Number1, Number2, operations, result;
    public ImageButton moreButton, deleteButton;


    public ModeloLinha(@NonNull View itemView) {
        super(itemView);
        Number1 = itemView.findViewById(R.id.n1);
        Number2 = itemView.findViewById(R.id.n2);
        operations = itemView.findViewById(R.id.op);
        result = itemView.findViewById(R.id.result);

        deleteButton = itemView.findViewById(R.id.excluir);
    }
}
