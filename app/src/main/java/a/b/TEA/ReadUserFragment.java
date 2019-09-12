package a.b.TEA;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eng.R;

import java.util.List;


public class ReadUserFragment extends Fragment {

    private RecyclerView recyclerView;
    public RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    List<User> andrzej;

    String kategoria;
    View view;
    boolean vi;



    TextView textView;
String TAG="ReadUserFragment";


    public ReadUserFragment(String k, boolean visibility) {
        kategoria = k;
        vi = visibility;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_read_user, container, false);
        final List<User> users;

        if (!kategoria.equals("Wybierz Kategorię")) {
            users = MainActivity.baza.myDao().loadUserByKategoria(kategoria);
        }else users = MainActivity.baza.myDao().loadUserOrderByKategoria();

        andrzej=users;

//        AdView mAdView;
//        mAdView = view.findViewById(R.id.adViewReadUser);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        recyclerView = view.findViewById(R.id.rower);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        mAdapter = new Adapterek(andrzej);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);


       new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                try
                {
                   int id= viewHolder.getAdapterPosition();
                    User user= andrzej.get(id);
                   andrzej.remove(id);
                    mAdapter.notifyItemRemoved(id);
                    //user = MainActivity.baza.myDao().loadUserById(id);
                   MainActivity.baza.myDao().deleteUsers(user);

//                    Toast.makeText(getContext() ,"Usunięto",Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.custom_toast,
                            (ViewGroup) view.findViewById(R.id.custom_toast_container));
                    TextView text = layout.findViewById(R.id.text);
                    text.setText("Usunięto");
                    Toast toast = new Toast(getContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();

                }
                catch(Exception e)
                {
                    Toast.makeText(getContext() ,"Nie ma takiego id!",Toast.LENGTH_LONG).show();

                }
            }
        }).attachToRecyclerView(recyclerView);

    return view;
    }

}
