package com.example.recept2;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.recept2.databinding.FragmentFirstBinding;
import com.example.recept2.databinding.FragmentThirdBinding;
import com.google.android.material.appbar.MaterialToolbar;

import java.io.File;


public class ThirdFragment extends Fragment {

    private FragmentThirdBinding binding;

    DBHelper db;

    int idToNext;

    File[] pictures;

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

    public String hozzavalok_string = "";

    private DialogInterface.OnClickListener dialogClickListener;

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

        ImageView kep = view.findViewById(R.id.imageView);

        Button update = view.findViewById(R.id.button6);
        Button delete = view.findViewById(R.id.button8);
        Button calculator = view.findViewById(R.id.button9);

        getParentFragmentManager().setFragmentResultListener("dataFromFirst", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                int id = Integer.parseInt(result.getString("id"));
                idToNext = Integer.parseInt(result.getString("id"));

                //Toast.makeText(getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();
                Recept r = db.getReceptById(id);

                File file=new File(Environment.getExternalStorageDirectory(),"DCIM");

                pictures=file.listFiles();

                //Toast.makeText(getContext(), pictures[10].getAbsolutePath(), Toast.LENGTH_SHORT).show();

                for(int i = 0; i<pictures.length;i++){
                    if(pictures[i].getAbsolutePath().contains("/"+String.valueOf(id)+".png")){
                        kep.setImageBitmap(BitmapFactory.decodeFile(pictures[i].getAbsolutePath()));

                    }
                }

                String[][] hozzavalok = splitter(r.getAlapanyagok());

                TextView nev = view.findViewById(R.id.textView7);
                TextView hanyfo = view.findViewById(R.id.textView8);
                TextView leiras = view.findViewById(R.id.textView11);
                TextView hozzavalo =  view.findViewById(R.id.textView14);

                nev.setText(r.getNev());
                hanyfo.setText(r.getHanyfo()+" fő");
                leiras.setText(r.getLeiras());

                int id2 = hozzavalok.length;

                for(int i = 0; i<id2; i++){
                    hozzavalok_string += hozzavalok[i][0] + " " + hozzavalok[i][1]+hozzavalok[i][2]+System.lineSeparator();
                    //hozzavalo.setText(hozzavalok[i][0] + " " + hozzavalok[i][1]+hozzavalok[i][3]);
                }
                //Toast.makeText(getContext(), hozzavalok_string, Toast.LENGTH_SHORT).show();
                hozzavalo.setText(hozzavalok_string);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int id = v.getId();

                Bundle result = new Bundle();
                result.putString("id", String.valueOf(idToNext));
                getParentFragmentManager().setFragmentResult("updateId", result);

                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_FourthFragment);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //int id = v.getId();

                dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            // on below line we are setting a click listener
                            // for our positive button
                            case DialogInterface.BUTTON_POSITIVE:
                                // on below line we are displaying a toast message.
                                Toast.makeText(getContext(), "Yes clicked", Toast.LENGTH_SHORT).show();
                                Boolean checkinsertdata;

                                checkinsertdata = db.deleteRecept(idToNext);

                                if(checkinsertdata){
                                    Toast.makeText(getContext(), "A recept törlésre került.", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(getContext(), "A recept nem került törlésre.", Toast.LENGTH_SHORT).show();
                                }
                                NavHostFragment.findNavController(ThirdFragment.this)
                                        .navigate(R.id.action_ThirdFragment_to_FirstFragment);
                                break;
                            // on below line we are setting click listener
                            // for our negative button.
                            case DialogInterface.BUTTON_NEGATIVE:
                                // on below line we are dismissing our dialog box.
                                dialog.dismiss();

                        }
                    }
                };
                // on below line we are creating a builder variable for our alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                // on below line we are setting message for our dialog box.
                builder.setMessage("Select yes to display toast message and no to dismiss the dialog ?")
                        // on below line we are setting positive button
                        // and setting text to it.
                        .setPositiveButton("Yes", dialogClickListener)
                        // on below line we are setting negative button
                        // and setting text to it.
                        .setNegativeButton("No", dialogClickListener)
                        // on below line we are calling
                        // show to display our dialog.
                        .show();

            }
        });

        calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int id = v.getId();

                try {
                    Bundle result = new Bundle();
                    result.putString("id", String.valueOf(idToNext));
                    getParentFragmentManager().setFragmentResult("calculatorId", result);

                    NavHostFragment.findNavController(ThirdFragment.this)
                            .navigate(R.id.action_Calculator);
                }
                catch(Exception e){
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

                /*Bundle result = new Bundle();
                result.putString("id", String.valueOf(idToNext));
                getParentFragmentManager().setFragmentResult("updateId", result);

                NavHostFragment.findNavController(ThirdFragment.this)
                        .navigate(R.id.action_ThirdFragment_to_FourthFragment);*/
    }
}