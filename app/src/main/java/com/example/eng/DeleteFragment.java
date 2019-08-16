package com.example.eng;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class DeleteFragment extends Fragment {

    private EditText TxUserId;
    private Button Dlbut;
    private String TAG="LOGDeleteFragment";


    public DeleteFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: called.");
        View view = inflater.inflate(R.layout.fragment_delete, container, false);
        TxUserId = view.findViewById(R.id.editText2);
        Dlbut=view.findViewById(R.id.delete);

        TxUserId.setText("");
        TxUserId.setHint("id");

        Dlbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: called.");
                try
                {
                int id =Integer.parseInt(TxUserId.getText().toString());
                User user = new User();
                user = MainActivity.baza.myDao().loadUserById(id);
                MainActivity.baza.myDao().deleteUsers(user);
                    Snackbar.make(view, "Usunięto", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                TxUserId.setText("");
                    Log.d(TAG, "onClick: Deleted.");
                }
                catch(Exception e)
                {
                    Snackbar.make(view, "Nie ma takiego id!", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();
                    Log.d(TAG, "onClick: Exception Delete");
                }

            }
        });
        return view;
    }

}
