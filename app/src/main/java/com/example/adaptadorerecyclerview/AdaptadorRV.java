package com.example.adaptadorerecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorRV extends RecyclerView.Adapter<ModeloLinha> {

    private final List<Calculo> list;
    private Context activityExecucao;
    public EventoDeClickCustomizado<Calculo> eventoClickDeletar = null;
    public EventoDeClickCustomizado<Calculo> eventoClickLinha = null;

    public List<Calculo> getList(){
        return list;
    }
    public AdaptadorRV(Calculo[] valores) {
        list = new ArrayList<>();
        for (Calculo c : valores) {
            list.add(c);
        }
    }
    public ModeloLinha onCreateViewHolder(ViewGroup parent, int viewType) {
        activityExecucao = parent.getContext();
        ModeloLinha holder = new ModeloLinha(LayoutInflater.from(activityExecucao).inflate(R.layout.modelo_linha, parent,false));
        return holder;
    }
    public void onBindViewHolder(ModeloLinha holder, int position) {

        final Object n1OB = list.get(position).number1;
        final Object n2OB = list.get(position).number2;
        final Object opOB = list.get(position).opereation;
        final Object resOB = list.get(position).result;

        holder.Number1.setText(n1OB.toString());
        holder.Number2.setText(n2OB.toString());
        holder.operations.setText(opOB.toString());
        holder.result.setText(resOB.toString());

        if (eventoClickDeletar != null)
            holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventoClickDeletar.onItemClick(list.get(position));
                }
            });
        if(eventoClickLinha != null)
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    eventoClickLinha.onItemClick(list.get(position));
                }
            });

    }
    public int getItemCount () {
        return list!=null ? list.size():0;
    }

}
