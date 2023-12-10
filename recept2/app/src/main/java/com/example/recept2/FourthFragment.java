package com.example.recept2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.recept2.databinding.FragmentFourthBinding;
import com.example.recept2.databinding.FragmentSecondBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FourthFragment extends Fragment {

    private FragmentFourthBinding binding;

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

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        //Toast.makeText(getContext(), "4", Toast.LENGTH_SHORT).show();
        binding = FragmentFourthBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    int pictureId;
    int recept_id;

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = new DBHelper(getContext());

        Button update=view.findViewById(R.id.button7);
        ConstraintLayout llMain = view.findViewById(R.id.cl);

        Button cam = view.findViewById(R.id.button5);

        cam.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent open_cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(open_cam, 100);
            }
        });

        getParentFragmentManager().setFragmentResultListener("updateId", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                Toast.makeText(getContext(), "Beléptem ide", Toast.LENGTH_SHORT).show();
                recept_id = Integer.parseInt(result.getString("id"));



                //Toast.makeText(getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();
                Recept r = db.getReceptById(recept_id);

                //File file=new File(Environment.getExternalStorageDirectory(),"DCIM");

                //Toast.makeText(getContext(), pictures[10].getAbsolutePath(), Toast.LENGTH_SHORT).show();

                EditText nev = view.findViewById(R.id.editTextText);
                EditText hanyfo = view.findViewById(R.id.editTextText2);
                EditText leiras = view.findViewById(R.id.editTextText3);
                EditText alapanyagok = view.findViewById(R.id.editTextText4);

                RadioGroup kateg = view.findViewById(R.id.kategoriak);

                int kategoria_id = kateg.getCheckedRadioButtonId();

                RadioButton b = view.findViewById(kategoria_id);

                nev.setText(r.getNev());
                //hanyfo.setText(r.getHanyfo());
                leiras.setText(r.getLeiras());
                alapanyagok.setText(r.getAlapanyagok());
                //b.setChecked(true);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //db = new DBHelper(getContext());

                EditText nev = view.findViewById(R.id.editTextText);
                EditText hanyfo = view.findViewById(R.id.editTextText2);
                EditText leiras = view.findViewById(R.id.editTextText3);
                EditText alapanyagok = view.findViewById(R.id.editTextText4);

                RadioGroup kateg = view.findViewById(R.id.kategoriak);

                int id = kateg.getCheckedRadioButtonId();

                RadioButton b = view.findViewById(id);

                if(nev.getText().toString().isEmpty() || hanyfo.getText().toString().isEmpty()
                        || leiras.getText().toString().isEmpty() || alapanyagok.getText().toString().isEmpty() || b.getText().toString().isEmpty()){
                    TextView hiba = view.findViewById(R.id.textView12);

                    hiba.setVisibility(view.VISIBLE);

                    return;
                }

                Boolean checkinsertdata;
                try {
                    checkinsertdata = db.updateRecept(recept_id, nev.getText().toString(),  leiras.getText().toString(), Integer.parseInt(hanyfo.getText().toString()),
                            alapanyagok.getText().toString(), b.getText().toString());

                    if (checkinsertdata) {
                        Toast.makeText(getContext(), "A recept frissítése megtörtént.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "A recept frissítése nem sikerült.", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(Exception e){
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap photo = (Bitmap)data.getExtras().get("data");

        db = new DBHelper(getContext());

        pictureId = db.getRecept().size()+1;

        File filename=new File(Environment.getExternalStorageDirectory(),"DCIM/"+pictureId+".png");

        try (FileOutputStream out = new FileOutputStream(filename)) {
            photo.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}