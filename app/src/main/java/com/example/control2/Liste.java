package com.example.control2;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class Liste extends AppCompatActivity {
    ListView lst;
    MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);
        lst=findViewById(R.id.ls);
        db=new MyDatabase(this);
        ArrayList<Societe> s =MyDatabase.getAllSociete(db.getReadableDatabase());
        ArrayList<HashMap<String,Object>> h = new ArrayList<>();
        for(Societe ec : s){
            HashMap<String,Object> b =new HashMap<>();
            b.put("nom",ec.getNom());
            b.put("nombre",ec.getNombre());
            h.add(b);
        }
        String[] from = {"nom","nombre"};
        int [] to ={R.id.trai,R.id.tcap};
        SimpleAdapter b = new SimpleAdapter(this,h,R.layout.allentreprise,from,to);
        lst.setAdapter(b);
    }
}
