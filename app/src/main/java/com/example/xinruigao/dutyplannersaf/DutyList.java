package com.example.xinruigao.dutyplannersaf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class DutyList extends AppCompatActivity {

    int numberOfDutyPersonnel;
    List<SoldierNames> originalListOfNames;
    Button chooseAgainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duty_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        chooseAgainButton = findViewById(R.id.chooseAgainButton);
        ArrayList<String> finalSoldierNames = getIntent()
                .getStringArrayListExtra("SOLDIERNAMES");
        numberOfDutyPersonnel = getIntent().getIntExtra("NUMBEROFDUTYPERSONNEL", 0);
        originalListOfNames = new Database(DutyList.this).getNames();



        final FinalDutyListAdapter adapter = new FinalDutyListAdapter(this, finalSoldierNames);

        final ListView listView = findViewById(R.id.dutyListListView);
        listView.setAdapter(adapter);

        chooseAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FinalDutyListAdapter adapter = new FinalDutyListAdapter(getApplicationContext(), getNames(originalListOfNames));
                listView.setAdapter(adapter);
                originalListOfNames = new Database(DutyList.this).getNames();
            }
        });
    }

    public ArrayList<String> getNames(List<SoldierNames> nameList) {
        int numberOfChosenOnes = numberOfDutyPersonnel;
        ArrayList<String> chosenOnes = new ArrayList<>();
        for (int i = 0; i < numberOfChosenOnes; i++) {
            int j = new Random().nextInt(originalListOfNames.size());
            chosenOnes.add(originalListOfNames.get(j).getName());
            originalListOfNames.remove(j);
        }
        return chosenOnes;

    }
}
