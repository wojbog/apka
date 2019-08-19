package com.example.eng;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class DeleteFragment extends Fragment {

    private EditText TxUserId;
    private String TAG="LOGDeleteFragment";
    View layout;


    public DeleteFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: called.");
        View view = inflater.inflate(R.layout.fragment_delete, container, false);
        layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) getActivity().findViewById(R.id.custom_toast_container));

        TxUserId = view.findViewById(R.id.editText2);
        Button dlbut = view.findViewById(R.id.delete);

        TxUserId.setText("");
        TxUserId.setHint("id");

        dlbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: called.");
                try
                {
                int id =Integer.parseInt(TxUserId.getText().toString());
                User user;
                user = MainActivity.baza.myDao().loadUserById(id);
                MainActivity.baza.myDao().deleteUsers(user);
                    zrobToast("Usunięto!");
                TxUserId.setText("");
                    Log.d(TAG, "onClick: Deleted.");
                }
                catch(Exception e)
                {
                    zrobToast("Nie ma takiego id!");
                    Log.d(TAG, "onClick: Exception Delete");
                }

            }
        });
        return view;
    }

    private void zrobToast(String coNapisac) {
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(coNapisac);

        Toast toast = new Toast(getContext());
        toast.setGravity(Gravity.CENTER, 0, -350);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

}
