package com.example.xinruigao.dutyplannersaf;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class AllNames extends AppCompatActivity {


    List<SoldierNames> soldierNames;
    EditText numberOfDutyPersonnelEditText;
    Button selectDutyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_names);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        soldierNames = new Database(this).getNames();
        final Adapter adapter = new Adapter(this,soldierNames);

        final ListView listView = (ListView)findViewById(R.id.allNamesListView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(AllNames.this);
                dialog.setTitle("Remove Name");
                dialog.setMessage("Are you sure you want to remove this name?");

                final int positionToRemove = position;

                dialog.setNegativeButton("no", null);
                dialog.setPositiveButton("yes", new AlertDialog.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //DialogInterface is the interface used, int which means option chosen
                        SoldierNames name = soldierNames.get(positionToRemove);
                        new Database(AllNames.this).deleteNames(name);
                        soldierNames = new Database(AllNames.this).getNames();
                        Adapter adapterNew = new Adapter(AllNames.this, soldierNames);
                        listView.setAdapter(adapterNew);

                    }
                });
                dialog.show();

            }
        });
        numberOfDutyPersonnelEditText = findViewById(R.id.inputRandomNumberEditText);
        selectDutyButton = findViewById(R.id.selectDutyButton);
        selectDutyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllNames.this, NumberOfDutyPersonnel.class);
                startActivity(intent);

            }
        });
    }

}
