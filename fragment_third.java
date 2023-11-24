package com.example.recept2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_third#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_third extends Fragment {

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


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_third() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_third.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_third newInstance(String param1, String param2) {
        fragment_third fragment = new fragment_third();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }
}