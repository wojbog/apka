package com.example.eng;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Adapterek extends RecyclerView.Adapter<Adapterek.ExampleViewHolder> {
  private List<User> mlist;
    static class ExampleViewHolder extends RecyclerView.ViewHolder {
       TextView jedynka, dwojka,kolejny;

       ExampleViewHolder(View itemView) {
           super(itemView);
           jedynka = itemView.findViewById(R.id.nad);
           dwojka = itemView.findViewById(R.id.pod);
           kolejny = itemView.findViewById(R.id.numerek);
       }
   }
   Adapterek(List<User> lista){
        mlist=lista;
   }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
    User currentItem = mlist.get(position);
    holder.jedynka.setText(currentItem.getName());
    holder.dwojka.setText(currentItem.getSurname());
//    holder.kolejny.setText(String.format(Locale.getDefault(),"%d",currentItem.id));
    holder.kolejny.setText(currentItem.getCategory());
    }
public void setMlist(List<User> notes)
{
    mlist=notes;
   // notifyDataSetChanged();
}
    @Override
    public int getItemCount() {
        return mlist.size();
    }



}
