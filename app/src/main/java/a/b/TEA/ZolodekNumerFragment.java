package a.b.TEA;


import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;
import java.util.Random;


public class ZolodekNumerFragment extends Fragment {

    private View view;
    private TextView kartaTVZ;
    private Button umiemBtn, nieumiemBtn;
    private Animation animationMove = null;

    private Random random = new Random();
    private List<User> users;

    private boolean odwrocone;
    private String imie, nazwisko;
    private int ktoryZoladek, ustawZolodek, los, t=0;
    private int[] losy;

    public ZolodekNumerFragment(int ktory) {
        ktoryZoladek = ktory;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_zolodek_numer, container, false);
        Log.d("niewiem", "onCreateView: "+ktoryZoladek);
        users = MainActivity.baza.myDao().loadUsersByZolodek(ktoryZoladek);

        losy = new int[users.size()];

        final Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);
        final Animation animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.rotate2);
        animationMove = AnimationUtils.loadAnimation(getContext(), R.anim.move);

        for (int i = 0; i < users.size(); i++) {
            losy[i]=0;
        }

        losuj();

        kartaTVZ = view.findViewById(R.id.kartaTVZ);
        kartaTVZ.setText(imie);
        umiemBtn = view.findViewById(R.id.umiemBtnZ);
        nieumiemBtn = view.findViewById(R.id.nieUmiemBtnZ);

        kartaTVZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                los = random.nextInt(users.size());
                if (!odwrocone) {
                    kartaTVZ.startAnimation(animation);
                    kartaTVZ.setText(nazwisko);
                    kartaTVZ.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.fiszkishapereversed));
                    odwrocone=true;
                }else {
                    kartaTVZ.startAnimation(animation2);
                    kartaTVZ.setText(imie);
                    kartaTVZ.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.fiszkishape));
                    odwrocone=false;
                }
            }
        });

        umiemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ktoryZoladek == 5) {
                    MainActivity.baza.myDao().deleteUsers(users.get(los));
                } else {
                    ustawZolodek = ktoryZoladek+1;
                    users.get(los).setZolodek(String.format(Locale.getDefault(),"%d",ustawZolodek));
                    MainActivity.baza.myDao().updateUser(users.get(los));
                }

                losuj();
                kolejne();
            }
        });

        nieumiemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                losuj();
                kolejne();
            }
        });

        return view;
    }

    void losuj(){
        if (t == users.size()) {
            MainActivity.fragmentManager.popBackStack();
            zrobToast("To już wszystkie słówka");
        }else {
            do {
                los = random.nextInt(users.size());
            } while (losy[los] == 1);
            t++;
            losy[los] = 1;
            imie = users.get(los).getName();
            nazwisko = users.get(los).getSurname();
        }
    }

    void kolejne(){
        if (!odwrocone) {
            kartaTVZ.startAnimation(animationMove);
            kartaTVZ.setText(imie);
        }else {
            kartaTVZ.startAnimation(animationMove);
            kartaTVZ.setText(imie);
            kartaTVZ.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.fiszkishape));
            odwrocone=false;
        }
    }

    private void zrobToast(String coNapisac) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) view.findViewById(R.id.custom_toast_container));
        TextView text = layout.findViewById(R.id.text);
        text.setText(coNapisac);

        Toast toast = new Toast(getContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

}
