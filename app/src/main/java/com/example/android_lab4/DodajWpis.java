package com.example.android_lab4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class DodajWpis extends AppCompatActivity {

    private int modify_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_wpis);

        ArrayAdapter gatunki = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[] {"Pies", "Kot", "Rybki"}
        );

        Spinner gatunek = (Spinner) findViewById(R.id.spinner_gatunek);
        gatunek.setAdapter(gatunki);

        Bundle extras = getIntent().getExtras();
        try {
            if (extras.getSerializable("element") != null){
                Animal zwierz = (Animal) extras.getSerializable("element");
                EditText kolor = (EditText) findViewById(R.id.editTextKolor);
                EditText wielkosc = (EditText) findViewById(R.id.editTextWielkosc);
                EditText opis = (EditText) findViewById(R.id.editTextTextOpis);

                kolor.setText (zwierz.getKolor());
                wielkosc.setText(Float.toString(zwierz.getWielkosc()));
                opis.setText(zwierz.getOpis());

                this.modify_id=zwierz.getId();
            }
        } catch (Exception ex){
            this.modify_id=0;
        }
    }

    public void wyslij (View view)
    {
        EditText kolor = (EditText) findViewById(R.id.editTextKolor);
        EditText wielkosc = (EditText) findViewById(R.id.editTextWielkosc);
        EditText opis = (EditText) findViewById(R.id.editTextTextOpis);
        Spinner gatunek = (Spinner) findViewById(R.id.spinner_gatunek);

        Animal zwierze = new Animal (
                gatunek.getSelectedItem().toString(),
                kolor.getText().toString(),
                Float.valueOf (wielkosc.getText().toString() ),
                opis.getText().toString()
                );

        //EditText kontrolka = (EditText) findViewById(R.id.editTextNazwa);
        //String pole = kontrolka.getText().toString();
        //intencja.putExtra("wpis", pole);

        zwierze.setId(this.modify_id);

        Intent intencja = new Intent();
        intencja.putExtra("nowy", zwierze);

        setResult(RESULT_OK, intencja);
        finish();
    }
}