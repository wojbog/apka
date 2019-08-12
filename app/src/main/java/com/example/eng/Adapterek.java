package com.example.eng;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapterek extends RecyclerView.Adapter<Adapterek.ExampleViewHolder> {
  private ArrayList<ExampleItem> mlist;
    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
       public TextView jedynka, dwojka,kolejny;

       public ExampleViewHolder(View itemView) {
           super(itemView);
           jedynka = itemView.findViewById(R.id.nad);
           dwojka = itemView.findViewById(R.id.pod);
           kolejny = itemView.findViewById(R.id.numerek);
       }
   }
   public Adapterek(ArrayList<ExampleItem> lista){
        mlist=lista;
   }

    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        ExampleViewHolder tak = new ExampleViewHolder (v);
        return tak;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
    ExampleItem currentItem = mlist.get(position);
    holder.jedynka.setText(currentItem.getText1());
    holder.dwojka.setText(currentItem.getText2());
    holder.kolejny.setText(currentItem.getNumer());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }



}
