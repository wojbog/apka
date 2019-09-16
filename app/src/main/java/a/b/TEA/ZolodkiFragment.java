package a.b.TEA;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;


public class ZolodkiFragment extends Fragment implements View.OnClickListener {

    View view;
    String kategoria;
    List<User> users1;
    List<User> users2;
    List<User> users3;
    List<User> users4;
    List<User> users5;

    public ZolodkiFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_zolodki, container, false);
        users1 = MainActivity.baza.myDao().loadUsersByZolodek(1);
        users2 = MainActivity.baza.myDao().loadUsersByZolodek(2);
        users3 = MainActivity.baza.myDao().loadUsersByZolodek(3);
        users4 = MainActivity.baza.myDao().loadUsersByZolodek(4);
        users5 = MainActivity.baza.myDao().loadUsersByZolodek(5);

        Button zolodek1Btn = view.findViewById(R.id.zolodek1);
        zolodek1Btn.setOnClickListener(this);
        zolodek1Btn.setText("Żołądek 1 - ".concat(String.format(Locale.getDefault(), "%d", users1.size())));
        Button zolodek2Btn = view.findViewById(R.id.zolodek2);
        zolodek2Btn.setOnClickListener(this);
        zolodek2Btn.setText("Żołądek 2 - ".concat(String.format(Locale.getDefault(), "%d", users2.size())));
        Button zolodek3Btn = view.findViewById(R.id.zolodek3);
        zolodek3Btn.setOnClickListener(this);
        zolodek3Btn.setText("Żołądek 3 - ".concat(String.format(Locale.getDefault(), "%d", users3.size())));
        Button zolodek4Btn = view.findViewById(R.id.zolodek4);
        zolodek4Btn.setOnClickListener(this);
        zolodek4Btn.setText("Żołądek 4 - ".concat(String.format(Locale.getDefault(), "%d", users4.size())));
        Button zolodek5Btn = view.findViewById(R.id.zolodek5);
        zolodek5Btn.setOnClickListener(this);
        zolodek5Btn.setText("Żołądek 5 - ".concat(String.format(Locale.getDefault(), "%d", users5.size())));


        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.zolodek1:
                if (users1.size() > 0) {
                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                    android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new ZolodekNumerFragment(1))
                            .addToBackStack(null)
                            .commit();
                }else zrobToast("Nie ma więcej słówek");
                break;
            case R.id.zolodek2:
                if (users2.size() > 0) {
                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                    android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new ZolodekNumerFragment(2))
                            .addToBackStack(null)
                            .commit();
                }else zrobToast("Nie ma więcej słówek");
                break;
            case R.id.zolodek3:
                if (users3.size() > 0) {
                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                    android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new ZolodekNumerFragment(3))
                            .addToBackStack(null)
                            .commit();
                }else zrobToast("Nie ma więcej słówek");
                break;
            case R.id.zolodek4:
                if (users4.size() > 0) {
                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                    android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new ZolodekNumerFragment(4))
                            .addToBackStack(null)
                            .commit();
                }else zrobToast("Nie ma więcej słówek");
                break;
            case R.id.zolodek5:
                if (users5.size() > 0) {
                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                    android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new ZolodekNumerFragment(5))
                            .addToBackStack(null)
                            .commit();
                }else zrobToast("Nie ma więcej słówek");
                break;
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
