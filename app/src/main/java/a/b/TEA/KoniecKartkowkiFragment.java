package a.b.TEA;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class KoniecKartkowkiFragment extends Fragment {

    private String dobrych, zlych, kategoria;
    boolean vi;

    public KoniecKartkowkiFragment(String dobryh, String zlyh, String kateg, boolean visibility) {
        dobrych = dobryh;
        zlych = zlyh;
        kategoria = kateg;
        vi=visibility;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_koniec_kartkowki, container, false);


//        AdView mAdView;
//        mAdView = view.findViewById(R.id.adViewKoniecKArtkowki);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        TextView dobrychTVkoniec = view.findViewById(R.id.dobrychTVkoniec);
        TextView zlychTVkoniec = view.findViewById(R.id.zlychTVkoniec);
        Button zakoncz = view.findViewById(R.id.zakonczBtn);
        dobrychTVkoniec.setText(dobrych);
        zlychTVkoniec.setText(zlych);

        zakoncz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fragmentManager.popBackStack();
                MainActivity.fragmentManager.popBackStack();
                MainActivity.fragmentManager.popBackStack();
                MainActivity.fragmentManager.popBackStack();
            }
        });

        return view;
    }

}
