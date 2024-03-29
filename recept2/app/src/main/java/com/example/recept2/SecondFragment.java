package com.example.recept2;

import android.content.Intent;
import android.graphics.Bitmap;
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

import com.example.recept2.databinding.FragmentSecondBinding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    DBHelper db;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }
    int pictureId;

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button save=view.findViewById(R.id.button7);
        ConstraintLayout llMain = view.findViewById(R.id.cl);

        Button cam = view.findViewById(R.id.button5);

        cam.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent open_cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(open_cam, 100);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                db = new DBHelper(getContext());

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

                checkinsertdata = db.insertRecept(nev.getText().toString(), "DCIM/"+pictureId+".png", leiras.getText().toString(), Integer.parseInt(hanyfo.getText().toString()),
                        alapanyagok.getText().toString(), b.getText().toString());

                if(checkinsertdata){
                    Toast.makeText(getContext(), "A recept mentésre került.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getContext(), "A recept nem került mentésre.", Toast.LENGTH_SHORT).show();
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