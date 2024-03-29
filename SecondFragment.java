package com.example.recept2;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.recept2.databinding.FragmentSecondBinding;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    DBHelper db;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    int id = 0;



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        Button save=view.findViewById(R.id.button7);
        ConstraintLayout llMain = view.findViewById(R.id.cl);

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                db = new DBHelper(getContext());

                EditText nev = view.findViewById(R.id.editTextText);
                EditText hanyfo = view.findViewById(R.id.editTextText2);
                EditText leiras = view.findViewById(R.id.editTextText3);
                EditText alapanyagok = view.findViewById(R.id.editTextText4);

                Boolean checkinsertdata = db.insertRecept(nev.getText().toString(), new Blob() {
                    @Override
                    public long length() throws SQLException {
                        return 0;
                    }

                    @Override
                    public byte[] getBytes(long pos, int length) throws SQLException {
                        return new byte[0];
                    }

                    @Override
                    public InputStream getBinaryStream() throws SQLException {
                        return null;
                    }

                    @Override
                    public long position(byte[] pattern, long start) throws SQLException {
                        return 0;
                    }

                    @Override
                    public long position(Blob pattern, long start) throws SQLException {
                        return 0;
                    }

                    @Override
                    public int setBytes(long pos, byte[] bytes) throws SQLException {
                        return 0;
                    }

                    @Override
                    public int setBytes(long pos, byte[] bytes, int offset, int len) throws SQLException {
                        return 0;
                    }

                    @Override
                    public OutputStream setBinaryStream(long pos) throws SQLException {
                        return null;
                    }

                    @Override
                    public void truncate(long len) throws SQLException {

                    }

                    @Override
                    public void free() throws SQLException {

                    }

                    @Override
                    public InputStream getBinaryStream(long pos, long length) throws SQLException {
                        return null;
                    }
                }, leiras.getText().toString(), Integer.parseInt(hanyfo.getText().toString()), alapanyagok.getText().toString(), "Főétel");

                if(checkinsertdata){
                    Toast.makeText(getContext(), "New Entry inserted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getContext(), "New Entry Not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });


        /*plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText textView = new EditText(getContext());
                id++;


                    ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );
                    textView.setId(id);
                    textView.setTextColor(Color.GREEN);
                    textView.setText("aa");
                    textView.setLayoutParams(params);
                    llMain.addView(textView);

                    ConstraintSet constraintSet = new ConstraintSet();
                    constraintSet.clone(llMain);

                constraintSet.connect(textView.getId(), ConstraintSet.TOP, R.id.editTextText4, ConstraintSet.BOTTOM, 18);
                constraintSet.connect(textView.getId(), ConstraintSet.START, llMain.getId(), ConstraintSet.START, 18);
                    constraintSet.applyTo(llMain);


            }
        });*/

    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}