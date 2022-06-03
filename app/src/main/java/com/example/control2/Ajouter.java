package com.example.control2;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Ajouter extends AppCompatActivity {
    EditText e1,e2,e3;
    MyDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);

        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        e3=findViewById(R.id.e3);
        db=new MyDatabase(this);
        db=new MyDatabase(this);



    }

    public void annuler(View view) {
        finish();
    }

    public void enregistrer(View view) {
        Societe s = new Societe();

        s.setNom(e1.getText().toString());
        s.setSecteurActivité(e2.getText().toString());
        s.setNombre((int) Double.parseDouble(e3.getText().toString()));

        if(MyDatabase.AddSociete(db.getWritableDatabase(),s)==-1)
            Toast.makeText(this, "Insertion echoue", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Insertion reussie", Toast.LENGTH_SHORT).show();
    }
}
