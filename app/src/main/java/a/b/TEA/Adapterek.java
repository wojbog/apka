package a.b.TEA;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class Adapterek extends RecyclerView.Adapter<Adapterek.ExampleViewHolder> {
  private List<User> mlist;
    static class ExampleViewHolder extends RecyclerView.ViewHolder {
       TextView jedynka, dwojka,kolejny,ijeszczejeden;

       ExampleViewHolder(View itemView) {
           super(itemView);
           jedynka = itemView.findViewById(R.id.nad);
           dwojka = itemView.findViewById(R.id.pod);
           kolejny = itemView.findViewById(R.id.numerek);
//           ijeszczejeden = itemView.findViewById(R.id.zolo);
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
    holder.kolejny.setText(currentItem.getCategory());
//    holder.ijeszczejeden.setText(String.format("Stopień nauczenia: %s", currentItem.getZolodek()));
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
