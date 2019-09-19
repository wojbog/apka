package a.b.TEA;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class ZolodkiFragment extends Fragment implements View.OnClickListener {

    View view;
    String kategoria, dzien, godzina;

    List<User> users1,users2,users3,users4,users5,listaZastepcza;
    User listazGodzina;
    Date date;
    DateFormat dateFormat1;
    DateFormat dateFormat2;

    public ZolodkiFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_zolodki, container, false);


        listazGodzina = MainActivity.bazaKategorii.myDao().loadData();

        date = Calendar.getInstance().getTime();
        dateFormat1 = new SimpleDateFormat("dd");
        dateFormat2 = new SimpleDateFormat("hh");
        dzien = dateFormat1.format(date);
        godzina = dateFormat2.format(date);

        boolean cztTenSamDzien = listazGodzina.getSurname().equals(dzien);

        if (!cztTenSamDzien) {
            listaZastepcza = MainActivity.bazaZolodkowaZastepcza.myDao().getUsers();

            for (User s : listaZastepcza) {
                User use = new User();
                use.setCategory(s.getCategory());
                use.setZolodek(s.getZolodek());
                use.setSurname(s.getSurname());
                use.setName(s.getName());
                MainActivity.bazaZolodkowa.myDao().addUser(use);
                MainActivity.bazaZolodkowaZastepcza.myDao().deleteUsers(s);
            }

            User user = new User();
            Date date1 = Calendar.getInstance().getTime();
            DateFormat dateFormat3 = new SimpleDateFormat("dd");
            DateFormat dateFormat4 = new SimpleDateFormat("hh");
            String dzien1 = dateFormat3.format(date1);
            String godzina1 = dateFormat4.format(date1);
            user.setName(godzina1);
            user.setSurname(dzien1);
            user.setCategory("Data");
            MainActivity.bazaKategorii.myDao().deleteUsers(listazGodzina);
            MainActivity.bazaKategorii.myDao().addUser(user);
        }else{

        }


        users1 = MainActivity.bazaZolodkowa.myDao().loadUsersByZolodek("1");
        users2 = MainActivity.bazaZolodkowa.myDao().loadUsersByZolodek("2");
        users3 = MainActivity.bazaZolodkowa.myDao().loadUsersByZolodek("3");
        users4 = MainActivity.bazaZolodkowa.myDao().loadUsersByZolodek("4");
        users5 = MainActivity.bazaZolodkowa.myDao().loadUsersByZolodek("5");

//        String slowka, slowek;
        String poprawnaodmiana1;
        if (users1.size()==1) poprawnaodmiana1="słówko";
        else if (users1.size()>4&&users1.size()<22) poprawnaodmiana1="słówek";
        else if (users1.size()%10==2||users1.size()%10==3||users1.size()%10==4) poprawnaodmiana1="słówka";
        else poprawnaodmiana1="słówek";
        String poprawnaodmiana2;
        if (users2.size()==1) poprawnaodmiana2="słówko";
        else if (users2.size()>4&&users2.size()<22) poprawnaodmiana2="słówek";
        else if (users2.size()%10==2||users2.size()%10==3||users2.size()%10==4) poprawnaodmiana2="słówka";
        else poprawnaodmiana2="słówek";
        String poprawnaodmiana3;
        if (users3.size()==1) poprawnaodmiana3="słówko";
        else if (users3.size()>4&&users3.size()<22) poprawnaodmiana3="słówek";
        else if (users3.size()%10==2||users3.size()%10==3||users3.size()%10==4) poprawnaodmiana3="słówka";
        else poprawnaodmiana3="słówek";
        String poprawnaodmiana4;
        if (users4.size()==1) poprawnaodmiana4="słówko";
        else if (users4.size()>4&&users4.size()<22) poprawnaodmiana4="słówek";
        else if (users4.size()%10==2||users4.size()%10==3||users4.size()%10==4) poprawnaodmiana4="słówka";
        else poprawnaodmiana4="słówek";
        String poprawnaodmiana5;
        if (users5.size()==1) poprawnaodmiana5="słówko";
        else if (users5.size()>4&&users5.size()<22) poprawnaodmiana5="słówek";
        else if (users5.size()%10==2||users5.size()%10==3||users5.size()%10==4) poprawnaodmiana5="słówka";
        else poprawnaodmiana5="słówek";

        Button zolodek1Btn = view.findViewById(R.id.zolodek1);
        zolodek1Btn.setOnClickListener(this);
        zolodek1Btn.setText(String.format(Locale.getDefault(), "%s\n%d %s", getResources().getString(R.string._1_stopien_nauczania), users1.size(), poprawnaodmiana1));
        Button zolodek2Btn = view.findViewById(R.id.zolodek2);
        zolodek2Btn.setOnClickListener(this);
        zolodek2Btn.setText(String.format(Locale.getDefault(), "%s\n%d %s", getResources().getString(R.string._2_stopien_nauczania), users2.size(), poprawnaodmiana2));
        Button zolodek3Btn = view.findViewById(R.id.zolodek3);
        zolodek3Btn.setOnClickListener(this);
        zolodek3Btn.setText(String.format(Locale.getDefault(), "%s\n%d %s", getResources().getString(R.string._3_stopien_nauczania), users3.size(), poprawnaodmiana3));
        Button zolodek4Btn = view.findViewById(R.id.zolodek4);
        zolodek4Btn.setOnClickListener(this);
        zolodek4Btn.setText(String.format(Locale.getDefault(), "%s\n%d %s", getResources().getString(R.string._4_stopien_nauczania), users4.size(), poprawnaodmiana4));
        Button zolodek5Btn = view.findViewById(R.id.zolodek5);
        zolodek5Btn.setOnClickListener(this);
        zolodek5Btn.setText(String.format(Locale.getDefault(), "%s\n%d %s", getResources().getString(R.string._5_stopien_nauczania), users5.size(), poprawnaodmiana5));


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
                            .replace(R.id.stefan, new ZolodekNumerFragment("1"))
                            .addToBackStack(null)
                            .commit();
                }else zrobToast("Nie ma więcej słówek");
                break;
            case R.id.zolodek2:
                if (users2.size() > 0) {
                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                    android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new ZolodekNumerFragment("2"))
                            .addToBackStack(null)
                            .commit();
                }else zrobToast("Nie ma więcej słówek");
                break;
            case R.id.zolodek3:
                if (users3.size() > 0) {
                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                    android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new ZolodekNumerFragment("3"))
                            .addToBackStack(null)
                            .commit();
                }else zrobToast("Nie ma więcej słówek");
                break;
            case R.id.zolodek4:
                if (users4.size() > 0) {
                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                    android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new ZolodekNumerFragment("4"))
                            .addToBackStack(null)
                            .commit();
                }else zrobToast("Nie ma więcej słówek");
                break;
            case R.id.zolodek5:
                if (users5.size() > 0) {
                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                    android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new ZolodekNumerFragment("5"))
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
