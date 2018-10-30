package com.example.xinruigao.dutyplannersaf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class NumberOfDutyPersonnel extends AppCompatActivity {

    List<SoldierNames> soldierNames;
    EditText numberOfChosenEditText;
    Button confirmButton;
    int numberOfDutyPersonnel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_of_duty_personnel);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        soldierNames = new Database(this).getNames();
        numberOfChosenEditText = findViewById(R.id.inputRandomNumberEditText);
        confirmButton = findViewById(R.id.confirmDutyButton);


        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOfChosenEditText.getText().toString().equals("")|| Integer.parseInt(numberOfChosenEditText.getText().toString())>soldierNames.size()){
                    Toast.makeText(getApplicationContext(),"Invalid Input", Toast.LENGTH_SHORT).show();
                    return;
                }
                numberOfDutyPersonnel = Integer.parseInt(numberOfChosenEditText.getText().toString());
                Intent intent = new Intent(NumberOfDutyPersonnel.this, DutyList.class);
                intent.putStringArrayListExtra("SOLDIERNAMES", getNames(soldierNames));
                intent.putExtra("NUMBEROFDUTYPERSONNEL",numberOfDutyPersonnel);
                startActivity(intent);
                soldierNames = new Database(NumberOfDutyPersonnel.this).getNames();
            }
        });
    }


    public ArrayList<String> getNames(List<SoldierNames> nameList) {
        int numberOfChosenOnes = numberOfDutyPersonnel;
        ArrayList<String> chosenOnes = new ArrayList<>();
        for (int i = 0; i < numberOfChosenOnes; i++) {
            int j = new Random().nextInt(soldierNames.size());
            chosenOnes.add(soldierNames.get(j).getName());
            soldierNames.remove(j);
        }
        return chosenOnes;

    }

}
