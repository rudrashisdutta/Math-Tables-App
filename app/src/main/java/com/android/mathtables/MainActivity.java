package com.android.mathtables;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SeekBar number;
    private ListView tables;
    private TextView numberVal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number = findViewById(R.id.number);
        tables = findViewById(R.id.table);
        numberVal = findViewById(R.id.numberVal);
        number.setMin(1);
        number.setMax(200);
        changeProgress(1);
        numberVal.setText(String.valueOf(1));
        prepareForAnimation();
        animateTable(1000);
        number.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                changeProgress(i);
                numberVal.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                prepareForAnimation();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                animateTable(400);
            }
        });
    }

    private void changeProgress(int i){
        ArrayList<Integer> tableOfInt = new ArrayList<>();
        for(int multiplier=1;multiplier<=10;multiplier++){
            tableOfInt.add(i*multiplier);
        }
        ArrayAdapter<Integer> tableOfIntA = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, tableOfInt);
        tables.setAdapter(tableOfIntA);
    }
    private void prepareForAnimation(){
        tables.animate().scaleY(0).scaleY(0).alpha(0).setDuration(100);
    }
    private void animateTable(long duration){
        tables.animate().scaleX(1).scaleY(1).alpha(1).setDuration(duration);
    }
}