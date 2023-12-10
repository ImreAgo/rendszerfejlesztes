package com.example.recept2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

import com.example.recept2.databinding.FragmentFifthBinding;
import com.example.recept2.databinding.FragmentFirstBinding;

import java.util.List;

public class FifthFragment extends Fragment {

    private FragmentFifthBinding binding;

    DBHelper db;

    String hozzavalok_string = "";

    private static String[][] splitter (String input) {

        String[] hozzavalok = input.split(";");
        int n = hozzavalok.length;
        String[][] minden = new String[n][3];
        for(int i = 0; i < hozzavalok.length; i++) {
            String[] hozzavalo = hozzavalok[i].split(",");
            minden[i][0] = hozzavalo[0];
            minden[i][1] = hozzavalo[1];
            minden[i][2] = hozzavalo[2];
        }
        return minden;
    }

    private static String calculator(String mennyiseg, int hanyfo){
        int m = Integer.parseInt(mennyiseg);
        double d = Double.valueOf(mennyiseg);

        return String.valueOf(d/hanyfo);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFifthBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = new DBHelper(getContext());

        getParentFragmentManager().setFragmentResultListener("calculatorId", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                int id = Integer.parseInt(result.getString("id"));
                //Toast.makeText(getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();

                Recept r = db.getReceptById(id);

                String[][] hozzavalok = splitter(r.getAlapanyagok());

                TextView hozzavalo = view.findViewById(R.id.textView15);
                EditText hany = view.findViewById(R.id.editTextText5);
                Button calc = view.findViewById(R.id.button10);

                int hanyfo = Integer.parseInt(hany.getText().toString());

                for(int i = 0; i<hozzavalok.length; i++){
                    //hozzavalok_string += hozzavalok[i][0] + " " + hozzavalok[i][1]+hozzavalok[i][2]+System.lineSeparator();
                    hozzavalok_string += hozzavalok[i][0] + " " + calculator(hozzavalok[i][1], hanyfo)+hozzavalok[i][2]+System.lineSeparator();
                    //hozzavalo.setText(hozzavalok[i][0] + " " + hozzavalok[i][1]+hozzavalok[i][3]);
                }
                //Toast.makeText(getContext(), hozzavalok_string, Toast.LENGTH_SHORT).show();
                hozzavalo.setText(hozzavalok_string);
                hozzavalok_string = "";


                calc.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        int hanyfo_uj = Integer.parseInt(hany.getText().toString());
                        for(int i = 0; i<hozzavalok.length; i++){
                            //hozzavalok_string += hozzavalok[i][0] + " " + hozzavalok[i][1]+hozzavalok[i][2]+System.lineSeparator();
                            hozzavalok_string += hozzavalok[i][0] + " " + calculator(hozzavalok[i][1], hanyfo_uj)+hozzavalok[i][2]+System.lineSeparator();
                            //hozzavalo.setText(hozzavalok[i][0] + " " + hozzavalok[i][1]+hozzavalok[i][3]);
                        }
                        //Toast.makeText(getContext(), hozzavalok_string, Toast.LENGTH_SHORT).show();
                        hozzavalo.setText(hozzavalok_string);
                    }
                });
            }
        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FifthFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}