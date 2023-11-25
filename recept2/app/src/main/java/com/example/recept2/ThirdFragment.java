package com.example.recept2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recept2.databinding.FragmentFirstBinding;
import com.example.recept2.databinding.FragmentThirdBinding;


public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;

    DBHelper db;
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentThirdBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = new DBHelper(getContext());

        getParentFragmentManager().setFragmentResultListener("dataFromFirst", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                int id = Integer.parseInt(result.getString("id"));

                Toast.makeText(getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();
                Recept r = db.getReceptById(id);

                TextView nev = view.findViewById(R.id.textView7);
                TextView hanyfo = view.findViewById(R.id.textView8);

                nev.setText(r.getNev());
                hanyfo.setText(r.getHanyfo()+" fő");
            }
        });
    }
}