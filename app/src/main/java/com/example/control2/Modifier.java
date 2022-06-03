package com.example.control2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Modifier extends AppCompatActivity {
    Spinner sp;
    MyDatabase db;
    EditText ed1,ed2,ed3;
    ArrayList<Societe> entr;
    ArrayAdapter n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier);
        sp=findViewById(R.id.sp);
        ed1=findViewById(R.id.e1);
        entr= MyDatabase.getAllSociete(db.getReadableDatabase());
        ed2=findViewById(R.id.e2);
        ed3=findViewById(R.id.e3);
        db=new MyDatabase(this);
        ArrayList<Societe> a =MyDatabase.getAllSociete(db.getReadableDatabase());
        ArrayList<String> s =new ArrayList<>();
        for(Societe e : a){
            s.add(e.getId()+"-"+e.getNom());
        }
        n = new ArrayAdapter(this,R.layout.activity_modifier,s);
        sp.setAdapter(n);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Societe p = entr.get(i);
                ed1.setText(p.getNom());
                ed2.setText(p.getSecteurActivité());
                ed3.setText(String.format("%f",p.getNombre()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void modifier(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(Modifier.this);
        alert.setTitle("Modifier ");
        alert.setMessage("Voulez-vous vraiment modifier");
        alert.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Societe e = entr.get(sp.getSelectedItemPosition());

                e.setNom(ed1.getText().toString());
                e.setSecteurActivité(ed2.getText().toString());
                e.setNombre((int) Double.parseDouble(ed3.getText().toString()));

                if(MyDatabase.UpdateSociete(db.getWritableDatabase(),e)==-1)
                    Toast.makeText(Modifier.this, "Modification Echoué", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Modifier.this, "Modification Reussie", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Modifier.this, "Operation annulee", Toast.LENGTH_SHORT).show();
            }
        });

        alert.show();
    }

    public void supprimer(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(Modifier.this);
        alert.setTitle("Suppression produit");
        alert.setMessage("Voulez-vous supprimer ce produit ?");

        alert.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Societe p = entr.get(sp.getSelectedItemPosition());

                if(MyDatabase.DeleteSociete(db.getWritableDatabase(),p.getId())==-1)
                    Toast.makeText(Modifier.this, "suppression echoue", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(Modifier.this, "Suppression reussie", Toast.LENGTH_SHORT).show();
                    n.remove(p.getId() + " - " + p.getNom());
                }
            }
        });

        alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Modifier.this, "Operation annulee", Toast.LENGTH_SHORT).show();
            }
        });

        alert.show();
    }

}
