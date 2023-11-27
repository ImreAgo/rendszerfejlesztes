package com.example.recept2;

import android.content.Intent;
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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.recept2.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.List;

public class  FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    DBHelper db;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void receptek(String kategoria, List<Recept> receptek, LinearLayout verticalDatas){

        if(kategoria.isEmpty()){
            for(Recept r : receptek){
                TextView v = new TextView(getContext());

                v.setId(r.getId());
                v.setText(r.getNev());
                v.setTextSize(20);
                v.setPadding(100,30,0,30);

                switch(r.getKategoria()){
                    case "Leves": v.setText("🍲"+ r.getNev()); break;
                    case "Főétel": v.setText("🍛"+ r.getNev()); break;
                    case "Köret": v.setText("🍚"+ r.getNev()); break;
                    case "Desszert": v.setText("🍰"+ r.getNev()); break;
                }

                verticalDatas.addView(v);

                v.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        int id = v.getId();

                        Bundle result = new Bundle();
                        result.putString("id", String.valueOf(id));
                        getParentFragmentManager().setFragmentResult("dataFromFirst", result);

                        NavHostFragment.findNavController(FirstFragment.this)
                                .navigate(R.id.action_FirstFragment_to_fragment_third);
                    }
                });
            }
        }
        else{
            verticalDatas.removeViews(1,verticalDatas.getChildCount()-1);

            List<Recept> kategoriak = db.getReceptbyKategoria(kategoria);

            for (Recept r : kategoriak) {
                TextView recept = new TextView(getContext());

                recept.setId(r.getId());
                recept.setText(r.getNev());
                recept.setTextSize(20);
                recept.setPadding(100,30,0,30);

                switch(r.getKategoria()){
                    case "Leves": recept.setText("🍲"+ r.getNev()); break;
                    case "Főétel": recept.setText("🍛"+ r.getNev()); break;
                    case "Köret": recept.setText("🍚"+ r.getNev()); break;
                    case "Desszert": recept.setText("🍰"+ r.getNev()); break;
                }

                verticalDatas.addView(recept);

                recept.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        int id = recept.getId();

                        Bundle result = new Bundle();
                        result.putString("id", String.valueOf(id));
                        getParentFragmentManager().setFragmentResult("dataFromFirst", result);

                        NavHostFragment.findNavController(FirstFragment.this)
                                .navigate(R.id.action_FirstFragment_to_fragment_third);
                    }
                });
            }
        }
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = new DBHelper(getContext());

        List<Recept> receptek = db.getRecept();

        LinearLayout verticalDatas = view.findViewById(R.id.verticalLayout);

        SearchView sv = view.findViewById(R.id.searchView);

        Button levesek = view.findViewById(R.id.button);
        Button foetelek = view.findViewById(R.id.button2);
        Button koretek = view.findViewById(R.id.button3);
        Button desszertek = view.findViewById(R.id.button4);

        receptek("", receptek, verticalDatas);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                verticalDatas.removeViews(1, verticalDatas.getChildCount()-1);
                if(query.isEmpty()){
                    receptek("",receptek, verticalDatas);
                }
                else {
                    for (Recept r : receptek) {
                        if (r.getNev().contains(query)) {
                            TextView v = new TextView(getContext());

                            v.setId(r.getId());
                            v.setText(r.getNev());
                            v.setTextSize(20);
                            v.setPadding(100, 30, 0, 30);

                            switch (r.getKategoria()) {
                                case "Leves":
                                    v.setText("🍲" + r.getNev());
                                    break;
                                case "Főétel":
                                    v.setText("🍛" + r.getNev());
                                    break;
                                case "Köret":
                                    v.setText("🍚" + r.getNev());
                                    break;
                                case "Desszert":
                                    v.setText("🍰" + r.getNev());
                                    break;
                            }

                            verticalDatas.addView(v);

                            v.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    int id = v.getId();

                                    Bundle result = new Bundle();
                                    result.putString("id", String.valueOf(id));
                                    getParentFragmentManager().setFragmentResult("dataFromFirst", result);

                                    NavHostFragment.findNavController(FirstFragment.this)
                                            .navigate(R.id.action_FirstFragment_to_fragment_third);
                                }
                            });
                        }
                    }
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        levesek.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                receptek("Leves", receptek, verticalDatas);
            }
        });

        foetelek.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                receptek("Főétel", receptek, verticalDatas);
            }
        });

        koretek.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                receptek("Köret", receptek, verticalDatas);
            }
        });

        desszertek.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                receptek("Desszert", receptek, verticalDatas);
            }
        });
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
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