package com.example.eng;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeleteFragment extends Fragment {

    private EditText TxUserId;
    private Button Dlbut;


    public DeleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete, container, false);
        TxUserId = view.findViewById(R.id.editText2);
        Dlbut=view.findViewById(R.id.delete);

        TxUserId.setText("");

        Dlbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id =Integer.parseInt(TxUserId.getText().toString());
                User user = new User();

                try
                {
                user = MainActivity.baza.myDao().loadUserById(id);
                MainActivity.baza.myDao().deleteUsers(user);
                Toast.makeText(getActivity(),"Usunięty",Toast.LENGTH_SHORT).show();
                TxUserId.setText("");
                }
                catch(Exception e)
                {
                    Toast.makeText(getContext(), "nie ma takiego id", Toast.LENGTH_LONG).show();
                }

            }
        });
        return view;
    }

}
