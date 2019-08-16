package com.example.eng;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterReadCategoryFragment extends RecyclerView.Adapter<AdapterReadCategoryFragment.ViewHolder> {

    private final ArrayList<Listakategorii> mValues;
    private Click mclick;



    public AdapterReadCategoryFragment(ArrayList<Listakategorii> items, Click m) {
        mValues = items;
        mclick=m;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_pozycja, parent, false);
        return new ViewHolder(view,mclick);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Listakategorii current = mValues.get(position);
        holder.nazwak.setText(current.getNazwa());

        /*holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    //mListener.onListFragmentInteraction(holder.current);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public final TextView nazwak;
        Click click;

        public ViewHolder(View view,Click click) {
            super(view);
            mView = view;
            nazwak= (TextView) view.findViewById(R.id.content);
            this.click=click;
            view.setOnClickListener(this);


        }

        @Override
        public String toString() {
            return super.toString() + " '" + nazwak.getText() + "'";
        }

        @Override
        public void onClick(View view) {
            click.onClickKlikniecie(getAdapterPosition());
        }
    }
    public interface Click{
        void onClickKlikniecie(int position);
    }
}
