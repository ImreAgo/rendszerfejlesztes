package com.example.recept2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.recept2.databinding.FragmentFirstBinding;

import java.util.List;

public class FirstFragment extends Fragment {

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

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = new DBHelper(getContext());

        List<Recept> receptek = db.getRecept();

        ConstraintLayout llMain = view.findViewById(R.id.cl1);

        LinearLayout verticalDatas = view.findViewById(R.id.verticalLayout);

        Button levesek = view.findViewById(R.id.button);
        Button foetelek = view.findViewById(R.id.button2);
        Button koretek = view.findViewById(R.id.button3);
        Button desszertek = view.findViewById(R.id.button4);

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

            /*ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(llMain);

            constraintSet.connect(v.getId(), ConstraintSet.TOP, R.id.linearLayout, ConstraintSet.BOTTOM, 18);
            constraintSet.connect(v.getId(), ConstraintSet.START, llMain.getId(), ConstraintSet.START, 18);
            constraintSet.applyTo(llMain);*/
        }

        levesek.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String kategoria = "Leves";

                verticalDatas.removeAllViews();

                List<Recept> levesek = db.getReceptbyKategoria(kategoria);

                for (Recept r : levesek) {
                    TextView recept = new TextView(getContext());

                    recept.setId(r.getId());
                    recept.setText(r.getNev());

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
        });

        foetelek.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String kategoria = "Főétel";

                verticalDatas.removeAllViews();

                List<Recept> foetelek = db.getReceptbyKategoria(kategoria);

                for (Recept r : foetelek) {
                    TextView recept = new TextView(getContext());

                    recept.setId(r.getId());
                    recept.setText(r.getNev());

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
        });

        koretek.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String kategoria = "Köret";

                verticalDatas.removeAllViews();

                List<Recept> koretek = db.getReceptbyKategoria(kategoria);

                for (Recept r : koretek) {
                    TextView recept = new TextView(getContext());

                    recept.setId(r.getId());
                    recept.setText(r.getNev());

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
        });

        desszertek.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String kategoria = "Desszert";

                verticalDatas.removeAllViews();

                List<Recept> desszertek = db.getReceptbyKategoria(kategoria);

                for (Recept r : desszertek) {
                    TextView recept = new TextView(getContext());

                    recept.setId(r.getId());
                    recept.setText(r.getNev());

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